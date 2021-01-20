package com.example.demo.Controller;


import com.example.demo.Model.Connection1;
import com.example.demo.Model.user;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CRUD {
    @PostMapping(value = "/user")
    public void postUser(@RequestBody UserInserted userInserted){
        String sql = " INSERT INTO USER (username,password,fullname,email,address,phone) VALUE ('" + userInserted.getAddress() + "','"
                + userInserted.getPassword() + "','" + userInserted.getFullname() + "','"
                + userInserted.getEmail() + "','" + userInserted.getAddress() + "','" + userInserted.getPhone() + "');";
        try {
            Statement statement = Connection1.getInstance().getConnections().createStatement();
            statement.execute(sql);
            System.out.println("Insert complete");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @GetMapping(value = "/user/list")
    public List<user> getListUser(){
        String sql  = "SELECT * FROM USER ORDER BY FULLNAME DESC ; ";
        List < user> users = new ArrayList<>();
        try {
            Statement statement = Connection1.getInstance().getConnections().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                user user1 = new user(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));
                users.add(user1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            return users;
        }

    }
    @PutMapping(value = "/user/{user_id}")
    public void updateUser(@PathVariable(name ="user_id") int userId,@RequestBody UserInserted userInserted){
        String sql = "SELECT id FROM USER WHERER id = '"+ userId +"' ";

        Statement statement = null;
        try {
            statement = Connection1.getInstance().getConnections().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String sqlUpdate = "UPDATE USER SET id = " + userId;
                if(userInserted.getUsername()!= null){
                    sqlUpdate+= ",username = '" + userInserted.getUsername() + "'";
                }
                if(userInserted.getPassword()!=null)
                {
                    sqlUpdate+=",password = '" +userInserted.getPassword() + "'";
                }
                if(userInserted.getFullname()!=null)
                {
                    sqlUpdate+=",fullname = '" +userInserted.getFullname() + "'";
                }
                if(userInserted.getEmail()!=null)
                {
                    sqlUpdate+=",email = '" +userInserted.getEmail() + "'";
                }
                if(userInserted.getAddress()!=null)
                {
                    sqlUpdate+=",address = '" +userInserted.getAddress() + "'";
                }
                sqlUpdate += "WHERE id = "  + userId + ";";
                Statement statement1 = Connection1.getInstance().getConnections().createStatement();
                statement1.execute(sqlUpdate);
                System.out.println("Update complete");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    @DeleteMapping(value = "/user")
    public void deleteUser(@RequestParam(name = "user_id") int userID)
    {
        String delete = "DELETE FROM USER WHERE id = " + userID + ";";
        //     System.out.println(delete);
        try{
            Statement statement = Connection1.getInstance().getConnections().createStatement();
            statement.execute(delete);
            System.out.println("Delete successfully");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Delete fail");
        }
    }
}

class UserInserted{
    private String username, password, fullname, email, address, phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}