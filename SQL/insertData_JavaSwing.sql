USE `JavaSwing_Database`;


/* Dữ liệu mẫu cho bảng TaiKhoan */
INSERT INTO `TaiKhoan` (`TenDangNhap`, `MatKhau`, `TrangThai`, `Quyen`) VALUES 
('admin', 'admin123', true, 'Admin'),
('user1', 'user123', true, 'User'),
('user2', 'user456', true, 'User');

/* Dữ liệu mẫu cho bảng NguoiDung */
INSERT INTO `NguoiDung` (`MaTK`, `HoTen`, `NgaySinh`, `GioiTinh`, `SoDienThoai`, `Email`, `DiaChi`) VALUES
(1, 'Admin User', '1990-01-01', 'Male', '0123456789', 'admin@example.com', '123 Admin Street'),
(2, 'Normal User 1', '1995-05-05', 'Female', '0123456789', 'user1@example.com', '456 User Street'),
(3, 'Normal User 2', '1998-08-08', 'Male', '0123456789', 'user2@example.com', '789 User Street');

/* Dữ liệu mẫu cho bảng KhoHang */
INSERT INTO `KhoHang` (`TenKhoHang`) VALUES
('Kho A'),
('Kho B'),
('Kho C');

/* Dữ liệu mẫu cho bảng LoaiSanPham */
INSERT INTO `LoaiSanPham` (`TenLoaiSanPham`, `MaKhoHang`) VALUES
('Loai SP 1', 1),
('Loai SP 2', 2),
('Loai SP 3', 3);

/* Dữ liệu mẫu cho bảng SanPham */
INSERT INTO `SanPham` (`TenSP`, `XuatXu`, `Gia`, `SoLuongConLai`, `TrangThai`, `SoLuot`, `MaLoaiSanPham`, `MaKhoHang`) VALUES
('SP 1', 'Viet Nam', 100000, 50, true, 10, 1, 1),
('SP 2', 'Trung Quoc', 50000, 30, true, 5, 2, 2),
('SP 3', 'Han Quoc', 150000, 20, true, 15, 3, 3);

/* Dữ liệu mẫu cho bảng NhaCungCap */
INSERT INTO `NhaCungCap` (`TenNCC`, `SoDienThoai`, `Email`, `MaKhoHang`) VALUES
('NCC 1', '0123456789', 'ncc1@example.com', 1),
('NCC 2', '0123456789', 'ncc2@example.com', 2),
('NCC 3', '0123456789', 'ncc3@example.com', 3);

/* Dữ liệu mẫu cho bảng PhieuNhapKho */
INSERT INTO `PhieuNhapKho` (`NgayNhapKho`, `TongGiaTri`, `MaNCC`, `MaNguoiNhap`, `MaKhoHang`) VALUES
('2024-01-15 08:00:00', 500000, 1, 1, 1),
('2024-01-16 09:00:00', 750000, 2, 2, 2),
('2024-01-17 10:00:00', 1000000, 3, 3, 3);

/* Dữ liệu mẫu cho bảng CTPNK */
INSERT INTO `CTPNK` (`DonGiaNhap`, `SoLuong`, `ThanhTien`, `MaPhieu`, `MaSP`) VALUES
(100000, 5, 500000, 1, 1),
(50000, 10, 500000, 2, 2),
(150000, 5, 750000, 3, 3);

/* Dữ liệu mẫu cho bảng PhieuXuatKho */
INSERT INTO `PhieuXuatKho` (`NgayXuatKho`, `TongGiaTri`, `MaNguoiLap`, `MaKhoHang`) VALUES
('2024-01-15 08:00:00', 500000, 1, 1),
('2024-01-16 09:00:00', 750000, 2, 2),
('2024-01-17 10:00:00', 1000000, 3, 3);

/* Dữ liệu mẫu cho bảng CTPXK */
INSERT INTO `CTPXK` (`SoLuong`, `ThanhTien`, `DonGia`, `MaPhieu`, `MaSP`) VALUES
(5, 500000, 100000, 1, 1),
(10, 500000, 50000, 2, 2),
(5, 750000, 150000, 3, 3);
