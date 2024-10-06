import java.util.Arrays;

/**
 * Кастомная реализация ArrayList.
 * @param <A> Тип элементов, которые будут храниться в списке.
 */
public class CustomArrayList<A> implements CustomList<A> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    /**
     * Конструктор по умолчанию.
     */
    public CustomArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Конструктор с указанием начальной емкости.
     * @param initialCapacity Начальная емкость списка.
     */
    public CustomArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity cannot be negative");
        }
        this.elements = new Object[initialCapacity];
        this.size = 0;
    }

    @Override
    public void add(A element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        ensureCapacity();
        elements[size++] = element;
    }

    @Override
    public A get(int index) {
        checkIndex(index);
        return (A) elements[index];
    }

    @Override
    public A remove(int index) {
        checkIndex(index);
        A removedElement = (A) elements[index];
        // Смещение элементов влево
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null; // Удаляем последний элемент
        return removedElement;
    }

    /**
     * Проверяет, достаточно ли места в массиве, и если нет, увеличивает его размер.
     */
    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    /**
     * Проверяет, находится ли индекс в пределах допустимых значений.
     * @param index Индекс для проверки.
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Возвращает текущий размер списка.
     * @return Текущий размер списка.
     */
    public int size() {
        return size;
    }
}
