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
import java.util.*;
import javafx.beans.property.StringProperty;

/**
 *
 * @author orion_stark
 */
public class ActivityCore {
    private String to_do;
    private String description;
    private Date date_todo;
    private String color;
    
    public ActivityCore(String To_do, String description, String color, Date date_todo)
    {
        this.to_do = To_do;
        this.description = description;
        this.color = color;
        this.date_todo = date_todo;
    }
    
    public String getTodo()
    {
        return this.to_do;
    }
    public String getDescription()
    {
        return this.description;
    }
    public String getColor()
    {
        return this.color;
    }
    public Date getDateAct()
    {
        return this.date_todo;
    }
}
