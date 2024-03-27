USE `JavaSwing_Database`;

/* Dữ liệu mẫu cho bảng KhoHang */
INSERT INTO `KhoHang` (`TenKhoHang`) VALUES
('Kho hàng của THug24'),
('Kho hàng của AnDo'),
('Kho hàng của Thanh Điền'),
('Kho hàng của MinhVi');

/* Dữ liệu mẫu cho bảng TaiKhoan */
INSERT INTO `TaiKhoan` (`TenDangNhap`,		 	`MatKhau`, `TrangThai`, `Quyen`, `MaKhoHang`) 
VALUES 
						('THug24', 				'123456',	true,		'Admin',	1),
						('An1808', 				'123456', 	true, 		'User',		2),
						('YangHoHocMon', 		'123456', 	true, 		'User',		3),
						('MinhViDepTrai04', 	'123456', 	true, 		'User',		4);


/* Dữ liệu mẫu cho bảng NguoiDung */
INSERT INTO `NguoiDung` (`MaNguoiDung`, `HoTen`, 				`NgaySinh`,		 	`GioiTinh`, `SoDienThoai`, `Email`, 					`DiaChi`) VALUES
						(1, 		'Ngô Tuấn Hưng', 			'1990-01-01', 		'Male', '	0123456789', 'thug24@gmail.com', 			'DKP1221'),
						(2, 		'Diệp Thụy An', 			'1995-05-05', 		'Male', '	0123456789', 'an1808@example.com', 			'DCT1221'),
						(3, 		'Nguyễn Thanh Điền', 		'1998-08-08', 		'Male', '	0123456789', 'yangHoHocHon@example.com', 	'DKP1222'),
						(4, 		'Dương Văn Minh Vi', 		'1998-08-08', 		'Male', '	0123456789', 'minhViDepTrai04@example.com', 'DCT1222');




/* Dữ liệu mẫu cho bảng LoaiSanPham */
INSERT INTO `LoaiSanPham` (`TenLoaiSanPham`, `MaKhoHang`) VALUES
('Lego', 1),
('Figure', 1),
('Xe mô tô', 1),

('Laptop', 2),
('Quần áo', 2);


/* Dữ liệu mẫu cho bảng SanPham */
INSERT INTO `SanPham` (`TenSanPham`, 											`XuatXu`, 		`Gia`, 		`SoLuongConLai`, 	`TrangThai`, `SoLuot`, `MaLoaiSanPham`, `MaKhoHang`, `AnhMinhHoa`) VALUES
						('Lego Ninjago 9447 Lasha"s bite cycle', 				'Đan Mạch', 	864000, 		10, 				true, 		10, 		1, 				1,		""	),
						('SP 2', 												'Trung Quoc', 	50000, 			30, 				true,		 5, 		1, 				1,		""	),
						('SP 3', 												'Han Quoc', 	150000, 		20, 				true, 		15, 		1, 				1,		""	);

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
