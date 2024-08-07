CREATE DATABASE QL_BAN_QUAN_AO3;
USE QL_BAN_QUAN_AO3;


CREATE TABLE "THUONGHIEU" (
    "ID" INT NOT NULL IDENTITY(1,1),
    "Ten" NVARCHAR(255) NOT NULL,
    "MoTa" NVARCHAR(255) NOT NULL DEFAULT N'Chưa có',
    "TrangThai" BIT NOT NULL DEFAULT 1,
    PRIMARY KEY ("ID")
);

CREATE TABLE "DANHMUC" (
    "ID" INT NOT NULL IDENTITY(1,1),
    "Ten" NVARCHAR(255) NOT NULL,
    "TrangThai" BIT NOT NULL DEFAULT 1,
    PRIMARY KEY ("ID")
);

CREATE TABLE "SANPHAM" (
    "ID" INT NOT NULL IDENTITY(1,1),
    "TenSP" NVARCHAR(255) NOT NULL,
    "ID_DM" INT NOT NULL,
    "ID_TH" INT NOT NULL,
    "MoTa" NVARCHAR(255) NOT NULL DEFAULT N'Chưa có',
    "AnhSP" NVARCHAR(255) NOT NULL,
    "SoLuong" INT NOT NULL,
    "Gia" DECIMAL(10, 2) NOT NULL,
    "TrangThai" BIT NOT NULL DEFAULT 1,
    PRIMARY KEY ("ID"),
    FOREIGN KEY ("ID_TH") REFERENCES "THUONGHIEU"("ID"),
    FOREIGN KEY ("ID_DM") REFERENCES "DANHMUC"("ID")
);

CREATE TABLE "SIZE" (
    "ID" INT NOT NULL IDENTITY(1,1),
    "Ten" CHAR(255) NOT NULL,
    "MoTa" NVARCHAR(255) NOT NULL,
    PRIMARY KEY ("ID")
);

CREATE TABLE "CTSIZE" (
    "ID" INT NOT NULL IDENTITY(1,1),
    "ID_SP" INT NOT NULL,
    "ID_SIZE" INT NOT NULL,
    "TrangThai" BIT NOT NULL DEFAULT 1,
    PRIMARY KEY ("ID"),
    FOREIGN KEY ("ID_SP") REFERENCES "SANPHAM"("ID"),
    FOREIGN KEY ("ID_SIZE") REFERENCES "SIZE"("ID")
);

CREATE TABLE "MAUSAC" (
    "ID" INT NOT NULL IDENTITY(1,1),
    "MauSac" NVARCHAR(255) NOT NULL,
    PRIMARY KEY ("ID")
);

CREATE TABLE "CTMAUSAC" (
    "ID" INT NOT NULL IDENTITY(1,1),
    "ID_MS" INT NOT NULL,
    "ID_SP" INT NOT NULL,
    "TrangThai" BIT NOT NULL DEFAULT 1,
    PRIMARY KEY ("ID"),
    FOREIGN KEY ("ID_MS") REFERENCES "MAUSAC"("ID"),
    FOREIGN KEY ("ID_SP") REFERENCES "SANPHAM"("ID")
);

CREATE TABLE "NHANVIEN" (
    "ID" INT NOT NULL IDENTITY(1,1),
    "MaNV" CHAR(255) NOT NULL,
    "HoTen" NVARCHAR(255) NOT NULL,
    "SDT" CHAR(15) NOT NULL,
    "Email" VARCHAR(255) NOT NULL,
    "TenDN" VARCHAR(255) NOT NULL,
    "MatKhau" VARCHAR(255) NOT NULL,
    "QuyenHan" BIT NOT NULL,
    "TrangThai" BIT NOT NULL DEFAULT 1,
    PRIMARY KEY ("ID")
);

CREATE TABLE "VOUCHER" (
    "MaVocher" CHAR(255) NOT NULL,
    "PhanTramGiam" int NOT null,
    "NgayBatDau" DATE NOT NULL,
    "NgayKetThuc" DATE NOT NULL,
    "TrangThai" BIT NOT NULL DEFAULT 1,
    PRIMARY KEY ("MaVocher")
);

