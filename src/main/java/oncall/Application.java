package oncall;

import oncall.controller.OnCallController;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Application {

    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        OnCallController oncallController = initializeComponents();
        oncallController.run();
    }

    private OnCallController initializeComponents() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        return new OnCallController(inputView, outputView);
    }
}
