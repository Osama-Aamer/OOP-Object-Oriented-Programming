// Task2
import java.util.ArrayList;
import java.util.List;

public class SafeStringList {
    private final List<String> list = new ArrayList<>();


    public synchronized void add(String item) {
        list.add(item);
    }


    public synchronized boolean remove(String item) {
        return list.remove(item);
    }


    public synchronized int size() {
        return list.size();
    }


    public synchronized String get(int index) {
        if (index < 0 || index >= list.size()) return null;
        return list.get(index);
    }

    @Override
    public synchronized String toString() {
        return list.toString();
    }
}