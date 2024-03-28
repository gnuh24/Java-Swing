/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.example.DTO.SanPhamDTO;
import org.example.Others.JDBCConfigure;
import org.example.Others.JDBCUtil;


/**
 *
 * @author Admin
 */
public class SanPhamDAO implements DAOInterface<SanPhamDTO>{
    public int check=0;
    public static SanPhamDAO getInstance() {
        return new SanPhamDAO();
    }

    @Override
    public int them(SanPhamDTO t) {
        try {
            Connection ketNoi= JDBCUtil.getJDBCConnection();//JDBCConfigure.getConnection();
            
            String sql="";
            
            System.out.println(ketNoi);
            PreparedStatement pst= ketNoi.prepareStatement(sql);
            
            
            return check;
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    @Override
    public int capNhat(SanPhamDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int xoa(SanPhamDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<SanPhamDTO> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SanPhamDTO selectById(SanPhamDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<SanPhamDTO> selectByDieuKien(String dk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void main(String[] args) {
        SanPhamDAO a= new SanPhamDAO();
        SanPhamDTO b= new SanPhamDTO();
        System.out.println("Chay duoc");
        System.out.println(a.them(b));
    }
}
