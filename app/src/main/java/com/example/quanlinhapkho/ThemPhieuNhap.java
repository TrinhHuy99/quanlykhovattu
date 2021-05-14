package com.example.quanlinhapkho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ThemPhieuNhap extends AppCompatActivity {
Spinner spinner;
EditText soluong;
TextView donvitinh;
Button huy,them;
String maVT;
String tenVT;
byte[] hinh;
ArrayList<Vattu1> arrvattu = new ArrayList<>();
final String DATABASE_NAME = "QUANLYKHOVATTU.db";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_themvattu);
        setControl();
        setEvent();
    }
    private void setControl(){
        spinner=findViewById(R.id.spinner);
        soluong=findViewById(R.id.sl);
        donvitinh=findViewById(R.id.dvt);
        huy=findViewById(R.id.huy_vt_kho);
        them=findViewById(R.id.themvt_kho);
    }
    private void setEvent(){
        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("Select VATTU.MAVT, VATTU.HINH,VATTU.TENVT,VATTU.DVT from VATTU", null);
        arrvattu.clear();
        while (cursor.moveToNext()) {
            String mavt = cursor.getString(0);
            String tenvattu = cursor.getString(2);
            String dvt = cursor.getString(3);
            byte[] hinh = cursor.getBlob(1);
            arrvattu.add(new Vattu1(mavt, tenvattu,dvt, hinh));
        }
        AdapterMa_Hinhvt adapterMa_hinhvt = new AdapterMa_Hinhvt(this,R.layout.spinner_mavattu, arrvattu);
        spinner.setAdapter(adapterMa_hinhvt);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Vattu1 vattu1= arrvattu.get(position);
              donvitinh.setText(vattu1.donvitinh);
//                maVT = vattu1.maVt;
//                tenVT = vattu1.tenVt;
//                hinh = vattu1.hinh;
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
//        them.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ThemPhieuNhap.this, Nhapvattuvaokho.class);
//                intent.putExtra("MAVT", maVT );
//                intent.putExtra("TENVT", tenVT );
//                intent.putExtra("DVT", donvitinh.getText().toString() );
//                intent.putExtra("SOLUONG", Integer.parseInt(soluong.getText().toString()));
//                intent.putExtra("HINH", hinh);
//                startActivity(intent);
//            }
//        });

    }

}