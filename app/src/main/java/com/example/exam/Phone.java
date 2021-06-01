package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Phone extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        button_main = (Button) findViewById(R.id.button_main);
        button_main.setOnClickListener(this);

        button_contacts = (Button) findViewById(R.id.button_contacts);
        button_contacts.setOnClickListener(this);

        button_1 = (Button) findViewById(R.id.button_1);
        button_2 = (Button) findViewById(R.id.button_2);
        button_3 = (Button) findViewById(R.id.button_3);
        button_4 = (Button) findViewById(R.id.button_4);
        button_5 = (Button) findViewById(R.id.button_5);
        button_6 = (Button) findViewById(R.id.button_6);
        button_7 = (Button) findViewById(R.id.button_7);
        button_8 = (Button) findViewById(R.id.button_8);
        button_9 = (Button) findViewById(R.id.button_9);
        button_0 = (Button) findViewById(R.id.button_0);
        button_clear = (Button) findViewById(R.id.button_clear);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_0.setOnClickListener(this);
        button_clear.setOnClickListener(this);

        phone_text = (TextView) findViewById(R.id.phone_text);
        phone_text.setText("");
        phone = "";
    }

    Button button_main;
    Button button_contacts;
    Button button_1;
    Button button_2;
    Button button_3;
    Button button_4;
    Button button_5;
    Button button_6;
    Button button_7;
    Button button_8;
    Button button_9;
    Button button_0;
    TextView phone_text;
    String phone;
    Button button_clear;

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_main:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.button_contacts:
                intent = new Intent(this, Contacts.class);
                startActivity(intent);
                break;
            case R.id.button_1:
                phone = phone + "1";
                phone_text.setText(phone);
                break;
            case R.id.button_2:
                phone = phone + "2";
                phone_text.setText(phone);
                break;
            case R.id.button_3:
                phone = phone + "3";
                phone_text.setText(phone);
                break;
            case R.id.button_4:
                phone = phone + "4";
                phone_text.setText(phone);
                break;
            case R.id.button_5:
                phone = phone + "5";
                phone_text.setText(phone);
                break;
            case R.id.button_6:
                phone = phone + "6";
                phone_text.setText(phone);
                break;
            case R.id.button_7:
                phone = phone + "7";
                phone_text.setText(phone);
                break;
            case R.id.button_8:
                phone = phone + "8";
                phone_text.setText(phone);
                break;
            case R.id.button_9:
                phone = phone + "9";
                phone_text.setText(phone);
                break;
            case R.id.button_0:
                phone = phone + "0";
                phone_text.setText(phone);
                break;
            case R.id.button_clear:
                if (phone != null && phone.length() > 0) {
                    phone = phone.substring(0, phone.length() - 1);
                }
                phone_text.setText(phone);
                break;
            default:
                break;
        }
    }
}