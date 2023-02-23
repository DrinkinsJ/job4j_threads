package ru.job4j;

import net.jcip.annotations.ThreadSafe;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@ThreadSafe
public class SimpleBlockingQueueTest  {

    @Test
    public void simpleBlockingQueueTest() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        List<Integer> list = new ArrayList<>();

        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < queue.getLimit() ; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        producer.start();

        Thread consumer = new Thread(
                () -> {
                    for (int i = queue.getLimit(); i > 0; i--) {
                        try {
                            list.add(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.join();

        assertThat(list.get(3), is(3));
        assertThat(list, is(List.of(0,1,2,3,4,5,6,7,8,9)));
    }
}