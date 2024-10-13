import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BubbleSortStrategyTest {

  @Test
  public void testSortWithNormalList() throws SortException {
    List<Integer> list = Arrays.asList(5, 3, 8, 1, 2, 7, 4, 6);
    List<Integer> sortedList = BubbleSortStrategy.sort(list);
    assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), sortedList);
  }

  @Test
  public void testSortWithEmptyList() throws SortException {
    List<Integer> list = List.of();
    List<Integer> sortedList = BubbleSortStrategy.sort(list);
    assertEquals(List.of(), sortedList);
  }

  @Test
  public void testSortWithSingleElementList() throws SortException {
    List<Integer> list = List.of(42);
    List<Integer> sortedList = BubbleSortStrategy.sort(list);
    assertEquals(List.of(42), sortedList);
  }

  @Test
  public void testSortWithAlreadySortedList() throws SortException {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
    List<Integer> sortedList = BubbleSortStrategy.sort(list);
    assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), sortedList);
  }

  @Test
  public void testSortWithReverseSortedList() throws SortException {
    List<Integer> list = Arrays.asList(8, 7, 6, 5, 4, 3, 2, 1);
    List<Integer> sortedList = BubbleSortStrategy.sort(list);
    assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), sortedList);
  }

  @Test
  public void testSortWithMaxElements() {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 5000; i++) {
      list.add(i);
    }
    assertDoesNotThrow(() -> BubbleSortStrategy.sort(list));
  }

  @Test
  public void testSortWithExceedingMaxElements() {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 5001; i++) {
      list.add(i);
    }
    assertThrows(SortException.class, () -> BubbleSortStrategy.sort(list));
  }
}
