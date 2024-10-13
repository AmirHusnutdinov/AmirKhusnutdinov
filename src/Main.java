import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws SortException {
    List<Integer> list = Arrays.asList(5, 2, 9, 1, 5, 6);

    SortContext context = new SortContext();

    Scanner input = new Scanner(System.in);
    System.out.print("Выбери тип сортировки 1-пузырек 2-встроенная:  ");
    int strategy = input.nextInt();

    context.sort(list, strategy);

  }
}
