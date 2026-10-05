package com.mastery.interview.datastructures;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

class MyArrayListTest {

    private MyArrayList<String> listOf(String... values) {
        MyArrayList<String> list = new MyArrayList<>();
        for (String v : values) {
            list.add(v);
        }
        return list;
    }

    @Test
    void newListIsEmpty() {
        MyArrayList<String> list = new MyArrayList<>();
        assertThat(list.isEmpty()).isTrue();
        assertThat(list.size()).isZero();
    }

    @Test
    void addAndGet() {
        MyArrayList<String> list = listOf("a", "b", "c");
        assertThat(list.size()).isEqualTo(3);
        assertThat(list.isEmpty()).isFalse();
        assertThat(list.get(0)).isEqualTo("a");
        assertThat(list.get(2)).isEqualTo("c");
    }

    @Test
    void growsBeyondInitialCapacity() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        assertThat(list.size()).isEqualTo(100);
        assertThat(list.get(99)).isEqualTo(99);
    }

    @Test
    void addAtIndexShiftsRight() {
        MyArrayList<String> list = listOf("a", "c");
        list.add(1, "b");
        list.add(0, "z");
        list.add(4, "end");
        assertThat(list.get(0)).isEqualTo("z");
        assertThat(list.get(1)).isEqualTo("a");
        assertThat(list.get(2)).isEqualTo("b");
        assertThat(list.get(3)).isEqualTo("c");
        assertThat(list.get(4)).isEqualTo("end");
        assertThat(list.size()).isEqualTo(5);
    }

    @Test
    void setReturnsOldValue() {
        MyArrayList<String> list = listOf("a", "b");
        assertThat(list.set(0, "z")).isEqualTo("a");
        assertThat(list.get(0)).isEqualTo("z");
    }

    @Test
    void removeShiftsLeft() {
        MyArrayList<String> list = listOf("a", "b", "c");
        assertThat(list.remove(1)).isEqualTo("b");
        assertThat(list.size()).isEqualTo(2);
        assertThat(list.get(1)).isEqualTo("c");
    }

    @Test
    void containsAndIndexOf() {
        MyArrayList<String> list = listOf("a", "b", "a");
        assertThat(list.contains("b")).isTrue();
        assertThat(list.contains("z")).isFalse();
        assertThat(list.indexOf("a")).isZero();
        assertThat(list.indexOf("z")).isEqualTo(-1);
    }

    @Test
    void comparesWithEquals() {
        MyArrayList<String> list = listOf(new String("hi"));
        assertThat(list.contains(new String("hi"))).isTrue();
    }

    @Test
    void badIndexThrows() {
        MyArrayList<String> list = listOf("a");
        assertThatThrownBy(() -> list.get(1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> list.get(-1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> list.set(1, "x")).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> list.remove(1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> list.add(2, "x")).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
    void millionAddsAreFast() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            list.add(i);
        }
        assertThat(list.get(999_999)).isEqualTo(999_999);
    }
}
