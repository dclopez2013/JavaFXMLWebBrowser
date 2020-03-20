/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webproject;

import java.net.URI;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

/**
 *
 * @author dclop
 */
public class WebDocController implements Initializable {
    
    //FXML items
    @FXML
    private Button back;
    
    @FXML
    private Button forward;
    
    @FXML
    private Button refresh;
    
    @FXML
    private Button home;
    
    @FXML
    private Button gotoLink;
    
    @FXML
    private Button setHomePage;
    
    @FXML
    private WebView wv;
    
    private WebEngine we;
    
    private WebHistory wh;
    
    private String pre = "https://";
    @FXML
    private TextField URL;
    // Eend FXML items
    
    //variables
    private URI url;
    private String sUrl;
    private String homeUrl=pre+"www.google.com";
    private String homeUrl2=pre+"www.youtube.com";
    //end variables
    
    
    //FXML Methods
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        wv.getEngine().load(homeUrl);
        
        we = wv.getEngine();
        wh = we.getHistory();
        this.URL.setText(we.getLocation());
    }    
    
    
    //methods
    public void gotoLink(){
        sUrl = this.URL.getText();
        if(!sUrl.isEmpty()){
        we.load(pre+sUrl);
        }
        else{
        we.load(pre+homeUrl);
        this.URL.setText(we.getLocation());
        }
    }
        
        //direct web engine to home page
    public void goHome(){
        this.wv.getEngine().load(homeUrl);
        this.URL.setText(we.getLocation());
    }
    
    //refresh current page
    public void refresh(){
        this.wv.getEngine().reload();
        this.URL.setText(we.getLocation());
    }
    
    //set location and name of desired home page
    public void setHome(){
        TextInputDialog dialog = new TextInputDialog("walter");
        dialog.setTitle("Set Home Page");
        dialog.setHeaderText("Set Home Page");
        dialog.setContentText("Please enter your new home page:");
        Optional<String> rslt = dialog.showAndWait();
        
        this.homeUrl = rslt.toString();
        
    }
    
    //set url to be visited by web engine
    public void setUrl(String s){
        
        this.sUrl = pre+s;
    }
    
    // direct webengine/webview to go back one location
    public void goBack(){
        if(wh.getEntries().size()>1){
        wh.go(-1);
        this.URL.setText(we.getLocation());
        }
        else{
            this.goHome();
            
        }
    }
    
    // direct webengine/webview to go forwards one location
    public void goForward(){
        if(wh.getEntries().size()>1){
            wh.go(+1);
            this.URL.setText(we.getLocation());
        }
        else{
            this.goHome();
        }
    }


    
    
}