CREATE TABLE "HOADON" (
    "ID" INT NOT NULL IDENTITY(1,1),
    "ID_NV" INT NOT NULL,
    "MaVocher" CHAR(255) NOT NULL,
    "ThoiGian" DATETIME NOT NULL,
    "GhiChu" NVARCHAR(255) NOT NULL DEFAULT N'Đã thanh toán đủ',
    "TT_ThanhToan" INT NOT NULL,
    -- "TenKhachHang" NVARCHAR(255) NOT NULL,
    -- "SoDienThoaiKH" CHAR(15) NOT NULL,
    "TongTien" DECIMAL(10, 2) NOT NULL,
    "TrangThai" INT NOT NULL DEFAULT 1,
    PRIMARY KEY ("ID"),
    FOREIGN KEY ("ID_NV") REFERENCES "NHANVIEN"("ID"),
    FOREIGN KEY ("MaVocher") REFERENCES "VOUCHER"("MaVocher")
);

CREATE TABLE "CTHOADON" (
    "ID" INT NOT NULL IDENTITY(1,1),
    "ID_HD" INT NOT NULL,
    "ID_SP" INT NOT NULL,
    "SoLuong" INT NOT NULL,
    "Gia" DECIMAL(10, 2) NOT NULL,
    "TrangThai" BIT NOT NULL DEFAULT 1,
    PRIMARY KEY ("ID"),
    FOREIGN KEY ("ID_HD") REFERENCES "HOADON"("ID"),
    FOREIGN KEY ("ID_SP") REFERENCES "SANPHAM"("ID")
);
-- insert data
-- Insert rows into THUONGHIEU
INSERT INTO "THUONGHIEU" ("Ten", "MoTa", "TrangThai") VALUES 
(N'Việt Tiến', N'Thương hiệu thời trang công sở', 1),
(N'An Phước', N'Thương hiệu thời trang nam cao cấp', 1),
(N'May 10', N'Thương hiệu thời trang công sở', 1),
(N'Canifa', N'Thương hiệu thời trang gia đình', 1),
(N'Blue Exchange', N'Thương hiệu thời trang trẻ', 1);

-- Insert rows into DANHMUC
INSERT INTO "DANHMUC" ("Ten", "TrangThai") VALUES 
(N'Áo sơ mi', 1),
(N'Quần tây', 1),
(N'Áo vest', 1),
(N'Áo khoác', 1),
(N'Áo thun', 1),
(N'Váy đầm', 1),
(N'Quần jeans', 1),
(N'Áo len', 1),
(N'Giày dép', 1),
(N'Phụ kiện thời trang', 1);

