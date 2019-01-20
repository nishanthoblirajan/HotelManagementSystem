package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {


    static Connection connection = null;
    public static Connection getConnection(){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/fake_hotel_db";
            connection = DriverManager.getConnection(url,"root","");
            System.out.println("Connection Successful");
        }catch(Exception e){
            System.out.println("Connection Error");
        }

        return connection;

    }

    public static void main(String[] args) {
        getConnection();
    }
}
