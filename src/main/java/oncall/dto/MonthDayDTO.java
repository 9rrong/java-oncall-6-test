package oncall.dto;

import oncall.model.Day;
import oncall.model.Month;

public record MonthDayDTO(
        Month month,
        Day day
) {
}
