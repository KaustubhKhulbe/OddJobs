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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //UI Components
    private TextView username, password;
    private Button logIn, signUp;

    private FirebaseAuth mAuth;
    private static final String TAG = "TAG";
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUIComponents();
    }

    private void initializeUIComponents(){
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        logIn = (Button) findViewById(R.id.logIn);
        signUp = (Button) findViewById(R.id.signUp);

        setOnClickListenersForButtons();
    }

    private void setOnClickListenersForButtons(){
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //confirmAccountDetails();

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, signUp.class);
                startActivity(intent);
            }
        });
    }

   /* private void confirmAccountDetails() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                checkUserInfo(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void checkUserInfo(DataSnapshot dataSnapshot){

        for(DataSnapshot ds: dataSnapshot.getChildren()){
            User user = new User();
        }

    }*/


}
