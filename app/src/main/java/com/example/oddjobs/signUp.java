package com.example.oddjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signUp extends AppCompatActivity {

    //Declare UI Components
    private TextView email, username, password;
    private Button signUp;

    //FireBase Components
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initializeUIComponents();
    }

    private void initializeUIComponents(){
        email = (TextView) findViewById(R.id.email);
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        signUp = (Button) findViewById(R.id.signUp);

        setOnClickListenersForButtons();
    }

    private void setOnClickListenersForButtons(){
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString() != null && username.getText().toString() != null && password.getText().toString() != null){

                }
            }
        });
    }

    public void writeToDataBase() {
        //TODO need to make it so that it checks
        // if checkmark is ticked at sign up time, not at
        // anytime or this will enable it as true if user
        // accidentally clicks it


        //TODO database updating previously signed in user not current user
        mDatabase = FirebaseDatabase.getInstance().getReference();

        writeNewUserToDataBase(email.getText().toString(), username.getText().toString(), password.getText().toString());

    }

    private void writeNewUserToDataBase(String email, String username, String password) {
        User user = new User(email, username, password);
        mDatabase.child("users").child(email).setValue(user);
    }
}
