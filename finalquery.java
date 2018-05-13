import java.sql.*;                                    //IMPORTS SQL ALL NEEDE FILES
import java.lang.*;
import javafx.stage.FileChooser;                      //TO CHOOSE FILE
import java.io.*;                                     //FOR INPUT OUPUT STREAM
import com.jfoenix.controls.JFXButton;                //USED FOR FENCY BUTTONS
import com.jfoenix.controls.JFXTextField;             //USED FOR FENCY LABELS
import java.io.File;                                  //USED IN CHOOSING THE FILE 
import java.sql.PreparedStatement;                     //USED FOR PREPARED STATEMENT IN SQL
import javafx.scene.Group;                             //USED TO RELOCATE THE ELEMNETS USING X AND Y
import javafx.application.Application;
import javafx.event.ActionEvent;                       //USED FOR THE ACTION EVENT IN JAVAFX                    
import javafx.scene.Scene;                             //MAIN
import javafx.scene.control.Button;                    
import javafx.scene.control.Label;
import javafx.scene.control.TextField;                //TEXTFILED
import javafx.stage.Stage;                            //MAIN
import javafx.scene.image.Image;                     //USED FOR IMAGES IN SQL DATABASE
import javafx.scene.image.ImageView;                 //USED TO SHOW IMAGES FROM SQL DATA BASE
import javafx.geometry.Insets;                       //USED TO GIVE PADDING BETWEEN ANY ELEMENTS 
import java.io.FileInputStream;                      //USED FOR IMAGE TO EXTRACT FROM DATABASE
import java.io.FileNotFoundException;           

public class finalquery extends Application { 
  
  //ASSIGN THEM PUBLICALLY SO THEY CAN BE ACESS ANYWHERE
  
   byte[] fileBytes;                                //USED TO SORE IMAGE 
   Label NAME,ID,AGE,Result,Back;
   InputStream in;
   Image image,img,im;
   ImageView imageView,imageView1;
   JFXTextField ids,age,name;
   int id,umer;
   String naam,path;
   JFXButton update,insert,clear,choose,show;
   Statement stmt;
   
   @Override 
   public void start(Stage stage) throws FileNotFoundException {  
     
     //MAKING A GROUP BEST THING IN JAVA TO RELOCATE THE ELEMENTS GUI's
     Group group = new Group();
     
     //CREATING OBJECT OF THE CLASS
       Findid f = new Findid();
          
          
     //ASSIGNING IMAGE A VIEW WITH HEIGHT AND WIDTH AND RELOCATING IT
      imageView1 = new ImageView();
      imageView = new ImageView();
      imageView.setImage(img);
      imageView.setFitHeight(100); 
      imageView.setFitWidth(100); 
      imageView1.relocate(600,190);
      
     //ID FIELD=>
     ids = new JFXTextField();
     ids.setLabelFloat(true);
     ids.setPromptText("ID");
     ids.relocate(100,170);
     ids.setOnAction((ActionEvent event) -> {                  //A OBJECT OF THE CLASS IS CREATED TO ACESS THE METhODS FROM THE CLASS
           f.connect();                                        //WHICH IN CASE OUR ACTION EVENT IS DOING
           
     });
     
     //LABEL FOR THE TITLE OF OUR DATABASE
     Back = new Label("Student Database");
     Back.relocate(300,30);
     Back.setPadding(new Insets(5, 10, 10, 10));
     
     //RESULT TITLE FOR SHOWING UPDATED AND INERTED AND CLEAR 
     Result = new Label("NO RESULT TO SHOW");
     Result.setPadding(new Insets(3, 3, 3, 3));
     Result.relocate(100,400);

     //NAME FIELD=>
     name = new JFXTextField();
     name.setLabelFloat(true);
     name.setPromptText("NAME");
     name.relocate(100,240);
     
     //AGE FIELD=>
     age = new JFXTextField();
     age.setLabelFloat(true);
     age.setPromptText("AGE");
     age.relocate(100,310);
     
     //UPDATE BUTTON=>
     update = new JFXButton("Update Now"); 
     update.relocate(550,400);
     update.setOnAction((ActionEvent event) -> {
         f.update();
     });
     
     //CLEAR BUTTON=>
     clear = new JFXButton("Clear Now"); 
     clear.relocate(750,400);
     clear.setOnAction((ActionEvent event) -> {
         f.clear();
     });
     
     //SHOW BUTTON=>
     show = new JFXButton("Show Now"); 
     show.relocate(750,450);
     show.setOnAction((ActionEvent event) -> {
         f.showme();
     });
     
     //INSERT BUTTON=>
     insert = new JFXButton("Insert Now"); 
     insert.relocate(350,400);
     insert.setOnAction((ActionEvent event) -> {
         f.now();
     });

     //CHOOSE BUTTON=>
     choose = new JFXButton("Choose File"); 
     choose.relocate(635,330); 
     choose.setVisible(false);                   //SET TO HIDDEN FOR NOW
     choose.setOnAction((ActionEvent event) -> {
         f.inserted();
     });
    
     //GROUPING ALL THE CHILDREN IN OUR GROUP TOGETHER
      group.getChildren().addAll(ids,name,age,Back,imageView1,clear,insert,update,choose,Result,show);
      
      //GIVING STYLES TO OUR ELEMENTS AND FILEDS
      //imageView.setStyle("-fx-border-width: 5;-fx-border-color: #EBDEF0  ; ");
       Back.setStyle("-fx-font: helvetica bold 39px 'serif'; -fx-text-fill: #884EA0 ; -fx-border-width: 5;-fx-border-color: #EBDEF0  ; "); 
       Result.setStyle("-fx-font: normal bold 21px 'serif'; -fx-text-fill: #A12;-fx-border-width: 5;-fx-border-color:   #fdedec    ; ");
       insert.setStyle("-fx-background-color: #AA3FF4  ; -fx-text-fill: white;");
       clear.setStyle("-fx-background-color: #AA3FF4  ; -fx-text-fill: white;");
       choose.setStyle("-fx-background-color: #AA3FF4  ; -fx-text-fill: white;");
       update.setStyle("-fx-background-color: #AA3FF4  ; -fx-text-fill: white;");
       show.setStyle("-fx-background-color: #AA3FF4  ; -fx-text-fill: white;");
       ids.setStyle("-fx-font: fantasy bold 19px 'serif';");
       name.setStyle("-fx-font: fantasy bold 19px 'serif';");
       age.setStyle("-fx-font: fantasy bold 19px 'serif';");
       
      
      //Creating a scene object 
      Scene scene = new Scene(group,900,500); 
      
      //Setting title to the Stage 
      stage.setTitle("Connected Or not"); 
         
      //Adding scene to the stage 
      stage.setScene(scene);  
      
      //Displaying the contents of the stage 
      stage.show();    
   }

