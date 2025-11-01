package lotto;

public class LottoConstants {
    private LottoConstants() {
    };

    public static final boolean OCCURRENCE_ERROR = true;

    public static final int LOTTO_PRICE = 1_000;
    public static final int NO_REMAINDER = 0;

    public static final String INPUT_PURCHASE_AMOUNT_GUIDE_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String PURCHASE_SUFFIX_MESSAGE = "개를 구매했습니다.";

    public static final String MULTIPLE_OF_UNIT_ERROR_MESSAGE = "[ERROR] 1,000원 단위로 입력해야 합니다.";
    public static final String NOT_POSITIVE_INTEGER_ERROR_MESSAGE = "[ERROR] 입력은 양의 정수여야 합니다.";
}
