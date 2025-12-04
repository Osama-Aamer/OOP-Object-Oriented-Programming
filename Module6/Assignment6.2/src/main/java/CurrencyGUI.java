import controller.CurrencyController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.CurrencyView;

public class CurrencyGUI extends Application {
    @Override
    public void start(Stage stage) {
        CurrencyController controller = new CurrencyController();
        CurrencyView view = new CurrencyView(controller);
        view.start(stage);
    }
}