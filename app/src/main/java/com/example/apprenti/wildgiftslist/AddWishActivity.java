package com.example.apprenti.wildgiftslist;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddWishActivity extends AppCompatActivity {

    private FirebaseDatabase mFire;
    private DatabaseReference mDbRef;

    private static final int GALLERY_INTENT = 20;
    private StorageReference mStorage;

    private ImageView mGiftImage;
    private String mImageID;

    private EditText mName;
    private String nameValue;
    private EditText mDescription;
    private String descriptionValue;
    private EditText mLink;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wish);

        Intent intent = getIntent();
        final String usrID = intent.getStringExtra("userID");

        mFire = FirebaseDatabase.getInstance();
        mDbRef = mFire.getReference("User");
        mStorage = FirebaseStorage.getInstance().getReference();

        mName = (EditText) findViewById(R.id.name);
        mDescription = (EditText) findViewById(R.id.description);
        mLink = (EditText) findViewById(R.id.link);

        mGiftImage = (ImageView) findViewById(R.id.giftImage);



        getSupportActionBar().setTitle(R.string.add_wish);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button abort = findViewById(R.id.abort);
        abort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button create = findViewById(R.id.send);
        create.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mName = (EditText) findViewById(R.id.name);
                mDescription = (EditText) findViewById(R.id.description);
                mLink = (EditText) findViewById(R.id.link);
                nameValue = mName.getText().toString();
                descriptionValue = mDescription.getText().toString();
                if (nameValue.isEmpty() || descriptionValue.isEmpty() ) {
                    Toast.makeText(AddWishActivity.this,
                            getResources().getString(R.string.empty_fields),
                            Toast.LENGTH_LONG).show();
                } else {

                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, GALLERY_INTENT);
                }

                currentWish();
            }
        });
       // currentWish();
    }

    protected void currentWish() {

        mDbRef.child("souhait").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mName = (EditText) findViewById(R.id.name);
                mDescription = (EditText) findViewById(R.id.description);
                mLink = (EditText) findViewById(R.id.link);

                WishModel wishAdd = dataSnapshot.getValue(WishModel.class);
                mName.setText(wishAdd.getName());
                mDescription.setText(wishAdd.getDescription());
                mLink.setText(wishAdd.getLink());
                Glide.with(getApplicationContext()).load(wishAdd.getImage()).into(mGiftImage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDbRef.child("souhait/descriptionSouhait").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String wishDescription = dataSnapshot.getValue(String.class);
                mDescription.setText(wishDescription);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDbRef.child("souhait/image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String wishImage = dataSnapshot.getValue(String.class);
                //description.setText(wishImage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDbRef.child("souhait/descriptionLien").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String wishLink = dataSnapshot.getValue(String.class);
                mLink.setText(wishLink);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK){
            Uri uri = data.getData();

            final StorageReference filepath = mStorage.child("Image").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    WishModel createdWish = new WishModel(mName.getText().toString(),
                            mDescription.getText().toString(),
                            taskSnapshot.getDownloadUrl().toString(),
                            mLink.getText().toString());

                    String idWish = mDbRef.push().getKey();
                    mDbRef.child("souhait").child(idWish).setValue(createdWish);
                    //Uri downloadUri = taskSnapshot.getDownloadUrl();
                    Glide.with(AddWishActivity.this).load(createdWish.getImage()).into(mGiftImage);
                    Toast.makeText(AddWishActivity.this, R.string.download, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}