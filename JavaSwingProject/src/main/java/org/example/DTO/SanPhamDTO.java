/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.DTO;

/**
 *
 * @author Admin
 */
public class SanPhamDTO {
    private String maSP;
    private String tenSP;
    private String xuatXu;
    private int gia;
    private int soLuong;
    private String maLoaiSP;
    private String maKhoHang;
    private String anhMinhHoa;
    private String trangthai;

    public SanPhamDTO() {
    }

    public SanPhamDTO(String maSP, String tenSP, String xuatXu, int gia, int soLuong, String maLoaiSP, String maKhoHang, String anhMinhHoa, String trangthai) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.xuatXu = xuatXu;
        this.gia = gia;
        this.soLuong = soLuong;
        this.maLoaiSP = maLoaiSP;
        this.maKhoHang = maKhoHang;
        this.anhMinhHoa = anhMinhHoa;
        this.trangthai = trangthai;
    }

    public String getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public int getGia() {
        return gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getMaLoaiSP() {
        return maLoaiSP;
    }

    public String getMaKhoHang() {
        return maKhoHang;
    }

    public String getAnhMinhHoa() {
        return anhMinhHoa;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setMaLoaiSP(String maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    public void setMaKhoHang(String maKhoHang) {
        this.maKhoHang = maKhoHang;
    }

    public void setAnhMinhHoa(String anhMinhHoa) {
        this.anhMinhHoa = anhMinhHoa;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    @Override
    public String toString() {
        return "SanPhamDTO{" + "maSP=" + maSP + ", tenSP=" + tenSP + ", xuatXu=" + xuatXu + ", gia=" + gia + ", soLuong=" + soLuong + ", maLoaiSP=" + maLoaiSP + ", maKhoHang=" + maKhoHang + ", trangthai=" + trangthai + '}';
    }
    
    
}
