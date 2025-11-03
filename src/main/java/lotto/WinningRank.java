package lotto;

import static lotto.LottoConstants.*;

public enum WinningRank {
    MISS(MATCH_MISS, false, MONEY_MISS),
    FIFTH(MATCH_THREE, false, MONEY_FIFTH),
    FOURTH(MATCH_FOUR, false, MONEY_FOURTH),
    THIRD(MATCH_FIVE, false, MONEY_THIRD),
    SECOND(MATCH_FIVE, true, MONEY_SECOND),
    FIRST(MATCH_SIX, false, MONEY_FIRST);

    private final int matchCount;
    private final boolean bonusMatch;
    private final int money;

    WinningRank(int matchCount, boolean bonusMatch, int money) {
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public static WinningRank getRank(long count, boolean bonusMatch) {
        if (count == MATCH_SIX)
            return FIRST;
        if (count == MATCH_FIVE && bonusMatch)
            return SECOND;
        if (count == MATCH_FIVE)
            return THIRD;
        if (count == MATCH_FOUR)
            return FOURTH;
        if (count == MATCH_THREE)
            return FIFTH;
        return MISS;
    }

    public String formatResult(int count) {
        String bonusMessage = EMPTY_MESSAGE;
        if (bonusMatch) {
            bonusMessage += WINNING_RANK_BONUS_MESSAGE;
        }

        return String.format(WINNING_RANK_RESULT_FORMAT,
                matchCount, bonusMessage, String.format(PRINT_MONEY_FORMAT, money), count);
    }
}
