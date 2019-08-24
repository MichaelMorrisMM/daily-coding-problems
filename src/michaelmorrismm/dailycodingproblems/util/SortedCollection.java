package michaelmorrismm.dailycodingproblems.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public class SortedCollection<T extends Comparable<? super T>> implements Collection<T> {

    private int size = 0;
    private Node<T> head;
    private Node<T> tail;
    private Comparator<? super T> comparator;

    public SortedCollection() {
        this.comparator = Comparator.naturalOrder();
    }

    public SortedCollection(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    private void insertNode(T value, Node<T> prev, Node<T> next) {
        Node<T> newNode = new Node<>(value);
        newNode.prev = prev;
        newNode.next = next;

        if (prev != null) {
            prev.next = newNode;
        } else {
            this.head = newNode;
        }

        if (next != null) {
            next.prev = newNode;
        } else {
            this.tail = newNode;
        }

        this.size++;
    }

    private void removeNode(Node<T> node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            this.head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            this.tail = node.prev;
        }

        this.size--;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (T item : this) {
            if ((o == null && item == null) || (o != null && o.equals(item))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new NodeIterator<>(this, this.head);
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.size];
        int i = 0;
        for (T val : this) {
            array[i] = val;
            i++;
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] providedArray) {
        if (providedArray.length >= this.size) {
            int i = 0;
            for (T current : this) {
                providedArray[i++] = (T1) current;
            }
            if (providedArray.length > this.size) {
                providedArray[this.size] = null;
            }
            return providedArray;
        } else {
            return (T1[]) this.toArray();
        }
    }

    @Override
    public boolean add(T t) {
        Node<T> current = this.head;
        if (current == null) {
            this.insertNode(t, null, null);
            return true;
        }
        while (true) {
            if (this.comparator.compare(t, current.value) <= 0) {
                this.insertNode(t, current.prev, current);
                return true;
            }
            if (current.next == null) {
                this.insertNode(t, current, null);
                return true;
            }
            current = current.next;
        }
    }

    @Override
    public boolean remove(Object o) {
        return this.removeIf((T val) -> (o == null && val == null) || (o != null && o.equals(val)));
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object item : c) {
            if (!this.contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean changed = false;
        for (T item : c) {
            if (this.add(item)) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object item : c) {
            if (this.remove(item)) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.removeIf((T val) -> !c.contains(val));
    }

    @Override
    public void clear() {
        this.head = this.tail = null;
        this.size = 0;
    }

    private static class Node<T> {
        T value;
        Node<T> prev;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }

    }

    private static class NodeIterator<T extends Comparable<? super T>> implements Iterator<T> {
        SortedCollection<T> collection;
        Node<T> current;
        boolean checkCurrent = true;

        NodeIterator(SortedCollection<T> collection, Node<T> start) {
            this.collection = collection;
            this.current = start;
        }

        @Override
        public boolean hasNext() {
            if (!this.checkCurrent) {
                return this.current.next != null;
            } else {
                return this.current != null;
            }
        }

        @Override
        public T next() {
            if (!this.checkCurrent) {
                this.current = this.current.next;
            } else {
                this.checkCurrent = false;
            }
            return this.current.value;
        }

        @Override
        public void remove() {
            Node<T> previouslyReturnedNode = this.current;
            this.current = this.current.next;
            this.checkCurrent = true;
            this.collection.removeNode(previouslyReturnedNode);
        }

    }

}
