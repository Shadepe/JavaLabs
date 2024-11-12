package lab2;

public class nodeList<T extends Number & Comparable<T>> {
    private Node<T> head;
    private Node<T> tail;

    public nodeList() {
        this.head = null;
        this.tail = null;
    }

    public nodeList(T[] values) {
        this.head = null;
        this.tail = null;

        for (T value : values) {
            this.append(value);
        }
    }

    public int len(){
        int len = 0;
        for (Node<T> node = this.head; node != null; node=node.next, len++);
        return len;
    }

    public void append(T value) {
        Node<T> newNode = new Node<T>(value);
        if (this.head == null)
            this.head = newNode;

        if (this.tail != null){
            this.tail.next = newNode;
            newNode.prev = this.tail;
        }

        this.tail = newNode;
    }

    public Node<T> get(int index) throws InvalidIndexException{
        Node<T> node = this.head;

        for (int i = 0; i < index; i++) {
            if (node.next == null)
                throw new InvalidIndexException("Get Error: Invalid index");
            node = node.next;
        }

        return node;
    }

    public void insert(T value, int index) throws InvalidIndexException{
        Node<T> node;
        try {
            node = this.get(index);
            
        } catch (InvalidIndexException e) {
            throw new InvalidIndexException("Remove Error: Invalid index");
        }
        
        Node<T> prevNode = node.prev;
        Node<T> newNode = new Node<T>(value);

        newNode.next = node;

        if (node == this.head)
            this.head = newNode;

        if (prevNode != null){
            prevNode.next = newNode;
            newNode.prev  = prevNode;
        }
    } 

    public Node<T> replace(T value, int index) throws InvalidIndexException{
        Node<T> node;
        try {
            node = this.get(index);
        } catch (InvalidIndexException e) {
            throw new InvalidIndexException("Remove Error: Invalid index");
        }
        
        Node<T> prevNode = node.prev;
        Node<T> nextNode = node.next;
        Node<T> newNode = new Node<T>(value);
        
        if (node == this.head)
            this.head = newNode;
        if (node == this.tail)
            this.tail = newNode;
        
        if (prevNode != null){
            prevNode.next = newNode;
            newNode.prev  = prevNode;
        }
        if (nextNode != null){
            nextNode.prev = newNode;
            newNode.next  = nextNode;
        }
        node.detach();
        return node;
    } 

    public Node<T> remove(int index) throws InvalidIndexException{
        Node<T> node;

        try {
            node = this.get(index);
        } catch (InvalidIndexException e) {
            throw new InvalidIndexException("Remove Error: Invalid index");
        }
        
        Node<T> prevNode = node.prev;
        Node<T> nextNode = node.next;

        if (node == this.head)
            this.head = nextNode;
        if (node == this.tail)
            this.tail = prevNode;
        
        if (prevNode != null)
            prevNode.next = nextNode;
        if (nextNode != null)
            nextNode.prev = prevNode;
        
        node.detach();
        return node;
    }


    public Number[] toArray(){
        int len = this.len();
        Number[] array = new Number[len];
        Node<T> node = this.head;

        for (int i = 0; i < len; i++){
            array[i] = node.value;
            node = node.next;
        }
        return array;
    }

    // public void sort(){
    // }

    // public void sort(boolean reverse){
    // }

    public void sort() {
        this.head = mergeSort(this.head);
        // Update the tail pointer after sorting
        Node<T> current = this.head;
        while (current != null && current.next != null) {
            current = current.next;
        }
        this.tail = current;
    }

    private Node<T> mergeSort(Node<T> leftHead) {
        if (leftHead == null || leftHead.next == null) {
            return leftHead;
        }

        Node<T> middle = getMiddle(leftHead);
        Node<T> rightHead = middle.next;
        middle.next = null;

        Node<T> left  = mergeSort(leftHead);
        Node<T> right = mergeSort(rightHead);

        return sortedMerge(left, right);
    }

    private Node<T> sortedMerge(Node<T> left, Node<T> right) {
        if (left  == null) return right;
        if (right == null) return left;

        Node<T> result;
        if (left.value.compareTo(right.value) <= 0) {
            result = left;
            result.next = sortedMerge(left.next, right);
            if (result.next != null) result.next.prev = result;
        } else {
            result = right;
            result.next = sortedMerge(left, right.next);
            if (result.next != null) result.next.prev = result;
        }
        return result;
    }

    private Node<T> getMiddle(Node<T> head) {
        if (head == null) return head;

        Node<T> slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public int search(T value) {
        int index = 0;
        Node<T> node = this.head;
        
        while (node != null) {
            if (node.value.equals(value)) {
                return index;
            }
            node = node.next;
            index++;
        }

        return -1;
    }

    public void forEach(ForEachCallback<T> callback) {
        Node<T> current = head;
        while (current != null) {
            callback.toDo(current);
            current = current.next;
        }
    }
}
