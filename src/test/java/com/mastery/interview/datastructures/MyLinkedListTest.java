package com.mastery.interview.datastructures;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

class MyLinkedListTest {

    private MyLinkedList<String> listOf(String... values) {
        MyLinkedList<String> list = new MyLinkedList<>();
        for (String v : values) {
            list.addLast(v);
        }
        return list;
    }

    @Test
    void newListIsEmpty() {
        MyLinkedList<String> list = new MyLinkedList<>();
        assertThat(list.isEmpty()).isTrue();
        assertThat(list.size()).isZero();
    }

    @Test
    void addLastKeepsOrder() {
        MyLinkedList<String> list = listOf("a", "b", "c");
        assertThat(list.size()).isEqualTo(3);
        assertThat(list.get(0)).isEqualTo("a");
        assertThat(list.get(2)).isEqualTo("c");
    }

    @Test
    void addFirstGoesToTheFront() {
        MyLinkedList<String> list = listOf("b", "c");
        list.addFirst("a");
        assertThat(list.get(0)).isEqualTo("a");
        assertThat(list.get(1)).isEqualTo("b");
        assertThat(list.size()).isEqualTo(3);
    }

    @Test
    void addFirstThenAddLastOnEmptyList() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.addFirst("a");
        list.addLast("b");
        assertThat(list.get(0)).isEqualTo("a");
        assertThat(list.get(1)).isEqualTo("b");
    }

    @Test
    void removeFirst() {
        MyLinkedList<String> list = listOf("a", "b");
        assertThat(list.removeFirst()).isEqualTo("a");
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0)).isEqualTo("b");
    }

    @Test
    void removeLast() {
        MyLinkedList<String> list = listOf("a", "b", "c");
        assertThat(list.removeLast()).isEqualTo("c");
        assertThat(list.removeLast()).isEqualTo("b");
        list.addLast("z");
        assertThat(list.get(1)).isEqualTo("z");
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void emptyAgainAfterRemovingEverything() {
        MyLinkedList<String> list = listOf("a");
        list.removeFirst();
        assertThat(list.isEmpty()).isTrue();
        list.addLast("b");
        assertThat(list.get(0)).isEqualTo("b");
        assertThat(list.removeLast()).isEqualTo("b");
        assertThat(list.isEmpty()).isTrue();
    }

    @Test
    void removeOnEmptyThrows() {
        MyLinkedList<String> list = new MyLinkedList<>();
        assertThatThrownBy(list::removeFirst).isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(list::removeLast).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void badIndexThrows() {
        MyLinkedList<String> list = listOf("a");
        assertThatThrownBy(() -> list.get(1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> list.get(-1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void contains() {
        MyLinkedList<String> list = listOf("a", "b");
        assertThat(list.contains(new String("b"))).isTrue();
        assertThat(list.contains("z")).isFalse();
    }

    @Test
    void reverseInPlace() {
        MyLinkedList<String> list = listOf("a", "b", "c");
        list.reverse();
        assertThat(list.get(0)).isEqualTo("c");
        assertThat(list.get(2)).isEqualTo("a");
        list.addLast("z");
        assertThat(list.get(3)).isEqualTo("z");
    }

    @Test
    void reverseEmptyAndSingle() {
        MyLinkedList<String> empty = new MyLinkedList<>();
        empty.reverse();
        assertThat(empty.isEmpty()).isTrue();
        MyLinkedList<String> one = listOf("a");
        one.reverse();
        assertThat(one.get(0)).isEqualTo("a");
    }

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
    void millionAddsAtBothEndsAreFast() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 0; i < 1_000_000; i++) {
            list.addLast(i);
            list.addFirst(i);
        }
        assertThat(list.size()).isEqualTo(2_000_000);
    }
}
