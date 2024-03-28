/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.GUIPanel;
import javax.swing.*;
import java.awt.*;
import lombok.*;
import org.example.GUIThanhPhan.ButtonCustom;
@Data
/**
 *
 * @author Admin
 */
public class MenuTaskBar extends JPanel{

    private ButtonCustom btn_TrangChu, btn_sanPham, btn_loaiSP;
    private ButtonCustom btn_PhieuNhap, btn_PhieuXuat, btn_TaiKhoan;
    private ButtonCustom btn_ThongKe, btn_DoiThongTin, btn_DangXuat;

    public MenuTaskBar() {
        this.setPreferredSize(new Dimension(288,800));
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout(0,0));
        
        JPanel thongTinUser= new JPanel(new BorderLayout(0,0));
        thongTinUser.setBackground(Color.WHITE);
        thongTinUser.setPreferredSize(new Dimension(288,120));
        JLabel hoTen= new JLabel("Tên Đăng Nhập");
        hoTen.setVerticalAlignment(JLabel.CENTER);
        hoTen.setHorizontalAlignment(JLabel.CENTER);
        hoTen.setIcon(new ImageIcon("iconUser64.png"));
        JPanel bar1= new JPanel();
        bar1.setBackground(Color.BLACK);//new Color(204,214,219));
        bar1.setPreferredSize(new Dimension(1,0));
        JPanel bar2= new JPanel();
        bar2.setBackground(Color.BLACK);//new Color(204,214,219));
        bar2.setPreferredSize(new Dimension(0,1));
        JPanel bar3= new JPanel();
        bar3.setBackground(Color.BLACK); //Color(204,214,219));
        bar3.setPreferredSize(new Dimension(1,0));
        
        
        thongTinUser.add(hoTen,BorderLayout.CENTER);
        thongTinUser.add(bar1,BorderLayout.EAST);
        thongTinUser.add(bar2,BorderLayout.SOUTH);
        this.add(bar3,BorderLayout.EAST);
        this.add(thongTinUser,BorderLayout.NORTH);
        this.setPreferredSize(new Dimension(200,900));
        // Chuyển thành giao diện sản phẩm
        JPanel dieuHuong= new JPanel(new GridLayout(9,1,0,0));// giữa, khoảng cách chiều ngang là 0, khoảng cách dọc là 5
        dieuHuong.setBackground(Color.WHITE);
        btn_TrangChu= new ButtonCustom("Trang chủ","trangchu.png");
        btn_sanPham= new ButtonCustom("Sản phẩm","sanpham.png");
        btn_loaiSP= new ButtonCustom("Loại Sản phẩm","sanpham.png");
        btn_PhieuNhap= new ButtonCustom("Phiếu nhập","phieunhap.png");
        btn_PhieuXuat= new ButtonCustom("Phiếu xuất","phieuxuat.png");
        btn_TaiKhoan= new ButtonCustom("Tài khoản","taikhoan.png");
        btn_ThongKe= new ButtonCustom("Thống kê","thongke.png");
        btn_DoiThongTin= new ButtonCustom("Đổi thông tin","thongtin.png");
        btn_DangXuat= new ButtonCustom("Đăng xuất","dangxuat.png");
        dieuHuong.add(btn_TrangChu); dieuHuong.add(btn_sanPham); dieuHuong.add(btn_loaiSP);
        dieuHuong.add(btn_PhieuNhap); dieuHuong.add(btn_PhieuXuat); dieuHuong.add(btn_TaiKhoan);
        dieuHuong.add(btn_ThongKe);  dieuHuong.add(btn_DoiThongTin); dieuHuong.add(btn_DangXuat);
        this.add(dieuHuong,BorderLayout.CENTER);
    }
    
    
}
