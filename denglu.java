
package com.txl.jm;
import com.sun.java.swing.plaf.motif.MotifBorders;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.jiemain;

public class denglu extends Application {
    public void start(final Stage primaryStage){
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(50));
        pane.setHgap(5);
        pane.setVgap(5);

        final TextField yhm = new TextField();
        final TextField mm = new TextField();

        pane.add(new Label("用户名"),0,0);
        pane.add(yhm,1,0);

        pane.add(new Label("密码"),0,1);
        pane.add(mm,1,1);
        Button button1 =ButtonBuildler.create().text("登录").onAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                String a = yhm.getText();
                String b = mm.getText();
                if("8".equals(a)&&"8".equals(b)){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            new jiemain().start(new Stage());
                        }
                    });
                }else{
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            new jiemain().start(new Stage());
                        }
                    });
                }
                primaryStage.close();
                System.out.println("button clicked");
            }
        }
        ).build();
        pane.add(button1,2,3);
        Scene scene = new Scene(pane);
        primaryStage.setTitle("通讯录");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args){
        Application.launch(args);
    }
}
//创建用户

class person{
    String id;
    String dh;
    String xm;
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getDh(){
        return dh;
    }
    public void setDh(String dh){
        this.dh = dh;
    }
    public String getXm(){
        return xm;
    }
    public void setXm(String xm){
        this.xm = xm;
    }
    public void people(String xm,String dh,String id){
        this.setDh(dh);
        this.setId(id);
        this.setXm(xm);
    }
}
