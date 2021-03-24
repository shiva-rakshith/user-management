package org.rakshith.useractions.mysqluseractions;

import org.rakshith.useractions.UserActions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class MysqlUserActions extends MysqlDBConfiguration implements UserActions {

    int queryResult=0;

    private static final Logger logger = LoggerFactory.getLogger(MysqlUserActions.class);

    @Override
    public String createUser (String phoneNumber, String name,int age, String location){
        try {
            if (phoneNumber.length() == 10) {
                con = DriverManager.getConnection(MySQLURL, dbUserName, dbPassword);
                PreparedStatement stmt=con.prepareStatement("insert into userdetails values(?,?,?,?)");
                stmt.setString(1,phoneNumber);
                stmt.setString(2,name);
                stmt.setInt(3,age);
                stmt.setString(4,location);
                stmt.executeUpdate();
                logger.info("Created user details successfully: " + phoneNumber + " " + name + " " + age + " " + location);
                return "Created user details successfully";
            } else {
                return "Invalid phone number";
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
       return null;

    }

    @Override
    public String updateUser(String phoneNumber, String name, int age, String location) {
        try {
            con = DriverManager.getConnection(MySQLURL, dbUserName, dbPassword);
            PreparedStatement stmt=con.prepareStatement("update userdetails set name=?,age=?,location=? where phoneNumber=?");
            stmt.setString(1,name);
            stmt.setInt(2,age);
            stmt.setString(3,location);
            stmt.setString(4,phoneNumber);
            queryResult = stmt.executeUpdate();
            if (queryResult == 1){
                logger.info("New details are successfully updated: " + phoneNumber + " " + name + " " + age + " " + location);
                return "User details updated successfully";
            }
            else{
                return "user does not exist";
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String deleteUser(String phoneNumber) {
        try {
            con = DriverManager.getConnection(MySQLURL, dbUserName, dbPassword);
            PreparedStatement stmt=con.prepareStatement("delete from userdetails where phoneNumber=?");
            stmt.setString(1,phoneNumber);
            queryResult = stmt.executeUpdate();
            if (queryResult == 1){
                logger.info(phoneNumber + " user details deleted successfully.");
                return "Deleted user details successfully";
            }
            else{
                return "user does not exist";
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String showUser(String phoneNumber) {
        try {
            con = DriverManager.getConnection(MySQLURL, dbUserName, dbPassword);
            PreparedStatement stmt=con.prepareStatement("select * from userdetails where phoneNumber=?");
            stmt.setString(1,phoneNumber);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()) {
                return "{\"name\":" +"\""+ rs.getString(2) +"\""+ ",\"age\":" + rs.getInt(3) + ",\"location\":" +"\""+ rs.getString(4)+"\""+"}";
            }
            else{
                return "user does not exist";
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
