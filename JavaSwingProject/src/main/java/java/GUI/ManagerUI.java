package GUI;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerUI extends JPanel{
	
		private JLabel nameLabel, emailLabel, roleLabel, oldPasswordLabel, newPasswordLabel;
		private JTextField nameField, emailField;
		private JCheckBox changePasswordCheckbox;
		private JPasswordField oldPasswordField, newPasswordField;
		private JButton submitButton;

		public ManagerUI() {
			Border compoundBorder = BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Thông tin cá nhân"),
				BorderFactory.createEmptyBorder(10, 10, 10, 10));
			setBorder(compoundBorder);
			setLayout(null);

			nameLabel = new JLabel("Họ và tên:");
			nameLabel.setBounds(230, 50, 100, 50);
			nameField = new JTextField(20);
			nameField.setBounds(230, 90, 600, 30);

			emailLabel = new JLabel("Email:");
			emailLabel.setBounds(230, 120, 100, 50);

			emailField = new JTextField(20);
			emailField.setBounds(230, 160, 600, 30);

			roleLabel = new JLabel("Vai trò: ");
			roleLabel.setBounds(230, 200, 100, 50);

			changePasswordCheckbox = new JCheckBox("Đổi mật khẩu");
			changePasswordCheckbox.setBounds(230, 250, 150, 50);
			changePasswordCheckbox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (changePasswordCheckbox.isSelected()) {
					oldPasswordLabel.setVisible(true);
					oldPasswordField.setVisible(true);
					newPasswordLabel.setVisible(true);
					newPasswordField.setVisible(true);
				} else {
					oldPasswordLabel.setVisible(false);
					oldPasswordField.setVisible(false);
					newPasswordLabel.setVisible(false);
					newPasswordField.setVisible(false);
				}
			}
			});

			oldPasswordLabel = new JLabel("Mật khẩu cũ:");
			oldPasswordLabel.setBounds(230, 300, 100, 50);

			oldPasswordField = new JPasswordField(20);
			oldPasswordField.setBounds(230, 340, 600, 30);

			newPasswordLabel = new JLabel("Mật khẩu mới:");
			newPasswordLabel.setBounds(230, 390, 100, 50);

			newPasswordField = new JPasswordField(20);
			newPasswordField.setBounds(230, 430, 600, 30);

			oldPasswordLabel.setVisible(false);
			oldPasswordField.setVisible(false);
			newPasswordLabel.setVisible(false);
			newPasswordField.setVisible(false);
			
			submitButton = new JButton("Cập nhật");
			submitButton.setBounds(480, 600, 100, 50);

			add(nameLabel);
			add(nameField);
			add(emailLabel);
			add(emailField);
			add(roleLabel);
			add(changePasswordCheckbox);
			add(oldPasswordLabel);
			add(oldPasswordField);
			add(newPasswordLabel);
			add(newPasswordField);
			add(submitButton);
			
			setPreferredSize(new Dimension(1000,700));
			setVisible(true);
		}
		
		// public static void main(String[] args) {
		// 	JFrame frame = new JFrame();
		// 	frame.setTitle("Personal Information");
		// 	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 	frame.setSize(1100, 800);
		// 	frame.setLocationRelativeTo(null);

		// 	ManagerUI managerUI = new ManagerUI();
		// 	frame.add(managerUI);

		// 	frame.setVisible(true);
		// }
	
}