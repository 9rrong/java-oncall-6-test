package oncall.model;

public class OncallOrders {
    private final OncallOrder weekdayOrder;
    private final OncallOrder weekEndOrder;

    private OncallOrders(OncallOrder weekdayOrder, OncallOrder weekEndOrder) {
        if (!weekdayOrder.hasEqualParticipantsTo(weekEndOrder)) {
            throw new IllegalArgumentException(ErrorCode.PARTICIPANTS_NOT_IDENTICAL.getMessage());
        }

        this.weekdayOrder = weekdayOrder;
        this.weekEndOrder = weekEndOrder;
    }

    public static OncallOrders ofValue(OncallOrder weekdayOrder, OncallOrder weekEndOrder) {
        return new OncallOrders(weekdayOrder, weekEndOrder);
    }
}
