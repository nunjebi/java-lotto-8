package lotto;

public class LottoConstants {
    private LottoConstants() {
    };

    public static final boolean OCCURRENCE_ERROR = true;
    public static final boolean DEFAULT_BONUS_MATCH_STATUS = true;

    public static final int LOTTO_PRICE = 1_000;
    public static final int NO_REMAINDER = 0;
    public static final int MAX_WINNING_NUMBERS_LENGTH = 6;
    public static final int MIN_PURCHASE_LOTTO_COUNT = 0;
    public static final int MIN_LOTTO_NUMBER_RANGE = 1;
    public static final int MAX_LOTTO_NUMBER_RANGE = 45;

    public static final int MATCH_MISS = 0;
    public static final int MATCH_THREE = 3;
    public static final int MATCH_FOUR = 4;
    public static final int MATCH_FIVE = 5;
    public static final int MATCH_SIX = 6;
    public static final int MONEY_MISS = 0;
    public static final int MONEY_FIFTH = 5_000;
    public static final int MONEY_FOURTH = 50_000;
    public static final int MONEY_THIRD = 1_500_000;
    public static final int MONEY_SECOND = 30_000_000;
    public static final int MONEY_FIRST = 2_000_000_000;
    public static final int PERCENTAGE = 100;
    public static final int ROUND_SCALE = 10;

    public static final String GET_POSITIVE_INTEGER_REGEX = "^[1-9]\\d*$";
    public static final String LOTTO_NUMBER_DELIMITER = ",";
    public static final String LOTTO_NUMBER_PATTERN = "\\d+";
    public static final String EMPTY_MESSAGE = "";
    public static final String WINNING_RANK_BONUS_MESSAGE = ", 보너스 볼 일치";
    public static final String WINNING_RANK_RESULT_FORMAT = "%d개 일치%s (%s원) - %d개";
    public static final String PRINT_MONEY_FORMAT = "%,d";

    public static final String INPUT_PURCHASE_AMOUNT_GUIDE_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String PURCHASE_SUFFIX_MESSAGE = "개를 구매했습니다.";
    public static final String INPUT_WINNING_NUMBERS_GUIDE_MESSAGE = "당첨 번호를 입력해 주세요.";
    public static final String INPUT_BONUS_WINNING_NUMBER_GUIDE_MESSAGE = "보너스 번호를 입력해 주세요.";
    public static final String PRINT_STATS_GUIDE_MESSAGE = "당첨 통계\n---";
    public static final String PRINT_LOTTO_PROFIT_FORMAT_MESSAGE = "총 수익률은 %.1f%%입니다.";

    public static final String MULTIPLE_OF_UNIT_ERROR_MESSAGE = "[ERROR] 1,000원 단위로 입력해야 합니다.";
    public static final String NOT_POSITIVE_INTEGER_ERROR_MESSAGE = "[ERROR] 입력은 양의 정수여야 합니다.";
    public static final String NOT_NUMBER_ERROR_MESSAGE = "[ERROR] 입력은 양의 정수여야 합니다.";
    public static final String NUMBER_RANGE_ERROR_MESSAGE = "[ERROR] 입력은 1부터 45사이의 정수여야 합니다.";
    public static final String WINNING_NUMBER_COUNT_ERROR_MESSAGE = "[ERROR] 로또 번호는 6개여야 합니다.";
    public static final String NOT_UNIQUE_NUMBER_ERROR_MESSAGE = "[ERROR] 중복된 수가 존재합니다.";
}
