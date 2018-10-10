package renwu_7.J255;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class J255 {
    public static void main(String[] args) {
        Connection c=null;
        PreparedStatement s=null;
        try {
            //初始化驱动，需要导入mysql-connector-java-5.0.8-bin.jar文件
            Class.forName("com.mysql.jdbc.Driver");

            /**
             * 建立与数据库的Connection连接
             这里需要提供：
             数据库所处于的ip:127.0.0.1 (本机)
             数据库的端口号： 3306 （mysql专用端口号）
             数据库名称 db
             编码方式 UTF-8
             账号 root
             密码 admin
             **/
            c = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/db?characterEncoding=UTF-8",
                    "root", "admin");

            System.out.println("连接成功，获取连接对象： " + c);

            //执行sql语句
            String sql="SELECT * FROM t_user WHERE regTime BETWEEN ? AND ?";
            s=c.prepareStatement(sql);
            Date start=new Date(str2Date("2017-8-10 10:20:30"));
            Date end=new Date(str2Date("2017-9-12 10:20:30"));
            s.setDate(1,start);
            s.setDate(2,end);
            ResultSet resultSet=s.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id")+"\t"+resultSet.getString("username"+"\t"+resultSet.getDate("regTime")));
            }

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
    public static long str2Date(String datestr){
        //格式化日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try{
            return dateFormat.parse(datestr).getTime();
        }catch (ParseException e){
            return 0;
        }
    }
}
