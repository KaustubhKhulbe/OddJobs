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

public class OrderConfirmation extends AppCompatActivity {

    private static final String TAG = "TAG";
    String name, UID, address;
    private Button accept;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseReference;
    private ValueEventListener databaseListener;
    //private TextView JobName, JobDescription, JobRate, JobAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);


        Intent intent = getIntent();
        String temp = intent.getExtras().getString("name");
        name = temp.substring(0, temp.indexOf("_"));
        address = temp.substring(temp.indexOf("_") + 1);

        System.out.println(name);
        System.out.println(address);

        initialize();
    }

    private void initialize() {

       /* JobName = (TextView)findViewById(R.id.JOBNAME);
        JobDescription = (TextView)findViewById(R.id.JOBDESCRIPTION);
        JobRate = (TextView)findViewById(R.id.JOBRATE);
        JobAddress = (TextView)findViewById(R.id.JOBADDRESS);*/
        accept = (Button)findViewById(R.id.Accept);

       // readUserData();

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  getUserFromUID();
            }
        });

    }

    private void readUserData(){
        mDatabaseReference = FirebaseDatabase.getInstance().getReference()
                .child("posts");

        databaseListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    System.out.println(ds.getValue(Post.class).getAddress().equals(address));
                    System.out.println(ds.getValue(Post.class).getAddress());

                    if(ds.getValue(Post.class).getAddress().equals(address)){
                        UID = dataSnapshot.getValue(Post.class).getHomeUID();
                        /*JobName.setText(dataSnapshot.getValue(Post.class).getJob());
                        JobDescription.setText(dataSnapshot.getValue(Post.class).getDescription());
                        JobRate.setText(dataSnapshot.getValue(Post.class).getRate());
                        JobAddress.setText(dataSnapshot.getValue(Post.class).getAddress());*/
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

       // getUserFromUID();
        mDatabaseReference.addValueEventListener(databaseListener);
    }

    private void getUserFromUID() {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference()
                .child("users");

        databaseListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //UID = dataSnapshot.getValue(Post.class).getHomeUID();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    if(ds.getValue(User.class).getUsername().equals(name)){
                        //dataSnapshot.getValue(User.class).setAccepted("Job: " + JobName.getText().toString() + " accepted by: " + name);
                    }
                }

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
