<?php
    require_once('../sp_connect/dbhelp.php');
?>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Kết quả thi đấu</title>
	<!-- Link -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../asset/css/base.css">
    <link rel="stylesheet" href="../asset/css/main.css">
    <link rel="stylesheet" href="../asset/font/fontawesome-free-5.15.4-web/css/all.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
</head>
<body>
	<!-- Header -->
    <div class="app">
        <div class="header">
            <nav class="header__navbar">
                <ul class="header__navbar-list">
                    <li class="header__navbar-item">
                        <a href="../index.php" class="header__navbar-link">
                            <img src="../asset/img/Premier-League-Logo.jpg" alt="Premier League Logo" class="header__navbar-img">
                        </a>
                    </li>
                    <li class="header__navbar-item header__navbar-item--bold">
                        GIẢI BÓNG ĐÁ NGOẠI HẠNG ANH
                    </li>
                </ul>
                <ul class="header__navbar-list">
                    <li class="header__navbar-item">
                        <a href="../index.php" class="header__navbar-link">Đội bóng</a>
                    </li>
                    <li class="header__navbar-item">
                        <a href="../cauthu/index.php" class="header__navbar-link">Cầu thủ</a>
                    </li>
                    <li class="header__navbar-item header__navbar-item--active">
                        <a href="./index.php" class="header__navbar-link">Kết quả thi đấu</a>
                    </li>
                    <li class="header__navbar-item">
                        <a href="../BXH/index.php" class="header__navbar-link">Bảng xếp hạng</a>
                    </li>
                </ul>
            </nav>
            <div class="header-with-search">
                <div class="header__title">
                    HỆ THỐNG QUẢN LÝ GIẢI BÓNG ĐÁ NGOẠI HẠNG ANH
                </div>
                <div class="header__search">
                    <form action="" method="GET" class="header__search-form">
                        <input type="text" name="find" class="header__search-input" placeholder="Nhập tên đội cần tìm kiếm">
                        <button class="header__search-btn">
                            <i class="header__search-btn-icon fas fa-search"></i>
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <!-- Container -->
        <div class="container">
            <div class="grid__column-2">
                <ul class="body__navbar-list">
                    <?php
                        $sql = "SELECT * FROM vongdau ORDER BY tenvongdau";   
                        $roundList = excuteResult($sql);
                        foreach ($roundList as $round) {
                            if(isset($_GET['mavong'])) {
                                if($round['mavongdau'] == $_GET['mavong']) {
                                    echo'
                                    <li class="body__navbar-item body__navbar-item--active">
                                        <a href="./index.php?mavong='.$round['mavongdau'].'" class="body__navbar-link">Vòng '.$round['tenvongdau'].'</a>
                                    </li>
                                    ';  
                                }
                                else {
                                    echo'
                                    <li class="body__navbar-item">
                                        <a href="./index.php?mavong='.$round['mavongdau'].'" class="body__navbar-link">Vòng '.$round['tenvongdau'].'</a>
                                    </li>
                                    ';       
                                }
                            }
                            else {
                                echo'
                                <li class="body__navbar-item">
                                    <a href="./index.php?mavong='.$round['mavongdau'].'" class="body__navbar-link">Vòng '.$round['tenvongdau'].'</a>
                                </li>
                                ';   
                            }
                        }
                    ?>
                </ul>
            </div>
            <div class="grid__column-10">
                <div class="content">
                    <div class="body__title">KẾT QUẢ THI ĐẤU CÁC ĐỘI
                    <?php
                        if(isset($_GET['mavong'])) {
                            $mavongdau = $_GET['mavong'];
                            $sql = "SELECT * FROM vongdau";   
                            $roundList = excuteResult($sql);
                            foreach ($roundList as $round) {
                                if($round['mavongdau'] == $mavongdau) {
                                    $tenvongdau = $round['tenvongdau'];
                                }
                            }
                            echo 'VÒNG '.$tenvongdau.'';
                        }
                    ?>
                    </div>
                    <div class="home__list-team">
                        <table class="home__list-team-table">
                            <thead>
                            	<th>Tên vòng đấu</th>
                                <th>Tên trận đấu</th>
                                <th>Tên đội bóng 1</th>
                                <th>Tên đội bóng 2</th>
                                <th>Sân vận động</th>
                                <th>Thời gian thi đấu</th>
                                <th>Số bàn thắng đội 1</th>
                                <th>Số bàn thắng đội 2</th>
                            </thead>
                            <tbody>
                                <?php
                                    if(isset($_GET['mavong'])) {
                                        $sql = 'SELECT VD.tenvongdau, TD.matran, TD.tentran, CLB1.tendoi tendoi1, CLB2.tendoi tendoi2, KQ.SVD, KQ.thoigian, KQ.banthang1, KQ.banthang2 FROM kqtd KQ JOIN trandau TD ON KQ.matran = TD.matran JOIN vongdau VD ON TD.mavong = VD.mavongdau JOIN clb CLB1 ON KQ.madoi1 = CLB1.madoi JOIN clb CLB2 ON KQ.madoi2 = CLB2.madoi WHERE VD.mavongdau = '.$_GET['mavong'].' ORDER BY TD.tentran';

                                        $resultList = excuteResult($sql);
                                        foreach ($resultList as $result) {
                                            echo '
                                            <tr>
                                                <td>'.$result['tenvongdau'].'</td>
                                                <td>'.$result['tentran'].'</td>
                                                <td>'.$result['tendoi1'].'</td>
                                                <td>'.$result['tendoi2'].'</td>
                                                <td>'.$result['SVD'].'</td>
                                                <td>'.$result['thoigian'].'</td>
                                                <td>'.$result['banthang1'].'</td>
                                                <td>'.$result['banthang2'].'</td>
                                            </tr>
                                            ';
                                        }
                                    }
                                    else {
                                        /* Tìm kiếm */
                                        if(isset($_GET['find']) && $_GET['find'] != '') {
                                            $sql = 'SELECT VD.tenvongdau, TD.matran, TD.tentran, CLB1.tendoi tendoi1, CLB2.tendoi tendoi2, KQ.SVD, KQ.thoigian, KQ.banthang1, KQ.banthang2 FROM kqtd KQ JOIN trandau TD ON KQ.matran = TD.matran JOIN vongdau VD ON TD.mavong = VD.mavongdau JOIN clb CLB1 ON KQ.madoi1 = CLB1.madoi JOIN clb CLB2 ON KQ.madoi2 = CLB2.madoi WHERE (CLB1.tendoi LIKE "'.$_GET['find'].'%") OR (CLB2.tendoi LIKE "'.$_GET['find'].'%")';
                                        }
                                        /* Lấy dữ liệu */
                                        else {
                                            $sql = 'SELECT VD.tenvongdau, TD.matran, TD.tentran, CLB1.tendoi tendoi1, CLB2.tendoi tendoi2, KQ.SVD, KQ.thoigian, KQ.banthang1, KQ.banthang2 FROM kqtd KQ JOIN trandau TD ON KQ.matran = TD.matran JOIN vongdau VD ON TD.mavong = VD.mavongdau JOIN clb CLB1 ON KQ.madoi1 = CLB1.madoi JOIN clb CLB2 ON KQ.madoi2 = CLB2.madoi ORDER BY VD.tenvongdau, TD.tentran';

                                        }
                                        $resultList = excuteResult($sql);
                                        foreach ($resultList as $result) {
                                            echo '
                                            <tr>
                                                <td>'.$result['tenvongdau'].'</td>
                                                <td>'.$result['tentran'].'</td>
                                                <td>'.$result['tendoi1'].'</td>
                                                <td>'.$result['tendoi2'].'</td>
                                                <td>'.$result['SVD'].'</td>
                                                <td>'.$result['thoigian'].'</td>
                                                <td>'.$result['banthang1'].'</td>
                                                <td>'.$result['banthang2'].'</td>
                                            </tr>
                                            ';
                                        }
                                    }
                                ?>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer -->
        <div class="footer">
            <div class="footer__title">HỆ THỐNG QUẢN LÝ GIẢI ĐẤU BÓNG ĐÁ NGOẠI HẠNG ANH</div>
        </div>
    </div>
</body>
</html>