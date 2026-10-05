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

    // Store key -> value. If the key exists, replace the value and return the old one; otherwise return null.
    // Ranger key -> value. Si la clé existe, remplacer la valeur et renvoyer l'ancienne ; sinon renvoyer null.
    // put("Ada", 36) -> returns null    put("Ada", 40) -> returns 36 (old value)
    public V put(K key, V value) {
        throw new UnsupportedOperationException("TODO");
    }

    // Return the value of key, or null if absent.
    // Renvoyer la valeur de key, ou null si absente.
    // get("Ada") -> 36    absent -> null
    public V get(K key) {
        throw new UnsupportedOperationException("TODO");
    }

    // Remove key and return its value, or null if absent.
    // Supprimer key et renvoyer sa valeur, ou null si absente.
    // remove("Ada") -> returns 36    absent -> null
    public V remove(K key) {
        throw new UnsupportedOperationException("TODO");
    }

    // Return true if key is present.
    // Renvoyer true si key est présente.
    public boolean containsKey(K key) {
        throw new UnsupportedOperationException("TODO");
    }

    // Return the number of keys.
    // Renvoyer le nombre de clés.
    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    // Return true if there is no key.
    // Renvoyer true s'il n'y a aucune clé.
    public boolean isEmpty() {
        throw new UnsupportedOperationException("TODO");
    }
}
