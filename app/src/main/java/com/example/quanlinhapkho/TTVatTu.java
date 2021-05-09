package com.example.quanlinhapkho;

public class TTVatTu {
    public String MaVT;
    public String TenVT;
    public String dVT;
    public int soLuong;
    public byte[] hinh;

    public TTVatTu() {
    }
    public TTVatTu(String maVT, String tenVT, String dVT, int soLuong, byte[] hinh) {
        MaVT = maVT;
        TenVT = tenVT;
        this.dVT = dVT;
        this.soLuong = soLuong;
        this.hinh = hinh;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public String getMaVT() {
        return MaVT;
    }

    public void setMaVT(String maVT) {
        MaVT = maVT;
    }

    public String getTenVT() {
        return TenVT;
    }

    public void setTenVT(String tenVT) {
        TenVT = tenVT;
    }

    public String getdVT() {
        return dVT;
    }

    public void setdVT(String dVT) {
        this.dVT = dVT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
