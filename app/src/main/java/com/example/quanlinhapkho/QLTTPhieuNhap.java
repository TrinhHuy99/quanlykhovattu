package com.example.quanlinhapkho;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class QLTTPhieuNhap extends AppCompatActivity {
    final String DATABASE_NAME = "QUANLYKHOVATTU.db";
    SQLiteDatabase database;
    ListView lv_row_ttPhieu;
    TextView tv_ngayGio, tv_tenKho, tv_soPhieu, tv_ds;
    ArrayList<TTPhieu> list_ttPhieu;
    AdapterTTPhieu adapterTTPhieu;
    //    Button Them, Trangchu;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu1:
                Intent intent = new Intent(QLTTPhieuNhap.this, MainActivity.class);
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
        actionBar.setTitle("THÔNG TIN PHIẾU NHẬP");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ttphieu);
        actionBar();
        addControls1();
        readData1();
    }

    private void addControls1() {
        lv_row_ttPhieu = findViewById((R.id.lv_ttPhieu));
        list_ttPhieu = new ArrayList<>();
        tv_soPhieu = findViewById(R.id.tv_ttPhieu_soPhieu);
        tv_tenKho = findViewById(R.id.tv_ttPhieu_tenKho);
        tv_ngayGio = findViewById(R.id.tv_ttPhieu_ngay);
        tv_ds = findViewById(R.id.tv_ttPhieu_ds);
        adapterTTPhieu = new AdapterTTPhieu(this, list_ttPhieu);
        lv_row_ttPhieu.setAdapter(adapterTTPhieu);
    }

    private void readData1() {
        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
        String maVT, tenVT;
        int soLuong;
        byte[] hinh;

        Intent intent = getIntent();
        String soPhieu = intent.getStringExtra("SOPHIEU");
        String ngayGio = intent.getStringExtra("NGAYGIO");
        String maKho = intent.getStringExtra("MAKHO");
        String tenKho = intent.getStringExtra("TENKHO");

        tv_ngayGio.setText(ngayGio);
        tv_tenKho.setText(tenKho);
        tv_soPhieu.setText(soPhieu);
        tv_ds.setText("Danh sách vật tư nhập kho");
        System.out.println(soPhieu + " " + maKho);
        Cursor cursor = database.rawQuery("SELECT VATTU.MAVT , VATTU.TENVT, VATTU.HINH, CHITIETPHIEUNHAP.SOLUONG " +
                "FROM PHIEUNHAP " +
                "INNER JOIN CHITIETPHIEUNHAP ON PHIEUNHAP.SOPHIEU = CHITIETPHIEUNHAP.SOPHIEU " +
                "INNER JOIN VATTU ON CHITIETPHIEUNHAP.MAVT = VATTU.MAVT " +
                "WHERE CHITIETPHIEUNHAP.SOPHIEU = ? AND PHIEUNHAP.MAKHO = ? " +
                "ORDER BY CHITIETPHIEUNHAP.SOLUONG DESC",new String[]{soPhieu,maKho});

        list_ttPhieu.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            maVT = cursor.getString(0);
            tenVT = cursor.getString(1);
            hinh = cursor.getBlob(2);
            soLuong = cursor.getInt(3);
            list_ttPhieu.add(new TTPhieu(maVT, tenVT, soLuong, hinh));
        }
        adapterTTPhieu.notifyDataSetChanged();
    }
}
