package oncall.model.enums;

import java.util.Arrays;

public enum Day {
    SUNDAY(1, "일"),
    MONDAY(2, "월"),
    TUESDAY(3, "화"),
    WEDNESDAY(4, "수"),
    THURSDAY(5, "목"),
    FRIDAY(6, "금"),
    SATURDAY(7, "토");

    private int index;
    private String name;

    Day(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public Day getNextDay() {
        return Arrays.stream(Day.values())
                .filter(day -> day.isIndex(index + 1))
                .findFirst()
                .orElse(SUNDAY);
    }

    public boolean isWeekend() {
        return this.equals(SATURDAY) || this.equals(SUNDAY);
    }

    private boolean isIndex(int value) {
        return index == value;
    }
}
