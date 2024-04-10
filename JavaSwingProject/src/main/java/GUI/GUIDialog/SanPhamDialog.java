
package GUI.GUIDialog;

import BUS.LoaiSanPhamBUS;
import BUS.SanPhamBUS;
import DTO.ThongTinSanPham.SanPhamDTO;
import GUI.GUIPanel.GiaoDienLoaiSanPham;
import GUI.GUIPanel.GiaoDienSanPham;
import GUI.GUIThanhPhan.ComboBoxFormCustom;
import GUI.GUIThanhPhan.InputFormCustom;
import Others.CloundinaryServices;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;



public class SanPhamDialog extends  JDialog implements ActionListener{
    public String tieuDe,type;
    public InputFormCustom maSP ,tenSP, soLuong, thuongHieu, trongLuong, giaSP;
    public ComboBoxFormCustom xuatXu,tenLoai;
    private JLabel hinhAnh;
    private SanPhamDTO SPDuocChon;
    private GiaoDienSanPham SPGUI;
    
    private LoaiSanPhamBUS LoaiSPBUS= new LoaiSanPhamBUS();
    private SanPhamBUS SanPhamBUS= new SanPhamBUS();
    
    
    public SanPhamDialog(GiaoDienSanPham guiaa,String title,String type) {
        this.tieuDe=title;
        this.type=type;
        this.SPGUI=guiaa;
        this.init();
    }
    public SanPhamDialog(String title,String type, SanPhamDTO sanPhamDuocChon) {
        this.tieuDe=title;
        this.type=type;
        this.SPDuocChon=sanPhamDuocChon;
        this.init();

    }
    
