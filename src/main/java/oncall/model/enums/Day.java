package oncall.model.enums;

public enum Day {
    SUNDAY(1, "일"),
    MONDAY(2, "월"),
    TUESDAY(3, "화"),
    WEDNESDAY(4, "수"),
    THURSDAY(5, "목"),
    FRIDAY(6, "금"),
    SATURDAY(7, "토");

    private final int index;
    private final String name;

    Day(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Day getNextDay() {
        return Day.values()[(index % 7)];
    }

    public boolean isWeekend() {
        return this.equals(SATURDAY) || this.equals(SUNDAY);
    }
}
