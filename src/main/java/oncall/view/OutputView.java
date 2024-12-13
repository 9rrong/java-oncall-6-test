package oncall.view;

import java.util.List;

public class OutputView {
    public void printError(String message) {
        System.out.println(message);
    }

    public void printOnCallSummary(List<String> summaries) {
        summaries.forEach(System.out::println);
    }
}
