package BUS.NghiepVuNhapKho;


import DAO.NghiepVuNhapKho.ChiTietPhieuNhapKhoDAO;
import DTO.NghiepVuNhapKho.ChiTietPhieuNhapKhoDTO;

import java.util.List;

public class ChiTietPhieuNhapKhoBUS {

    private final ChiTietPhieuNhapKhoDAO chiTietPhieuNhapKhoDAO;

    public ChiTietPhieuNhapKhoBUS() {
        this.chiTietPhieuNhapKhoDAO = new ChiTietPhieuNhapKhoDAO();
    }

    public List<ChiTietPhieuNhapKhoDTO> getChiTietPhieuNhapKhoByMaPhieu(Integer maPhieu){
        return chiTietPhieuNhapKhoDAO.getAll(maPhieu);
    }

    public boolean createChiTietPhieuNhapKho(ChiTietPhieuNhapKhoDTO dto){
        return chiTietPhieuNhapKhoDAO.create(null, dto);
    }

}
