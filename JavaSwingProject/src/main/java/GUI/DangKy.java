package view;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;



public class DangKy extends JFrame{

	public DangKy() {
		this.init();
	}
	
	public void init () {
        this.setTitle("Đăng Ký");
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        
        //panelLeft
        JPanel panelLeft = new JPanel();
        
        // Tiêu đề
        JLabel Title = new JLabel("Đăng ký ");
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
        
        
      //Trường Confirm Password
        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setBounds(50, 220, 130, 30);
        JPasswordField confirmPasswordField = new JPasswordField(50);
        confirmPasswordField.setBounds(50, 250, 300, 30);
        
        
        //Nút Đăng Ký
        JButton buttonLogin = new JButton("Đăng ký");
        buttonLogin.setFont(new Font("Arial", Font.BOLD, 15));
        buttonLogin.setBounds(50, 300, 300, 50);
        Color buttonColor = new Color(180, 201, 226);
        buttonLogin.setBackground(buttonColor);
        buttonLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        
        //Chưa có tài khoản
        JLabel labelQuestion = new JLabel("Bạn đã có tài khoản?");
        labelQuestion.setFont(new Font("Arial", Font.PLAIN, 15));
        labelQuestion.setBounds(50, 400, 300, 30);
        JButton labelLogin = new JButton("Đăng nhập");
        labelLogin.setActionCommand("Đăng nhập");
        labelLogin.setFont(new Font("Arial", Font.PLAIN, 15));
        labelLogin.setBounds(220, 405, 130, 20);
        labelLogin.setForeground(Color.BLUE);
        
//        DangNhapController ac = new DangNhapController(this);
//        labelRegister.addActionListener(ac);
        
//        DangNhapController ac = new DangNhapController(this);
        labelLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new DangNhap();
				closeWindow();
			}
		});
        
        
        
        panelLeft.add(Title);
        panelLeft.add(emailLabel);
        panelLeft.add(emailField);
        panelLeft.add(passwordLabel);
        panelLeft.add(passwordField);
        panelLeft.add(confirmPasswordLabel);
        panelLeft.add(confirmPasswordField);
        panelLeft.add(buttonLogin);
        panelLeft.add(labelQuestion);
        panelLeft.add(labelLogin);
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
