package oncall.controller;

import oncall.dto.MonthDayDTO;
import oncall.model.InputParser;
import oncall.model.OncallOrder;
import oncall.model.OncallOrders;
import oncall.view.InputView;
import oncall.view.OutputView;

import java.util.function.Supplier;

public class OncallController {

    private final InputView inputView;
    private final OutputView outputView;

    public OncallController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        MonthDayDTO monthDayDTO = retryUntilValid(() -> InputParser.parseMonthDay(inputView.askMonthAndDay()));
        OncallOrders oncallOrders = retryUntilValid(() ->
                OncallOrders.ofValue(
                        OncallOrder.ofValue(InputParser.parseOncallOrder(inputView.askWeekdayOncallOrder())),
                        OncallOrder.ofValue(InputParser.parseOncallOrder(inputView.askWeekendOncallOrder()))
                )
        );
    }

    private <T> T retryUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
