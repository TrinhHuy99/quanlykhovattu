package com.example.quanlinhapkho;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity_vattu extends AppCompatActivity {
    final String DATABASE_NAME = "QUANLYKHOVATTU.db";
    SQLiteDatabase database;

    ListView lv_VT;
    ArrayList<Vattu> list_VT;
    AdapterVatTu adapterVatTu;
    ImageButton Them, Trangchu;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu1:
                Intent intent = new Intent(MainActivity_vattu.this, MainActivity.class);
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
        actionBar.setTitle("DANH SÁCH VẬT TƯ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vattu);
        actionBar();
        addControls();
        readData();
    }

    private void addControls() {
        lv_VT = findViewById((R.id.lv_vattu));
        list_VT = new ArrayList<>();
        adapterVatTu = new AdapterVatTu(this, list_VT);
        lv_VT.setAdapter(adapterVatTu);
        Them = findViewById(R.id.them);

        Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_vattu.this, AddVattu.class);
                startActivity(intent);
//                finishAffinity();
//                System.exit(0);
            }
        });
    }

    private void readData() {
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * from VATTU", null);
        list_VT.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String maVT = cursor.getString(0);
            String tenVT = cursor.getString(1);
            String xuatXu = cursor.getString(2);
            byte[] hinh = cursor.getBlob(3);
            list_VT.add(new Vattu(maVT,tenVT,xuatXu,hinh));
        }
        adapterVatTu.notifyDataSetChanged();
    }

    private void setEvent() {
        Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_vattu.this, AddVattu.class);
                startActivity(intent);
            }
        });
    }
}