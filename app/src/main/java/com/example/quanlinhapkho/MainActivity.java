package com.example.quanlinhapkho;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

ImageButton kho,vattu,phieunhap,phieuxuat,baocao;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu1:
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
        actionBar.setTitle("QUẢN LÍ KHO VẬT TƯ");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__main);
        setControl();
        setEvent();
        actionBar();
    }


    private void setControl(){
        kho=findViewById(R.id.kho);
        vattu=findViewById(R.id.vattu);
        phieunhap=findViewById(R.id.phieunhap);
        phieuxuat=findViewById(R.id.phieuxuat);
        baocao=findViewById(R.id.baocao);
    }

    private void setEvent(){
        vattu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), MainActivity_vattu.class);
                startActivity(nextScreen);
            }
        });
        kho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), QuanLyKho.class);
                startActivity(nextScreen);
            }
        });
    }

}