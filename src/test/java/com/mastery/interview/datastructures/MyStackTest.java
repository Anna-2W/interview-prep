package com.mastery.interview.datastructures;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

class MyStackTest {

    @Test
    void lastInFirstOut() {
        MyStack stack = new MyStack();
        stack.push(5);
        stack.push(8);
        stack.push(2);
        assertThat(stack.size()).isEqualTo(3);
        assertThat(stack.pop()).isEqualTo(2);
        assertThat(stack.pop()).isEqualTo(8);
        assertThat(stack.pop()).isEqualTo(5);
        assertThat(stack.isEmpty()).isTrue();
    }

    @Test
    void peekDoesNotRemove() {
        MyStack stack = new MyStack();
        stack.push(5);
        stack.push(8);
        assertThat(stack.peek()).isEqualTo(8);
        assertThat(stack.size()).isEqualTo(2);
    }

    @Test
    void growsBeyondInitialCapacity() {
        MyStack stack = new MyStack();
        for (int i = 0; i < 50; i++) {
            stack.push(i);
        }
        assertThat(stack.pop()).isEqualTo(49);
        assertThat(stack.size()).isEqualTo(49);
    }

    @Test
    void emptyStackThrows() {
        MyStack stack = new MyStack();
        assertThat(stack.isEmpty()).isTrue();
        assertThatThrownBy(stack::pop).isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(stack::peek).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
    void millionPushesAreFast() {
        MyStack stack = new MyStack();
        for (int i = 0; i < 1_000_000; i++) {
            stack.push(i);
        }
        assertThat(stack.peek()).isEqualTo(999_999);
    }
}
