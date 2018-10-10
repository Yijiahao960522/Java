package renwu_7.J254;

import java.sql.*;

public class J254 {
    public static void main(String[] args) {

        Connection c=null;
        Statement s=null;
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

            //创建Statement
            s=c.createStatement();
            System.out.println("获取Statement对象："+s);

            //事务
            c.setAutoCommit(false);

            //执行sql语句
            String sq3="DELETE FROM staff WHERE dept='HR'";
            s.execute(sq3);
            System.out.println("执行删除语句成功");
            String sq2="UPDATE staff SET sal=30000 WHERE dept='Engineering'";
            s.execute(sq2);
            System.out.println("执行修改语句成功");
            String sql="SELECT * FROM staff";
            ResultSet resultSet=s.executeQuery(sql);
            while (resultSet.next()){
                int id=resultSet.getInt(1);
                String name=resultSet.getString(2);
                String emai=resultSet.getString(3);
                String dept=resultSet.getString(4);
                Double sal=resultSet.getDouble(5);
                System.out.println(id+"\t"+name+"\t"+emai+"\t"+dept+"\t"+sal);
            }
            //手动提交
            c.commit();

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
