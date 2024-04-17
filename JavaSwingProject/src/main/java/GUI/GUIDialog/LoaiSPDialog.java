package GUI.GUIDialog;
import BUS.LoaiSanPhamBUS;
import DTO.ThongTinSanPham.LoaiSanPhamDTO;
import GUI.GUIPanel.GiaoDienLoaiSanPham;
import GUI.GUIThanhPhan.ButtonCustom;
import GUI.GUIThanhPhan.InputFormCustom;
import javax.swing.*;



import java.awt.*;
import java.awt.event.*;

public class LoaiSPDialog extends  JDialog implements ActionListener{
    public String tieuDe,type; // type để set cho nút thêm hoặc xóa
    public InputFormCustom maLoaiSP, tenLoaiSP, maKho;
    public ButtonCustom btn_them,btn_chinhsua,btn_huy;
    
    
    private LoaiSanPhamBUS LoaiSPBUS= new LoaiSanPhamBUS();
    private LoaiSanPhamDTO loaiSPDuocChon;
    private GiaoDienLoaiSanPham loaiSPGUI;
    
    public LoaiSPDialog() {
        this.init();
    }

    public LoaiSPDialog(GiaoDienLoaiSanPham LoaiSPGUI,String tieuDe, String type) {
        this.tieuDe=tieuDe;
        this.type=type;
        this.loaiSPGUI=LoaiSPGUI;
        init();
    }
    public LoaiSPDialog(GiaoDienLoaiSanPham LoaiSPGUI,String tieuDe, String type, LoaiSanPhamDTO loaisp) {
        this.tieuDe=tieuDe;
        this.type=type;
        this.loaiSPDuocChon=loaisp;
        this.loaiSPGUI=LoaiSPGUI;
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
            this.btn_them.setHorizontalTextPosition(SwingConstants.CENTER);
            this.btn_them.setHorizontalAlignment(SwingConstants.CENTER);
            this.btn_them.addActionListener(this);
            cacNutNhan.add(this.btn_them);
        }
        else if (type.equals("Change")){
            this.btn_chinhsua= new ButtonCustom("Chỉnh Sửa","","#FFD700");
            this.btn_chinhsua.setHorizontalTextPosition(SwingConstants.CENTER);
            this.btn_chinhsua.setHorizontalAlignment(SwingConstants.CENTER);              
            themDuLieu();
            this.btn_chinhsua.addActionListener(this);
            cacNutNhan.add(this.btn_chinhsua);
        }
        this.btn_huy.setHorizontalTextPosition(SwingConstants.CENTER);
        this.btn_huy.setHorizontalAlignment(SwingConstants.CENTER);
       cacNutNhan.add(this.btn_huy);
        // sự kiện
        this.btn_huy.addActionListener(this);
        
        
        this.add(tieuDePanel,BorderLayout.NORTH);
        this.add(thongTin,BorderLayout.CENTER);
        this.add(cacNutNhan,BorderLayout.SOUTH);
        this.setVisible(true);
        
    }
    
    public void themDuLieu(){
        this.maLoaiSP.getTxtForm().setText(this.loaiSPDuocChon.getMaLoaiSanPham().toString());
        this.tenLoaiSP.getTxtForm().setText(this.loaiSPDuocChon.getTenLoaiSanPham());
        this.maKho.getTxtForm().setText(this.loaiSPDuocChon.getMaKhoHang().toString());
        
    }
    
    
    
    public void XuLyThemLoaiSP(){
        if (
            maLoaiSP.getTxtForm().getText().trim().equals("") ||
            tenLoaiSP.getTxtForm().getText().trim().equals("") ||
            maKho.getTxtForm().getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin cần thiết !!!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }
        else {
            try{
                int maLoaiSP=Integer.parseInt(this.maLoaiSP.getTxtForm().getText().trim());
                String tenLoaiSP=this.tenLoaiSP.getTxtForm().getText().trim();
                int maKho=Integer.parseInt(this.maKho.getTxtForm().getText().trim());
                if(LoaiSPBUS.create(maKho, new LoaiSanPhamDTO(maLoaiSP, tenLoaiSP,maKho))){
                    JOptionPane.showMessageDialog(this, "Thêm Loại Sản Phẩm Mới Thành Công ^^","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    this.loaiSPGUI.loadDuLieuLoaiSP(LoaiSPBUS.getAll());
                    this.dispose();
                }
                else 
                    JOptionPane.showMessageDialog(this, "Lỗi, vui lòng kiểm tra lại mã sản phẩm hoặc thông tin khác nếu trùng!","Thông báo",JOptionPane.ERROR_MESSAGE);
            } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số với các ô yêu cầu điền mã loại, mã kho","Thông báo",JOptionPane.ERROR_MESSAGE);
            }
                 
        }

    }
    
    public void XuLyChinhSuaLoaiSP(){
        if (
            maLoaiSP.getTxtForm().getText().trim().equals("") ||
            tenLoaiSP.getTxtForm().getText().trim().equals("") ||
            maKho.getTxtForm().getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin cần thiết !!!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }
        else {
            try{
                int maLoaiSP=Integer.parseInt(this.maLoaiSP.getTxtForm().getText().trim());
                String tenLoaiSP=this.tenLoaiSP.getTxtForm().getText().trim();
                int maKho=Integer.parseInt(this.maKho.getTxtForm().getText().trim());
                if(LoaiSPBUS.update(new LoaiSanPhamDTO(maLoaiSP, tenLoaiSP,maKho))){
                    JOptionPane.showMessageDialog(this, "Chỉnh sửa thành công ^^","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    this.loaiSPGUI.loadDuLieuLoaiSP(LoaiSPBUS.getAll());
                    this.dispose();
                }
                else 
                    JOptionPane.showMessageDialog(this, "Lỗi khi chỉnh sửa!","Thông báo",JOptionPane.ERROR_MESSAGE);
            } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số với các ô yêu cầu điền mã loại, mã kho","Thông báo",JOptionPane.ERROR_MESSAGE);
            }
                 
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String cauLenh=e.getActionCommand();
        switch(cauLenh){
            case "Thoát":
                this.dispose();
                break;
            case "Thêm":
                XuLyThemLoaiSP();
                break;
            case "Chỉnh Sửa":
                XuLyChinhSuaLoaiSP();
                break;
        }
    }
    
}
