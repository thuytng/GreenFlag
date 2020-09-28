package com.example.dom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterCredentialsActivity extends AppCompatActivity {

    private final String TAG = RegisterCredentialsActivity.class.getSimpleName();
    Button nextButton;
    EditText etEmail;
    EditText etCreatePw;
    EditText etRepeatPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_credentials);

        nextButton = findViewById(R.id.btn_register_next);
        etEmail = findViewById(R.id.et_email_address);
        etCreatePw = findViewById(R.id.et_create_pw);
        etRepeatPw = findViewById(R.id.et_repeat_pw);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String createPw = etCreatePw.getText().toString();
                String repeatPw = etRepeatPw.getText().toString();
                if (!validEmail(email)) {
                    Log.d(TAG, email);
                    Toast.makeText(RegisterCredentialsActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
                } else if (!validPw(createPw) || !validPw(repeatPw)) {
                    Log.d(TAG, "invalid pw");
                    Toast.makeText(RegisterCredentialsActivity.this, "Invalid pw. See details below.", Toast.LENGTH_SHORT).show();
                } else if (!createPw.equals(repeatPw)) {
                    Log.d(TAG, "unequal pws");
                    Toast.makeText(RegisterCredentialsActivity.this, "Passwords not equal", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG, "start new activity");
                    Intent intent = new Intent(RegisterCredentialsActivity.this, RegisterProfileActivity.class);
                    startActivity(intent);
                }

            }
        });


    }

    private boolean validPw(String pw) {
        boolean hasValidLength = pw.length() == 8;
        boolean hasUppercase = !pw.equals(pw.toLowerCase());
        boolean hasLowercase = !pw.equals(pw.toUpperCase());

        Pattern p = Pattern.compile( "[0-9]" );
        Matcher m = p.matcher(pw);
        boolean hasNumber = m.find();

        return hasValidLength && hasLowercase && hasUppercase && hasNumber;
    }

    private boolean validEmail(String email) {
        // TODO check availability in db
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        return pat.matcher(email).matches();
    }
}