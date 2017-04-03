package ca.bcit.comp2526.a3;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Generic Linked List.
 * @author Yashar Nesvaderani
 * @version 1.0
 * @param <E> Object type of the list.
 */
public class LinkedList<E> implements Collection<E> {
    
    // first element in the list
    private Node head;
    
    // last element in the list
    private Node tail;
    
    /**
     * Default constructor to set up empty list.
     */
    public LinkedList() {
        head = null;
        tail = null;
    }
    
    /**
     * Constructor to set up list with one element.
     * @param element element to add to list
     */
    public LinkedList(E element) {
        head = tail = new Node(element);
        
    }
    
    /**
     * Returns the head of the list.
     * @return the head as a Node
     */
    public Node getHead() {
        return head;
    }


    /**
     * Returns the tail of the list.
     * @return the tail as a Node
     */
    public Node getTail() {
        return tail;
    }

    // node that holds data of type E
    protected class Node {
        // the data held by the node
        private E data;
        
        // the previous node in the list
        private Node previous;
        
        // the next node in the list
        private Node next;
        
        /**
         * Constructor to set up node with its data.
         * @param data object to be added to node.
         */
        public Node(E data) {
            this.data = data;
        }

        /**
         * Returns the data held in the node.
         * @return the data in the Node
         */
        public E getData() {
            return data;
        }

        /**
         * Sets the data in the node.
         * @param data in the Node
         */
        public void setData(E data) {
            this.data = data;
        }

        /**
         * Returns the previous node.
         * @return previous as a Node
         */
        public Node getPrevious() {
            return previous;
        }

        /**
         * Returns the next node.
         * @return next as a Node
         */
        public Node getNext() {
            return next;
        }

    }
    
