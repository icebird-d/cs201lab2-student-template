import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap() {

        //order nodes based on value
        ArrayList<Node<E>> order = new ArrayList<Node<E>>();
        order.add(head);
        Node<E> cur = head.getNext();
        while (cur != null) {
            if (order.get(0).getElement().compareTo(cur.getElement()) < 0) { //smallest
                order.add(0, cur);
            } else if (order.get(order.size() - 1).getElement().compareTo(cur.getElement()) > 0) { //largest
                order.add(cur);
            } else {
                for (int i = 0; i < order.size() - 1; i++) {
                    if (order.get(i).getElement().compareTo(cur.getElement()) > 0 &&
                            order.get(i+1).getElement().compareTo(cur.getElement()) < 0) {
                            order.add(i+1, cur);
                        }
                }
            }
            cur = cur.getNext();
        }

        HashMap<Node<E>, Node<E>> map = new HashMap<Node<E>, Node<E>>(order.size() / 2);
        for (int j = 0; j < ((order.size() / 2) + 1); j++) {
            map.put(order.get(j), order.get(order.size()-j-1));
            map.put(order.get(order.size()-j-1), order.get(j));
        }
        //HashMap map maps each Node to its partner value

        //swapped SinglyLinkedList
        SinglyLinkedList<E> result = new SinglyLinkedList<E>();
        Node<E> swap1 = head; //first Node from original SLL to be swapped
        while (swap1 != null) {
            Node<E> swap2 = map.get(swap1); //Node to be swapped with
            result.addLast(swap2.getElement());
            swap1 = swap1.getNext();
        }

        //result SLL is now this SLL
        this.head = result.head;
        this.tail = result.tail;
    }
   
}

