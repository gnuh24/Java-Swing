package org.example.BUS;

import java.util.List;

import org.example.DTO.*;
import org.example.DAO.*;

public class XuatHang_BUS {
      private XuatHang_DAO xuatHangDAO;

      public XuatHang_BUS() {
            this.xuatHangDAO = new XuatHang_DAO();
      }
      //? danh sách sản phẩm ở panel Xuất hàng
      public List<SanPham_DTO> getDanhSachSanPham() {
            return xuatHangDAO.getDanhSachSanPham();
      }
      //? danh sách xuất hàng ở panel Xuất hàng
      public List<XuatHang_DTO> getDanhSachXuatHang() {
            return xuatHangDAO.getDanhSachXuatHang();
      }

      //?------------------------------------------------
      //? danh sách phiếu xuất hàng ở panel Phiếu Xuất
      public List<XuatHang_DTO> getDanhSachPhieuXuatHang() {
            return xuatHangDAO.getDanhSachPhieuXuatHang();
      }
      public XuatHang_DTO getChiTietPhieuXuatHang(int maPhieuXuat) {
            return xuatHangDAO.getChiTietPhieuXuatHang(maPhieuXuat);
      }
      
}
