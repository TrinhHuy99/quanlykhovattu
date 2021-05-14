package com.example.quanlinhapkho;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class QLPhieuXuat extends AppCompatActivity {
    final String DATABASE_NAME = "QUANLYKHOVATTU.db";
    SQLiteDatabase database;
    ListView lv_rowPhieu;
    ArrayList<Phieu> list_phieu;
    AdapterPhieuXuat adapterPhieuXuat;
    Spinner spinner;
    //    Button Them, Trangchu;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu1:
                Intent intent = new Intent(QLPhieuXuat.this, MainActivity.class);
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
        actionBar.setTitle("DANH SÁCH PHIẾU XUẤT");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listphieu);
        actionBar();
        addControls1();
        readData1();
    }

    private void addControls1() {
        lv_rowPhieu = findViewById((R.id.lv_phieu));
        list_phieu = new ArrayList<>();
        adapterPhieuXuat = new AdapterPhieuXuat(this, list_phieu);
        lv_rowPhieu.setAdapter(adapterPhieuXuat);
        spinner = findViewById(R.id.spinner_phieu);
    }

    private void readData1() {
        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursorKho = database.rawQuery("SELECT * FROM KHO",null);
        String spinner_tenKho;
        ArrayList<String> list_tenKho = new ArrayList<>();
        list_tenKho.add("Tất cả");
        while (cursorKho.moveToNext()){
            spinner_tenKho = cursorKho.getString(1);
            list_tenKho.add(spinner_tenKho);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,list_tenKho);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    String tenKho, maKho;
                    int soPhieu;
                    Cursor cursor = database.rawQuery("SELECT PHIEUXUAT.SOPHIEU , KHO.TENKHO, KHO.MAKHO, strftime('%d-%m-%Y %H:%M:%S',PHIEUXUAT.NGAYLAP) AS NGAYLAP " +
                            "FROM PHIEUXUAT " +
                            "INNER JOIN CHITIETPHIEUXUAT ON PHIEUXUAT.SOPHIEU = CHITIETPHIEUXUAT.SOPHIEU " +
                            "INNER JOIN KHO ON PHIEUXUAT.MAKHO = KHO.MAKHO " +
                            "GROUP BY PHIEUXUAT.SOPHIEU " +
                            "ORDER BY PHIEUXUAT.SOPHIEU DESC", null);
                    list_phieu.clear();
                    for (int i = 0; i < cursor.getCount(); i++) {
                        cursor.moveToPosition(i);
                        soPhieu = cursor.getInt(0);
                        tenKho = cursor.getString(1);
                        maKho = cursor.getString(2);
                        String ngay_gio = cursor.getString(3);
                        String ngay = "", gio = "";
                        for(int j = 0; j < ngay_gio.length(); j++){
                            if(j < 11){
                                ngay += ngay_gio.charAt(j);
                            }else{
                                gio += ngay_gio.charAt(j);
                            }
                        }
                        list_phieu.add(new Phieu(soPhieu, tenKho, ngay, gio, maKho));
                    }
                    adapterPhieuXuat.notifyDataSetChanged();
                }else{
                    String tenKho, maKho;
                    int soPhieu;
                    String ten = list_tenKho.get(position);
                    Cursor cursor = database.rawQuery("SELECT PHIEUXUAT.SOPHIEU , KHO.TENKHO, KHO.MAKHO, strftime('%d-%m-%Y %H:%M:%S',PHIEUXUAT.NGAYLAP) AS NGAYLAP " +
                            "FROM PHIEUXUAT " +
                            "INNER JOIN CHITIETPHIEUXUAT ON PHIEUXUAT.SOPHIEU = CHITIETPHIEUXUAT.SOPHIEU " +
                            "INNER JOIN KHO ON PHIEUXUAT.MAKHO = KHO.MAKHO " +
                            "WHERE KHO.TENKHO = ? " +
                            "GROUP BY PHIEUXUAT.SOPHIEU " +
                            "ORDER BY PHIEUXUAT.SOPHIEU DESC", new String[]{ten});
                    list_phieu.clear();
                    for (int i = 0; i < cursor.getCount(); i++) {
                        cursor.moveToPosition(i);
                        soPhieu = cursor.getInt(0);
                        tenKho = cursor.getString(1);
                        maKho = cursor.getString(2);
                        String ngay_gio = cursor.getString(3);
                        String ngay = "", gio = "";
                        for(int j = 0; j < ngay_gio.length(); j++){
                            if(j < 11){
                                ngay += ngay_gio.charAt(j);
                            }else{
                                gio += ngay_gio.charAt(j);
                            }
                        }
                        list_phieu.add(new Phieu(soPhieu, tenKho, ngay, gio, maKho));
                    }
                    adapterPhieuXuat.notifyDataSetChanged();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
