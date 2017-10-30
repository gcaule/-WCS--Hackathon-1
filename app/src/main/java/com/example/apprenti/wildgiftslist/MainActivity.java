package com.example.apprenti.wildgiftslist;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase mFire;
    private DatabaseReference mRef;
    private ListView mList_souhait;
    private Adapter_List_Souhait mAdapterListSouhait;
    private List<WishModel> mWish_List = new ArrayList<>();
    private WishModel mWm = new WishModel();

    private int mBackButtonCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        final String usrID = intent.getStringExtra("userID");

        //Toolbar personnalisée avec bouton retour à la page précédente
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        TextView souhait = (TextView) findViewById(R.id.souhait);
        TextView offert = (TextView) findViewById(R.id.offert);
        TextView offrir = (TextView) findViewById(R.id.offrir);
        mList_souhait = (ListView) findViewById(R.id.listsouhait);

        mFire = FirebaseDatabase.getInstance();
        mRef = mFire.getReference("User").child(usrID);

        ImageView add = (ImageView) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddWishActivity.class);
                intent.putExtra("userID", usrID);
                //startActivity(new Intent(MainActivity.this, AddWishActivity.class));
                startActivity(intent);
            }
        });
        addWish();
    }

    protected void addWish(){
        mRef.child("souhait").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()){
                    WishModel lsm = snap.getValue(WishModel.class);
                    //Glide.with(MainActivity.this).load(lsm.getImage()).into();
                    mWish_List.add(lsm);
                    mAdapterListSouhait = new Adapter_List_Souhait(MainActivity.this, mWish_List);
                    mList_souhait.setAdapter(mAdapterListSouhait);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBackButtonCount = 0;
    }

    @Override
    public void onBackPressed() {
        if(mBackButtonCount > 0) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else{
            //
        }
    }
}


       /* souhait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        offert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        offrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        */
