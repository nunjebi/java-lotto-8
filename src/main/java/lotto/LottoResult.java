package lotto;

import java.util.Map;
import java.util.EnumMap;

public class LottoResult {
    private final static int INCREMENT_COUNT = 1;

    private final Map<WinningRank, Integer> result = new EnumMap<>(WinningRank.class);

    public void insert(long matchCount, boolean bonusMatch) {
        WinningRank winningRank = WinningRank.getRank(matchCount, bonusMatch);
        result.put(winningRank, result.get(winningRank) + INCREMENT_COUNT);
    }

    public Map<WinningRank, Integer> getResult() {
        return result;
    }

    public Long getTotalProfit() {
        return result.entrySet()
                .stream()
                .mapToLong(entry -> (long) entry.getKey().getMoney() * entry.getValue())
                .sum();
    }
}
