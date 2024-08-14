USE [master]
GO
/****** Object:  Database [QL_BAN_QUAN_AO4]    Script Date: 8/14/2024 5:14:57 AM ******/
CREATE DATABASE [QL_BAN_QUAN_AO44]
 CONTAINMENT = NONE
 ON  PRIMARY 
-- ( NAME = N'QL_BAN_QUAN_AO444', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS01\MSSQL\DATA\QL_BAN_QUAN_AO444.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
--  LOG ON 
-- ( NAME = N'QL_BAN_QUAN_AO444_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS01\MSSQL\DATA\QL_BAN_QUAN_AO444_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
--  WITH CATALOG_COLLATION = DATABASE_DEFAULT
-- GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QL_BAN_QUAN_AO44].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET ARITHABORT OFF 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET  ENABLE_BROKER 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET  MULTI_USER 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET QUERY_STORE = OFF
GO
USE [QL_BAN_QUAN_AO44]
GO
/****** Object:  Table [dbo].[THUONGHIEU]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[THUONGHIEU](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Ten] [nvarchar](255) NOT NULL,
	[MoTa] [nvarchar](255) NOT NULL,
	[TrangThai] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DANHMUC]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DANHMUC](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Ten] [nvarchar](255) NOT NULL,
	[TrangThai] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SANPHAM]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SANPHAM](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[TenSP] [nvarchar](255) NOT NULL,
	[ID_DM] [int] NOT NULL,
	[ID_TH] [int] NOT NULL,
	[MoTa] [nvarchar](255) NOT NULL,
	[AnhSP] [nvarchar](255) NOT NULL,
	[SoLuong] [int] NOT NULL,
	[Gia] [decimal](10, 2) NOT NULL,
	[TrangThai] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SIZE]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SIZE](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Ten] [char](255) NOT NULL,
	[MoTa] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CTSIZE]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTSIZE](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[ID_SP] [int] NOT NULL,
	[ID_SIZE] [int] NOT NULL,
	[TrangThai] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MAUSAC]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MAUSAC](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[MauSac] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CTMAUSAC]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTMAUSAC](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[ID_MS] [int] NOT NULL,
	[ID_SP] [int] NOT NULL,
	[TrangThai] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[ProductDetails]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[ProductDetails] AS
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
    (SELECT STRING_AGG(kt.Ten, ', ')
     FROM CTSIZE cts
     JOIN SIZE kt ON cts.ID_SIZE = kt.ID
     WHERE cts.ID_SP = sp.ID) AS SizeNames,
    (SELECT STRING_AGG(ms.MauSac, ', ')
     FROM CTMAUSAC ctms
     JOIN MAUSAC ms ON ctms.ID_MS = ms.ID
     WHERE ctms.ID_SP = sp.ID) AS ColorNames
FROM SANPHAM sp
JOIN DANHMUC dm ON sp.ID_DM = dm.ID
JOIN THUONGHIEU th ON sp.ID_TH = th.ID
GROUP BY
    sp.ID,
    sp.TenSP,
    sp.MoTa,
    sp.AnhSP,
    sp.SoLuong,
    sp.Gia,
    sp.TrangThai,
    dm.Ten,
    th.Ten;
GO
/****** Object:  Table [dbo].[CTHOADON]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CTHOADON](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[ID_HD] [int] NOT NULL,
	[ID_SP] [int] NOT NULL,
	[TenSP] [nvarchar](50) NULL,
	[SoLuong] [int] NOT NULL,
	[Don_gia] [decimal](10, 2) NULL,
	[Gia] [decimal](10, 2) NOT NULL,
	[TrangThai] [bit] NOT NULL,
 CONSTRAINT [PK__CTHOADON__3214EC27B56BF43B] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HOADON]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HOADON](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[ID_NV] [int] NOT NULL,
	[SDT] [int] NULL,
	[TenKH] [nvarchar](50) NULL,
	[MaVocher] [char](20) NULL,
	[ThoiGian] [datetime] NOT NULL,
	[GhiChu] [nvarchar](255) NULL,
	[TT_ThanhToan] [int] NULL,
	[TongTien] [decimal](10, 2) NOT NULL,
	[TrangThai] [int] NOT NULL,
 CONSTRAINT [PK__HOADON__3214EC27AD86D7A4] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KHACHHANG]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KHACHHANG](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[SoDienThoai] [varchar](12) NOT NULL,
	[TenKhachHang] [nvarchar](100) NOT NULL,
	[GioiTinh] [bit] NULL,
	[Email] [nvarchar](255) NULL,
	[DiaChi] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHANVIEN](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[MaNV] [char](255) NOT NULL,
	[HoTen] [nvarchar](255) NOT NULL,
	[SDT] [char](15) NOT NULL,
	[Email] [varchar](255) NOT NULL,
	[TenDN] [varchar](255) NOT NULL,
	[MatKhau] [varchar](255) NOT NULL,
	[QuyenHan] [bit] NOT NULL,
	[TrangThai] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[VOUCHER]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[VOUCHER](
	[MaVocher] [char](20) NOT NULL,
	[PhanTramGiam] [int] NULL,
	[NgayBatDau] [date] NOT NULL,
	[NgayKetThuc] [date] NOT NULL,
	[TrangThai] [bit] NOT NULL,
 CONSTRAINT [PK__VOUCHER__296BDE23ADC36889] PRIMARY KEY CLUSTERED 
(
	[MaVocher] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[CTMAUSAC] ON 

INSERT [dbo].[CTMAUSAC] ([ID], [ID_MS], [ID_SP], [TrangThai]) VALUES (1, 1, 1, 1)
INSERT [dbo].[CTMAUSAC] ([ID], [ID_MS], [ID_SP], [TrangThai]) VALUES (33, 2, 36, 1)
INSERT [dbo].[CTMAUSAC] ([ID], [ID_MS], [ID_SP], [TrangThai]) VALUES (35, 1, 2, 1)
INSERT [dbo].[CTMAUSAC] ([ID], [ID_MS], [ID_SP], [TrangThai]) VALUES (36, 2, 2, 1)
INSERT [dbo].[CTMAUSAC] ([ID], [ID_MS], [ID_SP], [TrangThai]) VALUES (45, 5, 37, 1)
INSERT [dbo].[CTMAUSAC] ([ID], [ID_MS], [ID_SP], [TrangThai]) VALUES (46, 5, 5, 1)
INSERT [dbo].[CTMAUSAC] ([ID], [ID_MS], [ID_SP], [TrangThai]) VALUES (49, 2, 3, 1)
INSERT [dbo].[CTMAUSAC] ([ID], [ID_MS], [ID_SP], [TrangThai]) VALUES (50, 3, 3, 1)
INSERT [dbo].[CTMAUSAC] ([ID], [ID_MS], [ID_SP], [TrangThai]) VALUES (51, 5, 3, 1)
INSERT [dbo].[CTMAUSAC] ([ID], [ID_MS], [ID_SP], [TrangThai]) VALUES (52, 2, 4, 1)
INSERT [dbo].[CTMAUSAC] ([ID], [ID_MS], [ID_SP], [TrangThai]) VALUES (53, 1, 38, 1)
SET IDENTITY_INSERT [dbo].[CTMAUSAC] OFF
GO
SET IDENTITY_INSERT [dbo].[CTSIZE] ON 

INSERT [dbo].[CTSIZE] ([ID], [ID_SP], [ID_SIZE], [TrangThai]) VALUES (1, 1, 1, 1)
INSERT [dbo].[CTSIZE] ([ID], [ID_SP], [ID_SIZE], [TrangThai]) VALUES (32, 36, 3, 1)
INSERT [dbo].[CTSIZE] ([ID], [ID_SP], [ID_SIZE], [TrangThai]) VALUES (33, 36, 4, 1)
INSERT [dbo].[CTSIZE] ([ID], [ID_SP], [ID_SIZE], [TrangThai]) VALUES (36, 2, 1, 1)
INSERT [dbo].[CTSIZE] ([ID], [ID_SP], [ID_SIZE], [TrangThai]) VALUES (37, 2, 2, 1)
INSERT [dbo].[CTSIZE] ([ID], [ID_SP], [ID_SIZE], [TrangThai]) VALUES (38, 2, 3, 1)
INSERT [dbo].[CTSIZE] ([ID], [ID_SP], [ID_SIZE], [TrangThai]) VALUES (52, 37, 3, 1)
INSERT [dbo].[CTSIZE] ([ID], [ID_SP], [ID_SIZE], [TrangThai]) VALUES (53, 37, 5, 1)
INSERT [dbo].[CTSIZE] ([ID], [ID_SP], [ID_SIZE], [TrangThai]) VALUES (54, 5, 3, 1)
INSERT [dbo].[CTSIZE] ([ID], [ID_SP], [ID_SIZE], [TrangThai]) VALUES (55, 5, 5, 1)
INSERT [dbo].[CTSIZE] ([ID], [ID_SP], [ID_SIZE], [TrangThai]) VALUES (56, 3, 1, 1)
INSERT [dbo].[CTSIZE] ([ID], [ID_SP], [ID_SIZE], [TrangThai]) VALUES (57, 3, 2, 1)
INSERT [dbo].[CTSIZE] ([ID], [ID_SP], [ID_SIZE], [TrangThai]) VALUES (58, 4, 4, 1)
INSERT [dbo].[CTSIZE] ([ID], [ID_SP], [ID_SIZE], [TrangThai]) VALUES (59, 38, 1, 1)
INSERT [dbo].[CTSIZE] ([ID], [ID_SP], [ID_SIZE], [TrangThai]) VALUES (60, 38, 2, 1)
SET IDENTITY_INSERT [dbo].[CTSIZE] OFF
GO
SET IDENTITY_INSERT [dbo].[CTHOADON] ON 

INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (154, 1, 4, N'Áo khoác Canifa', 3, CAST(800000.00 AS Decimal(10, 2)), CAST(2400000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (155, 1, 3, N'Áo vest May 10', 2, CAST(1500000.00 AS Decimal(10, 2)), CAST(3000000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (156, 2, 10, N'Phụ kiện Blue Exchange', 3, CAST(200000.00 AS Decimal(10, 2)), CAST(600000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (157, 2, 8, N'Áo len May 10', 1, CAST(400000.00 AS Decimal(10, 2)), CAST(400000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (158, 16, 10, N'Phụ kiện Blue Exchange', 3, CAST(200000.00 AS Decimal(10, 2)), CAST(600000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (159, 16, 9, N'Giày dép Canifa', 3, CAST(600000.00 AS Decimal(10, 2)), CAST(1800000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (160, 16, 17, N'Quần jeans nữ An Phước', 2, CAST(750000.00 AS Decimal(10, 2)), CAST(1500000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (161, 2, 17, N'Quần jeans nữ An Phước', 3, CAST(750000.00 AS Decimal(10, 2)), CAST(2250000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (162, 2, 18, N'Áo len nam May 10', 1, CAST(450000.00 AS Decimal(10, 2)), CAST(450000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (163, 2, 15, N'Áo thun nam Blue Exchange', 1, CAST(220000.00 AS Decimal(10, 2)), CAST(220000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (164, 2, 24, N'Áo khoác nữ Canifa', 1, CAST(820000.00 AS Decimal(10, 2)), CAST(820000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (165, 2, 23, N'Áo vest nam May 10', 2, CAST(1550000.00 AS Decimal(10, 2)), CAST(3100000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (166, 3, 24, N'Áo khoác nữ Canifa', 2, CAST(820000.00 AS Decimal(10, 2)), CAST(1640000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (167, 3, 23, N'Áo vest nam May 10', 1, CAST(1550000.00 AS Decimal(10, 2)), CAST(1550000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (168, 1, 24, N'Áo khoác nữ Canifa', 2, CAST(820000.00 AS Decimal(10, 2)), CAST(1640000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (169, 1, 22, N'Quần tây nam An Phước', 1, CAST(520000.00 AS Decimal(10, 2)), CAST(520000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (170, 1, 23, N'Áo vest nam May 10', 1, CAST(1550000.00 AS Decimal(10, 2)), CAST(1550000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (171, 3, 4, N'Áo khoác Canifa', 2, CAST(800000.00 AS Decimal(10, 2)), CAST(1600000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (172, 3, 8, N'Áo len May 10', 1, CAST(400000.00 AS Decimal(10, 2)), CAST(400000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (173, 3, 7, N'Quần jeans An Phước', 1, CAST(700000.00 AS Decimal(10, 2)), CAST(700000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (174, 3, 6, N'Váy đầm Việt Tiến', 1, CAST(500000.00 AS Decimal(10, 2)), CAST(500000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (175, 16, 4, N'Áo khoác Canifa', 3, CAST(800000.00 AS Decimal(10, 2)), CAST(2400000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (176, 16, 35, N'Áo thun trẻ em Blue Exchange', 2, CAST(215000.00 AS Decimal(10, 2)), CAST(430000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (177, 17, 4, N'Áo khoác Canifa', 4, CAST(800000.00 AS Decimal(10, 2)), CAST(3200000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (178, 17, 3, N'Áo vest May 10', 2, CAST(1500000.00 AS Decimal(10, 2)), CAST(3000000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (179, 18, 4, N'Áo khoác Canifa', 1, CAST(800000.00 AS Decimal(10, 2)), CAST(800000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (180, 18, 3, N'Áo vest May 10', 1, CAST(1500000.00 AS Decimal(10, 2)), CAST(1500000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (181, 18, 2, N'Quần tây An Phước', 1, CAST(500000.00 AS Decimal(10, 2)), CAST(500000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (182, 18, 1, N'Áo sơ mi Việt Tiến', 1, CAST(300000.00 AS Decimal(10, 2)), CAST(300000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (183, 18, 9, N'Giày dép Canifa', 1, CAST(600000.00 AS Decimal(10, 2)), CAST(600000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (184, 18, 10, N'Phụ kiện Blue Exchange', 1, CAST(200000.00 AS Decimal(10, 2)), CAST(200000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (185, 18, 17, N'Quần jeans nữ An Phước', 1, CAST(750000.00 AS Decimal(10, 2)), CAST(750000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (186, 18, 21, N'Áo sơ mi ngắn tay Việt Tiến', 1, CAST(320000.00 AS Decimal(10, 2)), CAST(320000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (187, 1, 10, N'Phụ kiện Blue Exchange', 1, CAST(200000.00 AS Decimal(10, 2)), CAST(200000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (188, 2, 9, N'Giày dép Canifa', 1, CAST(600000.00 AS Decimal(10, 2)), CAST(600000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (189, 3, 14, N'Áo khoác nam Canifa', 1, CAST(850000.00 AS Decimal(10, 2)), CAST(850000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (190, 3, 13, N'Áo vest nữ May 10', 1, CAST(1600000.00 AS Decimal(10, 2)), CAST(1600000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (191, 16, 3, N'Áo vest May 10', 1, CAST(1500000.00 AS Decimal(10, 2)), CAST(1500000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (192, 2, 4, N'Áo khoác Canifa', 2, CAST(800000.00 AS Decimal(10, 2)), CAST(1600000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (193, 2, 35, N'Áo thun trẻ em Blue Exchange', 1, CAST(215000.00 AS Decimal(10, 2)), CAST(215000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (194, 19, 4, N'Áo khoác Canifa', 2, CAST(800000.00 AS Decimal(10, 2)), CAST(1600000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (195, 19, 7, N'Quần jeans An Phước', 1, CAST(700000.00 AS Decimal(10, 2)), CAST(700000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (196, 3, 17, N'Quần jeans nữ An Phước', 3, CAST(750000.00 AS Decimal(10, 2)), CAST(2250000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (197, 3, 16, N'Váy đầm dạ hội Việt Tiến', 1, CAST(700000.00 AS Decimal(10, 2)), CAST(700000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (198, 3, 15, N'Áo thun nam Blue Exchange', 1, CAST(220000.00 AS Decimal(10, 2)), CAST(220000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (199, 17, 2, N'Quần tây An Phước', 2, CAST(500000.00 AS Decimal(10, 2)), CAST(1000000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (200, 20, 1, N'Áo sơ mi Việt Tiến', 4, CAST(300000.00 AS Decimal(10, 2)), CAST(1200000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (201, 20, 4, N'Áo khoác Canifa', 3, CAST(800000.00 AS Decimal(10, 2)), CAST(2400000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (202, 20, 2, N'Quần tây An Phước', 9, CAST(500000.00 AS Decimal(10, 2)), CAST(4500000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (203, 20, 3, N'Áo vest May 10', 8, CAST(1500000.00 AS Decimal(10, 2)), CAST(12000000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (204, 21, 2, N'Quần tây An Phước', 3, CAST(500000.00 AS Decimal(10, 2)), CAST(1500000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (205, 21, 3, N'Áo vest May 10', 2, CAST(1500000.00 AS Decimal(10, 2)), CAST(3000000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (206, 21, 4, N'Áo khoác Canifa', 2, CAST(800000.00 AS Decimal(10, 2)), CAST(1600000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (207, 21, 1, N'Áo sơ mi Việt Tiến', 1, CAST(300000.00 AS Decimal(10, 2)), CAST(300000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (208, 22, 4, N'Áo khoác Canifa', 2, CAST(800000.00 AS Decimal(10, 2)), CAST(1600000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (209, 22, 3, N'Áo vest May 10', 2, CAST(1500000.00 AS Decimal(10, 2)), CAST(3000000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (210, 22, 2, N'Quần tây An Phước', 1, CAST(500000.00 AS Decimal(10, 2)), CAST(500000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (211, 22, 10, N'Phụ kiện Blue Exchange', 1, CAST(200000.00 AS Decimal(10, 2)), CAST(200000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (212, 22, 9, N'Giày dép Canifa', 1, CAST(600000.00 AS Decimal(10, 2)), CAST(600000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (213, 22, 13, N'Áo vest nữ May 10', 1, CAST(1600000.00 AS Decimal(10, 2)), CAST(1600000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (214, 22, 12, N'Quần tây nữ An Phước', 1, CAST(550000.00 AS Decimal(10, 2)), CAST(550000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (215, 22, 14, N'Áo khoác nam Canifa', 1, CAST(850000.00 AS Decimal(10, 2)), CAST(850000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (216, 19, 3, N'Áo vest May 10', 1, CAST(1500000.00 AS Decimal(10, 2)), CAST(1500000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (217, 19, 2, N'Quần tây An Phước', 1, CAST(500000.00 AS Decimal(10, 2)), CAST(500000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (218, 19, 19, N'Giày thể thao Canifa', 1, CAST(650000.00 AS Decimal(10, 2)), CAST(650000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (219, 19, 17, N'Quần jeans nữ An Phước', 1, CAST(750000.00 AS Decimal(10, 2)), CAST(750000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (220, 19, 34, N'Áo khoác trẻ em Canifa', 1, CAST(810000.00 AS Decimal(10, 2)), CAST(810000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (221, 19, 35, N'Áo thun trẻ em Blue Exchange', 1, CAST(215000.00 AS Decimal(10, 2)), CAST(215000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (224, 23, 16, N'Váy đầm dạ hội Việt Tiến', 18, CAST(700000.00 AS Decimal(10, 2)), CAST(12600000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (225, 23, 4, N'Áo khoác Canifa', 8, CAST(800000.00 AS Decimal(10, 2)), CAST(6400000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (226, 23, 8, N'Áo len May 10', 1, CAST(400000.00 AS Decimal(10, 2)), CAST(400000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (227, 23, 7, N'Quần jeans An Phước', 1, CAST(700000.00 AS Decimal(10, 2)), CAST(700000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (228, 23, 20, N'Phụ kiện nữ Blue Exchange', 3, CAST(250000.00 AS Decimal(10, 2)), CAST(750000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (229, 23, 27, N'Quần jeans nam An Phước', 2, CAST(720000.00 AS Decimal(10, 2)), CAST(1440000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (230, 23, 31, N'Áo sơ mi nam Việt Tiến', 1, CAST(310000.00 AS Decimal(10, 2)), CAST(310000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (231, 23, 38, N'sweater', 1, CAST(80000.00 AS Decimal(10, 2)), CAST(80000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (232, 23, 19, N'Giày thể thao Canifa', 2, CAST(650000.00 AS Decimal(10, 2)), CAST(1300000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (233, 23, 18, N'Áo len nam May 10', 2, CAST(450000.00 AS Decimal(10, 2)), CAST(900000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (234, 23, 2, N'Quần tây An Phước', 2, CAST(500000.00 AS Decimal(10, 2)), CAST(1000000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[CTHOADON] ([ID], [ID_HD], [ID_SP], [TenSP], [SoLuong], [Don_gia], [Gia], [TrangThai]) VALUES (235, 23, 37, N'Áo thun Blue Exchange', 10, CAST(200000.00 AS Decimal(10, 2)), CAST(2000000.00 AS Decimal(10, 2)), 1)
SET IDENTITY_INSERT [dbo].[CTHOADON] OFF
GO
SET IDENTITY_INSERT [dbo].[DANHMUC] ON 

INSERT [dbo].[DANHMUC] ([ID], [Ten], [TrangThai]) VALUES (1, N'Áo sơ mi', 1)
INSERT [dbo].[DANHMUC] ([ID], [Ten], [TrangThai]) VALUES (2, N'Quần tây', 1)
INSERT [dbo].[DANHMUC] ([ID], [Ten], [TrangThai]) VALUES (3, N'Áo vest', 1)
INSERT [dbo].[DANHMUC] ([ID], [Ten], [TrangThai]) VALUES (4, N'Áo khoác', 1)
INSERT [dbo].[DANHMUC] ([ID], [Ten], [TrangThai]) VALUES (5, N'Áo thun', 1)
INSERT [dbo].[DANHMUC] ([ID], [Ten], [TrangThai]) VALUES (6, N'Váy đầm', 1)
INSERT [dbo].[DANHMUC] ([ID], [Ten], [TrangThai]) VALUES (7, N'Quần jeans', 1)
INSERT [dbo].[DANHMUC] ([ID], [Ten], [TrangThai]) VALUES (8, N'Áo len', 1)
INSERT [dbo].[DANHMUC] ([ID], [Ten], [TrangThai]) VALUES (9, N'Giày dép', 1)
INSERT [dbo].[DANHMUC] ([ID], [Ten], [TrangThai]) VALUES (10, N'Phụ kiện thời trang', 1)
SET IDENTITY_INSERT [dbo].[DANHMUC] OFF
GO
SET IDENTITY_INSERT [dbo].[HOADON] ON 

INSERT [dbo].[HOADON] ([ID], [ID_NV], [SDT], [TenKH], [MaVocher], [ThoiGian], [GhiChu], [TT_ThanhToan], [TongTien], [TrangThai]) VALUES (1, 1, NULL, NULL, N'1234567             ', CAST(N'2024-07-31T11:23:05.000' AS DateTime), N'', 8379000, CAST(9310000.00 AS Decimal(10, 2)), 0)
INSERT [dbo].[HOADON] ([ID], [ID_NV], [SDT], [TenKH], [MaVocher], [ThoiGian], [GhiChu], [TT_ThanhToan], [TongTien], [TrangThai]) VALUES (2, 1, NULL, NULL, N'1234567             ', CAST(N'1900-01-01T00:00:00.000' AS DateTime), N'', 9229500, CAST(10255000.00 AS Decimal(10, 2)), 2)
INSERT [dbo].[HOADON] ([ID], [ID_NV], [SDT], [TenKH], [MaVocher], [ThoiGian], [GhiChu], [TT_ThanhToan], [TongTien], [TrangThai]) VALUES (3, 1, NULL, NULL, N'1234567             ', CAST(N'1900-01-01T00:00:00.000' AS DateTime), N'', 10809000, CAST(12010000.00 AS Decimal(10, 2)), 2)
INSERT [dbo].[HOADON] ([ID], [ID_NV], [SDT], [TenKH], [MaVocher], [ThoiGian], [GhiChu], [TT_ThanhToan], [TongTien], [TrangThai]) VALUES (16, 1, NULL, NULL, N'DA1                 ', CAST(N'2024-08-08T01:17:29.000' AS DateTime), N'', 6584000, CAST(8230000.00 AS Decimal(10, 2)), 0)
INSERT [dbo].[HOADON] ([ID], [ID_NV], [SDT], [TenKH], [MaVocher], [ThoiGian], [GhiChu], [TT_ThanhToan], [TongTien], [TrangThai]) VALUES (17, 1, NULL, NULL, N'1234567             ', CAST(N'2024-08-08T01:59:42.000' AS DateTime), N'', 6480000, CAST(7200000.00 AS Decimal(10, 2)), 0)
INSERT [dbo].[HOADON] ([ID], [ID_NV], [SDT], [TenKH], [MaVocher], [ThoiGian], [GhiChu], [TT_ThanhToan], [TongTien], [TrangThai]) VALUES (18, 1, NULL, NULL, N'DA1                 ', CAST(N'2024-08-08T02:04:01.000' AS DateTime), N'', 3976000, CAST(4970000.00 AS Decimal(10, 2)), 0)
INSERT [dbo].[HOADON] ([ID], [ID_NV], [SDT], [TenKH], [MaVocher], [ThoiGian], [GhiChu], [TT_ThanhToan], [TongTien], [TrangThai]) VALUES (19, 1, NULL, NULL, N'DA1                 ', CAST(N'2024-08-08T02:11:06.000' AS DateTime), N'', 5380000, CAST(6725000.00 AS Decimal(10, 2)), 0)
INSERT [dbo].[HOADON] ([ID], [ID_NV], [SDT], [TenKH], [MaVocher], [ThoiGian], [GhiChu], [TT_ThanhToan], [TongTien], [TrangThai]) VALUES (20, 1, NULL, NULL, N'DA1                 ', CAST(N'2024-08-09T18:16:16.000' AS DateTime), N'', 16080000, CAST(20100000.00 AS Decimal(10, 2)), 0)
INSERT [dbo].[HOADON] ([ID], [ID_NV], [SDT], [TenKH], [MaVocher], [ThoiGian], [GhiChu], [TT_ThanhToan], [TongTien], [TrangThai]) VALUES (21, 1, NULL, NULL, N'DA1                 ', CAST(N'2024-08-09T19:16:08.000' AS DateTime), N'', 5120000, CAST(6400000.00 AS Decimal(10, 2)), 0)
INSERT [dbo].[HOADON] ([ID], [ID_NV], [SDT], [TenKH], [MaVocher], [ThoiGian], [GhiChu], [TT_ThanhToan], [TongTien], [TrangThai]) VALUES (22, 1, NULL, NULL, N'DA1                 ', CAST(N'2024-08-11T10:53:20.000' AS DateTime), N'', 7120000, CAST(8900000.00 AS Decimal(10, 2)), 2)
INSERT [dbo].[HOADON] ([ID], [ID_NV], [SDT], [TenKH], [MaVocher], [ThoiGian], [GhiChu], [TT_ThanhToan], [TongTien], [TrangThai]) VALUES (23, 1, 338086647, N'Nguyen Van 1', N'DA1                 ', CAST(N'2024-08-13T11:54:42.000' AS DateTime), N'', 35904000, CAST(42880000.00 AS Decimal(10, 2)), 1)
SET IDENTITY_INSERT [dbo].[HOADON] OFF
GO
SET IDENTITY_INSERT [dbo].[KHACHHANG] ON 

INSERT [dbo].[KHACHHANG] ([ID], [SoDienThoai], [TenKhachHang], [GioiTinh], [Email], [DiaChi]) VALUES (1, N'0123456789', N'Huyen', 1, N'abc@gmail.com', N'hp')
SET IDENTITY_INSERT [dbo].[KHACHHANG] OFF
GO
SET IDENTITY_INSERT [dbo].[MAUSAC] ON 

INSERT [dbo].[MAUSAC] ([ID], [MauSac]) VALUES (1, N'Đỏ')
INSERT [dbo].[MAUSAC] ([ID], [MauSac]) VALUES (2, N'Xanh')
INSERT [dbo].[MAUSAC] ([ID], [MauSac]) VALUES (3, N'Vàng')
INSERT [dbo].[MAUSAC] ([ID], [MauSac]) VALUES (4, N'Trắng')
INSERT [dbo].[MAUSAC] ([ID], [MauSac]) VALUES (5, N'Đen')
SET IDENTITY_INSERT [dbo].[MAUSAC] OFF
GO
SET IDENTITY_INSERT [dbo].[NHANVIEN] ON 

INSERT [dbo].[NHANVIEN] ([ID], [MaNV], [HoTen], [SDT], [Email], [TenDN], [MatKhau], [QuyenHan], [TrangThai]) VALUES (1, N'NV001                                                                                                                                                                                                                                                          ', N'Nguyễn Văn Hoà', N'0123456789     ', N'nguyenvana@example.com', N'1', N'123', 1, 1)
INSERT [dbo].[NHANVIEN] ([ID], [MaNV], [HoTen], [SDT], [Email], [TenDN], [MatKhau], [QuyenHan], [TrangThai]) VALUES (2, N'NV002                                                                                                                                                                                                                                                          ', N'Lê Thị Bích', N'0987654321     ', N'lethib@example.com', N'lethib', N'password123', 0, 1)
INSERT [dbo].[NHANVIEN] ([ID], [MaNV], [HoTen], [SDT], [Email], [TenDN], [MatKhau], [QuyenHan], [TrangThai]) VALUES (3, N'NV003                                                                                                                                                                                                                                                          ', N'Trần Văn Cường', N'0912345678     ', N'tranvanc@example.com', N'tranvanc', N'password123', 0, 1)
SET IDENTITY_INSERT [dbo].[NHANVIEN] OFF
GO
SET IDENTITY_INSERT [dbo].[SANPHAM] ON 

INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (1, N'Áo sơ mi Việt Tiến', 1, 1, N'Áo sơ mi công sở', N'aosomi_viettien.jpg', 49, CAST(300000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (2, N'Quần tây An Phước', 2, 2, N'selectedMS', N'ExoT1.jpg', 39, CAST(500000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (3, N'Áo vest May 10', 3, 3, N'tot', N'448146698_954136146506599_6855434188932882489_n.jpg', 62, CAST(1500000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (4, N'Áo khoác Canifa', 4, 4, N'selectedMS', N'aolen_may10.jpg', 36, CAST(800000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (5, N'Áo thun Blue Exchange', 5, 5, N'selectedMS', N'66ab122788bfcb12c8bb80efa320a41f.jpg', 20, CAST(200000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (6, N'Váy đầm Việt Tiến', 6, 1, N'Váy đầm công sở', N'vaydam_viettien.jpg', 15, CAST(500000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (7, N'Quần jeans An Phước', 7, 2, N'Quần jeans cao cấp', N'quanjeans_anphuoc.jpg', 20, CAST(700000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (8, N'Áo len May 10', 8, 3, N'Áo len công sở', N'aolen_may10.jpg', 25, CAST(400000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (9, N'Giày dép Canifa', 9, 4, N'Giày dép gia đình', N'giaydep_canifa.jpg', 30, CAST(600000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (10, N'Phụ kiện Blue Exchange', 10, 5, N'Phụ kiện thời trang trẻ', N'phukien_blueexchange.jpg', 35, CAST(200000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (11, N'Áo sơ mi dài tay Việt Tiến', 1, 1, N'Áo sơ mi dài tay công sở', N'aosomidaitay_viettien.jpg', 50, CAST(350000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (12, N'Quần tây nữ An Phước', 2, 2, N'Quần tây nữ cao cấp', N'quantaynu_anphuoc.jpg', 40, CAST(550000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (13, N'Áo vest nữ May 10', 3, 3, N'Áo vest nữ công sở', N'aovestnu_may10.jpg', 30, CAST(1600000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (14, N'Áo khoác nam Canifa', 4, 4, N'Áo khoác nam gia đình', N'aokhoacnam_canifa.jpg', 25, CAST(850000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (15, N'Áo thun nam Blue Exchange', 5, 5, N'Áo thun nam trẻ', N'aothunnam_blueexchange.jpg', 20, CAST(220000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (16, N'Váy đầm dạ hội Việt Tiến', 6, 1, N'Váy đầm dạ hội', N'vaydamdahoi_viettien.jpg', 5, CAST(700000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (17, N'Quần jeans nữ An Phước', 7, 2, N'Quần jeans nữ cao cấp', N'quanjeansnu_anphuoc.jpg', 20, CAST(750000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (18, N'Áo len nam May 10', 8, 3, N'Áo len nam công sở', N'aolennam_may10.jpg', 23, CAST(450000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (19, N'Giày thể thao Canifa', 9, 4, N'Giày thể thao gia đình', N'giaythethao_canifa.jpg', 28, CAST(650000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (20, N'Phụ kiện nữ Blue Exchange', 10, 5, N'Phụ kiện thời trang nữ trẻ', N'phukiennu_blueexchange.jpg', 34, CAST(250000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (21, N'Áo sơ mi ngắn tay Việt Tiến', 1, 1, N'Áo sơ mi ngắn tay công sở', N'aosomingantay_viettien.jpg', 50, CAST(320000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (22, N'Quần tây nam An Phước', 2, 2, N'Quần tây nam cao cấp', N'quantaynam_anphuoc.jpg', 40, CAST(520000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (23, N'Áo vest nam May 10', 3, 3, N'Áo vest nam công sở', N'aovestnam_may10.jpg', 34, CAST(1550000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (24, N'Áo khoác nữ Canifa', 4, 4, N'Áo khoác nữ gia đình', N'aokhoacnu_canifa.jpg', 25, CAST(820000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (25, N'Áo thun nữ Blue Exchange', 5, 5, N'Áo thun nữ trẻ', N'aothunnu_blueexchange.jpg', 20, CAST(210000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (26, N'Váy đầm công sở Việt Tiến', 6, 1, N'Váy đầm công sở cao cấp', N'vaydamcongso_viettien.jpg', 15, CAST(520000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (27, N'Quần jeans nam An Phước', 7, 2, N'Quần jeans nam cao cấp', N'quanjeansnam_anphuoc.jpg', 19, CAST(720000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (28, N'Áo len nữ May 10', 8, 3, N'Áo len nữ công sở', N'aolennu_may10.jpg', 25, CAST(430000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (29, N'Giày lười Canifa', 9, 4, N'Giày lười gia đình', N'giayluoi_canifa.jpg', 30, CAST(670000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (30, N'Phụ kiện nam Blue Exchange', 10, 5, N'Phụ kiện thời trang nam trẻ', N'phukiennam_blueexchange.jpg', 35, CAST(230000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (31, N'Áo sơ mi nam Việt Tiến', 1, 1, N'Áo sơ mi nam công sở', N'aosominam_viettien.jpg', 49, CAST(310000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (32, N'Quần tây nữ công sở An Phước', 2, 2, N'Quần tây nữ công sở cao cấp', N'quantaynucongso_anphuoc.jpg', 40, CAST(530000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (33, N'Áo vest nữ công sở May 10', 3, 3, N'Áo vest nữ công sở cao cấp', N'aovestnucongso_may10.jpg', 30, CAST(1580000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (34, N'Áo khoác trẻ em Canifa', 4, 4, N'Áo khoác trẻ em gia đình', N'aokhoactreem_canifa.jpg', 25, CAST(810000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (35, N'Áo thun trẻ em Blue Exchange', 5, 5, N'Áo thun trẻ em trẻ', N'aothuntreem_blueexchange.jpg', 20, CAST(215000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (36, N'Áo khoác Canifa', 4, 4, N'selectedMS', N'448750620_1008722927327569_2632301707912171904_n.png', 25, CAST(800000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (37, N'Áo thun Blue Exchange', 5, 5, N'selectedMS', N'66ab122788bfcb12c8bb80efa320a41f.jpg', 20, CAST(200000.00 AS Decimal(10, 2)), 1)
INSERT [dbo].[SANPHAM] ([ID], [TenSP], [ID_DM], [ID_TH], [MoTa], [AnhSP], [SoLuong], [Gia], [TrangThai]) VALUES (38, N'sweater', 1, 1, N'tét', N'Capture001.png', 69, CAST(80000.00 AS Decimal(10, 2)), 1)
SET IDENTITY_INSERT [dbo].[SANPHAM] OFF
GO
SET IDENTITY_INSERT [dbo].[SIZE] ON 

INSERT [dbo].[SIZE] ([ID], [Ten], [MoTa]) VALUES (1, N'S                                                                                                                                                                                                                                                              ', N'Size nhỏ')
INSERT [dbo].[SIZE] ([ID], [Ten], [MoTa]) VALUES (2, N'M                                                                                                                                                                                                                                                              ', N'Size vừa')
INSERT [dbo].[SIZE] ([ID], [Ten], [MoTa]) VALUES (3, N'L                                                                                                                                                                                                                                                              ', N'Size lớn')
INSERT [dbo].[SIZE] ([ID], [Ten], [MoTa]) VALUES (4, N'XL                                                                                                                                                                                                                                                             ', N'Size rất lớn')
INSERT [dbo].[SIZE] ([ID], [Ten], [MoTa]) VALUES (5, N'XXL                                                                                                                                                                                                                                                            ', N'Size cực lớn')
SET IDENTITY_INSERT [dbo].[SIZE] OFF
GO
SET IDENTITY_INSERT [dbo].[THUONGHIEU] ON 

INSERT [dbo].[THUONGHIEU] ([ID], [Ten], [MoTa], [TrangThai]) VALUES (1, N'Việt Tiến', N'Thương hiệu thời trang công sở', 1)
INSERT [dbo].[THUONGHIEU] ([ID], [Ten], [MoTa], [TrangThai]) VALUES (2, N'An Phước', N'Thương hiệu thời trang nam cao cấp', 1)
INSERT [dbo].[THUONGHIEU] ([ID], [Ten], [MoTa], [TrangThai]) VALUES (3, N'May 10', N'Thương hiệu thời trang công sở', 1)
INSERT [dbo].[THUONGHIEU] ([ID], [Ten], [MoTa], [TrangThai]) VALUES (4, N'Canifa', N'Thương hiệu thời trang gia đình', 1)
INSERT [dbo].[THUONGHIEU] ([ID], [Ten], [MoTa], [TrangThai]) VALUES (5, N'Blue Exchange', N'Thương hiệu thời trang trẻ', 1)
SET IDENTITY_INSERT [dbo].[THUONGHIEU] OFF
GO
INSERT [dbo].[VOUCHER] ([MaVocher], [PhanTramGiam], [NgayBatDau], [NgayKetThuc], [TrangThai]) VALUES (N'1234567             ', 10, CAST(N'2024-08-01' AS Date), CAST(N'2024-12-31' AS Date), 1)
INSERT [dbo].[VOUCHER] ([MaVocher], [PhanTramGiam], [NgayBatDau], [NgayKetThuc], [TrangThai]) VALUES (N'DA1                 ', 20, CAST(N'2024-04-15' AS Date), CAST(N'2025-01-01' AS Date), 1)
INSERT [dbo].[VOUCHER] ([MaVocher], [PhanTramGiam], [NgayBatDau], [NgayKetThuc], [TrangThai]) VALUES (N'Demo                ', 99, CAST(N'2024-04-15' AS Date), CAST(N'2024-04-16' AS Date), 1)
GO
ALTER TABLE [dbo].[CTMAUSAC] ADD  DEFAULT ((1)) FOR [TrangThai]
GO
ALTER TABLE [dbo].[CTSIZE] ADD  DEFAULT ((1)) FOR [TrangThai]
GO
ALTER TABLE [dbo].[CTHOADON] ADD  CONSTRAINT [DF__CTHOADON__TrangT__5BE2A6F2]  DEFAULT ((1)) FOR [TrangThai]
GO
ALTER TABLE [dbo].[DANHMUC] ADD  DEFAULT ((1)) FOR [TrangThai]
GO
ALTER TABLE [dbo].[HOADON] ADD  CONSTRAINT [DF__HOADON__GhiChu__5629CD9C]  DEFAULT (N'Đã thanh toán đủ') FOR [GhiChu]
GO
ALTER TABLE [dbo].[HOADON] ADD  CONSTRAINT [DF__HOADON__TrangTha__571DF1D5]  DEFAULT ((1)) FOR [TrangThai]
GO
ALTER TABLE [dbo].[NHANVIEN] ADD  DEFAULT ((1)) FOR [TrangThai]
GO
ALTER TABLE [dbo].[SANPHAM] ADD  DEFAULT (N'Chưa có') FOR [MoTa]
GO
ALTER TABLE [dbo].[SANPHAM] ADD  DEFAULT ((1)) FOR [TrangThai]
GO
ALTER TABLE [dbo].[THUONGHIEU] ADD  DEFAULT (N'Chưa có') FOR [MoTa]
GO
ALTER TABLE [dbo].[THUONGHIEU] ADD  DEFAULT ((1)) FOR [TrangThai]
GO
ALTER TABLE [dbo].[VOUCHER] ADD  CONSTRAINT [DF__VOUCHER__TrangTh__534D60F1]  DEFAULT ((1)) FOR [TrangThai]
GO
ALTER TABLE [dbo].[CTMAUSAC]  WITH CHECK ADD FOREIGN KEY([ID_MS])
REFERENCES [dbo].[MAUSAC] ([ID])
GO
ALTER TABLE [dbo].[CTMAUSAC]  WITH CHECK ADD FOREIGN KEY([ID_SP])
REFERENCES [dbo].[SANPHAM] ([ID])
GO
ALTER TABLE [dbo].[CTSIZE]  WITH CHECK ADD FOREIGN KEY([ID_SIZE])
REFERENCES [dbo].[SIZE] ([ID])
GO
ALTER TABLE [dbo].[CTSIZE]  WITH CHECK ADD FOREIGN KEY([ID_SP])
REFERENCES [dbo].[SANPHAM] ([ID])
GO
ALTER TABLE [dbo].[CTHOADON]  WITH CHECK ADD  CONSTRAINT [FK__CTHOADON__ID_HD__5CD6CB2B] FOREIGN KEY([ID_HD])
REFERENCES [dbo].[HOADON] ([ID])
GO
ALTER TABLE [dbo].[CTHOADON] CHECK CONSTRAINT [FK__CTHOADON__ID_HD__5CD6CB2B]
GO
ALTER TABLE [dbo].[CTHOADON]  WITH CHECK ADD  CONSTRAINT [FK__CTHOADON__ID_SP__5DCAEF64] FOREIGN KEY([ID_SP])
REFERENCES [dbo].[SANPHAM] ([ID])
GO
ALTER TABLE [dbo].[CTHOADON] CHECK CONSTRAINT [FK__CTHOADON__ID_SP__5DCAEF64]
GO
ALTER TABLE [dbo].[HOADON]  WITH CHECK ADD  CONSTRAINT [FK__HOADON__ID_NV__5812160E] FOREIGN KEY([ID_NV])
REFERENCES [dbo].[NHANVIEN] ([ID])
GO
ALTER TABLE [dbo].[HOADON] CHECK CONSTRAINT [FK__HOADON__ID_NV__5812160E]
GO
ALTER TABLE [dbo].[SANPHAM]  WITH CHECK ADD FOREIGN KEY([ID_DM])
REFERENCES [dbo].[DANHMUC] ([ID])
GO
ALTER TABLE [dbo].[SANPHAM]  WITH CHECK ADD FOREIGN KEY([ID_TH])
REFERENCES [dbo].[THUONGHIEU] ([ID])
GO
/****** Object:  StoredProcedure [dbo].[getAllCTDT]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[getAllCTDT]
AS
BEGIN
    SELECT 
        FORMAT(CONVERT(DATE, hd.ThoiGian), 'dd/MM/yyyy') AS Time,
        COALESCE(SUM(ct.SoLuong), 0) AS SoLuongDaBan,
        COALESCE(FORMAT(SUM(ct.SoLuong * sp.Gia), 'N0'), '0') AS TongDT
    FROM HOADON hd
    JOIN CTHOADON ct ON hd.ID = ct.ID_HD
    JOIN SANPHAM sp ON ct.ID_SP = sp.ID
    WHERE hd.TrangThai = '0'
    GROUP BY CONVERT(DATE, hd.ThoiGian)
    ORDER BY CONVERT(DATE, hd.ThoiGian);
END
GO
/****** Object:  StoredProcedure [dbo].[getAllSP_DM]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[getAllSP_DM]
    @month INT,
    @year INT
AS
BEGIN
    SELECT 
        sp.ID AS ID_SP,
        sp.TenSP AS TenSP,
        sp.Gia AS Gia,
        COALESCE(SUM(ct.SoLuong), 0) AS SoLuongDaBan,
        dm.Ten AS DacDiem
    FROM SANPHAM sp
    LEFT JOIN CTHOADON ct ON sp.ID = ct.ID_SP
    JOIN HOADON hd ON ct.ID_HD = hd.ID
    JOIN DANHMUC dm ON sp.ID_DM = dm.ID
    WHERE hd.TrangThai = '0' AND MONTH(hd.ThoiGian) = @month AND YEAR(hd.ThoiGian) = @year
    GROUP BY sp.ID, sp.TenSP, sp.Gia, dm.Ten
    ORDER BY SoLuongDaBan DESC;
END
GO
/****** Object:  StoredProcedure [dbo].[getAllSP_TH]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[getAllSP_TH]
    @month INT,
    @year INT
AS
BEGIN
    SELECT 
        sp.ID AS ID_SP,
        sp.TenSP,
        sp.Gia,
        SUM(ct.SoLuong) AS SoLuongDaBan,
        th.Ten AS DacDiem
    FROM SANPHAM sp
    LEFT JOIN CTHOADON ct ON sp.ID = ct.ID_SP
    JOIN HOADON hd ON ct.ID_HD = hd.ID
    JOIN THUONGHIEU th ON sp.ID_TH = th.ID
    WHERE hd.TrangThai = '0' AND MONTH(hd.ThoiGian) = @month AND YEAR(hd.ThoiGian) = @year
    GROUP BY sp.ID, sp.TenSP, sp.Gia, th.Ten
    ORDER BY SoLuongDaBan DESC;
END
GO
/****** Object:  StoredProcedure [dbo].[getCTDTByDay]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[getCTDTByDay]
    @dateFrom DATE,
    @dateTo DATE
AS
BEGIN
    SELECT 
        FORMAT(CONVERT(DATE, hd.ThoiGian), 'dd/MM/yyyy') AS Time,
        COALESCE(SUM(ct.SoLuong), 0) AS SoLuongDaBan,
        COALESCE(FORMAT(SUM(ct.SoLuong * sp.Gia), 'N0'), '0') AS TongDT
    FROM HOADON hd
    JOIN CTHOADON ct ON hd.ID = ct.ID_HD
    JOIN SANPHAM sp ON ct.ID_SP = sp.ID
    WHERE hd.TrangThai = '0'
      AND CONVERT(DATE, hd.ThoiGian) BETWEEN @dateFrom AND @dateTo
    GROUP BY CONVERT(DATE, hd.ThoiGian)
    ORDER BY CONVERT(DATE, hd.ThoiGian);
END;
GO
/****** Object:  StoredProcedure [dbo].[getCTDTByMonth]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[getCTDTByMonth]
    @dateFrom DATE,
    @dateTo DATE
AS
BEGIN
    SELECT 
        FORMAT(hd.ThoiGian, 'MM/yyyy') AS Time,
        COALESCE(SUM(ct.SoLuong), 0) AS SoLuongDaBan,
        COALESCE(FORMAT(SUM(ct.SoLuong * sp.Gia), 'N0'), '0') AS TongDT
    FROM HOADON hd
    JOIN CTHOADON ct ON hd.ID = ct.ID_HD
    JOIN SANPHAM sp ON ct.ID_SP = sp.ID
    WHERE hd.TrangThai = '0'
      AND CONVERT(DATE, hd.ThoiGian) BETWEEN @dateFrom AND @dateTo
    GROUP BY FORMAT(hd.ThoiGian, 'MM/yyyy')
    ORDER BY MIN(hd.ThoiGian);
END;
GO
/****** Object:  StoredProcedure [dbo].[getCTDTByYear]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[getCTDTByYear]
    @dateFrom DATE,
    @dateTo DATE
AS
BEGIN
    SELECT 
        YEAR(hd.ThoiGian) AS Time,
        COALESCE(SUM(ct.SoLuong), 0) AS SoLuongDaBan,
        COALESCE(FORMAT(SUM(ct.SoLuong * sp.Gia), 'N0'), '0') AS TongDT
    FROM HOADON hd
    JOIN CTHOADON ct ON hd.ID = ct.ID_HD
    JOIN SANPHAM sp ON ct.ID_SP = sp.ID
    WHERE hd.TrangThai = '0'
      AND CONVERT(DATE, hd.ThoiGian) BETWEEN @dateFrom AND @dateTo
    GROUP BY YEAR(hd.ThoiGian)
    ORDER BY YEAR(hd.ThoiGian);
END;
GO
/****** Object:  StoredProcedure [dbo].[getDanhMucChart]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE   PROCEDURE [dbo].[getDanhMucChart]
    @Month INT,
    @Year INT
AS
BEGIN
    SELECT  
        DM.Ten AS chartColumn,
        SUM(CTHD.SoLuong * CTHD.Gia) AS dataValue
    FROM DANHMUC DM
    JOIN SANPHAM SP ON DM.ID = SP.ID_DM
    JOIN CTHOADON CTHD ON SP.ID = CTHD.ID_SP
    JOIN HOADON HD ON CTHD.ID_HD = HD.ID
    WHERE HD.TrangThai = 1 
      AND MONTH(HD.ThoiGian) = @Month 
      AND YEAR(HD.ThoiGian) = @Year
    GROUP BY DM.Ten;
END
GO
/****** Object:  StoredProcedure [dbo].[GetDT00daysChart]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[GetDT00daysChart]
    @Today DATE
AS
BEGIN
    SELECT FORMAT(CAST(HD.ThoiGian AS DATE), 'dd/MM') AS chartColumn,
           SUM(CTHD.SoLuong * CTHD.Gia) AS dataValue
    FROM HOADON HD
    JOIN CTHOADON CTHD ON HD.ID = CTHD.ID_HD
    WHERE HD.ThoiGian >= DATEADD(DAY, -00, @Today)
      AND HD.TrangThai = 0
    GROUP BY CAST(HD.ThoiGian AS DATE)
    ORDER BY CAST(HD.ThoiGian AS DATE);
END;
GO
/****** Object:  StoredProcedure [dbo].[GetDT10daysChart]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[GetDT10daysChart]
    @Today DATE
AS
BEGIN
    SELECT FORMAT(CAST(HD.ThoiGian AS DATE), 'dd/MM') AS chartColumn,
           SUM(CTHD.SoLuong * CTHD.Gia) AS dataValue
    FROM HOADON HD
    JOIN CTHOADON CTHD ON HD.ID = CTHD.ID_HD
    WHERE HD.ThoiGian >= DATEADD(DAY, -10, @Today)
      AND HD.TrangThai = 1
    GROUP BY CAST(HD.ThoiGian AS DATE)
    ORDER BY CAST(HD.ThoiGian AS DATE);
END;
GO
/****** Object:  StoredProcedure [dbo].[getDTWeekChart]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[getDTWeekChart]
    @Month INT,
    @Year INT
AS
BEGIN
    SELECT 
        DATEPART(WEEK, HD.ThoiGian) AS chartColumn,
        SUM(CTHD.SoLuong * CTHD.Gia) AS dataValue
    FROM HOADON HD
    JOIN CTHOADON CTHD ON HD.ID = CTHD.ID_HD
    WHERE MONTH(HD.ThoiGian) = @Month 
      AND YEAR(HD.ThoiGian) = @Year 
      AND HD.TrangThai = 1
    GROUP BY DATEPART(WEEK, HD.ThoiGian)
    ORDER BY chartColumn;
END
GO
/****** Object:  StoredProcedure [dbo].[getDTYearChart]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[getDTYearChart]
    @nam INT
AS
BEGIN
    SELECT 
        FORMAT(HD.ThoiGian, 'MM/yyyy') AS chartColumn,
        SUM(CTHD.SoLuong * CTHD.Gia) AS dataValue
    FROM HOADON HD
    JOIN CTHOADON CTHD ON HD.ID = CTHD.ID_HD
    WHERE YEAR(HD.ThoiGian) = @nam 
      AND HD.TrangThai = 1
    GROUP BY FORMAT(HD.ThoiGian, 'MM/yyyy')
    ORDER BY chartColumn;
END
GO
/****** Object:  StoredProcedure [dbo].[getHomNayDT]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[getHomNayDT]
AS
BEGIN
    SELECT 
        COALESCE(FORMAT(SUM(CASE WHEN hd.TrangThai = '0' THEN ct.SoLuong * sp.Gia ELSE 0 END), 'N0'), '0') AS TongDT,
        COUNT(CASE WHEN hd.TrangThai = '0' THEN 1 END) AS SoDonTC,
        COUNT(CASE WHEN hd.TrangThai = '2' THEN 1 END) AS SoDonBH
    FROM HOADON hd
    JOIN CTHOADON ct ON hd.ID = ct.ID_HD
    JOIN SANPHAM sp ON ct.ID_SP = sp.ID
    WHERE hd.TrangThai = '0' AND CONVERT(DATE, hd.ThoiGian) = CONVERT(DATE, GETDATE());
END;
GO
/****** Object:  StoredProcedure [dbo].[getNamNayDT]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[getNamNayDT]
AS
BEGIN
    SELECT 
        COALESCE(FORMAT(SUM(CASE WHEN hd.TrangThai = '0' THEN ct.SoLuong * sp.Gia ELSE 0 END), 'N0'), '0') AS TongDT,
        COUNT(CASE WHEN hd.TrangThai = '0' THEN 1 END) AS SoDonTC,
        COUNT(CASE WHEN hd.TrangThai = '2' THEN 1 END) AS SoDonBH
    FROM HOADON hd
    JOIN CTHOADON ct ON hd.ID = ct.ID_HD
    JOIN SANPHAM sp ON ct.ID_SP = sp.ID
    WHERE hd.TrangThai = '0'
      AND YEAR(hd.ThoiGian) = YEAR(GETDATE());
END
GO
/****** Object:  StoredProcedure [dbo].[getNgayDTCaoNhat]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[getNgayDTCaoNhat]
AS
BEGIN
    SELECT TOP 1
        COALESCE(FORMAT(SUM(CASE WHEN hd.TrangThai = '0' THEN ct.SoLuong * sp.Gia ELSE 0 END), 'N0'), '0') AS TongDT,
        COUNT(CASE WHEN hd.TrangThai = '0' THEN 1 END) AS SoDonTC,
        COUNT(CASE WHEN hd.TrangThai = '2' THEN 1 END) AS SoDonBH,
        CONVERT(DATE, hd.ThoiGian) AS Ngay
    FROM HOADON hd
    JOIN CTHOADON ct ON hd.ID = ct.ID_HD
    JOIN SANPHAM sp ON ct.ID_SP = sp.ID
    GROUP BY CONVERT(DATE, hd.ThoiGian)
    ORDER BY TongDT DESC;
END
GO
/****** Object:  StoredProcedure [dbo].[getSP_DM]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE  [dbo].[getSP_DM]
    @ID_DM INT,
    @month INT,
    @year INT
AS
BEGIN
    SELECT 
        sp.ID AS ID_SP,
        sp.TenSP AS TenSP,
        sp.Gia AS Gia,
        COALESCE(SUM(ct.SoLuong), 0) AS SoLuongDaBan,
        dm.Ten AS DacDiem
    FROM SANPHAM sp
    LEFT JOIN CTHOADON ct ON sp.ID = ct.ID_SP
    JOIN HOADON hd ON ct.ID_HD = hd.ID
    JOIN DANHMUC dm ON sp.ID_DM = dm.ID
    WHERE hd.TrangThai = '0' AND dm.ID = @ID_DM AND MONTH(hd.ThoiGian) = @month AND YEAR(hd.ThoiGian) = @year
    GROUP BY sp.ID, sp.TenSP, sp.Gia, dm.Ten
    ORDER BY SoLuongDaBan DESC;
END
GO
/****** Object:  StoredProcedure [dbo].[getSP_TH]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[getSP_TH]
    @ID_TH INT,
    @month INT,
    @year INT
AS
BEGIN
    SELECT 
        sp.ID AS ID_SP,
        sp.TenSP,
        sp.Gia,
        SUM(ct.SoLuong) AS SoLuongDaBan,
        th.Ten AS DacDiem
    FROM SANPHAM sp
    LEFT JOIN CTHOADON ct ON sp.ID = ct.ID_SP
    JOIN HOADON hd ON ct.ID_HD = hd.ID
    JOIN THUONGHIEU th ON sp.ID_TH = th.ID
    WHERE hd.TrangThai = '0' AND th.ID = @ID_TH AND MONTH(hd.ThoiGian) = @month AND YEAR(hd.ThoiGian) = @year
    GROUP BY sp.ID, sp.TenSP, sp.Gia, th.Ten
    ORDER BY SoLuongDaBan DESC;
END
GO
/****** Object:  StoredProcedure [dbo].[getSPBanKem]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE   PROCEDURE [dbo].[getSPBanKem]
AS
BEGIN
    SELECT TOP 1 
        SP.TenSP AS name, 
        SP.AnhSP AS value
    FROM SANPHAM SP
    JOIN CTHOADON CTHD ON SP.ID = CTHD.ID_SP
    JOIN HOADON HD ON CTHD.ID_HD = HD.ID
    WHERE HD.ThoiGian >= DATEADD(DAY, -60, GETDATE()) 
      AND HD.TrangThai = 1
    GROUP BY SP.TenSP, SP.AnhSP
    ORDER BY SUM(CTHD.SoLuong * CTHD.Gia) ASC;
END
GO
/****** Object:  StoredProcedure [dbo].[getSPTrending]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[getSPTrending]
AS
BEGIN
    SELECT TOP 1 
        sp.TenSP AS name,
        sp.AnhSP AS value
    FROM SANPHAM sp 
    JOIN CTHOADON cthd ON sp.ID = cthd.ID_SP
    JOIN HOADON hd ON cthd.ID_HD = hd.ID
    WHERE hd.ThoiGian >= DATEADD(DAY, -60, GETDATE()) AND hd.TrangThai = 1
    GROUP BY sp.TenSP, sp.AnhSP  
    ORDER BY SUM(cthd.SoLuong * cthd.Gia) DESC;
END
GO
/****** Object:  StoredProcedure [dbo].[getThangNayDT]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[getThangNayDT]
AS
BEGIN
    SELECT 
        COALESCE(FORMAT(SUM(CASE WHEN hd.TrangThai = '0' THEN ct.SoLuong * sp.Gia ELSE 0 END), 'N0'), '0') AS TongDT,
        COUNT(CASE WHEN hd.TrangThai = '0' THEN 1 END) AS SoDonTC,
        COUNT(CASE WHEN hd.TrangThai = '2' THEN 1 END) AS SoDonBH
    FROM HOADON hd
    JOIN CTHOADON ct ON hd.ID = ct.ID_HD
    JOIN SANPHAM sp ON ct.ID_SP = sp.ID
    WHERE hd.TrangThai = '0'
      AND YEAR(hd.ThoiGian) = YEAR(GETDATE()) 
      AND MONTH(hd.ThoiGian) = MONTH(GETDATE());
END
GO
/****** Object:  StoredProcedure [dbo].[getThuongHieuChart]    Script Date: 8/14/2024 5:14:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[getThuongHieuChart]
    @Month INT,
    @Year INT
AS
BEGIN
    SELECT  
        TH.Ten AS chartColumn, 
        SUM(CTHD.SoLuong * CTHD.Gia) AS dataValue
    FROM THUONGHIEU TH
    JOIN SANPHAM SP ON TH.ID = SP.ID_TH
    JOIN CTHOADON CTHD ON SP.ID = CTHD.ID_SP
    JOIN HOADON HD ON CTHD.ID_HD = HD.ID
    WHERE HD.TrangThai = 1 
      AND MONTH(HD.ThoiGian) = @Month 
      AND YEAR(HD.ThoiGian) = @Year
    GROUP BY TH.Ten;
END
GO
USE [master]
GO
ALTER DATABASE [QL_BAN_QUAN_AO44] SET  READ_WRITE 
GO
