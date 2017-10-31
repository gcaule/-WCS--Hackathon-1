package com.example.apprenti.wildgiftslist;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;

/**
 * @author greg
 * @since 6/21/13
 *
 * This class is an example of how to use FirebaseListAdapter. It uses the <code>Chat</code> class to encapsulate the
 * data for each individual chat message
 */

public class GiftAdapter extends FirebaseListAdapter<WishModel> {

    public GiftAdapter(Query ref, Activity activity, int layout) {
        super(ref, WishModel.class, layout, activity);
    }

    @Override
    protected void populateView(View v, WishModel wishModel) {

        //((ImageView)v.findViewById(R.id.imgOff).getDownloadUrl().toString();
        ((TextView)v.findViewById(R.id.nomOff)).setText(wishModel.getName());
        ((TextView)v.findViewById(R.id.descOff)).setText(wishModel.getDescription());
        ((TextView)v.findViewById(R.id.linkOff)).setText(wishModel.getLink());
    }
}