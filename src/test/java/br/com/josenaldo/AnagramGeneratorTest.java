package br.com.josenaldo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.Test;

class AnagramGeneratorTest {

  @Test
  void givenASetOfCharacters_whenGenerate_thenReturnAnArrayOfAnagrams() {
    // Arrange - Given
    final var letters = Set.of('a', 'b', 'c');
    final var expected = new String[]{"abc", "acb", "bac", "bca", "cab", "cba"};

    // Act - When
    final var anagramGenerator = new AnagramGenerator();
    String[] generate = anagramGenerator.generate(letters);

    // Assert - Then
    assertThat(generate).containsExactlyInAnyOrder(expected);
  }

}