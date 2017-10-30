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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wish);

        mFire = FirebaseDatabase.getInstance();
        mDbRef = mFire.getReference("user/wish");
        mStorage = FirebaseStorage.getInstance().getReference();

        mName = findViewById(R.id.name);
        nameValue = mName.getText().toString();
        mDescription = findViewById(R.id.description);
        descriptionValue = mDescription.getText().toString();
        mLink = findViewById(R.id.link);

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

        Button create = findViewById(R.id.button_create);
        create.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (nameValue.isEmpty() || descriptionValue.isEmpty() ) {
                    Toast.makeText(AddWishActivity.this,
                            getResources().getString(R.string.empty_fields),
                            Toast.LENGTH_LONG).show();
                } else {

                    /*createdWish.setName(name.getText().toString());
                    createdWish.setDescription(description.getText().toString());
                    createdWish.setImage(mImageID);
                    createdWish.setLink(link.getText().toString());*/

                    //mDbRef.child("souhait").setValue(createdWish);
                    //Toast.makeText(AddWishActivity.this, R.string.createdWish, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, GALLERY_INTENT);
                }
                currentWish();
            }
        });

    }

    protected void currentWish() {

        final EditText name = findViewById(R.id.name);
        final EditText description = findViewById(R.id.description);
        final EditText link = findViewById(R.id.link);

        mDbRef.child("souhait/nomSouhait").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nameWish = dataSnapshot.getValue(String.class);
                name.setText(nameWish);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDbRef.child("souhait/descriptionSouhait").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String wishDescription = dataSnapshot.getValue(String.class);
                description.setText(wishDescription);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDbRef.child("souhait/descriptionImage").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String wishImage = dataSnapshot.getValue(String.class);
                description.setText(wishImage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDbRef.child("souhait/descriptionLien").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String wishLink = dataSnapshot.getValue(String.class);
                link.setText(wishLink);
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
                    Uri downloadUri = taskSnapshot.getDownloadUrl();
                    Glide.with(AddWishActivity.this).load(downloadUri).into(mGiftImage);
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