package lab2;

public class Main {

    public static <T extends Number & Comparable<T>> void printListInfo(nodeList<T> list){
        list.forEach(node -> System.out.println("Value: " + node));
        System.out.print("Len: "+list.len()+"\n\n");
    }

    public static <T extends Number & Comparable<T>> void printNode(nodeList<T> list,int index){
        try {
            Node<T> node = list.get(index);
            System.out.println("List["+index+"] = "+node);
        } catch (InvalidIndexException e) {
            System.err.println("List["+index+"] = Invalid Index!");
        }
    }
    public static void main(String[] args) {
        nodeList<Double> list = new nodeList<>(new Double[] {1.0, 2.0, 3.0, 4.0});

        printListInfo(list);
        
        list.append(5.0);
        printListInfo(list);


        printNode(list, 2);        
        printNode(list, 4);
        System.out.println();
        
        try {
            list.remove(2);
        } catch (InvalidIndexException e) {
            System.err.print("Invalid index!\n\n");
        }

        printListInfo(list);

        try {
            list.replace(5.0, 2);
        } catch (InvalidIndexException e) {
            System.err.print("Invalid index!\n\n");
        }

        printListInfo(list);

        try {
            list.insert(5.0, 0);
        } catch (InvalidIndexException e) {
            System.err.print("Invalid index!\n\n");
        }
        
        printListInfo(list);

        list.forEach(node -> node.value = node.value+1.0);
        printListInfo(list);
        
        list.sort();
        printListInfo(list);

        int index = 0;
        while ( (index = list.search(6.0)) != -1){
            index = list.search(6.0);
            try {
                list.remove(index);
            } catch (InvalidIndexException e) {}
        }
        printListInfo(list);
    }
}
