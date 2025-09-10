package ru.hamming;

import ru.hamming.untils.LinkedList;

import java.util.Objects;

/**
 * Контейнер для хранения элементов с фиксированным размером.
 * Использует LinkedList для внутреннего хранения данных.
 *
 * @param <T> тип элементов в контейнере
 * @version 1.0
 * @author Hamming
 */
public class ClassContainer<T> {

    private final Integer sizeContainer;
    private final LinkedList<T> containerValues;

    /**
     * Конструктор контейнера.
     *
     * @param sizeContainer максимальный размер контейнера
     */
    public ClassContainer(Integer sizeContainer) {
        this.sizeContainer = sizeContainer;
        this.containerValues = new LinkedList<>();
    }

    /**
     * Добавляет элемент в конец контейнера.
     *
     * @param value элемент для добавления
     */
    public void add(T value) {
        this.containerValues.add(value);
    }

    /**
     * Добавляет элемент по указанному индексу.
     *
     * @param index индекс для вставки
     * @param value элемент для добавления
     * @throws IndexOutOfBoundsException если индекс выходит за границы
     */
    public void add(Integer index, T value) {
        this.containerValues.add(index, value);
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за границы
     */
    public T get(Integer index) {
        return this.containerValues.get(index);
    }

    /**
     * Удаляет первое вхождение указанного элемента.
     *
     * @param value элемент для удаления
     * @return true если элемент был удален, false если элемент не найден
     */
    public boolean remove(T value) {
        return this.containerValues.remove(value);
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index индекс элемента для удаления
     * @return удаленный элемент
     * @throws IndexOutOfBoundsException если индекс выходит за границы
     */
    public T remove(Integer index) {
        return this.containerValues.remove(index);
    }

    /**
     * Возвращает строковое представление контейнера.
     *
     * @return строковое представление контейнера
     */
    @Override
    public String toString() {
        return "ClassContainer{" +
                "sizeContainer=" + sizeContainer +
                ", containerValues=" + containerValues +
                '}';
    }

    /**
     * Сравнивает этот контейнер с другим объектом.
     *
     * @param o объект для сравнения
     * @return true если объекты равны, false в противном случае
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClassContainer<?> that = (ClassContainer<?>) o;
        return Objects.equals(sizeContainer, that.sizeContainer) && Objects.equals(containerValues, that.containerValues);
    }

    /**
     * Возвращает хэш-код контейнера.
     *
     * @return хэш-код контейнера
     */
    @Override
    public int hashCode() {
        return Objects.hash(sizeContainer, containerValues);
    }

    /**
     * Возвращает максимальный размер контейнера.
     *
     * @return размер контейнера
     */
    public Integer getSizeContainer() {
        return sizeContainer;
    }

    /**
     * Возвращает копию внутреннего списка значений.
     * Изменения в возвращенном списке не влияют на оригинальный контейнер.
     *
     * @return копия списка значений
     */
    public LinkedList<T> getContainerValues() {
        return this.containerValues.copy();
    }
}