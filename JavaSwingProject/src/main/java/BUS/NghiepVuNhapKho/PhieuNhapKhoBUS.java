package BUS.NghiepVuNhapKho;


import BUS.ThongTinSanPham.SanPhamBUS;
import DAO.NghiepVuNhapKho.ChiTietPhieuNhapKhoDAO;
import DAO.NghiepVuNhapKho.PhieuNhapKhoDAO;
import DTO.NghiepVuNhapKho.ChiTietPhieuNhapKhoDTO;
import DTO.NghiepVuNhapKho.PhieuNhapKhoDTO;
import DTO.ThongTinSanPham.SanPhamDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


public class PhieuNhapKhoBUS {

    private final PhieuNhapKhoDAO phieuNhapKhoDAO;

    private final ChiTietPhieuNhapKhoBUS chiTietPhieuNhapKhoBUS;

    private final SanPhamBUS sanPhamBUS;

    public PhieuNhapKhoBUS() {
        this.phieuNhapKhoDAO = new PhieuNhapKhoDAO();
        this.chiTietPhieuNhapKhoBUS = new ChiTietPhieuNhapKhoBUS();
        this.sanPhamBUS = new SanPhamBUS();
    }

    public List<PhieuNhapKhoDTO> getAllPhieuNhapKho(Integer maKhoHang){
        return phieuNhapKhoDAO.getAll(maKhoHang);
    }

    public PhieuNhapKhoDTO getPhieuNhapKhoByMaPhieu(Integer maPhieu){
        return phieuNhapKhoDAO.getById(maPhieu);
    }

    public boolean createPhieuNhapKho(Integer maKhoHang,
                                      PhieuNhapKhoDTO phieuNhapKhoDTO,
                                      List<ChiTietPhieuNhapKhoDTO> chiTietPhieuNhapKhoDTOList){
        //Tạo phiếu nhập kho
        phieuNhapKhoDAO.create(maKhoHang, phieuNhapKhoDTO);

        for (ChiTietPhieuNhapKhoDTO dto: chiTietPhieuNhapKhoDTOList){

            //Lấy mã phiếu mới được tạo :3
            dto.setMaPhieu(phieuNhapKhoDTO.getMaPhieu());

            //Tạo các chi tiết phiếu nhập kho
            chiTietPhieuNhapKhoBUS.createChiTietPhieuNhapKho(dto);


            //Tăng so lượng sản phẩm trong kho
            SanPhamDTO sanPhamDTO = sanPhamBUS.getById(dto.getMaSanPham());
            sanPhamDTO.setSoLuongConLai(
                sanPhamDTO.getSoLuongConLai() + dto.getSoLuong()
            );
            sanPhamBUS.updateSoLuongSanPham(sanPhamDTO);

        }


        return true;
    }

    public boolean updatePhieuNhapKho(PhieuNhapKhoDTO phieuNhapKhoDTO){
        return phieuNhapKhoDAO.update(phieuNhapKhoDTO);
    }

    public boolean updatePhieuNhapKhoVeNCCMacDinh(Integer maNCC){
        return phieuNhapKhoDAO.updatePhieuNhapKhoByMaNhaCungCap(maNCC);
    }


    public static void main(String[] args) {
        PhieuNhapKhoBUS bus = new PhieuNhapKhoBUS();
//        List<PhieuNhapKhoDTO> dtos = bus.getAllPhieuNhapKho(1);
//
//        for (PhieuNhapKhoDTO dto: dtos){
//            System.err.println("ID: " + dto.getMaPhieu());
//            System.err.println("NgayNhap: " + dto.getNgayNhapKho());
//            System.err.println("\n");
//        }

//        PhieuNhapKhoDTO dtos = bus.getPhieuNhapKhoByMaPhieu(1);
//
//            System.err.println("ID: " + dtos.getMaPhieu());
//            System.err.println("NgayNhap: " + dtos.getNgayNhapKho());
//            System.err.println("\n");


        PhieuNhapKhoDTO dto = new PhieuNhapKhoDTO();
        dto.setMaKhoHang(2);
        dto.setMaNCC(3);
        dto.setNgayNhapKho("1985-12-24");
        dto.setTongGiaTri(123456L);

        List<ChiTietPhieuNhapKhoDTO> list = new ArrayList<>();
        for (int i=1; i<=3; i++){
            ChiTietPhieuNhapKhoDTO dto1 = new ChiTietPhieuNhapKhoDTO();
            dto1.setThanhTien(13L);
            dto1.setMaPhieu(dto.getMaPhieu());
            dto1.setMaSanPham(i);
            dto1.setDonGia(12);
            dto1.setSoLuong(12);

            list.add(dto1);
        }

        bus.createPhieuNhapKho(2, dto, list);



    }
}
