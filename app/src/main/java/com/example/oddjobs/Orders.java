package com.example.oddjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Orders extends AppCompatActivity {

    Button[] buttons;
    String[] addresses;
    String name;
    private DatabaseReference mDatabaseReference;
    private ValueEventListener databaseListener;
    private String TAG = "TAG";
    RelativeLayout myLayout;
    private int currentX, currentY, firstX = 500, firstY = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        name = intent.getExtras().getString("name");

        currentX = firstX;
        currentY = firstY;

        myLayout = new RelativeLayout(this);
        readUserData();
        setContentView(myLayout);

    }

    private void readUserData(){
        mDatabaseReference = FirebaseDatabase.getInstance().getReference()
                .child("jobs");

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
                //userAddress.setText(dataSnapshot.child(UID).getValue(User.class).getAddress());

                //TextView mytxt = new TextView(Orders.this);


                buttons = new Button[(int)dataSnapshot.getChildrenCount()];
                addresses = new String[buttons.length];
                System.out.println("button length is: " + buttons.length);
                int count = 0;

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    System.out.println(ds.getValue(Post.class).isAccepted());
                    if(!ds.getValue(Post.class).isAccepted()){
                        buttons[count] = new Button(Orders.this);
                        buttons[count].setText(ds.getValue(Post.class).getJob());
                        buttons[count].setBackgroundColor(255);
                       // buttons[count].setTextColor(180);
                       // buttons[count].isUnd
                        addresses[count] = ds.getValue(Post.class).getAddress();
                        buttons[count].setX(currentX);
                        buttons[count].setY(currentY+100);
                        myLayout.addView(buttons[count]);
                        currentY+= 300;
                        inititalizeButton(count, ds.getValue(Post.class).getAddress());

                        count++;


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

    private void inititalizeButton(int count, final String address) {
       buttons[count].setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Orders.this, OrderConfirmation.class);
               intent.putExtra("name", name +"_"+address);
               startActivity(intent);
           }
       });
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
