package com.example.quanlinhapkho;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.BaseAdapter;

import java.io.Serializable;
import java.util.ArrayList;

import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class Nhapvattuvaokho extends AppCompatActivity {
    TextView tv_tenKho, tv_maKho;
    String maKho, tenKho;
    String mavt, tenvt, dvt;
    int sl;
    byte[] hinh;
    private Spinner mavattu;
    ArrayList<Vattu1> arrvattu = new ArrayList<>();
    ArrayList<TTVatTu> arrvattu1 = new ArrayList<>();
    final String DATABASE_NAME = "QUANLYKHOVATTU.db";
    AdapterTTVatTu_Pn adapterTTVatTu_pn;
    ListView listView;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu1:
                Intent intent = new Intent(Nhapvattuvaokho.this, MainActivity.class);
                startActivity(intent);

            case R.id.menu2:
                break;

            case R.id.menu3:
                finishAffinity();
                System.exit(0);
                break;
            case R.id.menuthem:
                openThemVattuVaoKho();

            case R.id.menusave:

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nhapvtvaokho, menu);
        return true;
    }

    private void actionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PH/NHẬP KHO");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nhapvattuvaokho);
        actionBar();
        setControl();
        readData();
    }

    private void readData() {
        Intent intent = getIntent();
        maKho = intent.getStringExtra("MAKHO");
        tenKho = intent.getStringExtra("TENKHO");
        tv_maKho.setText("Mã Kho: " + maKho);
        tv_tenKho.setText("Tên Kho: " + tenKho);
        mavt = intent.getStringExtra("MAVT");
        tenvt = intent.getStringExtra("TENVT");
        dvt = intent.getStringExtra("DVT");
        sl = intent.getIntExtra("SOLUONG", 0);
        hinh = intent.getByteArrayExtra("HINH");
        arrvattu1.add(new TTVatTu(mavt, tenvt, dvt, sl, hinh));
//        adapterTTVatTu_pn.notifyDataSetChanged();
    }

    private void setControl() {
        tv_tenKho = findViewById(R.id.tv_nhapXuat_tenKho);
        tv_maKho = findViewById(R.id.tv_nhapXuat_maKho);
        listView = findViewById(R.id.lv_TTKho);
        listView.setAdapter(adapterTTVatTu_pn);
    }


    private void openThemVattuVaoKho() {
        Intent intent = new Intent(Nhapvattuvaokho.this, ThemPhieuNhap.class);
//        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
//        Cursor cursor = database.rawQuery("Select VATTU.MAVT, VATTU.HINH,VATTU.TENVT,VATTU.DVT from VATTU", null);
//        while (cursor.moveToNext()) {
//            String mavt = cursor.getString(0);
//            String tenvattu = cursor.getString(2);
//            String dvt = cursor.getString(3);
//            byte[] hinh = cursor.getBlob(1);
//            arrvattu.add(new Vattu1(mavt, tenvattu,dvt, hinh));
//        }
        startActivity(intent);
    }


}

