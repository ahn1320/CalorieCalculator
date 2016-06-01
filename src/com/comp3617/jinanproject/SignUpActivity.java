package com.comp3617.jinanproject;

import java.sql.SQLException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends Activity {
    EditText editTextUserName, editTextPassword, editTextConfirmPassword;
    Button btnCreateAccount, btnCalcel;

    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        // get Instance of Database Adapter
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        try {
            loginDataBaseAdapter = loginDataBaseAdapter.open();
        } catch (SQLException exception) {
            Toast.makeText(SignUpActivity.this, "Failed to open database!",
                    Toast.LENGTH_LONG).show();
        }

        // Get References of Views
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);

        btnCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText()
                        .toString();

                // Check if any of the fields are vacant
                if (userName.equals("") || password.equals("")
                        || confirmPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field Vaccant",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                // Check if both password matches
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(),
                            "Password Does Not Matches", Toast.LENGTH_LONG)
                            .show();
                    return;
                } else {
                    // Save the Data in Database
                    loginDataBaseAdapter.insertEntry(userName, password);
                    Toast.makeText(getApplicationContext(),
                            "Account Successfully Created ", Toast.LENGTH_LONG)
                            .show();
                    Intent intentHomePage = new Intent(getApplicationContext(),
                            Project.class);
                    startActivity(intentHomePage);

                }
            }
        });

        btnCalcel = (Button) findViewById(R.id.cancel);
        btnCalcel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Create Intent for SignUpActivity
                Intent intentSignUP = new Intent(getApplicationContext(),
                        Project.class);
                startActivity(intentSignUP);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
}
