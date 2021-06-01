package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AddContact extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);

        btAdd = (Button) findViewById(R.id.btnAdd);
        btAdd.setOnClickListener(this);

        databaseHelper = new DatabaseHelper(getApplicationContext());
    }

    EditText etName, etPhone;
    Button btAdd;

    final String LOG_TAG = "myLogs";

    DatabaseHelper databaseHelper;

    public void onClick(View v) {

        ContentValues cv = new ContentValues();

        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Intent intent;
        switch (v.getId()) {
            case R.id.btnAdd:
                cv.put("name", name);
                cv.put("phone", phone);
                db.insert("contacts", null, cv);
//                long rowID = db.insert("contacts", null, cv);
//                Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                intent = new Intent(this, Contacts.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}