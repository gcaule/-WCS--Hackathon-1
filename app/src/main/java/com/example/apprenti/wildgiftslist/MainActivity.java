package com.example.apprenti.wildgiftslist;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase mFire;
    private DatabaseReference mRef;
    private ListView mList_souhait;

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
        mRef = mFire.getReference();

        souhait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
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

        ImageView add = (ImageView) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddWishActivity.class);
                intent.putExtra("userID", usrID);
                startActivity(new Intent(MainActivity.this, AddWishActivity.class));
            }
        });
    }
}
