package com.example.android_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase dbs;
    Button b1,b2,b3,b4;
    EditText e1,e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.name);
        e2=findViewById(R.id.age);
        b1=findViewById(R.id.insert);
        b2=findViewById(R.id.read);
        b3=findViewById(R.id.update);
        b4=findViewById(R.id.delete);
        DB db=new DB(this);
        dbs=db.getWritableDatabase();
        dbs=db.getReadableDatabase();
    }

    public void delete(View view) {
        String age=e2.getText().toString();
        dbs.delete("details","age="+age,null);
    }

    public void update(View view) {
        String name=e1.getText().toString();
        String age=e2.getText().toString();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("age",age);
        dbs.update("details",values,"age="+age,null);
        Toast.makeText(this, "Updated Succesfully", Toast.LENGTH_SHORT).show();
    }

    public void read(View view) {
        StringBuffer buffer = new StringBuffer();
        Cursor c = dbs.rawQuery("select * from details", null);
        while (c.moveToNext()) {
            buffer.append("\n" + c.getString(0));
            buffer.append("\n" + c.getString(1));
        }
        Toast.makeText(this, buffer.toString(), Toast.LENGTH_SHORT).show();

    }

    public void insert(View view) {
        String name=e1.getText().toString();
        String age=e2.getText().toString();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("age",age);
        dbs.insert("details",null,values);

    }
}