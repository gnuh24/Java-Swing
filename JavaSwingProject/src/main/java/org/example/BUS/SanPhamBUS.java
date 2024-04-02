/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.BUS;

import java.util.ArrayList;
import org.example.DAO.SanPhamDAO;
import org.example.DTO.SanPhamDTO;

/**
 *
 * @author Admin
 */
public class SanPhamBUS {
    private SanPhamDAO DAOsp= new SanPhamDAO();
    private ArrayList<SanPhamDTO> danhSachSanPham= new ArrayList<>();

    public SanPhamBUS() {
        danhSachSanPham=DAOsp.selectAll();
    }
    
    public ArrayList<SanPhamDTO> getAll(){
        return this.danhSachSanPham;
    }
    
    
    
    
    
    
}
