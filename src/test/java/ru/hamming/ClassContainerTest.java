package ru.hamming;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hamming.untils.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

public class ClassContainerTest {

    private ClassContainer<String> container;

    @BeforeEach
    void setUp() {
        container = new ClassContainer<>(5);
    }

    @Test
    void testConstructor() {
        assertEquals(5, container.getSizeContainer());
        assertTrue(container.getContainerValues().isEmpty());
    }

    @Test
    void testAdd() {
        container.add("test1");
        container.add("test2");

        assertEquals(2, container.getContainerValues().size());
        assertEquals("test1", container.get(0));
        assertEquals("test2", container.get(1));
    }

    @Test
    void testAddAtIndex() {
        container.add("first");
        container.add(0, "newFirst"); // Добавление в начало
        container.add(1, "middle");   // Добавление в середину

        assertEquals(3, container.getContainerValues().size());
        assertEquals("newFirst", container.get(0));
        assertEquals("middle", container.get(1));
        assertEquals("first", container.get(2));
    }

    @Test
    void testGet() {
        container.add("value1");
        container.add("value2");

        assertEquals("value1", container.get(0));
        assertEquals("value2", container.get(1));
    }

    @Test
    void testRemoveByValue() {
        container.add("toRemove");
        container.add("toKeep");

        assertTrue(container.remove("toRemove"));
        assertFalse(container.remove("nonExistent"));
        assertEquals(1, container.getContainerValues().size());
        assertEquals("toKeep", container.get(0));
    }

    @Test
    void testRemoveByIndex() {
        container.add("first");
        container.add("second");
        container.add("third");

        String removed = container.remove(1);
        assertEquals("second", removed);
        assertEquals(2, container.getContainerValues().size());
        assertEquals("first", container.get(0));
        assertEquals("third", container.get(1));
    }

    @Test
    void testGetContainerValuesCopy() {
        container.add("original");
        LinkedList<String> copy = container.getContainerValues();

        // Проверка, что возвращается копия, а не оригинал
        assertEquals(1, copy.size());
        assertEquals("original", copy.get(0));

        // Изменение копии не должно влиять на оригинал
        copy.add("newInCopy");
        assertEquals(1, container.getContainerValues().size());
        assertEquals(2, copy.size());
    }

    @Test
    void testGetOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(0));

        container.add("test");
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(1));
    }

    @Test
    void testRemoveOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(0));

        container.add("test");
        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(1));
    }

    @Test
    void testAddOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> container.add(-1, "test"));
        assertThrows(IndexOutOfBoundsException.class, () -> container.add(1, "test"));

        // Это должно работать
        container.add(0, "test");
        assertEquals(1, container.getContainerValues().size());
    }
}