-- Insert rows into SANPHAM
INSERT INTO "SANPHAM" ("TenSP", "ID_DM", "ID_TH", "MoTa", "AnhSP", "SoLuong", "Gia", "TrangThai") VALUES 
(N'Áo sơ mi Việt Tiến', 1, 1, N'Áo sơ mi công sở', N'aosomi_viettien.jpg', 50, 300000, 1),
(N'Quần tây An Phước', 2, 2, N'Quần tây nam cao cấp', N'quantay_anphuoc.jpg', 40, 500000, 1),
(N'Áo vest May 10', 3, 3, N'Áo vest công sở', N'aovest_may10.jpg', 30, 1500000, 1),
(N'Áo khoác Canifa', 4, 4, N'Áo khoác gia đình', N'aokhoac_canifa.jpg', 25, 800000, 1),
(N'Áo thun Blue Exchange', 5, 5, N'Áo thun trẻ', N'aothun_blueexchange.jpg', 20, 200000, 1),
(N'Váy đầm Việt Tiến', 6, 1, N'Váy đầm công sở', N'vaydam_viettien.jpg', 15, 500000, 1),
(N'Quần jeans An Phước', 7, 2, N'Quần jeans cao cấp', N'quanjeans_anphuoc.jpg', 20, 700000, 1),
(N'Áo len May 10', 8, 3, N'Áo len công sở', N'aolen_may10.jpg', 25, 400000, 1),
(N'Giày dép Canifa', 9, 4, N'Giày dép gia đình', N'giaydep_canifa.jpg', 30, 600000, 1),
(N'Phụ kiện Blue Exchange', 10, 5, N'Phụ kiện thời trang trẻ', N'phukien_blueexchange.jpg', 35, 200000, 1),
(N'Áo sơ mi dài tay Việt Tiến', 1, 1, N'Áo sơ mi dài tay công sở', N'aosomidaitay_viettien.jpg', 50, 350000, 1),
(N'Quần tây nữ An Phước', 2, 2, N'Quần tây nữ cao cấp', N'quantaynu_anphuoc.jpg', 40, 550000, 1),
(N'Áo vest nữ May 10', 3, 3, N'Áo vest nữ công sở', N'aovestnu_may10.jpg', 30, 1600000, 1),
(N'Áo khoác nam Canifa', 4, 4, N'Áo khoác nam gia đình', N'aokhoacnam_canifa.jpg', 25, 850000, 1),
(N'Áo thun nam Blue Exchange', 5, 5, N'Áo thun nam trẻ', N'aothunnam_blueexchange.jpg', 20, 220000, 1),
(N'Váy đầm dạ hội Việt Tiến', 6, 1, N'Váy đầm dạ hội', N'vaydamdahoi_viettien.jpg', 15, 700000, 1),
(N'Quần jeans nữ An Phước', 7, 2, N'Quần jeans nữ cao cấp', N'quanjeansnu_anphuoc.jpg', 20, 750000, 1),
(N'Áo len nam May 10', 8, 3, N'Áo len nam công sở', N'aolennam_may10.jpg', 25, 450000, 1),
(N'Giày thể thao Canifa', 9, 4, N'Giày thể thao gia đình', N'giaythethao_canifa.jpg', 30, 650000, 1),
(N'Phụ kiện nữ Blue Exchange', 10, 5, N'Phụ kiện thời trang nữ trẻ', N'phukiennu_blueexchange.jpg', 35, 250000, 1),
(N'Áo sơ mi ngắn tay Việt Tiến', 1, 1, N'Áo sơ mi ngắn tay công sở', N'aosomingantay_viettien.jpg', 50, 320000, 1),
(N'Quần tây nam An Phước', 2, 2, N'Quần tây nam cao cấp', N'quantaynam_anphuoc.jpg', 40, 520000, 1),
(N'Áo vest nam May 10', 3, 3, N'Áo vest nam công sở', N'aovestnam_may10.jpg', 30, 1550000, 1),
(N'Áo khoác nữ Canifa', 4, 4, N'Áo khoác nữ gia đình', N'aokhoacnu_canifa.jpg', 25, 820000, 1),
(N'Áo thun nữ Blue Exchange', 5, 5, N'Áo thun nữ trẻ', N'aothunnu_blueexchange.jpg', 20, 210000, 1),
(N'Váy đầm công sở Việt Tiến', 6, 1, N'Váy đầm công sở cao cấp', N'vaydamcongso_viettien.jpg', 15, 520000, 1),
(N'Quần jeans nam An Phước', 7, 2, N'Quần jeans nam cao cấp', N'quanjeansnam_anphuoc.jpg', 20, 720000, 1),
(N'Áo len nữ May 10', 8, 3, N'Áo len nữ công sở', N'aolennu_may10.jpg', 25, 430000, 1),
(N'Giày lười Canifa', 9, 4, N'Giày lười gia đình', N'giayluoi_canifa.jpg', 30, 670000, 1),
(N'Phụ kiện nam Blue Exchange', 10, 5, N'Phụ kiện thời trang nam trẻ', N'phukiennam_blueexchange.jpg', 35, 230000, 1),
(N'Áo sơ mi nam Việt Tiến', 1, 1, N'Áo sơ mi nam công sở', N'aosominam_viettien.jpg', 50, 310000, 1),
(N'Quần tây nữ công sở An Phước', 2, 2, N'Quần tây nữ công sở cao cấp', N'quantaynucongso_anphuoc.jpg', 40, 530000, 1),
(N'Áo vest nữ công sở May 10', 3, 3, N'Áo vest nữ công sở cao cấp', N'aovestnucongso_may10.jpg', 30, 1580000, 1),
(N'Áo khoác trẻ em Canifa', 4, 4, N'Áo khoác trẻ em gia đình', N'aokhoactreem_canifa.jpg', 25, 810000, 1),
(N'Áo thun trẻ em Blue Exchange', 5, 5, N'Áo thun trẻ em trẻ', N'aothuntreem_blueexchange.jpg', 20, 215000, 1);


