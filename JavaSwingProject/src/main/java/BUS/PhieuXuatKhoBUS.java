package BUS;

import DAO.*;
import java.util.ArrayList;

import DTO.NghiepVuXuatKho.*;

public class PhieuXuatKhoBUS {
    private final PhieuXuatKhoDAO PhieuXuatKhoDAO= new PhieuXuatKhoDAO();

    public PhieuXuatKhoBUS() {
    }
    
    public ArrayList<PhieuXuatKhoDTO> getAll(int maKhoHang){
        return PhieuXuatKhoDAO.getAll(maKhoHang);
    }
    public boolean create(Integer maKhoHang, PhieuXuatKhoDTO phieuXuatKhoDTO) {
        return PhieuXuatKhoDAO.create(maKhoHang, phieuXuatKhoDTO);
    }
    public boolean update(PhieuXuatKhoDTO phieuXuatKhoDTO) {
        return PhieuXuatKhoDAO.update(phieuXuatKhoDTO);
    }
    public boolean delete(PhieuXuatKhoDTO phieuXuatKhoDTO) {
        return PhieuXuatKhoDAO.delete(phieuXuatKhoDTO);
    }
    
    
    
}
