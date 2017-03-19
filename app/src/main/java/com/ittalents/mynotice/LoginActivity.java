package com.ittalents.mynotice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ittalents.mynotice.model.User;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText nameLogin;
    private EditText passLogin;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nameLogin = (EditText) this.findViewById(R.id.edit_username_login);
        passLogin = (EditText) this.findViewById(R.id.edit_pass_login);
        loginButton = (Button) this.findViewById(R.id.login_button);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLoginOk = false;
                if (isValid()) {
                    if (MainActivity.isSeller) {
                        for (User u : MainActivity.registeredSellers) {
                            if (u.getUsername().equals(nameLogin.getText().toString()) &&
                                    u.getPassword().equals(passLogin.getText().toString())) {
                                isLoginOk = true;
                                MainActivity.loggedUser = u;

                                Intent intent = new Intent(LoginActivity.this, SellerActivity.class);
                                intent.putExtra("user", u);
                                startActivity(intent);
                                break;
                            }
                        }
                    } else {
                        for (User u : MainActivity.registeredClients) {
                            if (u.getUsername() != null && u.getPassword() != null) {
                                if (u.getUsername().equals(nameLogin.getText().toString()) &&
                                        u.getPassword().equals(passLogin.getText().toString())) {
                                    isLoginOk = true;
                                    MainActivity.loggedUser = u;

                                    Intent intent = new Intent(LoginActivity.this, ClientActivity.class);
                                    intent.putExtra("user", MainActivity.loggedUser);
                                    LoginActivity.this.startActivity(intent);
                                    break;
                                }
                            }
                        }
                    }
                }
                if (!isLoginOk) {
                    Toast.makeText(LoginActivity.this, "Your username or your password aren't correct! " +
                            "Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValid() {
        boolean isValid = true;
        String name = nameLogin.getText().toString();
        if (name.isEmpty()) {
            isValid = false;
            nameLogin.setError("Username must be not empty!");
        }
        String pass = passLogin.getText().toString();
        if (pass.isEmpty()) {
            isValid = false;
            passLogin.setError("Password myst be not empty!");
        }
        return isValid;
    }
}
