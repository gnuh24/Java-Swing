/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS.ThongTinSanPham;

import DAO.ThongTinSanPham.LoaiSanPhamDAO;
import DTO.ThongTinSanPham.LoaiSanPhamDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class LoaiSanPhamBUS {
       private LoaiSanPhamDAO loaiSPDAO= new LoaiSanPhamDAO();
    private ArrayList<LoaiSanPhamDTO> listLoaiSP= new ArrayList<>();

    public LoaiSanPhamBUS() {
        listLoaiSP=loaiSPDAO.getAll(0);// 0 là mã kho
    }

    public LoaiSanPhamDAO getLoaiSPDAO() {
        return loaiSPDAO;
    }

    public ArrayList<LoaiSanPhamDTO> getAll() {
        return listLoaiSP;
    }
    
    public String[] tenLoaiSanPham(){
        String list[]= new String[listLoaiSP.size()+1];
        list[0]="Tất cả";
        for (int i=0; i<listLoaiSP.size();i++){
            list[i+1]=listLoaiSP.get(i).getTenLoaiSanPham();
        }
        return list;
    }
}
