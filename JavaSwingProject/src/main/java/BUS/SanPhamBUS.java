package BUS;

import DAO.SanPhamDAO;
import java.util.ArrayList;

import DTO.ThongTinSanPham.SanPhamDTO;


public class SanPhamBUS {
    private final SanPhamDAO sanPhamDAO= new SanPhamDAO();
    private ArrayList<SanPhamDTO> danhSachSanPham= new ArrayList<>();
    private LoaiSanPhamBUS loaiSPBUS= new LoaiSanPhamBUS();
    
    public SanPhamBUS() {
        danhSachSanPham=sanPhamDAO.getAll(0);
    }
    
    public ArrayList<SanPhamDTO> getAll(){
        
        return this.danhSachSanPham;
    }
    // hàm hỗ trợ lấy ra index trong danh sách sản phẩm dành cho việc update thông tin sản phẩm
    public int getIndexByMaSP(int maSP){
        for( int i=0; i < this.danhSachSanPham.size();i++){
            if(this.danhSachSanPham.get(i).getMaSanPham()==maSP)
                return i;
        }
        return -1;
    }
    
    
    public SanPhamDTO getById(int masp){
        for( int i=0; i<this.danhSachSanPham.size();i++)
            if ( this.danhSachSanPham.get(i).getMaSanPham()==masp)
                return this.danhSachSanPham.get(i);
        
        return null;
    }
    
    public boolean create(SanPhamDTO spMoi){
        if( sanPhamDAO.create(0,spMoi))
        {
            this.danhSachSanPham.add(spMoi);
            return true;
        }
        return false;
    }
    
    
    
    public boolean update(SanPhamDTO spMoi){
        if( sanPhamDAO.update(spMoi)){
            this.danhSachSanPham.set(getIndexByMaSP(spMoi.getMaSanPham()), spMoi);
            return true;
        }
        return false;
        
    }
    
    public boolean delete(SanPhamDTO spCanXoa){
        if( sanPhamDAO.delete(spCanXoa))
        {
            this.danhSachSanPham.remove(spCanXoa);
            return true;
        }
        return false;
    }
    
    // đang tìm kiếm với tên và loại sản phẩm
    // các loại tìm kiếm khác sau này có nhu cầu thì mở rộng sau
    public ArrayList<SanPhamDTO> search(String txt){
        txt=txt.toLowerCase();
        ArrayList<SanPhamDTO> kq= new ArrayList<>();
        for(SanPhamDTO sp: this.danhSachSanPham){
            if( sp.getTenSanPham().toLowerCase().contains(txt) || 
                loaiSPBUS.tenLoaiSanPham()[sp.getMaLoaiSanPham()].toLowerCase().equals(txt))
                    kq.add(sp);
        }
        return kq;
    }
}
