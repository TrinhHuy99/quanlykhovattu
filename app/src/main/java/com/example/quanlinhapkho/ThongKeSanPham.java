package com.example.quanlinhapkho;

public class ThongKeSanPham {
    public String tenSanPham;
    public int soLuong;

    public ThongKeSanPham(String tenSanPham, int soLuong) {
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
