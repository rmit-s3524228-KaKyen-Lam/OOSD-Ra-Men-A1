import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The entry point of this game application
 *
 * @author David Limantoro s3503728
 */
public class GameMain extends Application {

    public final static int WIDTH = 900;
    public final static int HEIGHT = 900;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("resources/gameLayout.fxml"));
        primaryStage.setTitle("RA-MEN Game");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        System.out.print("");
    }
}
