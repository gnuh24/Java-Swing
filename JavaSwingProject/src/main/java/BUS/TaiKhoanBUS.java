package BUS;

import DAO.TaiKhoanDAO;
import DTO.NguoiDung.TaiKhoanDTO;

import java.util.ArrayList;

public class TaiKhoanBUS {
    private TaiKhoanDAO taiKhoanDAO;

    public TaiKhoanBUS() {
        this.taiKhoanDAO = new TaiKhoanDAO();
    }

    public ArrayList<TaiKhoanDTO> getAllAccounts() {
        return taiKhoanDAO.getAllAccounts();
    }

    public int insertAccount(TaiKhoanDTO taiKhoanDTO) {
        return taiKhoanDAO.insert(taiKhoanDTO);
    }

    public int updateAccount(TaiKhoanDTO taiKhoanDTO) {
        return taiKhoanDAO.update(taiKhoanDTO);
    }

    public int deleteAccount(TaiKhoanDTO taiKhoanDTO) {
        return taiKhoanDAO.delete(taiKhoanDTO);
    }

    public TaiKhoanDTO getUserByUserName(String userName) {
        return taiKhoanDAO.getUserByUserName(userName);
    }
    
    public ArrayList<TaiKhoanDTO> searchUserName(String userName) {
        return taiKhoanDAO.searchUserName(userName);
    }
}
