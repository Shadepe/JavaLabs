package lab2;

public interface ForEachCallback<T extends Number & Comparable<T>> {
    void toDo(Node<T> node);
}
