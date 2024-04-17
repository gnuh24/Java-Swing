package GUI.GUIDialog;
import BUS.LoaiSanPhamBUS;
import DTO.ThongTinSanPham.LoaiSanPhamDTO;
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
    
    public LoaiSPDialog() {
        this.init();
    }

    public LoaiSPDialog(String tieuDe, String type) {
        this.tieuDe=tieuDe;
        this.type=type;
        init();
    }
    public LoaiSPDialog(String tieuDe, String type, LoaiSanPhamDTO loaisp) {
        this.tieuDe=tieuDe;
        this.type=type;
        this.loaiSPDuocChon=loaisp;
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
            cacNutNhan.add(this.btn_them);
        }
        else if (type.equals("Change")){
            this.btn_chinhsua= new ButtonCustom("Chỉnh Sửa","","#FFD700");
            this.btn_chinhsua.setHorizontalTextPosition(SwingConstants.CENTER);
            this.btn_chinhsua.setHorizontalAlignment(SwingConstants.CENTER);              
            themDuLieu();
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
