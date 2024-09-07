package com.dhu.dao;
import com.dhu.connection.connection;

import java.security.SecureRandom;
import java.sql.*;

public class UserDao {
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "username VARCHAR(50) NOT NULL, " +
                "password VARCHAR(50) NOT NULL)";

        try (
             Connection conn = connection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            System.out.println("Table created fail :("+e.getMessage());
        }
    }
//    插入一条数据
   public static void insertSomeData(){
        String sql = "INSERT INTO Users(username,password) VALUES(?,?)";
        try{
            Connection conn = connection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,UserDao.generateRandomUsername());
            stmt.setString(2,UserDao.generateRandomPassword());
            stmt.executeUpdate();
            System.out.println("Insert successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
   }
    //修改数据
    public static void UpdateSomeData(int id, String username, String password){
        String sql = "UPDATE Users SET username=?, password=? WHERE id=?";
        try{
            Connection conn = connection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(3,id);
            stmt.setString(1,username);
            stmt.setString(2,password);
            stmt.executeUpdate();
            System.out.println("update successfully!");
        } catch (SQLException e) {
            System.out.println("update error!"+e.getMessage());
        }
    }
//    删除一条数据
    public static void deleteSomeData(int id){
        String sql = "DELETE FROM Users WHERE id=?";
        try{
            Connection conn = connection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            stmt.executeUpdate();
            System.out.println("delete successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//   展示所有数据
   public static void ShowAllData(){
       String sql = "SELECT * FROM Users";

       try (Connection conn = connection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

           System.out.println("User ID\tUsername\tPassword");
           System.out.println("--------------------------------------");

           while (rs.next()) {
               int id = rs.getInt("id");
               String username = rs.getString("username");
               String password = rs.getString("password");

               System.out.printf("%d\t%s\t%s%n", id, username, password);
           }

       } catch (SQLException e) {
           System.out.println("Failed to retrieve data: " + e.getMessage());
       }
   }
    // 生成随机用户名和密码
    private static String generateRandomUsername() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder username = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 8; i++) { // 生成8位随机用户名
            int index = random.nextInt(chars.length());
            username.append(chars.charAt(index));
        }

        return username.toString();
    }

    private static String generateRandomPassword() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()";
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 12; i++) { // 生成12位随机密码
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }

        return password.toString();
    }

    // 插入一条数据
    public static void insertSomeDataWithTransaction(Connection conn) throws SQLException {
        String sql = "INSERT INTO Users(username,password) VALUES(?,?)";
        try {

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, generateRandomUsername());
            stmt.setString(2, generateRandomPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert error! " + e.getMessage());
        }
    }

    // 修改数据
    public static void updateSomeDataWithTransaction(Connection conn,int id, String username, String password) throws SQLException {
        String sql = "UPDATE Users SET username=?, password=? WHERE id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("update error! " + e.getMessage());
        }
    }

    // 删除一条数据
    public static void deleteSomeDataWithTransaction(Connection conn,int id) throws SQLException {
        String sql = "DELETE FROM Users WHERE id=?";
        try {

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("delete error! " + e.getMessage());
        }
    }


}