    public void init(){
        this.setTitle("Sản Phẩm");
        this.setLayout(new BorderLayout(0,0));
        this.setSize(1050, 600);
        // Font chữ
        Font tieudeFont= new Font("Arial",Font.BOLD,35);
        Font nutNhanFont= new Font("Arial",Font.BOLD,15);
        // Tiêu đề cho dialog
        JPanel tieuDe= new JPanel(new BorderLayout(0,0));
        JLabel tieuDeTrang= new JLabel(this.tieuDe);
        tieuDeTrang.setForeground(Color.white);
        tieuDeTrang.setFont(tieudeFont);
        tieuDeTrang.setSize(800, 100);
        tieuDeTrang.setHorizontalAlignment(JLabel.CENTER);
        tieuDe.add(tieuDeTrang);
        
        // Phần giữa
        // trái là hình ảnh và nút thêm ảnh; Phải là các thông tin label panel
        JPanel noiDungGiua= new JPanel(new BorderLayout(50, 0));
        noiDungGiua.setPreferredSize(new Dimension(1050,400));
        
        JPanel themHinhAnh= new JPanel(new FlowLayout(FlowLayout.CENTER));
        themHinhAnh.setPreferredSize(new Dimension(400,400));
        

         hinhAnh= new JLabel();
        JButton nutHinhAnh= new JButton("Hình ảnh");
        hinhAnh.setBorder(new EmptyBorder(10,10,10,10));
        hinhAnh.setPreferredSize(new Dimension(350,350));
        hinhAnh.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(10, 10, 10, 10), // Đặt khoảng cách
                BorderFactory.createLineBorder(Color.BLACK) // Đặt màu viền
        ));
        nutHinhAnh.addActionListener(this);
        nutHinhAnh.setPreferredSize(new Dimension(150,40));
        nutHinhAnh.setBackground(Color.decode("#82CAFF"));
        nutHinhAnh.setForeground(Color.white);
        nutHinhAnh.setFont(nutNhanFont);
        themHinhAnh.add(hinhAnh,BorderLayout.CENTER);
        themHinhAnh.add(nutHinhAnh,BorderLayout.SOUTH);
        
       
        themHinhAnh.add(nutHinhAnh,BorderLayout.SOUTH);
        noiDungGiua.add(themHinhAnh,BorderLayout.WEST);
        
        // phải là các input cho sản phẩm
        JPanel thongTinSP=new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));//GridLayout(3,4,10,5)
        thongTinSP.setPreferredSize(new Dimension(650,600));
        String xuatxu[]= {"Tất Cả","Đan Mạch", "Nhật Bản"};
        maSP= new InputFormCustom("Mã Sản Phẩm:");
        tenSP= new InputFormCustom("Tên Sản Phẩm:");
        soLuong= new InputFormCustom("Số Lượng:");
        xuatXu= new ComboBoxFormCustom("Xuất Xứ:",xuatxu);
        //thuongHieu= new InputFormCustom("Thương Hiệu:");
        //trongLuong= new InputFormCustom("Trọng Lượng:");
        tenLoai= new ComboBoxFormCustom("Loại:",LoaiSPBUS.tenLoaiSanPham());
        giaSP= new InputFormCustom("Giá Sản Phẩm:");
        thongTinSP.add(maSP); thongTinSP.add(tenSP); thongTinSP.add(tenLoai); 
        thongTinSP.add(soLuong); thongTinSP.add(giaSP);thongTinSP.add(xuatXu); 
        //thongTinSP.add(thuongHieu); thongTinSP.add(trongLuong);
        noiDungGiua.add(thongTinSP,BorderLayout.CENTER);
        
        // Phần dưới
        // Nút thêm SP và hủy
        JPanel noiDungDuoi= new JPanel(new FlowLayout(FlowLayout.CENTER,50, 50));
        noiDungDuoi.setPreferredSize(new Dimension(1000,100));
        //noiDungDuoi.setBackground(Color.lightGray);
        JButton themSP, huySP, chinhsua;
        if (type.equals("Add"))
        {
            tieuDe.setBackground(Color.decode("#66FF33"));
            themSP= new JButton("Thêm SP Mới"); themSP.setPreferredSize(new Dimension(200,40));
            themSP.setOpaque(true);
            themSP.setFont(nutNhanFont);    
            themSP.setBackground(Color.decode("#66FF33"));
            noiDungDuoi.add(themSP);
            huySP= new JButton("Hủy Bỏ");
            themSP.addActionListener(this);
            themSP.setForeground(Color.white);
        }
        else {
            tieuDe.setBackground(Color.decode("#82CAFF"));
            themDuLieu();
            chinhsua= new JButton("Sửa Sản Phẩm"); chinhsua.setPreferredSize(new Dimension(200,40));
            chinhsua.setOpaque(true);
            chinhsua.setFont(nutNhanFont);    
            chinhsua.setBackground(Color.decode("#F88017"));
            noiDungDuoi.add(chinhsua);
            huySP= new JButton("Thoát");
            chinhsua.addActionListener(this);
            chinhsua.setForeground(Color.white);
            
        }
        huySP.setPreferredSize(new Dimension(200,40));
        huySP.setFont(nutNhanFont);
        huySP.setBackground(Color.decode("#CD0000"));
        huySP.setForeground(Color.white);
        noiDungDuoi.add(huySP);
        // xử lý sự kiện
        huySP.addActionListener(this);
                
                
        // thêm nội dung
        this.add(tieuDe,BorderLayout.NORTH);
        this.add(noiDungGiua,BorderLayout.CENTER);
        this.add(noiDungDuoi,BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
    public void themDuLieu(){
        URL img=null;
        try {
            img = new URL(CloundinaryServices.getUrlImage(this.SPDuocChon.getAnhMinhhoa()));
        } catch (MalformedURLException ex) {
            System.out.println("Lỗi ko load được ảnh từ SanPhamDialog");
        }
        ImageIcon pic= new ImageIcon(img);
        Image scaleImage = pic.getImage().getScaledInstance(320, 320,Image.SCALE_SMOOTH);
        this.hinhAnh.setIcon(new ImageIcon(scaleImage));
        this.maSP.getTxtForm().setText(this.SPDuocChon.getMaSanPham().toString());
        this.tenSP.getTxtForm().setText(this.SPDuocChon.getTenSanPham());
        this.tenLoai.getList().setSelectedIndex(this.SPDuocChon.getMaLoaiSanPham());
        this.soLuong.getTxtForm().setText(this.SPDuocChon.getSoLuongConLai().toString());
        this.giaSP.getTxtForm().setText(this.SPDuocChon.getGiaSanPham().toString());
        this.xuatXu.getList().setSelectedItem(this.SPDuocChon.getXuatXu());
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String lenh=ae.getActionCommand();
        switch(lenh){
            case "Hủy Bỏ": case "Thoát":
                this.dispose();
                break;
            case "Hình ảnh":
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose an Image");
                int userSelection = fileChooser.showOpenDialog(SanPhamDialog.this);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    ImageIcon icon = new ImageIcon(selectedFile+"");
                    Image scaleImage = icon.getImage().getScaledInstance(320, 320,Image.SCALE_SMOOTH);
                    hinhAnh.setIcon(new ImageIcon(scaleImage));
                }
                break;
            case "Thêm SP Mới":
                if( this.maSP.txtForm.getText().trim().equals("") ||
                    this.tenSP.txtForm.getText().trim().equals("") ||
                    this.xuatXu.list.getSelectedItem().toString().equals("Tất cả") ||
                    this.giaSP.txtForm.getText().trim().equals("") ||
                    this.soLuong.txtForm.getText().trim().equals(""))
                {
                   JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin cần thiết !!!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                   break;
                }
                int maSP=Integer.parseInt(this.maSP.txtForm.getText());
                String tenSP=this.tenSP.txtForm.getText();
                String xuatXu=this.xuatXu.list.getSelectedItem().toString();
                int giaSP=Integer.parseInt(this.giaSP.txtForm.getText());
                int soLuong=Integer.parseInt(this.soLuong.txtForm.getText());
                String anhMinhHoa=this.hinhAnh.getText();
                int maLoai=tenLoai.list.getSelectedIndex();
                int maKho=1;
                SanPhamDTO spMoi=new SanPhamDTO(maSP, tenSP, xuatXu, giaSP, soLuong, true, anhMinhHoa, maLoai, maKho);
                if(SanPhamBUS.create(spMoi)){
                    JOptionPane.showMessageDialog(this, "Thêm Sản Phẩm Mới Thành Công ^^","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    this.SPGUI.loadDuLieuTuDatabase(SanPhamBUS.getAll());
                    this.dispose();
                }
                else JOptionPane.showMessageDialog(this, "Lỗi, vui lòng kiểm tra lại!","Thông báo",JOptionPane.ERROR_MESSAGE);
                // ktra dữ liệu
                // thêm sản phẩm
                break;
            case "Sửa Sản Phẩm":
              if( this.maSP.txtForm.getText().trim().equals("") ||
                    this.tenSP.txtForm.getText().trim().equals("") ||
                    this.xuatXu.list.getSelectedItem().toString().equals("Tất cả") ||
                    this.giaSP.txtForm.getText().trim().equals("") ||
                    this.soLuong.txtForm.getText().trim().equals(""))
                {
                   JOptionPane.showMessageDialog(this, "Vui lòng không để trống !!!","Thông báo",JOptionPane.ERROR_MESSAGE);
                   break;
                }
                int maSP2=Integer.parseInt(this.maSP.txtForm.getText());
                String tenSP2=this.tenSP.txtForm.getText();
                String xuatXu2=this.xuatXu.list.getSelectedItem().toString();
                int giaSP2=Integer.parseInt(this.giaSP.txtForm.getText());
                int soLuong2=Integer.parseInt(this.soLuong.txtForm.getText());
                String anhMinhHoa2=this.hinhAnh.getText();
                int maLoai2=tenLoai.list.getSelectedIndex();
                int maKho2=1;
                SanPhamDTO spChinhSua=new SanPhamDTO(maSP2, tenSP2, xuatXu2, giaSP2, soLuong2, true, anhMinhHoa2, maLoai2, maKho2);
                if(SanPhamBUS.update(spChinhSua)){
                    JOptionPane.showMessageDialog(this, "Chỉnh sửa thành công ^^","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }else JOptionPane.showMessageDialog(this, "Lỗi, vui lòng kiểm tra lại!","Thông báo",JOptionPane.ERROR_MESSAGE);
              
               
        }

        
    }
}
