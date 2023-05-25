package sample;

import com.txl.jm.person;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class jiemain extends Application {
    javafx.scene.control.TextField cx = new javafx.scene.control.TextField();
    javafx.scene.control.TextField mz = new javafx.scene.control.TextField();
    javafx.scene.control.TextField xh = new javafx.scene.control.TextField();
    javafx.scene.control.TextField dh = new javafx.scene.control.TextField();

    public void start(Stage primaryStage){

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11,12,13,14));
        pane.setHgap(5);
        pane.setVgap(5);

        pane.add(new Label("查询学号"),0,0);
        pane.add(cx,1,0);
        Button button2 = new Button("查询");
        OKHandlerClass handler1 = new OKHandlerClass();
        button2.setOnAction(handler1);
        pane.add(button2,2,0);

        pane.add(new Label("名字："),0,1);
        pane.add(mz,1,1);
        pane.add(new Label("学号："),0,2);
        pane.add(xh,1,2);
        pane.add(new Label("电话："),0,3);
        pane.add(dh,1,3);

        Button button1 = new Button("添加");
        pane.add(button1,2,3);
        OKHandlerClass1 handler2 = new OKHandlerClass1();
        button1.setOnAction(handler2);

        Button button3 = new Button("删除");
        pane.add(button3,2,2);
        OKHandlerClass2 handler3 = new OKHandlerClass2();
        button3.setOnAction(handler3);

        Button button4 = new Button("修改");
        pane.add(button4,2,1);
        OKHandlerClass2 handler4 = new OKHandlerClass2();
        button3.setOnAction(handler4);

        Scene scene1 = new Scene(pane);
        primaryStage.setTitle("通讯录");
        primaryStage.setScene(scene1);
        primaryStage.show();

    }
    class OKHandlerClass implements EventHandler<ActionEvent>{
        public void handle(ActionEvent e){
            search();
            System.out.println("查询button clicked");
        }
    }

    class OKHandlerClass1 implements EventHandler<ActionEvent>{
        public void handle(ActionEvent e){
            tianjia();
            System.out.println("添加button clicked");
        }
    }

    class OKHandlerClass2 implements EventHandler<ActionEvent>{
        public void handle(ActionEvent e){
            delete();
            System.out.println("删除button clicked");
        }
    }

    class OKHandlerClass3 implements EventHandler<ActionEvent>{
        public void handle(ActionEvent e){
            update();
            System.out.println("修改button clicked");
        }
    }

    private void search(){
        com.txl.jm.person ren= new person();
        ren.id = cx.getText();
        mysqljdbc a = new mysqljdbc();
        a.initializeDB();

        try{
            String queryString = "select * from stu where id="+ren.id+"";
            a.preStmt = a.connect.prepareStatement(queryString);
            a.rSet = a.preStmt.executeQuery();
            if(a.rSet.next()){
                String name1 = a.rSet.getString(1);
                String xh1 = a.rSet.getString(2);
                String txl1 = a.rSet.getString(3);
                xh.setText(name1);
                dh.setText(txl1);
                mz.setText(xh1);
            }
            exit1();
        }catch (Exception e){

        }
    }
    private void tianjia(){
        person ren1 = new person();
        ren1.xm = mz.getText();
        ren1.id = xh.getText();
        ren1.dh = dh.getText();

        try{
            mysqljdbc b1 = new mysqljdbc();
            b1.initializeDB();
            String queryString = "insert into stu values(?,?,?)";
            b1.preStmt = b1.connect.prepareStatement(queryString);
            b1.preStmt.setString(2,ren1.xm);
            b1.preStmt.setString(1,ren1.id);
            b1.preStmt.setString(3,ren1.dh);
            b1.preStmt.executeUpdate();
            exit1();
        }catch (Exception e){

        }
    }

    public int delete(){
        person ren2 = new person();
        ren2.id = xh.getText();
        String sql = "delete from stu where id="+ren2.id+"";

        int i = 0;
        try{
            mysqljdbc cMysqljdbc = new mysqljdbc();
            cMysqljdbc.initializeDB();
            cMysqljdbc.preStmt = cMysqljdbc.connect. PreparedStatement(sql);
            i  = cMysqljdbc.preStmt.executeUpdate();
            exit1();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return i;
    }
    public int update(){
        person aPerson = new person();
        aPerson.xm = mz.getText();
        aPerson.id = xh.getText();
        aPerson.dh = dh.getText();
        int i = 0;

        String sql = "update stu set name = ?,txl=?,id=? where id="+aPerson.id+"";
        try{
            mysqljdbc cMysqljdbc = new mysqljdbc();
            cMysqljdbc.initializeDB();
            cMysqljdbc.preStmt = cMysqljdbc.connect.prepareStatement(sql);
            cMysqljdbc.preStemt.setSring(1,aPerson.xm);
            cMysqljdbc.preStemt.setSring(2,aPerson.dh);
            cMysqljdbc.preStemt.setSring(3,aPerson.id);
            i = cMysqljdbc.preStmt.executeUpdate();
            exit1();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return i;
    }
    public void exit1(){
        mysqljdbc a  = new mysqljdbc();
        if(a.rSet != null){
            try{
                a.rSet.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(a.preStmt != null){
            try{
                a.preStmt.close();

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(a.connect != null){
            try{
                a.connect.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
