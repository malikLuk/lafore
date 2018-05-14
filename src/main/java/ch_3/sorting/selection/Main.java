/**
 *
 *                                          Сортировка выбором.
 * Сортировка выбором немного превосходит пузырьковую. Количество перестановок сокращается с O(n^2) до O(n). Но вот
 * количество сравнений остается все таки O(n^2). В сортировке выбором мы последовательно перебираем все элементы массива
 * и выбираем наименьший из них. Этот элемент меняется местами с крайним левым, после чего левый элемент считается
 * отсортированным и в дальнейшем перемещаться уже не будет. Следующая проходка начинается с элемента с индексом 1, и
 * обнаружив наименьший элемент, мы меняем его местами с крайним левым неотсортированным, т.е. с индексом 1. И так, пока
 * не отсортируем все.
 * Подробнее. Начинаем с 0 элемента. Запоминаем его. Берем следущий элемент. Если он меньше того, который мы запонимили,
 * то перезаписываем его, теперь это наименьший элемент. И так до конца массива - перезаписываем наименьший элемент каждый
 * раз, когда встречаем еще меньший, чем тот, который мы запомнили. После прохождения всего массива - переставляем
 * наш записанный наименьший элемент с крайним левым неотсортированным, то есть на позицию 0. Итого, мы провели N-1
 * сравнений и одну перестановку для сортировки одного элемента. И с каждой следующей проходкой мы будем сортировать
 * только по одному элементу. Реализация представлена ниже.
 * Инвариант здесь будет в том, что элементы слева от out всегда будут отсортированы. Сложность чуть лучше, чем у пузырька,
 * но в общем тоже O(n^2).
 *
 * */

package ch_3.sorting.selection;

public class Main {

  private long[] array;
  private int nElems;

  public Main(int max) {
    array = new long[max];
    this.nElems = 0;
  }

  public void insert(long value) {
    array[nElems] = value;
    nElems++;
  }

  public  void display() {
    for (int i = 0; i < nElems; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println();
  }

  public void selectionSort() {
    int min, in, out;
    for (out = 0; out < nElems - 1; out++) {
      min = out;
      for (in = out + 1; in < nElems; in++) {
        if (array[in] < array[min]) {
          min = in;
        }
      }
      swap(out, min);
      display();
    }
  }

  private void swap(int one, int two) {
    long temp = array[one];
    array[one] = array[two];
    array[two] = temp;
  }

  public static void main(String[] args) {
    int maxSize = 100;
    Main main = new Main(maxSize);
    main.insert(5);
    main.insert(7);
    main.insert(6);
    main.insert(1);
    main.insert(2);
    main.insert(0);
    main.insert(4);
    main.insert(3);
    main.insert(9);
    main.insert(8);

    main.display();
    main.selectionSort();
    System.out.println();
    main.display();
  }

}
