package lotto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Console;

import static lotto.LottoConstants.*;

public class WinningNumbers {
    private Lotto winningNumbers;
    private int bonusWinningNumber;

    public WinningNumbers() {
        this.winningNumbers = inputWinningNumbers();
        this.bonusWinningNumber = inputBonusWinningNumber();
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers.getNumbers();
    }

    public Integer getBonusWinningNumber() {
        return bonusWinningNumber;
    }

    private Lotto inputWinningNumbers() {
        while (OCCURRENCE_ERROR) {
            try {
                printInputWinningNumbersGide();
                String winningNumbers = Console.readLine();

                validateInputWinningNumbers(winningNumbers);

                return new Lotto(parseWinningNumbersAsInt(winningNumbers));
            } catch (IllegalArgumentException | IllegalStateException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private int inputBonusWinningNumber() {
        while (OCCURRENCE_ERROR) {
            try {
                printInputBonusWinningNumberGuide();
                String bonusWinningNumber = Console.readLine();

                validateBonusWinningNumber(getWinningNumbers(), bonusWinningNumber);

                return Integer.parseInt(bonusWinningNumber);
            } catch (IllegalArgumentException | IllegalStateException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public static void validateInputWinningNumbers(String winningNumbers) {
        List<String> parseNumbers = parseWinningNumbersAsString(winningNumbers);

        parseNumbers.forEach(number -> {
            if (!isNumber(number) || !isNumber(winningNumbers.charAt(winningNumbers.length() - 1))) {
                throw new NumberFormatException(NOT_NUMBER_ERROR_MESSAGE);
            }
            if (!validateNumberRange(number)) {
                throw new IllegalArgumentException(
                        String.format(NUMBER_RANGE_ERROR_MESSAGE, MIN_LOTTO_NUMBER_RANGE, MAX_LOTTO_NUMBER_RANGE));
            }
        });
    }

    public static void validateBonusWinningNumber(List<Integer> winningNumbers, String bonusWinningNumber) {
        int lastIndex = bonusWinningNumber.length() - 1;
        if (!isNumber(bonusWinningNumber) || !isNumber(bonusWinningNumber.charAt(lastIndex))) {
            throw new NumberFormatException(NOT_NUMBER_ERROR_MESSAGE);
        }

        if (!validateNumberRange(bonusWinningNumber)) {
            throw new IllegalArgumentException(
                    String.format(NUMBER_RANGE_ERROR_MESSAGE, MIN_LOTTO_NUMBER_RANGE, MAX_LOTTO_NUMBER_RANGE));
        }

        if (!validateUniqueNumbers(winningNumbers, bonusWinningNumber)) {
            throw new IllegalStateException(NOT_UNIQUE_NUMBER_ERROR_MESSAGE);
        }
    }

    private static boolean isNumber(Character number) {
        return Character.isDigit(number);
    }

    private static boolean isNumber(String number) {
        return number.matches(LOTTO_NUMBER_PATTERN);
    }

    private static boolean validateNumberRange(String number) {
        if (number.length() > MAX_LOTTO_NUMBER_LENGTH)
            return false;

        int perseNumber = Integer.parseInt(number);
        if (perseNumber < MIN_LOTTO_NUMBER_RANGE || perseNumber > MAX_LOTTO_NUMBER_RANGE)
            return false;
        return true;
    }

    private static boolean validateUniqueNumbers(List<Integer> winningNumbers, String bonusWinningNumber) {
        return !winningNumbers.contains(Integer.parseInt(bonusWinningNumber));
    }

    private void printInputWinningNumbersGide() {
        System.out.println();
        System.out.println(INPUT_WINNING_NUMBERS_GUIDE_MESSAGE);
    }

    private void printInputBonusWinningNumberGuide() {
        System.out.println();
        System.out.println(INPUT_BONUS_WINNING_NUMBER_GUIDE_MESSAGE);
    }

    private List<Integer> parseWinningNumbersAsInt(String winningNumbers) {
        return Arrays.stream(winningNumbers.split(LOTTO_NUMBER_DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static List<String> parseWinningNumbersAsString(String winningNumbers) {
        return Arrays.stream(winningNumbers.split(LOTTO_NUMBER_DELIMITER))
                .map(String::trim)
                .toList();
    }

}
