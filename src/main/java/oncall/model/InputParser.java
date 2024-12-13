package oncall.model;

import oncall.dto.MonthDayDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {
    private static final String INPUT_DELIMITER = ",";
    private static final String MONTH_DAY_REGEX = "([0-9]+)" + INPUT_DELIMITER + "([가-힣]+)";
    private static final Pattern MONTH_DAY_PATTERN = Pattern.compile(MONTH_DAY_REGEX);
    private static final String ON_CALL_ORDER_REGEX = "[가-힣]+" + "(?:" + INPUT_DELIMITER + "[가-힣]+)*";
    private static final Pattern ON_CALL_ORDER_PATTERN = Pattern.compile(ON_CALL_ORDER_REGEX);
    private static final int INPUT_MONTH_INDEX = 1;
    private static final int INPUT_DAY_INDEX = 2;

    public static MonthDayDTO parseMonthDay(String input) {
        Matcher matcher = MONTH_DAY_PATTERN.matcher(input);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(ErrorCode.INVALID_INPUT.getMessage());
        }

        Month month = parseMonth(matcher);
        Day day = parseDay(matcher);

        return new MonthDayDTO(month, day);
    }

    public static List<String> parseOnCallOrder(String input) {
        validateOnCallInputSyntax(input);

        return splitOnCallOrder(input);
    }

    private static List<String> splitOnCallOrder(String input) {
        return List.of(input.split(INPUT_DELIMITER));
    }

    private static void validateOnCallInputSyntax(String input) {
        Matcher matcher = ON_CALL_ORDER_PATTERN.matcher(input);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(ErrorCode.INVALID_INPUT.getMessage());
        }
    }

    private static Month parseMonth(Matcher matcher) {
        try {
            return Month.ofValue(Integer.parseInt(matcher.group(INPUT_MONTH_INDEX)));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorCode.INVALID_INPUT.getMessage());
        }
    }

    private static Day parseDay(Matcher matcher) {
        return Arrays.stream(Day.values())
                .filter(day -> Objects.equals(day.getName(), matcher.group(INPUT_DAY_INDEX)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.INVALID_INPUT.getMessage()));
    }

}

