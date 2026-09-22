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
    public void swap(){
        //order nodes
        ArrayList<E> order = new ArrayList<E>(); 
        Node<E> cur = head;
        while (cur != null) {
            order.add(cur.getElement());
            cur = cur.getNext();
        }
        Collections.sort(order);

        Node<E> swap1 = head; //first Node to be swapped
        Node<E> prev1 = head; //Node prior to first Node to be swapped
        Node<E> next1 = swap1.getNext(); //Node after Node to be swapped
        while (next1 != null && order.size() != 0) {
            if (order.contains(swap1.getElement())) {
                int index1 = order.indexOf(swap1.getElement()); //order of first Node
                int index2 = order.size() - index1 - 1;

                if (index1 != index2) {
                    //look for Node to be swapped with
                    Node<E> prev2 = swap1;
                    Node<E> swap2 = swap1.getNext();
                    while (!(swap2.getElement().equals(order.get(index2)))) {
                        prev2 = swap2;
                        swap2 = swap2.getNext();
                    }

                    //swap Nodes
                    prev1.setNext(swap2);
                    swap1.setNext(swap2.getNext());
                    if (swap2 == next1) { //swap2 is the immediate next Node
                        swap2.setNext(swap1);
                    } else {
                        prev2.setNext(swap1);
                        swap2.setNext(next1);
                    }
                    if (swap1 == head) {
                        head = swap2;
                    }
                    if (swap2 == tail) {
                        tail = swap1;
                    }

                    prev1 = swap2;
                    //avoid double-swapping
                    if (index1 < index2) {
                        order.remove(index1);
                        order.remove(index2 - 1);
                    } else {
                        order.remove(index2);
                        order.remove(index1 - 1);
                    }

                } else {
                    prev1 = swap1;
                    order.remove(index1);
                }
            } else {
                prev1 = swap1;
            }

            //reset for next swap
            swap1 = next1;
            next1 = swap1.getNext();
        }
    }
   
}

