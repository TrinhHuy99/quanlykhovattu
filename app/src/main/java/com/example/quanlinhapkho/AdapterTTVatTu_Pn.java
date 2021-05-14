package com.example.quanlinhapkho;

import android.content.Context;
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

public class AdapterTTVatTu_Pn extends BaseAdapter {
    Context context;
    ArrayList<TTVatTu> list;

    public AdapterTTVatTu_Pn(Context context, ArrayList<TTVatTu> list) {
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
        View row = inflater.inflate(R.layout.activyty_themphieunhap, null);
        TextView tv_ttTenVT = row.findViewById(R.id.tv_tenvt_pn);
        TextView tv_dvt = row.findViewById(R.id.tv_dvtvt_pn);
        TextView tv_soLuong = row.findViewById(R.id.tv_slvt_pn);
        Button btn_sua = row.findViewById(R.id.btn_suavt);
        Button btn_xoa = row.findViewById(R.id.btn_xoavt);
        ImageView hinh = row.findViewById(R.id.img_vattu_pn);

        TTVatTu ttVatTu = list.get(position);
        tv_ttTenVT.setText("Tên VT: " + ttVatTu.TenVT);
        tv_dvt.setText("ĐVT: " + ttVatTu.dVT);
        tv_soLuong.setText("Số lượng: " + ttVatTu.soLuong);
        Bitmap hinhttvt = BitmapFactory.decodeByteArray(ttVatTu.hinh, 0 , ttVatTu.hinh.length);
        hinh.setImageBitmap(hinhttvt);
        return row;
    }

}
