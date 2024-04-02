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
      private int maSanPham;
      private String tenSanPham;
      private String xuatXu;
      private int giaSanPham;
      private int soLuongConLai;
      private int trangThai;
      private String anhMinhhoa;
      private int maLoaiSanPham;
      private int maKhoHang;

    public SanPhamDTO() {
    }

    public SanPhamDTO(int maSanPham, String tenSanPham, String xuatXu, int giaSanPham, int soLuongConLai, int trangThai, String anhMinhhoa, int maLoaiSanPham, int maKhoHang) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.xuatXu = xuatXu;
        this.giaSanPham = giaSanPham;
        this.soLuongConLai = soLuongConLai;
        this.trangThai = trangThai;
        this.anhMinhhoa = anhMinhhoa;
        this.maLoaiSanPham = maLoaiSanPham;
        this.maKhoHang = maKhoHang;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public int getSoLuongConLai() {
        return soLuongConLai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public String getAnhMinhhoa() {
        return anhMinhhoa;
    }

    public int getMaLoaiSanPham() {
        return maLoaiSanPham;
    }

    public int getMaKhoHang() {
        return maKhoHang;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public void setSoLuongConLai(int soLuongConLai) {
        this.soLuongConLai = soLuongConLai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public void setAnhMinhhoa(String anhMinhhoa) {
        this.anhMinhhoa = anhMinhhoa;
    }

    public void setMaLoaiSanPham(int maLoaiSanPham) {
        this.maLoaiSanPham = maLoaiSanPham;
    }

    public void setMaKhoHang(int maKhoHang) {
        this.maKhoHang = maKhoHang;
    }
 
      
      
}