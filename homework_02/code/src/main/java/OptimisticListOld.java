/*
 * OptimisticList.java
 *
 * From "Multiprocessor Synchronization and Concurrent Data Structures",
 * by Maurice Herlihy and Nir Shavit.
 * Copyright 2006 Elsevier Inc. All rights reserved.
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Optimistic List implementation.
 * @param T Item type.
 * @author Maurice Herlihy
 * (minor refactoring by Emanuel Onica)
 */
public class OptimisticListOld<T> {
    /**
     * First list entry
     */
    public Node head;
    /**
     * Constructor
     */
    public OptimisticListOld() {
        this.head  = new Node(Integer.MIN_VALUE);
        this.head.next = new Node(Integer.MAX_VALUE);
    }
    /**
     * Add an element.
     * @param item element to add
     * @return true iff element was not there already
     */
    public boolean add(T item) {
        int key = item.hashCode();
        while (true) {
            Node pred = this.head;
            Node current = pred.next;
            while (current.key < key) {
                pred = current; current = current.next;
            }
            pred.lock(); current.lock();
            try {
                if (validate(pred, current)) {
                    if (current.key == key) { // present
                        return false;
                    } else {               // not present
                        Node entry = new Node(item);
                        entry.next = current;
                        pred.next = entry;
                        return true;
                    }
                }
            } finally {                // always unlock
                pred.unlock(); current.unlock();
            }
        }
    }

    /**
     * Remove an element.
     * @param item element to remove
     * @return true iff element was present
     */
    public boolean remove(T item) {
        int key = item.hashCode();
        while (true) {
            Node pred = this.head;
            Node current = pred.next;
            while (current.key < key) {
                pred = current; current = current.next;
            }
            pred.lock(); current.lock();
            try {
                if (validate(pred, current)) {
                    if (current.key == key) { // present in list
                        pred.next = current.next;
                        return true;
                    } else {               // not present in list
                        return false;
                    }
                }
            } finally {                // always unlock
                pred.unlock(); current.unlock();
            }
        }
    }

    /**
     * Test whether element is present
     * @param item element to test
     * @return true iff element is present
     */
    public boolean contains(T item) {
        int key = item.hashCode();
        while (true) {
            Node pred = this.head; // sentinel node;
            Node current = pred.next;
            while (current.key < key) {
                pred = current; current = current.next;
            }
            try {
                pred.lock(); current.lock();
                if (validate(pred, current)) {
                    return (current.key == key);
                }
            } finally {                // always unlock
                pred.unlock(); current.unlock();
            }
        }
    }

    /**
     * Check that prev and current are still in list and adjacent
     * @param pred predecessor node
     * @param current current node
     * @return whther predecessor and current have changed
     */
    private boolean validate(Node pred, Node current) {
        Node entry = head;
        while (entry.key <= pred.key) {
            if (entry == pred)
                return pred.next == current;
            entry = entry.next;
        }
        return false;
    }

    /**
     * list node
     */
    public class Node {
        /**
         * actual item
         */
        T item;
        /**
         * item's hash code
         */
        int key;
        /**
         * next node in list
         */
        Node next;
        /**
         * Synchronizes node.
         */
        Lock lock;
        /**
         * Constructor for usual node
         * @param item element in list
         */
        Node(T item) {
            this.item = item;
            this.key = item.hashCode();
            lock = new ReentrantLock();
        }
        /**
         * Constructor for sentinel node
         * @param key should be min or max int value
         */
        Node(int key) {
            this.key = key;
            lock = new ReentrantLock();
        }
        /**
         * Lock entry
         */
        void lock() {lock.lock();}
        /**
         * Unlock entry
         */
        void unlock() {lock.unlock();}
    }
}