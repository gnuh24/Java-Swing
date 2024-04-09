package GUI.GUIPanel;


import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class AdminUI extends JPanel {
    private JTextField searchField;

    public AdminUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel searchPanel = new JPanel();
        JLabel searchLabel = new JLabel("Tìm kiếm tên nhân viên:");
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Tìm kiếm");
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Thêm");
        JButton editButton = new JButton("Sửa");
        JButton deleteButton = new JButton("Xóa");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        
        String[][] data = {};
        String[] columnName = {"Tên Nhân Viên", "Tên đăng nhập","Email","Vai Trò","Trạng Thái"};
        DefaultTableModel model = new DefaultTableModel(data, columnName);
        JTable table = new JTable(model);


        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.setPreferredSize(new Dimension(1000, 700));

        add(mainPanel);
        setPreferredSize(new Dimension(1000,800));
        setVisible(true);
    }
}
