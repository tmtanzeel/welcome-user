import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.util.Duration;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class Main extends Application {
  Label welcome;
  Label name;
  Label message;
  Label greetingmessage=null;
  String myname=" "+System.getProperty("user.name")+"!";
  Scene scene;
  GridPane layout;
  Task <Void> shutdowntask;
  @Override
  public void start(Stage primarystage) {
    welcome=new Label("Welcome");
    welcome.getStyleClass().add("welcome");
    name=new Label();
    name.getStyleClass().add("name");
    name.setText(""+myname);
    message=new Label();
    message.getStyleClass().add("message");
    message.setText("Wish you a very "+new Clock().tellPhase());

    layout=new GridPane();
    layout.add(welcome, 0, 0);
    layout.add(name, 2, 0);
    layout.add(message, 0, 2, 4, 1);

    scene=new Scene(layout, 950, 200, Color.TRANSPARENT);
    scene.getStylesheets().add(Main.class.getResource("css/Main.css").toExternalForm());
    primarystage.setScene(scene);
    primarystage.initStyle(StageStyle.TRANSPARENT);
    primarystage.setWidth(950);
    primarystage.setHeight(200);
    Rectangle2D primScreenBounds=Screen.getPrimary().getVisualBounds();
    primarystage.setX((primScreenBounds.getWidth()-primarystage.getWidth())/2);
    primarystage.setY((primScreenBounds.getHeight()-primarystage.getHeight())/2);
    primarystage.show();
    shutdowntask=new Task<Void>() {
      @Override
      protected Void call() {
        try {
            Thread.sleep(3000);
        }
        catch(Exception e) {
          System.out.println("Exception caught: "+e);
        }
        Timeline timeline=new Timeline();
        KeyFrame key=new KeyFrame(Duration.millis(2000), new KeyValue(primarystage.getScene().getRoot().opacityProperty(), 0));
        timeline.getKeyFrames().add(key);
        timeline.setOnFinished((ae)->System.exit(1));
        timeline.play();
        return null;
      }
    };
    new Thread(shutdowntask).start();
  }
  public static void main(String[] args) {
    launch(args);
  }
}
