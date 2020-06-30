package com.example.oddjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class succesfullyLogedIn extends AppCompatActivity {

    private static TextView userName, userAddress;
    private static Button orders, request;

    private static final String TAG = "TAG";
    private String UID, address;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseReference;
    private ValueEventListener databaseListener;
    private boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succesfully_loged_in);

        Intent intent = getIntent();
        UID = intent.getExtras().getString("UID");
        System.out.println(UID+"JEFFJEFFJEFFJEFF");

        userName = (TextView) findViewById(R.id.UserName);
        userAddress = (TextView) findViewById(R.id.userAddress);

        orders = (Button) findViewById(R.id.Jobs);
        request = (Button) findViewById(R.id.request);



        readUserData();


        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(succesfullyLogedIn.this, Orders.class);
                intent.putExtra("name", userName.getText().toString());
                startActivity(intent);
            }
        });

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(succesfullyLogedIn.this, Request.class);
                intent.putExtra("UID", UID);
                startActivity(intent);
            }
        });


    }
    private void readUserData(){
        mDatabaseReference = FirebaseDatabase.getInstance().getReference()
                .child("users");

        databaseListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                // Post post = dataSnapshot.getValue(Post.class);
                // ...

               /* for(DataSnapshot ds: dataSnapshot.getChildren()){
                    System.out.println(ds.getChildren());
                    System.out.println(ds.getValue(User.class).getUID());


                    System.out.println(ds.child(UID).exists());



                }*/
                userName.setText(dataSnapshot.child(UID).getValue(User.class).getUsername());
                userAddress.setText(dataSnapshot.child(UID).getValue(User.class).getAddress());
                address = dataSnapshot.child(UID).getValue(User.class).getAddress();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mDatabaseReference.addValueEventListener(databaseListener);
    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (databaseListener != null) {
            mDatabaseReference.removeEventListener(databaseListener);
        }

    }



}
