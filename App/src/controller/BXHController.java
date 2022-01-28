/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BXH;

/**
 *
 * @author Dell
 */
public class BXHController {

    public static List<BXH> findAll() {
        List<BXH> BXHList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        PreparedStatement prestatement = null;
        int banthang = 0, banthang1 = 0, banthang2 = 0,banthua = 0, banthua1 = 0,banthua2 = 0,
            sotran = 0, sotranthang = 0, sotranthua = 0, sotranhoa = 0, diem = 0, hieuso = 0;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            String sql = "SELECT vongdau.mavongdau, vongdau.tenvongdau, clb.madoi, clb.tendoi "
                        + "FROM clb JOIN kqtd "
                        + "ON clb.madoi = kqtd.madoi1 OR clb.madoi = kqtd.madoi2 "
                        + "JOIN trandau "
                        + "ON trandau.matran = kqtd.matran "
                        + "JOIN vongdau "
                        + "ON vongdau.mavongdau = trandau.mavong "
                        + "ORDER BY vongdau.tenvongdau";
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            
            while (resultSet.next()) {
                // Cập nhật số sận trận của đội
                sql = "SELECT COUNT(kqtd.matran) sotran " +
                        "FROM kqtd JOIN trandau " +
                        "ON kqtd.matran = trandau.matran " +
                        "JOIN vongdau " +
                        "ON trandau.mavong = vongdau.mavongdau " +
                        "WHERE (kqtd.madoi1 = ? OR kqtd.madoi2 = ?) " +
                        "AND (vongdau.tenvongdau <= ?)";
                prestatement = connection.prepareCall(sql);
                prestatement.setInt(1, resultSet.getInt("madoi"));
                prestatement.setInt(2, resultSet.getInt("madoi"));
                prestatement.setInt(3, resultSet.getInt("tenvongdau"));
                ResultSet preresultSet = prestatement.executeQuery();
                while (preresultSet.next()) {
                    sotran = preresultSet.getInt("sotran");
                }
                
                // Cập nhật số trận thắng của đội
                sql = "SELECT COUNT(kqtd.matran) sotranthang " +
                        "FROM kqtd JOIN trandau " +
                        "ON kqtd.matran = trandau.matran " +
                        "JOIN vongdau " +
                        "ON trandau.mavong = vongdau.mavongdau " +
                        "WHERE ((kqtd.madoi1 = ? AND kqtd.banthang1 > kqtd.banthang2) " +
                        "OR (kqtd.madoi2 = ? AND kqtd.banthang2 > kqtd.banthang1))" +
                        "AND (vongdau.tenvongdau <= ?)";
                prestatement = connection.prepareCall(sql);
                prestatement.setInt(1, resultSet.getInt("madoi"));
                prestatement.setInt(2, resultSet.getInt("madoi"));
                prestatement.setInt(3, resultSet.getInt("tenvongdau"));
                preresultSet = prestatement.executeQuery();
                while (preresultSet.next()) {
                    sotranthang = preresultSet.getInt("sotranthang");
                }
                
                // Cập nhật số trận thua của đội
                sql = "SELECT COUNT(kqtd.matran) sotranthua " +
                        "FROM kqtd JOIN trandau " +
                        "ON kqtd.matran = trandau.matran " +
                        "JOIN vongdau " +
                        "ON trandau.mavong = vongdau.mavongdau " +
                        "WHERE ((kqtd.madoi1 = ? AND kqtd.banthang1 < kqtd.banthang2) " +
                        "OR (kqtd.madoi2 = ? AND kqtd.banthang2 < kqtd.banthang1))" +
                        "AND (vongdau.tenvongdau <= ?)";
                prestatement = connection.prepareCall(sql);
                prestatement.setInt(1, resultSet.getInt("madoi"));
                prestatement.setInt(2, resultSet.getInt("madoi"));
                prestatement.setInt(3, resultSet.getInt("tenvongdau"));
                preresultSet = prestatement.executeQuery();
                while (preresultSet.next()) {
                    sotranthua = preresultSet.getInt("sotranthua");
                }
                
                // Cập nhật số trận hòa của đội
                sql = "SELECT COUNT(kqtd.matran) sotranhoa " +
                        "FROM kqtd JOIN trandau " +
                        "ON kqtd.matran = trandau.matran " +
                        "JOIN vongdau " +
                        "ON trandau.mavong = vongdau.mavongdau " +
                        "WHERE ((kqtd.madoi1 = ? AND kqtd.banthang1 = kqtd.banthang2) " +
                        "OR (kqtd.madoi2 = ? AND kqtd.banthang2 = kqtd.banthang1))" +
                        "AND (vongdau.tenvongdau <= ?)";
                prestatement = connection.prepareCall(sql);
                prestatement.setInt(1, resultSet.getInt("madoi"));
                prestatement.setInt(2, resultSet.getInt("madoi"));
                prestatement.setInt(3, resultSet.getInt("tenvongdau"));
                preresultSet = prestatement.executeQuery();
                while (preresultSet.next()) {
                    sotranhoa = preresultSet.getInt("sotranhoa");
                }
                
                // Cập nhật số bàn thắng của đội
                //Đếm số bàn thắng khi ở đội 1
                sql = "SELECT SUM(kqtd.banthang1) sbthang " +
                    "FROM kqtd JOIN trandau " +
                    "ON kqtd.matran = trandau.matran " +
                    "JOIN vongdau " +
                    "ON trandau.mavong = vongdau.mavongdau " +
                    "WHERE (kqtd.madoi1 = ?) AND (vongdau.tenvongdau <= ?)";
                prestatement = connection.prepareCall(sql);
                prestatement.setInt(1, resultSet.getInt("madoi"));
                prestatement.setInt(2, resultSet.getInt("tenvongdau"));
                preresultSet = prestatement.executeQuery();
                while (preresultSet.next()) {
                    banthang1 = preresultSet.getInt("sbthang");
                }
                // Đếm số bàn thắng khi ở đội 2
                sql = "SELECT SUM(kqtd.banthang2) sbthang " +
                    "FROM kqtd JOIN trandau " +
                    "ON kqtd.matran = trandau.matran " +
                    "JOIN vongdau " +
                    "ON trandau.mavong = vongdau.mavongdau " +
                    "WHERE (kqtd.madoi2 = ?) AND (vongdau.tenvongdau <= ?)";
                prestatement = connection.prepareCall(sql);
                prestatement.setInt(1, resultSet.getInt("madoi"));
                prestatement.setInt(2, resultSet.getInt("tenvongdau"));
                preresultSet = prestatement.executeQuery();
                while (preresultSet.next()) {
                    banthang2 = preresultSet.getInt("sbthang");
                }
                banthang = banthang1 + banthang2;
                
                // Cập nhật số bàn thua của đội
                //Đếm số bàn thua khi ở đội 1
                sql = "SELECT SUM(kqtd.banthang2) sbthua " +
                    "FROM kqtd JOIN trandau " +
                    "ON kqtd.matran = trandau.matran " +
                    "JOIN vongdau " +
                    "ON trandau.mavong = vongdau.mavongdau " +
                    "WHERE (kqtd.madoi1 = ?) AND (vongdau.tenvongdau <= ?)";
                prestatement = connection.prepareCall(sql);
                prestatement.setInt(1, resultSet.getInt("madoi"));
                prestatement.setInt(2, resultSet.getInt("tenvongdau"));
                preresultSet = prestatement.executeQuery();
                while (preresultSet.next()) {
                    banthua1 = preresultSet.getInt("sbthua");
                }
                // Đếm số bàn thua khi ở đội 2
                sql = "SELECT SUM(kqtd.banthang1) sbthua " +
                    "FROM kqtd JOIN trandau " +
                    "ON kqtd.matran = trandau.matran " +
                    "JOIN vongdau " +
                    "ON trandau.mavong = vongdau.mavongdau " +
                    "WHERE (kqtd.madoi2 = ?) AND (vongdau.tenvongdau <= ?)";
                prestatement = connection.prepareCall(sql);
                prestatement.setInt(1, resultSet.getInt("madoi"));
                prestatement.setInt(2, resultSet.getInt("tenvongdau"));
                preresultSet = prestatement.executeQuery();
                while (preresultSet.next()) {
                    banthua2 = preresultSet.getInt("sbthua");
                }
                banthua = banthua1 + banthua2;
                
                // Tính hiệu số của đội
                hieuso = banthang - banthua;
                
                // Tính điểm của đội
                diem = sotranthang*3 + sotranhoa*1;
                
                // Kiểm tra xem đội bóng có trong BXH không?
                int check = 0;
                sql = "SELECT * FROM bxh JOIN clb ON bxh.madoi = clb.madoi";
                prestatement = connection.prepareCall(sql);
                preresultSet = prestatement.executeQuery();
                while (preresultSet.next()) {
                    if(preresultSet.getInt("madoi") == resultSet.getInt("madoi") && preresultSet.getInt("mavong") == resultSet.getInt("mavongdau")) {
                        check++;
                    }
                }
                
                // Nếu chưa có CLB trong BXH thì thêm CLB vào BXH
                if(check == 0) {
                    sql = "INSERT INTO bxh(vitri, mavong, madoi, sotran, stthang, stthua, sthoa, sobanthang, sobanthua, hieuso, diem) "
                            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    prestatement = connection.prepareCall(sql);
                    prestatement.setInt(1, 1);
                    prestatement.setInt(2, resultSet.getInt("mavongdau"));
                    prestatement.setInt(3, resultSet.getInt("madoi"));
                    prestatement.setInt(4, sotran);
                    prestatement.setInt(5, sotranthang);
                    prestatement.setInt(6, sotranthua);
                    prestatement.setInt(7, sotranhoa);
                    prestatement.setInt(8, banthang);
                    prestatement.setInt(9, banthua);
                    prestatement.setInt(10, hieuso);
                    prestatement.setInt(11, diem);
                    
                    prestatement.execute();
                }
            }
            
            // Cập nhật vị trí mỗi CLB trong BXh
            sql = "SELECT * FROM vongdau";
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                int rank = 0;
                sql = "SELECT * FROM bxh WHERE mavong = ? ORDER BY diem DESC, hieuso DESC, sobanthang DESC";
                prestatement = connection.prepareCall(sql);
                prestatement.setInt(1, resultSet.getInt("mavongdau"));
                ResultSet preresultSet = prestatement.executeQuery();
                while(preresultSet.next()) {
                    sql = "UPDATE bxh SET vitri = ? WHERE (madoi = ?) AND (mavong = ?)";
                    prestatement = connection.prepareCall(sql);
                    prestatement.setInt(1, ++rank);
                    prestatement.setInt(2, preresultSet.getInt("madoi"));
                    prestatement.setInt(3, preresultSet.getInt("mavong"));
                    prestatement.execute();         
                }
            }
            
