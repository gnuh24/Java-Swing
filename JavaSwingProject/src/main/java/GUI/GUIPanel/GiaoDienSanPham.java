package GUI.GUIPanel;


import BUS.ThongTinSanPham.LoaiSanPhamBUS;
import BUS.ThongTinSanPham.SanPhamBUS;
import DTO.ThongTinSanPham.SanPhamDTO;
import GUI.GUIDialog.SanPhamDialog;
import GUI.GUIThanhPhan.ButtonCustom;
import Others.CloundinaryServices;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import org.apache.poi.ss.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.*;
import org.apache.poi.xssf.usermodel.*;

public class GiaoDienSanPham extends JPanel implements ActionListener{

    private  JTable thongTin;
    private  DefaultTableModel model;
    private SanPhamBUS SPBUS= new SanPhamBUS();
    private LoaiSanPhamBUS LoaiSPBUS= new LoaiSanPhamBUS();
    private ArrayList<SanPhamDTO> listSP= SPBUS.getAll();
    private JComboBox<String> locSP;

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
        xoaSP = new ButtonCustom("Xóa Sản Phẩm", "C:\\Users\\Admin\\OneDrive\\Documents\\NetBeansProjects\\JavaHung\\JavaHung\\src\\main\\java\\Resources\\iconDelete24.png");
        themSP= new ButtonCustom("Thêm Sản Phẩm", "C:\\Users\\Admin\\OneDrive\\Documents\\NetBeansProjects\\JavaHung\\JavaHung\\src\\main\\java\\Resources\\iconAdd24.png");
        excelSP= new ButtonCustom("Xuất File Excel", "C:\\Users\\Admin\\OneDrive\\Documents\\NetBeansProjects\\JavaHung\\JavaHung\\src\\main\\java\\Resources\\iconExcel24.png");
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
        locSP= new JComboBox<>(LoaiSPBUS.tenLoaiSanPham());
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
        String[] colum = new String[]{"STT", "Hình Ảnh", "Mã Sản Phẩm", "Tên sản phẩm","Loại Sản Phẩm", "Xuất xứ",  "Số lượng", "Giá"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(colum);
        thongTin = new JTable(model);
        thongTin.setPreferredScrollableViewportSize(thongTin.getPreferredSize());
        loadDuLieuTuDatabase(listSP);
        chinhSuaGiaoDienTable();


        JScrollPane dulieuSP = new JScrollPane(thongTin);


        panelDuoi.add(timKiemLocSP,BorderLayout.NORTH);
        panelDuoi.add(dulieuSP,BorderLayout.CENTER);

        //// mục sự kiện chính cho giao diện
        // nút thêm sp
        themSP.addActionListener(this);
        // double click xem thông tin
        thongTin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable target = (JTable) e.getSource();
                if (e.getClickCount() == 2)
                {
                    SanPhamDialog spDia=new SanPhamDialog("Chi Tiết Sản Phẩm","ChiTiet",listSP.get(thongTin.getSelectedRow()));
                    System.out.println(listSP.get(thongTin.getSelectedRow()).getAnhMinhhoa());
                    loadDuLieuTuDatabase(new SanPhamBUS().getAll());
                    chinhSuaGiaoDienTable();
                }
            }
        });
        // nút xóa sản phẩm
        xoaSP.addActionListener(this);
        // lọc loại sản phẩm và tìm kiếm
        excelSP.addActionListener(this);
        locSP.addActionListener(this);
        timKiem.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent e){
                listSP=SPBUS.search(timKiem.getText());
                loadDuLieuTuDatabase(listSP);
                chinhSuaGiaoDienTable();
            }
        });


        this.add(panelTren,BorderLayout.NORTH);
        this.add(panelDuoi,BorderLayout.CENTER);
    }
    public void chinhSuaGiaoDienTable(){
        // Căn giữa tiêu đề của các cột
        //DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) thongTin.getTableHeader().getDefaultRenderer();
        //headerRenderer.setHorizontalAlignment(JLabel.CENTER);

        thongTin.setShowGrid(false);        thongTin.setShowHorizontalLines(true);

        thongTin.setDefaultEditor(Object.class, null);

        thongTin.setRowHeight(80);

        thongTin.getTableHeader().setReorderingAllowed(false);
        thongTin.getTableHeader().setPreferredSize(new Dimension(0,50));
        thongTin.getTableHeader().setFont(new Font("Arial",Font.BOLD,15));
        thongTin.getTableHeader().setBackground(Color.WHITE);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);



        for(int i=0; i < thongTin.getColumnCount();i++){
            // set kích thước, ngoài trừ cột chứa ảnh
            if( i!=1){
                thongTin.getColumnModel().getColumn(i).setPreferredWidth(150);
                if( i!=3)
                    thongTin.getColumnModel().getColumn(i).setCellRenderer(centerRenderer );
            }
        }
        thongTin.getColumnModel().getColumn(0).setPreferredWidth(60);
        thongTin.getColumnModel().getColumn(3).setPreferredWidth(350);
    }

    public void loadDuLieuTuDatabase(ArrayList<SanPhamDTO> listSP){
        DefaultTableModel dtm= new DefaultTableModel(){
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }};
        String[] colum = new String[]{"STT", "Hình Ảnh", "Mã Sản Phẩm", "Tên sản phẩm","Loại Sản Phẩm", "Xuất xứ",  "Số lượng", "Giá"};
        dtm.setColumnIdentifiers(colum);
        thongTin.setModel(dtm);
        thongTin.getColumnModel().getColumn(1).setPreferredWidth(150);
        thongTin.setDefaultEditor(Object.class, null);
        int dem=1;
        for(SanPhamDTO sanPham: listSP){
            try {
                URL img= new URL(CloundinaryServices.getUrlImage(sanPham.getAnhMinhhoa()));
                ImageIcon pic= new ImageIcon(img);
                Image scaleImage = pic.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH);
                Icon picFix= new ImageIcon(scaleImage);
                Object dong[]={dem,picFix,sanPham.getMaSanPham(),sanPham.getTenSanPham(),LoaiSPBUS.tenLoaiSanPham()[sanPham.getMaLoaiSanPham()],sanPham.getXuatXu(), sanPham.getSoLuongConLai(), sanPham.getGiaSanPham()};
                dtm.addRow(dong);
                dem+=1;
            } catch (MalformedURLException ex) {
                System.out.println("Ko lấy dc link ảnh");
            }
        }
    }

    public void xuatFileExcelSanPham(){
        XSSFWorkbook workbook=new XSSFWorkbook();
        // tạo sheet cho excel
        XSSFSheet sheet=workbook.createSheet("DanhSachSanPham");
        // khởi tạo dòng và ô
        XSSFRow row=null;
        XSSFCell cell=null;
        row=sheet.createRow(2); // xuống 2 dòng trong excel
        // tạo các ô tiêu đề bắt đầu từ dòng row theo cột 0
        String[] colum = new String[]{"STT","Mã Sản Phẩm", "Tên sản phẩm","Loại Sản Phẩm", "Xuất xứ",  "Số lượng", "Giá"};
        for ( int i=0; i < colum.length;i++){
            cell=row.createCell(i,CellType.STRING);
            cell.setCellValue(colum[i]);

        }


        for( int i=0; i < this.listSP.size();i++){
            // bắt đầu từ dòng 3 vì dòng 2 là tiêu đề
            row=sheet.createRow(3+i);
            cell=row.createCell(0,CellType.NUMERIC);
            cell.setCellValue(i+1);

            cell=row.createCell(1,CellType.NUMERIC);
            cell.setCellValue(listSP.get(i).getMaSanPham());

            cell=row.createCell(2,CellType.STRING);
            cell.setCellValue(listSP.get(i).getTenSanPham());

            cell=row.createCell(3,CellType.STRING);
            cell.setCellValue(LoaiSPBUS.tenLoaiSanPham()[listSP.get(i).getMaLoaiSanPham()]);

            cell=row.createCell(4,CellType.STRING);
            cell.setCellValue(listSP.get(i).getXuatXu());

            cell=row.createCell(5,CellType.NUMERIC);
            cell.setCellValue(listSP.get(i).getSoLuongConLai());

            cell=row.createCell(6,CellType.NUMERIC);
            cell.setCellValue(listSP.get(i).getGiaSanPham());
        }
        File f= new File("danhSachSanPham.xlsx");
        try {
            FileOutputStream file= new FileOutputStream(f);
            workbook.write(file);
            file.close();

        }catch (FileNotFoundException e){
            System.out.println("Lỗi ko tìm dc file");
        } catch (IOException ex) {
            System.out.println("Lỗi IO");
        }
        JOptionPane.showMessageDialog(this, "Tạo file thành công");
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        String lenh=ae.getActionCommand();
        SanPhamDialog ab= null;
        if(lenh.equals("comboBoxChanged")){
            if (locSP.getSelectedItem().toString().equals("Tất cả"))
                listSP=SPBUS.getAll();
            else listSP=SPBUS.search(locSP.getSelectedItem().toString());
            loadDuLieuTuDatabase(listSP);
            chinhSuaGiaoDienTable();
        }else if(lenh.equals("Thêm Sản Phẩm")){
            ab=new SanPhamDialog(this,"Thêm SP mới","Add");
            listSP=SPBUS.getAll();
            loadDuLieuTuDatabase(listSP);
            chinhSuaGiaoDienTable();
        }
        else if(lenh.equals("Xuất File Excel")){
            xuatFileExcelSanPham();
        }

        else if(lenh.equals("Xóa Sản Phẩm") && thongTin.getSelectedRow()!=-1){
            int luaChon=JOptionPane.showConfirmDialog(this,"Chắc chắn xóa sản phẩm ?","Xóa Sản Phẩm",JOptionPane.YES_NO_OPTION);
            if( luaChon==0)
            {
                SPBUS.delete(listSP.get(thongTin.getSelectedRow()));
                JOptionPane.showMessageDialog(this, "Sản phẩm đã xóa");
                loadDuLieuTuDatabase(SPBUS.getAll());
                chinhSuaGiaoDienTable();
            }
        }else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa");
        }
    }

    public static void main(String[] args) {

        new GiaoDienSanPham();

    }


}
