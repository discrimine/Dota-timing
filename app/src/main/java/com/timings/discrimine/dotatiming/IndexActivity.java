package com.timings.discrimine.dotatiming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class IndexActivity extends AppCompatActivity {

    ImageButton imageButtonStart;
    ImageButton imageButtonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_index);
        imageButtonStart = (ImageButton) findViewById(R.id.imageButtonStart);
        imageButtonSettings = (ImageButton) findViewById(R.id.imageButtonSettings);


        imageButtonStart.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(IndexActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        imageButtonSettings.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(IndexActivity.this, SettingsAppActivity.class);
                        startActivity(intent);
                    }
                });


    }
}
