
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
            ResultSet kq=pst.executeQuery();
            while( kq.next()){
                int maSP=kq.getInt("MaSanPham");
                String tenSP=kq.getString("TenSanPham");
                String xuatXu=kq.getString("XuatXu");
                int gia=kq.getInt("Gia");
                int soLuong=kq.getInt("SoLuongConLai");
                boolean trangThai= kq.getBoolean("TrangThai");
                int maLoaiSP=kq.getInt("MaLoaiSanPham");
                String hinhAnh=kq.getString("AnhMinhHoa");
                int maKho=kq.getInt("MaKhoHang");
                SanPhamDTO sp= new SanPhamDTO(maSP, tenSP, xuatXu, gia, soLuong, trangThai, hinhAnh, maLoaiSP, maKho);
                list.add(sp);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            JDBCConfigure.closeConnection();
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
            pst.setString(1, String.valueOf(id));
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

        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            JDBCConfigure.closeConnection();
        }
        return sanPham;
    }
    @Override
    public boolean create(Integer maKhoHang, SanPhamDTO sanPhamDTO) {
        try {

                Connection con= JDBCConfigure.getConnection();
                String sql = "INSERT INTO sanpham (TenSanPham, XuatXu, Gia, SoLuongConLai, TrangThai, AnhMinhHoa, MaLoaiSanPham, MaKhoHang)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, sanPhamDTO.getTenSanPham());
                pst.setString(2, sanPhamDTO.getXuatXu());
                pst.setInt(3, sanPhamDTO.getGiaSanPham());
                pst.setInt(4, sanPhamDTO.getSoLuongConLai());
                pst.setBoolean(5, true);
                pst.setString(6, sanPhamDTO.getAnhMinhhoa());
                pst.setInt(7, sanPhamDTO.getMaLoaiSanPham());
                pst.setInt(8, sanPhamDTO.getMaKhoHang()); 
                check=pst.executeUpdate();

                return true;

        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            JDBCConfigure.closeConnection();
        }
        return false;
    }

    @Override
    public boolean update(SanPhamDTO sanPhamDTO) {
        Connection con = JDBCConfigure.getConnection();
        try {

            String sql="Update sanpham Set TenSanPham=?, XuatXu=?, Gia=?, SoLuongConLai=?, TrangThai=1, MaLoaiSanPham=?, AnhMinhHoa=?, MaKhoHang=? Where MaSanPham=?";
            PreparedStatement pst= con.prepareStatement(sql);
            pst.setString(1, sanPhamDTO.getTenSanPham());
            pst.setString(2, sanPhamDTO.getXuatXu());
            pst.setInt(3, sanPhamDTO.getGiaSanPham());
            pst.setInt(4, sanPhamDTO.getSoLuongConLai());
            pst.setInt(5, sanPhamDTO.getMaLoaiSanPham());
            pst.setString(6, sanPhamDTO.getAnhMinhhoa());
            pst.setInt(7, sanPhamDTO.getMaKhoHang());
            pst.setInt(8, sanPhamDTO.getMaSanPham());
            check=pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  finally{
            JDBCConfigure.closeConnection();
        }
        return false;
    }

    @Override
    public boolean delete(SanPhamDTO sanPhamDTO) {
        Connection con=JDBCConfigure.getConnection();
        try {

            String sql="DELETE FROM sanpham WHERE MaSanPham=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1, sanPhamDTO.getMaSanPham());
            check=pst.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  finally{
            JDBCConfigure.closeConnection();
        }
        return false;
    }
}
