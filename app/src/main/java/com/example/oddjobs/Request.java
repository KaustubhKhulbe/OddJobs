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

public class Request extends AppCompatActivity {

    private static final String TAG = "TAG";
    private TextView jobAddress, jobName, jobDescription, jobRate;
    private Button Confirm;
    private String homeUID;

    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseReference;
    private ValueEventListener databaseListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        initializeUIComponents();
        Intent intent = getIntent();
        homeUID = intent.getExtras().getString("UID");
    }

    private void initializeUIComponents() {
        jobAddress = (TextView) findViewById(R.id.jobAddress);
        jobName = (TextView) findViewById(R.id.jobName);
        jobDescription = (TextView) findViewById(R.id.jobDescription);
        jobRate = (TextView) findViewById(R.id.jobRate);

        Confirm = (Button) findViewById(R.id.Confirm);
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPost();
                Intent intent = new Intent(Request.this, succesfullyRequested.class);
                startActivity(intent);
            }
        });
    }

    private void createPost() {
        writeToDataBase();
    }

    public void writeToDataBase() {

        mDatabase = FirebaseDatabase.getInstance().getReference();

        writeNewUserToDataBase(jobAddress.getText().toString(), jobName.getText().toString(), jobDescription.getText().toString(), Integer.parseInt(jobRate.getText().toString()));
    }

    private void writeNewUserToDataBase(String address, String job, String description, int rate) {
        //String UID = mAuth.getCurrentUser().getUid();

       // System.out.println(UID + "THIS IS THE UID HIROWVNIDGNWNLSNDKKDJFKDKKKKKKK");
        //User user = new User(username, password, address, zipCode, UID);
        ///mDatabase.child("users").child(UID).setValue(user);
        //readUserData();
        Post post = new Post(address,job,description,rate, false, homeUID);
        mDatabase.child("jobs").child(address).setValue(post);
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
                //userName.setText(dataSnapshot.child(UID).getValue(User.class).getUsername());
               // userAddress.setText(dataSnapshot.child(UID).getValue(User.class).getAddress());
                  //homeAddress = dataSnapshot.getValue(Post.class).getAddress();

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
}
