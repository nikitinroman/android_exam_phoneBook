package com.example.exam;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateContact extends AppCompatActivity implements View.OnClickListener {

    long userId = 0;
    String Name_text;
    String Phone_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        etName1 = (EditText) findViewById(R.id.etName1);
        etPhone1 = (EditText) findViewById(R.id.etPhone1);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);

        databaseHelper = new DatabaseHelper(getApplicationContext());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getLong("userId");
            Name_text = extras.getString("Name_text");
            Phone_text = extras.getString("Phone_text");
        }

        etName1.setText(Name_text);
        etPhone1.setText(Phone_text);
    }

    EditText etName1, etPhone1;
    Button btnUpdate;
    Cursor userCursor;
    SQLiteDatabase db;

    DatabaseHelper databaseHelper;

    public void onClick(View v) {

        ContentValues cv = new ContentValues();

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Intent intent;
        switch (v.getId()) {
            case R.id.btnUpdate:
                // записываю в сет значения для замены
                cv.put(DatabaseHelper.COLUMN_NAME, etName1.getText().toString());
                cv.put(DatabaseHelper.COLUMN_PHONE, etPhone1.getText().toString());
                // обновляю бд и закрываю соединение
                db.update(DatabaseHelper.TABLE, cv, DatabaseHelper.COLUMN_ID + "=" + String.valueOf(userId), null);
                db.close();

                intent = new Intent(this, Contacts.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void onDestroy() {
        super.onDestroy();
        // Закрываем подключение и курсор
        db.close();
        userCursor.close();
    }
}