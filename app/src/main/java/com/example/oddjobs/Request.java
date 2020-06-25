package com.example.oddjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Request extends AppCompatActivity {

    private TextView jobAddress, jobName, jobDescription, jobRate;
    private Button Confirm;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        initializeUIComponents();
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

        Post post = new Post(address,job,description,rate, false);
        mDatabase.child("jobs").child(address).setValue(post);
    }
}
