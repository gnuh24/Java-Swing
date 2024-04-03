package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
// import javax.swing.border.*;
// import javax.swing.table.*;

public class GUI extends JFrame implements ActionListener {
      private JPanel header;
      private JPanel main;
      
      private AdminUI adminUI = new AdminUI();
      private ManagerUI managerUI = new ManagerUI();
      private XuatHangUI xuatHangUI = new XuatHangUI();
      private PhieuXuatUI phieuXuatUI = new PhieuXuatUI();
      //? Các button header
      JButton title_btn1, title_btn2, title_btn3, title_btn4, title_btn5, title_btn6, title_btn7, title_btn8,account_btn1, account_btn2; 
      public GUI() {
            //? Header 
            header = new JPanel();
                  JPanel author = new JPanel();
                  author.setPreferredSize(new Dimension(200, 80));
                        JLabel author_lb1 = new JLabel("QUẢN LÝ KHO HÀNG");
                        author_lb1.setFont(new Font("Arial", Font.BOLD, 18));
                        author_lb1.setForeground(Color.BLACK);
                        author_lb1.setPreferredSize(new Dimension(200, 80));
                        author_lb1.setHorizontalAlignment(SwingConstants.CENTER);
                  author.add(author_lb1); 
                  author.setBackground(Color.WHITE);

                  JPanel title = new JPanel();
                        title_btn1 = new JButton("SẢN PHẨM");
                        title_btn2 = new JButton("NHÀ CUNG CẤP");
                        title_btn3 = new JButton("NHẬP HÀNG");
                        title_btn4 = new JButton("PHIẾU NHẬP");
                        title_btn5 = new JButton("XUẤT HÀNG");
                        title_btn5.addActionListener(this);
                        title_btn6 = new JButton("PHIẾU XUẤT");
                        title_btn6.addActionListener(this);
                        title_btn7 = new JButton("TÀI KHOẢN");
                        title_btn7.addActionListener(this);
                        title_btn8 = new JButton("THỐNG KÊ");
                        title.add(customButtonHeader(title_btn1,"sanpham.png"));
                        title.add(customButtonHeader(title_btn2,"nhacungcap.png"));
                        title.add(customButtonHeader(title_btn3,"nhaphang.png"));
                        title.add(customButtonHeader(title_btn4,"phieunhap.png"));
                        title.add(customButtonHeader(title_btn5,"xuathang.png"));
                        title.add(customButtonHeader(title_btn6,"phieuxuat.png"));
                        title.add(customButtonHeader(title_btn7,"taikhoan.png"));
                        title.add(customButtonHeader(title_btn8,"thongke.png"));
                        title.setBackground(Color.WHITE);
                        title.setPreferredSize(new Dimension(200, 550));
                        
                        JPanel account = new JPanel();
                        account_btn1 = new JButton("ĐỔI THÔNG TIN");
                        account_btn1.addActionListener(this);
                        account_btn2 = new JButton("ĐĂNG XUẤT");
                        account.add(customButtonHeader(account_btn1,"thongtin.png"));
                        account.add(customButtonHeader(account_btn2,"dangxuat.png"));
                        account.setBackground(Color.WHITE);
                        account.setPreferredSize(new Dimension(200, 170));

            header.setPreferredSize(new Dimension(225, 850));
            header.setBackground(Color.WHITE);
            header.add(author, BorderLayout.NORTH);
            header.add(title, BorderLayout.CENTER);
            header.add(account, BorderLayout.SOUTH);

            setTitle("QUẢN LÝ KHO HÀNG");
            //? Nội dung chính
            main = new JPanel();
            main.setPreferredSize(new Dimension(1300, 800));
            main.setLayout(new FlowLayout());
            //? Thay đổi ở đây
            // main.add(xuatHangUI);
            //main.add(managerUI);
            // main.add(adminUI);
            main.add(phieuXuatUI);
            //? Add Panel
            add(header);
            add(main);
            //? Frame setting
            setLayout(new FlowLayout());
            setResizable(false);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);
      }
      public JButton customButtonHeader(JButton button, String linkIMG) {
            button.setFont(new Font("Arial", Font.BOLD, 14)); // Thiết lập font
            button.setForeground(Color.BLACK); // Thiết lập màu chữ
            button.setBackground(Color.WHITE); // Thiết lập màu nền
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK,1)); // Thiết lập viền
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            // Tùy chỉnh kích thước
            Dimension size = button.getPreferredSize();
            size.width = 200;
            size.height = 35;
            button.setPreferredSize(size);

            ImageIcon icon = new ImageIcon("Java-Swing\\JavaSwingProject\\src\\main\\java\\org\\example\\GUI\\IMG\\" + linkIMG); 
            button.setIcon(new ImageIcon(icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));

            return button;
      }
      @Override
      public void actionPerformed(ActionEvent e) {
            if (e.getSource() == title_btn5) { //? Xuất hàng
                  switchPanel(xuatHangUI);
            } else if (e.getSource() == title_btn6){
                  switchPanel(phieuXuatUI);
            }else if (e.getSource() == title_btn7){
                  switchPanel(adminUI);
            } else if (e.getSource() == account_btn1) {
                  switchPanel(managerUI);
            } else {
                  System.out.println("chưa set actionListener");
            }
      }
      public void switchPanel(JPanel newPanel) {
            //? Xóa panel hiện tại
            main.removeAll();
            //? Thêm panel mới
            main.add(newPanel);
            //? Cập nhật giao diện
            main.revalidate();
            main.repaint();
      }
}
