package oncall.model.employee;

import java.util.List;

public class Employees {
    private final List<Employee> employees;

    private Employees(List<Employee> employees) {
        this.employees = employees;
    }

    public static Employees ofValue(List<String> nicknames) {
        return new Employees(
                nicknames.stream()
                        .map(Employee::ofValue)
                        .toList()
        );

    }
}
