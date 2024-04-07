
package DAO;

import DTO.ThongTinSanPham.SanPhamDTO;
import Others.JDBCConfigure;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SanPhamDAO implements DAOInterface<SanPhamDTO>{
    
    public int check=0;

    public static SanPhamDAO getInstance() {
        return new SanPhamDAO();
    }


    @Override
    public ArrayList<SanPhamDTO> getAll(Integer maKhoHang) {
        ArrayList<SanPhamDTO> list= new ArrayList<>();
        try {
            Connection con= JDBCConfigure.getConnection();
            String sql="Select * From sanpham";
            PreparedStatement pst=con.prepareStatement(sql);
            ResultSet kq=pst.executeQuery(sql);
            while( kq.next()){
                int maSP=kq.getInt("MaSanPham");
                String tenSP=kq.getString("TenSanPham");
                String xuatXu=kq.getString("XuatXu");
                int gia=kq.getInt("Gia");
                int soLuong=kq.getInt("SoLuongConLai");
                boolean trangThai= kq.getBoolean("TrangThai");
                String maLoaiSP=String.valueOf(kq.getInt("MaLoaiSanPham"));
                String hinhAnh=kq.getString("AnhMinhHoa");
                int maKho=kq.getInt("MaKhoHang");
                SanPhamDTO sp= new SanPhamDTO(maSP, tenSP, xuatXu, gia, soLuong, trangThai, hinhAnh, maSP, maKho);
                list.add(sp);
                
            }
            JDBCConfigure.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public SanPhamDTO getById(Integer id) {
        SanPhamDTO sanPham=null;
        try {
            Connection con= JDBCConfigure.getConnection();
            String sql="Select * From sanpham Where MaSanPham=?";
            PreparedStatement pst= con.prepareStatement(sql);
            pst.setString(0, String.valueOf(id));
            ResultSet kq=pst.executeQuery();    
            while(kq.next()){
                int maSP=kq.getInt("MaSanPham");
                String tenSP=kq.getString("TenSanPham");
                String xuatXu=kq.getString("XuatXu");
                int gia=kq.getInt("Gia");
                int soLuong=kq.getInt("SoLuongConLai");
                boolean trangThai= kq.getBoolean("TrangThai");
                String maLoaiSP=String.valueOf(kq.getInt("MaLoaiSanPham"));
                String hinhAnh=kq.getString("AnhMinhHoa");
                int maKho=kq.getInt("MaKhoHang");
                sanPham= new SanPhamDTO(maSP, tenSP, xuatXu, gia, soLuong, trangThai, hinhAnh, maSP, maKho);
            }
            JDBCConfigure.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sanPham;
    }
    @Override
    public boolean create(Integer maKhoHang, SanPhamDTO sanPhamDTO) {
        Connection con= JDBCConfigure.getConnection();
        try {


            String sql="Insert Into sanpham (MaSanPham, TenSanPham, XuatXu, Gia, SoLuongConLai, TrangThai, AnhMinhHoa, MaLoaiSanPham, MaKhoHang)"
                        +" Values (?, ?, ?, ?, ?, 1, ?, ?, ?)";
            PreparedStatement pst= con.prepareStatement(sql);
            
            pst.setString(0, String.valueOf(sanPhamDTO.getMaSanPham()));
            pst.setString(1, String.valueOf(sanPhamDTO.getTenSanPham()));
            pst.setString(2, sanPhamDTO.getXuatXu());
            pst.setString(3, String.valueOf(sanPhamDTO.getGiaSanPham()));
            pst.setString(4, String.valueOf(sanPhamDTO.getSoLuongConLai()));
            pst.setString(5, String.valueOf(sanPhamDTO.getAnhMinhhoa()));
            pst.setString(6, String.valueOf(sanPhamDTO.getMaLoaiSanPham()));
            pst.setString(7, String.valueOf(sanPhamDTO.getMaKhoHang())); 
            check=pst.executeUpdate(sql);
            
            JDBCConfigure.closeConnection();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(SanPhamDTO sanPhamDTO) {
        Connection con = JDBCConfigure.getConnection();
        try {

            String sql="Update sanpham Set, MaSanPham=?, TenSanPham=?, XuatXu=?, Gia=?, SoLuongConLai=?, TrangThai=1, MaLoaiSanPham=?, AnhMinhHoa=?, MaKhoHang=? Where MaSanPham=?";
            PreparedStatement pst= con.prepareStatement(sql);
            pst.setString(0, String.valueOf(sanPhamDTO.getMaSanPham()));
            pst.setString(1, sanPhamDTO.getTenSanPham());
            pst.setString(2, sanPhamDTO.getXuatXu());
            pst.setString(3, String.valueOf(sanPhamDTO.getGiaSanPham()));
            pst.setString(4, String.valueOf(sanPhamDTO.getSoLuongConLai()));
            pst.setString(5, String.valueOf(sanPhamDTO.getMaLoaiSanPham()));
            pst.setString(6, sanPhamDTO.getAnhMinhhoa());
            pst.setString(7, String.valueOf(sanPhamDTO.getMaKhoHang()));
            pst.setString(8, String.valueOf(sanPhamDTO.getMaSanPham()));
            check=pst.executeUpdate(sql);
            
            JDBCConfigure.closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(SanPhamDTO sanPhamDTO) {
        Connection con=JDBCConfigure.getConnection();
        try {

            String sql="Delete from sanpham Where MaSanPham=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(0, String.valueOf(sanPhamDTO.getMaSanPham()));
            check=pst.executeUpdate(sql);
            JDBCConfigure.closeConnection();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
