package oncall.dto;

import oncall.model.Day;

public record MonthDayDTO(
        int month,
        Day day
) {
}
