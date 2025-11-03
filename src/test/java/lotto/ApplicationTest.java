package lotto;

import camp.nextstep.edu.missionutils.test.NsTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static lotto.LottoConstants.*;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다.");
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45));
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @ParameterizedTest
    @DisplayName("입력된 구입금액이 올바른 값인 경우")
    @ValueSource(strings = { "1000", "2000", "10000", "100000000" })
    void 구입금액의_입력이_올바른_경우는_예외가_발생하지_않는다(String purchaseAmount) {
        assertThatCode(
                () -> Application.validateInputPurchaseAmount(purchaseAmount))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("입력된 구입금액이 양의 정수가 아닌 경우")
    @ValueSource(strings = { " ", "a,", "a, ", "-1" })
    void 구입금액이_양의_정수가_아닌_경우_예외가_발생한다(String purchaseAmount) {
        assertThatThrownBy(
                () -> Application.validateInputPurchaseAmount(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(NOT_NUMBER_ERROR_MESSAGE);
    }

    @ParameterizedTest
    @DisplayName("입력된 구입금액이 1,000원 단위가 아닌 경우")
    @ValueSource(strings = { "1010", "910" })
    void 구입금액이_1000원_단위가_아닌_경우_예외가_발생한다(String purchaseAmount) {
        assertThatThrownBy(
                () -> Application.validateInputPurchaseAmount(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MULTIPLE_OF_UNIT_ERROR_MESSAGE);
    }

    @Override
    public void runMain() {
        Application.main(new String[] {});
    }
}
