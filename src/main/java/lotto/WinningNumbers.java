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
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private int inputBonusWinningNumber() {
        while (OCCURRENCE_ERROR) {
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

    private void validateInputWinningNumbers(String winningNumbers) {
        List<String> parseNumbers = parseWinningNumbersAsString(winningNumbers);

        parseNumbers.forEach(number -> {
            if (!isNumber(number) || !isNumber(winningNumbers.charAt(winningNumbers.length() - 1))) {
                throw new IllegalArgumentException(NOT_NUMBER_ERROR_MESSAGE);
            }
            if (!validateNumberRange(number)) {
                throw new IllegalArgumentException(NUMBER_RANGE_ERROR_MESSAGE);
            }
        });
    }

    private void validateBonusWinningNumber(String bonusWinningNumber) {
        int lastIndex = bonusWinningNumber.length() - 1;
        if (!isNumber(bonusWinningNumber) || !isNumber(bonusWinningNumber.charAt(lastIndex))) {
            throw new IllegalArgumentException(NOT_NUMBER_ERROR_MESSAGE);
        }
        if (!validateNumberRange(bonusWinningNumber)) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR_MESSAGE);
        }

        if (!validateUniqueNumbers(getWinningNumbers(), bonusWinningNumber)) {
            throw new IllegalArgumentException(NOT_UNIQUE_NUMBER_ERROR_MESSAGE);
        }
    }

    private boolean isNumber(Character number) {
        return Character.isDigit(number);
    }

    private boolean isNumber(String number) {
        return number.matches(LOTTO_NUMBER_PATTERN);
    }

    private boolean validateNumberRange(String number) {
        int perseNumber = Integer.parseInt(number);
        if (perseNumber < MIN_LOTTO_NUMBER_RANGE || perseNumber > MAX_LOTTO_NUMBER_RANGE)
            return false;
        return true;
    }

    private boolean validateUniqueNumbers(List<Integer> winningNumbers, String bonusWinningNumber) {
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

    private List<String> parseWinningNumbersAsString(String winningNumbers) {
        return Arrays.stream(winningNumbers.split(LOTTO_NUMBER_DELIMITER))
                .map(String::trim)
                .toList();
    }

}
