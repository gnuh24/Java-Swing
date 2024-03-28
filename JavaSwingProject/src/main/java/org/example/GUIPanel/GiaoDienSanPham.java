
package org.example.GUIPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.example.GUIDialog.SanPhamDialog;
import org.example.GUIThanhPhan.ButtonCustom;



public class GiaoDienSanPham extends JPanel implements ActionListener{

    private  JTable thongTin;
    private  DefaultTableModel model;

    public GiaoDienSanPham()  {
        this.setSize(1200,800);// 1980, 1050
        this.setLayout(new BorderLayout(0,0));    
        //this.getContentPane().setBackground(Color.LIGHT_GRAY);
        // menu điều hướng bên trái 15% 
        
        //////// kết thúc
        // chia 1/3 là chức năng, 2/3 dưới là table sản phẩm
        // PanelTren
        JPanel panelTren= new JPanel(new BorderLayout(0,0));
        panelTren.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        panelTren.setPreferredSize(new Dimension(1200,100));
        panelTren.setBackground(Color.WHITE);
        
        // PanelTren bên trái
        JPanel chucNangTrai= new JPanel(new FlowLayout(FlowLayout.CENTER,20,30));
        chucNangTrai.setBackground(Color.WHITE);//Color.decode("#3366FF"))
        JLabel noiDung=new JLabel("Sản phẩm");
        noiDung.setFont(new Font("Arial",Font.BOLD, 40));
        chucNangTrai.add(noiDung);
        // PanelTren bên phải
        JPanel chucNangPhai= new JPanel(new FlowLayout(FlowLayout.RIGHT,20,20));
        chucNangPhai.setBackground(Color.WHITE);
        JButton themSP,xoaSP,excelSP;
        xoaSP = new ButtonCustom("Xóa Sản Phẩm", "iconDelete24.png");
        themSP= new ButtonCustom("Thêm Sản Phẩm", "iconAdd24.png");
        excelSP= new ButtonCustom("Xuất File Excel", "iconExcel24.png");
        xoaSP.setHorizontalTextPosition(SwingConstants.CENTER); 
        xoaSP.setVerticalTextPosition(SwingConstants.BOTTOM);
        themSP.setHorizontalTextPosition(SwingConstants.CENTER); 
        themSP.setVerticalTextPosition(SwingConstants.BOTTOM);
        excelSP.setHorizontalTextPosition(SwingConstants.CENTER); 
        excelSP.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        chucNangPhai.add(excelSP); chucNangPhai.add(themSP); chucNangPhai.add(xoaSP);
        panelTren.add(chucNangTrai,BorderLayout.WEST);
        panelTren.add(chucNangPhai,BorderLayout.EAST);
        
        
        // PanelDuoi
        JPanel panelDuoi= new JPanel(new BorderLayout(0,10));
        //PanelDuoi TimKiem
        String loc[]={"Tất cả","Lọc A","Lọc B","Lọc C","Lọc D"};
        JComboBox<String> locSP= new JComboBox<>(loc);
        locSP.setBackground(Color.WHITE);
        JPanel timKiemLocSP= new JPanel(new FlowLayout(FlowLayout.LEFT,20,10));
        locSP.setPreferredSize(new Dimension(150,40));
        //timKiemLocSP.setBackground(Color.white);
        
        JTextField timKiem = new JTextField("Nhập nội dung tìm kiếm...");
        timKiem.setForeground(Color.GRAY);
        timKiem.setBorder(new EmptyBorder(10,20,10,10)); // top left bottom right
        timKiem.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.ORANGE),BorderFactory.createEmptyBorder(10,20,10,10));
                timKiem.setBorder(border);
                if(timKiem.getText().equals(("Nhập nội dung tìm kiếm...")))
                {
                    timKiem.setText("");
                    timKiem.setForeground(Color.BLACK);
                }

            }

            @Override
            public void focusLost(FocusEvent fe) {
                timKiem.setBorder(new EmptyBorder(10,20,10,10));
                if( timKiem.getText().isEmpty())
                {
                    timKiem.setForeground(Color.GRAY);
                    timKiem.setText("Nhập nội dung tìm kiếm...");
                }

            }
        });
        timKiem.setPreferredSize(new Dimension(900, 40));
        timKiemLocSP.add(locSP); timKiemLocSP.add(timKiem);
        // PanelDuoi Table
        String[] colum = new String[]{"STT", "Hình Ảnh", "Mã SP", "Tên sản phẩm", "Số lượng", "Thương hiệu", "Trọng lượng", "Xuất xứ", "Giá"};
        Object data[][] = {{"1","", "MaSP1", "Rượu Soju","12","Hàn Quốc","550ml","Hàn Quốc","70000"},
                {"2","", "MaSP2", "Rượu Vodka","12","Ba Lan","300ml","Mỹ","95500"},
                {"3","", "MaSP3", "Rượu Gin","7","Mỹ","550ml","Mỹ","12000",""},
                {"4","", "MaSP4", "Rượu Rum","2","Caribbean","300ml","Hàn Quốc","245000"},
                {"5","", "MaSP5", "Rượu Tequila","5","Mexico","650ml","Việt Nam","240000"},
                {"6","", "MaSP6", "Rượu Whisky","9","Scotland","550ml","Việt Nam","150000"},
                {"7","", "MaSP7", "Rượu Whisky","9","Scotland","550ml","Việt Nam","150000"},
                {"8","", "MaSP8", "Rượu Whisky","9","Scotland","550ml","Trung Quốc","150000"},
                {"9","", "MaSP9", "Rượu Whisky","9","Scotland","550ml","Mỹ","150000"},
                {"10","", "MaSP10", "Rượu Whisky","9","Scotland","550ml","Hàn Quốc","150000"},
                {"11","", "MaSP10", "Rượu Whisky","9","Scotland","550ml","Trung Quốc","150000"},
                {"12","", "MaSP10", "Rượu Whisky","9","Scotland","550ml","Hàn Quốc","150000"},
                {"13","", "MaSP10", "Rượu Whisky","9","Scotland","550ml","Hàn Quốc","150000"},
                {"14","", "MaSP10", "Rượu Whisky","9","Scotland","550ml","Nhật Bản","150000"},
                {"15","", "MaSP10", "Rượu Whisky","9","Scotland","550ml","Mỹ","150000"},
                {"16","", "MaSP10", "Rượu Whisky","9","Scotland","550ml","Nhật Bản","150000"},
                {"17","", "MaSP11", "Rượu Vang","13","VULTUS","550ml","Nhật Bản","194000"}
        };
         model = new DefaultTableModel(data, colum);
        thongTin = new JTable(model);
        chinhSuaGiaoDienTable();
        JScrollPane dulieuSP = new JScrollPane(thongTin);

        
        panelDuoi.add(timKiemLocSP,BorderLayout.NORTH);
        panelDuoi.add(dulieuSP,BorderLayout.CENTER);

        // đưa vào giao diện chính của sản phẩm

        
        
        //// mục sự kiện chính cho giao diện
        themSP.addActionListener(this);
        thongTin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable target = (JTable) e.getSource();
                if (e.getClickCount() == 2) 
                    new SanPhamDialog("Chi Tiết Sản Phẩm","ChiTiet",thongTin,thongTin.getSelectedRow());
                
            }
        });
        xoaSP.addActionListener(this);

        this.add(panelTren,BorderLayout.NORTH);
        this.add(panelDuoi,BorderLayout.CENTER);
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
            if( i==0)
               thongTin.getColumnModel().getColumn(i).setPreferredWidth(20);
            else if( i==3)
               thongTin.getColumnModel().getColumn(3).setPreferredWidth(300);
            else thongTin.getColumnModel().getColumn(i).setPreferredWidth(100);
            if( i!=3)
                thongTin.getColumnModel().getColumn(i).setCellRenderer(centerRenderer );
           

       }
    }

    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String lenh=ae.getActionCommand();
        SanPhamDialog a= null;
        if(lenh.equals("Thêm Sản Phẩm"))
            a=new SanPhamDialog("Thêm Sản Phẩm Mới","Add");
        else if(lenh.equals("Xóa Sản Phẩm") && thongTin.getSelectedRow()!=-1){
            int luaChon=JOptionPane.showConfirmDialog(this,"Chắc chắn xóa sản phẩm ?","Xóa Sản Phẩm",JOptionPane.YES_NO_OPTION);
            if( luaChon==0)
            {
                model.removeRow(thongTin.getSelectedRow());
                JOptionPane.showMessageDialog(this, "Sản phâm đã xóa");
            }
        }else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa");
        }
    }
    
    public static void main(String[] args) {
        //try {
         //   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new GiaoDienSanPham();
            
       //}catch(Exception e){
         //   e.printStackTrace();
        //}
        
    }


}
