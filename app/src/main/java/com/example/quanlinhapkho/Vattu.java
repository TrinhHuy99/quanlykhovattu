package com.example.quanlinhapkho;

public class Vattu {
    public String MaVT;
    public String TenVT;
    public String XuatXu;
    public byte[] hinh;

    public Vattu(String maVT, String tenVT, String xuatXu, byte[] hinh) {
        MaVT = maVT;
        TenVT = tenVT;
        XuatXu = xuatXu;
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

    public String getXuatXu() {
        return XuatXu;
    }

    public void setXuatXu(String xuatXu) {
        XuatXu = xuatXu;
    }
}
