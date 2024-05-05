package pl.edu.pw.elka.prm2t.kakuro.gui.utils;


import java.util.ArrayList;
import java.util.List;

public class Stack<T> {
    private List<T> items;

    public Stack() {
        items = new ArrayList<>();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void push(T item) {
        items.add(item);
    }

    public T pop() {
        if (isEmpty()) {
            throw new StackEmptyException("No moves recorded");
        }
        return items.remove(items.size() - 1);
    }

    public T peek() {
        if (isEmpty()) {
            throw new StackEmptyException("No moves recorded");
        }
        return items.get(items.size() - 1);
    }

    public int size() {
        return items.size();
    }
}
