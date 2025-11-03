package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.LottoConstants.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        if (!validateWinningNumbersCount(numbers)) {
            throw new IllegalArgumentException(WINNING_NUMBER_COUNT_ERROR_MESSAGE);
        }

        if (!validateUniqueNumbers(numbers)) {
            throw new IllegalStateException (NOT_UNIQUE_NUMBER_ERROR_MESSAGE);
        }
    }

    private boolean validateWinningNumbersCount(List<Integer> numbers) {
        if (numbers.size() != MAX_WINNING_NUMBERS_LENGTH)
            return false;
        return true;
    }

    private boolean validateUniqueNumbers(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (numbers.size() != uniqueNumbers.size())
            return false;
        return true;
    }
}
