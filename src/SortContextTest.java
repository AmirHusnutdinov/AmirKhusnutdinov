import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SortContextTest {

  @Test
  public void testSortWithEmptyList() throws SortException {
    SortContext context = new SortContext();
    List<Integer> list = List.of();
    context.sort(list, 1);
    assertEquals(List.of(), list);
  }

  @Test
  public void testSortWithSingleElementList() throws SortException {
    SortContext context = new SortContext();
    List<Integer> list = List.of(42);
    context.sort(list, 1);
    assertEquals(List.of(42), list);
  }

  @Test
  public void testSortWithAlreadySortedList() throws SortException {
    SortContext context = new SortContext();
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
    context.sort(list, 1);
    assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), list);
  }

  @Test
  public void testSortWithMaxElementsForBubbleSort() {
    SortContext context = new SortContext();
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 5000; i++) {
      list.add(i);
    }
    assertDoesNotThrow(() -> context.sort(list, 1));
  }

  @Test
  public void testSortWithExceedingMaxElementsForBubbleSort() {
    SortContext context = new SortContext();
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 5001; i++) {
      list.add(i);
    }
    assertThrows(SortException.class, () -> context.sort(list, 1));
  }

  @Test
  public void testSortWithMaxElementsForMergeSort() {
    SortContext context = new SortContext();
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 10000; i++) {
      list.add(i);
    }
    assertDoesNotThrow(() -> context.sort(list, 2));
  }

  @Test
  public void testSortWithExceedingMaxElementsForMergeSort() {
    SortContext context = new SortContext();
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 10001; i++) {
      list.add(i);
    }
    assertThrows(SortException.class, () -> context.sort(list, 2));
  }
}