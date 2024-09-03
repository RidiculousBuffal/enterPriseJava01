package com.dhu.main;
import com.dhu.dao.UserDao;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
//        创建一张表
        UserDao.createTable();
//        插入一些数据
        for(int i = 0 ; i < 10 ; i++){
            UserDao.insertSomeData();
        }
//        查看所有数据
        UserDao.ShowAllData();
//        修改用户数据
        UserDao.UpdateSomeData(8,"DAVID","123456");
        UserDao.ShowAllData();
//      删除用户数据
        UserDao.deleteSomeData(9);
        UserDao.ShowAllData();
//        ---------------------------
//        事务插入
        for (int i = 0; i < 10; i++) {
            UserDao.insertSomeDataWithTransaction();
        }
//        事务删除
        UserDao.deleteSomeDataWithTransaction(10);
//        事务更新
        UserDao.updateSomeDataWithTransaction(5,"11111","2222");
    }
}
