package com.mastery.interview.datastructures;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class MyCircularQueueTest {

    @Test
    void firstInFirstOut() {
        MyCircularQueue queue = new MyCircularQueue(3);
        queue.offer(1);
        queue.offer(2);
        assertThat(queue.poll()).isEqualTo(1);
        assertThat(queue.peek()).isEqualTo(2);
        assertThat(queue.size()).isEqualTo(1);
    }

    @Test
    void refusesWhenFull() {
        MyCircularQueue queue = new MyCircularQueue(2);
        assertThat(queue.offer(1)).isTrue();
        assertThat(queue.offer(2)).isTrue();
        assertThat(queue.isFull()).isTrue();
        assertThat(queue.offer(3)).isFalse();
        assertThat(queue.size()).isEqualTo(2);
    }

    @Test
    void wrapsAround() {
        MyCircularQueue queue = new MyCircularQueue(4);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.poll();
        queue.offer(4);
        assertThat(queue.offer(5)).isTrue();
        assertThat(queue.isFull()).isTrue();
        assertThat(queue.poll()).isEqualTo(2);
        assertThat(queue.poll()).isEqualTo(3);
        assertThat(queue.poll()).isEqualTo(4);
        assertThat(queue.poll()).isEqualTo(5);
        assertThat(queue.isEmpty()).isTrue();
    }

    @Test
    void manyRoundsInSmallQueue() {
        MyCircularQueue queue = new MyCircularQueue(3);
        for (int i = 0; i < 100; i++) {
            queue.offer(i);
            assertThat(queue.poll()).isEqualTo(i);
        }
        assertThat(queue.isEmpty()).isTrue();
    }

    @Test
    void emptyQueueThrows() {
        MyCircularQueue queue = new MyCircularQueue(2);
        assertThat(queue.isEmpty()).isTrue();
        assertThat(queue.isFull()).isFalse();
        assertThatThrownBy(queue::poll).isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(queue::peek).isInstanceOf(NoSuchElementException.class);
    }
}
