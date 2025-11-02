package lotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import camp.nextstep.edu.missionutils.Randoms;

public class LottoMachine {
    public List<Lotto> buyLottos(int purchaseAmount) {
        int lottoCount = purchaseAmount / LottoConstants.LOTTO_PRICE;
        return IntStream.range(0, lottoCount)
                .mapToObj(index -> generateLotto())
                .collect(Collectors.toList());
    }

    public LottoResult checkWinning(List<Lotto> purchasedLottos, List<Integer> winningNumbers, int bonusWinningNumber) {
        LottoResult result = new LottoResult();

        purchasedLottos.forEach(lotto -> {
            int matchCount = getCountMatches(lotto, winningNumbers);
            boolean bonusMatch = getCountBonusMatches(lotto, bonusWinningNumber);

            result.insert(matchCount, bonusMatch);
        });

        return result;
    }

    private Integer getCountMatches(Lotto lotto, List<Integer> winningNumbers) {
        return (int) lotto.getNumbers().stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private boolean getCountBonusMatches(Lotto lotto, int bonusWinningNumber) {
        return lotto.getNumbers()
                .contains(bonusWinningNumber);
    }

    private Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
                .stream()
                .sorted()
                .toList();
        return new Lotto(numbers);
    }

}
