/*
 * The MIT License
 *
 * Copyright 2017 orion_stark.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package personal.activity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javafx.beans.property.StringProperty;

/**
 *
 * @author orion_stark
 */
public class ActivityConfiguration {
    private dbConnector data_base;
    private Connection db_connection;
    
    public ActivityConfiguration()
    {
        this.data_base = new dbConnector();
        this.db_connection = this.data_base.getConnector();
    }
    
    private boolean isnullConnector()
    {
        if(this.db_connection == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean setActivity(String to_do, String description, Date date_todo, 
            String color_act, Integer User_ID)
    {
        if(!(isnullConnector()))
        {
            PreparedStatement statement = null;
            String act_query = "insert into Activity(Activity_Name, Description, Date_Todo, Color, User_ID)" + 
                    "values(?, ?, ?, ?, ?)";
            try
            {
                statement = this.db_connection.prepareStatement(act_query);
                statement.setString(1, to_do);
                statement.setString(2, description);
                statement.setTimestamp(3, new java.sql.Timestamp(date_todo.getTime()));
                statement.setString(4, color_act);
                statement.setInt(5, User_ID);
                statement.execute();
                //this.db_connection.close();
                return true;
                
            }
            catch(SQLException e)
            {
                return false;
            }
        }
        else
        {
            return false;
        }

    }
    
    public List<ActivityCore> getActivity(int user_id)
    {
        List<ActivityCore> act = new ArrayList<>();
        if(!isnullConnector())
        {
            PreparedStatement statement = null;
            ResultSet result = null;
            String query = "select * from Activity where User_ID=?";
            try
            {
                statement = this.db_connection.prepareStatement(query);
                statement.setInt(1, user_id);
                result = statement.executeQuery();
                while(result.next())
                {
                    act.add(new ActivityCore(result.getString("Activity_Name"), result.getString("Description"), result.getString("Color"), 
                            result.getDate("Date_Todo"), result.getInt("Activity_ID")));
                }
                if(act.size() < 0)
                {
                    throw new Exception("ActivityList is empty, set a new one!");
                }
                else
                {
                    return act;
                }
                
            }
            catch (Exception e)
            {
                // Nothing todo XD
            }
        }
        return act;
    }
    
    public List<ActivityCore> getTodayActivity(int user_id)
    {
        List<ActivityCore> act = new ArrayList<>();
        Date test = new Date();
        return act;
    }
    
}
