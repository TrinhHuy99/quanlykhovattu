package com.example.quanlinhapkho;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class QLTTKho extends AppCompatActivity {

    final String DATABASE_NAME = "QUANLYKHOVATTU.db";
    SQLiteDatabase database;
    String maKho = null;
    ListView lv_TTKho;
    ArrayList<TTVatTu> list_ttKho;
    AdapterTTVatTu adapterTTVatTu;
    TextView tv_tenkho, tv_makho;
    ImageButton chon;

    //    Button Them, Trangchu;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu1:
                Intent intent = new Intent(QLTTKho.this, MainActivity.class);
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

    private void actionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("DS VẬT TƯ TRONG KHO");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_xuat_nhap, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nhap:
                break;
            case R.id.xuat:
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tt_kho);
        actionBar();
        addControls1();
        readData1();
    }

    private void addControls1() {
        lv_TTKho = findViewById((R.id.lv_TTKho));
        list_ttKho = new ArrayList<>();
        adapterTTVatTu = new AdapterTTVatTu(this, list_ttKho);
        lv_TTKho.setAdapter(adapterTTVatTu);
        tv_makho = findViewById(R.id.tv_ttMaKho);
        tv_tenkho = findViewById(R.id.tv_ttTenKho);
        chon = findViewById(R.id.lua_chon);
        this.registerForContextMenu(this.chon);
    }

    private void readData1() {
        Intent intent = getIntent();
        maKho = intent.getStringExtra("MAKHO");
        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);

        String maVT = null, tenVT, dvt = null;
        int soLuong = 0;
        byte[] hinh;

        Cursor cursor = database.rawQuery("Select * from KHO WHERE MAKHO=?", new String[]{maKho});
        cursor.moveToFirst();
        list_ttKho.clear();
        String maKho = cursor.getString(0);
        String tenKho = cursor.getString(1);
        tv_makho.setText("Mã kho: " + maKho);
        tv_tenkho.setText("Tên kho: " + tenKho);

        Cursor cursorVT = database.rawQuery("Select * from VATTU", null);
        Cursor cursorChose = database.rawQuery("Select * from CHITIETKHO WHERE CHITIETKHO.MAKHO=? ORDER BY SOLUONG DESC", new String[]{maKho});
        for (int j = 0; j < cursorChose.getCount(); j++) {
            cursorChose.moveToPosition(j);
            dvt = cursorChose.getString(3);
            soLuong = cursorChose.getInt(2);
            maVT = cursorChose.getString(1);
            for (int i = 0; i < cursorVT.getCount(); i++) {
                cursorVT.moveToPosition(i);
                if (cursorVT.getString(0).equals(maVT)) {
                    tenVT = cursorVT.getString(1);
                    hinh = cursorVT.getBlob(3);
                    if (soLuong > 0) {
                        list_ttKho.add(new TTVatTu(maVT, tenVT, dvt, soLuong, hinh));
                        break;
                    }
                }
            }
        }

        adapterTTVatTu.notifyDataSetChanged();
    }

}
