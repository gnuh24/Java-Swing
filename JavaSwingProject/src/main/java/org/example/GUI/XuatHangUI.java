package org.example.GUI;

import org.example.DTO.*;
import org.example.BUS.*;
import org.example.Configure.JDBCConfigure;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class XuatHangUI extends JPanel implements ActionListener{
      XuatHang_BUS XuatHang_BUS = new XuatHang_BUS();
      JPanel main;
            //? Main left
            JPanel main_left;
                  JPanel tim_kiem;
                        JTextField tim_kiem_tf;
                        JButton tim_kiem_refesh;
                  DefaultTableModel model_ds_san_pham;
                  JTable table_ds_san_pham;
                  JScrollPane ds_san_pham;

                  JPanel them_sp;
                        JLabel them_sp_lb;
                        JTextField them_sp_number;
                        JButton them_sp_confirm;
                  //? Main right
                  JPanel main_right;
                        JPanel thong_tin;
                              JPanel ma_phieu_nhap_pn;
                                    JLabel ma_phieu_nhap;
                                    JTextField ma_phieu_nhap_tf;

                              JPanel nguoi_tao_phieu_pn;
                                    JLabel nguoi_tao_phieu;
                                    JTextField nguoi_tao_phieu_tf;

                        DefaultTableModel model_ds_xuat_hang;
                        JTable table_ds_xuat_hang;
                        JScrollPane ds_xuat_hang;
                  
                        JPanel chuc_nang_pn;
                              JButton xoa_sp;
                              JButton sua_sl_sp;
                        JPanel thanh_tien_pn;
                              JLabel thanh_tien_lb;
                              JLabel thanh_tien_total;
                              JButton thanh_tien_xac_nhan;


      Color bgBlue = new Color(0,145,253);
      public XuatHangUI() {
            
            main = new JPanel();
            main.setPreferredSize(new Dimension(1300, 800));
            main.setLayout(new FlowLayout());
                        main_left = new JPanel();
                        main_left.setPreferredSize(new Dimension(600,800));
                        main_left.setBackground(Color.WHITE);
                              tim_kiem = new JPanel();
                              tim_kiem.setPreferredSize(new Dimension(550, 70));
                              tim_kiem.setBorder(new CompoundBorder(new TitledBorder("Tìm Kiếm"), new EmptyBorder(4, 4, 4, 4)));
                              tim_kiem.setBackground(Color.WHITE);
                                    tim_kiem_tf = new JTextField(30);
                                    tim_kiem_tf.setPreferredSize(new Dimension(100, 35));
                                    tim_kiem_refesh = new JButton("Làm mới");
                                    tim_kiem_refesh.addActionListener(this);
                              tim_kiem.add(tim_kiem_tf);
                              tim_kiem.add(customButtonMain(tim_kiem_refesh,100,35));

                              String columns_ds_san_pham[] = {"Mã máy", "Tên máy", "Số lượng", "Đơn giá"};
                              String data_ds_san_pham[][] = {};
                              model_ds_san_pham = new DefaultTableModel(data_ds_san_pham, columns_ds_san_pham){
                                    @Override
                                    public Class<?> getColumnClass(int columnIndex) {
                                          // Đặt kiểu dữ liệu cho từng cột
                                          if (columnIndex == 0) {
                                                return Integer.class; // Kiểu dữ liệu cho cột 0 là Integer
                                          } else if (columnIndex == 1) {
                                                return String.class; // Kiểu dữ liệu cho cột 1 là String
                                          } else if (columnIndex == 2) {
                                                return Integer.class; // Giả sử kiểu dữ liệu cho cột 2 là Integer
                                          } else if (columnIndex == 3) {
                                                return String.class; // Kiểu dữ liệu cho cột 3 là Double
                                          } else {
                                                return Object.class; // Hoặc có thể trả về kiểu Object làm mặc định
                                          }
                                    }
                                };
                              table_ds_san_pham = new JTable(model_ds_san_pham);
                              table_ds_san_pham.getColumnModel().getColumn(0).setPreferredWidth(25);
                              table_ds_san_pham.getColumnModel().getColumn(1).setPreferredWidth(200);
                              table_ds_san_pham.getColumnModel().getColumn(2).setPreferredWidth(20);
                              table_ds_san_pham.getColumnModel().getColumn(3).setPreferredWidth(100);
                              //? Set vị trí cho nội dung (căn giữa cho nội dung)
                              DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                              centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                              table_ds_san_pham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                              table_ds_san_pham.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                              table_ds_san_pham.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                              table_ds_san_pham.getTableHeader().setReorderingAllowed(false);
                              ds_san_pham = new JScrollPane(table_ds_san_pham);
                              ds_san_pham.setPreferredSize(new Dimension(550, 600));
                              ds_san_pham.setBackground(Color.WHITE);
                              showDanhSachSanPham();

                              them_sp = new JPanel();
                              them_sp.setBackground(Color.WHITE);
                                    them_sp_lb = new JLabel("Số lượng : ");
                                    them_sp_lb.setFont(new Font("Arial", Font.BOLD, 16));

                                    them_sp_number = new JTextField(5);
                                    them_sp_number.setText("");
                                    them_sp_number.setPreferredSize(new Dimension(100, 35));
                                    them_sp_number.setHorizontalAlignment(SwingConstants.CENTER);
                                    
                                    them_sp_confirm = new JButton("Thêm");
                                    them_sp_confirm.addActionListener(this);
                                    them_sp_confirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                              them_sp.setPreferredSize(new Dimension(550, 50));
                              them_sp.add(them_sp_lb);
                              them_sp.add(them_sp_number);
                              them_sp.add(customButtonMain(them_sp_confirm,100,35));

                        main_left.add(tim_kiem);
                        main_left.add(ds_san_pham);
                        main_left.add(them_sp);

                        //? --------------------------------------------

                        main_right = new JPanel();
                        main_right.setBackground(Color.WHITE);
                        main_right.setPreferredSize(new Dimension(600,800));
                              thong_tin = new JPanel();
                              thong_tin.setLayout(new BoxLayout(thong_tin, BoxLayout.Y_AXIS));
                                    ma_phieu_nhap_pn = new JPanel();
                                          ma_phieu_nhap = new JLabel("Mã phiếu nhập : ");
                                          ma_phieu_nhap_tf = new JTextField(30);
                                          ma_phieu_nhap_tf.setPreferredSize(new Dimension(500, 35));
                                          ma_phieu_nhap_tf.setEditable(false);
                                    ma_phieu_nhap_pn.setPreferredSize(new Dimension(550,50));
                                    ma_phieu_nhap_pn.add(ma_phieu_nhap);
                                    ma_phieu_nhap_pn.add(ma_phieu_nhap_tf);

                                    nguoi_tao_phieu_pn = new JPanel();
                                          nguoi_tao_phieu = new JLabel("Người tạo phiếu : ");
                                          nguoi_tao_phieu_tf = new JTextField("Admin",30);
                                          nguoi_tao_phieu_tf.setPreferredSize(new Dimension(50, 35));
                                          nguoi_tao_phieu_tf.setEditable(false);
                                    nguoi_tao_phieu_pn.setPreferredSize(new Dimension(550,50));
                                    nguoi_tao_phieu_pn.add(nguoi_tao_phieu);
                                    nguoi_tao_phieu_pn.add(nguoi_tao_phieu_tf);
                              thong_tin.add(ma_phieu_nhap_pn);
                              thong_tin.add(nguoi_tao_phieu_pn);

                              String columns_xuat_hang[] = {"STT", "Mã SP", "Tên SP","Số lượng", "Đơn giá"};
                              String data_xuat_hang[][] = {};
                              model_ds_xuat_hang = new DefaultTableModel(data_xuat_hang, columns_xuat_hang){
                                    @Override
                                    public Class<?> getColumnClass(int columnIndex) {
                                          // Đặt kiểu dữ liệu cho từng cột
                                          if (columnIndex == 0) {
                                                return Integer.class; // Kiểu dữ liệu cho cột 0 là Double
                                          } else if (columnIndex == 1) {
                                                return String.class; // Kiểu dữ liệu cho cột 1 là String
                                          } else if (columnIndex == 2) {
                                                return Integer.class; // Giả sử kiểu dữ liệu cho cột 2 là Double
                                          } else if (columnIndex == 3) {
                                                return String.class; // Kiểu dữ liệu cho cột 4 là String
                                          } else {
                                                return Object.class; // Hoặc có thể trả về kiểu Object làm mặc định
                                          }
                                    }
                                };
                              table_ds_xuat_hang = new JTable(model_ds_xuat_hang);
                              table_ds_xuat_hang.getColumnModel().getColumn(0).setPreferredWidth(15);
                              table_ds_xuat_hang.getColumnModel().getColumn(1).setPreferredWidth(25);
                              table_ds_xuat_hang.getColumnModel().getColumn(2).setPreferredWidth(150);
                              table_ds_xuat_hang.getColumnModel().getColumn(3).setPreferredWidth(20);
                              table_ds_xuat_hang.getColumnModel().getColumn(4).setPreferredWidth(100);
                              //? Set vị trí cho nội dung (căn giữa cho nội dung)
                              
                              table_ds_xuat_hang.getTableHeader().setReorderingAllowed(false);
                              ds_xuat_hang = new JScrollPane(table_ds_xuat_hang);
                              ds_xuat_hang.setPreferredSize(new Dimension(550, 550));
                        
                              chuc_nang_pn = new JPanel();
                              chuc_nang_pn.setPreferredSize(new Dimension(550,40));
                                    xoa_sp = new JButton("Xóa sản phẩm");
                                    xoa_sp.addActionListener(this);
                                    sua_sl_sp = new JButton("Sửa số lượng");
                                    sua_sl_sp.addActionListener(this);
                              chuc_nang_pn.add(customButtonMain(xoa_sp,150,35));
                              chuc_nang_pn.add(customButtonMain(sua_sl_sp,150,35));

                              thanh_tien_pn = new JPanel();
                                          thanh_tien_lb = new JLabel("Tổng tiền : ");
                                          thanh_tien_lb.setFont(new Font("Arial", Font.BOLD, 16));
                                          thanh_tien_lb.setPreferredSize(new Dimension(130, 35));
                                          thanh_tien_total = new JLabel("0đ");
                                          thanh_tien_total.setFont(new Font("Arial", Font.BOLD, 18));
                                          thanh_tien_total.setPreferredSize(new Dimension(150, 35));
                                          thanh_tien_total.setForeground(Color.RED);
                                          thanh_tien_xac_nhan = new JButton("Xuất hàng");
                                          thanh_tien_xac_nhan.addActionListener(this);
                                          thanh_tien_xac_nhan = customButtonMain(thanh_tien_xac_nhan,130,35);
                                          thanh_tien_xac_nhan.setPreferredSize(new Dimension(130, 35));
                              thanh_tien_pn.setPreferredSize(new Dimension(550, 50));
                              thanh_tien_pn.setLayout(new FlowLayout(FlowLayout.CENTER));
                              thanh_tien_pn.add(thanh_tien_lb);
                              thanh_tien_pn.add(thanh_tien_total);
                              thanh_tien_pn.add(thanh_tien_xac_nhan);
                        main_right.add(thong_tin);
                        main_right.add(ds_xuat_hang);
                        main_right.add(chuc_nang_pn);
                        main_right.add(thanh_tien_pn);
                        
                  add(main_left);
                  add(main_right);
                  setPreferredSize(new Dimension(1300,800));
                  setLayout(new FlowLayout());
                  setVisible(true);
      }
      public JButton customButtonMain(JButton button, int width, int height) {
            button.setFont(new Font("Arial", Font.BOLD, 14)); // Thiết lập font
            button.setForeground(Color.WHITE); // Thiết lập màu chữ
            button.setBackground(bgBlue); // Thiết lập màu nền
            button.setBorder(BorderFactory.createLineBorder(Color.GRAY,1)); // Thiết lập viền
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            Dimension size = button.getPreferredSize();
            size.width = width;
            size.height = height;
            button.setPreferredSize(size);
            return button;
      }
      public String toCurrency(double a) {
            DecimalFormat numberFormat = new DecimalFormat("###,### đ");
            return  numberFormat.format(a);
      }
      public Double currencyBack(String a) {
            return Double.parseDouble(a.replaceAll("[^0-9]", ""));
      }
      public Integer getIntegerValueAt(JTable data, int rowIndex, int columnIndex) {
            Object value = data.getValueAt(rowIndex, columnIndex); // Giả sử data là một mảng 2 chiều chứa dữ liệu của bảng
            if (value instanceof Integer) {
                return (Integer) value;
            } else if (value instanceof String) {
                // Cố gắng chuyển đổi từ kiểu String sang kiểu Integer
                try {
                    return Integer.parseInt((String) value);
                } catch (NumberFormatException e) {
                    // Xử lý ngoại lệ nếu không thể chuyển đổi
                    System.out.println("bat loi");
                    return null; // hoặc trả về giá trị mặc định khác tùy thuộc vào nhu cầu
                }
            } else {
                System.out.println("loi khac");
                return null;
            }
        }
        
      public Double getDoubleValueAt(JTable data, int rowIndex, int columnIndex) {
            Object value = data.getValueAt(rowIndex, columnIndex); // Giả sử data là một mảng 2 chiều chứa dữ liệu của bảng
            if (value instanceof Double) {
                return ((Double) value).doubleValue();
            } else if (value instanceof Integer) {
                  // Xử lý khi value là kiểu int
                  return (double) ((Integer) value);
            } else if (value instanceof String) {
                // Cố gắng chuyển đổi từ kiểu String sang kiểu Double
                try {
                    return Double.parseDouble((String) value);
                } catch (NumberFormatException e) {
                      // Xử lý ngoại lệ nếu không thể chuyển đổi
                  System.out.println("bat loi");
                  return null; // hoặc trả về giá trị mặc định khác tùy thuộc vào nhu cầu
                }
            } else {
                  System.out.println("loi khac");
                  return null;
            }
      }
      
      public void xuLyTongGiaTri() {
            //? Tính và hiển hiển thị tổng giá trị phiếu xuất hàng
            if(table_ds_xuat_hang.getRowCount() != -1) {
                  int sum = 0;
                  for(int i= 0; i < table_ds_xuat_hang.getRowCount(); i++) {
                        sum += getDoubleValueAt(table_ds_xuat_hang,i ,3) * currencyBack(String.valueOf(table_ds_xuat_hang.getValueAt(i,4)));
                  }
                  thanh_tien_total.setText(toCurrency(sum));
            }
      }
      
      @Override
      public void actionPerformed(ActionEvent e) {
            if(e.getSource() == them_sp_confirm) {
                  themSanPhamPhieuXuatHang();
            } else if(e.getSource() == xoa_sp) {
                  xoaSanPhamPhieuXuatHang();
            } else if(e.getSource() == sua_sl_sp) {
                  suaSanPhamPhieuXuatHang();
            } else if(e.getSource() == thanh_tien_xac_nhan) {
                  taoPhieuXuatHang();
            }else {
                  System.out.println("Bam 1 nut nao do");
            }
      }
      public void showDanhSachSanPham() {
            List<SanPham_DTO> sanPhamList = XuatHang_BUS.getDanhSachSanPham();
            for (SanPham_DTO sanPham : sanPhamList) {
                  model_ds_san_pham.addRow(new Object[]{
                              sanPham.getMaSanPham(),
                              sanPham.getTenSanPham(),
                              sanPham.getSoLuongConLai(),
                              toCurrency(sanPham.getGiaSanPham())
                  });
            }
      }
      public void themSanPhamPhieuXuatHang() {
            //? Check number
            String text = them_sp_number.getText();
            if (!text.matches("^\\d+$")) {
                  //? Check kí tự có hợp lệ hay không
                  JOptionPane.showMessageDialog(null, "Chỉ được nhập số!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
                  them_sp_number.setText("");
            } else if(Integer.valueOf(them_sp_number.getText())  <= 0) {
                  //? Check xem số lượng cần thêm có phải số âm hoặc bằng 0 hay không 
                  JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
                  them_sp_number.setText("");
            } else {
                  int selectedRow = table_ds_san_pham.getSelectedRow();
                  if(selectedRow != -1) {
                        //? Đếm số lượng đã có trong phiếu xuất hàng
                        int tongSoLuong = 0;
                        boolean check = false;
                        int i;
                        for(i= 0; i < table_ds_xuat_hang.getRowCount(); i++) {
                              if(table_ds_san_pham.getValueAt(selectedRow,0) == table_ds_xuat_hang.getValueAt(i, 1)){
                                    tongSoLuong += getIntegerValueAt(table_ds_xuat_hang, i, 3);
                                    check = true;
                                    break;
                              } 
                        }
                        tongSoLuong += Integer.valueOf(them_sp_number.getText());
                        if(getIntegerValueAt(table_ds_san_pham, selectedRow, 2) >= tongSoLuong) {
                              if(check == true) {
                                    model_ds_xuat_hang.setValueAt(tongSoLuong, i, 3);
                                    xuLyTongGiaTri();
                                    them_sp_number.setText("");
                              } else {
                                    //? Thêm dữ liệu vào bảng xuất hàng
                                    model_ds_xuat_hang.addRow(new Object[]{
                                          model_ds_xuat_hang.getRowCount()+1,
                                          table_ds_san_pham.getValueAt(selectedRow,0),
                                          table_ds_san_pham.getValueAt(selectedRow,1),
                                          Integer.parseInt(them_sp_number.getText()),
                                          table_ds_san_pham.getValueAt(selectedRow,3)
                                    });
                                    xuLyTongGiaTri();
                                    them_sp_number.setText("");
                              }
                        } else {
                              System.out.println(getIntegerValueAt(table_ds_san_pham, selectedRow, 2));
                              JOptionPane.showMessageDialog(null, "Vượt quá số lượng trong kho!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
                              them_sp_number.setText("");
                        }
                        
                        //? Check số lượng có hợp lệ hay không
                        
                  } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 sản phẩm!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
                        them_sp_number.setText("");
                  }
            }
      }
      public void xoaSanPhamPhieuXuatHang() {
            //? Dòng đang chọn
            int selectedRow = table_ds_xuat_hang.getSelectedRow();
            if(selectedRow != -1) {
                  int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                  if (choice == JOptionPane.YES_OPTION) {
                        //? chọn YES
                        model_ds_xuat_hang.removeRow(selectedRow);
                  }
                  xuLyTongGiaTri();
            } else {
                  JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 sản phẩm trong bảng xuất hàng!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
            }
      }
      public void suaSanPhamPhieuXuatHang() {
            int selectedRow = table_ds_xuat_hang.getSelectedRow();
                  if(selectedRow != -1) {
                        String text = JOptionPane.showInputDialog(null,"Nhập số lượng","Sửa số lượng",JOptionPane.PLAIN_MESSAGE);
                        if(text!= null) {
                              if (!text.matches("^\\d+$")) {
                                    //? Check kí tự có hợp lệ hay không
                                    JOptionPane.showMessageDialog(null, "Chỉ được nhập số!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
                              } else if(Integer.valueOf(text) <= 0) {
                                    //? Check xem số lượng cần thêm có phải số âm hoặc bằng 0 hay không 
                                    JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
                              } else {
                                    model_ds_xuat_hang.setValueAt(Integer.valueOf(text), selectedRow, 3);
                                    xuLyTongGiaTri();
                              }
                        }
                  } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 sản phẩm trong bảng xuất hàng!","Cảnh báo", JOptionPane.ERROR_MESSAGE);
                  }
      }
      public void taoPhieuXuatHang() {

      }
}
