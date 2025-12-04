import controller.DictionaryController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.DictionaryView;

public class DictionaryGUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        DictionaryController controller = new DictionaryController();
        DictionaryView view = new DictionaryView(controller);
        view.start(primaryStage);
    }
}