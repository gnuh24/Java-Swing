package org.example.DTO;


public class SanPham_DTO {
      private int maSanPham;
      private String tenSanPham;
      private String xuatXu;
      private int giaSanPham;
      private int soLuongConLai;
      private int trangThai;
      private String anhMinhhoa;
      private int maLoaiSanPham;
      private int maKhoHang;
      
      public int getMaSanPham() {
            return maSanPham;
      }
      public void setMaSanPham(int maSanPham) {
            this.maSanPham = maSanPham;
      }
      public String getXuatXu() {
            return xuatXu;
      }
      public void setXuatXu(String xuatXu) {
            this.xuatXu = xuatXu;
      }
      public String getTenSanPham() {
            return tenSanPham;
      }
      public void setTenSanPham(String tenSanPham) {
            this.tenSanPham = tenSanPham;
      }
      public int getGiaSanPham() {
            return giaSanPham;
      }
      public void setGiaSanPham(int giaSanPham) {
            this.giaSanPham = giaSanPham;
      }
      public int getSoLuongConLai() {
            return soLuongConLai;
      }
      public void setSoLuongConLai(int soLuongConLai) {
            this.soLuongConLai = soLuongConLai;
      }
      public int getTrangThai() {
            return trangThai;
      }
      public void setTrangThai(int trangThai) {
            this.trangThai = trangThai;
      }
      public String getAnhMinhhoa() {
            return anhMinhhoa;
      }
      public void setAnhMinhhoa(String anhMinhhoa) {
            this.anhMinhhoa = anhMinhhoa;
      }
      public int getMaLoaiSanPham() {
            return maLoaiSanPham;
      }
      public void setMaLoaiSanPham(int maLoaiSanPham) {
            this.maLoaiSanPham = maLoaiSanPham;
      }
      public int getMaKhoHang() {
            return maKhoHang;
      }
      public void setMaKhoHang(int maKhoHang) {
            this.maKhoHang = maKhoHang;
      }
      public SanPham_DTO() {

      }
      public SanPham_DTO(int a, String b, String c, int d, int e, int f, int g, int h) {
            maSanPham = a;
            tenSanPham = b;
            xuatXu = c;
            giaSanPham = d;
            soLuongConLai = e;
            trangThai = f;
            maLoaiSanPham = g;
            maKhoHang = h;
      }
}
