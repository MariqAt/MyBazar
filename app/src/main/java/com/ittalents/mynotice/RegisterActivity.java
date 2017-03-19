package com.ittalents.mynotice;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ittalents.mynotice.model.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText pass1;
    private EditText pass2;
    private EditText email;
    private RadioButton seller;
    private RadioButton client;
    private Button register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) this.findViewById(R.id.username_edit);
        pass1 = (EditText) this.findViewById(R.id.password_edit);
        pass2 = (EditText) this.findViewById(R.id.password2_edit);
        email = (EditText) this.findViewById(R.id.email_edit);
        seller = (RadioButton) this.findViewById(R.id.check_seller);
        client = (RadioButton) this.findViewById(R.id.check_client);
        register_button = (Button) this.findViewById(R.id.reggister_button);


        RadioGroup rg = (RadioGroup) findViewById(R.id.radio_group);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checked = group.getCheckedRadioButtonId();
                switch(checked){
                    case R.id.check_seller:
                        MainActivity.isSeller = true;
                        break;
                    case R.id.check_client:
                        MainActivity.isSeller = false;
                        break;

                }
            }
        });


        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (isValid()) {
                   if (MainActivity.isSeller) {
                       User u = new User(username.getText().toString(), pass1.getText().toString(),
                               email.getText().toString(), true);
                       MainActivity.registeredSellers.add(u);

                       Intent intent = new Intent(RegisterActivity.this, SellerActivity.class);
                       intent.putExtra("user", u);
                       RegisterActivity.this.startActivity(intent);
                   } else
                   if (!MainActivity.isSeller) {
                       User u = new User(username.getText().toString(), pass1.getText().toString(),
                               email.getText().toString(), false);
                       MainActivity.registeredClients.add(u);

                       Intent intent = new Intent(RegisterActivity.this, ClientActivity.class);
                       intent.putExtra("user", u);
                       RegisterActivity.this.startActivity(intent);
                   } else {
                       Toast.makeText(RegisterActivity.this, "You must to check one of two opsions!", Toast.LENGTH_SHORT).show();
                   }
                } else {
                   Toast.makeText(RegisterActivity.this, "Date are not valid!", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }


    private boolean isValid() {
        boolean isValid = true;
        String name = username.getText().toString();
        if (name.isEmpty()) {
            isValid = false;
            username.setError("Username must be not empty!");
        }
        String password1 = pass1.getText().toString();
        if (password1.isEmpty()) {
            isValid = false;
            pass1.setError("Password must be not empty!");
        }
        String password2 = pass2.getText().toString();
        if (password2.isEmpty()) {
            isValid = false;
            pass2.setError("Confurm passrowd must be not empty!");
        }
        if (!password1.equals(password2)) {
            isValid = false;
            pass2.setError("Password and confurm password aren`t equals!");
        }
        return isValid;
    }
}
