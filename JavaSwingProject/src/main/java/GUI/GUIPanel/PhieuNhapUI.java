package GUI.GUIPanel;

import BUS.NghiepVuNhapKho.ChiTietPhieuNhapKhoBUS;
import BUS.NghiepVuNhapKho.PhieuNhapKhoBUS;
import DTO.NghiepVuNhapKho.PhieuNhapKhoDTO;
import DTO.NghiepVuXuatKho.PhieuXuatKhoDTO;

import java.time.LocalDate;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PhieuNhapUI extends JPanel implements ActionListener{
      int maKhoHang = 0;
      PhieuNhapKhoBUS phieuNhapKhoBUS = new PhieuNhapKhoBUS();
      ChiTietPhieuNhapKhoBUS chiTietPhieuNhapKhoBUS = new ChiTietPhieuNhapKhoBUS();
      JPanel top;
            JPanel chucNangPanel;
                  JButton xoa_btn;
                  JButton sua_btn;
                  JButton chi_tiet_btn;
                  JButton xuatExcel_btn;
            JPanel timKiemPanel;
                  JLabel tim_kiem;
                  JComboBox tim_kiem_cb;
            JButton refeshButton;
      JPanel bot;
            DefaultTableModel model_ds_xuat_hang;
            JTable table_ds_xuat_hang;
            JScrollPane ds_xuat_hang;
      public PhieuNhapUI(int maKhoHang) {
            this.maKhoHang = maKhoHang;
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
                        tim_kiem = new JLabel("Tổng giá trị : ");
                        String price[] = {"tất cả","dưới 5,000,000 VNĐ", "5,000,000 VNĐ - 10,000,000 VNĐ", "10,000,000 VNĐ - 20,000,000 VNĐ","20,000,000 VNĐ - 40,000,000 VNĐ", "lớn hơn 40,000,000 VNĐ"};
 
                        tim_kiem_cb = new JComboBox<>(price);
                          tim_kiem_cb.addItemListener(new ItemListener() {
                              public void itemStateChanged(ItemEvent e) {
                                  if (e.getStateChange() == ItemEvent.SELECTED) {
                                    // ArrayList<PhieuXuatKhoDTO> listPhieuXuatKho=phieuXuatKhoBUS.search(tim_kiem_cb.getSelectedIndex());
                                    // showDanhSachPhieuXuatHang(listPhieuXuatKho);
                                  }
                              }
                          });
                  timKiemPanel.setBorder(new CompoundBorder(new TitledBorder("Tìm Kiếm"), new EmptyBorder(4, 4, 4, 4)));
                  timKiemPanel.add(tim_kiem);
                  timKiemPanel.add(tim_kiem_cb);
                  
                  refeshButton = new JButton("Tải lại");
                  refeshButton.setIcon(new ImageIcon("C:\\Users\\dvmv2\\OneDrive\\Documents\\Nam_2\\Fixx\\Java-Swing-main-29-2\\Java-Swing-main\\JavaSwingProject\\src\\main\\java\\Resources\\refesh.png"));
                  refeshButton.setPreferredSize(new Dimension(110,40));
                  refeshButton.addActionListener(this);
            top.setPreferredSize(new Dimension(1200,100));
            top.add(chucNangPanel);
            top.add(timKiemPanel);
            top.add(refeshButton);

            bot = new JPanel();
                  
                  String columns_ds_xuat_hang[] = {"Mã phiếu xuất", "Ngày xuất kho","Tổng giá trị"};
                  String data_ds_xuat_hang[][] = {};
                  model_ds_xuat_hang = new DefaultTableModel(data_ds_xuat_hang, columns_ds_xuat_hang){
                        @Override
                        public Class<?> getColumnClass(int columnIndex) {
                              // Đặt kiểu dữ liệu cho từng cột
                              if (columnIndex == 0) {
                                    return Integer.class; // Kiểu dữ liệu cho cột 0 là Integer
                              } else if (columnIndex == 1) {
                                    return LocalDate.class; // Giả sử kiểu dữ liệu cho cột 3 là Date
                              } else if (columnIndex == 2) {
                                    return Integer.class; // Kiểu dữ liệu cho cột 4 là Double
                              } else {
                                    return Object.class; // Hoặc có thể trả về kiểu Object làm mặc định
                              }
                        }
                    };
                  table_ds_xuat_hang = new JTable(model_ds_xuat_hang);
                  table_ds_xuat_hang.getColumnModel().getColumn(0).setPreferredWidth(100);
                  table_ds_xuat_hang.getColumnModel().getColumn(1).setPreferredWidth(200);
                  table_ds_xuat_hang.getColumnModel().getColumn(2).setPreferredWidth(200);
                  //? Set vị trí cho nội dung (căn giữa cho nội dung)
                  DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                  centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                  table_ds_xuat_hang.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                  table_ds_xuat_hang.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                  table_ds_xuat_hang.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                  table_ds_xuat_hang.setRowHeight(40);
                  // showDanhSachPhieuXuatHang(phieuXuatKhoBUS.getAll(maKhoHang));
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
            // showDanhSachPhieuXuatHang(phieuXuatKhoBUS.getAll(maKhoHang));
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
            DecimalFormat numberFormat = new DecimalFormat("###,###.## VNĐ");
            return  numberFormat.format(a);
      }
      @Override
      public void actionPerformed(ActionEvent e) {
            if(e.getSource() == refeshButton) {
                  //Reset ds
            }
      }
}
