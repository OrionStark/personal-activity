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

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author orion_stark
 */
public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane anchor_base;
    
    @FXML
    private Pane top_pane;
    
    @FXML
    private Pane dash_pane;
    
    @FXML
    private Pane menu_btn;

    @FXML
    private JFXDrawer option_drawer;

    @FXML
    private AnchorPane content_ctr;

    @FXML
    private JFXTextField activity_field;

    @FXML
    private JFXTextArea description_field;

    @FXML
    private JFXDatePicker date_picker;

    @FXML
    private JFXTimePicker time_picker;

    @FXML
    private JFXColorPicker act_color_picker;

    @FXML
    private JFXButton submit_btn;
    
    @FXML
    private Pane today_act_btn;
    
    @FXML
    private JFXTreeTableView<?> act_table;
    
    private int userID;
    private User onstage_user;
    
    public void setUserID(int user_id)
    {
        this.userID = user_id;
    }
    
    public void setOnStageUser(User user)
    {
        this.onstage_user = user;
    }

    @FXML
    void menu_pressed(MouseEvent event) throws IOException {
        AnchorPane menu_side = FXMLLoader.load(getClass().getResource("OptionsMenu.fxml"));
        option_drawer.setSidePane(menu_side);
        if(option_drawer.isShown())
        {
            option_drawer.close();
        }
        else
        {
            option_drawer.open();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Rippler for menu button
        JFXRippler rippler = new JFXRippler(menu_btn);
        dash_pane.getChildren().add(rippler);
        rippler.ripplerFillProperty().set(Paint.valueOf("#ffffff"));
        rippler.setLayoutX(12);
        rippler.setLayoutY(236);
        
        // Rippler for top panel
        JFXRippler sec_rippler = new JFXRippler(top_pane);
        anchor_base.getChildren().add(sec_rippler);
        sec_rippler.setLayoutX(203);
        sec_rippler.setLayoutY(0);

    }
    
}
