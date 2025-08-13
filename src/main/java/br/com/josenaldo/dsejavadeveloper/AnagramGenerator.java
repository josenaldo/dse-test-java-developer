package br.com.josenaldo.dsejavadeveloper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

/**
 * The AnagramGenerator class is responsible for generating all unique permutations (anagrams) of a
 * set of characters. It validates input constraints and provides functionality to generate an array
 * of all possible anagrams.
 * <p>
 * This class supports generating anagrams only for sets containing letters and does not allow
 * numbers, spaces, or special characters. The input set must contain at least one valid
 * alphabetical character.
 */
public class AnagramGenerator {

  /**
   * Generates all unique permutations (anagrams) of the given set of characters. The set must
   * contain only valid alphabetical characters and cannot be empty or null.
   *
   * @param letters the set of characters to generate permutations from; must not be null, empty, or
   *                contain non-alphabetical characters
   * @return an array of strings representing all possible unique permutations of the input set
   * @throws NullPointerException     if the provided set is null
   * @throws IllegalArgumentException if the set is empty, contains non-alphabetical characters, or
   *                                  contains a single non-letter character
   */
  public String[] generate(Set<Character> letters) {
    Objects.requireNonNull(letters, "Set of characters cannot be null");

    if (letters.isEmpty()) {
      throw new IllegalArgumentException("Set of characters cannot be empty");
    }

    if (letters.size() == 1) {
      char only = letters.iterator().next();
      if (!Character.isLetter(only)) {
        throw new IllegalArgumentException("Set of characters must contain more than one character");
      }

      return new String[]{String.valueOf(only)};
    }

    if (letters.stream().anyMatch(Predicate.not(Character::isLetter))) {
      throw new IllegalArgumentException("Set of characters can only contain letters");
    }

    final List<Character> chars = new ArrayList<>(letters);
    final List<String> combinations = generatePermutations(chars);
    return combinations.toArray(new String[0]);
  }

  /**
   * Generates all possible permutations of a given list of characters.
   *
   * @param chars the list of characters to generate permutations from; must not be null or empty
   * @return a list of strings representing all the permutations of the given characters
   */
  private List<String> generatePermutations(List<Character> chars) {
    List<String> result = new ArrayList<>();

    if (chars.size() == 1) {
      result.add(chars.getFirst().toString());
      return result;
    }

    for (int i = 0; i < chars.size(); i++) {
      char current = chars.get(i);
      List<Character> remaining = new ArrayList<>(chars.size() - 1);
      remaining.addAll(chars.subList(0, i));
      remaining.addAll(chars.subList(i + 1, chars.size()));

      List<String> subPermutations = generatePermutations(remaining);

      for (String subPermutation : subPermutations) {
        result.add(current + subPermutation);
      }
    }

    return result;
  }
}