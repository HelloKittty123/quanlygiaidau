-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 05, 2022 lúc 02:11 PM
-- Phiên bản máy phục vụ: 10.4.22-MariaDB
-- Phiên bản PHP: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `league_football_management`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bxh`
--

CREATE TABLE `bxh` (
  `vitri` int(11) NOT NULL,
  `mavong` int(11) NOT NULL,
  `madoi` int(11) NOT NULL,
  `sotran` int(11) NOT NULL,
  `stthang` int(11) NOT NULL,
  `stthua` int(11) NOT NULL,
  `sthoa` int(11) NOT NULL,
  `sobanthang` int(11) NOT NULL,
  `sobanthua` int(11) NOT NULL,
  `hieuso` int(11) NOT NULL,
  `diem` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `bxh`
--

INSERT INTO `bxh` (`vitri`, `mavong`, `madoi`, `sotran`, `stthang`, `stthua`, `sthoa`, `sobanthang`, `sobanthua`, `hieuso`, `diem`) VALUES
(1, 1, 33, 1, 1, 0, 0, 3, 1, 2, 3),
(6, 1, 1, 1, 0, 1, 0, 1, 3, -2, 0),
(2, 1, 37, 1, 1, 0, 0, 2, 1, 1, 3),
(5, 1, 31, 1, 0, 1, 0, 1, 2, -1, 0),
(3, 1, 32, 1, 0, 0, 1, 2, 2, 0, 1),
(4, 1, 27, 1, 0, 0, 1, 2, 2, 0, 1),
(1, 2, 33, 2, 2, 0, 0, 6, 3, 3, 6),
(2, 2, 1, 2, 0, 2, 0, 3, 6, -3, 0),
(1, 3, 33, 3, 3, 0, 0, 10, 6, 4, 9),
(2, 3, 1, 3, 0, 3, 0, 6, 10, -4, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `clb`
--

CREATE TABLE `clb` (
  `madoi` int(11) NOT NULL,
  `tendoi` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `doitruong` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `HLV` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `socauthu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `clb`
--

