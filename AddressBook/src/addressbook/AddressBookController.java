
package addressbook;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.chalmers.cse.dat215.lab1.Presenter;

public class AddressBookController implements Initializable {
    
    @FXML private MenuBar menuBar;
    @FXML private Button newButton;
    @FXML private Button deleteButton;
    @FXML private ListView listOne;
     @FXML private TextField text1;
     @FXML private TextField text2;
     @FXML private TextField text3;
     @FXML private TextField text4;
     @FXML private TextField text5;
     @FXML private TextField text6;
     @FXML private TextField text7;
     
     private Presenter presenter;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        presenter = new Presenter(
        listOne, text1, text2, text3, text4, text5, text6, text7);
        presenter.init();
        
        
        listOne.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                presenter.contactsListChanged();
            }
        });
        
        text1.focusedProperty().addListener(new TextFieldListener(text1));
        text2.focusedProperty().addListener(new TextFieldListener(text2));
        text3.focusedProperty().addListener(new TextFieldListener(text3));
        text4.focusedProperty().addListener(new TextFieldListener(text4));
        text5.focusedProperty().addListener(new TextFieldListener(text5));
        text6.focusedProperty().addListener(new TextFieldListener(text6));
        text7.focusedProperty().addListener(new TextFieldListener(text7));

        
    }
    
    @FXML
    protected void textFieldActionPerformed (ActionEvent event){
        presenter.textFieldActionPerformed(event);
    }
    
    @FXML 
    protected void newButtonActionPerformed (ActionEvent event){
        presenter.newContact();
    }
    
    @FXML 
    protected void deleteButtonActionPerformed (ActionEvent event){
        presenter.removeCurrentContact();
    }
    
    @FXML 
    protected void openAboutActionPerformed(ActionEvent event) throws IOException{
    
        ResourceBundle bundle = java.util.ResourceBundle.getBundle("addressbook/resources/AddressBook");
        Parent root = FXMLLoader.load(getClass().getResource("address_book_about.fxml"), bundle);
        Stage aboutStage = new Stage();
        aboutStage.setScene(new Scene(root));
        aboutStage.setTitle(bundle.getString("about.title.text"));
        aboutStage.initModality(Modality.APPLICATION_MODAL);
        aboutStage.setResizable(false);
        aboutStage.showAndWait();
    }
    
    @FXML 
    protected void closeApplicationActionPerformed(ActionEvent event) throws IOException{
        
        Stage addressBookStage = (Stage) menuBar.getScene().getWindow();
        addressBookStage.hide();
    }
    
    private class TextFieldListener implements ChangeListener<Boolean>{

        private TextField textField;
        
        public TextFieldListener(TextField textField){
            this.textField = textField;
        }
        
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if(newValue){
                presenter.textFieldFocusGained(textField);
            
            }
            else{
                presenter.textFieldFocusLost(textField);
            }
        }        
    }
}
