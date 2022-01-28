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
import model.CauThu;

/**
 *
 * @author Dell
 */
public class CauThuConTroller {
    public static List<CauThu> findAll() {
        List<CauThu> cauthuList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        PreparedStatement prestatement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
                String sql = "SELECT thongtincauthu.madoi, "
                    + "thongtincauthu.macauthu, "
                    + "thongtincauthu.tencauthu, "
                    + "thongtincauthu.ngaysinh, "
                    + "thongtincauthu.soao, "
                    + "thongtincauthu.vitri, "
                    + "clb.tendoi clb_tendoi "
                    + "FROM thongtincauthu LEFT JOIN clb "
                    + "ON thongtincauthu.madoi = clb.madoi "
                    + "ORDER BY clb_tendoi";
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()) {
                CauThu cauThu;
                cauThu = new CauThu(resultSet.getInt("madoi"),
                        resultSet.getInt("macauthu"),
                        resultSet.getInt("soao"),
                        resultSet.getDate("ngaysinh"),
                        resultSet.getString("clb_tendoi"),
                        resultSet.getString("tencauthu"),
                        resultSet.getString("vitri"));
                
                
                cauthuList.add(cauThu);
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
        return cauthuList;
    }

    public static void insert(CauThu cauThu) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            String sql = "INSERT INTO thongtincauthu(madoi, tencauthu, ngaysinh, soao, vitri) VALUES(?, ?, ?, ?, ?)";
            statement = connection.prepareCall(sql);

            statement.setInt(1, cauThu.getMadoi());
            statement.setString(2, cauThu.getTencauthu());
            statement.setDate(3, new java.sql.Date(cauThu.getNgaysinh().getTime()));
            statement.setInt(4, cauThu.getSoao());
            statement.setString(5, cauThu.getVitri());      

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

    public static void update(CauThu cauThu) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            String sql = "UPDATE thongtincauthu SET madoi = ?, tencauthu = ?, ngaysinh = ?, soao = ?, vitri = ? WHERE macauthu = ?";
            statement = connection.prepareCall(sql);
            statement.setInt(1, cauThu.getMadoi());
            statement.setString(2, cauThu.getTencauthu());
            statement.setDate(3, new java.sql.Date(cauThu.getNgaysinh().getTime()));
            statement.setInt(4, cauThu.getSoao());
            statement.setString(5, cauThu.getVitri());
            statement.setInt(6, cauThu.getMacauthu());
                    
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

    public static void delete(int macauthu) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            String sql = "DELETE FROM thongtincauthu WHERE macauthu = ?";
            statement = connection.prepareCall(sql);

            statement.setInt(1, macauthu);

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

    public static List<CauThu> findByNameCauthu(String tencauthu) {
        List<CauThu> cauThuList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            String sql = "SELECT thongtincauthu.macauthu, "
                    + "thongtincauthu.tencauthu, "
                    + "thongtincauthu.ngaysinh, "
                    + "thongtincauthu.soao, "
                    + "thongtincauthu.vitri, "
                    + "clb.tendoi clb_tendoi "
                    + "FROM thongtincauthu JOIN clb "
                    + "ON thongtincauthu.madoi = clb.madoi "
                    + "WHERE thongtincauthu.tencauthu LIKE ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, tencauthu + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CauThu cauThu;
                cauThu = new CauThu(resultSet.getInt("soao"),
                        resultSet.getDate("ngaysinh"),
                        resultSet.getString("clb_tendoi"),
                        resultSet.getString("tencauthu"),
                        resultSet.getString("vitri"));
                cauThuList.add(cauThu);
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
        return cauThuList;
    }
    
    public static List<CauThu> findByTenDoi(String tendoi) {
        List<CauThu> cauThuList = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Lấy tất cả dah sách sinh viên
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/league_football_management", "root", "");

            //query
            String sql = "SELECT thongtincauthu.macauthu, "
                    + "thongtincauthu.tencauthu, "
                    + "thongtincauthu.ngaysinh, "
                    + "thongtincauthu.soao, "
                    + "thongtincauthu.vitri, "
                    + "clb.tendoi clb_tendoi "
                    + "FROM thongtincauthu JOIN clb "
                    + "ON thongtincauthu.madoi = clb.madoi "
                    + "WHERE clb.tendoi = ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, tendoi);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CauThu cauThu;
                cauThu = new CauThu(resultSet.getInt("soao"),
                        resultSet.getDate("ngaysinh"),
                        resultSet.getString("clb_tendoi"),
                        resultSet.getString("tencauthu"),
                        resultSet.getString("vitri"));
                cauThuList.add(cauThu);
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
        return cauThuList;
    }
    
}
