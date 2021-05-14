package com.example.quanlinhapkho;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterMa_Hinhvt extends BaseAdapter {
    Context context;
    int myLayout;
    List<Vattu1> arrVattu;


    public AdapterMa_Hinhvt(Context context, int myLayout, List<Vattu1> arrVattu) {
        this.context = context;
        this.myLayout = myLayout;
        this.arrVattu = arrVattu;
    }

    @Override
    public int getCount() {
        return arrVattu.size();
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(myLayout, null);
        TextView mavatt = (TextView) convertView.findViewById(R.id.ma_vattu);
        TextView tenvatt = (TextView) convertView.findViewById(R.id.ten_vattu);
        ImageView imagevt =(ImageView) convertView.findViewById(R.id.imagevt);
        Vattu1 vattu1=arrVattu.get(position);
        Bitmap hinhttvt = BitmapFactory.decodeByteArray(vattu1.hinh, 0 , vattu1.hinh.length);
        imagevt.setImageBitmap(hinhttvt);
        mavatt.setText(vattu1.maVt );
        tenvatt.setText(vattu1.tenVt );
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }

}
