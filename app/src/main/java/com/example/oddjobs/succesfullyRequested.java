package com.example.oddjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class succesfullyRequested extends AppCompatActivity {

    private Button returnToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succesfully_requested);

        returnToMenu = (Button)findViewById(R.id.menu);
        returnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(succesfullyRequested.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
