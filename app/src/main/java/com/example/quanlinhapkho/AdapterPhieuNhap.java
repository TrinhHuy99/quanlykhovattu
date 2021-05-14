package com.example.quanlinhapkho;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterPhieuNhap extends BaseAdapter {
    Context context;
    ArrayList<Phieu> list;

    public AdapterPhieuNhap(Context context, ArrayList<Phieu> list) {
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
        View row = inflater.inflate(R.layout.activity_row_phieu, null);
        TextView tv_soPhieu = row.findViewById(R.id.tv_soPhieu);
        TextView tv_soNgay = row.findViewById(R.id.tv_soNgay);
        TextView tv_soGio = row.findViewById(R.id.tv_soGio);
        TextView tv_tenKho = row.findViewById(R.id.tv_phieu_tenKho);
        LinearLayout lin_rowPhieu = row.findViewById(R.id.row_phieu);

        Phieu phieu = list.get(position);

        tv_soNgay.setText(phieu.ngay);
        tv_soGio.setText(phieu.gio);
        System.out.println(phieu.soPhieu);
        tv_soPhieu.setText("" + phieu.soPhieu);
        tv_tenKho.setText(phieu.tenKho);

        lin_rowPhieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, QLTTPhieuNhap.class);
                intent.putExtra("SOPHIEU", "" + phieu.soPhieu );
                intent.putExtra("MAKHO", phieu.maKho);
                intent.putExtra("TENKHO", phieu.tenKho);
                intent.putExtra("NGAYGIO", phieu.gio + "  " + phieu.ngay);
                context.startActivity(intent);
            }
        });
        return row;
    }

}
