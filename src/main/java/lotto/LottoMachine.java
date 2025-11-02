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

    private Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
                .stream()
                .sorted()
                .toList();
        return new Lotto(numbers);
    }

}
