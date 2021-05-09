package com.example.quanlinhapkho;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterKho extends BaseAdapter {
    Context context;
    ArrayList<Kho> list;

    public AdapterKho(Context context, ArrayList<Kho> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.activity_rowkho, null);
        TextView tv_maKho = row.findViewById(R.id.tv_maKho);
        TextView tv_tenKho = row.findViewById(R.id.tv_tenKho);
        Button btn_sua = row.findViewById(R.id.btn_sua);
        Button btn_xoa = row.findViewById(R.id.btn_xoa);
        Button btn_ttKho = row.findViewById(R.id.btn_tt);
        ImageView hinh = row.findViewById(R.id.img_kho);

        Kho kho = list.get(position);

        tv_maKho.setText("Mã Kho: " + kho.MaKho);
        tv_tenKho.setText("Tên Kho: " + kho.TenKho);
        Bitmap hinhKho = BitmapFactory.decodeByteArray(kho.Hinh, 0 , kho.Hinh.length);
        hinh.setImageBitmap(hinhKho);
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateKho.class);
                intent.putExtra("MAKHO",kho.MaKho);
                context.startActivity(intent);
            }
        });

        btn_ttKho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, QLTTKho.class);
                intent.putExtra("MAKHO",kho.MaKho);
                context.startActivity(intent);
            }
        });

        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa kho này không? ");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(kho.MaKho);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });

        return row;
    }
    private void delete(String maKho){
        SQLiteDatabase database=Database.initDatabase((Activity) context,"QUANLYKHOVATTU.db");
        database.delete("KHO","MAKHO=?",new String[]{maKho});
        list.clear();
        Cursor cursor=database.rawQuery("Select * from KHO",null);
        while(cursor.moveToNext()){
            String makho=cursor.getString(0);
            String tenkho=cursor.getString(1);
            byte[] hinhKho = cursor.getBlob(2);
            list.add(new Kho(makho,tenkho,hinhKho));

        }
        notifyDataSetChanged();
    }
}
