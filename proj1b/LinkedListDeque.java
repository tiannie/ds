/** Implement a deque conform to cpp.com's description of deque API. */
public class LinkedListDeque<T> implements Deque<T> {
    private int size;
    private DNode sentinel;
    /** Private supporting class DNode. */
    private static class DNode<T> {
        T item;
        DNode prev;
        DNode next;
        DNode() {
            this.item = null;
            this.prev = this;
            this.next = this;
        }
        DNode(T item, DNode prev, DNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new DNode();
    }
    public LinkedListDeque(LinkedListDeque other) {
        size = 0;
        sentinel = new DNode();
        DNode p = other.sentinel;
        while (p.next != other.sentinel) {
            addLast((T) p.next.item);
            p = p.next;
        }
    }

    @Override
    public void addFirst(T item) {
        size++;

        DNode newFirst = new DNode(item, sentinel, sentinel.next);
        sentinel.next.prev = newFirst;
        sentinel.next = newFirst;
    }
    @Override
    public void addLast(T item) {
        size++;

        DNode newLast = new DNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = newLast;
        sentinel.prev = newLast;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        DNode p = sentinel;
        while (p.next != sentinel) {
            System.out.println(p.item);
            p = p.next;
        }
    }
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        size--;

        DNode oldFirst = sentinel.next;
        sentinel.next.next.prev = sentinel;
        sentinel.next = oldFirst.next;
        return (T) oldFirst.item;
    }
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;

        DNode oldLast = sentinel.prev;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = oldLast.prev;
        return (T) oldLast.item;
    }
    @Override
    public T get(int index) {
        DNode p = sentinel;
        for (int pos = 0; pos < index; pos++) {
            p = p.next;
        }
        return (T) p.next.item;
    }
    private DNode nthDNode(DNode dnode, int i) {
        if (i == 0) {
            return dnode;
        }
        return nthDNode(dnode.next, i - 1);
    }
    public T getRecursive(int index) {
        return (T) nthDNode(sentinel.next, index).item;
    }
}
