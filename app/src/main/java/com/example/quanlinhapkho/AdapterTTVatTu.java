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

public class AdapterTTVatTu extends BaseAdapter {
    Context context;
    ArrayList<TTVatTu> list;

    public AdapterTTVatTu(Context context, ArrayList<TTVatTu> list) {
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
        View row = inflater.inflate(R.layout.activity_row_ttvt, null);
        TextView tv_ttTenVT = row.findViewById(R.id.tv_tenVT);
        TextView tv_maVT = row.findViewById(R.id.tv_maVT);
        TextView tv_dvt = row.findViewById(R.id.tv_dvt);
        TextView tv_soLuong = row.findViewById(R.id.tv_ttSLVT);
/*        Button btn_sua = row.findViewById(R.id.btn_sua);
        Button btn_xoa = row.findViewById(R.id.btn_xoa);*/
        ImageView hinh = row.findViewById(R.id.img_ttvt);

        TTVatTu ttVatTu = list.get(position);

        tv_maVT.setText("Mã VT: " + ttVatTu.MaVT);
        tv_ttTenVT.setText("Tên VT: " + ttVatTu.TenVT);
        tv_dvt.setText("ĐVT: " + ttVatTu.dVT);
        tv_soLuong.setText("Số lượng: " + ttVatTu.soLuong);
        Bitmap hinhttvt = BitmapFactory.decodeByteArray(ttVatTu.hinh, 0 , ttVatTu.hinh.length);
        hinh.setImageBitmap(hinhttvt);
        return row;
    }

}
