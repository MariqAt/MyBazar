package com.ittalents.mynotice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ittalents.mynotice.model.Notice;
import com.ittalents.mynotice.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button logSellerButton;
    private Button logClientButton;
    private Button registrationButton;
    public static ArrayList<User> registeredSellers = new ArrayList<User>();
    public static ArrayList<User> registeredClients = new ArrayList<User>();
    public static User loggedUser = null;
    public static boolean isSeller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user1 = new User("pesho", "pesho", "pesho", true);
        user1.addNotice(new Notice("Обувки", "Нова, Размер: 38", "0888888888", "100"));
        user1.addNotice(new Notice("Чанта", "Нова, GUESS", "0888888888", "400"));
        user1.addNotice(new Notice("Рокля", "Нова, Размер: универсал", "0888888888", "150"));
        this.registeredSellers.add(user1);

        User user2 = new User("minka", "minka", "minka", true);
        user2.addNotice(new Notice("Чанта", "addidas", "0888998811", "150"));
        user2.addNotice(new Notice("Пола", "Нова", "0888998811", "50"));
        user2.addNotice(new Notice("Панталон", "Нова, Размер: С", "0888998811", "80"));
        this.registeredSellers.add(user2);

        User user3 = new User("stamat", "stamat", "stamat", false);
        this.registeredClients.add(user3);

        logSellerButton = (Button) this.findViewById(R.id.button_seller);
        logClientButton = (Button) this.findViewById(R.id.button_client);
        registrationButton = (Button) this.findViewById(R.id.button_registration);


        logSellerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSeller = true;
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        logClientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSeller = false;
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
