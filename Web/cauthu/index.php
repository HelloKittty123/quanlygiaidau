<?php
    require_once('../sp_connect/dbhelp.php');
?>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Cầu thủ</title>
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
                    <li class="header__navbar-item header__navbar-item--active">
                        <a href="./index.php" class="header__navbar-link">Cầu thủ</a>
                    </li>
                    <li class="header__navbar-item">
                        <a href="../KQthidau/index.php" class="header__navbar-link">Kết quả thi đấu</a>
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
                        <input type="text" name="find" class="header__search-input" placeholder="Nhập cầu thủ cần tìm kiếm">
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
                        $sql = "SELECT * FROM clb ORDER BY tendoi";   
                        $CLBList = excuteResult($sql);
                        foreach ($CLBList as $CLB) {
                            if(isset($_GET['madoi'])) {
                                if($CLB['madoi'] == $_GET['madoi']) {
                                    echo'
                                    <li class="body__navbar-item body__navbar-item--active">
                                        <a href="./index.php?madoi='.$CLB['madoi'].'" class="body__navbar-link">'.$CLB['tendoi'].'</a>
                                    </li>
                                    ';  
                                }
                                else {
                                    echo'
                                    <li class="body__navbar-item">
                                        <a href="./index.php?madoi='.$CLB['madoi'].'" class="body__navbar-link">'.$CLB['tendoi'].'</a>
                                    </li>
                                    ';       
                                }
                            }
                            else {
                                echo'
                                <li class="body__navbar-item">
                                    <a href="./index.php?madoi='.$CLB['madoi'].'" class="body__navbar-link">'.$CLB['tendoi'].'</a>
                                </li>
                                ';   
                            }
                        }
                    ?>
                </ul>
            </div>
            <div class="grid__column-10">
                <div class="content">
                    <div class="body__title">DANH SÁCH CÁC CẦU THỦ
                    <?php
                        if(isset($_GET['madoi'])) {
                            $madoi = $_GET['madoi'];
                            $sql = "SELECT * FROM clb";   
                            $CLBList = excuteResult($sql);
                            foreach ($CLBList as $CLB) {
                                if($CLB['madoi'] == $madoi) {
                                    $tendoi = $CLB['tendoi'];
                                }
                            }
                            echo ''.$tendoi.'';
                        }
                    ?>
                    </div>
                    <div class="home__list-team">
                        <table class="home__list-team-table">
                            <thead>
                            	<th>STT</th>
                                <th>Tên CLB</th>
                                <th>Tên cầu thủ</th>
                                <th>Ngày/ tháng / năm sinh</th>
                                <th>Số áo</th>
                                <th>Vị trí</th>
                            </thead>
                            <tbody>
                                <?php
                                    if(isset($_GET['madoi'])) {
                                        $sql = 'SELECT thongtincauthu.macauthu, thongtincauthu.tencauthu, thongtincauthu.ngaysinh, thongtincauthu.soao, thongtincauthu.vitri, clb.tendoi clb_tendoi FROM thongtincauthu JOIN clb on thongtincauthu.madoi = clb.madoi WHERE thongtincauthu.madoi = '.$_GET['madoi'].'';

                                        $memList = excuteResult($sql);
                                        $index = 0;
                                        foreach ($memList as $mem) {
                                            echo '
                                            <tr>
                                                <td>'.(++$index).'</td>
                                                <td>'.$mem['clb_tendoi'].'</td>
                                                <td>'.$mem['tencauthu'].'</td>
                                                <td>'.$mem['ngaysinh'].'</td>
                                                <td>'.$mem['soao'].'</td>
                                                <td>'.$mem['vitri'].'</td>
                                            </tr>
                                            ';
                                        }
                                    }
                                    else {
                                        /* Tìm kiếm */
                                        if(isset($_GET['find']) && $_GET['find'] != '') {
                                            $sql = 'SELECT thongtincauthu.macauthu, thongtincauthu.tencauthu, thongtincauthu.ngaysinh, thongtincauthu.soao, thongtincauthu.vitri, clb.tendoi clb_tendoi FROM thongtincauthu JOIN clb on thongtincauthu.madoi = clb.madoi WHERE tencauthu LIKE "'.$_GET['find'].'%"';
                                        }
                                        /* Lấy dữ liệu */
                                        else {
                                            $sql = 'SELECT thongtincauthu.macauthu, thongtincauthu.tencauthu, thongtincauthu.ngaysinh, thongtincauthu.soao, thongtincauthu.vitri, clb.tendoi clb_tendoi FROM thongtincauthu JOIN clb on thongtincauthu.madoi = clb.madoi ORDER BY clb_tendoi';
                                        }
                                        $memList = excuteResult($sql);
                                        $index = 0;
                                        foreach ($memList as $mem) {
                                            echo '
                                            <tr>
                                                <td>'.(++$index).'</td>
                                                <td>'.$mem['clb_tendoi'].'</td>
                                                <td>'.$mem['tencauthu'].'</td>
                                                <td>'.$mem['ngaysinh'].'</td>
                                                <td>'.$mem['soao'].'</td>
                                                <td>'.$mem['vitri'].'</td>
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