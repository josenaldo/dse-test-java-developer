package br.com.josenaldo.dsejavadeveloper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AnagramGeneratorTest {

  @Test
  void givenASetOfCharacters_whenGenerate_thenReturnAnArrayOfAnagrams() {
    // Arrange - Given
    final var letters = Set.of('a', 'b', 'c');
    final var expected = new String[]{"abc", "acb", "bac", "bca", "cab", "cba"};

    // Act - When
    final var anagramGenerator = new AnagramGenerator();
    String[] generated = anagramGenerator.generate(letters);

    // Assert - Then
    assertThat(expected).containsExactlyInAnyOrder(generated);
  }

  @Test
  void givenASetOfFourCharacters_whenGenerate_thenReturnAnArrayOfAnagrams() {
    // Arrange - Given
    final var letters = Set.of('a', 'b', 'c', 'd');
    final var expected = new String[]{
        "abcd", "abdc", "acbd", "acdb",
        "adbc", "adcb", "bacd", "badc",
        "bcad", "bcda", "bdac", "bdca",
        "cabd", "cadb", "cbad", "cbda",
        "cdab", "cdba", "dabc", "dacb",
        "dbac", "dbca", "dcab", "dcba"
    };

    // Act - When
    final var anagramGenerator = new AnagramGenerator();
    String[] generated = anagramGenerator.generate(letters);

    // Assert - Then
    assertThat(expected).containsExactlyInAnyOrder(generated);
  }
  

  @Test
  void givenASetOfCharactersWithSingleCharacter_whenGenerate_thenReturnAnArrayOfAnagrams(){
    // Arrange - Given
    final Set<Character> letters = Set.of('a');
    final var expected = List.of("a").toArray(new String[0]);

    // Act - When
    final var anagramGenerator = new AnagramGenerator();
    String[] generated = anagramGenerator.generate(letters);

    // Assert - Then
    assertThat(expected).containsExactlyInAnyOrder(generated);
  }

  @Test
  void givenAnEmptySetOfCharacters_whenGenerate_thenThrowAnIllegalArgumentException(){
    // Arrange - Given
    final Set<Character> letters = Set.of();
    final var expected = new IllegalArgumentException("Set of characters cannot be empty");

    // Act - When
    final var anagramGenerator = new AnagramGenerator();
    final var actualException = Assertions.catchIllegalArgumentException(() -> anagramGenerator.generate(letters));

    // Assert - Then
    assertThat(actualException).hasMessage(expected.getMessage());
    assertThat(actualException).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void givenAnNullSetOfCharacters_whenGenerate_thenThrowAnNullPointerException(){
    // Arrange - Given
    final Set<Character> letters = null;
    final var expected = new NullPointerException("Set of characters cannot be null");

    // Act - When
    final var anagramGenerator = new AnagramGenerator();
    final var actualException = Assertions.catchNullPointerException(() -> anagramGenerator.generate(letters));

    // Assert - Then
    assertThat(actualException).hasMessage(expected.getMessage());
    assertThat(actualException).isInstanceOf(NullPointerException.class);
  }

  @Test
  void givenASetOfCharactersWithEmptySpace_whenGenerate_thenThrowAnIllegalArgumentException(){
    // Arrange - Given
    final Set<Character> letters = Set.of(' ');
    final var expected = new IllegalArgumentException("Set of characters must contain more than one character");

    // Act - When
    final var anagramGenerator = new AnagramGenerator();
    final var actualException = Assertions.catchIllegalArgumentException(() -> anagramGenerator.generate(letters));

    // Assert - Then
    assertThat(actualException).hasMessage(expected.getMessage());
    assertThat(actualException).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void givenASetOfCharactersWithNumbers_whenGenerate_thenThrowAnIllegalArgumentException(){
    // Arrange - Given
    final Set<Character> letters = Set.of('a', '2', 'c');
    final var expected = new IllegalArgumentException("Set of characters can only contain letters");

    // Act - When
    final var anagramGenerator = new AnagramGenerator();
    final var actualException = Assertions.catchIllegalArgumentException(() -> anagramGenerator.generate(letters));

    // Assert - Then
    assertThat(actualException).hasMessage(expected.getMessage());
    assertThat(actualException).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void givenASetOfCharactersWithSpecialCharacters_whenGenerate_thenThrowAnIllegalArgumentException(){
    // Arrange - Given
    final Set<Character> letters = Set.of('a', '#', 'c');
    final var expected = new IllegalArgumentException("Set of characters can only contain letters");

    // Act - When
    final var anagramGenerator = new AnagramGenerator();
    final var actualException = Assertions.catchIllegalArgumentException(() -> anagramGenerator.generate(letters));

    // Assert - Then
    assertThat(actualException).hasMessage(expected.getMessage());
    assertThat(actualException).isInstanceOf(IllegalArgumentException.class);
  }


}