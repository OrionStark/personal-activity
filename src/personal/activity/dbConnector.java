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
import java.sql.*;
/**
 *
 * @author orion_stark
 */
public class dbConnector {
    private Connection dbConnect;
    private boolean isConnected;
    
    public dbConnector()
    {
        this.dbConnect = null;
        this.isConnected = false;
    }
    
    private boolean Connecting()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            this.dbConnect = DriverManager.getConnection("jdbc:sqlite:Personal_ActivityDB.sqlite");
            this.isConnected = true;
        }
        catch (ClassNotFoundException | SQLException e)
        {
            this.isConnected = false;
        }
        return this.isConnected;
    }
    
    public Connection getConnector()
    {
        if(Connecting())
        {
            return this.dbConnect;
        }
        else
        {
            return null;
        }
    }
    
    public void closeConnection() throws SQLException
    {
        this.dbConnect.close();
    }
}
