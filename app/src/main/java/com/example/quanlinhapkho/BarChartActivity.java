package com.example.quanlinhapkho;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.Calendar;

import static com.github.mikephil.charting.utils.ColorTemplate.MATERIAL_COLORS;

public class BarChartActivity extends AppCompatActivity {
    final String DATABASE_NAME =  "QUANLYKHOVATTU.db";
    SQLiteDatabase database;
    EditText edtDate;
    BarChart barChart;
    ArrayList<ThongKeSanPham> listThongKe;
    ArrayList<BarEntry> list;
    Button btnThongKe;
    int year;
    String month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        addControls();
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgay();
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readData();
            }
        });
    }

    private void  addControls(){
        edtDate = findViewById(R.id.editTextDate);
        barChart = findViewById(R.id.barChart);
        listThongKe = new ArrayList<>();
        list = new ArrayList<>();
        btnThongKe = findViewById(R.id.buttonThongKe);
    }

    private void readData() {
        database = Database.initDatabase(this,DATABASE_NAME);
        String sql = "SELECT TENVT,TONGSOLUONG FROM VATTU,"
                + " (SELECT CHITIETPHIEUXUAT.MAVT,SUM(SOLUONG) AS TONGSOLUONG FROM CHITIETPHIEUXUAT,(SELECT * FROM PHIEUXUAT WHERE strftime('%Y',NGAYLAP)IN('"
                + year
                + "') AND strftime('%m',NGAYLAP) IN('"
                + month
                + "')) AS PHIEUXUAT"
                + " WHERE CHITIETPHIEUXUAT.SOPHIEU = PHIEUXUAT.SOPHIEU"
                + " GROUP BY CHITIETPHIEUXUAT.MAVT) AS A"
                + " WHERE A.MAVT = VATTU.MAVT";
        Cursor cursor = database.rawQuery(sql,null);
        listThongKe.clear();
        list.clear();
        for(int i = 0;i<cursor.getCount();i++) {
            cursor.moveToPosition(i);
            listThongKe.add(new ThongKeSanPham(cursor.getString(0),cursor.getInt(1)));
        }
        final ArrayList<String> xAxisLabel = new ArrayList<>();
        for(int i = 0;i< listThongKe.size();i++){
            xAxisLabel.add(listThongKe.get(i).tenSanPham);
        }
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return xAxisLabel.get((int) value);
            }
        };

        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);


        for(int i = 0;i< listThongKe.size();i++){
            list.add(new BarEntry(i,listThongKe.get(i).soLuong));
        }
        BarDataSet barDataSet = new BarDataSet(list,"SẢN PHẨM BÁN CHẠY");
        barDataSet.setColors(MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("");
        barChart.animateY(2000);
    }

    private void ChonNgay() {
        Calendar calendar = Calendar.getInstance();
        int dayNow = calendar.get(Calendar.DATE);
        int monthNow = calendar.get(Calendar.MONTH);
        int yearNow = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                edtDate.setText("Tháng "+ (i1 + 1) + " Năm "+ i);
                year = i;
                if(i1<10){
                    month = "0"+(i1 + 1);
                } else {
                    month = Integer.toString(i1);
                }
            }
        },yearNow,monthNow,dayNow);
        datePickerDialog.show();
    }

}