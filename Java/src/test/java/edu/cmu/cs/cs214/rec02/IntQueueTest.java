package edu.cmu.cs.cs214.rec02;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;


/**
 * TODO: 
 * 1. The {@link LinkedIntQueue} has no bugs. We've provided you with some example test cases.
 * Write your own unit tests to test against IntQueue interface with specification testing method 
 * using mQueue = new LinkedIntQueue();
 * 
 * 2. 
 * Comment `mQueue = new LinkedIntQueue();` and uncomment `mQueue = new ArrayIntQueue();`
 * Use your test cases from part 1 to test ArrayIntQueue and find bugs in the {@link ArrayIntQueue} class
 * Write more unit tests to test the implementation of ArrayIntQueue, with structural testing method
 * Aim to achieve 100% line coverage for ArrayIntQueue
 *
 * @author Alex Lockwood, George Guo, Terry Li
 */
public class IntQueueTest {

    private IntQueue mQueue;
    private List<Integer> testList;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
        // mQueue = new LinkedIntQueue();
       mQueue = new ArrayIntQueue();

        testList = new ArrayList<>(List.of(1, 2, 3));
    }

    @Test
    public void testIsEmpty() {
        // This is an example unit test
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testNotEmpty() {
        // add integer into queue
        mQueue.enqueue(testList.get(0));

        // if queue is empty, assert it as false
        assertFalse(mQueue.isEmpty());
    }

    @Test
    public void testPeekEmptyQueue() {
        // call peek(); if the return value is null, the queue is empty (True)
        assertTrue(mQueue.peek() == null);
    }

    @Test
    public void testPeekNoEmptyQueue() {
        // add integer into the queue
        mQueue.enqueue(testList.get(0));

        // call peek(); if the return value is not null, the queue is not empty (True)
        assertTrue(mQueue.peek() != null);
    }

    @Test
    public void testClear() {
        // Call Clear()
        mQueue.clear();

        // Check the status of queue
        assertTrue(mQueue.size() == 0);
    }
    
    @Test
    public void testEnqueue() {
        // This is an example unit test
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
        }
    }

    @Test
    public void testDequeueNotNull() {
        // If queue is not empty
        while (mQueue.size() != 0) {
            // Get the head value
            int headVal = mQueue.peek();

            // Record queue size
            int qSize = mQueue.size();

            // Dequeue
            int deHead = mQueue.dequeue();

            // Assert if the head is dequeued
            assertEquals(headVal, deHead);
            assertEquals(qSize - 1, mQueue.size());
        }
    }

    @Test
    public void testDequeueNull() {
        // get head element (null)
        Integer lastHead = mQueue.dequeue();

        // Check if the head is null
        assertEquals(lastHead, null);

        // Check if the size is 0
        assertEquals(0, mQueue.size());
    }

    @Test
    public void testensureCapacity() {
        // add elements to queue (3 times, 9 ints will be added)
        for (int j = 0; j < 3; j++)
            for (int i = 0; i < testList.size(); i++) 
                mQueue.enqueue(testList.get(i));
        
        // Check the size; should be 3 * 3 = 9
        assertEquals(mQueue.size(), testList.size() * 3);
        // Check the head of queue; should be equal to the 1st element of testList
        assertEquals(testList.get(0), mQueue.peek());

        // dequeue; aim to move the head; now 8 elements in queue
        mQueue.dequeue();

        // Check the size; should be 9 - 1 = 8
        assertEquals(mQueue.size(), testList.size() * 3 - 1);
        
        // add another 3 elements to queue
        // will be 11 elements in queue; need to extend queue
        for (int i = 0; i < testList.size(); i++) 
                mQueue.enqueue(testList.get(i));
        
        // Check the size; should be 11
        assertEquals(mQueue.size(), testList.size() * 4 - 1);
    }

    @Test
    public void testContent() throws IOException {
        // This is an example unit test
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println("enqueue: " + input);
                mQueue.enqueue(input);
            }

            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        }
    }


}
