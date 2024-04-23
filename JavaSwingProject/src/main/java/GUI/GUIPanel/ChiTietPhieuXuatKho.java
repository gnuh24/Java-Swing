package GUI.GUIPanel;

import java.awt.*;
// import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.sql.*;

import javax.swing.*;
// import javax.swing.border.*;
// import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.*;

import BUS.NghiepVuXuatKho.ChiTietPhieuXuatKhoBUS;
import BUS.ThongTinSanPham.SanPhamBUS;
import DTO.NghiepVuXuatKho.*;
import Others.JDBCConfigure;

public class ChiTietPhieuXuatKho{
      ChiTietPhieuXuatKhoBUS chiTietPhieuXuatKhoBUS = new ChiTietPhieuXuatKhoBUS();
      SanPhamBUS sanPhamBUS;
      JFrame frame = new JFrame();
      JPanel header;
      JLabel header_lb;
      JPanel main;
      JPanel main_top;
      JLabel ma_phieu,ma_phieu_lb;
      JLabel ma_kho_hang, ma_kho_hang_lb;
      JLabel ten_kho_hang, ten_kho_hang_lb;
      JLabel nguoi_xuat, nguoi_xuat_lb;
      JLabel ngay_xuat_kho, ngay_xuat_kho_lb;
      JPanel main_middle;
      DefaultTableModel model_ds_ctpxk;
      JTable table_ds_ctpxk;
      JScrollPane ds_ctpxk;
      JPanel main_bottom;
      JLabel tong_tien, tong_tien_lb;
      JButton closeCTPXK;
      public ChiTietPhieuXuatKho(int maPhieuXuat) {

            header = new JPanel();
            header_lb = new JLabel("CHI TIẾT PHIẾU XUẤT");
            header_lb.setForeground(Color.WHITE);
            header_lb.setFont(new Font("Arial", Font.BOLD, 20));
            header.setBackground(Color.BLACK);
            header.setPreferredSize(new Dimension(800,35));
            header.add(header_lb);
            main = new JPanel();
            main_top = new JPanel();
            //? JLabel ma_phieu,ma_phieu_lb;
            ma_phieu = new JLabel("Mã phiếu : ");
            ma_phieu_lb = new JLabel();

            //? JLabel ma_kho_hang, ma_kho_hang_lb;
            ma_kho_hang = new JLabel("Mã kho hàng : ");
            ma_kho_hang_lb = new JLabel();

            //? JLabel ten_kho_hang, ten_kho_hang_lb;
            ten_kho_hang = new JLabel("Tên kho hàng : ");
            ten_kho_hang_lb = new JLabel();

            //? JLabel nguoi_xuat, nguoi_xuat_lb;
            nguoi_xuat = new JLabel("Tên người xuất : ");
            nguoi_xuat_lb = new JLabel();

            //? JLabel ngay_xuat_kho, ngay_xuat_kho_lb;
            ngay_xuat_kho = new JLabel("Ngày xuất kho : ");
            ngay_xuat_kho_lb = new JLabel();
            main_top.add(ma_phieu); main_top.add(ma_phieu_lb);
            main_top.add(ma_kho_hang); main_top.add(ma_kho_hang_lb);
            main_top.add(ten_kho_hang); main_top.add(ten_kho_hang_lb);
            main_top.add(nguoi_xuat); main_top.add(nguoi_xuat_lb);
            main_top.add(ngay_xuat_kho); main_top.add(ngay_xuat_kho_lb);
            main_top.setLayout(new GridLayout(3, 3));
            main_top.setPreferredSize(new Dimension(600,70));

            main_middle = new JPanel();
            String columns_ds_ctpxk[] = {"STT", "Mã sản phẩm","Tên sản phẩm", "Đơn giá", "Số lượng", "Thành tiền"};
            String data_ds_ctpxk[][] = {};
            model_ds_ctpxk = new DefaultTableModel(data_ds_ctpxk, columns_ds_ctpxk){
                  @Override
                  public Class<?> getColumnClass(int columnIndex) {
                        // Đặt kiểu dữ liệu cho từng cột
                        if (columnIndex == 0 || columnIndex == 1 || columnIndex == 3) {
                              return Integer.class;
                        } else if (columnIndex == 2 || columnIndex == 4 || columnIndex == 5) {
                              return String.class;
                        } else {
                              return Object.class; // Hoặc có thể trả về kiểu Object làm mặc định
                        }
                  }
            };
            table_ds_ctpxk = new JTable(model_ds_ctpxk);
            table_ds_ctpxk.getColumnModel().getColumn(0).setPreferredWidth(20);
            table_ds_ctpxk.getColumnModel().getColumn(1).setPreferredWidth(50);
            table_ds_ctpxk.getColumnModel().getColumn(2).setPreferredWidth(250);
            table_ds_ctpxk.getColumnModel().getColumn(3).setPreferredWidth(60);
            table_ds_ctpxk.getColumnModel().getColumn(4).setPreferredWidth(40);
            table_ds_ctpxk.getColumnModel().getColumn(5).setPreferredWidth(80);
            //? Set vị trí cho nội dung (căn giữa cho nội dung)
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            table_ds_ctpxk.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            table_ds_ctpxk.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            table_ds_ctpxk.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            table_ds_ctpxk.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
            table_ds_ctpxk.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
            table_ds_ctpxk.getTableHeader().setReorderingAllowed(false);
            table_ds_ctpxk.setRowHeight(40);
            table_ds_ctpxk.setEnabled(false);
            ds_ctpxk = new JScrollPane(table_ds_ctpxk);
            ds_ctpxk.setPreferredSize(new Dimension(730,300));
            ds_ctpxk.setBackground(Color.WHITE);
            main_middle.add(ds_ctpxk);

            main_bottom = new JPanel();
            // JLabel tong_tien, tong_tien_lb;
            tong_tien = new JLabel("Tổng tiền : ");
            tong_tien_lb = new JLabel();
            // JButton closeCTPXK;
            closeCTPXK = new JButton("Đóng");
            main_bottom.add(tong_tien); main_bottom.add(tong_tien_lb);
            main_bottom.add(closeCTPXK);
            main.add(main_top);
            main.add(main_middle);
            main.add(main_bottom);
            main.setLayout(new FlowLayout());
            main.setPreferredSize(new Dimension(750,430));
            setThongTinMain(maPhieuXuat);
            this.sanPhamBUS= new SanPhamBUS(Integer.valueOf(this.ma_kho_hang_lb.getText()));
            frame.add(header);
            frame.add(main);
            frame.setLayout(new FlowLayout());
            frame.setSize(800,550);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
      }
      public String toCurrency(int a) {
            DecimalFormat numberFormat = new DecimalFormat("###,### VNĐ");
            return  numberFormat.format(a);
      }
      public void setThongTinMain(int maPhieuXuat) {
            try {
                  Statement state = JDBCConfigure.getConnection().createStatement();
                  ResultSet result = state.executeQuery("SELECT * FROM `phieuxuatkho`, `khohang`, `taikhoan`, `nguoidung` WHERE phieuxuatkho.MaKhoHang = khohang.MaKhoHang and taikhoan.MaTaiKhoan = nguoidung.MaNguoiDung and taikhoan.MaKhoHang = khohang.MaKhoHang and phieuxuatkho.MaPhieu = " + maPhieuXuat);
                  while(result.next()) {
                        ma_phieu_lb.setText(String.valueOf(maPhieuXuat));
                        ma_kho_hang_lb.setText(String.valueOf(result.getInt("MaKhoHang")));
                        ten_kho_hang_lb.setText(result.getString("TenKhoHang"));
                        nguoi_xuat_lb.setText(result.getString("HoTen"));
                        ngay_xuat_kho_lb.setText(result.getString("NgayXuatKho"));

                        tong_tien_lb.setText(toCurrency(result.getInt("TongGiaTri")));
                  }
            } catch(SQLException e) {
                  System.err.println(e.getMessage());
            }

            //? Tạo bảng danh sách
            ArrayList<ChiTietPhieuXuatKhoDTO> chiTietPhieuXuatKhoList = chiTietPhieuXuatKhoBUS.getAll(maPhieuXuat);
            for(int i = 0; i < chiTietPhieuXuatKhoList.size(); i++) {
                  model_ds_ctpxk.addRow(new Object[]{
                      model_ds_ctpxk.getRowCount()+1,
                      chiTietPhieuXuatKhoList.get(i).getMaSanPham(),
                      sanPhamBUS.getById(chiTietPhieuXuatKhoList.get(i).getMaSanPham()).getTenSanPham(),
                      toCurrency(chiTietPhieuXuatKhoList.get(i).getDonGia()),
                      chiTietPhieuXuatKhoList.get(i).getSoLuong(),
                      toCurrency(chiTietPhieuXuatKhoList.get(i).getThanhTien()),
                  });
            }
      }
}
