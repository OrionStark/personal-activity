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

import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author orion_stark
 */
public class Today_Act_PageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView close_btn;
    
    @FXML
    public AnchorPane anchor_pane;


    @FXML
    public JFXListView<ActivityCore> activity_list_view;
    
    public ObservableList<ActivityCore> activityObservable;
    
    public void setList(ObservableList<ActivityCore> activity)
    {
        this.activityObservable = activity;
    }
    private void setAct()
    {
        for(int i = 0; i < this.activityObservable.size(); i++)
        {
            Date date = activityObservable.get(i).getDateAct();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            if (Calendar.getInstance().get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR))
            {
                this.activity_list_view.getItems().add(activityObservable.get(i));
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        activity_list_view.setStyle("-fx-background-color: rgba(255,255,255,0.0)");
        setAct();
        activity_list_view.setExpanded(true);
        activity_list_view.setDepth(1);
        //activity_list_view.setItems(activityObservable);
        activity_list_view.setCellFactory(new Callback<ListView<ActivityCore>, javafx.scene.control.ListCell<ActivityCore>>() {
            @Override
            public ListCell<ActivityCore> call(ListView<ActivityCore> param) {
                //System.out.println(new ActivityListViewCell());
                return new ActivityListViewCell();
            }
        });
    }    
    
}
