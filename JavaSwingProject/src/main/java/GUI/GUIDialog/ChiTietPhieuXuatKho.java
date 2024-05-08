package GUI.GUIDialog;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.*;

import BUS.NghiepVuXuatKho.ChiTietPhieuXuatKhoBUS;
import BUS.NghiepVuXuatKho.PhieuXuatKhoBUS;
import BUS.ThongTinSanPham.SanPhamBUS;
import DTO.NghiepVuNhapKho.ChiTietPhieuNhapKhoDTO;
import DTO.NghiepVuNhapKho.PhieuNhapKhoDTO;
import DTO.NghiepVuXuatKho.*;
import GUI.GUIThanhPhan.ButtonCustom;
import Others.JDBCConfigure;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChiTietPhieuXuatKho implements ActionListener{
      ChiTietPhieuXuatKhoBUS chiTietPhieuXuatKhoBUS;
      PhieuXuatKhoBUS phieuXuatKhoBUS;
      SanPhamBUS sanPhamBUS;
      int maPhieuXuat;
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
      JLabel trang_thai, trang_thai_lb;
      JPanel main_middle;
      DefaultTableModel model_ds_ctpxk;
      JTable table_ds_ctpxk;
      JScrollPane ds_ctpxk;
      JPanel main_bottom;
      JLabel tong_tien, tong_tien_lb;
      ButtonCustom closeCTPXK,xuatFileXuat;
      public ChiTietPhieuXuatKho(int maPhieuXuat) {
          this.maPhieuXuat=maPhieuXuat;
            chiTietPhieuXuatKhoBUS = new ChiTietPhieuXuatKhoBUS();
            phieuXuatKhoBUS = new PhieuXuatKhoBUS();
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

            //? JLabel trang_thai, trang_thai_lb;
            trang_thai = new JLabel("Trạng thái : ");
            trang_thai_lb = new JLabel();
            main_top.add(ma_phieu); main_top.add(ma_phieu_lb);
            main_top.add(ma_kho_hang); main_top.add(ma_kho_hang_lb);
            main_top.add(ten_kho_hang); main_top.add(ten_kho_hang_lb);
            main_top.add(nguoi_xuat); main_top.add(nguoi_xuat_lb);
            main_top.add(ngay_xuat_kho); main_top.add(ngay_xuat_kho_lb);
            main_top.add(trang_thai); main_top.add(trang_thai_lb);
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
            closeCTPXK = new ButtonCustom("Đóng","","#3eceff");
            closeCTPXK.addActionListener(this);
            closeCTPXK.setHorizontalAlignment(SwingConstants.CENTER);
            closeCTPXK.setForeground(Color.white);            
            main_bottom.add(tong_tien); main_bottom.add(tong_tien_lb);

            PhieuXuatKhoDTO phieuXuat = phieuXuatKhoBUS.getById(maPhieuXuat);
            if (phieuXuat.getTrangThai().equals("DaDuyet")){
                xuatFileXuat= new ButtonCustom("Xuất File","","#3eceff");
                xuatFileXuat.setHorizontalAlignment(SwingConstants.CENTER);
                xuatFileXuat.setForeground(Color.white);
                xuatFileXuat.addActionListener(this);
                main_bottom.add(xuatFileXuat);                   
            }                     
            
            main_bottom.add(closeCTPXK);
            main.add(main_top);
            main.add(main_middle);
            main.add(main_bottom);
            main.setLayout(new FlowLayout());
            main.setPreferredSize(new Dimension(750,480));
            setThongTinMain(maPhieuXuat);
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
      public void setThongTinMain(int maPhieuXuat) {
            PhieuXuatKhoDTO phieuXuat = phieuXuatKhoBUS.getById(maPhieuXuat);
            //? Set thông tin hiển thị
            ma_phieu_lb.setText(String.valueOf(maPhieuXuat));
            ma_kho_hang_lb.setText(String.valueOf(phieuXuat.getMaKhoHang()));
            ten_kho_hang_lb.setText(phieuXuatKhoBUS.getTenKhoHang(maPhieuXuat));
            nguoi_xuat_lb.setText(phieuXuatKhoBUS.getHoTenByMaPhieuXuat(maPhieuXuat));
            ngay_xuat_kho_lb.setText(String.valueOf(phieuXuat.getNgayXuatKho()));
            trang_thai_lb.setText(toStringTrangThai(phieuXuat.getTrangThai()));
            tong_tien_lb.setText(toCurrency(phieuXuat.getTongGiaTri()));
            this.sanPhamBUS= new SanPhamBUS(Integer.valueOf(this.ma_kho_hang_lb.getText()));
            //? Tạo bảng danh sách
            ArrayList<ChiTietPhieuXuatKhoDTO> chiTietPhieuXuatKhoList = chiTietPhieuXuatKhoBUS.getAll(maPhieuXuat);
            for(int i = 0; i < chiTietPhieuXuatKhoList.size(); i++) {
                  model_ds_ctpxk.addRow(new Object[]{
                      model_ds_ctpxk.getRowCount()+1,
                      chiTietPhieuXuatKhoList.get(i).getMaSanPham(),
                      sanPhamBUS.getById_ver2(chiTietPhieuXuatKhoList.get(i).getMaSanPham(),phieuXuat.getMaKhoHang()).getTenSanPham(),
                      toCurrency(chiTietPhieuXuatKhoList.get(i).getDonGia()),
                      chiTietPhieuXuatKhoList.get(i).getSoLuong(),
                      toCurrency(chiTietPhieuXuatKhoList.get(i).getThanhTien()),
                  });
            }
      }
      
      
 public void xuatFilePDFSanPham(int maPhieuXuat) {
    FileOutputStream file = null;
          try {
            com.itextpdf.text.Font fontChung = new com.itextpdf.text.Font(BaseFont.createFont("C:\\Users\\Admin\\OneDrive\\Documents\\NetBeansProjects\\JavaVeryNew\\JavaSwingProject\\src\\main\\java\\Others\\Font\\SVN-Times New Roman.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED),14);
            com.itextpdf.text.Font fontDam=new com.itextpdf.text.Font(BaseFont.createFont("C:\\Users\\Admin\\OneDrive\\Documents\\NetBeansProjects\\JavaVeryNew\\JavaSwingProject\\src\\main\\java\\Others\\Font\\SVN-Times New Roman Bold.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED),18);

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
            Paragraph tenPhieu = new Paragraph("THÔNG TIN PHIẾU XUẤT KHO",fontDam);
            tenPhieu.setAlignment(Element.ALIGN_CENTER);
            document.add(tenPhieu);
            
            Paragraph maPhieu = new Paragraph("Mã phiếu: "+maPhieuXuat,fontChung);
            Paragraph hoTenNguoiXuat = new Paragraph("Người xuất hàng: "+this.nguoi_xuat_lb.getText(),fontChung);
            Paragraph ngayXuatKho= new Paragraph("Ngày xuất hàng: "+UtilServices.convertToDate(this.ngay_xuat_kho_lb.getText()),fontChung);
            
            document.add(maPhieu);
            document.add(hoTenNguoiXuat);
            document.add(ngayXuatKho);
            
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
            ArrayList<ChiTietPhieuXuatKhoDTO> chiTietPhieuXuatKhoList = chiTietPhieuXuatKhoBUS.getAll(maPhieuXuat);
            PhieuXuatKhoDTO phieuXuat = phieuXuatKhoBUS.getById(maPhieuXuat);

            
         
            for (ChiTietPhieuXuatKhoDTO ctp : chiTietPhieuXuatKhoList) {
                danhsach.addCell(new PdfPCell(new Phrase(String.valueOf(i), fontChung)));                
                danhsach.addCell(new PdfPCell(new Phrase(String.valueOf(ctp.getMaSanPham()),fontChung)));
                danhsach.addCell(new PdfPCell(new Phrase(sanPhamBUS.getById_ver2(ctp.getMaSanPham(),phieuXuat.getMaKhoHang()).getTenSanPham(),fontChung)));
                danhsach.addCell(new PdfPCell(new Phrase(toCurrency(ctp.getDonGia()),fontChung)));                
                danhsach.addCell(new PdfPCell(new Phrase(String.valueOf(ctp.getSoLuong()),fontChung)));                
                danhsach.addCell(new PdfPCell(new Phrase(toCurrency(ctp.getThanhTien()),fontChung))); 
                i++;
            }
            document.add(danhsach);
            document.add(Chunk.NEWLINE);            
            document.add(Chunk.NEWLINE);              
            Paragraph tongTien = new Paragraph(new Phrase("Tổng tiền: "+toCurrency(phieuXuat.getTongGiaTri()),fontDam));       
            tongTien.setIndentationLeft(300);
          
            document.add(tongTien);      
            document.add(Chunk.NEWLINE);    
            document.add(Chunk.NEWLINE);     
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
            if(e.getSource() == closeCTPXK) {
                  frame.dispose();
            }else if ( e.getSource()==xuatFileXuat){
                xuatFilePDFSanPham(this.maPhieuXuat);
            }
      }
}