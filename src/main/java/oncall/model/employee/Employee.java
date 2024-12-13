package oncall.model.employee;

import oncall.model.ErrorCode;

public class Employee {

    public static final int NICKNAME_MAX_LENGTH = 5;

    private final String nickname;

    private Employee(String nickname) {
        validateLength(nickname);
        this.nickname = nickname;
    }

    public static Employee valueOf(String nickname) {
        return new Employee(nickname);
    }

    public String getNickname() {
        return nickname;
    }

    private void validateLength(String value) {
        if (NICKNAME_MAX_LENGTH < value.length()) {
            throw new IllegalArgumentException(ErrorCode.EMPLOYEE_NICKNAME_LENGTH_OUT_OF_RANGE.getMessage());
        }
    }
}
