/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.ThongTinSanPham;

import DAO.DAOInterface;
import DTO.ThongTinSanPham.LoaiSanPhamDTO;
import Others.JDBCConfigure;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class LoaiSanPhamDAO implements DAOInterface<LoaiSanPhamDTO> {

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
                LoaiSanPhamDTO tmp= new LoaiSanPhamDTO(maLoaiSP,tenLoaiSP,maKhoHang);
                list.add(tmp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public LoaiSanPhamDTO getById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean create(Integer maKhoHang, LoaiSanPhamDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(LoaiSanPhamDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(LoaiSanPhamDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}
