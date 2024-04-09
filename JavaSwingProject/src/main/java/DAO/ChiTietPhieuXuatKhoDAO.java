package DAO;

import DTO.NghiepVuXuatKho.ChiTietPhieuXuatKhoDTO;
import DTO.NghiepVuXuatKho.PhieuXuatKhoDTO;
import DTO.ThongTinSanPham.SanPhamDTO;
import Others.JDBCConfigure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ChiTietPhieuXuatKhoDAO implements DAOInterface<ChiTietPhieuXuatKhoDTO>{

      @Override
      public ArrayList<ChiTietPhieuXuatKhoDTO> getAll(Integer maKhoHang) {
            ArrayList<ChiTietPhieuXuatKhoDTO> chiTietPhieuXuatKhoDTO = new ArrayList<>();

            return chiTietPhieuXuatKhoDTO;
      }

      @Override
      public ChiTietPhieuXuatKhoDTO getById(Integer id) {
            ChiTietPhieuXuatKhoDTO chiTietPhieuXuatKho = new ChiTietPhieuXuatKhoDTO();
            return chiTietPhieuXuatKho;
      }


    //

      @Override
      public boolean create(Integer maKhoHang, ChiTietPhieuXuatKhoDTO ChiTietPhieuXuatKhoDTO) {
            try{
                  Statement state = JDBCConfigure.getConnection().createStatement();
                  int taoPhieuXuatHang = state.executeUpdate("INSERT INTO `ctpxk` (`SoLuong`, `ThanhTien`, `DonGia`, `MaPhieu`, `MaSanPham`) VALUES ('"+ChiTietPhieuXuatKhoDTO.getSoLuong()+"', '"+ChiTietPhieuXuatKhoDTO.getThanhTien()+"', '"+ChiTietPhieuXuatKhoDTO.getDonGia()+"', '"+ChiTietPhieuXuatKhoDTO.getMaPhieu()+"', '"+ChiTietPhieuXuatKhoDTO.getMaSanPham()+"');");
                  if(taoPhieuXuatHang != 1) {
                        System.out.println("Khoi tao phieu xuat hàng that bai !");
                        return false;
                  } else return true;
            }catch(SQLException e) {
                  System.err.println(e.getMessage());
                  return false;
            }
      }

      @Override
      public boolean update(ChiTietPhieuXuatKhoDTO ChiTietPhieuXuatKhoDTO) {
            return true;
      }

      @Override
      public boolean delete(ChiTietPhieuXuatKhoDTO ChiTietPhieuXuatKhoDTO) {
            return true;
      }
}
