package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_phone = (Button) findViewById(R.id.button_phone);
        button_phone.setOnClickListener(this);

        button_contact = (Button) findViewById(R.id.button_contact);
        button_contact.setOnClickListener(this);
    }

    Button button_phone;
    Button button_contact;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Контакты");
        menu.add(0, 2, 1, "Создать контакт");
        menu.add(0, 3, 2, "Набор номера");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        if (item.getItemId() == 1){
            intent = new Intent(this, Contacts.class);
            startActivity(intent);
        }
        if (item.getItemId() == 2){
            intent = new Intent(this, AddContact.class);
            startActivity(intent);
        }
        if (item.getItemId() == 3){
            intent = new Intent(this, Phone.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.button_phone:
                intent = new Intent(this, Phone.class);
                startActivity(intent);
                break;
            case R.id.button_contact:
                intent = new Intent(this, Contacts.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}