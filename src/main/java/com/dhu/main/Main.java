package com.dhu.main;
import com.dhu.dao.UserDao;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
//        创建一张表
        UserDao.createTable();
//        插入一些数据
        for (int i = 0; i < 10; i++) {
            UserDao.insertSomeData();
        }
//        查看所有数据
        UserDao.ShowAllData();
//        修改用户数据
        UserDao.UpdateSomeData(8, "DAVID", "123456");
        UserDao.ShowAllData();
//      删除用户数据
        UserDao.deleteSomeData(9);
        UserDao.ShowAllData();
    }
}
