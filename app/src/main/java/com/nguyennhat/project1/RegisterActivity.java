package com.nguyennhat.project1;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.nguyennhat.project1.R;
import com.nguyennhat.project1.database.DatabaseHelper;
import com.nguyennhat.project1.database.MyDatabase;


public class RegisterActivity extends AppCompatActivity {
    private Button mBtnCreate;
    private EditText mEdtUserName;
    private EditText mEdtPass;

    private MyDatabase database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        database = new MyDatabase(this);

        mBtnCreate = (Button) findViewById(R.id.btnCreate);
        mEdtUserName = (EditText) findViewById(R.id.edtUserName);
        mEdtPass = (EditText) findViewById(R.id.edtPass);

        mBtnCreate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEdtPass.getText().toString().isEmpty()
                        || mEdtUserName.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Nhap thong tin de dang ki", Toast.LENGTH_SHORT).show();
                } else {
                    database.open();
                    database.createData(mEdtUserName.getText().toString(), mEdtPass.getText().toString());
                    database.close();
                    finish();
                }
            }
        });

    }
}
