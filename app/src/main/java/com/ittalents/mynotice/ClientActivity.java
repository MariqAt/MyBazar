package com.ittalents.mynotice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ittalents.mynotice.model.User;

import java.util.ArrayList;
import java.util.Random;

public class ClientActivity extends AppCompatActivity {

    private TextView titleClient;
    private Button buttonSeller1;
    private Button buttonSeller2;
    private Button buttonSeller3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);


        titleClient = (TextView) this.findViewById(R.id.client_welcome);
        buttonSeller1 = (Button) this.findViewById(R.id.seller1_buuton);
        buttonSeller2 = (Button) this.findViewById(R.id.seller2_buuton);
        buttonSeller3 = (Button) this.findViewById(R.id.seller3_buuton);

        User u = (User) getIntent().getExtras().getSerializable("user");
        titleClient.setText("Hello " + u.getUsername() + ", chose a seller");


        switch (MainActivity.registeredSellers.size()){
            case 3:
                buttonSeller3.setText(MainActivity.registeredSellers.get(2).getUsername());
                buttonSeller3.setVisibility(View.VISIBLE);
            case 2:
                buttonSeller2.setText(MainActivity.registeredSellers.get(1).getUsername());
                buttonSeller2.setVisibility(View.VISIBLE);
            case 1:
                buttonSeller1.setText(MainActivity.registeredSellers.get(0).getUsername());
                buttonSeller1.setVisibility(View.VISIBLE);
                break;
            default:
                if(MainActivity.registeredSellers.isEmpty()){
                    return;
                }
                ArrayList<String> copiedSellers = (ArrayList<String>) MainActivity.registeredSellers.clone();
                int rand1 = new Random().nextInt(MainActivity.registeredSellers.size());
                String seller1 = copiedSellers.remove(rand1);
                buttonSeller1.setText(seller1);
                int rand2 = new Random().nextInt(MainActivity.registeredSellers.size());
                String seller2 = copiedSellers.remove(rand2);
                buttonSeller2.setText(seller2);
                int rand3 = new Random().nextInt(MainActivity.registeredSellers.size());
                String seller3 = copiedSellers.remove(rand3);
                buttonSeller3.setText(seller3);
                buttonSeller1.setVisibility(View.VISIBLE);
                buttonSeller2.setVisibility(View.VISIBLE);
                buttonSeller3.setVisibility(View.VISIBLE);

        }
        buttonSeller1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientActivity.this, ViewNoticeActivity.class);
                intent.putExtra("name", buttonSeller1.getText().toString());
                startActivity(intent);
            }
        });

        buttonSeller2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientActivity.this, ViewNoticeActivity.class);
                intent.putExtra("name", buttonSeller2.getText().toString());
                startActivity(intent);
            }
        });

        buttonSeller3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientActivity.this, ViewNoticeActivity.class);
                intent.putExtra("name", buttonSeller3.getText().toString());
                startActivity(intent);
            }
        });
    }
}
