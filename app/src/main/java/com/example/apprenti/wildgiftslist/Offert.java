package com.example.apprenti.wildgiftslist;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class Offert extends Fragment {

    private ListView mList_offert;
    private Adapter_List mAdapterListOffert;
    private List<WishModel> mWish_List = new ArrayList<>();
    private WishModel mWm = new WishModel();
    private DatabaseReference mRef;
    private FirebaseDatabase mFire;
    private String mUserID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ConstraintLayout rootView = (ConstraintLayout) inflater.inflate(R.layout.offert, container, false);

        mList_offert = (ListView) rootView.findViewById(R.id.listoffert);

        final SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        mUserID = sharedpreferences.getString("mUserId", "");

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
                    mAdapterListOffert = new Adapter_List(getActivity(), mWish_List);
                    mList_offert.setAdapter(mAdapterListOffert);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}