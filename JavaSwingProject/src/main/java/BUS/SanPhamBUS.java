package BUS;

import java.util.ArrayList;

import DTO.ThongTinSanPham.SanPhamDTO;
import org.example.DAO.SanPhamDAO;

public class SanPhamBUS {
    private final SanPhamDAO sanPhamDAO= new SanPhamDAO();
    private ArrayList<SanPhamDTO> danhSachSanPham= new ArrayList<>();

    public SanPhamBUS() {
        
//        danhSachSanPham=sanPhamDAO.getAll();
    }
    
    public ArrayList<SanPhamDTO> getAll(){
        
        return this.danhSachSanPham;
    }
    
    
    
    
}
