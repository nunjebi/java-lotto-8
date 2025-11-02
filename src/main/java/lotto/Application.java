package lotto;

import java.util.List;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        LottoMachine machine = new LottoMachine();

        int purchaseAmount = inputLottoPurchaseAmount();
        List<Lotto> purchasedLottos = machine.buyLottos(purchaseAmount);

        printPurchasedLottos(purchasedLottos);

        WinningNumbers winningNumbers = new WinningNumbers();
    }

    private static int inputLottoPurchaseAmount() {
        while (LottoConstants.OCCURRENCE_ERROR) {
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
        System.out.println(LottoConstants.INPUT_PURCHASE_AMOUNT_GUIDE_MESSAGE);
    }

    private static void validateInputPurchaseAmount(String purchaseAmount) {
        if (!validatePositiveInteger(purchaseAmount))
            throw new IllegalArgumentException(LottoConstants.NOT_POSITIVE_INTEGER_ERROR_MESSAGE);

        if (!validateMultipleOfUnit(purchaseAmount))
            throw new IllegalArgumentException(LottoConstants.MULTIPLE_OF_UNIT_ERROR_MESSAGE);
    }

    private static boolean validatePositiveInteger(String purchaseAmount) {
        return purchaseAmount.matches("^[1-9]\\d*$");
    }

    private static boolean validateMultipleOfUnit(String purchaseAmount) {
        if (Integer.parseInt(purchaseAmount) % LottoConstants.LOTTO_PRICE == LottoConstants.NO_REMAINDER)
            return true;
        return false;
    }

    private static void printPurchasedLottos(List<Lotto> purchasedLottos) {
        System.out.println();
        System.out.println(purchasedLottos.size() + LottoConstants.PURCHASE_SUFFIX_MESSAGE);
        purchasedLottos.forEach(lotto -> System.out.println(lotto.getNumbers()));

    }

}