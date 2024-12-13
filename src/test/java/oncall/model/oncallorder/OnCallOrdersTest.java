package oncall.model.oncallorder;

import oncall.model.Day;
import oncall.model.Month;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class OnCallOrdersTest {

    @Test
    void 연속_근무_순서_변경_테스트() {
        OnCallOrder weekdayOrder = OnCallOrder.from(List.of("중복", "일", "이", "삼", "사"));
        OnCallOrder weekendOrder = OnCallOrder.from(List.of("중복", "일", "이", "삼", "사"));
        OnCallOrders onCallOrders = OnCallOrders.of(weekdayOrder, weekendOrder);

        assertThat(onCallOrders.generateMonthlyOnCallOrder(Month.JANUARY, Day.SUNDAY))
                .contains("1월 1일 일 중복", "1월 2일 월 일", "1월 3일 화 중복");

    }

}