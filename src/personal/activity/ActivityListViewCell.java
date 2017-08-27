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

import com.jfoenix.controls.JFXListCell;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 *
 * @author orion_stark
 */
public class ActivityListViewCell  extends JFXListCell<ActivityCore>{
    @FXML
    private HBox hBox;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane color_act;

    @FXML
    private Label activity_name;

    @FXML
    private Label activity_time;
    
    private FXMLLoader mloader;

    @Override
    public void updateItem(ActivityCore act, boolean empty)
    {
        super.updateItem(act, empty);
        
        if(empty || act == null)
        {
            setText(null);
            setGraphic(null);
        }
        else
        {
            if(mloader == null)
            {
                mloader = new FXMLLoader(getClass().getResource("ListCell.fxml"));
                mloader.setController(this);
                
                try
                {
                    mloader.load();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            activity_name.setText(act.getTodo());
            activity_time.setText(act.getDateAct().toString());
            color_act.setStyle("-fx-background-color: #" + act.getColor().substring(2));
            setText(null);
            setGraphic(hBox);
        }
    }
    
}
