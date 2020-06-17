package com.example.signupform;

import androidx.annotation.NonNull;
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

        StringBuilder msg = new StringBuilder(getString(R.string.thank_you));
        Intent intent = getIntent();
        Bundle b = intent.getExtras();


        String username = getString(R.string.test_name);

        int age = 30;

        if (b != null) {
            if (b.containsKey(Constants.KEY_USERNAME)) {
                username = b.getString(Constants.KEY_USERNAME);
            }

            msg.append(username).append(getString(R.string.stringForUse));

            textView.setText(msg);
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(Constants.KEY_GREETING)) {
            textView.setText((String)savedInstanceState.get(Constants.KEY_GREETING));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.KEY_GREETING, textView.getText().toString());
    }

    public void goToFirstActivity(View view) {
        finish();
    }
}

