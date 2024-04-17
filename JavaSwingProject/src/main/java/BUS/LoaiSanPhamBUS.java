/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.LoaiSanPhamDAO;
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
    
    public int getIndexByMaLoaiSP(int MaLoaiSP){
        for( int i=0; i < this.listLoaiSP.size();i++){
            if(this.listLoaiSP.get(i).getMaLoaiSanPham()==MaLoaiSP)
                return i;
        }
        return -1;
    }
    
    
    public String[] tenLoaiSanPham(){
        String list[]= new String[listLoaiSP.size()+1];
        list[0]="Tất cả";
        for (int i=0; i<listLoaiSP.size();i++){
            list[i+1]=listLoaiSP.get(i).getTenLoaiSanPham();
        }
        return list;
    }
    
    public boolean create(Integer maKhoHang, LoaiSanPhamDTO loaiSP){
        if(loaiSPDAO.create(maKhoHang, loaiSP)){
            listLoaiSP.add(loaiSP);
            return true;
        }
        return false;
    }
    // 7h-11h30 1h30-5h30, 7h-11h
    public boolean update(LoaiSanPhamDTO loaiSP){
        if(loaiSPDAO.update(loaiSP)){
            listLoaiSP.set(getIndexByMaLoaiSP(loaiSP.getMaLoaiSanPham()), loaiSP);
            return true;
        }
        return false;
    }
    
    public boolean delete(LoaiSanPhamDTO loaiSP){
        if(loaiSPDAO.delete(loaiSP)){
            listLoaiSP.remove(loaiSP);
            return true;
        }
        return false;
    }
    
    
    
    public ArrayList<LoaiSanPhamDTO> search(String txt){
        txt=txt.toLowerCase();
        ArrayList<LoaiSanPhamDTO> kq= new ArrayList<>();
        for(LoaiSanPhamDTO loaiSP: this.listLoaiSP){
            System.out.println("a");
            if ( loaiSP.getTenLoaiSanPham().toLowerCase().contains(txt) || String.valueOf(loaiSP.getMaLoaiSanPham()).contains(txt))
                kq.add(loaiSP);
        }
        return kq;
    }
}
