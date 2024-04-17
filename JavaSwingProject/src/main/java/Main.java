import GUI.GUIPanel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 *
 * @author Admin
 */
public class Main extends JFrame implements ActionListener{
    private JPanel MainContent;
    private  MenuTaskBar menu;
    public Main(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0,0));    
        
        
        // MenuTaskbar
         menu = new MenuTaskBar();
        // add sự kiện
        menu.getBtn_TrangChu().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                JPanel trangchu= new JPanel();
                trangchu.setBackground(Color.white);  
                setPanelMain(trangchu); 
                hieuUngHover(e);
            }
        });
        
        menu.getBtn_sanPham().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                GiaoDienSanPham sanPham= new GiaoDienSanPham();
                setPanelMain(sanPham);
                hieuUngHover(e);
            }
        });
        
        menu.getBtn_loaiSP().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                GiaoDienLoaiSanPham loaiSP= new GiaoDienLoaiSanPham();
                setPanelMain(loaiSP);
                hieuUngHover(e);
            }
        });
        
        menu.getBtn_PhieuNhap().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                JPanel phieunhap= new JPanel();
                phieunhap.setBackground(Color.cyan);  
                setPanelMain(phieunhap); 
                hieuUngHover(e);
            }
        });
        
        menu.getBtn_XuatHang().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                JPanel xuathang= new XuatHangUI();
                setPanelMain(xuathang); 
                hieuUngHover(e);
            }
        });
        menu.getBtn_PhieuXuat().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                JPanel phieuxuat= new PhieuXuatUI();
                setPanelMain(phieuxuat); 
                hieuUngHover(e);
            }
        });
        
        menu.getBtn_TaiKhoan().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                JPanel taikhoan= new JPanel();
                taikhoan.setBackground(Color.MAGENTA);  
                setPanelMain(taikhoan);
                hieuUngHover(e);
            }
        });
        
        menu.getBtn_ThongKe().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                JPanel thongke= new JPanel();
                thongke.setBackground(Color.PINK);  
                setPanelMain(thongke);
                hieuUngHover(e);
            }
        });
        
        menu.getBtn_DoiThongTin().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                JPanel doithongtin= new JPanel();
                doithongtin.setBackground(Color.yellow);  
                setPanelMain(doithongtin);
                hieuUngHover(e);
            }
        });
        
        menu.getBtn_DangXuat().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                JPanel dangxuat= new JPanel();
                dangxuat.setBackground(Color.LIGHT_GRAY);  
                setPanelMain(dangxuat);
                hieuUngHover(e);
            }
        });
        
        this.add(menu,BorderLayout.WEST);
        
        
        // Panel giao diện bên phải
        MainContent = new JPanel();
        MainContent.setBackground(new Color(255,255,255));
        MainContent.setLayout(new BorderLayout(0, 0));
        this.add(MainContent, BorderLayout.CENTER);
        JPanel trangchu= new JPanel();
        trangchu.setBackground(Color.orange);
        this.setPanelMain(trangchu);
        this.setResizable(false);
        this.setVisible(true);
    }


    public void setPanelMain(JPanel pn){
        MainContent.removeAll();
        MainContent.add(pn).setVisible(true);
        MainContent.repaint();
        MainContent.validate();
    }
    public void hieuUngHover(MouseEvent event){
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public static void main(String[] args) {
        new Main();
    }
    
}
