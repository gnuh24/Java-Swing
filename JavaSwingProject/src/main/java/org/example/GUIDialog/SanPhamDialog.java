/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.GUIDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import org.example.GUIThanhPhan.ComboBoxFormCustom;
import org.example.GUIThanhPhan.InputFormCustom;
/**
 *
 * @author Admin
 */
public class SanPhamDialog extends  JDialog implements ActionListener{
    public String tieuDe,type;
    public int dong;
    public JTable thongTin;
    public InputFormCustom maSP ,tenSP, soLuong, thuongHieu, trongLuong, giaSP;
    public ComboBoxFormCustom xuatXu,tenLoai;
    private JLabel hinhAnh;
    public SanPhamDialog(String title,String type) {
        this.tieuDe=title;
        this.type=type;
        this.init();
    }
    public SanPhamDialog(String title,String type, JTable thongtin, int dong) {
        this.tieuDe=title;
        this.type=type;
        this.thongTin=thongtin;
        this.dong=dong;
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
        hinhAnh.setPreferredSize(new Dimension(400,350));
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
        String xuatxu[]= {"Việt Nam","Nhật Bản","Hàn Quốc","Trung Quốc","Mỹ","Singapore"};
        String loai[]={"Loại A","Loại B","Loại C","Loại D","Loại E"};
        maSP= new InputFormCustom("Mã Sản Phẩm:");
        tenSP= new InputFormCustom("Tên Sản Phẩm:");
        soLuong= new InputFormCustom("Số Lượng:");
        xuatXu= new ComboBoxFormCustom("Xuất Xứ:",xuatxu);
        thuongHieu= new InputFormCustom("Thương Hiệu:");
        trongLuong= new InputFormCustom("Trọng Lượng:");
        tenLoai= new ComboBoxFormCustom("Loại:",loai);
        giaSP= new InputFormCustom("Giá Sản Phẩm:");
        thongTinSP.add(maSP); thongTinSP.add(tenSP); thongTinSP.add(tenLoai); 
        thongTinSP.add(soLuong); thongTinSP.add(giaSP);thongTinSP.add(xuatXu); 
        thongTinSP.add(thuongHieu); thongTinSP.add(trongLuong);
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
        maSP.getTxtForm().setText((thongTin.getValueAt(dong, 2)).toString());
        tenSP.getTxtForm().setText((thongTin.getValueAt(dong, 3)).toString());
        soLuong.getTxtForm().setText((thongTin.getValueAt(dong, 4)).toString());
        thuongHieu.getTxtForm().setText((thongTin.getValueAt(dong, 5)).toString());
        trongLuong.getTxtForm().setText((thongTin.getValueAt(dong, 6)).toString());
        xuatXu.getList().setSelectedItem(thongTin.getValueAt(dong, 7).toString());   
        giaSP.getTxtForm().setText((thongTin.getValueAt(dong, 8)).toString());
        //tenLoai.getTxtForm().setText((thongTin.getValueAt(dong, 2)).toString());
        
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Hủy Bỏ") || ae.getActionCommand().equals("Thoát"))
            this.dispose();
        if( ae.getActionCommand().equals("Hình ảnh")){
            JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose an Image");
                int userSelection = fileChooser.showOpenDialog(SanPhamDialog.this);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    ImageIcon icon = new ImageIcon(selectedFile+"");
                    Image scaleImage = icon.getImage().getScaledInstance(hinhAnh.getWidth()-10, hinhAnh.getHeight()-10,Image.SCALE_SMOOTH);
                    hinhAnh.setIcon(new ImageIcon(scaleImage));
                }
            }
        }
    }
