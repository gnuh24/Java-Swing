package org.example.DTO;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;


public class XuatHang_DTO {
      private int maPhieu;
      private int maKhoHang;
      private String tenKhoHang;
      private Timestamp ngayXuatKho;
      private Double tongGiaTri;
      
      public int getMaPhieu() {
            return maPhieu;
      }
      public void setMaPhieu(int maPhieu) {
            this.maPhieu = maPhieu;
      }
      public int getMaKhoHang() {
            return maKhoHang;
      }
      public void setMaKhoHang(int maKhoHang) {
            this.maKhoHang = maKhoHang;
      }
      public String getTenKhoHang() {
            return tenKhoHang;
      }
      public void setTenKhoHang(String tenKhoHang) {
            this.tenKhoHang = tenKhoHang;
      }
      public Timestamp getNgayXuatKho() {
            return ngayXuatKho;
      }
      public void setNgayXuatKho(Timestamp ngayXuatKho) {
            this.ngayXuatKho = ngayXuatKho;
      }
      public Double getTongGiaTri() {
            return tongGiaTri;
      }
      public void setTongGiaTri(Double tongGiaTri) {
            this.tongGiaTri = tongGiaTri;
      }
      public XuatHang_DTO() {

      }
      public XuatHang_DTO(int a, int b, String c, Timestamp d, Double e) {
            maPhieu = a;
            maKhoHang = b;
            tenKhoHang = c;
            ngayXuatKho = d;
            tongGiaTri = e;
      }
      public XuatHang_DTO(XuatHang_DTO a) {
            maPhieu = a.getMaPhieu();
            maKhoHang = a.getMaKhoHang();
            tenKhoHang = a.getTenKhoHang();
            ngayXuatKho = a.getNgayXuatKho();
            tongGiaTri = a.getTongGiaTri();
      }
}
