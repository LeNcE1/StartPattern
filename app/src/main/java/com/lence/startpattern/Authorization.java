package com.lence.startpattern;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Authorization extends AppCompatActivity {

    @BindView(R.id.error)
    TextView mError;
    @BindView(R.id.phone)
    EditText mPhone;
    @BindView(R.id.pass)
    EditText mPass;
    @BindView(R.id.entry)
    Button mEntry;
    @BindView(R.id.reg)
    TextView mReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        ButterKnife.bind(this, this);
    }

    @OnClick(R.id.entry)
    public void onMEntryClicked() {
        if ((mPhone.getText().toString().equals("123"))&&(mPass.getText().toString().equals("123"))){
            startActivity(new Intent(this,MainActivity.class));
        }
    }

    @OnClick(R.id.reg)
    public void onMRegClicked() {
    }
}
