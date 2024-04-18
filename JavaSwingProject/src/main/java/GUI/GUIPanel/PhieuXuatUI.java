package GUI.GUIPanel;


import BUS.NghiepVuXuatKho.ChiTietPhieuXuatKhoBUS;
import BUS.NghiepVuXuatKho.PhieuXuatKhoBUS;
import DTO.NghiepVuXuatKho.PhieuXuatKhoDTO;


import java.time.LocalDateTime;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PhieuXuatUI extends JPanel implements ActionListener{
      PhieuXuatKhoBUS phieuXuatKhoBUS = new PhieuXuatKhoBUS();
      ChiTietPhieuXuatKhoBUS chiTietPhieuXuatKhoBUS = new ChiTietPhieuXuatKhoBUS();
      JPanel top;
            JPanel chucNangPanel;
                  JButton xoa_btn;
                  JButton sua_btn;
                  JButton chi_tiet_btn;
                  JButton xuatExcel_btn;
            JPanel timKiemPanel;
                  JTextField tim_kiem_tf;
                  JButton tim_kiem_refesh;
      JPanel bot;
            DefaultTableModel model_ds_xuat_hang;
            JTable table_ds_xuat_hang;
            JScrollPane ds_xuat_hang;
      public PhieuXuatUI() {
            top = new JPanel();
                  chucNangPanel = new JPanel();
                        xoa_btn = new JButton("Xóa");
                        xoa_btn.addActionListener(this);
                        sua_btn = new JButton("Sửa");
                        sua_btn.addActionListener(this);
                        chi_tiet_btn = new JButton("Chi tiết");
                        chi_tiet_btn.addActionListener(this);
                  chucNangPanel.setBorder(new CompoundBorder(new TitledBorder("Chức năng"), new EmptyBorder(4, 4, 4, 4)));
                  chucNangPanel.add(customButtonOption(xoa_btn,"xoa_btn.png"));
                  chucNangPanel.add(customButtonOption(sua_btn,"sua_btn.png"));
                  chucNangPanel.add(customButtonOption(chi_tiet_btn,"chi_tiet.png"));


                  timKiemPanel = new JPanel();
                        tim_kiem_tf = new JTextField(30);
                        tim_kiem_tf.setPreferredSize(new Dimension(30,35));
                        tim_kiem_refesh = new JButton("Làm mới");
                        tim_kiem_refesh.addActionListener(this);//!  Chú ý
                  timKiemPanel.setBorder(new CompoundBorder(new TitledBorder("Tìm Kiếm"), new EmptyBorder(4, 4, 4, 4)));
                  timKiemPanel.add(tim_kiem_tf);
                  timKiemPanel.add(tim_kiem_refesh);
            top.setPreferredSize(new Dimension(1200,100));
            top.add(chucNangPanel);
            top.add(timKiemPanel);

            bot = new JPanel();
                  
                  String columns_ds_xuat_hang[] = {"Mã phiếu xuất", "Mã kho hàng", "Ngày xuất kho","Tổng giá trị"};
                  String data_ds_xuat_hang[][] = {};
                  model_ds_xuat_hang = new DefaultTableModel(data_ds_xuat_hang, columns_ds_xuat_hang){
                        @Override
                        public Class<?> getColumnClass(int columnIndex) {
                              // Đặt kiểu dữ liệu cho từng cột
                              if (columnIndex == 0) {
                                    return Integer.class; // Kiểu dữ liệu cho cột 0 là Integer
                              } else if (columnIndex == 1 ) {
                                    return String.class; // Kiểu dữ liệu cho cột 1 và 2 là String
                              } else if (columnIndex == 2) {
                                    return LocalDateTime.class; // Giả sử kiểu dữ liệu cho cột 3 là Date
                              } else if (columnIndex == 3) {
                                    return Integer.class; // Kiểu dữ liệu cho cột 4 là Double
                              } else {
                                    return Object.class; // Hoặc có thể trả về kiểu Object làm mặc định
                              }
                        }
                    };
                  table_ds_xuat_hang = new JTable(model_ds_xuat_hang);
                  table_ds_xuat_hang.getColumnModel().getColumn(0).setPreferredWidth(100);
                  table_ds_xuat_hang.getColumnModel().getColumn(1).setPreferredWidth(100);
                  table_ds_xuat_hang.getColumnModel().getColumn(2).setPreferredWidth(200);
                  table_ds_xuat_hang.getColumnModel().getColumn(3).setPreferredWidth(200);
                  //? Set vị trí cho nội dung (căn giữa cho nội dung)
                  DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                  centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                  table_ds_xuat_hang.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                  table_ds_xuat_hang.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                  table_ds_xuat_hang.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                  table_ds_xuat_hang.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                  table_ds_xuat_hang.setRowHeight(40);
                  showDanhSachPhieuXuatHang();
                  ds_xuat_hang = new JScrollPane(table_ds_xuat_hang);
                  ds_xuat_hang.setPreferredSize(new Dimension(900, 600));
                  ds_xuat_hang.setBackground(Color.WHITE);

            bot.setPreferredSize(new Dimension(1200,700));
            bot.add(ds_xuat_hang);

            add(top);
            add(bot);
            setPreferredSize(new Dimension(1300,800));
            setLayout(new FlowLayout());
            setVisible(true);
            showDanhSachPhieuXuatHang();
      }
      public JButton customButtonOption(JButton button, String linkIMG) {
            BoxLayout boxlayout = new BoxLayout(button, BoxLayout.Y_AXIS);
            button.setLayout(boxlayout);
            button.setFont(new Font("Arial", Font.BOLD, 14)); // Thiết lập font
            button.setForeground(Color.BLACK); // Thiết lập màu chữ
            button.setBackground(Color.WHITE); // Thiết lập màu nền
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE,1)); // Thiết lập viền
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            // Tùy chỉnh kích thước
            Dimension size = button.getPreferredSize();
            size.width = 90;
            size.height = 40;
            button.setPreferredSize(size);

            ImageIcon icon = new ImageIcon("Java-Swing\\JavaSwingProject\\src\\main\\java\\org\\example\\GUI\\IMG\\" + linkIMG); 
            button.setIcon(new ImageIcon(icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));

            return button;
      }
      public String toCurrency(long a) {
            DecimalFormat numberFormat = new DecimalFormat("###,###.## đ");
            return  numberFormat.format(a);
      }
      @Override
      public void actionPerformed(ActionEvent e) {
            if(e.getSource() == xoa_btn) {
                  xoaChiTietPhieuXuatHang();
            } else if(e.getSource() == sua_btn) {
                  suaChiTietPhieuXuatHang();
            } else if(e.getSource() == chi_tiet_btn) {
                  xemChiTietPhieuXuatHang();
            } else if(e.getSource() == tim_kiem_refesh){

            }
      }
      public void showDanhSachPhieuXuatHang() {
           PhieuXuatKhoBUS phieuXuatKhoBUS = new PhieuXuatKhoBUS();
           ArrayList<PhieuXuatKhoDTO> phieuXuatKhoList = phieuXuatKhoBUS.getAll(4);
           //? Xóa bảng danh sách sản phẩm xuất hàng
           for (int i = model_ds_xuat_hang.getRowCount() - 1; i >= 0; i--) {
                 model_ds_xuat_hang.removeRow(i);
           }
           for (PhieuXuatKhoDTO phieuXuatKho : phieuXuatKhoList) {
                 model_ds_xuat_hang.addRow(new Object[]{
                             phieuXuatKho.getMaPhieu(),
                             phieuXuatKho.getMaKhoHang(),
                             String.valueOf(phieuXuatKho.getNgayXuatKho()).replace("T"," "),
                             toCurrency(phieuXuatKho.getTongGiaTri())
                 });
           }
      }
      public void xoaChiTietPhieuXuatHang() {
            int selectedRow = table_ds_xuat_hang.getSelectedRow();
            //? kiểm tra xem có dòng nào đang được chọn không
            if(selectedRow != -1) {
                  int maPhieuXuat = (Integer) table_ds_xuat_hang.getValueAt(selectedRow, 0);
                  PhieuXuatKhoDTO temp = phieuXuatKhoBUS.getById(maPhieuXuat);
                  phieuXuatKhoBUS.delete(temp);
                  showDanhSachPhieuXuatHang();
            } else {
                  JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 phiếu xuất hàng!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
            }
      }
      public void suaChiTietPhieuXuatHang() {
            int selectedRow = table_ds_xuat_hang.getSelectedRow();
            //? kiểm tra xem có dòng nào đang được chọn không
            if(selectedRow != -1) {
                  int maPhieuXuat = (Integer) table_ds_xuat_hang.getValueAt(selectedRow, 0);
                  new SuaChiTietPhieuXuatKho(maPhieuXuat);
            } else {
                  JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 phiếu xuất hàng!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
            }
      }
      public void xemChiTietPhieuXuatHang() {
            int selectedRow = table_ds_xuat_hang.getSelectedRow();
            //? kiểm tra xem có dòng nào đang được chọn không
            if(selectedRow != -1) {
                  int maPhieuXuat = (Integer) table_ds_xuat_hang.getValueAt(selectedRow, 0);
                  new ChiTietPhieuXuatKho(maPhieuXuat);
            } else {
                  JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 phiếu xuất hàng!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
            }
      }
}
