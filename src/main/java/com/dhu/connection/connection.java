package com.dhu.connection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class connection {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static{
//        从数据库配置文件中中读入信息
        try{
            InputStream in = connection.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties prop = new Properties();
            prop.load(in);
            driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            //通过反射加载数据库驱动
            Class.forName(driver);
        }catch (Exception e){
            System.out.println("something wrong in get jdbc.properties 😒");
            e.printStackTrace();
        }
    }
    //获得数据库链接对象
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }
}