   //A CLASS MADE TO ACESS IT's METHODS FOR OUR ACTION USE FOR ANY GUI's FIELD OR BUTTON etc. 
public class Findid{
  
  public void showme(){
    
  ids.setVisible(true);
  choose.setVisible(false);
  }
  
 public void connect() {
          
    Connection c = null;
    Statement stmt = null;
    int num;
    choose.setVisible(false);
    num=Integer.parseInt(ids.getText());
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ms com\\Desktop\\data\\person.sqlite");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");
      stmt = c.createStatement();
      String str = "SELECT * FROM personDb where id ="+ num + "";
      ResultSet rs = stmt.executeQuery( str);
      while ( rs.next() ) {
        //TAKE ID FROM DATA BASE AND STORE IT TO idkh VARIABLE
          int idkh = rs.getInt("id");
          //TAKE NAME FROM DATABASE AND STORE IT TO naam VARIABLE
          naam = rs.getString("name");
          
          //TAKE THE FILE FROM DATABASE 
          InputStream in = rs.getBinaryStream("image");
          
          //STORE THE IMAGE FILE NOW TO THIS LOCATION SO THAT NEXT IT CAN BE USED
          OutputStream os=  new FileOutputStream(new File("d:\\ood\\test5.jpeg"));
          byte[] content = new byte[1024];
          int size = 0;
          while ((size = in.read(content))!=-1){
            os.write(content,0,size);
          }
          os.close();
          in.close();
         umer  = rs.getInt("age");
         System.out.println( "ID = " + idkh );
         System.out.println( "NAME = " + naam );
         System.out.println();
      }
      Result.setText("Found");
      name.setText(""+naam);
      age.setText(""+umer);
      
      //USING THE FILE WHICH WE STORED EARLIER
      Image im = new Image("file:d:\\ood\\test5.jpeg",150,250,true,true);
      imageView1.setImage(im);
      imageView.setFitHeight(250); 
      imageView.setFitWidth(250); 
      stmt.close();
      c.close();
      
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
    //SHOWING THE IMAGE IN OUR GUI
    System.out.println("Operation done successfully");
          img = new Image("file:d:\\ood\\test5.jpeg");
      ImageView imageView = new ImageView(img);
     }
 
