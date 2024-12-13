package oncall.dto;

import java.util.List;

public record OncallOrderDTO(
        List<String> weekday,
        List<String> weekend
) {
}