-- Insert rows into SIZE
INSERT INTO "SIZE" ("Ten", "MoTa") VALUES 
(N'S', N'Size nhỏ'),
(N'M', N'Size vừa'),
(N'L', N'Size lớn'),
(N'XL', N'Size rất lớn'),
(N'XXL', N'Size cực lớn');

-- Insert rows into MAUSAC
INSERT INTO "MAUSAC" ("MauSac") VALUES 
(N'Đỏ'),
(N'Xanh'),
(N'Vàng'),
(N'Trắng'),
(N'Đen');

-- Insert rows into CTSIZE
INSERT INTO "CTSIZE" ("ID_SP", "ID_SIZE", "TrangThai") VALUES 
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1);

-- Insert rows into CTMAUSAC
INSERT INTO "CTMAUSAC" ("ID_MS", "ID_SP", "TrangThai") VALUES 
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1);

-- Insert rows into NHANVIEN
INSERT INTO "NHANVIEN" ("MaNV", "HoTen", "SDT", "Email", "TenDN", "MatKhau", "QuyenHan", "TrangThai") VALUES 
('NV001', N'Nguyễn Văn Hoà', '0123456789', 'nguyenvana@example.com', 'nguyenvana', 'password123', 1, 1),
('NV002', N'Lê Thị Bích', '0987654321', 'lethib@example.com', 'lethib', 'password123', 0, 1),
('NV003', N'Trần Văn Cường', '0912345678', 'tranvanc@example.com', 'tranvanc', 'password123', 0, 1);

SELECT * FROM NhanVien WHERE TenDN = 'nguyevana' AND MatKhau='password123'


GO;
CREATE VIEW ProductView AS
SELECT 
    sp.ID AS ProductID,
    sp.TenSP AS ProductName,
    sp.MoTa AS ProductDescription,
    sp.AnhSP AS ProductImage,
    sp.SoLuong AS Quantity,
    sp.Gia AS Price,
    sp.TrangThai AS ProductStatus,
    dm.Ten AS CategoryName,
    th.Ten AS BrandName,
    STRING_AGG(s.Ten, ', ') AS SizeNames,  -- Use STRING_AGG in SQL Server
    STRING_AGG(ms.MauSac, ', ') AS ColorNames  -- Use STRING_AGG in SQL Server
FROM 
    SANPHAM sp
    LEFT JOIN CTSIZE cts ON sp.ID = cts.ID_SP
    LEFT JOIN SIZE s ON cts.ID_SIZE = s.ID
    LEFT JOIN CTMAUSAC ctm ON sp.ID = ctm.ID_SP
    LEFT JOIN MAUSAC ms ON ctm.ID_MS = ms.ID
    INNER JOIN DANHMUC dm ON sp.ID_DM = dm.ID
    INNER JOIN THUONGHIEU th ON sp.ID_TH = th.ID
WHERE 
    sp.TrangThai = 1
    AND (cts.TrangThai = 1 OR cts.TrangThai IS NULL)
    AND (ctm.TrangThai = 1 OR ctm.TrangThai IS NULL)
