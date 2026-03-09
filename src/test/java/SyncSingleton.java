import com.creational.design.singleton.*;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class SyncSingleton {


    //This test demonstrates how concurrent access can create multiple instances, breaking the Singleton contract. In a proper Singleton, instances should be 1.
    //But due to race conditions, we might get multiple instances.
    @Test
    void givenUnsafeSingleton_whenAccessedConcurrently_thenMultipleInstancesCreated() throws InterruptedException {
        int threadCount = 1000;
        Set<Singleton> instances = ConcurrentHashMap.newKeySet();
        CountDownLatch latch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                instances.add(Singleton.getInstance());
                latch.countDown();
            }).start();
        }
        latch.await();
        assertTrue(instances.size() > 1, "Multiple instances were created");
    }



    //// This guarantees mutual exclusion but introduces
    // performance overhead, as synchronization happens on every access
    @Test
    void givenMultipleThreads_whenUsingSynchronizedSingleton_thenOnlyOneInstanceCreated() {
        Set<Object> instances = ConcurrentHashMap.newKeySet();
        IntStream.range(0, 100).parallel().forEach(i ->
                instances.add(SynchronizedSingleton.getInstance()));
        assertEquals(1, instances.size());
    }


    //It’s inherently thread-safe, as the JVM guarantees class initialization is atomic. The downside? The instance is
    //created even if never used, which may not be optimal for expensive resources:
    @Test
    void whenCallingEagerSingleton_thenSameInstanceReturned() {
        assertSame(EagerSingleton.getInstance(), EagerSingleton.getInstance());
    }



   // This approach improves performance by avoiding synchronization once the instance is initialized. The volatile keyword ensures visibility of changes across threads.
    //It has a good use case for high-concurrency environments where performance matters.
    @Test
    void givenDCLSingleton_whenAccessedFromThreads_thenOneInstanceCreated() {
        List<Object> instances = Collections.synchronizedList(new ArrayList<>());
        IntStream.range(0, 100).parallel().forEach(i ->
                instances.add(DoubleCheckLocking.getInstance()));
        assertEquals(1, new HashSet<>(instances).size());
    }


    //The class remains unloaded until the system references it,
            //which ensures both laziness and thread safety without synchronization:
    @Test
    void testThreadSafety() throws InterruptedException {
        int numberOfThreads = 10;
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        Set<BillpughSingleton> instances = ConcurrentHashMap.newKeySet();

        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(() -> {
                instances.add(BillpughSingleton.getInstance());
                latch.countDown();
            }).start();
        }

        latch.await(5, TimeUnit.SECONDS);

        assertEquals(1, instances.size(), "All threads should get the same instance");
    }


    //It’s also serialization-safe and reflection-safe.
    //Enums provide a robust Singleton solution. The JVM instantiates enum values only once:
    @Test
    void givenEnumSingleton_whenAccessedConcurrently_thenSingleInstanceCreated()
            throws InterruptedException {
        Set<EnumSingleton> instances = ConcurrentHashMap.newKeySet();
        CountDownLatch latch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                instances.add(EnumSingleton.INSTANCE);
                latch.countDown();
            }).start();
        }

        latch.await();
        assertEquals(1, instances.size());
    }

}

