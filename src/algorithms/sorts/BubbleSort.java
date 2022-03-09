package algorithms.sorts;

import static algorithms.sorts.SortUtils.*;

class BubbleSort implements SortAlgorithm {

@Override
public <T extends Comparable<T>> T[] sort(T[] array){
    for (int i = 1, size = array.length; i < size; ++i){
        boolean swapped = false;
        for (int j = 0; j < size - i; ++j){
            if (greater(array[j], array[j + 1])){
                swap(array, j, j + 1);
                swapped = true;
            }
        }
        if (!swapped){
            break;
        }
    }
    return array;
}

public static void main(String[] args){

    // Apenas para testar, mudar para outros testes
    Integer[] integers = {4, 23, 6, 78, 1, 54, 231, 9, 12};
    BubbleSort bubbleSort = new BubbleSort();
    bubbleSort.sort(integers);

    for (int i = 0; i < integers.length - 1; ++i){
        assert integers[i] <= integers[i + 1];
    }
    print(integers);

    // Apenas para testar, mudar para outros testes
    String[] strings = {"c", "a", "e", "b", "d"};
    bubbleSort.sort(strings);

    for (int i = 0; i < strings.length - 1; ++i){
        assert strings[i].compareTo(strings[i + 1]) <= 0;
    }
    print(bubbleSort.sort(strings));
}
}
