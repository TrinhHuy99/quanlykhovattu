package com.example.quanlinhapkho;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class QuanLyKho extends AppCompatActivity {

    final String DATABASE_NAME = "QUANLYKHOVATTU.db";
    SQLiteDatabase database;

    ListView lv_Kho;
    ArrayList<Kho> list_Kho;
    AdapterKho adapterKho;
    ImageButton Them;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu1:
                Intent intent = new Intent(QuanLyKho.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.menu2:
                break;

            case R.id.menu3:
                finishAffinity();
                System.exit(0);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    private void actionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("DANH SÁCH KHO");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listkho);

        addControls1();
        readData1();
        actionBar();

    }

    private void addControls1() {
        lv_Kho = findViewById((R.id.lv_kho));
        list_Kho = new ArrayList<>();
        adapterKho = new AdapterKho(this, list_Kho);
        lv_Kho.setAdapter(adapterKho);
        Them = findViewById(R.id.them);
        Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLyKho.this, AddKho.class);
                startActivity(intent);
            }
        });

    }

    private void readData1() {
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * from KHO", null);
        list_Kho.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String maKho = cursor.getString(0);
            String tenKho = cursor.getString(1);
            byte[] hinhKho = cursor.getBlob(2);
            list_Kho.add(new Kho(maKho, tenKho, hinhKho));
        }
        adapterKho.notifyDataSetChanged();
    }


}
