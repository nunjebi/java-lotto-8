package lotto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Console;

public class WinningNumbers {
    private Lotto winningNumbers;
    private int bonusWinningNumber;

    public WinningNumbers() {
        this.winningNumbers = inputWinningNumbers();
        this.bonusWinningNumber = inputBonusWinningNumber();
    }

    private Lotto inputWinningNumbers() {
        while (LottoConstants.OCCURRENCE_ERROR) {
            try {
                printInputWinningNumbersGide();
                String winningNumbers = Console.readLine();

                validateInputWinningNumbers(winningNumbers);

                List<Integer> numbers = parseWinningNumbers(winningNumbers);

                return new Lotto(numbers);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private int inputBonusWinningNumber() {
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

    private void validateInputWinningNumbers(String winningNumbers) {
        List<String> parseNumbers = Arrays.stream(winningNumbers.split(","))
                .map(String::trim)
                .toList();

        parseNumbers.forEach(number -> {
            if (!isNumber(number) || !isNumber(winningNumbers.charAt(winningNumbers.length() - 1))) {
                throw new IllegalArgumentException(LottoConstants.NOT_NUMBER_ERROR_MESSAGE);
            }
            if (!validateNumberRange(number)) {
                throw new IllegalArgumentException(LottoConstants.NUMBER_RANGE_ERROR_MESSAGE);
            }
        });
    }

    private void validateBonusWinningNumber(String bonusWinningNumber) {
        int lastIndex = bonusWinningNumber.length() - 1;
        if (!isNumber(bonusWinningNumber) || !isNumber(bonusWinningNumber.charAt(lastIndex))) {
            throw new IllegalArgumentException(LottoConstants.NOT_NUMBER_ERROR_MESSAGE);
        }
        if (!validateNumberRange(bonusWinningNumber)) {
            throw new IllegalArgumentException(LottoConstants.NUMBER_RANGE_ERROR_MESSAGE);
        }

        if (!validateUniqueNumbers(getWinningNumbers(), bonusWinningNumber)) {
            throw new IllegalArgumentException(LottoConstants.NOT_UNIQUE_NUMBER_ERROR_MESSAGE);
        }
    }

    private boolean isNumber(Character number) {
        return Character.isDigit(number);
    }

    private boolean isNumber(String number) {
        return number.matches("\\d+");
    }

    private boolean validateNumberRange(String number) {
        int perseNumber = Integer.parseInt(number);
        if (perseNumber < 1 || perseNumber > 45)
            return false;
        return true;
    }

    private boolean validateUniqueNumbers(List<Integer> winningNumbers, String bonusWinningNumber) {
        return !winningNumbers.contains(Integer.parseInt(bonusWinningNumber));
    }

    private void printInputWinningNumbersGide() {
        System.out.println(LottoConstants.INPUT_WINNING_NUMBERS_GUIDE_MESSAGE);
    }

    private void printInputBonusWinningNumberGuide() {
        System.out.println(LottoConstants.INPUT_BONUS_WINNING_NUMBER_GUIDE_MESSAGE);
    }

    private List<Integer> parseWinningNumbers(String winningNumbers) {
        return Arrays.stream(winningNumbers.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private List<Integer> getWinningNumbers() {
        return winningNumbers.getNumbers();
    }
}
