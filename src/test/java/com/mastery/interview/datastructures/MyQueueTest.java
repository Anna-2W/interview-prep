package com.mastery.interview.datastructures;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

class MyQueueTest {

    @Test
    void firstInFirstOut() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.offer(5);
        queue.offer(8);
        queue.offer(2);
        assertThat(queue.size()).isEqualTo(3);
        assertThat(queue.poll()).isEqualTo(5);
        assertThat(queue.poll()).isEqualTo(8);
        assertThat(queue.poll()).isEqualTo(2);
        assertThat(queue.isEmpty()).isTrue();
    }

    @Test
    void peekDoesNotRemove() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.offer(5);
        queue.offer(8);
        assertThat(queue.peek()).isEqualTo(5);
        assertThat(queue.size()).isEqualTo(2);
    }

    @Test
    void emptyQueueReturnsNull() {
        MyQueue<Integer> queue = new MyQueue<>();
        assertThat(queue.poll()).isNull();
        assertThat(queue.peek()).isNull();
    }

    @Test
    void reusableAfterBecomingEmpty() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.offer(1);
        queue.poll();
        queue.offer(2);
        queue.offer(3);
        assertThat(queue.poll()).isEqualTo(2);
        assertThat(queue.poll()).isEqualTo(3);
        assertThat(queue.poll()).isNull();
    }

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
    void millionOperationsAreFast() {
        MyQueue<Integer> queue = new MyQueue<>();
        for (int i = 0; i < 1_000_000; i++) {
            queue.offer(i);
        }
        long sum = 0;
        for (int i = 0; i < 1_000_000; i++) {
            sum += queue.poll();
        }
        assertThat(sum).isEqualTo(499_999_500_000L);
    }
}
