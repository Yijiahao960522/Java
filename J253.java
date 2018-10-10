package renwu_7.J253;

import java.sql.*;

public class J253 {
    public static void main(String[] args) {
        Connection c=null;
        PreparedStatement s=null;
        try {
            //初始化驱动，需要导入mysql-connector-java-5.0.8-bin.jar文件
            Class.forName("com.mysql.jdbc.Driver");

            /**建立与数据库的Connection连接
             这里需要提供：
             数据库所处于的ip:127.0.0.1 (本机)
             数据库的端口号： 3306 （mysql专用端口号）
             数据库名称 db
             编码方式 UTF-8
             账号 root
             密码 admin**/
            c = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/db?characterEncoding=UTF-8",
                    "root", "admin");

            System.out.println("连接成功，获取连接对象： " + c);

            //执行sql语句
            String sql="INSERT INTO t_user (username,pwd,regTime)VALUES(?,?,?)";
            s=c.prepareStatement(sql);
            for (int i=0;i<200;i++){
            s.setString(1,"qianliu");
            s.setString(2,"123456");
            s.setDate(3,new Date(1000));
            s.addBatch();
            }
            s.executeBatch();
            System.out.println("成功插入行数据");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (s !=null)
                try{
                    s.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            if (c != null)try {
                c.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}