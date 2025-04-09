USE master
GO
CREATE DATABASE STORE
GO
USE STORE
GO

ALTER TABLE USERS ALTER COLUMN vaitro TINYINT NOT NULL;
SELECT * FROM dbo.USERS

SELECT * FROM dbo.USERS
CREATE TABLE USERS(
	id_user VARCHAR(50) PRIMARY KEY,
	sdt VARCHAR(10) NOT NULL,
	hinh VARCHAR(255) NULL,
	hoten NVARCHAR(50) NOT NULL,
	matkhau VARCHAR(50) NOT NULL,
	vaitro BIT NOT NULL
)
GO
CREATE TABLE LOAI(
	id_loai INT IDENTITY(1,1) PRIMARY KEY,
	ten_loai NVARCHAR(255) NOT NULL
)
GO
SELECT * FROM dbo.SANPHAM
CREATE TABLE SANPHAM(
	id_sanpham INT IDENTITY(1,1) PRIMARY KEY,
	ten_sanpham NVARCHAR(255) NOT NULL,
	soluong INT NOT NULL,
	hinh VARCHAR(255) NULL,
	mota NVARCHAR(MAX) NOT NULL,
	motangan NVARCHAR(MAX) NULL,
	gia INT NOT NULL,
	giamgia INT NOT NULL,
	ngaytao DATE NOT NULL,
	id_loai INT FOREIGN KEY REFERENCES Loai(id_loai)
)
GO
SELECT * FROM dbo.HOADON
CREATE TABLE HOADON(
	id_hoadon INT IDENTITY(1,1) PRIMARY KEY,
	ngaytao DATE NOT NULL,
	trangthai VARCHAR(30) NOT NULL,
	diachi NVARCHAR(50) NOT NULL,
	giaohang NVARCHAR(MAX) NULL,
	id_user VARCHAR(50) FOREIGN KEY REFERENCES Users(id_user)
)
GO
SELECT * FROM dbo.GIOHANGCHITIET
CREATE TABLE HOADONCHITIET(
	id_hoadon INT,
	id_sanpham INT,
	soluong INT NOT NULL,
	giamgia INT NULL,
	PRIMARY KEY (id_hoadon, id_sanpham),
    FOREIGN KEY (id_hoadon) REFERENCES HoaDon(id_hoadon),
    FOREIGN KEY (id_sanpham) REFERENCES SanPham(id_sanpham)
)
GO
SELECT * FROM dbo.GIOHANG
CREATE TABLE GIOHANG(
	id_giohang INT IDENTITY(1,1) PRIMARY KEY,
	id_user VARCHAR(50) FOREIGN KEY REFERENCES Users(id_user)
) 
GO
SELECT * FROM dbo.GIOHANGCHITIET
CREATE TABLE GIOHANGCHITIET(
	id_giohang INT,
	id_sanpham INT,
	soluong INT NOT NULL,
	PRIMARY KEY (id_giohang, id_sanpham),
    FOREIGN KEY (id_giohang) REFERENCES GioHang(id_giohang),
    FOREIGN KEY (id_sanpham) REFERENCES SanPham(id_sanpham)
)
GO

ALTER TABLE hoadonchitiet ADD dongia DECIMAL(10,2) NOT NULL DEFAULT 0;

SELECT TOP (10) 
    u1_0.hoten, 
    SUM(hdct1_0.soluong * sp1_0.gia) AS tong_tien, 
    MIN(hd1_0.ngaytao) AS ngay_dau_tien, 
    MAX(hd1_0.ngaytao) AS ngay_cuoi_cung
FROM hoadon hd1_0
JOIN users u1_0 ON u1_0.id_user = hd1_0.id_user
JOIN hoadonchitiet hdct1_0 ON hd1_0.id_hoadon = hdct1_0.id_hoadon
JOIN sanpham sp1_0 ON hdct1_0.id_sanpham = sp1_0.id_sanpham
GROUP BY u1_0.id_user, u1_0.hoten
ORDER BY SUM(hdct1_0.soluong * sp1_0.gia) DESC;

