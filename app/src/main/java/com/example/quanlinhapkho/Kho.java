package com.example.quanlinhapkho;

public class Kho {
    public String MaKho;
    public String TenKho;
    public byte[] Hinh;

    public Kho(String maKho, String tenKho, byte[] hinh) {
        MaKho = maKho;
        TenKho = tenKho;
        Hinh = hinh;
    }

    public byte[] getHinh() {
        return Hinh;
    }

    public void setHinh(byte[] hinh) {
        Hinh = hinh;
    }

    public String getMaKho() {
        return MaKho;
    }

    public void setMaKho(String maKho) {
        MaKho = maKho;
    }

    public String getTenKho() {
        return TenKho;
    }

    public void setTenKho(String tenKho) {
        TenKho = tenKho;
    }
}
