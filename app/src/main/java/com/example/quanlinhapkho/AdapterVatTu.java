package com.example.quanlinhapkho;

import android.app.Activity;
import android.app.AlertDialog;
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

public class AdapterVatTu extends BaseAdapter {
    Activity context;
    ArrayList<Vattu> list;

    public AdapterVatTu(Activity context, ArrayList<Vattu> list) {
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
        View row = inflater.inflate(R.layout.danhsachvattu, null);
        TextView tv_maVT = row.findViewById(R.id.tv_maVT);
        TextView tv_tenVT = row.findViewById(R.id.tv_tenVT);
        TextView tv_xx = row.findViewById(R.id.tv_dvt);
        Button btn_sua = row.findViewById(R.id.btn_sua);
        Button btn_xoa = row.findViewById(R.id.btn_xoa);
        ImageView hinh = row.findViewById(R.id.img_vattu);

        Vattu vattu = list.get(position);

        tv_maVT.setText("Mã VT: " + vattu.MaVT);
        tv_tenVT.setText("Tên VT: " + vattu.TenVT);
        tv_xx.setText("Xuất xứ: " + vattu.XuatXu);
        Bitmap hinhVattu = BitmapFactory.decodeByteArray(vattu.hinh, 0 , vattu.hinh.length);
        hinh.setImageBitmap(hinhVattu);

        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateVattu.class);
                intent.putExtra("MAVT",vattu.MaVT);
                context.startActivity(intent);
            }
        });

        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa vật tư này không? ");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(vattu.MaVT);
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
    private void delete(String maVT){
        SQLiteDatabase database=Database.initDatabase((Activity) context,"QUANLYKHOVATTU.db");
        database.delete("VATTU","MAVT=?",new String[]{maVT});
        list.clear();
        Cursor cursor=database.rawQuery("Select * from VATTU",null);
        while(cursor.moveToNext()){
            String mavt=cursor.getString(0);
            String tenvt=cursor.getString(1);
            String xuatxu=cursor.getString(2);
            byte[] hinh = cursor.getBlob(3);
            list.add(new Vattu(mavt,tenvt,xuatxu,hinh));
        }
        notifyDataSetChanged();
    }

}