SELECT * FROM dbo.USERS
SELECT * FROM dbo.HOADONCHITIET
SELECT * FROM dbo.SANPHAM
SELECT * FROM dbo.HOADONCHITIET
INSERT INTO GIOHANGCHITIET (id_giohang, id_sanpham, soluong) VALUES
(1, 4, 2),
(1, 5, 1),
(2, 3, 1);


-- lấy tất cả sản phẩm theo lượt bán giảm dần theo giá 25000 đến 30000
--điều kiện sản phẩm bán chạy
--tìm theo sản phẩm thấp nhất
-- tìm theo ngày tạo mới nhất
SELECT 
    sp.id_sanpham, 
    sp.ten_sanpham, 
    SUM(hdct.soluong) AS "soluogban",
	sp.gia
FROM HOADONCHITIET hdct
JOIN SANPHAM sp ON hdct.id_sanpham = sp.id_sanpham
WHERE sp.gia BETWEEN 25000 AND 40000
GROUP BY sp.id_sanpham,
         sp.ten_sanpham,
         sp.gia
ORDER BY "soluogban" DESC;

USE STORE
SELECT * FROM dbo.SANPHAM
SELECT * FROM dbo.LOAI
SELECT * FROM dbo.USERS

UPDATE dbo.USERS SET vaitro = 1

