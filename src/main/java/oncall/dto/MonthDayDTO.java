package oncall.dto;

import oncall.model.enums.Day;
import oncall.model.enums.Month;

public record MonthDayDTO(
        Month month,
        Day day
) {
}
