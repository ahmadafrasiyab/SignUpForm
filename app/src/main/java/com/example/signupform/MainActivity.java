package com.example.signupform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private String name ="";
    private String email ="";
    private String username ="";
    private String dateOfBirth ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText txt4 = findViewById(R.id.dateOfBirth);
        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EditText txt4 = (EditText)findViewById(R.id.dateOfBirth);
        Calendar c = Calendar.getInstance();

        if (year >= 2003) {
            Toast.makeText(this, "Not 18", Toast.LENGTH_LONG).show();
            return;
        } else {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String currentDateString = DateFormat.getDateInstance().format(c.getTime());
            txt4.setText(currentDateString);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        EditText txt1 = (EditText)findViewById(R.id.name);
        EditText txt2 = (EditText)findViewById(R.id.email);
        EditText txt3 = (EditText)findViewById(R.id.username);
        EditText txt4 = (EditText)findViewById(R.id.dateOfBirth);
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");

    }

    public void goToSecondActivity(View view) {
        EditText txt1 = (EditText)findViewById(R.id.name);
        EditText txt2 = (EditText)findViewById(R.id.email);
        EditText txt3 = (EditText)findViewById(R.id.username);
        EditText txt4 = (EditText)findViewById(R.id.dateOfBirth);
        name = txt1.getText().toString();
        email = txt2.getText().toString();
        username = txt3.getText().toString();
        dateOfBirth = txt4.getText().toString();

        while (name.isEmpty()) {
            txt1.setError("Name cannot be empty");
            return;
        }

         while((email.isEmpty()) || (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))) {
            txt2.setError("invalid email");
            return;
        }

        while (username.isEmpty()) {
            txt3.setError("username cannot be empty");
            return;
        }
        while ((dateOfBirth.isEmpty())) {
            txt4.setError("dateOfBirth cannot be empty");
            return;
        }

        Intent intent = new Intent(MainActivity.this, Main2Activity.class);

        intent.putExtra(Constants.KEY_USERNAME, username);

        startActivity(intent);
    }
}
