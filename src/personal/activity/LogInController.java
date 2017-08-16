/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personal.activity;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.scene.Parent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 *
 * @author orion_stark
 */
public class LogInController implements Initializable {
    @FXML
    private StackPane stack_pane;

    @FXML
    private JFXTextField username_field;

    @FXML
    private JFXPasswordField password_field;

    @FXML
    private JFXButton submit_btn;

    @FXML
    private JFXButton reg_btn;
    UserConfiguration user_config = new UserConfiguration();
    private double Xoffset = 0;
    private double Yoffset = 0;
    
    @FXML
    void reg_btn_onAction(ActionEvent event) throws IOException {
        Parent register_page;
        register_page = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene register_page_scene = new Scene(register_page);
        Stage page_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        setDraggedForm(register_page, page_stage);
        page_stage.setScene(register_page_scene);
        page_stage.show();
    }
    
    @FXML
    void login_in_action(ActionEvent event) {
        if(username_field.getText() == "" || username_field.getText().length() < 5)
        {
            warning_dialog(new Text("Username Invalid"), new Text("Something went wrong\n" + 
                    "With your input information\n" + "Please check again and make sure\n" + 
                    "it's a valid information\n"));
        }
        else if(password_field.getText() == "" || password_field.getText().length() < 4)
        {
            warning_dialog(new Text("Password Invalid"), new Text("Something went wrong\n" + 
                    "Your password input is invalid password\n" + 
                    "Make sure it's your really password\n"));
        }
        else
        {
            try
            {
                if(this.user_config.login(username_field.getText(), password_field.getText()))
                {
                    // Testing the database connection
                    //warning_dialog(new Text("Login Success"), new Text("Nice Work"));
                    
                    // Go to Dashboard Page
                    System.out.print(true);
                    Parent register_page;
                    FXMLLoader loader_page = new FXMLLoader();
                    register_page = loader_page.load(getClass().getResource("Dashboard.fxml"));
                    
                    // Get Page Controller and set the value
                    DashboardController dash_board = loader_page.getController();
                    dash_board.setUserID(this.user_config.getUserID());
                    
                    // Set View to Scene and replace latest view with newer and show it
                    Scene register_page_scene = new Scene(register_page);
                    Stage page_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    setDraggedForm(register_page, page_stage);
                    page_stage.setScene(register_page_scene);
                    page_stage.show();
                }
            }
            catch (Exception e)
            {
                warning_dialog(new Text("Something went wrong"), new Text("Please check your information again \n" + 
                        "It may caused by your invalid Password or Username"));
            }
        }

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
    private void warning_dialog(Text text_header, Text body_message)
    {
        JFXDialogLayout warning_dialog_layout = new JFXDialogLayout();
            warning_dialog_layout.setHeading(text_header);
            warning_dialog_layout.setBody(body_message);
            JFXDialog get_dialog = new JFXDialog(stack_pane, warning_dialog_layout, JFXDialog.DialogTransition.CENTER);
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
