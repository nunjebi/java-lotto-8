package lotto;

import java.util.List;
import camp.nextstep.edu.missionutils.Console;

import static lotto.LottoConstants.*;

public class Application {

    public static void main(String[] args) {
        LottoMachine machine = new LottoMachine();

        int purchaseAmount = inputLottoPurchaseAmount();
        List<Lotto> purchasedLottos = machine.buyLottos(purchaseAmount);
        printPurchasedLottos(purchasedLottos);

        WinningNumbers winningNumbers = new WinningNumbers();

        LottoResult lottoResult = machine.checkWinning(purchasedLottos, winningNumbers.getWinningNumbers(),
                winningNumbers.getBonusWinningNumber());
        double lottoProfit = getLottoProfit(purchaseAmount, lottoResult.getTotalProfit());
        printStats(lottoResult, lottoProfit);
    }

    private static int inputLottoPurchaseAmount() {
        while (OCCURRENCE_ERROR) {
            try {
                printInputPurchaseAmountGuide();
                String purchaseAmount = Console.readLine();

                validateInputPurchaseAmount(purchaseAmount);

                return Integer.parseInt(purchaseAmount);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private static void printInputPurchaseAmountGuide() {
        System.out.println(INPUT_PURCHASE_AMOUNT_GUIDE_MESSAGE);
    }

    private static void printPurchasedLottos(List<Lotto> purchasedLottos) {
        System.out.println();
        System.out.println(purchasedLottos.size() + PURCHASE_SUFFIX_MESSAGE);
        purchasedLottos.forEach(lotto -> System.out.println(lotto.getNumbers()));
    }

    private static void printStats(LottoResult lottoResult, double lottoProfit) {
        printStatsGuide();
        printLottoResult(lottoResult);
        printLottoProfit(lottoProfit);
    }

    private static void printStatsGuide() {
        System.out.println();
        System.out.println(PRINT_STATS_GUIDE_MESSAGE);
    }

    private static void printLottoResult(LottoResult lottoResult) {
        lottoResult.getResult()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey() != WinningRank.MISS)
                .forEach(entry -> System.out.println(entry.getKey().formatResult(entry.getValue())));
    }

    private static void printLottoProfit(double lottoProfit) {
        System.out.println(String.format(PRINT_LOTTO_PROFIT_FORMAT_MESSAGE, lottoProfit));
    }

    static void validateInputPurchaseAmount(String purchaseAmount) {
        if (!validatePositiveInteger(purchaseAmount)) {
            throw new IllegalArgumentException(NOT_POSITIVE_INTEGER_ERROR_MESSAGE);
        }

        if (!validateMultipleOfUnit(purchaseAmount)) {
            throw new IllegalArgumentException(MULTIPLE_OF_UNIT_ERROR_MESSAGE);
        }
    }

    private static boolean validatePositiveInteger(String purchaseAmount) {
        return purchaseAmount.matches(GET_POSITIVE_INTEGER_REGEX);
    }

    private static boolean validateMultipleOfUnit(String purchaseAmount) {
        if (Integer.parseInt(purchaseAmount) % LOTTO_PRICE == NO_REMAINDER) {
            return true;
        }
        return false;
    }

    private static double getLottoProfit(int purchaseAmount, long totalProfit) {
        double rate = (double) totalProfit / purchaseAmount * PERCENTAGE;
        return Math.round(rate * ROUND_SCALE) / (double) ROUND_SCALE;
    }
}
