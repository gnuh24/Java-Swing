/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ThongTinSanPham.LoaiSanPhamDTO;
import Others.JDBCConfigure;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class LoaiSanPhamDAO implements DAOInterface<LoaiSanPhamDTO>{

    @Override
    public ArrayList<LoaiSanPhamDTO> getAll(Integer maKhoHang) {
        ArrayList<LoaiSanPhamDTO> list= new ArrayList<>();        
        try {

            Connection con= JDBCConfigure.getConnection();
            String sql="Select * From loaisanpham";
            PreparedStatement pst= con.prepareStatement(sql);
            ResultSet kq=pst.executeQuery(sql);
            while(kq.next()){
                int maLoaiSP=kq.getInt("MaLoaiSanPham");
                String tenLoaiSP=kq.getString("TenLoaiSanPham");
                int maKho=kq.getInt("MaKhoHang");
                LoaiSanPhamDTO tmp= new LoaiSanPhamDTO(maLoaiSP,tenLoaiSP,maKho);
                list.add(tmp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                String sql = "INSERT INTO loaisanpham (MaLoaiSanPham, TenLoaiSanPham, MaKhoHang) VALUES (?, ?, ?)";
                PreparedStatement pst= con.prepareStatement(sql);
                pst.setInt(1, loaiSP.getMaLoaiSanPham());
                pst.setString(2,loaiSP.getTenLoaiSanPham());
                pst.setInt(3, loaiSP.getMaKhoHang());
                int check= pst.executeUpdate();
                return true;
            }
       
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  finally{
            JDBCConfigure.closeConnection();
        }
        return false;
    }

    @Override
    public boolean update(LoaiSanPhamDTO loaiSP) {
        Connection con = JDBCConfigure.getConnection();
        try {

            String sql="Update loaisanpham Set MaLoaiSanPham=?, TenLoaiSanPham=? Where MaLoaiSanPham=?";
            PreparedStatement pst= con.prepareStatement(sql);
            pst.setInt(1, loaiSP.getMaLoaiSanPham());
            pst.setString(2, loaiSP.getTenLoaiSanPham());
            pst.setInt(3, loaiSP.getMaLoaiSanPham());
            int check=pst.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            JDBCConfigure.closeConnection();
        }
        return false;
    }

    @Override
    public boolean delete(LoaiSanPhamDTO loaiSP) {
        Connection con=JDBCConfigure.getConnection();
        try {

            String sql="DELETE FROM loaisanpham WHERE MaLoaiSanPham=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1, loaiSP.getMaLoaiSanPham());
            int check=pst.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            JDBCConfigure.closeConnection();
        }
        return false;
    }

    
    
}
