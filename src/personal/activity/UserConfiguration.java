/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personal.activity;
import java.sql.*;
import java.util.*;

/**
 *
 * @author orion_stark
 */
public class UserConfiguration {
    private dbConnector data_base;
    private Connection db_connection;
    private User online_user;
    private final List<User> user_buffer;
    private int user_id;
    
    public UserConfiguration()
    {
        this.data_base = new dbConnector();
        this.user_buffer = new ArrayList<>();
        this.db_connection = this.data_base.getConnector();
        this.online_user = null;
    }
    
    private boolean isNullConnector()
    {
        return this.db_connection == null;
    }
    
    public String user_register(String username, String fullName, String email_address,
            String password) throws SQLException
    {

            PreparedStatement statement = null;
            final String query = "insert into user (username, Full_Name, password, user_email)" + "values (?, ?, ?, ?)";
            try
            {
                statement = this.db_connection.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, fullName);
                statement.setString(3, password);
                statement.setString(4, email_address);
                statement.execute();
                this.user_buffer.add(new User(fullName, username, email_address, password));
                return "sukses";
            }
            catch (Exception e)
            {
                return e.getMessage();
            }
            finally
            {
                statement.close();
                this.db_connection.close();
            }
            
    }
    
    public boolean login(String username, String password) throws SQLException
    {
        if(isNullConnector())
        {
            return false;
        }
        else
        {
            PreparedStatement statement = null;
            ResultSet resultset = null;
            String query = "select * from user where username = ? and password = ?";
            try
            {
                statement = this.db_connection.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, password);
                resultset = statement.executeQuery();
                if(resultset.next())
                {
                    this.online_user = new User(resultset.getString("Full_Name"), resultset.getString("username"), 
                    resultset.getString("user_email"), resultset.getString("password"));
                    this.user_id = resultset.getInt("User_ID");
                    return true;
                }
                else
                {
                    return false;
                }
            }
            catch (Exception e)
            {
                return false;
            }
            finally
            {
                statement.close();
                resultset.close();
                this.db_connection.close();
            }
        }
    }
    
    public User getOnlineUser()
    {
        if(this.online_user != null)
        {
            return this.online_user;
        }
        else
        {
            return new User();
        }
    }
    
    public int getUserID()
    {
        if(this.online_user != null)
        {
            return this.user_id;
        }
        else
        {
            return 0;
        }
    }
    
}
