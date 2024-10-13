import java.util.ArrayList;
import java.util.List;

public class BubbleSortStrategy {

  private static final int MAX_ELEMENTS = 5000;

  public static List<Integer> sort(List<Integer> list) throws SortException {
    if (list.size() > MAX_ELEMENTS) {
      throw new SortException("BubbleSortStrategy: List size exceeds the maximum allowed limit.");
    }
    List<Integer> sortedList = new ArrayList<>(list);
    int n = sortedList.size();
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (sortedList.get(j) > sortedList.get(j + 1)) {
          int temp = sortedList.get(j);
          sortedList.set(j, sortedList.get(j + 1));
          sortedList.set(j + 1, temp);
        }
      }
    }
    System.out.println("BubbleSort");
    return sortedList;
  }
}
