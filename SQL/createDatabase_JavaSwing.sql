DROP DATABASE IF EXISTS `JavaSwing_Database`;
CREATE DATABASE IF NOT EXISTS `JavaSwing_Database`;
USE `JavaSwing_Database`;

/* _____________________________________________________________________ CÁC BẢNG LIÊN QUAN TỚI TÀI KHOẢN _________________________________________________________*/

DROP TABLE IF EXISTS `KhoHang`;
CREATE TABLE IF NOT EXISTS `KhoHang` (
    `MaKhoHang` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `TenKhoHang` NVARCHAR(255) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS `TaiKhoan`;
CREATE TABLE IF NOT EXISTS `TaiKhoan` (
    `MaTaiKhoan`  			INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	`TenDangNhap`      		NVARCHAR(255) 			NOT NULL 	UNIQUE,
    `MatKhau` 				NVARCHAR(255) 			NOT NULL,
    `TrangThai`     		BOOLEAN                 NOT NULL    DEFAULT true,
    `NgayTao`				DATETIME                NOT NULL 	DEFAULT NOW(),
	`Quyen`					ENUM("User", "Admin"),
    `MaKhoHang`				INT UNSIGNED			NOT NULL,
	FOREIGN KEY (`MaKhoHang`) REFERENCES `KhoHang`(`MaKhoHang`)
);

DROP TABLE IF EXISTS `NguoiDung`;
CREATE TABLE IF NOT EXISTS `NguoiDung` (
    `MaNguoiDung`  		INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `HoTen` 			NVARCHAR(255) 			NOT NULL,
    `NgaySinh`			DATE 					NOT NULL,
    `GioiTinh`  		ENUM('Male', 'Female')  NOT NULL,
    `SoDienThoai` 		NVARCHAR(20) 			NOT NULL,
    `Email` 			NVARCHAR(255) 			NOT NULL,
	`DiaChi` 			NVARCHAR(255) 			NOT NULL,
	FOREIGN KEY (`MaNguoiDung`) REFERENCES `TaiKhoan`(`MaTaiKhoan`)
);

/* _____________________________________________________________________ CÁC BẢNG LIÊN QUAN TỚI SẢN PHẨM _________________________________________________________*/



DROP TABLE IF EXISTS `LoaiSanPham`;
CREATE TABLE IF NOT EXISTS `LoaiSanPham` (
    `MaLoaiSanPham` 	INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `TenLoaiSanPham` 	NVARCHAR(255) NOT NULL UNIQUE,
	`MaKhoHang` 		INT UNSIGNED 			NOT NULL,
	FOREIGN KEY (`MaKhoHang`) REFERENCES `KhoHang`(`MaKhoHang`)

);

DROP TABLE IF EXISTS `SanPham`;
CREATE TABLE IF NOT EXISTS `SanPham` (
    `MaSanPham` 		INT  UNSIGNED 			PRIMARY KEY AUTO_INCREMENT,
    `TenSanPham` 		NVARCHAR(255) 			NOT NULL 	UNIQUE,
    `XuatXu` 			NVARCHAR(255) 			NOT NULL,
    `Gia` 				INT UNSIGNED 			NOT NULL,
    `SoLuongConLai`	 	INT UNSIGNED 			NOT NULL 	DEFAULT 0,
	`TrangThai` 		BOOLEAN 				NOT NULL    DEFAULT true,	
    `AnhMinhHoa` 		LONGTEXT,
    `MaLoaiSanPham` 	INT UNSIGNED 			NOT NULL,
	`MaKhoHang` 		INT UNSIGNED 			NOT NULL,

    FOREIGN KEY (`MaLoaiSanPHam`) 	REFERENCES `LoaiSanPham`(`MaLoaiSanPham`),
	FOREIGN KEY (`MaKhoHang`) 		REFERENCES `KhoHang`(`MaKhoHang`)

);

/* _____________________________________________________________________ CÁC BẢNG LIÊN QUAN TỚI NGHIEP VU NHAP KHO _________________________________________________________*/
DROP TABLE IF EXISTS `NhaCungCap`;
CREATE TABLE IF NOT EXISTS  `NhaCungCap` (
    `MaNCC` 		INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `TenNCC` 		NVARCHAR(255) 		NOT NULL UNIQUE,
    `SoDienThoai` 	NVARCHAR(20) 		NOT NULL,
    `Email` 		NVARCHAR(255) 		NOT NULL,
    
	`MaKhoHang` 		INT UNSIGNED 			NOT NULL,
	FOREIGN KEY (`MaKhoHang`) REFERENCES `KhoHang`(`MaKhoHang`)
);

DROP TABLE IF EXISTS `PhieuNhapKho`;
CREATE TABLE IF NOT EXISTS  `PhieuNhapKho` (
    `MaPhieu` 			INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `NgayNhapKho` 		DATETIME 				NOT NULL,
    `TongGiaTri` 		INT UNSIGNED 			NOT NULL,
    `MaNCC`				INT UNSIGNED,
	`MaKhoHang` 		INT UNSIGNED 			NOT NULL,

    FOREIGN KEY (`MaNCC`) 		REFERENCES `NhaCungCap`(`MaNCC`),
	FOREIGN KEY (`MaKhoHang`) REFERENCES `KhoHang`(`MaKhoHang`)
);

DROP TABLE IF EXISTS `CTPNK`;
CREATE TABLE IF NOT EXISTS  `CTPNK` (
    `DonGiaNhap` 		INT UNSIGNED 	NOT NULL,
	`SoLuong` 			INT UNSIGNED  	NOT NULL,
	`ThanhTien` 		INT UNSIGNED 	NOT NULL,
    `MaPhieu` 			INT UNSIGNED,
    `MaSanPham` 		INT UNSIGNED,
    FOREIGN KEY (`MaPhieu`) 	REFERENCES `PhieuNhapKho`(`MaPhieu`),
	FOREIGN KEY (`MaSanPham`) 		REFERENCES `SanPham`(`MaSanPham`),
    PRIMARY KEY (`MaPhieu`, `MaSanPham`)
);


/* _____________________________________________________________________ CÁC BẢNG LIÊN QUAN TỚI NGHIEP VU MUA HÀNG _________________________________________________________*/

DROP TABLE IF EXISTS `PhieuXuatKho`;
CREATE TABLE IF NOT EXISTS `PhieuXuatKho`(
	`MaPhieu`  					INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `NgayXuatKho` 				DATETIME NOT NULL, 
    `TongGiaTri` 				INT UNSIGNED NOT NULL,
	`MaKhoHang` 				INT UNSIGNED 			NOT NULL,

	FOREIGN KEY (`MaKhoHang`) REFERENCES `KhoHang`(`MaKhoHang`)

);


DROP TABLE IF EXISTS `CTPXK`;
CREATE TABLE IF NOT EXISTS  `CTPXK` (
	`SoLuong`          		INT UNSIGNED    NOT NULL,
    `ThanhTien`         	INT UNSIGNED    NOT NULL,
    `DonGia`            	INT UNSIGNED    NOT NULL,
    `MaPhieu`              	INT UNSIGNED,
    `MaSanPham`              INT UNSIGNED,
    
    
    FOREIGN KEY (`MaPhieu`) REFERENCES `PhieuXuatKho`(`MaPhieu`),
	FOREIGN KEY (`MaSanPham`) REFERENCES `SanPham`(`MaSanPham`),
    PRIMARY KEY (`MaPhieu`, `MaSanPham`)
);
