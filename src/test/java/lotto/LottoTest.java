package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static lotto.LottoConstants.*;

class LottoTest {

    @Test
    @DisplayName("입력된 당첨 번호가 올바른 경우")
    void 당첨_번호의_입력이_올바른_경우는_예외가_발생하지_않는다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        assertThatCode(
                () -> Lotto.validate(numbers))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("입력된 당첨 번호의 개수가 6개가 아닌 경우")
    void 당첨_번호의_개수가_6개가_아닌_경우는_예외가_발생한다() {
        List<List<Integer>> invalidNumbers = List.of(
                List.of(),
                List.of(1, 2, 3, 4, 4),
                List.of(1, 2, 3, 4, 5),
                List.of(1, 2, 3, 4, 5, 6, 7));
        invalidNumbers.forEach(numbers -> assertThatThrownBy(
                () -> Lotto.validate(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(
                        String.format(WINNING_NUMBER_COUNT_ERROR_MESSAGE, MAX_WINNING_NUMBERS_LENGTH)));
    }

    @Test
    @DisplayName("입력된 당첨 번호에 중복된 수가 있는 경우")
    void 당첨_번호에_중복된_수가_존재할_경우_예외가_발생한다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);
        assertThatThrownBy(
                () -> Lotto.validate(numbers))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(NOT_UNIQUE_NUMBER_ERROR_MESSAGE);
    }
}