SELECT * FROM dbo.LOAI
INSERT INTO SANPHAM (ten_sanpham, soluong, hinh, mota, motangan, gia, giamgia, ngaytao, id_loai)
VALUES
('Paracetamol 500mg', 100, 'paracetamol.jpg', N'Thuốc giảm đau, hạ sốt', 'Giảm đau, hạ sốt', 25000, 10, '2025-02-26', 1),
('Ibuprofen 400mg', 200, 'ibuprofen.jpg', 'Chống viêm, giảm đau', 'Giảm đau, kháng viêm', 30000, 5, '2025-02-26', 1),
('Aspirin 81mg', 150, 'aspirin.jpg', 'Hỗ trợ tim mạch, giảm đau', 'Hỗ trợ tim mạch', 28000, 8, '2025-02-26', 2),
('Amoxicillin 500mg', 120, 'amoxicillin.jpg', 'Kháng sinh trị nhiễm khuẩn', 'Kháng sinh phổ rộng', 45000, 12, '2025-02-26', 3),
('Cefixime 200mg', 90, 'cefixime.jpg', 'Kháng sinh nhóm Cephalosporin', 'Trị viêm nhiễm', 55000, 15, '2025-02-26', 3),
('Vitamin C 1000mg', 300, 'vitamin_c.jpg', 'Tăng sức đề kháng', 'Hỗ trợ miễn dịch', 60000, 10, '2025-02-26', 4),
('Calcium D3', 250, 'calcium_d3.jpg', 'Bổ sung canxi và vitamin D3', 'Giúp xương chắc khỏe', 70000, 10, '2025-02-26', 4),
('Magnesium B6', 180, 'magnesium_b6.jpg', 'Giúp giảm căng thẳng, ngủ ngon', 'Bổ sung magiê', 65000, 12, '2025-02-26', 4),
('Berberin 100mg', 400, 'berberin.jpg', 'Trị tiêu chảy, rối loạn tiêu hóa', 'Giảm đau bụng', 20000, 5, '2025-02-26', 5),
('Loperamid 2mg', 100, 'loperamid.jpg', 'Điều trị tiêu chảy cấp', 'Cầm tiêu chảy', 25000, 8, '2025-02-26', 5),
('Omeprazole 20mg', 130, 'omeprazole.jpg', 'Điều trị viêm loét dạ dày', 'Hỗ trợ tiêu hóa', 50000, 15, '2025-02-26', 1),
('Esomeprazole 40mg', 110, 'esomeprazole.jpg', 'Chống trào ngược dạ dày', 'Giảm acid dạ dày', 60000, 10, '2025-02-26', 2),
('Panadol Extra', 200, 'panadol_extra.jpg', 'Giảm đau nhanh', 'Giảm đau đầu, cảm sốt', 35000, 10, '2025-02-26', 1),
('Telfast 180mg', 90, 'telfast.jpg', 'Chống dị ứng', 'Giảm hắt hơi, sổ mũi', 75000, 10, '2025-02-26', 4),
('Cetirizine 10mg', 150, 'cetirizine.jpg', 'Chống dị ứng, giảm ngứa', 'Giảm viêm mũi dị ứng', 50000, 3, '2025-02-26', 1),
('Loratadine 10mg', 180, 'loratadine.jpg', 'Giảm các triệu chứng dị ứng', 'Chống dị ứng không gây buồn ngủ', 45000, 10, '2025-02-26', 1),
('Cough Syrup', 160, 'cough_syrup.jpg', 'Siro ho giảm ho', 'Trị ho hiệu quả', 40000, 5, '2025-02-26', 2),
('Acetylcysteine 200mg', 130, 'acetylcysteine.jpg', 'Làm loãng đờm, trị ho', 'Trị ho có đờm', 55000, 12, '2025-02-26', 2),
('Strepsils', 200, 'strepsils.jpg', 'Viên ngậm trị đau họng', 'Giảm viêm họng', 30000, 10, '2025-02-26', 3),
('Efferalgan Codeine', 110, 'efferalgan.jpg', 'Giảm đau mạnh', 'Giảm đau có codein', 60000, 10, '2025-02-26', 3),
('Domperidone 10mg', 120, 'domperidone.jpg', 'Chống buồn nôn', 'Điều trị nôn mửa', 45000, 8, '2025-02-26', 4),
('Metformin 500mg', 100, 'metformin.jpg', 'Điều trị tiểu đường', 'Kiểm soát đường huyết', 70000, 10, '2025-02-26', 1),
('Glibenclamide 5mg', 130, 'glibenclamide.jpg', 'Thuốc trị tiểu đường', 'Kiểm soát đường huyết', 65000, 12, '2025-02-26', 2),
('Losartan 50mg', 90, 'losartan.jpg', 'Điều trị cao huyết áp', 'Giảm huyết áp hiệu quả', 80000, 10, '2025-02-26', 2),
('Amlodipine 5mg', 110, 'amlodipine.jpg', 'Thuốc hạ huyết áp', 'Ổn định huyết áp', 60000, 12, '2025-02-26', 3),
('Simvastatin 20mg', 120, 'simvastatin.jpg', 'Giảm cholesterol', 'Hạ mỡ máu', 75000, 10, '2025-02-26', 3),
('Atorvastatin 10mg', 130, 'atorvastatin.jpg', 'Điều trị rối loạn lipid máu', 'Hạ cholesterol', 85000, 4, '2025-02-26', 4),
('Insulin Lantus', 70, 'insulin.jpg', 'Hỗ trợ điều trị tiểu đường', 'Kiểm soát đường huyết', 90000, 5, '2025-02-26', 9),
('Hydrocortisone Cream', 100, 'hydrocortisone.jpg', 'Điều trị viêm da, dị ứng', 'Chống viêm da', 50000, 10, '2025-02-26', 1);
GO


