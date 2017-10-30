package com.example.apprenti.wildgiftslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.zip.Inflater;

/**
 * Created by katarie on 30/10/2017.
 */

public class Souhait extends Fragment {

    public Souhait() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ConstraintLayout rootView = (ConstraintLayout) inflater.inflate(R.layout.souhait, container, false);

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
        return rootView;
    }

}