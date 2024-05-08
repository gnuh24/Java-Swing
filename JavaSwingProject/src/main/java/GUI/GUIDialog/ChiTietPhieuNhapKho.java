package GUI.GUIDialog;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.*;

import BUS.NghiepVuNhapKho.ChiTietPhieuNhapKhoBUS;
import BUS.NghiepVuNhapKho.PhieuNhapKhoBUS;
import BUS.ThongTinSanPham.SanPhamBUS;
import DTO.NghiepVuNhapKho.*;
import GUI.GUIThanhPhan.ButtonCustom;
import Others.UtilServices;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;

//
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ChiTietPhieuNhapKho implements ActionListener{
      ChiTietPhieuNhapKhoBUS chiTietPhieuNhapKhoBUS;
      PhieuNhapKhoBUS phieuNhapKhoBUS;
      SanPhamBUS sanPhamBUS;
      int maPhieuNhap;
      JFrame frame = new JFrame();
      JPanel header;
      JLabel header_lb;
      JPanel main;
      JPanel main_top;
      JLabel ma_phieu,ma_phieu_lb;
      JLabel ma_kho_hang, ma_kho_hang_lb;
      JLabel ten_kho_hang, ten_kho_hang_lb;
      JLabel nguoi_nhap, nguoi_nhap_lb;
      JLabel ngay_nhap_kho, ngay_nhap_kho_lb;
      JLabel trang_thai, trang_thai_lb;
      JLabel nha_cung_cap, nha_cung_cap_lb;
      JPanel main_middle;
      DefaultTableModel model_ds_ctpnk;
      JTable table_ds_ctpnk;
      JScrollPane ds_ctpnk;
      JPanel main_bottom;
      JLabel tong_tien, tong_tien_lb;
      
      ButtonCustom closeCTPNK, xuatFileNhap;
      public ChiTietPhieuNhapKho(int maPhieuNhap) {
          this.maPhieuNhap=maPhieuNhap;
            chiTietPhieuNhapKhoBUS = new ChiTietPhieuNhapKhoBUS();
            phieuNhapKhoBUS = new PhieuNhapKhoBUS();
            header = new JPanel();
            header_lb = new JLabel("CHI TIẾT PHIẾU NHẬP");
            header_lb.setForeground(Color.WHITE);
//            header_lb.setFont(new Font("Arial", Font.BOLD, 20));
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

            //? JLabel nguoi_nhap, nguoi_nhap_lb;
            nguoi_nhap = new JLabel("Tên người nhập : ");
            nguoi_nhap_lb = new JLabel();

            //? JLabel ngay_nhap_kho, ngay_nhap_kho_lb;
            ngay_nhap_kho = new JLabel("Ngày nhập kho : ");
            ngay_nhap_kho_lb = new JLabel();

            //? JLabel trang_thai, trang_thai_lb;
            trang_thai = new JLabel("Trạng thái : ");
            trang_thai_lb = new JLabel();

            //? JLabel nha_cung_cap, nha_cung_cap_lb;
            nha_cung_cap = new JLabel("Nhà cung cấp : ");
            nha_cung_cap_lb = new JLabel();
            
            main_top.add(ma_phieu); main_top.add(ma_phieu_lb);
            main_top.add(ma_kho_hang); main_top.add(ma_kho_hang_lb);
            main_top.add(ten_kho_hang); main_top.add(ten_kho_hang_lb);
            main_top.add(nguoi_nhap); main_top.add(nguoi_nhap_lb);
            main_top.add(ngay_nhap_kho); main_top.add(ngay_nhap_kho_lb);
            main_top.add(trang_thai); main_top.add(trang_thai_lb);
            main_top.setLayout(new GridLayout(3, 3));
            main_top.setPreferredSize(new Dimension(700,70));

            main_middle = new JPanel();
            String columns_ds_ctpnk[] = {"STT", "Mã sản phẩm","Tên sản phẩm", "Đơn giá", "Số lượng", "Thành tiền"};
            String data_ds_ctpnk[][] = {};
            model_ds_ctpnk = new DefaultTableModel(data_ds_ctpnk, columns_ds_ctpnk){
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
            table_ds_ctpnk = new JTable(model_ds_ctpnk);
            table_ds_ctpnk.getColumnModel().getColumn(0).setPreferredWidth(20);
            table_ds_ctpnk.getColumnModel().getColumn(1).setPreferredWidth(50);
            table_ds_ctpnk.getColumnModel().getColumn(2).setPreferredWidth(250);
            table_ds_ctpnk.getColumnModel().getColumn(3).setPreferredWidth(60);
            table_ds_ctpnk.getColumnModel().getColumn(4).setPreferredWidth(40);
            table_ds_ctpnk.getColumnModel().getColumn(5).setPreferredWidth(80);
            //? Set vị trí cho nội dung (căn giữa cho nội dung)
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            table_ds_ctpnk.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            table_ds_ctpnk.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
            table_ds_ctpnk.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            table_ds_ctpnk.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
            table_ds_ctpnk.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
            table_ds_ctpnk.getTableHeader().setReorderingAllowed(false);
            table_ds_ctpnk.setRowHeight(40);
            table_ds_ctpnk.setEnabled(false);
            ds_ctpnk = new JScrollPane(table_ds_ctpnk);
            ds_ctpnk.setPreferredSize(new Dimension(730,300));
            ds_ctpnk.setBackground(Color.WHITE);
            main_middle.add(ds_ctpnk);

            main_bottom = new JPanel(new FlowLayout(10));
            // JLabel tong_tien, tong_tien_lb;
            tong_tien = new JLabel("Tổng tiền : ");
            tong_tien_lb = new JLabel();
            // JButton closeCTPXK;
            closeCTPNK = new ButtonCustom("Đóng","","#3eceff");
            closeCTPNK.setHorizontalAlignment(SwingConstants.CENTER);
                closeCTPNK.setForeground(Color.white);

            closeCTPNK.addActionListener(this);
            main_bottom.add(tong_tien); main_bottom.add(tong_tien_lb);
            PhieuNhapKhoDTO phieuNhap = phieuNhapKhoBUS.getPhieuNhapKhoByMaPhieu(maPhieuNhap);           
            if (phieuNhap.getTrangThai().equals("DaDuyet")){
                xuatFileNhap= new ButtonCustom("Xuất File","","#3eceff");
                xuatFileNhap.setHorizontalAlignment(SwingConstants.CENTER);
                xuatFileNhap.setForeground(Color.white);
                xuatFileNhap.addActionListener(this);
                main_bottom.add(xuatFileNhap);                   
            }            
            
            main_bottom.add(closeCTPNK);
            main.add(main_top);
            main.add(nha_cung_cap);
            main.add(nha_cung_cap_lb);
            main.add(main_middle);
            main.add(main_bottom);
            main.setLayout(new FlowLayout());
            main.setPreferredSize(new Dimension(750,480));
            setThongTinMain(maPhieuNhap);
            this.sanPhamBUS= new SanPhamBUS(Integer.valueOf(this.ma_kho_hang_lb.getText()));

            frame.add(header);
            frame.add(main);
            frame.setLayout(new FlowLayout());
            frame.setSize(800,600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
      }
      public String toCurrency(int a) {
            DecimalFormat numberFormat = new DecimalFormat("###,### VNĐ");
            return  numberFormat.format(a);
      }
      public String toCurrency(long a) {
            DecimalFormat numberFormat = new DecimalFormat("###,### VNĐ");
            return  numberFormat.format(a);
      }
      public String toStringTrangThai(String trangThai) {
            if(trangThai.equals("DaDuyet")) {
                  return "Đã duyệt";
            } else if(trangThai.equals("ChoDuyet")) {
                  return "Chờ duyệt";
            } else {
                  return "Hủy";
            }
      }
      public void setThongTinMain(int maPhieuNhap) {
            PhieuNhapKhoDTO phieuNhap = phieuNhapKhoBUS.getPhieuNhapKhoByMaPhieu(maPhieuNhap);
            //? Set thông tin hiển thị
            ma_phieu_lb.setText(String.valueOf(maPhieuNhap));
            ma_kho_hang_lb.setText(String.valueOf(phieuNhap.getMaKhoHang()));
            ten_kho_hang_lb.setText(phieuNhapKhoBUS.getTenKhoHang(maPhieuNhap));
            nguoi_nhap_lb.setText(phieuNhapKhoBUS.getHoTenByMaPhieuNhap(maPhieuNhap));
            nha_cung_cap_lb.setText(phieuNhapKhoBUS.getTenNhaCungCap(maPhieuNhap));
            ngay_nhap_kho_lb.setText(String.valueOf(phieuNhap.getNgayNhapKho()));
            trang_thai_lb.setText(toStringTrangThai(phieuNhap.getTrangThai()));
            tong_tien_lb.setText(toCurrency(phieuNhap.getTongGiaTri()));
            this.sanPhamBUS= new SanPhamBUS(Integer.valueOf(this.ma_kho_hang_lb.getText()));
            //? Tạo bảng danh sách
            ArrayList<ChiTietPhieuNhapKhoDTO> chiTietPhieuNhapKhoList = chiTietPhieuNhapKhoBUS.getChiTietPhieuNhapKhoByMaPhieu(maPhieuNhap);
            for(int i = 0; i < chiTietPhieuNhapKhoList.size(); i++) {
                model_ds_ctpnk.addRow(new Object[]{
                    model_ds_ctpnk.getRowCount()+1,
                    chiTietPhieuNhapKhoList.get(i).getMaSanPham(),
                    sanPhamBUS.getById_ver2(chiTietPhieuNhapKhoList.get(i).getMaSanPham(),phieuNhap.getMaKhoHang()).getTenSanPham(),
                    toCurrency(chiTietPhieuNhapKhoList.get(i).getDonGia()),
                    chiTietPhieuNhapKhoList.get(i).getSoLuong(),
                    toCurrency(chiTietPhieuNhapKhoList.get(i).getThanhTien()),
                });
            }
      }
      
public void xuatFilePDFSanPham(int maPhieuNhap) {
    FileOutputStream file = null;
          try {
            Font fontChung = new Font(BaseFont.createFont("C:\\Users\\Admin\\OneDrive\\Documents\\NetBeansProjects\\JavaVeryNew\\Font\\SVN-Times New Roman.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED),14);

              // chọn file
              JFileChooser chonFile = new JFileChooser();
              chonFile.setDialogTitle("Chọn thư mục tạo file");
              int chonThuMuc = chonFile.showSaveDialog(null);
              String filePath="";
              if (chonThuMuc == JFileChooser.APPROVE_OPTION) {
                  File chonTenFile = chonFile.getSelectedFile();
                  filePath = chonTenFile.getAbsolutePath();
                  
                  if (!filePath.toLowerCase().endsWith(".pdf"))
                      filePath += ".pdf";
              }         // tạo pdf
              System.out.println("Link file:"+ filePath);
              if (filePath!=""){
            file = new FileOutputStream(filePath);
              Document document = new Document();
              PdfWriter writer = PdfWriter.getInstance(document, file);
              
              document.open();
              
              // thông tin phiếu: kho hàng, hoten, ncc, ngày
            Paragraph tenkho = new Paragraph(this.ten_kho_hang_lb.getText(),fontChung);
            tenkho.setAlignment(Element.ALIGN_RIGHT);
            document.add(tenkho);
            document.add(Chunk.NEWLINE);
            Paragraph tenPhieu = new Paragraph("THÔNG TIN PHIẾU NHẬP KHO",fontChung);
            tenPhieu.setAlignment(Element.ALIGN_CENTER);
            document.add(tenPhieu);
            
            Paragraph maPhieu = new Paragraph("Mã phiếu: "+maPhieuNhap,fontChung);
            Paragraph hoTenNguoiNhap = new Paragraph("Người nhập kho: "+this.nguoi_nhap_lb.getText(),fontChung);
            Paragraph nhaCungCap = new Paragraph("Nhà cung cấp: "+this.nha_cung_cap_lb.getText(),fontChung);
            Paragraph ngayNhapKho= new Paragraph("Ngày nhập kho: "+UtilServices.convertToDate(this.ngay_nhap_kho_lb.getText()),fontChung);
            
            document.add(maPhieu);
            document.add(hoTenNguoiNhap);
            document.add(nhaCungCap);
            document.add(ngayNhapKho);
            
            document.add(Chunk.NEWLINE);
            // danh sách sản phẩm
             PdfPTable danhsach = new PdfPTable(6);           
             danhsach.setWidthPercentage(100);
            danhsach.setWidths(new float[]{7f, 12f, 40f, 20f, 12f,32f});
            PdfPCell cell;           
            
            // tạo các ô để điền thông tin
            danhsach.addCell(new PdfPCell(new Phrase("STT",fontChung)));            
            danhsach.addCell(new PdfPCell(new Phrase("Mã sản phẩm",fontChung)));            
            danhsach.addCell(new PdfPCell(new Phrase("Tên sản phẩm",fontChung)));
            danhsach.addCell(new PdfPCell(new Phrase("Đơn giá",fontChung)));
            danhsach.addCell(new PdfPCell(new Phrase("Số lượng",fontChung)));
            danhsach.addCell(new PdfPCell(new Phrase("Thành tiền tiền",fontChung)));
            
            //  tạo các ô chứa nội dung sản phẩm
            for (int i = 0; i < 6; i++) {
                cell = new PdfPCell(new Phrase(""));
                danhsach.addCell(cell);
            }            
            
            //Truyen thong tin tung chi tiet vao table
            int i=1;
             ArrayList<ChiTietPhieuNhapKhoDTO> chiTietPhieuNhapKhoList = chiTietPhieuNhapKhoBUS.getChiTietPhieuNhapKhoByMaPhieu(maPhieuNhap);
            PhieuNhapKhoDTO phieuNhap = phieuNhapKhoBUS.getPhieuNhapKhoByMaPhieu(maPhieuNhap);
            for (ChiTietPhieuNhapKhoDTO ctp : chiTietPhieuNhapKhoList) {
                danhsach.addCell(new PdfPCell(new Phrase(String.valueOf(i), fontChung)));                
                danhsach.addCell(new PdfPCell(new Phrase(String.valueOf(ctp.getMaSanPham()),fontChung)));
                danhsach.addCell(new PdfPCell(new Phrase(sanPhamBUS.getById_ver2(ctp.getMaSanPham(),phieuNhap.getMaKhoHang()).getTenSanPham(),fontChung)));
                danhsach.addCell(new PdfPCell(new Phrase(toCurrency(ctp.getDonGia()),fontChung)));                
                danhsach.addCell(new PdfPCell(new Phrase(String.valueOf(ctp.getSoLuong()),fontChung)));                
                danhsach.addCell(new PdfPCell(new Phrase(toCurrency(ctp.getThanhTien()),fontChung))); 
                i++;
            }
            document.add(danhsach);
            document.add(Chunk.NEWLINE);            
            
            Paragraph tongTien = new Paragraph(new Phrase("Tổng tiền: "+toCurrency(phieuNhap.getTongGiaTri()),fontChung));       
            tongTien.setIndentationLeft(300);
          
            document.add(tongTien);      
            document.add(Chunk.NEWLINE);       
            Paragraph paragraph = new Paragraph();
            paragraph.setIndentationLeft(22);
            paragraph.add(new Chunk("Người lập phiếu",fontChung));
            paragraph.add(new Chunk("                                          "));
            paragraph.add(new Chunk("Nhân viên nhận",fontChung));           

            Paragraph sign = new Paragraph();
            sign.setIndentationLeft(23);
            sign.add(new Chunk("(Ký và ghi rõ họ tên)",fontChung));
            sign.add(new Chunk("                                          "));
            sign.add(new Chunk("(Ký và ghi rõ họ tên)",fontChung));
            sign.add(new Chunk("                                          "));
            document.add(paragraph);
            document.add(sign);
            document.close();
            writer.close();          
            
            JOptionPane.showMessageDialog(frame, "Xuất file thành công ^^");          
                  frame.dispose();            
            
              }
              
          } catch (FileNotFoundException ex) {
              System.out.println("Ko tìm thấy file");
          } catch (DocumentException ex) {
              System.out.println("Ko tìm thấy tài liệu");          
          } catch (IOException ex) {
              System.out.println("Ko mở dc font");
          } finally {
              try {
                  file.close();
              } catch (IOException ex) {
                  Logger.getLogger(ChiTietPhieuNhapKho.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
}    
      
      public void actionPerformed(ActionEvent e) {
            if(e.getSource() == closeCTPNK) {
                  frame.dispose();
            }else if (e.getSource()== xuatFileNhap){
                xuatFilePDFSanPham(maPhieuNhap);
            }
      }
}