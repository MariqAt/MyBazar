package com.ittalents.mynotice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ittalents.mynotice.model.Notice;

public class NoticeActivity extends AppCompatActivity {

    public static final int RESULT_CODE_SAVE = 2;
    public static final int RESULT_CODE_CANCEL = 3;
    private TextView titleNotice;
    private EditText description;
    private EditText phone;
    private EditText price;
    private EditText title;
    private Button saveButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        titleNotice = (TextView) findViewById(R.id.title_notice);

        title = (EditText) findViewById(R.id.title_edit);
        description = (EditText) findViewById(R.id.description_edit);
        phone = (EditText) findViewById(R.id.phone_edit);
        price = (EditText) findViewById(R.id.price_edit);

        saveButton = (Button) this.findViewById(R.id.save_button);
        cancelButton = (Button) this.findViewById(R.id.cancle_button);

        Notice notice = (Notice) getIntent().getExtras().getSerializable("notice");

        titleNotice.setText("Edit " + notice.getTitle().toString());
        title.setText(notice.getTitle().toString());
        description.setText(notice.getDescription().toString());
        phone.setText(notice.getPhone().toString());
        price.setText(notice.getPrice().toString());


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notice noticeNew = new Notice(title.getText().toString(), description.getText().toString(),
                        phone.getText().toString(), price.getText().toString());

                Intent intent = new Intent();
                intent.putExtra("noticeNew", noticeNew);
                setResult(RESULT_CODE_SAVE, intent);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CODE_CANCEL);
                finish();
            }
        });

    }
}

