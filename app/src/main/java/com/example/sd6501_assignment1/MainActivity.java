package com.example.sd6501_assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button btnLogin, btnGuestLogin;

    int numberLoginAttempts = 3;
    public static int width;
    public static int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get current device screen width and height
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;
        height = metrics.heightPixels;

        username = findViewById(R.id.loginUsername);
        password = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnGuestLogin = findViewById(R.id.btnGuestLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateLoginCredentials(username.getText().toString(),
                                         password.getText().toString());
            }
        });

        btnGuestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                accessMainMenu();
            }
        });
    }

    private void accessMainMenu() {

        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    private void validateLoginCredentials(String username, String password){

        if ((username.equals("admin")) && (password.equals("0000"))) {

            accessMainMenu();

        }else if (numberLoginAttempts > 1){

            Toast.makeText(getApplicationContext(), "Username or password incorrect, you have "
                    + numberLoginAttempts + " attempts remaining. Please try again...",
                    Toast.LENGTH_SHORT).show();

            numberLoginAttempts--;

        }else if (numberLoginAttempts == 1){

            Toast.makeText(getApplicationContext(), "Username or password incorrect, you have "
                    + numberLoginAttempts + " attempt remaining. Please try again...",
                    Toast.LENGTH_LONG).show();

            numberLoginAttempts--;

        }else {

            btnLogin.setEnabled(false);

              Toast.makeText(getApplicationContext(), "Username or password incorrect, please "
                      + "use the guest login.", Toast.LENGTH_SHORT).show();
        }
    }
}
