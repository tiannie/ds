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
        T[] newArray = (T[]) new Object[(size - 1) * 2];
        System.arraycopy(array, 0, newArray, 0, head);
        System.arraycopy(array, head, newArray, size - 1 + head, size - 1 - head);
        return newArray;
    }

    public void addFirst(T item) {
        size++;

        if (size <= array.length) {
            head = (head + array.length - 1) % array.length;
            array[head] = item;
            return;
        }

        T[] newArray = getNewArray();
        head += size - 1 - 1;
        newArray[head] = item;
        array = newArray;
    }

    public void addLast(T item) {
        size++;

        if (size <= array.length) {
            int tail = (head + size - 1) % array.length;
            array[tail] = item;
            return;
        }

        T[] newArray = getNewArray();
        head += size - 1;
        newArray[head] = item;
        array = newArray;
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
        T[] newArray = (T[]) new Object[size];
        if (head + size <= array.length) {
            System.arraycopy(array, head, newArray, 0, size);
        } else {
            System.arraycopy(array, head, newArray, 0, array.length - head);
            System.arraycopy(array, 0, newArray, array.length - head,
                    head + size - array.length);
        }
        return newArray;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        T oldFirst = array[head];
        array[head] = null;

        size--;
        head = (head + 1) % array.length;

        if (array.length >= 16) {
            if (size < (int) (array.length * 0.25)) {
                array = shrinkArray();
                head = 0;
            }
        }
        return oldFirst;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        T oldLast = array[(head + size - 1) % array.length];
        array[(head + size - 1) % array.length] = null;

        size--;

        if (array.length >= 16) {
            if (size < (int) (array.length * 0.25)) {
                array = shrinkArray();
                head = 0;
            }
        }
        return oldLast;
    }

    public T get(int index) {
        return array[(head + index) % array.length];
    }
}
