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
import model.CLB;

/**
 *
 * @author Dell
 */
public class ClLBController {

    public static List<CLB> findAll() {
        List<CLB> ClbList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        PreparedStatement prestatement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            String sql = "SELECT * FROM clb";
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            // Cập nhật số cầu thủ của đội bóng
            while (resultSet.next()) {
                sql = "SELECT clb.madoi, COUNT(thongtincauthu.macauthu) soct "
                        + "FROM thongtincauthu JOIN clb "
                        + "ON thongtincauthu.madoi = clb.madoi "
                        + "WHERE clb.madoi = ?";
                prestatement = connection.prepareCall(sql);
                prestatement.setInt(1, resultSet.getInt("madoi"));
                ResultSet preresultSet = prestatement.executeQuery();
                while (preresultSet.next()) {
                    sql = "UPDATE clb SET socauthu = ? WHERE madoi = ?";
                    prestatement = connection.prepareCall(sql);
                    prestatement.setInt(1, preresultSet.getInt("soct"));
                    prestatement.setInt(2, preresultSet.getInt("madoi"));
                    prestatement.execute();
                }      
            }
    
            // Lấy danh sách đội bóng
            sql = "SELECT * FROM clb ORDER BY tendoi";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                CLB clb;
                clb = new CLB(resultSet.getInt("madoi"),
                        resultSet.getInt("socauthu"),
                        resultSet.getString("tendoi"),
                        resultSet.getString("doitruong"),
                        resultSet.getString("HLV"));

                ClbList.add(clb);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClLBController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClLBController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (prestatement != null) {
                try {
                    prestatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClLBController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClLBController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        // Kết thúc
        return ClbList;
    }

    public static void insert(CLB clb) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            String sql = "INSERT INTO clb(tendoi, doitruong, HLV, socauthu) VALUES(?, ?, ?, ?)";
            statement = connection.prepareCall(sql);

            statement.setString(1, clb.getTendoi());
            statement.setString(2, clb.getDoitruong());
            statement.setString(3, clb.getHLV());
            statement.setInt(4, clb.getSocauthu());

            statement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ClLBController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClLBController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClLBController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        // Kết thúc
    }

    public static void update(CLB clb) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            String sql = "UPDATE clb SET tendoi = ?, doitruong = ?, HLV = ? WHERE madoi = ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, clb.getTendoi());
            statement.setString(2, clb.getDoitruong());
            statement.setString(3, clb.getHLV());
            statement.setInt(4, clb.getMadoi());
            statement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ClLBController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClLBController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClLBController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        // Kết thúc
    }

    public static void delete(int madoi) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            
           
            /* Xóa tất cả thông tin cầu thủ của đội */
            String sql = "DELETE FROM thongtincauthu WHERE madoi = ?";
            statement = connection.prepareCall(sql);
            statement.setInt(1, madoi);
            statement.execute();
            
            /* Xóa tất cả kết quả trận đấu của đội */
            sql = "DELETE FROM kqtd WHERE madoi1 = ? OR madoi2 = ?";
            statement = connection.prepareCall(sql);
            statement.setInt(1, madoi);
            statement.setInt(2, madoi);
            statement.execute();
            
            sql = "DELETE FROM bxh WHERE madoi = ?";
            statement = connection.prepareCall(sql);
            statement.setInt(1, madoi);
            statement.execute();
            
            
            /* Xóa đội bóng */
            sql = "DELETE FROM clb WHERE madoi = ?";
            statement = connection.prepareCall(sql);
            statement.setInt(1, madoi);
            statement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ClLBController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClLBController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClLBController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        // Kết thúc
    }

    public static List<CLB> findByNameCLB(String tendoi) {
        List<CLB> ClbList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            String sql = "SELECT * FROM clb WHERE tendoi LIKE ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, tendoi + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CLB clb;
                clb = new CLB(resultSet.getInt("madoi"),
                        resultSet.getInt("socauthu"),
                        resultSet.getString("tendoi"),
                        resultSet.getString("doitruong"),
                        resultSet.getString("HLV"));
                ClbList.add(clb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClLBController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClLBController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClLBController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        // Kết thúc
        return ClbList;
    }
}
