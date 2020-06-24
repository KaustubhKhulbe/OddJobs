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

    private static final String TAG = "TAG";
    //UI Components
    private TextView username, password;
    private Button logIn, signUp;

    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseReference;
    private ValueEventListener databaseListener;
    private boolean status = false;

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

                readUserData();

                if(status){

                }

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

    private void readUserData(){
        mDatabaseReference = FirebaseDatabase.getInstance().getReference()
                .child("users");

        databaseListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                // Post post = dataSnapshot.getValue(Post.class);
                // ...

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    System.out.println(ds.getChildren());
                    System.out.println("TRUETRUETRUETRUE");


                    if(ds.getValue(User.class).getUsername().equals(username.getText().toString()) && ds.getValue(User.class).getPassword().equals(password.getText().toString())){
                        System.out.println("TRUETRUETRUETRUE");
                        System.out.println(ds.getValue(User.class).getUID()+ "BILLYBILLYBOBOB");
                        Intent intent = new Intent(MainActivity.this, succesfullyLogedIn.class);
                        intent.putExtra("UID", ds.getValue(User.class).getUID());
                        startActivity(intent);
                        status = true;
                    }
                    else{
                        System.out.println("BOOOOOOOOOO");

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
