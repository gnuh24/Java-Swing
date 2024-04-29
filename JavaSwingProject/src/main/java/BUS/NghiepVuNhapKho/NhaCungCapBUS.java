package BUS.NghiepVuNhapKho;

import DAO.NghiepVuNhapKho.NhaCungCapDAO;
import DTO.NghiepVuNhapKho.NhaCungCapDTO;
import ErrorResponse.TheValueAlreadyExists;

import java.util.List;

public class NhaCungCapBUS {

    private final NhaCungCapDAO nhaCungCapDAO;

    private final PhieuNhapKhoBUS phieuNhapKhoBUS;

    public NhaCungCapBUS() {

        this.nhaCungCapDAO = new NhaCungCapDAO();
        this.phieuNhapKhoBUS = new PhieuNhapKhoBUS();

    }

    public List<NhaCungCapDTO> getAllNhaCungCap(Integer maKhoHang){

        return nhaCungCapDAO.getAll(maKhoHang);
    }


    public NhaCungCapDTO getNhaCungCapById(Integer maNCC){

        return nhaCungCapDAO.getById(maNCC);
    }

    public List<NhaCungCapDTO> searchNhaCungCapByTenNCC(Integer maKhoHang, String tenNCC){
        return nhaCungCapDAO.search(maKhoHang, tenNCC);
    }


    public boolean createNhaCungCap(NhaCungCapDTO nhaCungCapDTO) throws TheValueAlreadyExists {

        if (!nhaCungCapDAO.isTenNCCExists(nhaCungCapDTO.getMaKhoHang(), nhaCungCapDTO.getTenNCC()) ){
            nhaCungCapDAO.create(nhaCungCapDTO.getMaKhoHang(), nhaCungCapDTO);
            return true;
        }else{
            throw new TheValueAlreadyExists("Tên nhà cung cấp `" + nhaCungCapDTO.getTenNCC() + "` đã tồn tại !!");

        }
    }

    public boolean updateNhaCungCap(NhaCungCapDTO nhaCungCapDTO) throws TheValueAlreadyExists {
        NhaCungCapDTO dto = nhaCungCapDAO.getNhaCungCapByTenNCC(nhaCungCapDTO.getMaKhoHang(),
            nhaCungCapDTO.getTenNCC());

        if (dto != null && dto.getMaNCC() != nhaCungCapDTO.getMaNCC()){
            throw new TheValueAlreadyExists("Tên nhà cung cấp `" + nhaCungCapDTO.getTenNCC() + "` đã tồn tại !!");
        }

        nhaCungCapDAO.update(nhaCungCapDTO);
        return true;

    }

    public boolean deleteNhaCungCap(NhaCungCapDTO nhaCungCapDTO){

        //Chuyển các phiếu nhập kho về mặc định
        phieuNhapKhoBUS.updatePhieuNhapKhoVeNCCMacDinh(nhaCungCapDTO.getMaNCC());

        //Xóa nhà cung cấp
        return nhaCungCapDAO.delete(nhaCungCapDTO);

    }
}
