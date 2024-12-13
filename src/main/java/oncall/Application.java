package oncall;

import oncall.controller.OncallController;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {

    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        OncallController oncallController = initializeComponents();
        oncallController.run();
    }

    private OncallController initializeComponents() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        return new OncallController(inputView, outputView);
    }
}
