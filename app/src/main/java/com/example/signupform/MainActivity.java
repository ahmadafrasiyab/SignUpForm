package com.example.signupform;

import androidx.annotation.NonNull;
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
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private String name ="";
    private String email ="";
    private String username ="";
    private String dateOfBirth ="";

    private EditText nameEdit, emailEdit, usernameEdit, dateEdit;
    private TextView nav, title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEdit =(EditText)findViewById(R.id.name);
        emailEdit =(EditText) findViewById(R.id.email);
        usernameEdit = (EditText) findViewById(R.id.username);
        nav = findViewById(R.id.nav);
        title = findViewById(R.id.title);
        dateEdit = findViewById(R.id.dateOfBirth);
        dateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), getString(R.string.date_picker));
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EditText txt4 = (EditText)findViewById(R.id.dateOfBirth);
        Calendar c = Calendar.getInstance();

        if (year >= 2003) {
            Toast.makeText(this, R.string.not_18, Toast.LENGTH_LONG).show();
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
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(Constants.KEY_NAME)) {
            nameEdit.setText((String)savedInstanceState.get(Constants.KEY_NAME));
        }

        if (savedInstanceState.containsKey(Constants.KEY_USERNAME)) {
            usernameEdit.setText((String)savedInstanceState.get(Constants.KEY_USERNAME));
        }

        if (savedInstanceState.containsKey(Constants.KEY_EMAIL)) {
            emailEdit.setText((String)savedInstanceState.get(Constants.KEY_EMAIL));
        }

        if (savedInstanceState.containsKey(Constants.KEY_DATE)) {
            dateEdit.setText((String)savedInstanceState.get(Constants.KEY_DATE));
        }

        if (savedInstanceState.containsKey(Constants.KEY_NAV)) {
            nav.setText((String)savedInstanceState.get(Constants.KEY_NAV));
        }

        if (savedInstanceState.containsKey(Constants.KEY_TITLE)) {
            title.setText((String)savedInstanceState.get(Constants.KEY_TITLE));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.KEY_USERNAME, usernameEdit.getText().toString());
        outState.putString(Constants.KEY_EMAIL, emailEdit.getText().toString());
        outState.putString(Constants.KEY_NAME, nameEdit.getText().toString());
        outState.putString(Constants.KEY_DATE,dateEdit.getText().toString());
        outState.putString(Constants.KEY_NAV, nav.getText().toString());
        outState.putString(Constants.KEY_TITLE,title.getText().toString());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        EditText txt1 = findViewById(R.id.name);
        EditText txt2 = findViewById(R.id.email);
        EditText txt3 = findViewById(R.id.username);
        EditText txt4 = findViewById(R.id.dateOfBirth);
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
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
            txt1.setError(getString(R.string.name_cannot_be_empty));
            return;
        }

         while((email.isEmpty()) || (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))) {
            txt2.setError(getString(R.string.invalid_email));
            return;
        }

        while (username.isEmpty()) {
            txt3.setError(getString(R.string.username_cannot_be_empty));
            return;
        }
        while ((dateOfBirth.isEmpty())) {
            txt4.setError(getString(R.string.dateOfBirth_cannot_be_empty));
            return;
        }

        Intent intent = new Intent(MainActivity.this, Main2Activity.class);

        intent.putExtra(Constants.KEY_USERNAME, username);

        startActivity(intent);
    }
}
