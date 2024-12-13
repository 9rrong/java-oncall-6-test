package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String MONTH_AND_DAY_PROMPT = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
    private static final String WEEKDAY_ONCALL_ORDER_PROMPT = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
    private static final String WEEKEND_ONCALL_ORDER_PROMPT = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";

    public String askMonthAndDay() {
        System.out.println(MONTH_AND_DAY_PROMPT);
        return Console.readLine();
    }

    public String askWeekdayOncallOrder() {
        System.out.println(WEEKDAY_ONCALL_ORDER_PROMPT);
        return Console.readLine();
    }

    public String askWeekendOncallOrder() {
        System.out.println(WEEKEND_ONCALL_ORDER_PROMPT);
        return Console.readLine();
    }
}
