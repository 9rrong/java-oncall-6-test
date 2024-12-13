package oncall.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EmployeeTest {

    @ParameterizedTest
    @ValueSource(strings = {"일곱글자아아아", "여섯글자라니"})
    void 닉네임_길이_초과_예외테스트(String input) {
        assertThatThrownBy(() -> Employee.valueOf(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
    }

}