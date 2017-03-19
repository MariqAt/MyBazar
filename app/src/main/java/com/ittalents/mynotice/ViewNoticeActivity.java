package com.ittalents.mynotice;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.renderscript.Byte2;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ittalents.mynotice.model.Notice;
import com.ittalents.mynotice.model.User;

public class ViewNoticeActivity extends AppCompatActivity {

    private TextView offersTitle;
    private TextView title;
    private TextView description;
    private TextView price;
    private TextView gsm;
    private Notice notice = null;
    private Button callButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notice);

        callButton = (Button) this.findViewById(R.id.call_button);
        offersTitle = (TextView) this.findViewById(R.id.offer_title);

        String name = getIntent().getExtras().getString("name");
        offersTitle.setText("All offers of " + name);

        User userOffer = null;
        for (User u : MainActivity.registeredSellers) {
            if (u.getUsername().equals(name)) {
                userOffer = u;
                break;
            }
        }

        for (Notice n : userOffer.getNotices()) {

            title = (TextView) findViewById(R.id.title_view);
            title.setText(n.getTitle());

            description = (TextView) findViewById(R.id.description_view);
            description.setText(n.getDescription());

            price = (TextView) findViewById(R.id.price_view);
            price.setText(n.getPrice());

            gsm = (TextView) findViewById(R.id.phone_view);
            gsm.setText(n.getPhone());

            notice = n;
            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + notice.getPhone()));
                    if (ActivityCompat.checkSelfPermission(ViewNoticeActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(intent);
                }
            });
        }
    }
}
