package BUS.NghiepVuXuatKho;
import java.util.ArrayList;

import DAO.NghiepVuXuatKho.ChiTietPhieuXuatKhoDAO;
import DTO.NghiepVuXuatKho.*;
public class ChiTietPhieuXuatKhoBUS {
      private final DAO.NghiepVuXuatKho.ChiTietPhieuXuatKhoDAO ChiTietPhieuXuatKhoDAO= new ChiTietPhieuXuatKhoDAO();

    public ChiTietPhieuXuatKhoBUS() {
    }
    
    public ArrayList<ChiTietPhieuXuatKhoDTO> getAll(int maKhoHang){
        return ChiTietPhieuXuatKhoDAO.getAll(maKhoHang);
    }
    public boolean create(Integer maKhoHang, ChiTietPhieuXuatKhoDTO ChiTietPhieuXuatKhoDTO) {
        return ChiTietPhieuXuatKhoDAO.create(maKhoHang, ChiTietPhieuXuatKhoDTO);
    }
    public boolean update(ChiTietPhieuXuatKhoDTO ChiTietPhieuXuatKhoDTO) {
        return ChiTietPhieuXuatKhoDAO.update(ChiTietPhieuXuatKhoDTO);
    }
    public boolean delete(ChiTietPhieuXuatKhoDTO ChiTietPhieuXuatKhoDTO) {
        return ChiTietPhieuXuatKhoDAO.delete(ChiTietPhieuXuatKhoDTO);
    }
}
