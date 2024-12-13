package oncall.model.oncallorder;

import oncall.model.Day;
import oncall.model.EmployeeStack;
import oncall.model.ErrorCode;
import oncall.model.Month;

import java.util.ArrayList;
import java.util.List;

public class OnCallOrders {

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
            monthlyOnCallOrder.add(String.format(
                    OnCallOrder.ON_CALL_ORDER_SUMMARY_FORMAT,
                    month.getNumber(),
                    dayNumber,
                    day.getName(),
                    currentNickname));
            previousNickname = currentNickname;
            day = day.getNextDay();
        }

        return monthlyOnCallOrder;
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
