<?php
    require_once('../sp_connect/dbhelp.php');
?>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Bảng xếp hạng</title>
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
                    <li class="header__navbar-item">
                        <a href="../KQthidau/index.php" class="header__navbar-link">Kết quả thi đấu</a>
                    </li>
                    <li class="header__navbar-item header__navbar-item--active">
                        <a href="./index.php" class="header__navbar-link">Bảng xếp hạng</a>
                    </li>
                </ul>
            </nav>
            <div class="header-with-search">
                <div class="header__title">
                    HỆ THỐNG QUẢN LÝ GIẢI BÓNG ĐÁ NGOẠI HẠNG ANH
                </div>
                <div class="header__search">
                    <form action="" method="GET" class="header__search-form">
                        <input type="text" name="find" class="header__search-input" placeholder="Nhập đội bóng cần tìm kiếm">
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
                    <div class="body__title">BẢNG XẾP HẠNG
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
                                <th>Tên vòng</th>
                            	<th>Vị trí</th>
                                <th>Tên CLB</th>
                                <th>Số trận</th>
                                <th>Số trận thắng</th>
                                <th>Số trận thua</th>
                                <th>Số trận hòa</th>
                                <th>Số bàn thắng</th>
                                <th>Số bàn thua</th>
                                <th>Hiệu số</th>
                                <th>Điểm</th>
                            </thead>
                            <tbody>
                                <?php       
                                    if(isset($_GET['mavong'])) {
                                        $sql = "SELECT * 
                                            FROM bxh JOIN vongdau
                                            ON bxh.mavong = vongdau.mavongdau
                                            JOIN clb
                                            ON bxh.madoi = clb.madoi
                                            WHERE bxh.mavong = ".$_GET['mavong']."
                                            ORDER BY bxh.vitri"; 
                                        $rankList = excuteResult($sql);
                                        foreach($rankList as $rank) {
                                            echo '
                                            <tr>
                                                <td>'.$rank['tenvongdau'].'</td>
                                                <td>'.$rank['vitri'].'</td>
                                                <td>'.$rank['tendoi'].'</td>
                                                <td>'.$rank['sotran'].'</td>
                                                <td>'.$rank['stthang'].'</td>
                                                <td>'.$rank['stthua'].'</td>
                                                <td>'.$rank['sthoa'].'</td>
                                                <td>'.$rank['sobanthang'].'</td>
                                                <td>'.$rank['sobanthua'].'</td>
                                                <td>'.$rank['hieuso'].'</td>
                                                <td>'.$rank['diem'].'</td>
                                            </tr>
                                            ';
                                        }
                                    }      
                                    else {  
                                        $sql = "SELECT * FROM vongdau";   
                                        $roundList = excuteResult($sql);
                                        foreach ($roundList as $round) {
                                            /* Tìm kiếm */
                                            if(isset($_GET['find']) && $_GET['find'] != '') {
                                                $sql = 'SELECT * 
                                                        FROM bxh JOIN vongdau
                                                        ON bxh.mavong = vongdau.mavongdau
                                                        JOIN clb
                                                        ON bxh.madoi = clb.madoi
                                                        WHERE clb.tendoi LIKE "'.$_GET['find'].'%" 
                                                        AND bxh.mavong = '.$round['mavongdau'].'
                                                        ORDER BY clb.tendoi';
                                            }
                                            else {
                                                $sql = "SELECT * 
                                                        FROM bxh JOIN vongdau
                                                        ON bxh.mavong = vongdau.mavongdau
                                                        JOIN clb
                                                        ON bxh.madoi = clb.madoi
                                                        WHERE bxh.mavong = ".$round['mavongdau']."
                                                        ORDER BY bxh.vitri";
                                            }
                                            $rankList = excuteResult($sql);
                                            foreach($rankList as $rank) {
                                                echo '
                                                <tr>
                                                    <td>'.$rank['tenvongdau'].'</td>
                                                    <td>'.$rank['vitri'].'</td>
                                                    <td>'.$rank['tendoi'].'</td>
                                                    <td>'.$rank['sotran'].'</td>
                                                    <td>'.$rank['stthang'].'</td>
                                                    <td>'.$rank['stthua'].'</td>
                                                    <td>'.$rank['sthoa'].'</td>
                                                    <td>'.$rank['sobanthang'].'</td>
                                                    <td>'.$rank['sobanthua'].'</td>
                                                    <td>'.$rank['hieuso'].'</td>
                                                    <td>'.$rank['diem'].'</td>
                                                </tr>
                                                ';
                                            }
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