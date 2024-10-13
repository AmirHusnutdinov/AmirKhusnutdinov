import java.util.List;

public class SortContext {

  public void sort(List<Integer> list, int strategy) throws SortException {
    if (strategy == 1) {
      list = BubbleSortStrategy.sort(list);
      System.out.println(list);
    } else {
      list = MergeSortStrategy.sort(list);
      System.out.println(list);
    }
  }
}
