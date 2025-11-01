package lotto;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        int inputPurchaseAmount = inputLottoPurchaseAmount();

        System.out.println("money: " + inputPurchaseAmount); // print test
    }

    private static int inputLottoPurchaseAmount() {
        while (LottoConstants.OCCURRENCE_ERROR) {
            try {
                printInputPurchaseAmountGuide();
                String inputPurchaseAmount = Console.readLine();

                validateInputPurchaseAmount(inputPurchaseAmount);

                return Integer.parseInt(inputPurchaseAmount);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private static void printInputPurchaseAmountGuide() {
        System.out.println(LottoConstants.INPUT_PURCHASE_AMOUNT_GUIDE_MESSAGE);
    }

    private static void validateInputPurchaseAmount(String inputPurchaseAmount) {
        if (isNotPositiveInteger(inputPurchaseAmount))
            throw new IllegalArgumentException(LottoConstants.NOT_POSITIVE_INTEGER_ERROR_MESSAGE);

        int purchaseAmount = Integer.parseInt(inputPurchaseAmount);
        if (isNotMultipleOfUnit(purchaseAmount))
            throw new IllegalArgumentException(LottoConstants.MULTIPLE_OF_UNIT_ERROR_MESSAGE);
    }

    private static boolean isNotPositiveInteger(String inputPurchaseAmount) {
        return !inputPurchaseAmount.matches("^[1-9]\\d*$");
    }

    private static boolean isNotMultipleOfUnit(int inputPurchaseAmount) {
        if (inputPurchaseAmount % LottoConstants.LOTTO_PRICE != LottoConstants.NO_REMAINDER)
            return true;
        return false;
    }
}