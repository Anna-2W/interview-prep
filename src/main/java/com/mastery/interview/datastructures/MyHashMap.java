package com.mastery.interview.datastructures;

// 02 Data structures by hand: book/en/02-data-structures-by-hand.md, section 6
// Chaining. Keys are never null. Double the capacity when size > 0.75 * capacity.
public class MyHashMap<K, V> {

    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Entry<K, V>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = (Entry<K, V>[]) new Entry[16];
    }

    // put("Ada", 36) -> returns null    put("Ada", 40) -> returns 36 (old value)
    public V put(K key, V value) {
        throw new UnsupportedOperationException("TODO");
    }

    // get("Ada") -> 36    absent -> null
    public V get(K key) {
        throw new UnsupportedOperationException("TODO");
    }

    // remove("Ada") -> returns 36    absent -> null
    public V remove(K key) {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean containsKey(K key) {
        throw new UnsupportedOperationException("TODO");
    }

    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }
}
