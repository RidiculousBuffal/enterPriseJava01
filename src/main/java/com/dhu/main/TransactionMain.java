package com.dhu.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

import com.dhu.dao.UserDao;
import com.dhu.connection.connection;

public class TransactionMain {
    public static void main(String[] args) throws SQLException {
        Connection conn = connection.getConnection();
//        插入时候的事务
//        事务开始前
        UserDao.ShowAllData();
        try {
            conn.setAutoCommit(false);//关闭事务
            for (int i = 0; i < 10; i++) {
                UserDao.insertSomeDataWithTransaction(conn);
                // 创建 Random 类的实例
                Random random = new Random();
                // 生成一个随机整数
                int randomInt = random.nextInt(100);
//                50%的概率出现异常
                if (randomInt % 2 == 0) {
                    int b = 1 / 0;
                }
                System.out.println("第" + i + "条数据插入成功");
            }
        } catch (Exception e) {
            conn.rollback();
            System.out.println("insert Error rollback~");
            System.out.println(e.getMessage());
        }
        //        事务开始后
        UserDao.ShowAllData();
//        ---------------------------------------------------------
        //删除事务
        Connection conn2 = connection.getConnection();
//        删除前
        UserDao.ShowAllData();
//        开始删除
        try {
            conn2.setAutoCommit(false);
            UserDao.deleteSomeDataWithTransaction(conn2, 9);
            Random random = new Random();
            // 生成一个随机整数
            int randomInt = random.nextInt(100);
//                50%的概率出现异常
            if (randomInt % 2 == 0) {
                int b = 1 / 0;
            } else {
                System.out.println("删除成功");
            }

        } catch (Exception e) {
            conn2.rollback();
            System.out.println("delete Error rollback~");
            System.out.println(e.getMessage());
        }
//        -----------------------------------------------------------
//        更新事务
//        更新前
        UserDao.ShowAllData();
        Connection conn3 = connection.getConnection();
//        开始更新
        try {
            conn3.setAutoCommit(false);
            UserDao.updateSomeDataWithTransaction(conn3, 10, "123123123", "ccc");
            Random random = new Random();
            // 生成一个随机整数
            int randomInt = random.nextInt(100);
//                50%的概率出现异常
            if (randomInt % 2 == 0) {
                int b = 1 / 0;
            } else {
                System.out.println("更新成功");
            }
        } catch (Exception e) {
            conn3.rollback();
            System.out.println("update Error rollback~");
            System.out.println(e.getMessage());
        }
        UserDao.ShowAllData();
    }

}
