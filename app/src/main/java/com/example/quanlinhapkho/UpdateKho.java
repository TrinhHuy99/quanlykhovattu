package com.example.quanlinhapkho;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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
import android.content.Intent;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UpdateKho extends AppCompatActivity {
    final String DATABASE_NAME = "QUANLYKHOVATTU.db";
    final int REQUEST_TAKE_PHOTO=123;
    final int REQUEST_CHOOSE_PHOTO=321;
    Button btn_Luu, btn_Thoat,btn_chuphinh,btn_chonhinh;
    EditText edt_Tenkho;
    ImageView hinhKho;

    String maKho=null;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu1:
                Intent intent = new Intent(UpdateKho.this, MainActivity.class);
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
        actionBar.setTitle("SỬA THÔNG TIN KHO");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatekho);
        btn_Luu = findViewById(R.id.buttonLuu);
        btn_Thoat = findViewById(R.id.buttonThoat);
        btn_chuphinh=findViewById(R.id.btn_chuphinh);
        btn_chonhinh=findViewById(R.id.btn_chonhinh);
        edt_Tenkho = findViewById(R.id.edtTenkho);
        hinhKho=findViewById(R.id.hinh_kho);
        initUI();
        actionBar();
        addEvent();
        btn_Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update();
            }
        });
        btn_Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cancel();
            }
        });
    }


    private void initUI() {
        Intent intent = getIntent();
        maKho = intent.getStringExtra("MAKHO");
        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("Select * from KHO WHERE MAKHO=?", new String[]{maKho});
        cursor.moveToFirst();
        String ten = cursor.getString(1);
        byte[] hinh = cursor.getBlob(2);

        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
        hinhKho.setImageBitmap(bitmap);
        edt_Tenkho.setText(ten);
    }

    private void Update() {
        String ten = edt_Tenkho.getText().toString();
        byte [] hinh=getByteArrayFromImageView(hinhKho);
        ContentValues contentValues = new ContentValues();
        contentValues.put("TENKHO", ten);
        contentValues.put("HINH", hinh);
        SQLiteDatabase database = Database.initDatabase(this, "QUANLYKHOVATTU.db");
        database.update("KHO", contentValues, "MAKHO=?", new String[]{maKho});
        Intent intent = new Intent(this, QuanLyKho.class);
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
    private void Cancel(){
        Intent intent = new Intent(this, QuanLyKho.class);
        startActivity(intent);
    }
    private void addEvent(){
        btn_chonhinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });
        btn_chuphinh.setOnClickListener(new View.OnClickListener() {
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
                    hinhKho.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_TAKE_PHOTO) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                hinhKho.setImageBitmap(bitmap);
            }
        }
    }
}