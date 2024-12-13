package oncall.model.employee;

import oncall.model.ErrorCode;

import java.util.HashSet;
import java.util.List;

public class Employees {
    private final List<Employee> employees;

    private Employees(List<Employee> employees) {
        this.employees = employees;
    }

    public static Employees ofValue(List<String> nicknames) {
        checkDuplicate(nicknames);
        checkCountInRange(nicknames);

        return new Employees(
                nicknames.stream()
                        .map(Employee::ofValue)
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
}
