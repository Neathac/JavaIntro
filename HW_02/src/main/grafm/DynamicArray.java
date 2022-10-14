package main.grafm;

interface MyCollection {
    void add(Object o);
    Object get(int i);
    void remove(Object o);
    void remove(int i);
}

public class DynamicArray implements MyCollection {

    private Object array[];
    private int count = 0;
    private int size;

    DynamicArray() {
        this.array = new Object[2];
        this.size = 2;
    }

    DynamicArray(int size) {
        this.array = new Object[size];
        this.size = size;
    }

    public void add(Object o) {
        this.array[this.count] = o;
        this.count += 1;
        if(this.count == this.size-1) {
            Object newArr[] = new Object[this.size*2];
            for (int i = 0; i < this.size; ++i) {
                newArr[i] = this.array[i];
            }
            this.array = newArr;
            this.size *= 2;
        }
    }

    public Object get(int i) {
        if (i < size) {
            return this.array[i];
        } else {
            return new Exception("Element not found");
        }
    }

    public void remove(Object o) {
        Object newArr[] = new Object[this.size];
        this.count = 0;
        for (int i = 0; i < this.size; ++i) {
            if (o != this.array[i]) {
                newArr[this.count] = this.array[i];
                this.count +=1;
            }
        }
        this.array = newArr;
    }

    public void remove(int i) {
        Object newArr[] = new Object[this.size];
        this.count = 0;
        for (int j = 0; j < this.size; ++j) {
            if (i != j) {
                newArr[this.count] = this.array[j];
                this.count +=1;
            }
        }
        this.array = newArr;
    }

    public int getElementCount() {
        return this.count;
    }
}
