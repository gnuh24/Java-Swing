package GUI;


import BUS.XuatHangBUS;
import DTO.NghiepVuXuatKho.PhieuXuatKhoDTO;

import java.awt.*;
// import java.awt.event.*;
// import java.text.DecimalFormat;
// import java.util.List;

import javax.swing.*;
// import javax.swing.border.*;
// import javax.swing.table.DefaultTableCellRenderer;
// import javax.swing.table.DefaultTableModel;

public class ChiTietPhieuXuat{
      JFrame frame = new JFrame();
      JPanel header;
            JLabel header_lb;
      JPanel main;
            JPanel main_top;
                  JLabel ma_phieu;
                  JLabel ma_phieu_lb;
            JPanel main_mid;
            JPanel main_bot;
      public ChiTietPhieuXuat(int maPhieuXuat) {
            PhieuXuatKhoDTO chiTietPhieuXuatHang = new XuatHangBUS().getChiTietPhieuXuatHang(maPhieuXuat);
            header = new JPanel();
            header_lb = new JLabel("CHI TIẾT PHIẾU XUẤT");
                  header_lb.setForeground(Color.WHITE);
                  header_lb.setFont(new Font("Arial", Font.BOLD, 20));
            header.setBackground(Color.BLACK);
            header.setPreferredSize(new Dimension(700,35));
            header.add(header_lb);
            main = new JPanel();
                  main_top = new JPanel();
                        ma_phieu = new JLabel("Mã Phiếu : ");
                        ma_phieu_lb = new JLabel();
                        ma_phieu_lb.setText(String.valueOf(chiTietPhieuXuatHang.getMaKhoHang()));
                  main_top.add(ma_phieu);
                  main_top.add(ma_phieu_lb);
                  main_mid = new JPanel();
                  main_bot = new JPanel();
            main.add(main_top);
            main.add(main_mid);
            main.add(main_bot);
            main.setLayout(new GridLayout(3, 1));

            frame.add(header);
            frame.add(main);
            frame.setLayout(new FlowLayout());
            frame.setSize(700,500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
      }
}
