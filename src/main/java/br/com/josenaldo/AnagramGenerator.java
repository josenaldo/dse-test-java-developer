package br.com.josenaldo;

import java.util.Objects;
import java.util.Set;

public class AnagramGenerator {
  public String[] generate(Set<Character> letters) {
    Objects.requireNonNull(letters, "Set of characters cannot be null");

    if (letters.isEmpty()) {
      throw new IllegalArgumentException("Set of characters cannot be empty");
    }

    if(letters.size() == 1){
      throw new IllegalArgumentException("Set of characters must contain more than one character");
    }

    return new String[0];
  }
}
