package BUS.ThongTinSanPham;

import java.util.ArrayList;

import DAO.ThongTinSanPham.SanPhamDAO;
import DTO.ThongTinSanPham.SanPhamDTO;
import java.util.Collections;
import java.util.Comparator;


public class SanPhamBUS {
    private final SanPhamDAO sanPhamDAO= new SanPhamDAO();
    private ArrayList<SanPhamDTO> danhSachSanPham= new ArrayList<>();
    private LoaiSanPhamBUS loaiSPBUS= new LoaiSanPhamBUS();
    private int maKho;
    
    public SanPhamBUS() {
        danhSachSanPham=sanPhamDAO.getAll(0);
    }
    
    public ArrayList<SanPhamDTO> getAll(){
        
        danhSachSanPham=sanPhamDAO.getAll(0); 
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
        for( int i=0; i < this.danhSachSanPham.size();i++)
            if(this.danhSachSanPham.get(i).getTenSanPham().equals(spMoi.getTenSanPham()))
                return false;
        
        if( sanPhamDAO.create(1,spMoi))
        {
            this.danhSachSanPham.add(spMoi);
            return true;
        }
        return false;
    }
    
    
    
    public boolean update(SanPhamDTO spMoi){
        SanPhamDTO spdto= sanPhamDAO.getByName(spMoi.getMaKhoHang(),spMoi.getTenSanPham());
        if(spdto!=null && spdto.getMaSanPham() != spMoi.getMaSanPham())
            return false;
        else if( sanPhamDAO.update(spMoi)){
            this.danhSachSanPham.set(getIndexByMaSP(spMoi.getMaSanPham()), spMoi);
            return true;
        }
        return false;
        
    }
    
    public boolean updateSoLuongSanPham(SanPhamDTO dto){
        return sanPhamDAO.updateSoLuongSanPham(dto);
    }
    
    
    public boolean delete(SanPhamDTO spCanXoa){
        if( sanPhamDAO.delete(spCanXoa))
        {
            this.danhSachSanPham=sanPhamDAO.getAll(0);
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
    public ArrayList<SanPhamDTO> locTheoGiaTangGiam(String txt){
        if (txt.equals("Tăng"))
        {
            Collections.sort(this.danhSachSanPham, new Comparator<SanPhamDTO>() {
                @Override
                public int compare(SanPhamDTO sp1, SanPhamDTO sp2) {
                    return Double.compare(sp1.getGiaSanPham(), sp2.getGiaSanPham());
                }
            });
        }
        else {
                Collections.sort(this.danhSachSanPham, new Comparator<SanPhamDTO>() {
                @Override
                public int compare(SanPhamDTO sp1, SanPhamDTO sp2) {
                    return Double.compare(sp2.getGiaSanPham(), sp1.getGiaSanPham());
                }
            });
        }
        return this.danhSachSanPham;
        
    }
    public ArrayList<SanPhamDTO> locTheoKhoangGia(int loai,int num1, int num2){
        ArrayList<SanPhamDTO> kq= new ArrayList<>();
        switch (loai) {
            case 1:
            for(SanPhamDTO sp: this.danhSachSanPham){
                if( sp.getGiaSanPham() < 200000)
                    kq.add(sp);
            }    
                break;
            case 2:
            for(SanPhamDTO sp: this.danhSachSanPham){
                if( sp.getGiaSanPham() > 200000 && sp.getGiaSanPham() < 1000000)
                    kq.add(sp);
            }
                break;
            case 3:
            for(SanPhamDTO sp: this.danhSachSanPham){
                if( sp.getGiaSanPham() > 1000000 && sp.getGiaSanPham() < 2000000)
                    kq.add(sp);
            }
                break;
            case 4:
            for(SanPhamDTO sp: this.danhSachSanPham){
                if( sp.getGiaSanPham() > 2000000)
                    kq.add(sp);
            }
                break;
            case 5:
            for(SanPhamDTO sp: this.danhSachSanPham){
                if( sp.getGiaSanPham() > num1 && sp.getGiaSanPham() < num2)
                    kq.add(sp);
            }
                break;
        }
        return kq;
        
    }

    
}
