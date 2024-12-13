package oncall.model;

public enum ErrorCode {
    DAY_NOT_FOUND("잘못된 요일 입력입니다. 다시 입력해 주세요."),
    MONTH_NOT_FOUND("잘못된 월 입력입니다. 다시 입력해 주세요."),
    EMPLOYEE_NICKNAME_LENGTH_OUT_OF_RANGE("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    EMPLOYEE_NICKNAME_DUPLICATED("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    EMPLOYEE_COUNT_OUT_OF_RANGE("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    INVALID_INPUT("유효하지 않은 입력 값입니다. 다시 입력해 주세요.");


    private static final String ERROR_PREFIX = "[ERROR] ";

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}

