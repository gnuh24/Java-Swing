package BUS;


import DAO.PhieuXuatKhoDAO;
import DTO.NghiepVuXuatKho.PhieuXuatKhoDTO;
import DTO.ThongTinSanPham.SanPhamDTO;

import java.util.List;

public class XuatHangBUS {
      private PhieuXuatKhoDAO phieuXuatKhoDAO;

      public XuatHangBUS() {
            this.phieuXuatKhoDAO = new PhieuXuatKhoDAO();
      }
      //? danh sách sản phẩm ở panel Xuất hàng
      public List<SanPhamDTO> getDanhSachSanPham() {

//            return phieuXuatKhoDAO.getDanhSachSanPham();
            return null;
      }
      //? danh sách xuất hàng ở panel Xuất hàng
      public List<PhieuXuatKhoDTO> getDanhSachXuatHang() {

//            return phieuXuatKhoDAO.getDanhSachXuatHang();
            return null;
      }

      //?------------------------------------------------
      //? danh sách phiếu xuất hàng ở panel Phiếu Xuất
      public List<PhieuXuatKhoDTO> getDanhSachPhieuXuatHang() {

//            return phieuXuatKhoDAO.getDanhSachPhieuXuatHang();
            return null;
      }
      public PhieuXuatKhoDTO getChiTietPhieuXuatHang(int maPhieuXuat) {
//            return phieuXuatKhoDAO.getChiTietPhieuXuatHang(maPhieuXuat);
            return null;
      }
      
}
