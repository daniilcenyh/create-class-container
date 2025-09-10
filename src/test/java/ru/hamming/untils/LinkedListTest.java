package ru.hamming.untils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {

    private LinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new LinkedList<>();
    }

    @Test
    void testAddAndGet() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testAddAtIndex() {
        list.add(0, 10);
        list.add(1, 20);
        list.add(1, 15); // Вставка в середину

        assertEquals(3, list.size());
        assertEquals(10, list.get(0));
        assertEquals(15, list.get(1));
        assertEquals(20, list.get(2));
    }

    @Test
    void testAddAtIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, 1));
    }

    @Test
    void testRemoveByIndex() {
        list.add(1);
        list.add(2);
        list.add(3);

        Integer removed = list.remove(1);
        assertEquals(2, removed);
        assertEquals(2, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    void testRemoveByValue() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.remove(Integer.valueOf(2)));
        assertFalse(list.remove(Integer.valueOf(5))); // Несуществующий элемент
        assertEquals(2, list.size());
    }

    @Test
    void testRemoveFirstElement() {
        list.add(1);
        list.add(2);

        Integer removed = list.remove(0);
        assertEquals(1, removed);
        assertEquals(1, list.size());
        assertEquals(2, list.get(0));
    }

    @Test
    void testRemoveLastElement() {
        list.add(1);
        list.add(2);

        Integer removed = list.remove(1);
        assertEquals(2, removed);
        assertEquals(1, list.size());
        assertEquals(1, list.get(0));
    }

    @Test
    void testRemoveOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
    }

    @Test
    void testClear() {
        list.add(1);
        list.add(2);
        list.clear();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testContains() {
        list.add(1);
        list.add(2);

        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertFalse(list.contains(3));
    }

    @Test
    void testIndexOf() {
        list.add(1);
        list.add(2);
        list.add(1); // Дубликат

        assertEquals(0, list.indexOf(1));
        assertEquals(1, list.indexOf(2));
        assertEquals(-1, list.indexOf(3));
    }

    @Test
    void testToArray() {
        list.add(1);
        list.add(2);
        list.add(3);

        Object[] array = list.toArray();
        assertEquals(3, array.length);
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
        assertEquals(3, array[2]);
    }

    @Test
    void testCopy() {
        list.add(1);
        list.add(2);
        list.add(3);

        LinkedList<Integer> copy = list.copy();
        assertEquals(list.size(), copy.size());
        assertEquals(list.get(0), copy.get(0));
        assertEquals(list.get(1), copy.get(1));
        assertEquals(list.get(2), copy.get(2));

        // Проверка, что копия независима
        copy.add(4);
        assertEquals(3, list.size());
        assertEquals(4, copy.size());
    }

    @Test
    void testDeepCopy() {
        list.add(1);
        list.add(2);

        LinkedList<Integer> deepCopy = list.deepCopy();
        assertEquals(list.size(), deepCopy.size());
        assertEquals(list.get(0), deepCopy.get(0));
        assertEquals(list.get(1), deepCopy.get(1));
    }

    @Test
    void testCopyConstructor() {
        list.add(1);
        list.add(2);

        LinkedList<Integer> copy = new LinkedList<>(list);
        assertEquals(list.size(), copy.size());
        assertEquals(list.get(0), copy.get(0));
        assertEquals(list.get(1), copy.get(1));
    }

    @Test
    void testToString() {
        assertTrue(list.toString().equals("[]"));

        list.add(1);
        list.add(2);
        list.add(3);

        String result = list.toString();
        assertTrue(result.equals("[1, 2, 3]") || result.equals("[1,2,3]"));
    }

    @Test
    void testGetOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));

        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }
}