package ru.hamming.untils;

/**
 * Двусвязный список с поддержкой generics.
 * Реализует основные операции со списком: добавление, удаление, поиск и другие.
 *
 * @param <T> тип элементов в списке
 * @version 1.0
 * @author Hamming
 */
public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Внутренний класс для представления узла списка.
     *
     * @param <T> тип данных узла
     */
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        /**
         * Конструктор узла.
         *
         * @param data данные для хранения в узле
         */
        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        /**
         * Конструктор копирования узла.
         *
         * @param other узел для копирования
         */
        Node(Node<T> other) {
            this.data = other.data;
            this.next = null;
            this.prev = null;
        }
    }

    /**
     * Конструктор по умолчанию.
     * Создает пустой список.
     */
    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Конструктор копирования.
     * Создает новый список с элементами из другого списка.
     *
     * @param other список для копирования
     */
    public LinkedList(LinkedList<T> other) {
        this();
        if (other != null && !other.isEmpty()) {
            Node<T> current = other.head;
            while (current != null) {
                this.add(current.data);
                current = current.next;
            }
        }
    }

    /**
     * Создает поверхностную копию списка.
     *
     * @return новая копия списка
     */
    public LinkedList<T> copy() {
        LinkedList<T> copyList = new LinkedList<>();
        if (this.isEmpty()) {
            return copyList;
        }

        Node<T> current = this.head;
        while (current != null) {
            copyList.add(current.data);
            current = current.next;
        }

        return copyList;
    }

    /**
     * Создает глубокую копию списка.
     * Попытается клонировать элементы, если они поддерживают интерфейс Cloneable.
     *
     * @return глубокая копия списка
     */
    @SuppressWarnings("unchecked")
    public LinkedList<T> deepCopy() {
        LinkedList<T> copyList = new LinkedList<>();
        if (this.isEmpty()) {
            return copyList;
        }

        Node<T> current = this.head;
        while (current != null) {
            T data = current.data;
            if (data instanceof Cloneable) {
                try {
                    data = (T) data.getClass().getMethod("clone").invoke(data);
                } catch (Exception e) {
                    // Если клонирование не удалось, используем оригинальные данные
                }
            }
            copyList.add(data);
            current = current.next;
        }

        return copyList;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param data элемент для добавления
     */
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * Добавляет элемент по указанному индексу.
     *
     * @param index индекс для вставки (от 0 до size)
     * @param data элемент для добавления
     * @throws IndexOutOfBoundsException если индекс выходит за границы
     */
    public void add(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == size) {
            add(data);
            return;
        }

        Node<T> newNode = new Node<>(data);
        if (index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else {
            Node<T> current = getNode(index);
            newNode.next = current;
            newNode.prev = current.prev;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        size++;
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за границы
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return getNode(index).data;
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index индекс элемента для удаления
     * @return удаленный элемент
     * @throws IndexOutOfBoundsException если индекс выходит за границы
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> toRemove;
        if (index == 0) {
            toRemove = head;
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
        } else if (index == size - 1) {
            toRemove = tail;
            tail = tail.prev;
            tail.next = null;
        } else {
            toRemove = getNode(index);
            toRemove.prev.next = toRemove.next;
            toRemove.next.prev = toRemove.prev;
        }
        size--;
        return toRemove.data;
    }

    /**
     * Удаляет первое вхождение указанного элемента.
     *
     * @param data элемент для удаления
     * @return true если элемент был удален, false если элемент не найден
     */
    public boolean remove(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                if (current == head) {
                    head = head.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        tail = null;
                    }
                } else if (current == tail) {
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Возвращает количество элементов в списке.
     *
     * @return размер списка
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуст ли список.
     *
     * @return true если список пуст, false в противном случае
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Очищает список, удаляя все элементы.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Проверяет, содержит ли список указанный элемент.
     *
     * @param data элемент для поиска
     * @return true если элемент найден, false в противном случае
     */
    public boolean contains(T data) {
        return indexOf(data) != -1;
    }

    /**
     * Возвращает индекс первого вхождения указанного элемента.
     *
     * @param data элемент для поиска
     * @return индекс элемента или -1 если элемент не найден
     */
    public int indexOf(T data) {
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(data)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    /**
     * Преобразует список в массив.
     *
     * @return массив содержащий все элементы списка
     */
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }

    /**
     * Вспомогательный метод для получения узла по индексу.
     * Оптимизирован для поиска с начала или конца в зависимости от позиции.
     *
     * @param index индекс узла
     * @return узел по указанному индексу
     */
    private Node<T> getNode(int index) {
        Node<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    /**
     * Возвращает строковое представление списка.
     *
     * @return строковое представление списка в формате [element1, element2, ...]
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}