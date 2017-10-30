package com.example.apprenti.wildgiftslist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katarie on 30/10/2017.
 */

public class Souhait extends Fragment {

    private ListView mList_souhait;
    private Adapter_List mAdapterListSouhait;
    private List<WishModel> mWish_List = new ArrayList<>();
    private WishModel mWm = new WishModel();
    private DatabaseReference mRef;
    private FirebaseDatabase mFire;
    private String mUserID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ConstraintLayout rootView = (ConstraintLayout) inflater.inflate(R.layout.souhait, container, false);

        mList_souhait = (ListView) rootView.findViewById(R.id.listsouhait);

        final SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        mUserID = sharedpreferences.getString("mUserId", "");

        ImageView add = (ImageView) rootView.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = getActivity().getIntent();
                final String usrID = intent.getStringExtra("userID");
                Intent wishIntent = new Intent(getActivity(), AddWishActivity.class);
                wishIntent.putExtra("userID", usrID);
                getActivity().startActivity(wishIntent);
            }
        });
        addWish();
        return rootView;
    }

    protected void addWish(){

        mFire = FirebaseDatabase.getInstance();
        mRef = mFire.getReference("User").child(mUserID);

        mRef.child("souhait").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()){
                    WishModel lsm = snap.getValue(WishModel.class);
                    //Glide.with(MainActivity.this).load(lsm.getImage()).into();
                    mWish_List.add(lsm);
                    mAdapterListSouhait = new Adapter_List(getActivity(), mWish_List);
                    mList_souhait.setAdapter(mAdapterListSouhait);
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}