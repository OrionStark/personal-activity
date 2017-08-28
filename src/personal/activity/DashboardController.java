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
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author orion_stark
 */
public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ActivityConfiguration cft = new ActivityConfiguration();
    @FXML
    private StackPane stack_pane;
    
    @FXML
    private AnchorPane anchor_base;
    
    @FXML
    public Pane top_pane;
    
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
    private JFXButton tday_act_btn;
    
    @FXML
    private JFXTreeTableView<ActivityCore> act_table;
    
    @FXML
    private ImageView close_btn;

    @FXML
    private ImageView minimize_btn;
    
    @FXML
    private JFXDialogLayout today_act_layout;
    
    @FXML
    private JFXDialog today_act_dialog;
    
    private int userID;
    private User onstage_user;
    private double Xoffset = 0;
    private double Yoffset = 0;
    Today_Act_PageController today_act_p;
    Parent loader;
    Timer timer;
    TimerTask myTask;
    ObservableList<ActivityCore> act_list = FXCollections.observableArrayList();
    
    public void setUserID(int user_id)
    {
        this.userID = user_id;
    }
    
    public void setOnStageUser(User user)
    {
        this.onstage_user = user;
    }
    
    private void warning_dialog(Text text_header, Text body_message)
    {
        JFXDialogLayout warning_dialog_layout = new JFXDialogLayout();
            warning_dialog_layout.setHeading(text_header);
            warning_dialog_layout.setBody(body_message);
            JFXDialog get_dialog = new JFXDialog(stack_pane, warning_dialog_layout, JFXDialog.DialogTransition.TOP);
            JFXButton confirm_btn = new JFXButton("Okay");
            confirm_btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    get_dialog.close();
                }
            });
            warning_dialog_layout.setActions(confirm_btn);
            get_dialog.show();
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
    
    private void checkField(Calendar cal) throws Exception
    {
        int count = 0;
        if("".equals(this.activity_field.getText()))
        {
            throw new Exception("Some fields are not a valid information, please check it again");
        }
        if("".equals(this.description_field.getText()))
        {
            throw new Exception("Some fields are not a valid information, please check it again");
        }
        if(LocalDate.now().isAfter(this.date_picker.getValue()))
        {
            throw new Exception("Some fields are not a valid information, please check it again");
        }
        if(cal.getTime().getTime() < Calendar.getInstance().getTime().getTime())
        {
            throw new Exception("Some fields are not a valid information, please check it again");
        }
    }
    
    private void clearAllField()
    {
        this.activity_field.clear();
        this.description_field.clear();
    }

    private void setTableColumn()
    {
        JFXTreeTableColumn<ActivityCore, String> activity_name = new JFXTreeTableColumn("Activity");
        JFXTreeTableColumn<ActivityCore, String> description_col = new JFXTreeTableColumn("Description");
        JFXTreeTableColumn<ActivityCore, String> color_col = new JFXTreeTableColumn("Color");
        JFXTreeTableColumn<ActivityCore, String> date = new JFXTreeTableColumn("Date and Time");
        activity_name.setPrefWidth(144);
        activity_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ActivityCore, 
                String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ActivityCore, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getTodo());
            }
        });
        description_col.setPrefWidth(144);
        description_col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ActivityCore, String>, 
                ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ActivityCore, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getDescription());
            }
        });
        color_col.setPrefWidth(137);
        color_col.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ActivityCore, String>,
                ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ActivityCore, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getColor());
            }
        });
        date.setPrefWidth(144);
        date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ActivityCore, String>, 
                ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ActivityCore, String> param) {
                Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date_catcher = param.getValue().getValue().getDateAct();
                return new SimpleStringProperty(formatter.format(date_catcher));
            }
        });
        for(int i = 0; i < cft.getActivity(userID).size(); i++)
        {
            act_list.add(cft.getActivity(userID).get(i));
        }
        final TreeItem<ActivityCore> root = new RecursiveTreeItem<>(act_list, RecursiveTreeObject::getChildren);
        act_table.getColumns().setAll(activity_name, description_col, color_col, date);
        act_table.setRoot(root);
        act_table.setShowRoot(false);
    }
    
    void setDraggedForm(Parent parent, Stage stage)
    {   
        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Xoffset = event.getSceneX();
                Yoffset = event.getSceneY();
            }
        });
        
        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - Xoffset);
                stage.setY(event.getScreenY() - Yoffset);
            }
        });
        
    }
    
    private void LogoutClearBuffeer()
    {
        cft = null;
        userID = 0;
        onstage_user = null;
    }
    private void preparePopup() throws IOException
    {
        today_act_p = new Today_Act_PageController();
        today_act_p.setList(act_list);
        this.today_act_layout = new JFXDialogLayout();
        this.today_act_dialog = new JFXDialog(stack_pane, this.today_act_layout, JFXDialog.DialogTransition.LEFT);
        FXMLLoader loaderr = new FXMLLoader();
        loaderr.setLocation(getClass().getResource("Today_Act_Page.fxml"));
        loaderr.setController(today_act_p);
        this.today_act_dialog.setContent(loaderr.load());
        
    }
    
    private void refractorListTodayAct()
    {
        this.timer = new Timer();
        myTask = new TimerTask(){
            @Override
            public void run()
            {
                Date date;
                Calendar cal;
                for (int i = 0; i < today_act_p.activity_list_view.getItems().size(); i++)
                {
                    date = today_act_p.activity_list_view.getItems().get(i).getDateAct();
                    cal = Calendar.getInstance();
                    cal.setTime(date);
                    if (!(Calendar.getInstance().get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR)))
                    {
                        today_act_p.activity_list_view.getItems().remove(i);
                    }
                }
                if (act_list != null)
                {
                    for (int i =0; i < act_list.size(); i++)
                    {
                        date = act_list.get(i).getDateAct();
                        cal = Calendar.getInstance();
                        cal.setTime(date);
                        if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR))
                        {
                                if (!today_act_p.activity_list_view.getItems().contains(act_list.get(i)))                                    
                                    {
                                        today_act_p.activity_list_view.getItems().add(act_list.get(i));
                                    }
                        }
                    }
                }
            }
        };
        timer.schedule(myTask, 0, 1000);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTableColumn();
        try {
            preparePopup();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.minimize_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
                stage.setIconified(true);
            }
        });
        refractorListTodayAct();
        tday_act_btn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                today_act_dialog.show();
            }
        });
        
        /*
            Set on close button clicked and go to login page
        */
        this.close_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                JFXDialogLayout warning_dialog_layout = new JFXDialogLayout();
                warning_dialog_layout.setHeading(new Text("Exit"));
                warning_dialog_layout.setBody(new Text("Are your sure ?"));
                JFXDialog get_dialog = new JFXDialog(stack_pane, warning_dialog_layout, JFXDialog.DialogTransition.CENTER);
                JFXButton confirm_btn = new JFXButton("Okay");
                JFXButton cancel_btn = new JFXButton("Cancel");
                confirm_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        timer.cancel();
                        timer = null;
                        Platform.exit();
                    }
                });
                cancel_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        get_dialog.close();
                    }
                });
                warning_dialog_layout.setActions(confirm_btn, cancel_btn);
                get_dialog.show();
            }
        });
        
        // Rippler for menu button
        JFXRippler rippler = new JFXRippler(menu_btn);
        dash_pane.getChildren().add(rippler);
        rippler.ripplerFillProperty().set(Paint.valueOf("#ffffff"));
        rippler.setLayoutX(12);
        rippler.setLayoutY(236);
        
        
        // The validation is not available yet, but you can make it soon
        // It's just for testing, soon will be upgraded with any validation check
        submit_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               try
               {
                    Calendar cal = Calendar.getInstance();
                    Date test = java.sql.Date.valueOf(date_picker.getValue());
                    cal.setTime(test);
                    cal.set(Calendar.HOUR_OF_DAY, time_picker.getValue().getHour());
                    cal.set(Calendar.MINUTE, time_picker.getValue().getMinute());
                    cal.set(Calendar.SECOND, time_picker.getValue().getSecond());
                    test = cal.getTime();
                    checkField(cal);
                    cft.setActivity(activity_field.getText(), description_field.getText(), test, act_color_picker.getValue().toString(), 
                       userID);
                    int list_index = cft.getActivity(userID).size() - 1;
                    act_list.add(cft.getActivity(userID).get(list_index));
                    
                    // Get access to the Today_Act page for setting a newer Data to Today Activity ListView
                    /*
                    if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR))
                    {
                        today_act_p.activity_list_view.getItems().add(cft.getActivity(userID).get(list_index));
                    }
                    */
                    clearAllField();
               }catch (Exception e){
                   warning_dialog(new Text("Something went wrong"), new Text(e.getMessage()));
               }
               
            }
        }
        );

    }
    
}
