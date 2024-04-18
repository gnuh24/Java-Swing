/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.ThongTinSanPham;

import DAO.DAOInterface;
import DTO.ThongTinSanPham.SanPhamDTO;
import Others.JDBCConfigure;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamDAO implements DAOInterface<SanPhamDTO> {

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
                boolean trangThai=kq.getBoolean("TrangThai");
                String maLoaiSP=String.valueOf(kq.getInt("MaLoaiSanPham"));
                String hinhAnh=kq.getString("AnhMinhHoa");
                int maKho=kq.getInt("MaKhoHang");
                SanPhamDTO sp= new SanPhamDTO(maSP, tenSP, xuatXu, gia, soLuong, trangThai, hinhAnh, maSP, maKho);
                list.add(sp);

            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public SanPhamDTO getById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean create(Integer maKhoHang, SanPhamDTO sanPhamDTO) {
        try {
            Connection ketNoi= JDBCConfigure.getConnection();

            String sql="";

            System.out.println(ketNoi);
            PreparedStatement pst= ketNoi.prepareStatement(sql);


            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean update(SanPhamDTO sanPhamDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public boolean delete(SanPhamDTO sanPhamDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
