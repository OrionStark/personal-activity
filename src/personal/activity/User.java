/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personal.activity;
import java.util.*;

/**
 *
 * @author orion_stark
 */
public class User {
    private String Name;
    private String Username;
    private String Email_address;
    private String Password;
    
    public User()
    {
        this.Name = "";
        this.Username = "";
        this.Email_address = "";
        this.Password = "";
    }
    public User(String Name, String username, String email, String password)
    {
        this.Name = Name;
        this.Username = username;
        this.Email_address = email;
        this.Password = password;
    }
    
    public String getName()
    {
        return this.Name;
    }
    public String getUsername()
    {
        return this.Username;
    }
    public String getEmailAdd()
    {
        return this.Email_address;
    }
    public String getPassword()
    {
        return this.Password;
    }
    public void setName(String name)
    {
        this.Name = name;
    }
    public void setUsername(String username)
    {
        this.Username = username;
    }
    public void setEmail(String email)
    {
        this.Email_address = email;
    }
    public void setPassword(String password)
    {
        this.Password = password;
    }
}
