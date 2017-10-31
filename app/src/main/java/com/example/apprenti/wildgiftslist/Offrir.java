package com.example.apprenti.wildgiftslist;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
        mListOff = (ListView) rootView.findViewById(R.id.listoffrir);

        mFire = FirebaseDatabase.getInstance();
        mRef = mFire.getReference("User");

        final String personne = mRechercher.getText().toString();

        //final SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        //mUserID = sharedpreferences.getString("mUserId", "");

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String personne = mRechercher.getText().toString();
                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            //User personne = data.getValue(User.class);
                            /*if (personne.equals(usr.getUser_name())) {
                                mRef = mRef.child("souhait");
                                WishModel off = dataSnapshot.getValue(WishModel.class);
                                offrir_models.add(off);
                                mAdapter_Off = new Adapter_List_Offrir(getActivity(), offrir_models);
                                mListOff.setAdapter(mAdapter_Off);
                            }*/

                            ListView list = rootView.findViewById(R.id.listoffrir);

                            DatabaseReference giftOffered = mRef.child("souhait");
                            GiftAdapter adapter = new GiftAdapter(
                                    giftOffered,
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