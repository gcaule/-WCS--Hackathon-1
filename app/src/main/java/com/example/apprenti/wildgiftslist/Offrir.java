package com.example.apprenti.wildgiftslist;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katarie on 30/10/2017.
 */

public class Offrir extends Fragment {


    private DatabaseReference mRef;
    private FirebaseDatabase mFire;
    private List<WishModel> offrir_models = new ArrayList<>();
    private Adapter_List_Offrir mAdapter_Off;
    private User user = new User();
    private WishModel mWishM;
    private ListView mListOff;

    private String mUserID;
    private EditText mRechercher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.offrir, container, false);

        final ConstraintLayout rootView = (ConstraintLayout) inflater.inflate(R.layout.offrir, container, false);
        mRechercher = (EditText) rootView.findViewById(R.id.rechercherOff);
        Button search = (Button) rootView.findViewById(R.id.search);

        mFire = FirebaseDatabase.getInstance();
        mRef = mFire.getReference("User");

        final String personne = mRechercher.getText().toString();

        //final SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        //mUserID = sharedpreferences.getString("mUserId", "");

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String personne = mRechercher.getText().toString();
                mRef.orderByChild("user_name").equalTo(personne).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot userDatasnapshot : dataSnapshot.getChildren()){

                            ListView list = rootView.findViewById(R.id.listoffrir);

                            Query wishQuery = mRef.child(userDatasnapshot.getKey()).child("souhait").orderByKey();
                            GiftAdapter adapter = new GiftAdapter(
                                    wishQuery,
                                    getActivity(), R.layout.activity_list_offrir_item);
                            list.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        return rootView;
    }
}