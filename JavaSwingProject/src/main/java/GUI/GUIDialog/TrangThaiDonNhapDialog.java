/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.GUIDialog;

import BUS.NghiepVuNhapKho.ChiTietPhieuNhapKhoBUS;
import BUS.NghiepVuNhapKho.PhieuNhapKhoBUS;
import BUS.ThongTinSanPham.SanPhamBUS;
import DTO.NghiepVuNhapKho.ChiTietPhieuNhapKhoDTO;
import DTO.NghiepVuNhapKho.PhieuNhapKhoDTO;
import GUI.GUIPanel.PhieuNhapUI;
import GUI.GUIThanhPhan.ButtonCustom;
import Others.JDBCConfigure;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class TrangThaiDonNhapDialog extends  JDialog implements ActionListener{
    ChiTietPhieuNhapKhoBUS chiTietPhieuNhapKhoBUS = new ChiTietPhieuNhapKhoBUS();
    SanPhamBUS sanPhamBUS;
    JPanel header;
    JLabel header_lb;
    JPanel main;
    JPanel main_top;
    JLabel ma_phieu,ma_phieu_lb;
    JLabel ma_kho_hang, ma_kho_hang_lb;
    JLabel ten_kho_hang, ten_kho_hang_lb;
    JLabel nguoi_nhap, nguoi_nhap_lb;
    JLabel ngay_nhap_kho, ngay_nhap_kho_lb;
    JLabel trang_thai;
    JComboBox trang_thai_cb;
    JPanel main_middle;
    DefaultTableModel model_ds_ctpxk;
    JTable table_ds_ctpnk;
    JScrollPane ds_ctpnk;
    JPanel main_bottom;
    JLabel tong_tien, tong_tien_lb;
    ButtonCustom capnhat_btn;
    PhieuNhapUI phieu;
    
    public TrangThaiDonNhapDialog(PhieuNhapUI phieu,int maPhieuNhap) {
        this.phieu=phieu;
        header = new JPanel();
        header_lb = new JLabel("THÔNG TIN PHIẾU");
        header_lb.setForeground(Color.WHITE);
        header_lb.setFont(new Font("Arial", Font.BOLD, 20));
        header.setBackground(Color.BLACK);
        header.setPreferredSize(new Dimension(800,35));
        header.add(header_lb);

        main = new JPanel();
        main_top = new JPanel();

        ma_phieu = new JLabel("Mã phiếu : ");
        ma_phieu_lb = new JLabel();

        ma_kho_hang = new JLabel("Mã kho hàng : ");
        ma_kho_hang_lb = new JLabel();

        ten_kho_hang = new JLabel("Tên kho hàng : ");
        ten_kho_hang_lb = new JLabel();

        nguoi_nhap = new JLabel("Tên người xuất : ");
        nguoi_nhap_lb = new JLabel();

        ngay_nhap_kho = new JLabel("Ngày xuất kho : ");
        ngay_nhap_kho_lb = new JLabel();

        trang_thai = new JLabel("Trạng thái đơn: ");
        String tt[]={"Chờ Duyệt","Đã Duyệt","Hủy"};
        trang_thai_cb =new JComboBox<>(tt);
        main_top.add(ma_phieu); main_top.add(ma_phieu_lb);
        main_top.add(ma_kho_hang); main_top.add(ma_kho_hang_lb);
        main_top.add(ten_kho_hang); main_top.add(ten_kho_hang_lb);
        main_top.add(nguoi_nhap); main_top.add(nguoi_nhap_lb);
        main_top.add(ngay_nhap_kho); main_top.add(ngay_nhap_kho_lb);
        main_top.add(trang_thai); main_top.add(trang_thai_cb);
        
        main_top.setLayout(new GridLayout(3, 3));
        main_top.setPreferredSize(new Dimension(600,70));

        main_middle = new JPanel();
        String columns_ds_ctpxk[] = {"STT", "Tên sản phẩm", "Đơn giá", "Số lượng", "Thành tiền"};
        String data_ds_ctpxk[][] = {};
        model_ds_ctpxk = new DefaultTableModel(data_ds_ctpxk, columns_ds_ctpxk){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0 || columnIndex == 1 || columnIndex == 3)
                    return Integer.class;
                else if (columnIndex == 2 || columnIndex == 4 || columnIndex == 5)
                    return String.class;
                else
                    return Object.class;
            }
        };
        table_ds_ctpnk = new JTable(model_ds_ctpxk);
        table_ds_ctpnk.getColumnModel().getColumn(0).setPreferredWidth(20);
        table_ds_ctpnk.getColumnModel().getColumn(1).setPreferredWidth(250);
        table_ds_ctpnk.getColumnModel().getColumn(2).setPreferredWidth(70);
        table_ds_ctpnk.getColumnModel().getColumn(3).setPreferredWidth(40);
        table_ds_ctpnk.getColumnModel().getColumn(4).setPreferredWidth(70);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table_ds_ctpnk.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_ds_ctpnk.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table_ds_ctpnk.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table_ds_ctpnk.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table_ds_ctpnk.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table_ds_ctpnk.getTableHeader().setReorderingAllowed(false);
        table_ds_ctpnk.setRowHeight(40);
        table_ds_ctpnk.setEnabled(false);
        ds_ctpnk = new JScrollPane(table_ds_ctpnk);
        ds_ctpnk.setPreferredSize(new Dimension(730,300));
        ds_ctpnk.setBackground(Color.WHITE);
        main_middle.add(ds_ctpnk);

        main_bottom = new JPanel();
        tong_tien = new JLabel("Tổng tiền : ");
        tong_tien_lb = new JLabel();
        main_bottom.add(tong_tien); main_bottom.add(tong_tien_lb);
        capnhat_btn= new ButtonCustom("Cập Nhật","","#3eceff");
        capnhat_btn.setSize(180, 50);
        capnhat_btn.setHorizontalAlignment(SwingConstants.CENTER);
        main_bottom.add(capnhat_btn);
        main.add(main_top);
        main.add(main_middle);
        main.add(main_bottom);

        main.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        main.setPreferredSize(new Dimension(800,480));
        setThongTinMain(maPhieuNhap);

        capnhat_btn.addActionListener(this);
        
        this.add(header);
        this.add(main);
        
        this.setLayout(new FlowLayout());
        this.setSize(800,550);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public String toCurrency(int a) {
        DecimalFormat numberFormat = new DecimalFormat("###,### VNĐ");
        return  numberFormat.format(a);
    }

    public String toCurrency(Long a) {
        DecimalFormat numberFormat = new DecimalFormat("###,### VNĐ");
        return  numberFormat.format(a);
    }

    public void setThongTinMain(int maPhieuNhap) {
        try {
            Statement state = JDBCConfigure.getConnection().createStatement();
            ResultSet result = state.executeQuery("SELECT * FROM `phieunhapkho`, `khohang`, `taikhoan` WHERE phieunhapkho.MaKhoHang = khohang.MaKhoHang  and taikhoan.MaKhoHang = khohang.MaKhoHang and phieunhapkho.MaPhieu = " + maPhieuNhap);
            while(result.next()) {

                ma_phieu_lb.setText(String.valueOf(maPhieuNhap));
                ma_kho_hang_lb.setText(String.valueOf(result.getInt("MaKhoHang")));
                ten_kho_hang_lb.setText(result.getString("TenKhoHang"));
                nguoi_nhap_lb.setText(result.getString("HoTen"));
                ngay_nhap_kho_lb.setText(result.getString("NgayNhapKho"));
                tong_tien_lb.setText(String.valueOf(toCurrency(result.getLong("TongGiaTri"))));
            }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }

        this.sanPhamBUS = new SanPhamBUS(Integer.parseInt(this.ma_kho_hang_lb.getText()));
        ArrayList<ChiTietPhieuNhapKhoDTO> chiTietPhieuNhapKhoDTOS = chiTietPhieuNhapKhoBUS.getChiTietPhieuNhapKhoByMaPhieu(maPhieuNhap);

        for(int i = 0; i < chiTietPhieuNhapKhoDTOS.size(); i++) {
            model_ds_ctpxk.addRow(new Object[]{
                model_ds_ctpxk.getRowCount()+1,
                sanPhamBUS.getById(chiTietPhieuNhapKhoDTOS.get(i).getMaSanPham()).getTenSanPham(),
                toCurrency(chiTietPhieuNhapKhoDTOS.get(i).getDonGia()),
                chiTietPhieuNhapKhoDTOS.get(i).getSoLuong(),
                toCurrency(chiTietPhieuNhapKhoDTOS.get(i).getThanhTien()),
            });
        }
    }  

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== capnhat_btn) {
            if(trang_thai_cb.getSelectedItem().toString().equals("Đã Duyệt")){
                PhieuNhapKhoBUS a= new PhieuNhapKhoBUS();
                PhieuNhapKhoDTO phieu= a.getPhieuNhapKhoByMaPhieu(Integer.parseInt(ma_phieu_lb.getText()));
                phieu.setTrangThai("DaDuyet");
                a.updatePhieuNhapKho(phieu);
                JOptionPane.showMessageDialog(this, "Thay đổi trạng thái thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                this.phieu.showDanhSachPhieuNhapHang((ArrayList<PhieuNhapKhoDTO>) a.getAllPhieuNhapKho(Integer.parseInt(ma_kho_hang_lb.getText())));
                this.dispose();

            }
            else if (trang_thai_cb.getSelectedItem().toString().equals("Hủy")){
                int kq = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn hủy bỏ phiếu nhập ?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(kq==JOptionPane.YES_OPTION){
                PhieuNhapKhoBUS a= new PhieuNhapKhoBUS();
                PhieuNhapKhoDTO phieu= a.getPhieuNhapKhoByMaPhieu(Integer.parseInt(ma_phieu_lb.getText()));
                phieu.setTrangThai("Huy");
                a.updatePhieuNhapKho(phieu);   
                JOptionPane.showMessageDialog(this, "Thay đổi trạng thái thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                this.phieu.showDanhSachPhieuNhapHang((ArrayList<PhieuNhapKhoDTO>) a.getAllPhieuNhapKho(Integer.parseInt(ma_kho_hang_lb.getText())));
                this.dispose();
                }
            }
            
        }
    }
}
