package com.example.apprenti.wildgiftslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by katarie on 30/10/2017.
 */

public class FirstFragment extends Fragment {

    public FirstFragment() {
        // Required empty public constructor
    }

    public void goToAttract(View v) {

        ImageView add = (ImageView) getView().findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getActivity().getIntent();
                final String usrID = intent.getStringExtra("userID");

                Intent wishIntent = new Intent(getActivity(), AddWishActivity.class);
                wishIntent.putExtra("userID", usrID);
                getActivity().startActivity(wishIntent);

            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }
}