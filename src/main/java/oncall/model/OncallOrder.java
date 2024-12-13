package oncall.model;

import oncall.model.employee.Employee;

import java.util.HashSet;
import java.util.List;

public class OncallOrder {
    private final List<Employee> employees;

    private OncallOrder(List<Employee> employees) {
        this.employees = employees;
    }

    public static OncallOrder ofValue(List<String> nicknames) {
        checkDuplicate(nicknames);
        checkCountInRange(nicknames);

        return new OncallOrder(
                nicknames.stream()
                        .map(Employee::ofValue)
                        .toList()
        );
    }

    public boolean hasEqualParticipantsTo(OncallOrder oncallOrder) {
        return new HashSet<>(employees).equals(new HashSet<>(oncallOrder.employees));
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
}
