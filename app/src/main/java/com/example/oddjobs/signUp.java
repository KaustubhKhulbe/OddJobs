package com.example.oddjobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signUp extends AppCompatActivity {

    private static final String TAG = "TAG";
    //Declare UI Components
    private TextView email, username, password, address, zipCode;
    private Button signUp;
    private FirebaseAuth mAuth;

    //FireBase Components
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initializeUIComponents();
    }

    private void initializeUIComponents(){
        email = (TextView) findViewById(R.id.UserEmail);
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        address = (TextView) findViewById(R.id.address);
        zipCode = (TextView) findViewById(R.id.zipCode);
        signUp = (Button) findViewById(R.id.signUpUser);

        setOnClickListenersForButtons();
        initializeFireBaseComponents();
    }

    private void initializeFireBaseComponents(){
        mAuth = FirebaseAuth.getInstance();

    }

    private void createUser(String email, String password) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(signUp.this, "User created.",
                                    Toast.LENGTH_SHORT).show();
                            //FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(signUp.this, successfullySignedUp.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(signUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        signUserIn(email, password);
    }

    private void signUserIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(signUp.this, "signInWithEmail:success",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(signUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void setOnClickListenersForButtons(){
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString() != null && password.getText().toString() != null
                        && address.getText().toString() != null && zipCode.getText().toString() != null){
                    writeToDataBase();
                    createUser(email.getText().toString(), password.getText().toString());
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
        mDatabase.child("users").child(mAuth.getCurrentUser().getUid()).setValue(user);
    }
}
