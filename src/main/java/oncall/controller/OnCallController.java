package oncall.controller;

import oncall.dto.MonthDayDTO;
import oncall.model.InputParser;
import oncall.model.oncallorder.OnCallOrder;
import oncall.model.oncallorder.OnCallOrders;
import oncall.view.InputView;
import oncall.view.OutputView;

import java.util.function.Supplier;

public class OnCallController {

    private final InputView inputView;
    private final OutputView outputView;

    public OnCallController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        MonthDayDTO monthDayDTO = retryUntilValid(() -> InputParser.parseMonthDay(inputView.askMonthAndDay()));
        OnCallOrders oncallOrders = retryUntilValid(() ->
                OnCallOrders.of(
                        OnCallOrder.from(InputParser.parseOnCallOrder(inputView.askWeekdayOnCallOrder())),
                        OnCallOrder.from(InputParser.parseOnCallOrder(inputView.askWeekendOnCallOrder()))
                )
        );

        outputView.printOnCallSummary(oncallOrders.generateMonthlyOnCallOrder(monthDayDTO.month(), monthDayDTO.day()));
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
