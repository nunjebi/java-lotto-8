package lotto;

import java.util.Map;
import java.util.EnumMap;

public class LottoResult {
    private final static int INITIAL_COUNT = 0;
    private final static int INCREMENT_COUNT = 1;

    private final Map<WinningRank, Integer> result = new EnumMap<>(WinningRank.class);

    public void insert(long matchCount, boolean bonusMatch) {
        WinningRank winningRank = WinningRank.getRank(matchCount, bonusMatch);
        if (winningRank != WinningRank.MISS) {
            result.put(winningRank, result.getOrDefault(winningRank, INITIAL_COUNT) + INCREMENT_COUNT);
        }
    }

    public Map<WinningRank, Integer> getResult() {
        return result;
    }
}
