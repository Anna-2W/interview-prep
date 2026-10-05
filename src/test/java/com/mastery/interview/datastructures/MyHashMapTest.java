package com.mastery.interview.datastructures;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

class MyHashMapTest {

    record SameHash(String name) {
        @Override
        public int hashCode() {
            return 42;
        }
    }

    record NegativeHash(int id) {
        @Override
        public int hashCode() {
            return -id;
        }
    }

    @Test
    void newMapIsEmpty() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        assertThat(map.isEmpty()).isTrue();
        assertThat(map.size()).isZero();
        assertThat(map.get("Ada")).isNull();
    }

    @Test
    void putAndGet() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        assertThat(map.put("Ada", 36)).isNull();
        map.put("Bob", 30);
        assertThat(map.get("Ada")).isEqualTo(36);
        assertThat(map.get("Bob")).isEqualTo(30);
        assertThat(map.size()).isEqualTo(2);
    }

    @Test
    void putExistingKeyReplaces() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Ada", 36);
        assertThat(map.put("Ada", 40)).isEqualTo(36);
        assertThat(map.get("Ada")).isEqualTo(40);
        assertThat(map.size()).isEqualTo(1);
    }

    @Test
    void keysComparedWithEquals() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put(new String("Ada"), 36);
        assertThat(map.get(new String("Ada"))).isEqualTo(36);
        assertThat(map.containsKey(new String("Ada"))).isTrue();
    }

    @Test
    void remove() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("Ada", 36);
        map.put("Bob", 30);
        assertThat(map.remove("Ada")).isEqualTo(36);
        assertThat(map.remove("Ada")).isNull();
        assertThat(map.containsKey("Ada")).isFalse();
        assertThat(map.size()).isEqualTo(1);
    }

    @Test
    void collisionsInTheSameBucket() {
        MyHashMap<SameHash, Integer> map = new MyHashMap<>();
        map.put(new SameHash("a"), 1);
        map.put(new SameHash("b"), 2);
        map.put(new SameHash("c"), 3);
        assertThat(map.get(new SameHash("b"))).isEqualTo(2);
        assertThat(map.remove(new SameHash("b"))).isEqualTo(2);
        assertThat(map.get(new SameHash("a"))).isEqualTo(1);
        assertThat(map.get(new SameHash("c"))).isEqualTo(3);
        assertThat(map.get(new SameHash("b"))).isNull();
        assertThat(map.size()).isEqualTo(2);
    }

    @Test
    void negativeHashCodes() {
        MyHashMap<NegativeHash, String> map = new MyHashMap<>();
        for (int i = 1; i <= 50; i++) {
            map.put(new NegativeHash(i), "v" + i);
        }
        assertThat(map.get(new NegativeHash(37))).isEqualTo("v37");
    }

    @Test
    void stillCorrectAfterManyResizes() {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 1000; i++) {
            map.put(i, i * i);
        }
        assertThat(map.size()).isEqualTo(1000);
        for (int i = 0; i < 1000; i++) {
            assertThat(map.get(i)).isEqualTo(i * i);
        }
    }

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
    void millionPutsAreFast() {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 1_000_000; i++) {
            map.put(i, i);
        }
        assertThat(map.get(777_777)).isEqualTo(777_777);
        assertThat(map.size()).isEqualTo(1_000_000);
    }
}
