import java.util.Objects;

/**
 * Implement a deque conform to cpp.com's description of deque API.
 */
public class ArrayDeque<T> {
    private int size;
    private T[] array;
    private int head;

    public ArrayDeque() {
        size = 0;
        array = (T[]) new Object[8];
        head = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        size = other.size;
        array = (T[]) new Object[size];
        System.arraycopy(other.array, 0, array, 0, size);
        head = other.head;
    }

    private T[] getNewArray() {
        T[] new_array = (T[]) new Object[size];
        System.arraycopy(array, 0, new_array, 0, head);
        System.arraycopy(array, head, new_array, head + 1, size - head);
        return new_array;
    }

    public void addFirst(T item) {
        size++;

        if (size < array.length) {
            head = (head + array.length - 1) % array.length;
            array[head] = item;
            return;
        }

        T[] new_array = getNewArray();
        new_array[head] = item;
        array = new_array;
    }

    public void addLast(T item) {
        size++;

        if (size < array.length) {
            int tail = (head + size - 1) % array.length;
            array[tail] = item;
            return;
        }

        T[] new_array = getNewArray();
        new_array[head] = item;
        head++;
        array = new_array;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int idx = head;
        int seen = 0;
        while (head < array.length && seen < size) {
            System.out.println(array[idx]);
            idx = (idx + 1) % array.length;
            seen++;
        }
    }

    private T[] shrinkArray() {
        T[] new_array = (T[]) new Object[size * 4];
        if (head + size <= array.length) {
            System.arraycopy(array, head, new_array, 0, size);
        } else {
            System.arraycopy(array, head, new_array, 0, array.length - head);
            System.arraycopy(array, 0, new_array, array.length - head,
                    head + size - array.length);
        }
        return new_array;
    }

    public T removeFirst() {
        T old_first = array[head];

        size--;
        head = (head + 1) % array.length;

        if (array.length >= 16) {
            if (size < (int) (array.length * 0.25)) {
                array = shrinkArray();
            }
        }
        return old_first;
    }

    public T removeLast() {
        T old_last = array[head + size - 1];

        size--;

        if (array.length >= 16) {
            if (size < (int) (array.length * 0.25)) {
                array = shrinkArray();
            }
        }
        return old_last;
    }

    public T get(int index) {
        return array[(head + index) % array.length];
    }
}