    /**
     * Returns the number of elements in the list.
     * @return size of list as an integer
     */
    @Override
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    /**
     * Returns true if list is empty, false otherwise.
     * @return true if list empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    /**
     * Returns true if list contains specified object.
     * @param object to check if list contains.
     * @return true if list contains object, false otherwise.
     */
    @Override
    public boolean contains(Object object) {
        Node current = head;
        
        while (current != null) {
            if (current.data.equals(object)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Returns an iterator to move through elements of the list
     * @return ListIterator of the same type as the list.
     */
    @Override
    public ListIterator<E> iterator() {
        
        return new ListIterator<E>() {
            // Current node the iterator is on. Starts at head.
            private Node current = head;
            
            // initial index of the iterator.
            private int index = 0;
            
            /**
             * Returns true if current node has a next element in the list, false otherwise.
             * @return true if there is a next element in the list, false otherwise.
             */
            @Override
            public boolean hasNext() {
                return index < size();     
            }
            
            /**
             * Returns the next data member of the list and moves forward in the list.
             * @return object of specified list type
             */
            @Override
            public E next() {
                if (index == 0) {
                    current = current.next;
                    index++;
                    return head.data; 
                } else if (index == size() - 1) {
                    index++;
                    current = tail;
                    return tail.data;               
                } else if (index == size()) {
                    return null;
                } else {
                    Node temp = current;
                    current = current.next;
                    index++;
                    return temp.data;
                }             
            }
            
            /**
             * Returns true if current node has a previous element in the list, false otherwise.
             * @return true if there's a previous element in the list, false otherwise
             */
            @Override
            public boolean hasPrevious() {
                return index > 0;
            }
            
            /**
             * Return the previous element of the current node and moves to that previous node.
             * @return the previous data object
             */
            @Override
            public E previous() {
                if (index == size()) {
                    index--;
                    current = current.previous;
                    return tail.data; 
                } else if (index == 1) {
                    index--;
                    return head.data;               
                } else if (index == 0) {
                    return null;
                } else {
                    Node temp = current;
                    current = current.previous;
                    index--;
                    return temp.data;
                }          
            }
            
            /**
             * Returns the index of the next element in the list.
             * @return index as an integer
             */
            @Override
            public int nextIndex() {
                if (current != tail) {
                    return index;
                } else {
                    return size();
                }
            }
            
            /**
             * Returns the index of the previous element in the list.
             * @return index as an integer
             */
            @Override
            public int previousIndex() {
                return index - 1;
            }
            
            /**
             * Unused.
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
            
            /**
             * Unused.
             */
            @Override
            public void set(E element) {
                throw new UnsupportedOperationException();
            }
            
            /**
             * Unused.
             */
            @Override
            public void add(E element) {
                throw new UnsupportedOperationException();          
            }
            
        };
    }
    
    /**
     * Converts the linked list to an array of Object.
     * @return list as an Object array.
     */
    @Override
    public Object[] toArray() {
        Iterator<E> iterate = this.iterator();
        Object[] array = new Object[size()];
        int index = 0;
   
        while (iterate.hasNext()) {
            array[index] = iterate.next();
            index++;
        }
        return array;
    }
    
    /**
     * Converts linked list to array of type T.
     * @return list as a T array.
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] array) {
        Node current = head;
        if (size() > array.length) {
            array = (T[]) Arrays.copyOf(array, size());
        }
        
        for (int i = 0; i < size(); i++) {
            array[i] = (T) current.data;
            current = current.next;
        }
        return array;
    }

    /**
     * Adds the element to the end of the list.
     * @return true
     * @param element the element to be added to the list.
     */
    @Override
    public boolean add(E element) {
        if (head == null) {
            head = new Node(element);
            tail = head;
        } else {
            Node node = new Node(element);
            tail.next = node;
            node.previous = tail;
            tail =  node;
        }
        return true;
    }
    
    /**
     * Removes all instances of the specified object data from the list.
     * @param object to search for and remove from list
     * @return returns true if data was removed from list, false otherwise
     */
    @Override
    public boolean remove(Object object) {
        boolean result = false;
        if (size() == 0) {
            return result;
        } else if (size() == 1) {
            if (head.data.equals(object)) {
                head = null;
                tail = null;
            }
        } else {
            Node current = head;
            
            while (current != tail && current != null) {
                
                if (head.data.equals(object)) {
                    head = head.next;
                    head.previous = null;
                    result = true;
                }
                
                if (current.data.equals((object))) {
                    result = true;
                    if (current.previous != null) {
                        current.previous.next = current.next; 
                    }             
                    if (current.next != null) {
                        current.next.previous = current.previous;
                    }
                }
                current = current.next;
            }
            
            if (tail.data.equals(object)) {
                if (size() == 1) {
                    tail = head = null;
                    
                } else {
                    tail = tail.previous;
                    tail.next = null;
                }
                result = true;
            }
            
           
        }
        return result;
    }
    
    /**
     * Returns true if all members of specified collection are in the list.
     * @param collection Collection of elements to compare to list.
     * @return true if collection is in list, false otherwise
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        Iterator<?> iterate = collection.iterator();
        
        while (iterate.hasNext()) {
            if (!this.contains(iterate.next())) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Adds all elements of specified collection the end of the list.
     * @param collection Collection to add to list
     * @return returns false;
     */
    @Override
    public boolean addAll(Collection<? extends E> collection) {
 
        for (E element : collection) {
            add(element);
        }
        return false;
    }
    
    /**
     * Removes all element in specified collection from the list.
     * @param collection Collection of element to remove from list.
     * @return returns false
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        Iterator<?> iterate = collection.iterator();
        
        while (iterate.hasNext()) {
            this.remove(iterate.next());
        }
        
        return false;
    }
    
    /**
     * Retains all elements in the list that are in the collection, 
     * removes elements not in collection.
     * @param collection Collection of elements to retain.
     * @return returns false
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        Node temp = head;
        
        while (temp != null) {
            
            if (!collection.contains(temp.data)) {
                remove(temp.data);
            }
            temp = temp.next;
        }
        return false;
    }
    
    /**
     * Clears the list of every element.
     */
    @Override
    public void clear() {
        Node current = head.next;
        
        while (current != null) {
            current.previous = null;
            current.next = null;
            current = current.next;
        }
        head = null;
        tail = null;
    }
    
    
}
