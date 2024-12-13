package oncall.model.oncallorder;

import oncall.model.Day;
import oncall.model.EmployeeStack;
import oncall.model.ErrorCode;
import oncall.model.Month;

import java.util.ArrayList;
import java.util.List;

public class OnCallOrders {
    private static final String ON_CALL_ORDER_SUMMARY_FORMAT = "%d월 %d일 %s %s";

    private final OnCallOrder weekdayOrder;
    private final OnCallOrder weekendOrder;
    private final EmployeeStack weekdayEmployeeStack;
    private final EmployeeStack weekendEmployeeStack;

    private OnCallOrders(OnCallOrder weekdayOrder, OnCallOrder weekEndOrder) {
        if (!weekdayOrder.hasEqualParticipantsTo(weekEndOrder)) {
            throw new IllegalArgumentException(ErrorCode.PARTICIPANTS_NOT_IDENTICAL.getMessage());
        }

        this.weekdayOrder = weekdayOrder;
        this.weekendOrder = weekEndOrder;
        this.weekdayEmployeeStack = EmployeeStack.from(weekdayOrder.getOnCallOrder());
        this.weekendEmployeeStack = EmployeeStack.from(weekendOrder.getOnCallOrder());
    }

    public static OnCallOrders of(OnCallOrder weekdayOrder, OnCallOrder weekEndOrder) {
        return new OnCallOrders(weekdayOrder, weekEndOrder);
    }

    public List<String> generateMonthlyOnCallOrder(Month month, Day day) {

        List<String> monthlyOnCallOrder = new ArrayList<>();
        int maxDays = month.getMaxDays();

        for (int dayNumber = 1; dayNumber <= maxDays; dayNumber++) {

            String nickname = getNameFromStack(month, dayNumber, day);

            monthlyOnCallOrder.add(String.format(ON_CALL_ORDER_SUMMARY_FORMAT, month.getNumber(), dayNumber, day.getName(), nickname));
            day = day.getNextDay();
        }

        return monthlyOnCallOrder;
    }

    private String getNameFromStack(Month month, int dayNumber, Day day) {
        if (month.isExtraHoliday(dayNumber) || day.isWeekend()) {
            return weekendEmployeeStack.pop().getNickname();
        }
        return weekdayEmployeeStack.pop().getNickname();
    }

}
