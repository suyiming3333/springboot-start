package com.corn.springboot.start.thread.threadlocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PWD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/testdb?autoReconnect=true&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";

    //定义一个数据库连接
    private static Connection conn = null;
    //Threadlocal保存连接,避免每次操作都开启、关闭数据库连接
    private static ThreadLocal<Connection> connContainer = new ThreadLocal<Connection>();
    //获取连接
    public synchronized static Connection getConnection() {
        //获取连接对象
        conn = connContainer.get();
        try {
            if(conn == null) {
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(URL, USER, PWD);
                connContainer.set(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }
    //关闭连接
    public static void closeConnection() {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyThread mt = new MyThread();
        for (int i = 0; i < 3; i++) {
            new Thread(mt).start();
        }
    }
}

class MyThread implements Runnable{
    Dao dao = new Dao();
    @Override
    public void run() {
        dao.insert();
        dao.delete();
        dao.update();
        dao.select();
    }

}

class Dao {
    public void insert() {
        //获取连接
        Connection conn = DBUtil.getConnection();
        System.out.println("Dao.insert()-->" + Thread.currentThread().getName() + conn);
    }
    public void delete() {
        //获取连接
        Connection conn = DBUtil.getConnection();
        System.out.println("Dao.delete()-->" + Thread.currentThread().getName() + conn);
    }
    public void update() {
        //获取连接
        Connection conn = DBUtil.getConnection();
        System.out.println("Dao.update()-->" + Thread.currentThread().getName() + conn);
    }
    public void select() {
        //获取连接
        Connection conn = DBUtil.getConnection();
        System.out.println("Dao.select()-->" + Thread.currentThread().getName() + conn);
    }
}