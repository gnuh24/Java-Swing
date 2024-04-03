package GUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class DangNhap extends JFrame{
	
	public DangNhap() {
		this.init();
	}
	
	public void init () {
        this.setTitle("Đăng Nhập");
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        
        //panelLeft
        JPanel panelLeft = new JPanel();
        
        // Tiêu đề
        JLabel Title = new JLabel("Đăng nhập ");
        Title.setFont(new Font("Arial", Font.PLAIN, 20));
        Title.setBounds(150, 30, 200, 30);
        
        //Trường Email
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 100, 100, 30);
        JTextField emailField = new JTextField(50);
        emailField.setBounds(50, 130, 300, 30);
        
        
        //Trường Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 160, 100, 30);
        JPasswordField passwordField = new JPasswordField(50);
        passwordField.setBounds(50, 190, 300, 30);
        
        
        //Nút Đăng Nhập
        JButton buttonLogin = new JButton("Đăng nhập");
        buttonLogin.setFont(new Font("Arial", Font.BOLD, 15));
        buttonLogin.setBounds(50, 270, 300, 50);
        Color buttonColor = new Color(180, 201, 226);
        buttonLogin.setBackground(buttonColor);
        buttonLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        
        //Chưa có tài khoản
        JLabel labelQuestion = new JLabel("Bạn chưa có tài khoản?");
        labelQuestion.setFont(new Font("Arial", Font.PLAIN, 15));
        labelQuestion.setBounds(50, 400, 300, 30);
        JButton labelRegister = new JButton("Đăng ký");
        labelRegister.setActionCommand("Đăng ký");
        labelRegister.setFont(new Font("Arial", Font.PLAIN, 15));
        labelRegister.setBounds(220, 405, 100, 20);
        labelRegister.setForeground(Color.BLUE);
        
//        DangNhapController ac = new DangNhapController(this);
//        labelRegister.addActionListener(ac);
        
//        DangNhapController ac = new DangNhapController(this);
        labelRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new DangKy();
				closeWindow();
			}
		});
        
        
        
        panelLeft.add(Title);
        panelLeft.add(emailLabel);
        panelLeft.add(emailField);
        panelLeft.add(passwordLabel);
        panelLeft.add(passwordField);
        panelLeft.add(buttonLogin);
        panelLeft.add(labelQuestion);
        panelLeft.add(labelRegister);
        panelLeft.setLayout(null);
        mainPanel.add(panelLeft);    
            
        
        
            // panelRight
        JPanel panelRight = new JPanel();
        
        URL imageLogin = DangNhap.class.getResource("imgLogin.png");
        ImageIcon icon = new ImageIcon(imageLogin);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        JLabel label = new JLabel(scaledIcon);
        panelRight.add(label);    
            
        mainPanel.add(panelRight);
            
        this.add(mainPanel);
        this.setVisible(true);
    }
	
	
	public void closeWindow () {
		this.dispose();
	}


}
