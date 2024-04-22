package GUI.GUIPanel;

import BUS.TaiKhoanBUS;
import DTO.NguoiDung.TaiKhoanDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdminUI extends JPanel {
    private JTextField searchField;
    private JTable table;
    private DefaultTableModel model;
    private TaiKhoanBUS taiKhoanBUS;

    public AdminUI() {
        taiKhoanBUS = new TaiKhoanBUS();

        // Thiết lập giao diện chính
        setLayout(new BorderLayout());

        // Tạo thanh tìm kiếm
        JPanel searchPanel = new JPanel();
        JLabel searchLabel = new JLabel("Tìm kiếm tên nhân viên:");
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Tìm kiếm");
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Tạo bảng điều khiển chứa nút
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Thêm");
        JButton editButton = new JButton("Sửa");
        JButton deleteButton = new JButton("Xóa");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Tạo bảng dữ liệu và cuộn cho bảng
        String[] columnNames = {"Mã Tài Khoản", "Tên Đăng Nhập", "Mật Khẩu", "Họ và Tên", "Ngày Sinh", "Giới tính", "Số Điện Thoại", "Email", "Địa Chỉ", "Trạng Thái", "Vai Trò", "Mã Kho Hàng"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1300, 600));

        // Thêm các thành phần vào giao diện chính
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Thiết lập hành động cho các nút
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAccount();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editAccount();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAccount();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAccount();
            }
        });

        // Thêm sự kiện tìm kiếm cho trường tìm kiếm
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchAccount();
            }
        });

        // Tải dữ liệu tài khoản khi bắt đầu
        loadAllAccounts();
    }

    // Hàm để tải tất cả các tài khoản
    private void loadAllAccounts() {
        model.setRowCount(0);
        ArrayList<TaiKhoanDTO> accounts = taiKhoanBUS.getAllAccounts();
        for (TaiKhoanDTO account : accounts) {
            model.addRow(new Object[]{account.getMaTaiKhoan(), account.getTenDangNhap(), account.getMatKhau(),
                account.getHoVaTen(), account.getNgaySinh(), account.getGioiTinh(), 
                account.getSoDienThoai(), account.getEmail(), account.getDiaChi(), 
                account.getTrangThai(), account.getQuyen(), account.getMaKhoHang()});
        }
    }

    private void addAccount() {
        TaiKhoanDTO newAccount = inputAccountInfo(null);
        if (newAccount != null) {
            int result = taiKhoanBUS.insertAccount(newAccount);
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Thêm tài khoản thành công.");
                loadAllAccounts();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm tài khoản thất bại.");
            }
        }
    }

    private void editAccount() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần sửa.");
        return;
    }

    // Lấy thông tin tài khoản hiện tại từ hàng được chọn trong bảng
    TaiKhoanDTO account = new TaiKhoanDTO();
    account.setMaTaiKhoan((Integer) table.getValueAt(selectedRow, 0));
    account.setTenDangNhap((String) table.getValueAt(selectedRow, 1));
    account.setMatKhau((String) table.getValueAt(selectedRow, 2));
    account.setHoVaTen((String) table.getValueAt(selectedRow, 3));
    account.setNgaySinh((String) table.getValueAt(selectedRow, 4));
    account.setGioiTinh((String) table.getValueAt(selectedRow, 5));
    account.setSoDienThoai((String) table.getValueAt(selectedRow, 6));
    account.setEmail((String) table.getValueAt(selectedRow, 7));
    account.setDiaChi((String) table.getValueAt(selectedRow, 8));
    account.setTrangThai((Integer) table.getValueAt(selectedRow, 9));
    account.setQuyen((String) table.getValueAt(selectedRow, 10));
    account.setMaKhoHang((Integer) table.getValueAt(selectedRow, 11));

    // Hiển thị hộp thoại nhập thông tin với thông tin tài khoản hiện tại
    TaiKhoanDTO editedAccount = inputAccountInfo(account);
    if (editedAccount != null) {
        int result = taiKhoanBUS.updateAccount(editedAccount);
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Sửa tài khoản thành công.");
            loadAllAccounts();
        } else {
            JOptionPane.showMessageDialog(this, "Sửa tài khoản thất bại.");
        }
    }
}

    private void deleteAccount() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần xóa.");
            return;
        }

        TaiKhoanDTO account = new TaiKhoanDTO();
        account.setMaTaiKhoan((Integer) table.getValueAt(selectedRow, 0));

        int result = taiKhoanBUS.deleteAccount(account);
        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Xóa tài khoản thành công.");
            loadAllAccounts();
        } else {
            JOptionPane.showMessageDialog(this, "Xóa tài khoản thất bại.");
        }
    }

    private void searchAccount() {
        String searchTerm = searchField.getText();
        model.setRowCount(0);
        ArrayList<TaiKhoanDTO> accounts = taiKhoanBUS.searchUserName(searchTerm);
        for (TaiKhoanDTO account : accounts) {
            model.addRow(new Object[]{account.getMaTaiKhoan(), account.getTenDangNhap(), account.getMatKhau(),
                account.getHoVaTen(), account.getNgaySinh(), account.getGioiTinh(), 
                account.getSoDienThoai(), account.getEmail(), account.getDiaChi(), 
                account.getTrangThai(), account.getQuyen(), account.getMaKhoHang()});
        }
    }

    private TaiKhoanDTO inputAccountInfo(TaiKhoanDTO account) {
    // Tạo các trường nhập liệu
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JTextField fullNameField = new JTextField();
    JTextField phoneField = new JTextField();
    JTextField emailField = new JTextField();
    JComboBox<String> roleComboBox = new JComboBox<>(new String[] {"User", "Admin"});
    JTextField addressField = new JTextField();
    JTextField birthdayField = new JTextField();
    
    // Tạo các checkbox giới tính và nhóm chúng
    JRadioButton femaleRadioButton = new JRadioButton("Nữ");
    JRadioButton maleRadioButton = new JRadioButton("Nam");
    ButtonGroup genderGroup = new ButtonGroup();
    genderGroup.add(femaleRadioButton);
    genderGroup.add(maleRadioButton);
    
    // Nếu đối tượng tài khoản được truyền vào không rỗng, điền dữ liệu vào các trường nhập liệu mặc định
    if (account != null) {
        usernameField.setText(account.getTenDangNhap());
        passwordField.setText(account.getMatKhau());
        fullNameField.setText(account.getHoVaTen());
        phoneField.setText(account.getSoDienThoai());
        emailField.setText(account.getEmail());
        roleComboBox.setSelectedItem(account.getQuyen());
        addressField.setText(account.getDiaChi());
        birthdayField.setText(account.getNgaySinh());
        if ("Female".equals(account.getGioiTinh())) {
            femaleRadioButton.setSelected(true);
        } else if ("Male".equals(account.getGioiTinh())) {
            maleRadioButton.setSelected(true);
        }
    }

    // Tạo mảng Object chứa các trường nhập liệu
    Object[] message = {
        "Tên Đăng Nhập:", usernameField,
        "Mật Khẩu:", passwordField,
        "Họ Và Tên:", fullNameField,
        "Số Điện Thoại:", phoneField,
        "Email:", emailField,
        "Vai Trò:", roleComboBox,
        "Địa Chỉ:", addressField,
        "Ngày Sinh:", birthdayField,
        "Giới Tính:", new JPanel() {{
            add(femaleRadioButton);
            add(maleRadioButton);
        }},
    };

    // Hiển thị hộp thoại nhập thông tin tài khoản
    int option = JOptionPane.showConfirmDialog(null, message, "Thông Tin Tài Khoản", JOptionPane.OK_CANCEL_OPTION);

    if (option == JOptionPane.OK_OPTION) {
        // Khởi tạo đối tượng tài khoản nếu chưa có
        if (account == null) {
            account = new TaiKhoanDTO();
        }

        // Lấy các thông tin nhập vào
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String fullName = fullNameField.getText();
        String phoneNumber = phoneField.getText();
        String email = emailField.getText();
        String role = roleComboBox.getSelectedItem().toString();
        String address = addressField.getText();
        String birthday = birthdayField.getText();
        String gender = "";
        if (femaleRadioButton.isSelected()) {
        gender = "Female";
        } else if (maleRadioButton.isSelected()) {
        gender = "Male";
        }

        
        if (username.length() < 6) {
            JOptionPane.showMessageDialog(null, "Tên đăng nhập phải có ít nhất 6 ký tự.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Kiểm tra chiều dài mật khẩu
        if (password.length() < 8) {
            JOptionPane.showMessageDialog(null, "Mật khẩu phải có ít nhất 8 ký tự.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Kiểm tra tính hợp lệ của dữ liệu
        if (!fullName.isEmpty() && fullName.length() < 7) {
            JOptionPane.showMessageDialog(null, "Họ và tên phải có ít nhất 7 ký tự.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        if (!address.isEmpty() && address.length() < 10) {
            JOptionPane.showMessageDialog(null, "Địa chỉ phải có ít nhất 10 ký tự.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (!phoneNumber.isEmpty() && !phoneNumber.matches("\\d{10,11}")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (!email.isEmpty() && !email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            JOptionPane.showMessageDialog(null, "Email không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            if (!birthday.isEmpty()) {
                sdf.parse(birthday);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ngày sinh không hợp lệ. Vui lòng nhập lại theo định dạng yyyy-MM-dd.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Cập nhật thông tin vào đối tượng tài khoản
        account.setTenDangNhap(username);
        account.setMatKhau(password);
        account.setHoVaTen(fullName.isEmpty() ? null : fullName);
        account.setQuyen(role);
        account.setSoDienThoai(phoneNumber.isEmpty() ? null : phoneNumber);
        account.setEmail(email.isEmpty() ? null : email);
        account.setDiaChi(address.isEmpty() ? null : address);
        account.setNgaySinh(birthday.isEmpty() ? null : birthday);
        account.setGioiTinh(gender);

        return account;
    } else {
        return null;
    }
}


}