GROUP BY 
    sp.ID, sp.TenSP, sp.MoTa, sp.SoLuong, sp.Gia, sp.TrangThai,sp.AnhSP, dm.Ten, th.Ten;


GO;
    INSERT INTO VOUCHER (MaVocher, PhanTramGiam, NgayBatDau, NgayKetThuc, TrangThai) VALUES
('V000', 10, '2024-08-01', '2024-08-15', 1),
('VCH001', 10, '2024-08-01', '2024-08-15', 1),
('VCH002', 15, '2024-08-05', '2024-08-20', 1),
('VCH003', 20, '2024-08-10', '2024-08-25', 1),
('VCH004', 25, '2024-08-15', '2024-09-01', 1),
('VCH005', 30, '2024-08-20', '2024-09-05', 1),
('VCH006', 5, '2024-08-25', '2024-09-10', 1),
('VCH007', 12, '2024-08-30', '2024-09-15', 1),
('VCH008', 18, '2024-09-01', '2024-09-20', 1),
('VCH009', 22, '2024-09-05', '2024-09-25', 1),
('VCH010', 8, '2024-09-10', '2024-09-30', 1);

INSERT INTO CTSIZE (ID_SP, ID_SIZE, TrangThai) VALUES 
(6, 1, 1),
(7, 2, 1),
(8, 3, 1),
(9, 4, 1),
(10, 5, 1),
(11, 1, 1),
(12, 2, 1),
(13, 3, 1),
(14, 4, 1),
(15, 5, 1),
(16, 1, 1),
(17, 2, 1),
(18, 3, 1),
(19, 4, 1),
(20, 5, 1),
(21, 1, 1),
(22, 2, 1),
(23, 3, 1),
(24, 4, 1),
(25, 5, 1),
(26, 1, 1),
(27, 2, 1),
(28, 3, 1),
(29, 4, 1),
(30, 5, 1);

INSERT INTO CTMAUSAC (ID_SP, ID_MS, TrangThai) VALUES 
(6, 1, 1),
(7, 2, 1),
(8, 3, 1),
(9, 4, 1),
(10, 5, 1),
(11, 1, 1),
(12, 2, 1),
(13, 3, 1),
(14, 4, 1),
(15, 5, 1),
(16, 1, 1),
(17, 2, 1),
(18, 3, 1),
(19, 4, 1),
(20, 5, 1),
(21, 1, 1),
(22, 2, 1),
(23, 3, 1),
(24, 4, 1),
(25, 5, 1),
(26, 1, 1),
(27, 2, 1),
(28, 3, 1),
(29, 4, 1),
(30, 5, 1);


-- Insert rows into HOADON
INSERT INTO HOADON (ID_NV, MaVocher, ThoiGian, GhiChu, TT_ThanhToan, TongTien, TrangThai) VALUES 
(1, 'V000', '2023-07-01 10:00:00', N'Đã thanh toán đủ', 1,  1100000, 1),
(2, 'V000', '2023-07-02 11:30:00', N'Đã thanh toán đủ', 1,  4800000, 1),
(3, 'V000', '2023-07-03 14:45:00', N'Đã thanh toán đủ', 1, 500000, 1),
(1, 'V000', '2023-07-04 09:15:00', N'Đã thanh toán đủ', 1,  900000, 1),
(2, 'V000', '2023-07-05 16:00:00', N'Đã thanh toán đủ', 1, 700000, 1),
(3, 'V000', '2023-07-06 10:30:00', N'Đã thanh toán đủ', 1,  1200000, 1),
(1, 'V000', '2023-07-07 13:00:00', N'Đã thanh toán đủ', 1,  650000, 1),
(2, 'V000', '2023-07-08 15:30:00', N'Đã thanh toán đủ', 1,  850000, 1),
(3, 'V000', '2023-07-09 11:00:00', N'Đã thanh toán đủ', 1, 400000, 1),
(1, 'V000', '2023-07-10 14:30:00', N'Đã thanh toán đủ', 1,  1100000, 1);
 
--  Insert rows into CTHOADON


SELECT * FROM HOADON;

