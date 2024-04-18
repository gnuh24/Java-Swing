package BUS.NghiepVuNhapKho;


import DAO.NghiepVuNhapKho.ChiTietPhieuNhapKhoDAO;
import DAO.NghiepVuNhapKho.PhieuNhapKhoDAO;
import lombok.Data;
import lombok.NoArgsConstructor;


public class PhieuNhapKhoBUS {

    private final PhieuNhapKhoDAO phieuNhapKhoDAO;

    private final ChiTietPhieuNhapKhoDAO chiTietPhieuNhapKhoDAO;

    public PhieuNhapKhoBUS() {
        this.phieuNhapKhoDAO = new PhieuNhapKhoDAO();
        this.chiTietPhieuNhapKhoDAO = new ChiTietPhieuNhapKhoDAO();
    }

    public boolean updatePhieuNhapKhoVeNCCMacDinh(Integer maNCC){
        return phieuNhapKhoDAO.updatePhieuNhapKhoByMaNhaCungCap(maNCC);
    }
}
