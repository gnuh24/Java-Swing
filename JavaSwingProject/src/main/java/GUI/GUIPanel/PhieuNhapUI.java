package GUI.GUIPanel;

import BUS.NghiepVuNhapKho.ChiTietPhieuNhapKhoBUS;
import BUS.NghiepVuNhapKho.NhaCungCapBUS;
import BUS.NghiepVuNhapKho.PhieuNhapKhoBUS;
import DTO.NghiepVuNhapKho.PhieuNhapKhoDTO;

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

      NhaCungCapBUS nhaCungCapBUS = new NhaCungCapBUS();

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
            DefaultTableModel modelDSPhieuNhapKho;
            JTable tableDSPhieuNhapKho;
            JScrollPane dsPhieuNhapKho;
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
                  
                  String columns_ds_xuat_hang[] = {"Mã phiếu xuất", "Ngày xuất kho","Nhà cung cấp" ,"Tổng giá trị", };
                  String data_ds_xuat_hang[][] = {};
                  modelDSPhieuNhapKho = new DefaultTableModel(data_ds_xuat_hang, columns_ds_xuat_hang){
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
                  tableDSPhieuNhapKho = new JTable(modelDSPhieuNhapKho);
                  tableDSPhieuNhapKho.getColumnModel().getColumn(0).setPreferredWidth(100);
                  tableDSPhieuNhapKho.getColumnModel().getColumn(1).setPreferredWidth(100);
                  tableDSPhieuNhapKho.getColumnModel().getColumn(2).setPreferredWidth(200);
                  tableDSPhieuNhapKho.getColumnModel().getColumn(3).setPreferredWidth(200);

            //? Set vị trí cho nội dung (căn giữa cho nội dung)
                  DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                  centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                  tableDSPhieuNhapKho.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                  tableDSPhieuNhapKho.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
                  tableDSPhieuNhapKho.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                  tableDSPhieuNhapKho.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

                  tableDSPhieuNhapKho.setRowHeight(40);
                  // showDanhSachPhieuXuatHang(phieuXuatKhoBUS.getAll(maKhoHang));
                  dsPhieuNhapKho = new JScrollPane(tableDSPhieuNhapKho);
                  dsPhieuNhapKho.setPreferredSize(new Dimension(900, 600));
                  dsPhieuNhapKho.setBackground(Color.WHITE);

            bot.setPreferredSize(new Dimension(1200,700));
            bot.add(dsPhieuNhapKho);

            add(top);
            add(bot);
            setPreferredSize(new Dimension(1300,800));
            setLayout(new FlowLayout());
            setVisible(true);
            showDanhSachPhieuNhapHang((ArrayList<PhieuNhapKhoDTO>) phieuNhapKhoBUS.getAllPhieuNhapKho(maKhoHang));
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
                  showDanhSachPhieuNhapHang((ArrayList<PhieuNhapKhoDTO>) phieuNhapKhoBUS.getAllPhieuNhapKho(maKhoHang));
            } else if(e.getSource() == chi_tiet_btn) {
                  xemChiTietPhieuNhapHang();
            }
      }

      public void showDanhSachPhieuNhapHang(ArrayList<PhieuNhapKhoDTO> phieuNhapKhoDTOS) {
            //? Xóa bảng danh sách sản phẩm xuất hàng
            for (int i = modelDSPhieuNhapKho.getRowCount() - 1; i >= 0; i--) {
                  modelDSPhieuNhapKho.removeRow(i);
            }
            for (PhieuNhapKhoDTO phieuNhapKhoDTO : phieuNhapKhoDTOS) {
                  modelDSPhieuNhapKho.addRow(new Object[]{
                      phieuNhapKhoDTO.getMaPhieu(),
                      String.valueOf(phieuNhapKhoDTO.getNgayNhapKho()).replace("T"," "),
                      nhaCungCapBUS.getNhaCungCapById(phieuNhapKhoDTO.getMaNCC()).getTenNCC(),
                      toCurrency(phieuNhapKhoDTO.getTongGiaTri())
                  });
            }
      }

      public void xemChiTietPhieuNhapHang() {
            int selectedRow = tableDSPhieuNhapKho.getSelectedRow();
            //? kiểm tra xem có dòng nào đang được chọn không
            if(selectedRow != -1) {
                  int maPhieuXuat = (Integer) tableDSPhieuNhapKho.getValueAt(selectedRow, 0);
                  new ChiTietPhieuNhapKho(maPhieuXuat);
            } else {
                  System.out.println("1b");
                  JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 phiếu nhập hàng!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
            }
      }
}
