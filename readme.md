# 题目要求
## 练习1  
- 创建一个数据库，并建立表格
- 编写程序，显示数据库表中的记录 

**code example**
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseExample {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 使用正确的驱动类名
            Connection con;
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "1234"); // 使用标准双引号
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery("SELECT * FROM goods");
            System.out.println("查询到数据如下：");
            while (rs.next()) { // 循环将结果集游标往下移动，到达末尾返回false
                // 根据字段名称获得各个字段的值
                System.out.print(rs.getString("number") + "\t"); // 获得字符串
                System.out.print(rs.getString("name") + "\t"); // 获得字符串
                System.out.print(rs.getDate("madeTime") + "\t"); // 获得日期型数据
                System.out.print(rs.getDouble("price") + "\t"); // 获得数
                System.out.println(); // 换行
            }
            con.close();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
```
## 练习2
针对前面的数据库和程序，
- 插入、删除、修改数据库表中的记录
- 为上面的数据库操作增加事务处理 

**code example**
```java
import Chapter_14.GetDatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Example_7 {
    public static void main(String[] args) {
        Connection connection;
        Statement SQL;
        ResultSet resultSet;

        connection = GetDatabaseConnection.connectDB("book", "root", "");
        try {
            connection.setAutoCommit(false);    //关闭自动刷新
            SQL = connection.createStatement();
            String sql = "select * from mybooklist where name = '我的大学' ";
            resultSet = SQL.executeQuery(sql);
            resultSet.next();
            System.out.print("未修改之前:" + resultSet.getString(2) + "的价格为:" + resultSet.getString(3));
            float price1 = resultSet.getFloat(3);
            System.out.println();
            sql = "select * from mybooklist where name = '我的冬日' ";
            resultSet = SQL.executeQuery(sql);
            resultSet.next();
            System.out.print("未修改之前:" + resultSet.getString(2) + "的价格为:" + resultSet.getString(3));
            float price2 = resultSet.getFloat(3);
            float price = price1 - 5;
            sql = "update mybooklist set price = " + price + "where name = '我的大学'";
            SQL.executeUpdate(sql);
            price = price2 + 5;
            sql = "update mybooklist set price = " + price + "where name = '我的冬日'";
            SQL.executeUpdate(sql);
            connection.commit();
            connection.setAutoCommit(true);
            sql = "select * from mybooklist where name = '我的大学' ";
            resultSet = SQL.executeQuery(sql);
            resultSet.next();
            System.out.println();
            System.out.print("修改之后:" + resultSet.getString(2) + "的价格为:" + resultSet.getString(3));
            System.out.println();
            sql = "select * from mybooklist where name = '我的冬日' ";
            resultSet = SQL.executeQuery(sql);
            resultSet.next();
            System.out.print("修改之后:" + resultSet.getString(2) + "的价格为:" + resultSet.getString(3));
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();   //撤销事务所做的
            }
            e.printStackTrace();
        }}}
```