package oncall.model;

import oncall.dto.MonthDayDTO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {
    private static final String INPUT_DELIMITER = ",";
    private static final String MONTH_DAY_REGEX = "([0-9]+)" + INPUT_DELIMITER + "([가-힣]+)";
    private static final Pattern MONTH_DAY_PATTERN = Pattern.compile(MONTH_DAY_REGEX);

    public static MonthDayDTO parseMonthDay(String input) {
        Matcher matcher = MONTH_DAY_PATTERN.matcher(input);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(ErrorCode.INVALID_INPUT.getMessage());
        }

        int month = parseMonth(matcher);

        Day day = parseDay(matcher);

        return new MonthDayDTO(month, day);
    }

    private static int parseMonth(Matcher matcher) {
        try {
            return Integer.parseInt(matcher.group(1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorCode.INVALID_INPUT.getMessage());
        }
    }

    private static Day parseDay(Matcher matcher) {
        try {
            return Day.valueOf(matcher.group(2));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorCode.INVALID_INPUT.getMessage());
        }
    }

}

