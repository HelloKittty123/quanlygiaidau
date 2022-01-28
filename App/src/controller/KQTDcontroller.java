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
import model.KQTD;

/**
 *
 * @author Dell
 */
public class KQTDcontroller {

    public static List<KQTD> findAll() {
        List<KQTD> KQTDList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        PreparedStatement prestatement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            String sql = "SELECT VD.mavongdau, "
                    + "VD.tenvongdau, "
                    + "TD.matran, "
                    + "TD.tentran, "
                    + "CLB1.tendoi tendoi1, "
                    + "CLB1.madoi madoi1, "
                    + "CLB2.tendoi tendoi2, "
                    + "CLB1.madoi madoi2, "
                    + "KQ.SVD, KQ.thoigian, "
                    + "KQ.banthang1, KQ.banthang2 "
                    + "FROM kqtd KQ JOIN trandau TD "
                    + "ON KQ.matran = TD.matran "
                    + "JOIN vongdau VD "
                    + "ON TD.mavong = VD.mavongdau "
                    + "JOIN clb CLB1 "
                    + "ON KQ.madoi1 = CLB1.madoi "
                    + "JOIN clb CLB2 ON KQ.madoi2 = CLB2.madoi "
                    + "ORDER BY VD.tenvongdau, TD.tentran";
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                KQTD kqtd;
                kqtd = new KQTD(resultSet.getInt("mavongdau"),
                        resultSet.getInt("matran"),
                        resultSet.getInt("madoi1"),
                        resultSet.getInt("madoi2"),
                        resultSet.getInt("banthang1"),
                        resultSet.getInt("banthang2"),
                        resultSet.getString("tenvongdau"),
                        resultSet.getString("tentran"),
                        resultSet.getString("tendoi1"),
                        resultSet.getString("tendoi2"),
                        resultSet.getString("SVD"),
                        resultSet.getDate("thoigian"));

                KQTDList.add(kqtd);
            }

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
        return KQTDList;
    }

    public static List<KQTD> findAllTD() {
        List<KQTD> KQTDList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        PreparedStatement prestatement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            String sql = "SELECT matran, tentran FROM trandau";
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                KQTD kqtd;
                kqtd = new KQTD(resultSet.getInt("matran"),
                        resultSet.getString("tentran"));
                KQTDList.add(kqtd);
            }

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
        return KQTDList;
    }
    
    public static List<KQTD> findAllVD() {
        List<KQTD> KQTDList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        PreparedStatement prestatement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            String sql = "SELECT mavongdau, tenvongdau FROM vongdau";
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                KQTD kqtd;
                kqtd = new KQTD(resultSet.getString("tenvongdau"));
                KQTDList.add(kqtd);
            }

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
        return KQTDList;
    }

    public static void insert(KQTD kqtd) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            String sql = "INSERT INTO kqtd(matran, madoi1, madoi2, SVD, thoigian, banthang1, banthang2) VALUES(?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareCall(sql);

            statement.setInt(1, kqtd.getMatran());
            statement.setInt(2, kqtd.getMadoi1());
            statement.setInt(3, kqtd.getMadoi2());
            statement.setString(4, kqtd.getSVD());
            statement.setDate(5, new java.sql.Date(kqtd.getThoigianthidau().getTime()));
            statement.setInt(6, kqtd.getSobanthang1());
            statement.setInt(7, kqtd.getSobanthang2());
            
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

    public static void update(KQTD kqtd) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            String sql = "UPDATE kqtd SET matran = ?, "
                    + "madoi1 = ?, "
                    + "madoi2 = ?, "
                    + "SVD = ?, "
                    + "thoigian = ?, "
                    + "banthang1 = ?, "
                    + "banthang2 = ? "
                    + "WHERE matran = ?";
            statement = connection.prepareCall(sql);
            statement.setInt(1, kqtd.getMatran());
            statement.setInt(2, kqtd.getMadoi1());
            statement.setInt(3, kqtd.getMadoi2());
            statement.setString(4, kqtd.getSVD());
            statement.setDate(5, new java.sql.Date(kqtd.getThoigianthidau().getTime()));
            statement.setInt(6, kqtd.getSobanthang1());
            statement.setInt(7, kqtd.getSobanthang2());
            statement.setInt(8, kqtd.getMatrancu());

            statement.execute();

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
    }

    public static void delete(int matran) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            String sql = "DELETE FROM kqtd WHERE matran = ?";
            statement = connection.prepareCall(sql);
            statement.setInt(1, matran);

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

    public static List<KQTD> findByNamevongKQTD(int tenvong) {
        List<KQTD> KQTDList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            String sql = "SELECT VD.mavongdau, "
                    + "VD.tenvongdau, "
                    + "TD.matran, "
                    + "TD.tentran, "
                    + "CLB1.tendoi tendoi1, "
                    + "CLB1.madoi madoi1, "
                    + "CLB2.tendoi tendoi2, "
                    + "CLB1.madoi madoi2, "
                    + "KQ.SVD, KQ.thoigian, "
                    + "KQ.banthang1, KQ.banthang2 "
                    + "FROM kqtd KQ JOIN trandau TD "
                    + "ON KQ.matran = TD.matran "
                    + "JOIN vongdau VD "
                    + "ON TD.mavong = VD.mavongdau "
                    + "JOIN clb CLB1 "
                    + "ON KQ.madoi1 = CLB1.madoi "
                    + "JOIN clb CLB2 ON KQ.madoi2 = CLB2.madoi "
                    + "WHERE VD.tenvongdau = ? "
                    + "ORDER BY TD.tentran";
            statement = connection.prepareCall(sql);
            statement.setInt(1, tenvong);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                KQTD kqtd;
                kqtd = new KQTD(resultSet.getInt("mavongdau"),
                        resultSet.getInt("matran"),
                        resultSet.getInt("madoi1"),
                        resultSet.getInt("madoi2"),
                        resultSet.getInt("banthang1"),
                        resultSet.getInt("banthang2"),
                        resultSet.getString("tenvongdau"),
                        resultSet.getString("tentran"),
                        resultSet.getString("tendoi1"),
                        resultSet.getString("tendoi2"),
                        resultSet.getString("SVD"),
                        resultSet.getDate("thoigian"));
                KQTDList.add(kqtd);
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
        return KQTDList;
    }
    
    public static List<KQTD> findByTenDoi(String tendoi) {
        List<KQTD> KQTDList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            String sql = "SELECT VD.mavongdau, "
                    + "VD.tenvongdau, "
                    + "TD.matran, "
                    + "TD.tentran, "
                    + "CLB1.tendoi tendoi1, "
                    + "CLB1.madoi madoi1, "
                    + "CLB2.tendoi tendoi2, "
                    + "CLB2.madoi madoi2, "
                    + "KQ.SVD, KQ.thoigian, "
                    + "KQ.banthang1, KQ.banthang2 "
                    + "FROM kqtd KQ JOIN trandau TD "
                    + "ON KQ.matran = TD.matran "
                    + "JOIN vongdau VD "
                    + "ON TD.mavong = VD.mavongdau "
                    + "JOIN clb CLB1 "
                    + "ON KQ.madoi1 = CLB1.madoi "
                    + "JOIN clb CLB2 ON KQ.madoi2 = CLB2.madoi "
                    + "WHERE CLB1.tendoi LIKE ? OR CLB2.tendoi LIKE ? "
                    + "ORDER BY VD.tenvongdau, TD.tentran";
            statement = connection.prepareCall(sql);
            statement.setString(1, tendoi + "%");
            statement.setString(2, tendoi + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                KQTD kqtd;
                kqtd = new KQTD(resultSet.getInt("mavongdau"),
                        resultSet.getInt("matran"),
                        resultSet.getInt("madoi1"),
                        resultSet.getInt("madoi2"),
                        resultSet.getInt("banthang1"),
                        resultSet.getInt("banthang2"),
                        resultSet.getString("tenvongdau"),
                        resultSet.getString("tentran"),
                        resultSet.getString("tendoi1"),
                        resultSet.getString("tendoi2"),
                        resultSet.getString("SVD"),
                        resultSet.getDate("thoigian"));
                KQTDList.add(kqtd);
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
        return KQTDList;
    }
    
}
