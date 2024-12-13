package oncall.model;

import java.util.Arrays;
import java.util.List;

public enum Month {
    JANUARY(1, 31, List.of(1)), FEBRUARY(2, 28, List.of()), MARCH(3, 31, List.of(1)), APRIL(4, 30, List.of()), MAY(5, 31, List.of(5)), JUNE(6, 30, List.of(6)), JULY(7, 31, List.of()), AUGUST(8, 30, List.of(15)), SEPTEMBER(9, 31, List.of()), OCTOBER(10, 30, List.of(3, 9)), NOVEMBER(11, 31, List.of()), DECEMBER(12, 30, List.of(25));

    private int number;
    private int maxDays;
    private List<Integer> extraHoliday;

    Month(int number, int maxDays, List<Integer> extraHoliday) {
        this.number = number;
        this.maxDays = maxDays;
        this.extraHoliday = extraHoliday;
    }

    public static Month ofValue(int value) {
        return Arrays.stream(Month.values())
                .filter(month -> month.isNumber(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.MONTH_NOT_FOUND.getMessage()));
    }

    public boolean isNumber(int value) {
        return number == value;
    }
}
