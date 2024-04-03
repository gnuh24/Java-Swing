package GUI.GUIPanel;

import GUI.GUIDialog.LoaiSPDialog;
import GUI.GUIThanhPhan.ButtonCustom;
import GUI.GUIThanhPhan.InputFormCustom;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Admin
 */
public class GiaoDienLoaiSanPham extends JPanel implements ActionListener{

    private  DefaultTableModel model;
    private  JTable thongTin;

    public GiaoDienLoaiSanPham() {
        this.setSize(1200,900);// 1980, 1050
        this.setLayout(new BorderLayout(0,0));  

        // tiêu đề trên JPanel
        JPanel panelTren= new JPanel(new FlowLayout(FlowLayout.LEFT,20,20));
        JLabel tieuDe=new JLabel("Loại Sản Phẩm");
        panelTren.setBackground(new Color(255,255,255));
        panelTren.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        tieuDe.setFont(new Font("Arial",Font.BOLD, 40));
        panelTren.setPreferredSize(new Dimension(1200,100));
        panelTren.add(tieuDe);
        //70% trái và 30% phải
        JPanel noiDung= new JPanel(new BorderLayout(0,0));
        noiDung.setPreferredSize(new Dimension(1200,800));
        JPanel panelTrai= new JPanel(new BorderLayout(0,0));
        panelTrai.setPreferredSize(new Dimension(800,800));
        JPanel panelPhai= new JPanel(new BorderLayout(0,0));
        panelPhai.setPreferredSize(new Dimension(400,800));
        panelTrai.setBackground(Color.red);
        panelPhai.setBackground(Color.blue);
        // bên phải
        JPanel timKiemLoaiSP= new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        timKiemLoaiSP.setPreferredSize(new Dimension(400,200));
        InputFormCustom timKiem= new InputFormCustom("Loại Sản Phẩm: ");
        timKiem.setPreferredSize(new Dimension(350,120));
        timKiemLoaiSP.add(timKiem);
        panelPhai.add(timKiemLoaiSP,BorderLayout.NORTH);
        
            // hiệu ứng cho textfield
        timKiem.getTxtForm().setText("Nhập loại tìm kiếm...");
        timKiem.getTxtForm().setForeground(Color.gray);
        timKiem.setBorder(new EmptyBorder(10,20,10,10)); // top left bottom right
        timKiem.getTxtForm().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.ORANGE),BorderFactory.createEmptyBorder(10,20,10,10));
                timKiem.getTxtForm().setBorder(border);
                if(timKiem.getTxtForm().getText().equals(("Nhập loại tìm kiếm...")))
                {
                    timKiem.getTxtForm().setText("");
                    timKiem.getTxtForm().setForeground(Color.BLACK);
                }

            }

            @Override
            public void focusLost(FocusEvent fe) {
                timKiem.getTxtForm().setBorder(new EmptyBorder(10,20,10,10));
                if( timKiem.getTxtForm().getText().isEmpty())
                {
                    timKiem.getTxtForm().setForeground(Color.GRAY);
                    timKiem.getTxtForm().setText("Nhập loại tìm kiếm...");
                }

            }
        });
        
        JPanel cacNutNhan= new JPanel(new FlowLayout(FlowLayout.LEFT,10,30));
        cacNutNhan.setBackground(Color.white);
        ButtonCustom themLoaiSP= new ButtonCustom("Thêm Loại Mới");
        themLoaiSP.setPreferredSize(new Dimension(350,100));
        ButtonCustom suaLoaiSP= new ButtonCustom("Sửa Loại Hiện Tại");
        suaLoaiSP.setPreferredSize(new Dimension(350,100));
        ButtonCustom xoaLoaiSP= new ButtonCustom("Xóa Loại Sản Phẩm");
        xoaLoaiSP.setPreferredSize(new Dimension(350,100));
        cacNutNhan.add(themLoaiSP);cacNutNhan.add(suaLoaiSP);cacNutNhan.add(xoaLoaiSP);
        panelPhai.add(cacNutNhan,BorderLayout.CENTER);
        // bên trái
        String[] colum = new String[]{"Mã Loại Sản Phẩm","Tên Loại Sản Phẩm","Mã Kho Hàng"};
        Object data[][]={{"L001","Nước ngọt","Kho A"},
                        {"L002","Rượu","Kho A"},
                        {"L003","Quần Áo","Kho B"}};
         model = new DefaultTableModel(data, colum);
         thongTin = new JTable(model);
         chinhSuaGiaoDienTable();
        JScrollPane dulieuLoaiSP = new JScrollPane(thongTin);
        
        panelTrai.add(dulieuLoaiSP,BorderLayout.CENTER);
        // add sự kiện button
        themLoaiSP.addActionListener(this);
        suaLoaiSP.addActionListener(this);
        xoaLoaiSP.addActionListener(this);
        
        noiDung.add(panelTrai,BorderLayout.CENTER);
        noiDung.add(panelPhai,BorderLayout.EAST);
        this.add(panelTren,BorderLayout.NORTH);
        this.add(noiDung);
    }
    
        public void chinhSuaGiaoDienTable(){
                // Căn giữa tiêu đề của các cột
            //DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) thongTin.getTableHeader().getDefaultRenderer();
            //headerRenderer.setHorizontalAlignment(JLabel.CENTER);
            thongTin.setShowGrid(false);        thongTin.setShowHorizontalLines(true);
            thongTin.setDefaultEditor(Object.class, null);
            thongTin.setRowHeight(50);
            thongTin.getTableHeader().setReorderingAllowed(false);
            thongTin.getTableHeader().setPreferredSize(new Dimension(0,50));
            thongTin.getTableHeader().setFont(new Font("Arial",Font.BOLD,15));
            thongTin.getTableHeader().setBackground(Color.WHITE);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);



           for(int i=0; i < thongTin.getColumnCount();i++){
                thongTin.getColumnModel().getColumn(i).setPreferredWidth(100);
                if( i !=1)
                    thongTin.getColumnModel().getColumn(i).setCellRenderer(centerRenderer );


           }
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnLenh=e.getActionCommand();
        System.out.println(btnLenh);
        
        switch(btnLenh){
            case "Thêm Loại Mới":
                LoaiSPDialog themLoaiSP=new LoaiSPDialog("Thêm Mới", "Add");
                break;
            case "Sửa Loại Hiện Tại":
                if( this.thongTin.getSelectedRow()!=-1)
                {
                    System.out.println(this.thongTin.getSelectedRow());
                    LoaiSPDialog suaLoaiSP=new LoaiSPDialog("Chỉnh Sửa", "Change",this.thongTin);
                }
                else JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để chỉnh sửa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                
                
                break;
            case "Xóa Loại Sản Phẩm":
                JOptionPane.showMessageDialog(this, "Sản phâm đã xóa");
                break;
        }
        
    }
    
}
