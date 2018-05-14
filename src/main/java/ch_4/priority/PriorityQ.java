/**
 *
 *                                          Очередь с приоритетом.
 * У приоритеной очереди, как и у обычной, есть начало и конец, а элементы также извлекаются от начала. Но в то же время,
 * элементы в ней при добавлении упорядачиваются по ключу. Приоритетные очереди, каки простые очереди и стеки, часто
 * используются программистами в качестве вспомогательных инструментов. Приоритетные очереди часто используются в ОС.
 * Из плюсов, приоритетная очередь обеспечивает относительно быструю вставку, по этой причине, они реализуются с помощью
 * структуры данных, называемой кучей(будет рассмотрена позже). Ниже же представлена приоритетная очередь на базе простого
 * массива - это медленнее. Ниже представлена реализация от большего к меньшему, то есть, наименьший элемент в конце.
 * И вытаскиваем в методе remove() тоже наименьший элемент. При добавлении среднего по значению элемента - мы просто
 * сдвигаем меньшие элементы ближе к концу массива.
 *
 * */



package ch_4.priority;

public class PriorityQ {

  private int maxSize;
  private long[] queArray;
  private int nItems;

  public PriorityQ(int maxSize) {
    this.maxSize = maxSize;
    this.queArray = new long[maxSize];
    this.nItems = 0;
  }

  public void insert(long item) {
    int j;
    if (nItems == 0) {
      queArray[nItems++] = item;
    } else {
      for (j = nItems-1; j>=0; j--) {
        if (item > queArray[j]) {
          queArray[j+1] = queArray[j];
        } else {
          break;
        }
      }
      queArray[j+1] = item;
      nItems++;
    }
  }

  public long remove() {
//    nItems--;
    return queArray[--nItems];
  }

  public static void main(String[] args) {
    PriorityQ priorityQ = new PriorityQ(10);
    priorityQ.insert(30);
    priorityQ.insert(50);
    priorityQ.insert(10);
    priorityQ.insert(40);
    priorityQ.insert(20);

    System.out.println(priorityQ.remove());
    System.out.println(priorityQ.remove());
    System.out.println(priorityQ.remove());
    System.out.println(priorityQ.remove());
    System.out.println(priorityQ.remove());
  }

}
