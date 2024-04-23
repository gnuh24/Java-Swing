
package DAO.ThongTinSanPham;

import DAO.DAOInterface;
import DAO.ThongTinSanPham.SanPhamDAO;
import DTO.ThongTinSanPham.LoaiSanPhamDTO;
import Others.JDBCConfigure;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class LoaiSanPhamDAO implements DAOInterface<LoaiSanPhamDTO> {
    private int maKho;
    @Override
    public ArrayList<LoaiSanPhamDTO> getAll(Integer maKhoHang) {
        maKho=maKhoHang;
        ArrayList<LoaiSanPhamDTO> list= new ArrayList<>();
        try {

            Connection con= JDBCConfigure.getConnection();
            String sql="Select * From loaisanpham Where MaKhoHang=? or loaisanpham.TenLoaiSanPham='Các nhà cung cấp khác'";
            PreparedStatement pst= con.prepareStatement(sql);
            pst.setInt(1, maKhoHang);
            ResultSet kq=pst.executeQuery();
            while(kq.next()){
                int maLoaiSP=kq.getInt("MaLoaiSanPham");
                String tenLoaiSP=kq.getString("TenLoaiSanPham");
                int maKho=kq.getInt("MaKhoHang");
                LoaiSanPhamDTO tmp= new LoaiSanPhamDTO(maLoaiSP,tenLoaiSP,maKho);
                list.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally{
            JDBCConfigure.closeConnection();
        }
        return list;
    }

    @Override
    public LoaiSanPhamDTO getById(Integer id) {
        LoaiSanPhamDTO loaisp=null;
        try {

            Connection con=JDBCConfigure.getConnection();
            String sql="Select * from loaisanpham Where MaLoaiSanPham=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, String.valueOf(id));
            ResultSet kq=pst.executeQuery();
            while(kq.next()){
                int maLoaiSP=kq.getInt("MaLoaiSanPham");
                String tenLoaiSP=kq.getString("TenLoaiSanPham");
                int maKho=kq.getInt("MaKhoHang");
                loaisp= new LoaiSanPhamDTO(maLoaiSP,tenLoaiSP,maKho);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }finally{
            JDBCConfigure.closeConnection();
        }
        return loaisp;

    }
    
    
    public LoaiSanPhamDTO getByName(String tenLoai) {
        LoaiSanPhamDTO loaisp=null;
        try {

            Connection con=JDBCConfigure.getConnection();
            String sql="Select * from loaisanpham Where TenLoaiSanPham=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, tenLoai);
            ResultSet kq=pst.executeQuery();
            while(kq.next()){
                int maLoaiSP=kq.getInt("MaLoaiSanPham");
                String tenLoaiSP=kq.getString("TenLoaiSanPham");
                int maKho=kq.getInt("MaKhoHang");
                loaisp= new LoaiSanPhamDTO(maLoaiSP,tenLoaiSP,maKho);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }finally{
            JDBCConfigure.closeConnection();
        }
        return loaisp;

    }

    @Override
    public boolean create(Integer maKhoHang, LoaiSanPhamDTO loaiSP) {

        try {
            if( getById(loaiSP.getMaLoaiSanPham())!=null)
            {
                return false;
            }else
            {
                Connection con= JDBCConfigure.getConnection();
                String sql = "INSERT INTO loaisanpham (TenLoaiSanPham, MaKhoHang) VALUES (?, ?)";
                PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1,loaiSP.getTenLoaiSanPham());
                pst.setInt(2, loaiSP.getMaKhoHang());
                int check= pst.executeUpdate();
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("Lỗi SQL create loại SP");
        }  finally{
            JDBCConfigure.closeConnection();
        }
        return false;
    }

    @Override
    public boolean update(LoaiSanPhamDTO loaiSP) {
        Connection con = JDBCConfigure.getConnection();
        try {
            
            String sql="Update loaisanpham Set TenLoaiSanPham=? Where MaLoaiSanPham=?";
            PreparedStatement pst= con.prepareStatement(sql);
            pst.setString(1, loaiSP.getTenLoaiSanPham());
            pst.setInt(2, loaiSP.getMaLoaiSanPham());
            int check=pst.executeUpdate();

            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally{
            JDBCConfigure.closeConnection();
        }
        return false;
    }

    @Override
    public boolean delete(LoaiSanPhamDTO loaiSP) {
        Connection con=JDBCConfigure.getConnection();
        try {
            System.out.println("Thông tin mã loại:" +loaiSP.toString());
            SanPhamDAO.getInstance().updateWithDelteLoaiSP(loaiSP);
            String sql="DELETE FROM loaisanpham WHERE MaLoaiSanPham=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1, loaiSP.getMaLoaiSanPham());

            int check=pst.executeUpdate();

            return true;

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally{
            JDBCConfigure.closeConnection();
        }
        return false;
    }



}
