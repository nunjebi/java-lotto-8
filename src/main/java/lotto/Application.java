package lotto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    public static void main(String[] args) {
        int inputPurchaseAmount = inputLottoPurchaseAmount();

        List<List<Integer>> lottoNumbers = getLottoNumbers(inputPurchaseAmount);
        printLottoNumbers(lottoNumbers);

        List<Integer> winningNumbers = inputWinningNumbers();
        int bonusWinningNumber = inputBonusWinningNumber();
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
        if (!validatePositiveInteger(inputPurchaseAmount))
            throw new IllegalArgumentException(LottoConstants.NOT_POSITIVE_INTEGER_ERROR_MESSAGE);

        int purchaseAmount = Integer.parseInt(inputPurchaseAmount);
        if (!validateMultipleOfUnit(purchaseAmount))
            throw new IllegalArgumentException(LottoConstants.MULTIPLE_OF_UNIT_ERROR_MESSAGE);
    }

    private static boolean validatePositiveInteger(String inputPurchaseAmount) {
        return inputPurchaseAmount.matches("^[1-9]\\d*$");
    }

    private static boolean validateMultipleOfUnit(int inputPurchaseAmount) {
        if (inputPurchaseAmount % LottoConstants.LOTTO_PRICE == LottoConstants.NO_REMAINDER)
            return true;
        return false;
    }

    private static List<List<Integer>> getLottoNumbers(int inputPurchaseAmount) {
        int lottoCount = inputPurchaseAmount / LottoConstants.LOTTO_PRICE;
        return IntStream.range(0, lottoCount)
                .mapToObj(index -> Randoms.pickUniqueNumbersInRange(1, 45, 6))
                .collect(Collectors.toList());
    }

    private static void printLottoNumbers(List<List<Integer>> lottoNumbers) {
        System.out.println();
        System.out.println(lottoNumbers.size() + LottoConstants.PURCHASE_SUFFIX_MESSAGE);
        lottoNumbers.forEach(System.out::println);

    }

    private static List<Integer> inputWinningNumbers() {
        while (LottoConstants.OCCURRENCE_ERROR) {
            try {
                printInputWinningNumbersGide();
                String winningNumbers = Console.readLine();

                validateInputWinningNumbers(winningNumbers);

                return Arrays.stream(winningNumbers.split(","))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private static void printInputWinningNumbersGide() {
        System.out.println(LottoConstants.INPUT_WINNING_NUMBERS_GUIDE_MESSAGE);
    }

    private static void validateInputWinningNumbers(String winningNumbers) {
        List<String> parseNumbers = Arrays.stream(winningNumbers.split(","))
                .map(String::trim)
                .toList();

        int lastIndex = winningNumbers.length() - 1;
        parseNumbers.forEach(number -> {
            if (!isNumber(number) || !isNumber(winningNumbers.charAt(lastIndex))) {
                throw new IllegalArgumentException(LottoConstants.NOT_NUMBER_ERROR_MESSAGE);
            }
            if (!validateNumberRange(number)) {
                throw new IllegalArgumentException(LottoConstants.NUMBER_RANGE_ERROR_MESSAGE);
            }
        });

        if (!validateWinningNumbersCount(parseNumbers)) {
            throw new IllegalArgumentException(LottoConstants.WINNING_NUMBER_COUNT_ERROR_MESSAGE);
        }

        if (!validateUniqueNumbers(parseNumbers)) {
            throw new IllegalArgumentException(LottoConstants.NOT_UNIQUE_NUMBER_ERROR_MESSAGE);
        }
    }

    private static boolean isNumber(Character number) {
        return Character.isDigit(number);
    }

    private static boolean isNumber(String number) {
        return number.matches("\\d+");
    }

    private static boolean validateNumberRange(String number) {
        int perseNumber = Integer.parseInt(number);
        if (perseNumber < 1 || perseNumber > 45)
            return false;
        return true;
    }

    private static boolean validateWinningNumbersCount(List<String> parseNumbers) {
        if (parseNumbers.size() != 6)
            return false;
        return true;
    }

    private static boolean validateUniqueNumbers(List<String> parseNumbers) {
        Set<String> uniqueNumbers = new HashSet<>(parseNumbers);
        if (parseNumbers.size() != uniqueNumbers.size())
            return false;
        return true;
    }

    private static int inputBonusWinningNumber() {
        while (LottoConstants.OCCURRENCE_ERROR) {
            try {
                printInputBonusWinningNumberGuide();
                String bonusWinningNumber = Console.readLine();

                validateBonusWinningNumber(bonusWinningNumber);

                return Integer.parseInt(bonusWinningNumber);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private static void printInputBonusWinningNumberGuide() {
        System.out.println(LottoConstants.INPUT_BONUS_WINNING_NUMBER_GUIDE_MESSAGE);
    }

    private static void validateBonusWinningNumber(String bonusWinningNumber) {
        int lastIndex = bonusWinningNumber.length() - 1;
        if (!isNumber(bonusWinningNumber) || !isNumber(bonusWinningNumber.charAt(lastIndex))) {
            throw new IllegalArgumentException(LottoConstants.NOT_NUMBER_ERROR_MESSAGE);
        }
        if (!validateNumberRange(bonusWinningNumber)) {
            throw new IllegalArgumentException(LottoConstants.NUMBER_RANGE_ERROR_MESSAGE);
        }

        // (당첨 번호와 비교하여 유니크 번호인지 확인)
        // if (!validateUniqueNumbers(parseNumbers)) {
        // throw new
        // IllegalArgumentException(LottoConstants.NOT_UNIQUE_NUMBER_ERROR_MESSAGE);
        // }
    }
}