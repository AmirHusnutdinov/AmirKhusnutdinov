public class Zoo {

  public static class HomeworkSecond {

    // Интерфейсы для различных типов движения
    public interface Flying {

      void fly();
    }

    public interface Terrestrial {

      void walk();
    }

    public interface Waterfowl {

      void swim();
    }

    // Базовый интерфейс для еды
    public interface BaseMeal {

      String getMealName();
    }

    // Классы для различных типов еды
    public static class Meat implements BaseMeal {

      @Override
      public String getMealName() {
        return "мясо";
      }
    }

    public static class Beef extends Meat {

      @Override
      public String getMealName() {
        return "говядину";
      }
    }

    public static class Fish extends Meat {

      @Override
      public String getMealName() {
        return "рыбу";
      }
    }

    public static class Grass implements BaseMeal {

      @Override
      public String getMealName() {
        return "траву";
      }
    }

    // Абстрактный класс для животных
    public static abstract class BaseType {

      protected String animalTitle;

      public void printType(String typeName) {
        System.out.println(typeName);
      }

      public void eat(BaseMeal meal) {
        System.out.println(animalTitle + " ест " + meal.getMealName());
      }
    }

    // Класс для травоядных животных
    public static class Herbivores extends BaseType {

      public void printType() {
        super.printType("Травоядные");
      }

      public void eat(Grass meal) {
        super.eat(meal);
      }
    }

    // Класс для хищников
    public static class Predators extends BaseType {

      public void printType() {
        super.printType("Хищники");
      }

      public void eat(Meat meal) {
        super.eat(meal);
      }
    }

    // Конкретные классы животных
    public static class Camel extends Herbivores implements Terrestrial {

      public Camel() {
        animalTitle = "Верблюд";
      }

      @Override
      public void walk() {
        System.out.println(animalTitle + " ходит");
      }
    }

    public static class Dolphin extends Predators implements Waterfowl {

      public Dolphin() {
        animalTitle = "Дельфин";
      }

      public void eat(Fish meal) {
        super.eat(meal);
      }

      @Override
      public void swim() {
        System.out.println(animalTitle + " плавает");
      }
    }

    public static class Eagle extends Predators implements Flying {

      public Eagle() {
        animalTitle = "Орел";
      }

      @Override
      public void fly() {
        System.out.println(animalTitle + " летает");
      }
    }

    public static class Horse extends Herbivores implements Terrestrial {

      public Horse() {
        animalTitle = "Лошадь";
      }

      @Override
      public void walk() {
        System.out.println(animalTitle + " ходит");
      }
    }

    public static class Tiger extends Predators implements Terrestrial {

      public Tiger() {
        animalTitle = "Тигр";
      }

      @Override
      public void walk() {
        System.out.println(animalTitle + " ходит");
      }

      public void eat(Beef meal) {
        super.eat(meal);
      }
    }

    // Основной метод для демонстрации работы
    public static void main(String[] args) {
      Camel camel = new Camel();
      Grass grass = new Grass();
      camel.eat(grass);

      Dolphin dolphin = new Dolphin();
      dolphin.swim();

      Eagle eagle = new Eagle();
      eagle.fly();
    }
  }
}
