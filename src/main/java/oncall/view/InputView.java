package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String MONTH_AND_DAY_PROMPT = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";

    public String askMonthAndDay() {
        System.out.println(MONTH_AND_DAY_PROMPT);
        return Console.readLine();
    }
}
