package com.example.theholyquran.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.theholyquran.R;

public class MainActivity extends AppCompatActivity {

    private ImageView imgHome;
    private TextView tvAppName, tvDescription;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgHome = findViewById(R.id.img_home);
        tvAppName = findViewById(R.id.tv_app_name);
        tvDescription = findViewById(R.id.tv_description);
        btnStart = findViewById(R.id.btn_start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}