INSERT INTO SANPHAM (ten_sanpham, soluong, hinh, mota, motangan, gia, giamgia, ngaytao, id_loai)
VALUES
(N'Paracetamol 500mg', 100, 'paracetamol.jpg', N'Thuốc giảm đau, hạ sốt', N'Giảm đau, hạ sốt', 25000, 10, '2025-02-26', 1),
(N'Ibuprofen 400mg', 200, 'ibuprofen.jpg', N'Chống viêm, giảm đau', N'Giảm đau, kháng viêm', 30000, 5, '2025-02-26', 1),
(N'Aspirin 81mg', 150, 'aspirin.jpg', N'Hỗ trợ tim mạch, giảm đau', N'Hỗ trợ tim mạch', 28000, 8, '2025-02-26', 2),
(N'Amoxicillin 500mg', 120, 'amoxicillin.jpg', N'Kháng sinh trị nhiễm khuẩn', N'Kháng sinh phổ rộng', 45000, 12, '2025-02-26', 3),
(N'Cefixime 200mg', 90, 'cefixime.jpg', N'Kháng sinh nhóm Cephalosporin', N'Trị viêm nhiễm', 55000, 15, '2025-02-26', 3),
(N'Vitamin C 1000mg', 300, 'vitamin_c.jpg', N'Tăng sức đề kháng', N'Hỗ trợ miễn dịch', 60000, 10, '2025-02-26', 4),
(N'Calcium D3', 250, 'calcium_d3.jpg', N'Bổ sung canxi và vitamin D3', N'Giúp xương chắc khỏe', 70000, 10, '2025-02-26', 4),
(N'Magnesium B6', 180, 'magnesium_b6.jpg', N'Giúp giảm căng thẳng, ngủ ngon', N'Bổ sung magiê', 65000, 12, '2025-02-26', 4),
(N'Berberin 100mg', 400, 'berberin.jpg', N'Trị tiêu chảy, rối loạn tiêu hóa', N'Giảm đau bụng', 20000, 5, '2025-02-26', 5),
(N'Loperamid 2mg', 100, 'loperamid.jpg', N'Điều trị tiêu chảy cấp', N'Cầm tiêu chảy', 25000, 8, '2025-02-26', 5);
GO


SELECT ten_sanpham ,gia, HOADONCHITIET.soluong FROM dbo.SANPHAM 
INNER JOIN dbo.HOADONCHITIET ON HOADONCHITIET.id_sanpham = SANPHAM.id_sanpham
GROUP BY ten_sanpham,
         gia,
         HOADONCHITIET.soluong
ORDER BY HOADONCHITIET.soluong ASC

SELECT * FROM dbo.USERS
UPDATE dbo.USERS SET id_user = 1 WHERE id_user1 = 1


 SELECT * FROM dbo.HOADONCHITIET

 SELECT  SUM(soluong) AS "sl" FROM dbo.HOADONCHITIET
 GROUP BY soluong
ORDER BY soluong DESC

SELECT * FROM 
SELECT l.ten_loai, 
       SUM(sp.gia * hdct.soluong) AS tongDoanhThu, 
       SUM(hdct.soluong) AS tongSoLuongBan, 
       MAX(sp.gia) AS giaCaoNhat, 
       MIN(sp.gia) AS giaThapNhat, 
       AVG(sp.gia) AS giaTrungBinh 
FROM SANPHAM sp
JOIN LOAI l ON sp.id_loai = l.id_loai
JOIN HOADONCHITIET hdct ON sp.id_sanpham = hdct.id_sanpham
GROUP BY l.ten_loai
ORDER BY tongDoanhThu DESC;

SELECT SUM(
    CASE 
        WHEN giaohang = 'Giao hàng tiêu chuẩn' THEN 20000
        WHEN giaohang = 'Giao hàng nhanh' THEN 50000
        WHEN TRY_CAST(giaohang AS INT) IS NOT NULL THEN CAST(giaohang AS INT)
        ELSE 0  -- Nếu không hợp lệ, đặt về 0
    END
) AS tongTienVanChuyen
FROM HOADON;

SELECT 
    l.ten_loai,  
    u.hoten AS nguoiMua,
    u.sdt AS soDienThoai,
    SUM(sp.gia * hdct.soluong) AS tongDoanhThu, 
    SUM(hdct.soluong) AS tongSoLuongBan, 
    MAX(sp.gia) AS giaCaoNhat, 
    MIN(sp.gia) AS giaThapNhat, 
    AVG(sp.gia) AS giaTrungBinh 
FROM HOADON hd
JOIN USERS u ON hd.id_user = u.id_user
JOIN HOADONCHITIET hdct ON hd.id_hoadon = hdct.id_hoadon
JOIN SANPHAM sp ON hdct.id_sanpham = sp.id_sanpham
JOIN LOAI l ON sp.id_loai = l.id_loai
GROUP BY l.ten_loai, u.hoten, u.sdt
ORDER BY tongDoanhThu DESC;



SELECT * FROM dbo.HOADON


