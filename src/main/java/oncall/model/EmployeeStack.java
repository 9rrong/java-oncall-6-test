package oncall.model;

import java.util.LinkedList;
import java.util.List;

public class EmployeeStack {

    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;

    private final List<Employee> onCallOrder;
    private final LinkedList<Employee> stack;

    private EmployeeStack(List<Employee> onCallOrder) {
        this.onCallOrder = onCallOrder;
        this.stack = new LinkedList<Employee>(onCallOrder);
    }

    public static EmployeeStack from(List<Employee> employeeList) {
        return new EmployeeStack(employeeList);
    }

    public Employee getFirst() {
        return stack.get(FIRST_INDEX);
    }

    public Employee popFirst() {
        Employee firstEmployee = stack.pop();
        restoreStackIfEmpty();

        return firstEmployee;
    }

    public Employee popSecond() {
        Employee secondEmployee = stack.get(SECOND_INDEX);
        stack.remove(1);
        restoreStackIfEmpty();

        return secondEmployee;
    }

    private void restoreStackIfEmpty() {
        if (stack.isEmpty()) {
            stack.addAll(onCallOrder);
        }
    }
}
