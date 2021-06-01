package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;
import android.view.ContextMenu;
import android.view.MenuItem;

public class Contacts extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        button_call = (Button) findViewById(R.id.button_call);
        button_call.setOnClickListener(this);

        button_main = (Button) findViewById(R.id.button_main);
        button_main.setOnClickListener(this);

        button_add = (Button) findViewById(R.id.button_add);
        button_add.setOnClickListener(this);

        contacts_list = (ListView) findViewById(R.id.contacts_list);
        contacts_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            userId = id;
        }
        });

        databaseHelper = new DatabaseHelper(getApplicationContext());

        contacts_count = (TextView) findViewById(R.id.contacts_count);

        registerForContextMenu(contacts_list);
    }

    Button button_call;
    Button button_main;
    Button button_add;
    ListView contacts_list;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    TextView contacts_count;

    final int MENU_DELETE = 1;
    final int MENU_EDIT = 2;
    final int MENU_COPY = 3;
    long userId = 0;
    String Name_text;
    String Phone_text;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()) {
            case R.id.contacts_list:
                menu.add(0, MENU_DELETE, 0, "Удалить");
                menu.add(0, MENU_EDIT, 0, "Изменить");
                menu.add(0, MENU_COPY, 0, "Копировать");
                break;
        }
    }

    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_DELETE:
                userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                        DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
                userCursor.moveToFirst();
                db.delete(DatabaseHelper.TABLE, "_id = ?", new String[]{String.valueOf(userId)});
                onResume();
                break;
            case MENU_EDIT:
                userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                        DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
                userCursor.moveToFirst();
                if( userCursor != null && userCursor.moveToFirst() ){
                    Name_text = userCursor.getString(1);
                    Phone_text = userCursor.getString(2);
                    userCursor.close();
                }
                Intent intent = new Intent(getApplicationContext(), UpdateContact.class);
                intent.putExtra("userId", userId);
                intent.putExtra("Name_text", Name_text);
                intent.putExtra("Phone_text", Phone_text);
                startActivity(intent);
                break;
            case MENU_COPY:
                Name_text = userCursor.getString(1);
                Phone_text = userCursor.getString(2);
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("clippedData", Phone_text);
                clipboard.setPrimaryClip(clip);

        }
        return super.onContextItemSelected(item);
    }


    @Override
    public void onResume() {
        super.onResume();
        // получаем инстанс базы
        db = databaseHelper.getReadableDatabase();

        //получаем данные из бд в виде курсора
        userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE, null);

        // определяем, какие столбцы из курсора будут выводиться в ListView
        String[] headers = new String[] {DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_PHONE};

        // создаем адаптер, передаем в него значение курсора
        userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
        contacts_count.setText("Всего контактов: " +  userCursor.getCount());

        // Связываю адаптер с отображаемым списком
        contacts_list.setAdapter(userAdapter);
    }

    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.button_call:
                intent = new Intent(this, Phone.class);
                startActivity(intent);
                break;
            case R.id.button_main:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.button_add:
                intent = new Intent(this, AddContact.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение к бд и курсор
        db.close();
        userCursor.close();
    }
}