INSERT INTO `clb` (`madoi`, `tendoi`, `doitruong`, `HLV`, `socauthu`) VALUES
(1, 'Arsenal', 'Ben White', 'Mikel Arteta', 3),
(2, 'Liverpool', 'Alisson Becker', 'Jurgen Klopp', 2),
(3, 'Leicester City', 'AAA', 'Thomas Tuchel', 1),
(4, 'Watford', 'QQQ', 'Thomas Tuchel', 0),
(21, 'Southampton', 'CCCCCCC', 'Thomas Tuchel', 0),
(22, 'Burnley', 'HHHH', 'Thomas Tuchel', 1),
(23, 'Man City', '111', '222', 0),
(24, 'West Ham', '222', '111', 0),
(25, 'Man Utd', '333', '22', 0),
(26, 'Wolves', '333', '444', 0),
(27, 'Brighton', '333', '444', 0),
(28, 'Tottenham', '333', '444', 0),
(29, 'Everton', '333', '444', 0),
(31, 'Crystal Palace', '66', '77', 0),
(32, 'Brentford', '777', '88', 1),
(33, 'Aston Villa', '5', '2', 0),
(34, 'Leeds', '22', '11', 0),
(35, 'Newcastle', '66', '77', 0),
(36, 'Norwich', '33', '44', 0),
(37, 'Chelsea', '22', '333', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `kqtd`
--

CREATE TABLE `kqtd` (
  `matran` int(11) NOT NULL,
  `madoi1` int(11) NOT NULL,
  `madoi2` int(11) NOT NULL,
  `SVD` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `thoigian` date NOT NULL,
  `banthang1` int(11) NOT NULL,
  `banthang2` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `kqtd`
--

INSERT INTO `kqtd` (`matran`, `madoi1`, `madoi2`, `SVD`, `thoigian`, `banthang1`, `banthang2`) VALUES
(5, 1, 33, 'Mỹ Đình', '2000-12-12', 1, 3),
(2, 32, 27, 'Hàng Đẫy', '2000-12-12', 2, 2),
(3, 31, 37, 'Lạch Tray', '2000-12-01', 1, 2),
(11, 1, 33, '12345', '2021-01-01', 2, 3),
(21, 1, 33, '123', '2022-01-01', 3, 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thongtincauthu`
--

CREATE TABLE `thongtincauthu` (
  `macauthu` int(11) NOT NULL,
  `madoi` int(11) NOT NULL,
  `tencauthu` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ngaysinh` date NOT NULL,
  `soao` int(11) NOT NULL,
  `vitri` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `thongtincauthu`
--

INSERT INTO `thongtincauthu` (`macauthu`, `madoi`, `tencauthu`, `ngaysinh`, `soao`, `vitri`) VALUES
(1, 2, 'AVS', '2021-10-16', 14, 'Hậu vệ'),
(11, 2, 'CC', '2021-10-04', 5, 'THỦ MÔN'),
(14, 22, 'JJJJJK', '2021-11-02', 21, 'thủ môn'),
(15, 3, 'QƯQWQWQW', '2021-11-03', 56, 'HẬU VỆ'),
(21, 32, 'BBBBB', '2000-01-11', 12, 'Thủ môn'),
(26, 1, 'DDDA', '2001-12-01', 24, 'Thủ môn'),
(27, 1, 'AAAA', '2000-12-12', 12, 'Tiền đạo'),
(28, 1, '123', '2000-12-12', 23, 'tiền đạo');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `trandau`
--

CREATE TABLE `trandau` (
  `matran` int(11) NOT NULL,
  `mavong` int(11) NOT NULL,
  `tentran` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `trandau`
--

INSERT INTO `trandau` (`matran`, `mavong`, `tentran`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 1, 5),
(6, 1, 6),
(7, 1, 7),
(8, 1, 8),
(9, 1, 9),
(10, 1, 10),
(11, 2, 11),
(12, 2, 12),
(13, 2, 13),
(14, 2, 14),
(15, 2, 15),
(16, 2, 16),
(17, 2, 17),
(18, 2, 18),
(19, 2, 19),
(20, 2, 20),
(21, 3, 21),
(22, 3, 22),
(23, 3, 23),
(24, 3, 24),
(25, 3, 25),
(26, 3, 26),
(27, 3, 27),
(28, 3, 28),
(29, 3, 29),
(30, 3, 30),
(31, 4, 31),
(32, 4, 32),
(33, 4, 33),
(34, 4, 34),
(35, 4, 35),
(36, 4, 36),
(37, 4, 37),
(38, 4, 38),
(39, 4, 39),
(40, 4, 40),
(41, 5, 41),
(42, 5, 42),
(43, 5, 43),
(44, 5, 44),
(45, 5, 45),
(46, 5, 46),
(47, 5, 47),
(48, 5, 48),
(49, 5, 49),
(50, 5, 50),
(51, 6, 51),
(52, 6, 52),
(53, 6, 53),
(54, 6, 54),
(55, 6, 55),
(56, 6, 56),
(57, 6, 57),
(58, 6, 58),
(59, 6, 59),
(60, 6, 60),
(61, 7, 61),
(62, 7, 62),
(63, 7, 63),
(64, 7, 64),
(65, 7, 65),
(66, 7, 66),
(67, 7, 67),
(68, 7, 68),
(69, 7, 69),
(70, 7, 70),
(71, 8, 71),
(72, 8, 72),
(73, 8, 73),
(74, 8, 74),
(75, 8, 75),
(76, 8, 76),
(77, 8, 77),
(78, 8, 78),
(79, 8, 79),
(80, 8, 80);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `vongdau`
--

CREATE TABLE `vongdau` (
  `mavongdau` int(11) NOT NULL,
  `tenvongdau` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `vongdau`
--

INSERT INTO `vongdau` (`mavongdau`, `tenvongdau`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bxh`
--
ALTER TABLE `bxh`
  ADD KEY `mavong` (`mavong`),
  ADD KEY `madoi` (`madoi`);

--
-- Chỉ mục cho bảng `clb`
--
ALTER TABLE `clb`
  ADD PRIMARY KEY (`madoi`);

--
-- Chỉ mục cho bảng `kqtd`
--
ALTER TABLE `kqtd`
  ADD KEY `matran` (`matran`),
  ADD KEY `madoi1` (`madoi1`),
  ADD KEY `madoi2` (`madoi2`);

--
-- Chỉ mục cho bảng `thongtincauthu`
--
ALTER TABLE `thongtincauthu`
  ADD PRIMARY KEY (`macauthu`),
  ADD KEY `madoi` (`madoi`);

--
-- Chỉ mục cho bảng `trandau`
--
ALTER TABLE `trandau`
  ADD PRIMARY KEY (`matran`),
  ADD KEY `mavong` (`mavong`);

--
-- Chỉ mục cho bảng `vongdau`
--
ALTER TABLE `vongdau`
  ADD PRIMARY KEY (`mavongdau`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `clb`
--
ALTER TABLE `clb`
  MODIFY `madoi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=75;

--
-- AUTO_INCREMENT cho bảng `thongtincauthu`
--
ALTER TABLE `thongtincauthu`
  MODIFY `macauthu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT cho bảng `trandau`
--
ALTER TABLE `trandau`
  MODIFY `matran` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT cho bảng `vongdau`
--
ALTER TABLE `vongdau`
  MODIFY `mavongdau` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `bxh`
--
ALTER TABLE `bxh`
  ADD CONSTRAINT `bxh_ibfk_1` FOREIGN KEY (`mavong`) REFERENCES `vongdau` (`mavongdau`),
  ADD CONSTRAINT `bxh_ibfk_2` FOREIGN KEY (`madoi`) REFERENCES `clb` (`madoi`);

--
-- Các ràng buộc cho bảng `kqtd`
--
ALTER TABLE `kqtd`
  ADD CONSTRAINT `kqtd_ibfk_1` FOREIGN KEY (`matran`) REFERENCES `trandau` (`matran`),
  ADD CONSTRAINT `kqtd_ibfk_2` FOREIGN KEY (`madoi1`) REFERENCES `clb` (`madoi`),
  ADD CONSTRAINT `kqtd_ibfk_3` FOREIGN KEY (`madoi2`) REFERENCES `clb` (`madoi`);

--
-- Các ràng buộc cho bảng `thongtincauthu`
--
ALTER TABLE `thongtincauthu`
  ADD CONSTRAINT `thongtincauthu_ibfk_1` FOREIGN KEY (`madoi`) REFERENCES `clb` (`madoi`);

--
-- Các ràng buộc cho bảng `trandau`
--
ALTER TABLE `trandau`
  ADD CONSTRAINT `trandau_ibfk_1` FOREIGN KEY (`mavong`) REFERENCES `vongdau` (`mavongdau`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
