package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Sorter sorter = new Sorter(
        List.of(
            new MergeSort(10),
            new BubbleSort(6)
        )
    );

    System.out.print("Введите длину списка: ");
    int len = input.nextInt();
    System.out.print("Через пробел введите список: ");
    List<Integer> array = new ArrayList<>();
    for (int i = 0; i < len; i++) {
      array.add(input.nextInt());
    }
    System.out.println("Выбирите тип сортировки");
    for (SortType element : SortType.values()) {
      System.out.print(element + " ");
    }

    System.out.print("\nВведите её название: ");
    SortType type = SortType.valueOf(input.next());

    List<Integer> sortedList = sorter.sort(array, type);

    System.out.print("Исходный список: ");
    for (int element : array) {
      System.out.print(element + " ");
    }

    System.out.print("\nОтсортированный список: ");
    for (int element : sortedList) {
      System.out.print(element + " ");
    }
  }
}
