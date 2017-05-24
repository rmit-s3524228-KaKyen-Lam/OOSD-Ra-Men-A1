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

    private final static int WIDTH = 900;
    private final static int HEIGHT = 900;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // "gameLayout.fxml" as an example of the protected variations principle
        Parent root = FXMLLoader.load(getClass().getResource("resources/configLayout.fxml"));
        primaryStage.setTitle("RA-MEN Game");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        System.out.print("");
    }
}
