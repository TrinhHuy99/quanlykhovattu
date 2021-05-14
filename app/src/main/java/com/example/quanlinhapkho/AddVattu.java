package com.example.quanlinhapkho;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddVattu extends AppCompatActivity {
    final String DATABASE_NAME = "QUANLYKHOVATTU.db";
    final int REQUEST_TAKE_PHOTO=123;
    final int REQUEST_CHOOSE_PHOTO=321;
    Button btn_Luu, btn_Thoat,chonhinh,chuphinh;
    EditText edt_Mavt,edt_Ten, edt_Xuatxu,edt_dvt;
    ImageView hinhvattu;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu1:
                Intent intent = new Intent(AddVattu.this, MainActivity.class);
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
        actionBar.setTitle("THÊM VẬT TƯ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vattu);
        btn_Luu = findViewById(R.id.luu);
        btn_Thoat = findViewById(R.id.thoat);
        edt_Mavt=findViewById(R.id.mvt);
        edt_Ten = findViewById(R.id.tvt);
        edt_Xuatxu = findViewById(R.id.xx);
        edt_dvt = findViewById(R.id.dvt);
        chonhinh = findViewById(R.id.btn_chonhinhvt);
        chuphinh = findViewById(R.id.btn_chuphinhvt);
        hinhvattu=findViewById(R.id.hinh_addvt);
        addEvent();
        actionBar();
        btn_Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insert();
            }
        });
        btn_Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cancel();
            }
        });
    }
    private void Insert() {
        String mavt=edt_Mavt.getText().toString();
        String ten = edt_Ten.getText().toString();
        String xuatxu = edt_Xuatxu.getText().toString();
        String dvt = edt_dvt.getText().toString();
        byte [] hinh=getByteArrayFromImageView(hinhvattu);
        ContentValues contentValues = new ContentValues();
        contentValues.put("MAVT",mavt);
        contentValues.put("TENVT", ten);
        contentValues.put("XUATXU", xuatxu);
        contentValues.put("HINH", hinh);
        contentValues.put("DVT",dvt);
        SQLiteDatabase database = Database.initDatabase(this, "QUANLYKHOVATTU.db");
        database.insert("VATTU",null, contentValues );
        Intent intent = new Intent(this, MainActivity_vattu.class);
        startActivity(intent);
    }
    private void Cancel(){
        Intent intent = new Intent(this, MainActivity_vattu.class);
        startActivity(intent);
    }
    private byte[] getByteArrayFromImageView(ImageView imgv){

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    private void addEvent(){
        chonhinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });
        chuphinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
    }
    private void takePicture(){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_TAKE_PHOTO);
    }
    private void choosePicture(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CHOOSE_PHOTO) {

                try {
                    Uri imageUri = data.getData();
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    hinhvattu.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_TAKE_PHOTO) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                hinhvattu.setImageBitmap(bitmap);
            }
        }
    }
}