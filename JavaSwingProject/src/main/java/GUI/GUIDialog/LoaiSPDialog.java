/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.GUI.GUIDialog;
import javax.swing.*;

import org.example.GUI.GUIThanhPhan.ButtonCustom;
import org.example.GUI.GUIThanhPhan.InputFormCustom;

import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Admin
 */
public class LoaiSPDialog extends  JDialog implements ActionListener{
    public String tieuDe,type; // type để set cho nút thêm hoặc xóa
    public InputFormCustom maLoaiSP, tenLoaiSP, maKho;
    public ButtonCustom btn_them,btn_chinhsua,btn_huy;
    public JTable thongtin;
    public LoaiSPDialog() {
        this.init();
    }

    public LoaiSPDialog(String tieuDe, String type) {
        this.tieuDe=tieuDe;
        this.type=type;
        init();
    }
    public LoaiSPDialog(String tieuDe, String type, JTable thongTin) {
        this.tieuDe=tieuDe;
        this.type=type;
        this.thongtin=thongTin;
        init();
    }
    
    public void init(){
        this.setLayout(new BorderLayout(20,20));
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        
        this.setTitle(this.tieuDe);
        JPanel tieuDePanel= new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        tieuDePanel.setBackground(Color.WHITE);
        JLabel tieuDeLabel= new JLabel("Thông Tin Loại Sản Phẩm");
        Font tieuDeFont= new Font("Arial",Font.BOLD,30);
        tieuDeLabel.setFont(tieuDeFont);
        tieuDePanel.add(tieuDeLabel);
        JPanel thongTin= new JPanel(new  FlowLayout(FlowLayout.CENTER,20,20));
        this.maLoaiSP= new InputFormCustom("Mã Loại Sản Phẩm:");
        this.tenLoaiSP=new InputFormCustom("Tên Loại Sản Phẩm:");
        this.maKho= new InputFormCustom("Mã Kho:");
        maLoaiSP.setPreferredSize(new Dimension(400,100));
        tenLoaiSP.setPreferredSize(new Dimension(400,100));
        maKho.setPreferredSize(new Dimension(400,100));
        thongTin.add(maLoaiSP);
        thongTin.add(tenLoaiSP);
        thongTin.add(maKho);
        
        // các nút 
        JPanel cacNutNhan= new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        this.btn_huy= new ButtonCustom("Thoát","","#DC143C");
        if ( type.equals("Add")){
            this.btn_them= new ButtonCustom("Thêm","","#00FF7F");
            cacNutNhan.add(this.btn_them);
        }
        else if (type.equals("Change")){
            this.btn_chinhsua= new ButtonCustom("Chỉnh Sửa","","#FFD700");
            themDuLieu();
            cacNutNhan.add(this.btn_chinhsua);
        }
       cacNutNhan.add(this.btn_huy);
        // sự kiện
        this.btn_huy.addActionListener(this);
        
        
        this.add(tieuDePanel,BorderLayout.NORTH);
        this.add(thongTin,BorderLayout.CENTER);
        this.add(cacNutNhan,BorderLayout.SOUTH);
        this.setVisible(true);
        
    }
    
    public void themDuLieu(){
        int dongDuocChon= this.thongtin.getSelectedRow();
        this.maLoaiSP.getTxtForm().setText(this.thongtin.getValueAt(dongDuocChon, 0).toString());
        this.tenLoaiSP.getTxtForm().setText(this.thongtin.getValueAt(dongDuocChon, 1).toString());
        this.maKho.getTxtForm().setText(this.thongtin.getValueAt(dongDuocChon, 2).toString());
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String cauLenh=e.getActionCommand();
        switch(cauLenh){
            case "Thoát":
                this.dispose();
                break;
            case "Thêm":
                break;
            case "Chỉnh Sửa":
                break;
        }
    }
    
}
