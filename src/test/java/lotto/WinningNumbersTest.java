package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static lotto.LottoConstants.*;

class WinningNumbersTest {

    @Test
    @DisplayName("입력된 당첨 번호가 올바른 경우")
    void 당첨_번호의_입력이_올바른_경우는_예외가_발생하지_않는다() {
        String winningNumbers = "1,2,3,4,5,6";
        assertThatCode(
                () -> WinningNumbers.validateInputWinningNumbers(winningNumbers))
                .doesNotThrowAnyException();

    }

    @ParameterizedTest
    @DisplayName("입력된 당첨 번호가 양의 정수가 아닌 경우")
    @ValueSource(strings = { "1,2,3,", " ", "1,,", "" })
    void 입력된_당첨_번호가_양의_정수가_아닌_경우는_예외가_발생한다(String winningNumbers) {
        assertThatThrownBy(
                () -> WinningNumbers.validateInputWinningNumbers(winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NOT_NUMBER_ERROR_MESSAGE);
    }

    @ParameterizedTest
    @DisplayName("입력된 당첨 번호가 범위를 벗어난 경우")
    @ValueSource(strings = { "00", "1111111111111111111" })
    void 입력된_당첨_번호가_범위를_벗어난_경우_예외가_발생한다(String winningNumbers) {
        assertThatThrownBy(
                () -> WinningNumbers.validateInputWinningNumbers(winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(
                        String.format(NUMBER_RANGE_ERROR_MESSAGE, MIN_LOTTO_NUMBER_RANGE, MAX_LOTTO_NUMBER_RANGE));
    }

    @Test
    @DisplayName("입력된 보너스 번호가 올바른 경우")
    void 보너스_번호의_입력이_올바른_경우는_예외가_발생하지_않는다() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        String bonusWinningNumber = "7";
        assertThatCode(
                () -> WinningNumbers.validateBonusWinningNumber(winningNumbers, bonusWinningNumber))
                .doesNotThrowAnyException();

    }

    @ParameterizedTest
    @DisplayName("입력된 보너스 번호가 양의 정수가 아닌 경우")
    @ValueSource(strings = { " ", "-1", "1,", "", "1,2" })
    void 입력된_보너스_번호가_양의_정수가_아닌_경우는_예외가_발생한다(String bonusWinningNumber) {
        List<Integer> winningNumbers = List.of(11, 12, 13, 14, 15, 16);
        assertThatThrownBy(
                () -> WinningNumbers.validateBonusWinningNumber(winningNumbers, bonusWinningNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NOT_NUMBER_ERROR_MESSAGE);
    }

    @ParameterizedTest
    @DisplayName("입력된 보너스 번호가 범위를 벗어난 경우")
    @ValueSource(strings = { "00", "1111111111111111111" })
    void 입력된_보너스_번호가_범위를_벗어난_경우_예외가_발생한다(String bonusWinningNumber) {
        List<Integer> winningNumbers = List.of(11, 12, 13, 14, 15, 16);
        assertThatThrownBy(
                () -> WinningNumbers.validateBonusWinningNumber(winningNumbers, bonusWinningNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(
                        String.format(NUMBER_RANGE_ERROR_MESSAGE, MIN_LOTTO_NUMBER_RANGE, MAX_LOTTO_NUMBER_RANGE));
    }

    @Test
    @DisplayName("입력된 보너스 번호가 중복된 경우")
    void 입력된_보너스_번호가_중복된_경우_예외가_발생한다() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        String bonusWinningNumber = "1";
        assertThatThrownBy(
                () -> WinningNumbers.validateBonusWinningNumber(winningNumbers, bonusWinningNumber))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(NOT_UNIQUE_NUMBER_ERROR_MESSAGE);
    }

}
