package lab2;

class Node<T extends Number & Comparable<T>> {
    Node<T> prev;
    T value;
    Node<T> next;

    Node(T value) {
        this.value = value;
        this.next  = null;
        this.prev  = null;
    }

    void detach(){
        this.prev = null;
        this.next = null;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
