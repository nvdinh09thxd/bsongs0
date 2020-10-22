-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th9 12, 2020 lúc 05:14 AM
-- Phiên bản máy phục vụ: 10.1.32-MariaDB
-- Phiên bản PHP: 7.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `bsongs`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'Nhạc Hải Ngoại'),
(2, 'Nhạc Việt'),
(3, 'Nhạc Chế'),
(4, 'Nhạc Pop'),
(5, 'Nhạc Rock'),
(6, 'Nhạc Thánh'),
(7, 'Nhạc Remix'),
(8, 'Nhạc Dance');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `comments`
--

CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `id_song` int(11) NOT NULL,
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `comment` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `comments`
--

INSERT INTO `comments` (`id`, `id_song`, `username`, `comment`, `date_create`, `status`) VALUES
(1, 17, 'nvdinh185', 'bài hát đã sửa', '2020-09-12 02:00:05', 1),
(2, 17, 'toquyen', 'em yêu anh', '2020-09-12 02:00:02', 0),
(8, 17, 'admin', 'quá tuyệt vời', '2018-08-17 08:32:02', 1),
(13, 10, 'nvdinh185', 'cho nhau', '2018-08-17 08:32:02', 1),
(14, 10, 'admin', 'em yêu anh', '2018-08-17 08:32:02', 1),
(15, 5, 'nvdinh185', 'bài hát hay', '2020-09-09 04:10:26', 0),
(16, 5, 'admin', 'quá tuyệt vời', '2018-08-17 08:32:02', 1),
(17, 1, 'nvdinh185', 'em yêu anh', '2020-09-09 04:10:26', 0),
(18, 4, 'nvdinh185', 'chờ em', '2018-08-17 08:32:02', 1),
(19, 16, 'admin', 'cho nhau lối đi riêng', '2018-08-17 08:32:02', 1),
(20, 15, 'admin', 'tìm về nhà cha', '2020-09-09 04:10:24', 0),
(21, 17, 'toquyen', 'cho nhau', '2018-09-11 04:33:30', 1),
(22, 17, 'nvdinh185', 'em yêu anh', '2018-08-17 08:43:37', 0),
(23, 17, 'huonghoa', 'mình tên là Hoa', '2018-08-17 08:44:00', 1),
(24, 3, 'nvdinh185', 'vẫn mong em quay về', '2018-09-11 06:39:58', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `contacts`
--

CREATE TABLE `contacts` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `website` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `message` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `contacts`
--

INSERT INTO `contacts` (`id`, `name`, `email`, `website`, `message`) VALUES
(1, 'Khương Ngọc Lâm', 'chatwithme9x@gmail.com', 'https://ngockhuong.com', 'Liên hệ admin trang'),
(2, 'Lâm Ngọc Khương', 'chatwithme9x@gmail.com', 'https://ngockhuong.com', 'Liên hệ admin trang'),
(3, 'Trần Văn Sơn', 'sontv@vinaenter.com', 'http://vinaenter.edu.vn', 'Liên hệ admin trang'),
(4, 'Nguyễn Văn Định', 'chatwithme9x@gmail.com', 'http://vinaenter.edu.vn', 'Liên hệ admin trang');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `rating`
--

CREATE TABLE `rating` (
  `id` int(11) NOT NULL,
  `id_song` int(11) NOT NULL,
  `rating` float NOT NULL,
  `count` int(11) NOT NULL DEFAULT '1',
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `rating`
--

INSERT INTO `rating` (`id`, `id_song`, `rating`, `count`, `date`) VALUES
(1, 13, 2.42105, 19, '2019-12-18 14:53:06'),
(2, 12, 5, 2, '2019-12-18 14:54:04'),
(3, 9, 5.83333, 3, '2019-12-18 14:55:03'),
(4, 18, 5.63636, 11, '2020-09-12 02:01:53'),
(5, 17, 6.375, 4, '2019-12-18 14:52:12'),
(6, 2, 4, 6, '2019-12-18 13:15:36'),
(7, 10, 5, 2, '2019-12-18 13:21:38'),
(8, 6, 6, 2, '2019-12-18 13:24:33'),
(9, 11, 6.4, 5, '2019-12-18 13:34:21'),
(10, 16, 5.8125, 8, '2020-09-12 02:09:23');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `songs`
--

CREATE TABLE `songs` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `preview_text` text COLLATE utf8_unicode_ci NOT NULL,
  `detail_text` text COLLATE utf8_unicode_ci NOT NULL,
  `date_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `picture` text COLLATE utf8_unicode_ci NOT NULL,
  `counter` int(11) NOT NULL DEFAULT '0',
  `cat_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `songs`
--

INSERT INTO `songs` (`id`, `name`, `preview_text`, `detail_text`, `date_create`, `picture`, `counter`, `cat_id`) VALUES
(1, 'Nơi Ấy Con Tìm Về', 'Loi bai hat Noi Ay Con Tim Ve Nhac si Phan Manh Quynh Loi dang boi sonnycleon', 'Ngay con cat tieng khoc chao doi \r\nNgay me da noi niem vui trao dang ngap troi \r\nBang yeu thuong cua cha da danh \r\nde trong mai am tung ngay qua con lon len \r\nLa loi ru me dua ben canh vong cho con say \r\ngiac ngu \r\nla loi cha tung ngay am em mong sao cho con nen nguoi', '2018-08-16 09:01:46', 'noi-ay-con-tim-ve.jpg', 22, 2),
(2, 'Cang Kho Cang Yeu cho', 'Loi bai hat Cang cho Kho Cang YeuLoi dang boi kenvip88', 'Di dau tim ra duoc mot niem tin \r\nNiem tin mai mai chang phai tan \r\nGiua thoang choc nghe nhip tim rung dong \r\nVoi nhung yeu thuong', '2019-12-18 13:20:15', 'cang-kho-cang-yeu.jpg', 23, 4),
(3, 'Vẫn Mong Chừ', 'Loi bai hat Van Mong cho Loi dang boi huongviet1988 khờ', 'Anh van cho doi em \r\nKhi nang chieu roi \r\nDuong ve vang tenh \r\nMay bay cuoi troi buon thuong \r\nTheo gio ve dau ngan phuong \r\nAnh van cho doi em \r\nMua hat hiu ngoai hien', '2019-12-12 12:48:09', 'java3.jpg', 15, 2),
(4, 'Chờ em', 'chờ em, chờ những kỹ niệm hay chờ những niềm đau !!!.', '<p>chờ em, chờ những kỹ ni&ecirc;̣m hay chờ những ni&ecirc;̀m đau !!!.<br />\r\nNhững đ&ecirc;m thức trắng như h&ocirc;m nay anh v&acirc;̃n làm c&ocirc;ng vi&ecirc;̣c cũ....đó là Chờ em......<br />\r\nEm bi&ecirc;́t kh&ocirc;ng, đ&ecirc;m nay anh đã nghe m&ocirc;̣t bản nhạc r&acirc;́t hay, hay lắm. Anh nghe và hát theo lời có sẵn, nhưng.....chỉ hát được nữa bài thì anh ngừng hát theo.....vì sao em bi&ecirc;́t kh&ocirc;ng !!!.....vì .....nước mắt anh đang rơi.........<br />\r\n&quot;Mơ từng chiều về để hồn vẫn mơ<br />\r\nH&igrave;nh b&oacute;ng chưa phai mờ theo năm th&aacute;ng<br />\r\nD&ugrave; người đ&atilde; đi về nơi phương xa<br />\r\nĐể giờ đ&acirc;y con tim cứ ứơc mơ, cứ ng&oacute;ng chờ<br />\r\nH&agrave;ng c&acirc;y nghi&ecirc;ng lao xao c&aacute;nh chim ơi c&ograve;n bay về chốn nao</p>\r\n\r\n<p>Đau, ng&agrave;y vội t&agrave;n để đời m&atilde;i đau<br />\r\nTừng ph&uacute;t tr&ocirc;i qua chờ trong nỗi nhớ<br />\r\nDẫu trong ta cuộc t&igrave;nh đ&atilde; lỡ<br />\r\nNgười ơi khi n&agrave;o cho ta<br />\r\nThời gian ko bu&ocirc;ng tha để cho ta chờ em m&atilde;i th&ocirc;i</p>\r\n\r\n<p>Kh&ocirc;ng gian bao la đường về chiếc b&oacute;ng c&ocirc; đơn m&igrave;nh ta bước<br />\r\nĐ&ocirc;i ch&acirc;n đau thương ch&ocirc;ng gai ch&aacute;y s&aacute;ng l&ecirc;n tr&ecirc;n đời ta<br />\r\nKhi xưa b&ecirc;n em l&ograve;ng ta vui mỗi khi em cười<br />\r\nGiờ c&ograve;n lại g&igrave; nh&igrave;n quanh chỉ nỗi đau th&ocirc;i</p>\r\n\r\n<p>Tr&oacute;t y&ecirc;u em l&agrave; anh trọn đời m&atilde;i thương nhớ<br />\r\nKhi con tim b&acirc;y giờ vẫn lu&ocirc;n lu&ocirc;n mong chờ<br />\r\nD&ugrave; hai ta c&aacute;ch xa<br />\r\nCầu mong 1 ng&agrave;y cho ta nghe tiếng em<br />\r\nTrở về cho đời ta th&ocirc;i đ&acirc;u ng&agrave;y th&aacute;ng<br />\r\nNgười y&ecirc;u hỡi xin quay về nơi đ&acirc;y.</p>\r\n\r\n<p>Anh chờ em trọn đời đến mu&ocirc;n kiếp...<br />\r\nCầu mong một ng&agrave;y cho ta nghe tiếng em<br />\r\nTrở về cho ta th&ocirc;i đau v&igrave; nhớ<br />\r\nNgười y&ecirc;u ơi anh sẽ c&ograve;n chờ em......&quot;</p>\r\n\r\n<p>Anh cứ nghĩ đ&acirc;y là bài hát dành ri&ecirc;ng cho anh v&acirc;̣y.....Anh ng&ocirc;́c lắm phải kh&ocirc;ng? vì cứ mãi chờ m&ocirc;̣t người kh&ocirc;ng quay v&ecirc;̀.............!!!!!</p>\r\n\r\n<p>em giờ đã có cu&ocirc;̣c s&ocirc;́ng ri&ecirc;ng, có người y&ecirc;u và đang r&acirc;́t hạnh phúc nhỉ...!! Anh kh&ocirc;ng bi&ecirc;́t nữa vì đó chỉ là những suy đoán của ri&ecirc;ng anh mà th&ocirc;i. Còn anh....anh v&acirc;̃n như ngày nào; v&acirc;̃n là chàng ng&ocirc;́c m&ocirc;̃i l&acirc;̀n chạy xe ngang qua con hẻm vào nhà em là lại nhìn vào...., v&acirc;̃n là chàng ng&ocirc;́c l&ecirc;n face chỉ lặng lẻ vào face của em đ&ecirc;̉ bi&ecirc;́t em hi&ecirc;̣n như th&ecirc;́ nào....., v&acirc;̃n là chàng ng&ocirc;́c thức tới khuya nghe nhạc r&ocirc;̀i nhớ v&ecirc;̀ những kỹ ni&ecirc;̣m đã cùng có với em....., v&acirc;̃n là chàng ng&ocirc;́c từ ch&ocirc;́i tình cảm những c&ocirc; gái khác vì v&acirc;̃n nhớ em.....!!!!!!</p>\r\n\r\n<p>ng&ocirc;́c.....ng&ocirc;́c.....ng&ocirc;́c.....!!!! Đó có lẻ là từ thích hợp nh&acirc;́t đ&ocirc;́i với tình cảm anh dành cho em......Em bi&ecirc;́t kh&ocirc;ng, ngày đó khi em nhắn anh đừng làm phi&ecirc;̀n cu&ocirc;̣c s&ocirc;́ng của em nữa.......anh đau lắm. Đ&ecirc;́n khi em ch&acirc;́p nh&acirc;̣n quen anh thì anh lại bu&ocirc;ng lời cay đắng....chia tay.....có lẽ em nghĩ rằng anh mu&ocirc;́n trả thù em nhưng kh&ocirc;ng phải em à.....khi lớn anh bi&ecirc;́t suy nghĩ, anh mu&ocirc;́n em và anh chỉ lo học hành, đ&ecirc;̉ l&acirc;́y c&ocirc;ng danh, sự nghi&ecirc;̣p trước r&ocirc;̀i hãy y&ecirc;u nhau sau....anh gi&acirc;́u em vì nghĩ em sẽ hi&ecirc;̉u, vì nghĩ em sẽ chờ anh................nhưng................<br />\r\n.......................anh sai r&ocirc;̀i,.............thời gian và sự c&ocirc; đơn sẽ làm thay đ&ocirc;̃i con người, thay đ&ocirc;̃i tình y&ecirc;u.........!!!!!!!!!!!<br />\r\nEm quen anh &acirc;́y khi g&acirc;̀n h&ecirc;́t năm hai, anh bi&ecirc;́t chứ..........khi đó anh đã làm đi&ecirc;̀u r&acirc;́t ngu ng&ocirc;́c phải kh&ocirc;ng em.............giờ thì cũng đã hơn 3 nă mình kh&ocirc;ng gặp nhau r&ocirc;̀i em nhỉ....đ&ecirc;́n giờ phút này tim anh v&acirc;̃n còn hình bóng em, bi&ecirc;́t sau được khi tim anh trót y&ecirc;u em r&ocirc;̀i........................................!!!!!!!!!!!!!!!!!</p>\r\n', '2019-12-12 12:48:20', 'java4.jpg', 15, 2),
(5, 'Em cho gái mưa', 'Mưa trôi cả bầu Trời nắng, trượt THEO những nỗi buồn \r\nThấm ướt lệ sầu môi đắng vì đánh mất hy vọng \r\nLần đầu gặp nhau dưới mưa, trái tim rộn ràng bởi enh nhìn \r\nTình cảm dầm mưa thấm lâu, em nào ngờ... ', 'Mình hợp nhau đến như vậy thế nhưng... không phải là yêu! \r\nVà em muốn hỏi anh rằng \"chúng ta là thế nào?\" \r\nRồi... lặng người đến vô tận, trách sao được sự tàn nhẫn \r\nAnh trót vô tình.. thương em như- là- em- gái... ', '2019-12-19 12:46:08', '', 16, 2),
(6, 'Đổi thay nhau', 'Mình hợp nhau đến như vậy thế nhưng... không phải là yêu! \r\nVà em muốn hỏi anh rằng \"chúng ta là thế nào?\" \r\nRồi... lặng người đến vô tận, trách sao được sự tàn nhẫn \r\nAnh trót vô tình.. thương em như- là- em- gái... ', 'Mình hợp nhau đến như vậy thế nhưng... không phải là yêu! \r\nVà em muốn hỏi anh rằng \"chúng ta là thế nào?\" \r\nRồi... lặng người đến vô tận, trách sao được sự tàn nhẫn \r\nAnh trót vô tình.. thương em như- là- em- gái... ', '2019-12-18 13:24:04', 'doi-thay.jpg', 14, 1),
(7, 'Yêu em nhiều đến thế', 'Mình hợp nhau đến như vậy thế nhưng... không phải là yêu! \r\nVà em muốn hỏi anh rằng \"chúng ta là thế nào?\" \r\nRồi... lặng người đến vô tận, trách sao được sự tàn nhẫn \r\nAnh trót vô tình.. thương em như- là- em- gái... ', 'Mình hợp nhau đến như vậy thế nhưng... không phải là yêu! \r\nVà em muốn hỏi anh rằng \"chúng ta là thế nào?\" \r\nRồi... lặng người đến vô tận, trách sao được sự tàn nhẫn \r\nAnh trót vô tình.. thương em như- là- em- gái... ', '2020-09-12 03:03:17', 'java7.jpg', 14, 2),
(8, 'Tại sao', 'Mình hợp nhau đến như vậy thế nhưng... không phải là yêu! \r\nVà em muốn hỏi anh rằng \"chúng ta là thế nào?\" \r\nRồi... lặng người đến vô tận, trách sao được sự tàn nhẫn \r\nAnh trót vô tình.. thương em như- là- em- gái... ', 'Mình hợp nhau đến như vậy thế nhưng... không phải là yêu! \r\nVà em muốn hỏi anh rằng \"chúng ta là thế nào?\" \r\nRồi... lặng người đến vô tận, trách sao được sự tàn nhẫn \r\nAnh trót vô tình.. thương em như- là- em- gái... ', '2020-09-12 03:02:21', 'java8.jpg', 19, 2),
(9, 'Dĩ Vãng Cuộc Tình', 'Mình hợp nhau đến như vậy thế nhưng... không phải là yêu! \r\nVà em muốn hỏi anh rằng \"chúng ta là thế nào?\" \r\nRồi... lặng người đến vô tận, trách sao được sự tàn nhẫn \r\nAnh trót vô tình.. thương em như- là- em- gái... ', 'Mình hợp nhau đến như vậy thế nhưng... không phải là yêu! \r\nVà em muốn hỏi anh rằng \"chúng ta là thế nào?\" \r\nRồi... lặng người đến vô tận, trách sao được sự tàn nhẫn \r\nAnh trót vô tình.. thương em như- là- em- gái... ', '2019-12-18 14:55:16', 'java9.jpg', 21, 5),
(10, 'Nhớ để quên', 'Chẳng lẽ cứ xa nhau là không thể trở lại với nhau nữa và chẳng lẻ những kỹ niệm ngày xưa sẽ mãi trôi xa....!', '<p>Chẳng lẽ cứ xa nhau là không thể trở lại với nhau nữa và chẳng lẻ những kỹ niệm ngày xưa sẽ mãi trôi xa....!<br />\r\nPhải rồi....mới đó cũng vài năm rồi nhỉ...và giờ anh cười, cười vì anh vẫn không thể xóa tên em...<br />\r\nAnh ngốc thật nhỉ?. Mang theo yêu dấu và nỗi đau về em khi mà em đã ra đi...ước rằng hôm qua chỉ là cơn mơ để anh được mãi mơ về em và mãi không muốc thức giấc...<br />\r\n\"Thế nước mắt cứ khẽ lại rơi, nỗi đau riêng trong tim không nói nên lời. Hỡi nước mắt hỡi kỹ niệm ơn hãy cho ta một lần về phút ban đầu.....\"<br />\r\nCa từ này anh vẫn mãi không quên, anh cài từ khi hai ta hai lối. Anh vẫn sẽ nhớ, sẽ đi, đi về phía không em...</p>\r\n', '2020-09-12 03:03:05', 'java10.jpg', 42, 2),
(11, 'Only Love', 'có phải một ngày nào đó em cũng mãi xa cuộc đời tôi !\r\ncó phải tôi vẫn luôn là người ngộ nhận về một câu chuyện tình yêu tuyệt đẹp !', '<p>có phải m&ocirc;̣t ngày nào đó em cũng mãi xa cu&ocirc;̣c đời t&ocirc;i !<br />\r\ncó phải t&ocirc;i v&acirc;̃n lu&ocirc;n là người ng&ocirc;̣ nh&acirc;̣n v&ecirc;̀ m&ocirc;̣t c&acirc;u chuy&ecirc;̣n tình y&ecirc;u tuy&ecirc;̣t đẹp !<br />\r\ntrong màn đ&ecirc;m , ca từ của &quot; Only Love&quot; ng&acirc;n vang cũng chính là lúc t&acirc;m trạng b&ocirc;̀i h&ocirc;̀i và dạt dào cảm xúc với những rung đ&ocirc;̣ng v&ecirc;̣ m&ocirc;̣t người t&ocirc;i từng r&acirc;́t y&ecirc;u....!<br />\r\nchắc giờ này em đang say gi&acirc;́c n&ocirc;̀ng sau m&ocirc;̣t ngày dài hạnh phúc...à kh&ocirc;ng ! đúng hơn là em đang t&acirc;̣n hưởng m&ocirc;̣t cu&ocirc;̣c&nbsp;s&ocirc;́ng hạnh phúc với tình y&ecirc;u của mình....<br />\r\nT&ocirc;i vui, t&ocirc;i cười khi nhìn em hạnh phúc từ phía sau - m&ocirc;̣t nơi mà chẳng bao giờ em bi&ecirc;́t - &quot; nơi phía sau hạnh phúc&quot;.<br />\r\nem bi&ecirc;́t kh&ocirc;ng, từ ngày kh&ocirc;ng còn gặp em nữa , cu&ocirc;̣c s&ocirc;́ng của anh thay đ&ocirc;̃i r&acirc;́t nhi&ecirc;̀u. Anh kh&ocirc;ng còn nghẹn ngào, b&ocirc;́i r&ocirc;́i khi th&acirc;́y em b&ecirc;n người &acirc;́y nữa, cũng kh&ocirc;ng còn từng đ&ecirc;m nhớ v&ecirc;̀ hình ảnh của em, của những kỹ ni&ecirc;̣m ngày ta còn c&acirc;́p sách đ&ecirc;́n trường. Trong anh giờ chỉ còn ký ức - m&ocirc;̣t ký ức v&ecirc;̀ m&ocirc;́i tình đ&acirc;̀u kh&ocirc;ng phai.<br />\r\ntừ khi xa em, anh đã thử r&acirc;́t nhi&ecirc;̀u cách đ&ecirc;̉ qu&ecirc;n em: &quot; Anh lao vào c&ocirc;ng vi&ecirc;̣c, vào vi&ecirc;̣c học và hơn cả anh đã ép bản th&acirc;n y&ecirc;u m&ocirc;̣t người con gái khác với hy vọng xóa được hình ảnh của em...&quot; Nhưng em à! giờ anh nh&acirc;̣n ra đi&ecirc;̀u đó là kh&ocirc;ng th&ecirc;̉...!!!<br />\r\nCu&ocirc;̣c s&ocirc;́ng b&ocirc;̣n b&ecirc;̀ với những lo toan kh&ocirc;ng th&ecirc;̉ khi&ecirc;́n anh qu&ecirc;n em, khi y&ecirc;u người con gái khác anh lại chỉ th&acirc;́y hình ảnh của em nhi&ecirc;̀u hơn....Anh ng&ocirc;́c lắm phải kh&ocirc;ng! khi bi&ecirc;́t được có người con trai khác t&ocirc;́t hơn anh y&ecirc;u em anh đã đ&acirc;̀u hàng và chúc phúc cho hai người....Th&acirc;̣t sự giờ nghỉ lại lúc đó anh th&acirc;́y mình r&acirc;́t hèn và y&ecirc;́u đu&ocirc;́i<br />\r\nnhưng n&ecirc;́u anh được quay lại lúc đó thì anh v&acirc;̃n sẽ hành đ&ocirc;̣ng như v&acirc;̣y, em bi&ecirc;́t vì sao kh&ocirc;ng ? vì Anh y&ecirc;u Em.<br />\r\nVà anh v&acirc;̃n tin sẽ có ngày chúng ta gặp lại nhau, cười nói vui đùa cùng nhau như hai người bạn th&acirc;n của ngày trước.....<br />\r\nphải kh&ocirc;ng em - người lạ từng quen !</p>\r\n', '2019-12-18 13:34:34', 'only-love.jpg', 25, 1),
(12, 'Khó', 'Mình hợp nhau đến như vậy thế nhưng... không phải là yêu! \r\nVà em muốn hỏi anh rằng \"chúng ta là thế nào?\" \r\nRồi... lặng người đến vô tận, trách sao được sự tàn nhẫn \r\nAnh trót vô tình.. thương em như- là- em- gái... ', 'Mình hợp nhau đến như vậy thế nhưng... không phải là yêu! \r\nVà em muốn hỏi anh rằng \"chúng ta là thế nào?\" \r\nRồi... lặng người đến vô tận, trách sao được sự tàn nhẫn \r\nAnh trót vô tình.. thương em như- là- em- gái... ', '2019-12-18 14:54:00', 'java12.jpg', 23, 7),
(13, 'Bay giữa ngân hà', 'Mình hợp nhau đến như vậy thế nhưng... không phải là yêu! \r\nVà em muốn hỏi anh rằng \"chúng ta là thế nào?\" \r\nRồi... lặng người đến vô tận, trách sao được sự tàn nhẫn \r\nAnh trót vô tình.. thương em như- là- em- gái... ', 'Mình hợp nhau đến như vậy thế nhưng... không phải là yêu! \r\nVà em muốn hỏi anh rằng \"chúng ta là thế nào?\" \r\nRồi... lặng người đến vô tận, trách sao được sự tàn nhẫn \r\nAnh trót vô tình.. thương em như- là- em- gái... ', '2019-12-18 14:53:22', 'java13.jpg', 49, 7),
(14, 'Chọn Giê su thôi', 'Mình hợp nhau đến như vậy thế nhưng... không phải là yêu! \r\nVà em muốn hỏi anh rằng \"chúng ta là thế nào?\" \r\nRồi... lặng người đến vô tận, trách sao được sự tàn nhẫn \r\nAnh trót vô tình.. thương em như- là- em- gái... ', 'Mình hợp nhau đến như vậy thế nhưng... không phải là yêu! \r\nVà em muốn hỏi anh rằng \"chúng ta là thế nào?\" \r\nRồi... lặng người đến vô tận, trách sao được sự tàn nhẫn \r\nAnh trót vô tình.. thương em như- là- em- gái... ', '2019-12-12 12:49:25', 'java14.jpg', 54, 6),
(15, 'Tìm về nhà cha', 'Mình hợp nhau đến như vậy thế nhưng... không phải là yêu! \r\nVà em muốn hỏi anh rằng \"chúng ta là thế nào?\" \r\nRồi... lặng người đến vô tận, trách sao được sự tàn nhẫn \r\nAnh trót vô tình.. thương em như- là- em- gái... ', 'Mình hợp nhau đến như vậy thế nhưng... không phải là yêu! \r\nVà em muốn hỏi anh rằng \"chúng ta là thế nào?\" \r\nRồi... lặng người đến vô tận, trách sao được sự tàn nhẫn \r\nAnh trót vô tình.. thương em như- là- em- gái... ', '2019-12-18 13:46:43', 'java15.jpg', 54, 6),
(16, 'cho nhau loi di rieng', 'Xin giấu con Nơi cánh tay Cha Che phủ con Nhờ bóng Thiên Cha quyền năng ', 'Dù giông tố khiếp kinh, sóng gió vây quanh Nguyện xin Chúa dắt đưa vượt qua bão tố Lạy ha Thánh hiển vinh, Vua trên dương gian Con uôn vững lòng vì biết Cha bên con ', '2020-09-12 02:09:38', 'java16.jpg', 66, 6),
(17, 'Cho đến mãi mãi', 'Gai đâm thấu buốt nơi trán Giê-su Bao nhiêu ủa sả chất hết trên Ngài Lằn roi chứng minh ho cả thế giới Ngài yêu con đến nỗi chết thay con', 'Cha đã xóa hết tất cả lỗi lầm Giê-su chết thế công lý đã trọn Vì Ngài quá yêu bằng \r\n lòng hy sinh Nguyện xin Thánh ý của Cha được trọn', '2020-09-12 02:31:59', 'java17.jpg', 100, 6),
(18, 'Vua chí ái', 'Gai đâm thấu buốt nơi trán Giê-su Bao nhiêu ủa sả chất hết trên Ngài Lằn roi chứng minh ho cả thế giới Ngài yêu con đến nỗi chết thay con', 'Cha đã xóa hết tất cả lỗi lầm Giê-su chết thế công lý đã trọn Vì Ngài quá yêu bằng \r\n lòng hy sinh Nguyện xin Thánh ý của Cha được trọn', '2020-09-12 03:11:10', 'java18.jpg', 111, 6);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fullname` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `fullname`) VALUES
(1, 'admin', '202cb962ac59075b964b07152d234b70', 'Administrator'),
(2, 'dinh', '202cb962ac59075b964b07152d234b70', 'Nguyễn Văn Định'),
(3, 'ngockhuong', 'e10adc3949ba59abbe56e057f20f883e', 'Lâm Ngọc Khương'),
(4, 'khuongln', '202cb962ac59075b964b07152d234b70', 'Hello World'),
(5, 'manhme', '202cb962ac59075b964b07152d234b70', 'Đinh Công Mạnh'),
(6, 'nvdinh185', '202cb962ac59075b964b07152d234b70', 'Nguyễn Văn Định'),
(7, 'toquyen', '202cb962ac59075b964b07152d234b70', 'Tố Quyên'),
(8, 'huonghoa', 'e10adc3949ba59abbe56e057f20f883e', 'huong');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`,`name`);

--
-- Chỉ mục cho bảng `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `contacts`
--
ALTER TABLE `contacts`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `rating`
--
ALTER TABLE `rating`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `songs`
--
ALTER TABLE `songs`
  ADD PRIMARY KEY (`id`,`cat_id`),
  ADD KEY `cat_id` (`cat_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT cho bảng `contacts`
--
ALTER TABLE `contacts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `rating`
--
ALTER TABLE `rating`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `songs`
--
ALTER TABLE `songs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `songs`
--
ALTER TABLE `songs`
  ADD CONSTRAINT `songs_ibfk_1` FOREIGN KEY (`cat_id`) REFERENCES `categories` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
