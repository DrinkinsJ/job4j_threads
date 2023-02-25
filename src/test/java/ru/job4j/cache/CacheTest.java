package ru.job4j.cache;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class CacheTest {

    @Test
    public void whenBaseReceiveData() {
        Map<Integer, Base> map = new HashMap<>();
        Base base = new Base(1, 0);
        map.put(base.getId(), base);

        Base user1 = map.get(1);
        user1.setName("User 1");


        Base user2 = map.get(1);
        user1.setName("User 2");

        map.put(user1.getId(), user1);
        map.put(user2.getId(), user2);

        assertThat(map.get(base.getId()).getName()).isEqualTo("User 2");
        assertThat(map.get(base.getId()).getVersion()).isZero();
    }

    @Test
    public void whenCacheReceiveAndVersionIncrease() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        Base base2 = new Base(1, 0);

        cache.add(base);
        cache.update(base2);
        assertThat(cache.get(base.getId()).getVersion()).isEqualTo(1);
    }

    @Test
    public void whenOptimisticException() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        cache.add(base);
        cache.update(base);
        assertThat(catchThrowable(() -> cache.update(base))).isInstanceOf(OptimisticException.class);
    }
}