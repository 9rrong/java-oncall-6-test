package oncall.model.oncallorder;

import oncall.model.ErrorCode;
import oncall.model.employee.Employee;

import java.util.HashSet;
import java.util.List;

public class OnCallOrder {
    public static final String ON_CALL_ORDER_SUMMARY_FORMAT = "%d월 %d일 %s %s";

    private final List<Employee> onCallOrder;

    private OnCallOrder(List<Employee> onCallOrder) {
        this.onCallOrder = onCallOrder;
    }

    public static OnCallOrder from(List<String> nicknames) {
        checkDuplicate(nicknames);
        checkCountInRange(nicknames);

        return new OnCallOrder(
                nicknames.stream()
                        .map(Employee::valueOf)
                        .toList()
        );
    }

    private static void checkDuplicate(List<String> nicknames) {
        if (nicknames.size() != new HashSet<>(nicknames).size()) {
            throw new IllegalArgumentException(ErrorCode.EMPLOYEE_NICKNAME_DUPLICATED.getMessage());
        }
    }

    private static void checkCountInRange(List<String> nicknames) {
        if (nicknames.size() < 5 || 35 < nicknames.size()) {
            throw new IllegalArgumentException(ErrorCode.EMPLOYEE_COUNT_OUT_OF_RANGE.getMessage());
        }
    }

    public List<Employee> getOnCallOrder() {
        return onCallOrder;
    }

    public boolean hasEqualParticipantsTo(OnCallOrder compareOrder) {
        return new HashSet<>(onCallOrder.stream()
                .map(Employee::getNickname)
                .toList()
        )
                .containsAll(
                        new HashSet<>(compareOrder.getOnCallOrder().stream()
                                .map(Employee::getNickname)
                                .toList()
                        )
                );
    }
}
