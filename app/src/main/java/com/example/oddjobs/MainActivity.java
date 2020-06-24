package com.example.oddjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //UI Components
    private TextView username, password;
    private Button logIn, signUp;

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
}