 public void update(){
          
    Connection c = null;                     //CONNECTION DECLARED AS NULL
    Statement stmt = null;
    int num,updateAge;
    num=Integer.parseInt(ids.getText());      //EXTRACTING INT FROM TEXT
    String updateName = name.getText();
    updateAge=Integer.parseInt(age.getText());
    System.out.println(updateName+updateAge+""+num);
    try {
      Class.forName("org.sqlite.JDBC");                              //MAIN LINE FOR SQL DATABASE
      c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ms com\\Desktop\\data\\person.sqlite");   //DATABASE PATH OF OUR SQLITE PATH
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");
      String str = "UPDATE personDb set  name=?,age=? WHERE id="+ num + "";
      PreparedStatement ps = c.prepareStatement(str);   //SETTING THE PREPARED SATETMENT
      ps.setString(1, name.getText());                  //AT PLACE OF 1ST QUESTION MARKS WE USE PREPARED STATEMNENT FOR OUR name
      ps.setInt(2, Integer.parseInt(age.getText()));   //AT PLACE OF 2ND QUESTION MARKS  WE USE PREPARED STATEMNENT FOR OUR age
      ps.executeUpdate();                              //QUERY EXECUTED
      Result.setText("Updated Sucessfully");
      c.commit();
      stmt.close();                                         
      c.close();                                       //CLOSING CONNECTION
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
     }
 
  public void clear(){
    ids.setText("");
    name.setText("");
    age.setText("");
    choose.setVisible(true);      //CHOOSE BUTTON SET TO VISIBLE
    Result.setText("Cleared");
    ids.setVisible(false);        //id FIELD SET TO HIDDEN
  }
  
  public void inserted(){
    
      //FILE CHOOSER MENU WILL OPEN
       FileChooser fileChooser = new FileChooser();
       
     //GIVING CHOICE TO THE USER FOR THE IMAGE OPTION ONLY NOT FOR THE ALL FILES OPTION
       fileChooser.getExtensionFilters().addAll(
       new FileChooser.ExtensionFilter("JPG", "*.jpg"),
        new FileChooser.ExtensionFilter("GIF", "*.gif"),
        new FileChooser.ExtensionFilter("BMP", "*.bmp"),
       new FileChooser.ExtensionFilter("PNG", "*.png")
          );
       File selectedFile = fileChooser.showOpenDialog(null);

       if (selectedFile != null) {            //IF ANY FILE IS SELECTED THEN.

         System.out.println("File selected: " + selectedFile.getPath());
          path =  selectedFile.getPath();
         String escaped = path.replace("\\", "\\\\"); //Path will return c:\a.jpg type so to convert it into c:\\a.jpg we use this.
         System.out.println(escaped);

       }
       else {
         System.out.println("File selection cancelled.");
       }
     }
  
    public void now(){

    Connection c;
    int num,updateAge;
    //num=Integer.parseInt(ids.getText());             //Convert The Text To Integer num variable
    String updateName = name.getText();                
    updateAge=Integer.parseInt(age.getText());
    FileInputStream fis = null;
    PreparedStatement ps = null;
    clear();
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ms com\\Desktop\\data\\person.sqlite");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");
      String str = "INSERT INTO personDb (name,age,image) " +
                   "VALUES (?,?,?);"; 
      //TAKING THE FULL PATH OF THE IMAGE FROM OUR PATH VARIABLE FROM OUR inserted() FUNCTION.
      File file = new File(path);
      //NOW STORING TH BINARY FILE OF OUR IMAGE IN fis VARIABLE
      fis = new FileInputStream(file);
      //MAKING PREPARED STATEMENT
      ps = c.prepareStatement(str);
      ps.setString(1, updateName);
      ps.setInt(2,updateAge);
      ps.setBinaryStream(3, fis, (int) file.length());  //TAKING THE IMAGE AND STORING IT IN OUR DATBASE
      ps.executeUpdate();                                //EXECUTING THE QUERY NOW
      Result.setText("Inserted Sucessfully");
      choose.setVisible(false);
      c.commit();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
         //ID FIELD SET TO VISIBLE NOW
          ids.setVisible(true);
     }
}
public static void main( String args[] )
  {
    launch(args);
  }
}

//SITES TO LEARN JAVA 
  
  //1.zetcode.com
 //2.http://tutorials.jenkov.com/javafx/index.html
  
// SITES TO LEARN DJANGO
  
 // 1.djangobook.com
  //2.https://tutorial.djangogirls.org/en/

































