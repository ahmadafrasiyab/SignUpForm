package com.example.signupform;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = Main2Activity.class.getSimpleName();
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.textView);

        StringBuilder msg = new StringBuilder("Thank you ");
        Intent intent = getIntent();
        Bundle b = intent.getExtras();


        String username = "aabbcc";

        int age = 30;

        if (b != null) {
            if (b.containsKey(Constants.KEY_USERNAME)) {
                username = b.getString(Constants.KEY_USERNAME);
            }

            msg.append(username).append(" for signing up\n");

            textView.setText(msg);
        }
    }

    public void goToFirstActivity(View view) {
        finish();
    }
}

