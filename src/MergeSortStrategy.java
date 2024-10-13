import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class MergeSortStrategy {

  private static final int MAX_ELEMENTS = 10000;

  public static List<Integer> sort(List<Integer> list) throws SortException {
    if (list.size() > MAX_ELEMENTS) {
      throw new SortException("MergeSortStrategy: List size exceeds the maximum allowed limit.");
    }
    List<Integer> sortedList = new ArrayList<>(list);
    Collections.sort(sortedList);
    System.out.println("MergeSort");
    return sortedList;
  }
}
