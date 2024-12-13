package oncall.model;

import oncall.model.employee.Employee;

import java.util.LinkedList;
import java.util.List;

public class EmployeeStack {

    private final List<Employee> employees;
    private final LinkedList<Employee> stack;

    private EmployeeStack(List<Employee> employees) {
        this.employees = employees;
        this.stack = new LinkedList<Employee>(employees);
    }

    public static EmployeeStack from(List<Employee> employeeList) {
        return new EmployeeStack(employeeList);
    }

    public Employee pop() {
        Employee firstEmployee = stack.pop();
        restoreStackIfEmpty();

        return firstEmployee;
    }

    public Employee popSecond() {
        Employee secondEmployee = stack.get(1);
        stack.remove(1);
        restoreStackIfEmpty();

        return secondEmployee;
    }

    private void restoreStackIfEmpty() {
        if (stack.isEmpty()) {
            stack.addAll(employees);
        }
    }
}