            // Xuất dữ liệu BXH theo vòng đấu
            sql = "SELECT * FROM vongdau ORDER BY tenvongdau";
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                sql = "SELECT * FROM bxh JOIN vongdau "
                        + "ON bxh.mavong = vongdau.mavongdau "
                        + "JOIN clb "
                        + "ON bxh.madoi = clb.madoi "
                        + "WHERE bxh.mavong = ? "
                        + "ORDER BY bxh.vitri";
                prestatement = connection.prepareCall(sql);
                prestatement.setInt(1, resultSet.getInt("mavongdau"));
                ResultSet preresultSet = prestatement.executeQuery();
                while(preresultSet.next()) {
                    BXH bxh;
                    bxh = new BXH(preresultSet.getInt("vitri"), 
                            preresultSet.getInt("sotran"), 
                            preresultSet.getInt("stthang"), 
                            preresultSet.getInt("stthua"), 
                            preresultSet.getInt("sthoa"), 
                            preresultSet.getInt("sobanthang"), 
                            preresultSet.getInt("sobanthua"), 
                            preresultSet.getInt("hieuso"), 
                            preresultSet.getInt("diem"), 
                            preresultSet.getString("tenvongdau"), 
                            preresultSet.getString("tendoi"));
                    
                    BXHList.add(bxh);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(BXHController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BXHController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (prestatement != null) {
                try {
                    prestatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BXHController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BXHController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        // Kết thúc
        return BXHList;
    }
    
    public static void Delete() {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            String sql = "DELETE FROM bxh";
            statement = connection.prepareCall(sql);
            statement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(KQTDcontroller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KQTDcontroller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KQTDcontroller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        // Kết thúc
    }
    
    public static List<BXH> findByNameCLB(String tenCLB) {
        List<BXH> BXHList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        PreparedStatement prestatement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            String sql = "SELECT * FROM vongdau ORDER BY tenvongdau";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                sql = "SELECT * FROM bxh JOIN vongdau "
                        + "ON bxh.mavong = vongdau.mavongdau "
                        + "JOIN clb "
                        + "ON bxh.madoi = clb.madoi "
                        + "WHERE clb.tendoi LIKE ? "
                        + "AND bxh.mavong = ? " 
                        + "ORDER BY clb.tendoi";
                prestatement = connection.prepareCall(sql);
                prestatement.setString(1, tenCLB + "%");
                prestatement.setInt(2, resultSet.getInt("mavongdau"));
                ResultSet preresultSet = prestatement.executeQuery();
                while(preresultSet.next()) {
                    BXH bxh;
                    bxh = new BXH(preresultSet.getInt("vitri"), 
                            preresultSet.getInt("sotran"), 
                            preresultSet.getInt("stthang"), 
                            preresultSet.getInt("stthua"), 
                            preresultSet.getInt("sthoa"), 
                            preresultSet.getInt("sobanthang"), 
                            preresultSet.getInt("sobanthua"), 
                            preresultSet.getInt("hieuso"), 
                            preresultSet.getInt("diem"), 
                            preresultSet.getString("tenvongdau"), 
                            preresultSet.getString("tendoi"));
                    
                    BXHList.add(bxh);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CauThuConTroller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CauThuConTroller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CauThuConTroller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        // Kết thúc
        return BXHList;
    }
    
    public static List<BXH> findByVD(int tenVD) {
        List<BXH> BXHList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        PreparedStatement prestatement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
           
                String sql = "SELECT * FROM bxh JOIN vongdau "
                        + "ON bxh.mavong = vongdau.mavongdau "
                        + "JOIN clb "
                        + "ON bxh.madoi = clb.madoi "
                        + "WHERE bxh.mavong = ? " 
                        + "ORDER BY bxh.vitri";
                prestatement = connection.prepareCall(sql);
                prestatement.setInt(1, tenVD);
                ResultSet preresultSet = prestatement.executeQuery();
                while(preresultSet.next()) {
                    BXH bxh;
                    bxh = new BXH(preresultSet.getInt("vitri"), 
                            preresultSet.getInt("sotran"), 
                            preresultSet.getInt("stthang"), 
                            preresultSet.getInt("stthua"), 
                            preresultSet.getInt("sthoa"), 
                            preresultSet.getInt("sobanthang"), 
                            preresultSet.getInt("sobanthua"), 
                            preresultSet.getInt("hieuso"), 
                            preresultSet.getInt("diem"), 
                            preresultSet.getString("tenvongdau"), 
                            preresultSet.getString("tendoi"));
                    
                    BXHList.add(bxh);
                }
        } catch (SQLException ex) {
            Logger.getLogger(CauThuConTroller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CauThuConTroller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CauThuConTroller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        // Kết thúc
        return BXHList;
    }
    
}
