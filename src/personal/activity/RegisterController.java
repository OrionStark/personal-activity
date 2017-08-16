/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personal.activity;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author orion_stark
 */
public class RegisterController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private double Xoffset = 0;
    private double Yoffset = 0;
    UserConfiguration user_config = new UserConfiguration();
    @FXML
    private StackPane stack_pane;
    
    @FXML
    private JFXTextField name_field;

    @FXML
    private JFXTextField username_field;

    @FXML
    private JFXTextField email_field;

    @FXML
    private JFXPasswordField password_field;

    @FXML
    private JFXPasswordField password_confirm;

    @FXML
    private JFXCheckBox terms_conditions;

    @FXML
    private JFXButton register_btn;
    
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
    
    public boolean isUsernameFieldFIll()
    {
        if(this.username_field.getText().isEmpty() || "".equals(this.username_field.getText()) ||
                this.username_field.getText().length() < 4)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean isNameFieldFill()
    {
        if(this.name_field.getText().isEmpty() || "".equals(this.name_field.getText()) || 
        this.name_field.getText().length() < 3)
        {
            return false;            
        }        
        else
        {
            return true;
        }
    }

    public boolean isPasswordFill()
    {
        if(this.password_field.getText().isEmpty() || 
        "".equals(this.password_field.getText()) || this.password_field.getText().length() < 6)
        {
            return false;
        }
        else
        {
            return true;
        }
    }    
    public boolean isConfirmedPassword()
    {
        if(this.password_confirm.getText().isEmpty() || 
        "".equals(this.password_confirm.getText()) || this.password_confirm.getText().length() < 6)
        {
            return false;
        }
        else
        {
            if(!(this.password_confirm.getText().equals(this.password_field.getText())))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
    }
    public boolean isCheckedTC()
    {
        if(this.terms_conditions.isSelected())
        {
            return true;
        }
        else
        {
            return false;
        }
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
    @FXML
    void register_pressed(ActionEvent event) throws IOException{
        if(isUsernameFieldFIll() && isCheckedTC() && isPasswordFill() && isConfirmedPassword()
                && isNameFieldFill())
        {
            try
            {
                String test = this.user_config.user_register(this.username_field.getText(), this.name_field.getText(), 
                    this.email_field.getText(), this.password_field.getText());
                System.out.print(test);
                Parent register_page;
                register_page = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
                Scene register_page_scene = new Scene(register_page);
                Stage page_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                setDraggedForm(register_page, page_stage);
                page_stage.setScene(register_page_scene);
                page_stage.show();
            }
            catch (IOException | SQLException e)
            {
                System.out.print(e.getMessage());
                warning_dialog(new Text("Something is wrong!"), new Text("We can't connect to any databases\n" + 
                        "We recommend to restart this aplication and run it again\n" + 
                        "If it's not going well please report to\n" + 
                        "robbybellamy6@gmail.com"));
            
            }
        }
        else
        {
            warning_dialog(new Text("Information Check"), new Text("Some of these fields is not a valid input\n" + 
                    "Please check your information again, \nand for sure check the checkbox button to" + 
                    "If anything is right, maybe you need \nto change your username because\n" + 
                    "it's already used"));
        }
    }
    @FXML
    void closeBtn_onclicked(MouseEvent event) throws IOException {
        Parent register_page;
        register_page = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Scene register_page_scene = new Scene(register_page);
        Stage page_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        setDraggedForm(register_page, page_stage);
        page_stage.setScene(register_page_scene);
        page_stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    
}
