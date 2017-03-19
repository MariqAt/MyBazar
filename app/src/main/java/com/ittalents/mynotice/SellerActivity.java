package com.ittalents.mynotice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ittalents.mynotice.model.Notice;
import com.ittalents.mynotice.model.User;

public class SellerActivity extends AppCompatActivity {

    private Button editNoticeButton;

    private TextView title;
    private TextView description;
    private TextView price;
    private TextView gsm;
    private Notice notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller2);

        editNoticeButton = (Button) this.findViewById(R.id.edit_button);

        User u = (User) getIntent().getExtras().getSerializable("user");
        TextView pozdrav = (TextView) findViewById(R.id.text_pozdrav);
        pozdrav.setText("Hello, " + u.getUsername().toString() + " (logged as Seller)");


        for (Notice n : u.getNotices()) {
            title = (TextView) findViewById(R.id.title_edit);
            title.setText(n.getTitle());

            description = (TextView) findViewById(R.id.description_edit);
            description.setText(n.getDescription());

            price = (TextView) findViewById(R.id.price_edit);
            price.setText(n.getPrice());

            gsm = (TextView) findViewById(R.id.phone_edit);
            gsm.setText(n.getPhone());

            notice = n;
            editNoticeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SellerActivity.this, NoticeActivity.class);
                    intent.putExtra("notice", notice);
                    startActivityForResult(intent, 1);
                }
            });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == NoticeActivity.RESULT_CODE_CANCEL) {
            Toast.makeText(this, "The notice isn't changed!", Toast.LENGTH_SHORT).show();
        }
        if(resultCode == NoticeActivity.RESULT_CODE_SAVE) {
            if (data != null) {
                Notice notice = (Notice) data.getSerializableExtra("noticeNew");
                title.setText(notice.getTitle().toString());
                description.setText((notice.getDescription().toString()));
                gsm.setText(notice.getPhone().toString());
                price.setText(notice.getPrice().toString());

            }
        }
    }
}
