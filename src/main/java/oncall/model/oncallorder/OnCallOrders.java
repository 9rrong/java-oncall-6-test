package oncall.model.oncallorder;

import oncall.model.enums.Day;
import oncall.model.EmployeeStack;
import oncall.model.enums.ErrorCode;
import oncall.model.enums.Month;

import java.util.ArrayList;
import java.util.List;

public class OnCallOrders {

    public static final String INORDINARY_HOLIDAY_MARK = "(휴일)";
    private final EmployeeStack weekdayEmployeeStack;
    private final EmployeeStack weekendEmployeeStack;

    private OnCallOrders(OnCallOrder weekdayOrder, OnCallOrder weekEndOrder) {
        if (!weekdayOrder.hasEqualParticipantsTo(weekEndOrder)) {
            throw new IllegalArgumentException(ErrorCode.PARTICIPANTS_NOT_IDENTICAL.getMessage());
        }
        this.weekdayEmployeeStack = EmployeeStack.from(weekdayOrder.getOnCallOrder());
        this.weekendEmployeeStack = EmployeeStack.from(weekEndOrder.getOnCallOrder());
    }

    public static OnCallOrders of(OnCallOrder weekdayOrder, OnCallOrder weekEndOrder) {
        return new OnCallOrders(weekdayOrder, weekEndOrder);
    }

    public List<String> generateMonthlyOnCallOrder(Month month, Day day) {
        List<String> monthlyOnCallOrder = new ArrayList<>();
        String previousNickname = "";

        for (int dayNumber = 1; dayNumber <= month.getMaxDays(); dayNumber++) {
            String currentNickname = popFromStack(month, dayNumber, day, previousNickname);
            String dayExpression = getAppropriateDayExpression(month, dayNumber, day);
            monthlyOnCallOrder.add(String.format(
                    OnCallOrder.ON_CALL_ORDER_SUMMARY_FORMAT,
                    month.getNumber(),
                    dayNumber,
                    dayExpression,
                    currentNickname));
            previousNickname = currentNickname;
            day = day.getNextDay();
        }

        return monthlyOnCallOrder;
    }

    private String getAppropriateDayExpression(Month month, int dayNumber, Day day) {
        if (month.isExtraHoliday(dayNumber) && !day.isWeekend()) {
            return day.getName() + INORDINARY_HOLIDAY_MARK;
        }
        return day.getName();
    }

    private String popFromStack(Month month, int dayNumber, Day day, String previousNickname) {
        EmployeeStack stack = getAppropriateStack(month, dayNumber, day);
        String nickname = stack.getFirst().getNickname();

        if (nickname.equals(previousNickname)) {
            return stack.popSecond().getNickname();
        }
        return stack.popFirst().getNickname();
    }

    private EmployeeStack getAppropriateStack(Month month, int dayNumber, Day day) {
        if (month.isExtraHoliday(dayNumber) || day.isWeekend()) {
            return weekendEmployeeStack;
        }
        return weekdayEmployeeStack;
    }
}
