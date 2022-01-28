<?php
    require_once('./sp_connect/dbhelp.php');
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Page</title>
    <!-- Link -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="./asset/css/base.css">
    <link rel="stylesheet" href="./asset/css/main.css">
    <link rel="stylesheet" href="./asset/font/fontawesome-free-5.15.4-web/css/all.min.css">

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
                        <a href="./index.php" class="header__navbar-link">
                            <img src="./asset/img/Premier-League-Logo.jpg" alt="Premier League Logo" class="header__navbar-img">
                        </a>
                    </li>
                    <li class="header__navbar-item header__navbar-item--bold">
                        GIẢI BÓNG ĐÁ NGOẠI HẠNG ANH
                    </li>
                </ul>
                <ul class="header__navbar-list">
                    <li class="header__navbar-item header__navbar-item--active">
                        <a href="./index.php" class="header__navbar-link">Đội bóng</a>
                    </li>
                    <li class="header__navbar-item">
                        <a href="./cauthu/index.php" class="header__navbar-link">Cầu thủ</a>
                    </li>
                    <li class="header__navbar-item">
                        <a href="./KQthidau/index.php" class="header__navbar-link">Kết quả thi đấu</a>
                    </li>
                    <li class="header__navbar-item">
                        <a href="./BXH/index.php" class="header__navbar-link">Bảng xếp hạng</a>
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
            <div class="grid">
                <div class="content">
                    <div class="body__title">DANH SÁCH CÁC ĐỘI BÓNG</div>
                    <div class="home__list-team">
                        <table class="home__list-team-table">
                            <thead>
                                <th>STT</th>
                                <th>Tên CLB</th>
                                <th>Đội trưởng</th>
                                <th>Huấn luyện viên</th>
                                <th>Số thành viên</th>
                            </thead>
                            <tbody>
                                <?php
                                    /* Tìm kiếm */
                                    if(isset($_GET['find']) && $_GET['find'] != '') {
                                        $sql = 'SELECT * FROM clb WHERE tendoi LIKE "'.$_GET['find'].'%"';
                                    }
                                    /* Lấy dữ liệu */
                                    else {
                                        $sql = 'SELECT * FROM clb ORDER BY clb.tendoi';
                                    }
                                    $teamList = excuteResult($sql);
                                    $index = 0;
                                    foreach ($teamList as $team) {
                                        echo '
                                        <tr>
                                            <td>'.(++$index).'</td>
                                            <td>'.$team['tendoi'].'</a>
                                            </td>
                                            <td>'.$team['doitruong'].'</td>
                                            <td>'.$team['HLV'].'</td>
                                            <td>'.$team['socauthu'].'</td>
                                        </tr>
                                        ';
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