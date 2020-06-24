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
    private TextView username, password, address, zipCode;
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
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        address = (TextView) findViewById(R.id.address);
        zipCode = (TextView) findViewById(R.id.zipCode);
        signUp = (Button) findViewById(R.id.signUpUser);

        setOnClickListenersForButtons();
    }

    private void setOnClickListenersForButtons(){
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString() != null && password.getText().toString() != null
                        && address.getText().toString() != null && zipCode.getText().toString() != null){
                    writeToDataBase();
                }
            }
        });
    }

    public void writeToDataBase() {

        mDatabase = FirebaseDatabase.getInstance().getReference();

        writeNewUserToDataBase(username.getText().toString(), password.getText().toString(), address.getText().toString(), zipCode.getText().toString());

    }

    private void writeNewUserToDataBase(String username, String password, String address, String zipCode) {
        User user = new User(username, password, address, zipCode);
        mDatabase.child("users").child(username).setValue(user);
    }
}
