package com.example.quanlinhapkho;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterTTPhieu extends BaseAdapter {
    Activity context;
    ArrayList<TTPhieu> list;

    public AdapterTTPhieu(Activity context, ArrayList<TTPhieu> list) {
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
        View row = inflater.inflate(R.layout.activity_row_ttphieu, null);
        TextView tv_maVT = row.findViewById(R.id.tv_ttPhieu_maVT);
        TextView tv_tenVT = row.findViewById(R.id.tv_ttPhieu_tenVT);
        TextView tv_soLuong = row.findViewById(R.id.tv_ttPhieu_soLuong);
        ImageView hinhVT = row.findViewById(R.id.img_ttPhieu_hinh);

        TTPhieu ttPhieu = list.get(position);

        tv_maVT.setText(ttPhieu.maVT);
        tv_tenVT.setText(ttPhieu.tenVT);
        tv_soLuong.setText("" + ttPhieu.soLuong);
        Bitmap hinhVattu = BitmapFactory.decodeByteArray(ttPhieu.hinh, 0 , ttPhieu.hinh.length);
        hinhVT.setImageBitmap(hinhVattu);

        return row;
    }

}
