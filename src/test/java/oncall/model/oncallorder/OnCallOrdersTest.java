package oncall.model.oncallorder;

import oncall.model.enums.Day;
import oncall.model.enums.Month;
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

    @Test
    void 평일이면서_법정공휴일인_경우_휴일_표기테스트() {
        OnCallOrder weekdayOrder = OnCallOrder.from(List.of("영", "일", "이", "삼", "사"));
        OnCallOrder weekendOrder = OnCallOrder.from(List.of("사", "삼", "이", "일", "영"));
        OnCallOrders onCallOrders = OnCallOrders.of(weekdayOrder, weekendOrder);

        assertThat(onCallOrders.generateMonthlyOnCallOrder(Month.JANUARY, Day.MONDAY))
                .contains("1월 1일 월(휴일) 사", "1월 2일 화 영", "1월 3일 수 일");
    }

}