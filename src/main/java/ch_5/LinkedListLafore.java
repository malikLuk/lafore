/**
 *
 *                                              Связные списки.
 * Рассмотрев массивы мы выяснили, что хранение данных в массивах имеет ряд недостатков. Например, в неупорядоченном
 * массиве поиск выполнятеся медленно, тогда как в упорядоченном массиве медленно выполняется вставка. Удаление
 * выполняется медленно в обоих случаях(упорядоченный массив или нет). Кроме того, размер массива невозможно изменить.
 * Сейчас мы рассмотрим связные списки - структуру данных, которая решает некоторые из этих проблем. Связный список
 * вообще очень часто используется. Им можно заменить массив в качестве базы для других структур (стеки, очереди и т д).
 *                                          Строение связного списка.
 * В связном списке каждый элемент данных встраивается в специальный объект, называемый элементом списка(классу, на основе
 * которого создаются такие объекты, часто присваивается имя Link). Так как список содержит много однотипных элементов,
 * для них удобно создать отдельный класс, отличный от класса самого связного списка. Каждый элемент(то есть объект Link)
 * содержит ссылку на следующий элемент списка, а поле, в котором хранится эта ссылка, обычно, называется next. Объект
 * же самого связного списка содержит ссылку на самый первый элемент, обычно называется first. Ниже пример класса Link.
 *                                            Ссылки и базовые типы.
 * Включение поля типа Link в определения самого класса Link выглядит немного странным лишь на первый взгляд. Как мы
 * помним, в Java запись public Link next; записывает в переменную next лишь ссылку на объект а не сам объект. Ссылка
 * представляет собой число, ассоциированное с объектом. Это число соотвествует адресу объекта в памяти компьютера.
 * Напомним также, что в Java примитивные типы(int, double etc) хранятся не так, как ссылочные. В полях примитивных
 * типов хранятся не ссылки, а конкретные числовые значения. То есть double salary = 100000.00; резервирует область
 * памяти и размещает в ней число 100000.00. С другой стороны, ссылка на объект типа Link link = someLink; помещает
 * в переменную link ссылку на объект someLink типа Link. Сам же обхект хранится где-то в другом месте. Объекты же
 * всегда создаются ключевым словом new; Link link = new Link(); и даже в этом случае поле link содержит не объект, а
 * лишь ссылку на него.
 * Фундаментальным отличием связного списка от массива является то, что в массиве каждый элемент всегда занимает конкретную
 * позицию и к нему можно обратиться напрямую по индексу. В связном списке же, конкретный элемент можно найти только одним
 * способом: отследив его по цепочке элеметов от начала списка. То есть, обратиться к элементу данных напрямую невозможно,
 * для поиска приходится использовать отношения между элементами. Мы начинаем с первого, переходим ко второму, затем к
 * третьему и так до тех пор, пока не найдем нужный. Ниже представлена реализация простого связного списка, поддерживающего
 * следующие операции: вставку элемента в начало списка, удаление элемента из начала списка, перебор списка для вывода
 * содержимого. 190
 *
 * */



package ch_5;

/**
 * Этот класс(самого связного списка) содержит всего однин элемент данных: ссылку на самый первый элемент списка, как уже
 * говорилось выше. Остальные элементы будут отслеживаться по цепочке ссылок next, начиная с элемента first.
 * */
public class LinkedListLafore {

  private Link first;

  // Да, это конструктор, который возвращает void (!!!)
  // ахуеть, даже и не знал.
  public LinkedListLafore() {
    first = null; // просто явно присваиваем null ссылке на первый элемент, так как элементов в списке еще нет
                  // как и в классе Link, явная инициализация  необязательна.
  }

  public boolean isEmpty() {
    return first == null; // если нет ссылки на первый элемент , то и сам список пуст
  }

  /**
   * Этот метод вставляет новый элемент в начало, потому что так проще всего. Мы просто создаем новый Link, присваиваем
   * его полю next значение first, а самому first присваиваем newLink.
   * */
  public void insertFirst(int id, double dd) {
    Link newLink = new Link(id, dd);
    newLink.next = first;
    first = newLink;
  }

  /**
   * Этот метод возвращает первый элемент списка и перезаписывает ссылку на первый элемент другим элементом,
   * который хранился у первого элемента в поле next. То есть, он отсоединяет от связного списка первый элемент и пишет
   * на его место второй.
   * */
  public Link deleteFirst() {
    Link temp = first;
    first = first.next;
    return temp;
  }

  public void displayList() {
    Link current = first;
    while (current != null) {
      current.displayLink();
      current = current.next;
    }
  }

  /**
   * Ищем элемент
   * */
  public Link find(Link link) {
    Link current = first;
    while (!current.equals(link)) {
      if (current.next == null) {
        return null;
      } else {
        current = current.next;
      }
    }
    return current;
  }

  /**
   * Удаление элемента
   * */
  public Link delete(Link link) {
    Link current = first;
    Link previous = first;
    while (!current.equals(link)) { //ищем элемент в нашем связном списке
      if (current.next == null) {
        return null;
      } else {
        previous = current;       // попутно сохраняем предыдущий элемент
        current = current.next;
      }
    }

    // если нашли элемент - то просто меняем ссылку next предыдущего элемента самого найденного на next найденного
    if (current == first) {
      first = first.next;
    } else {
      previous.next = current.next;
    }
    return current;
  }

  public static void main(String[] args) {
    LinkedListLafore linkedListLafore = new LinkedListLafore();
    linkedListLafore.insertFirst(22,2.99);
    linkedListLafore.insertFirst(44,4.99);
    linkedListLafore.insertFirst(66,6.99);
    linkedListLafore.insertFirst(88,8.99);
    linkedListLafore.insertFirst(99,8.99);
    linkedListLafore.insertFirst(100,8.99);

    linkedListLafore.displayList();
    System.out.println("====================================");

    while (!linkedListLafore.isEmpty()) {
      Link link = linkedListLafore.deleteFirst();
      link.displayLink();
    }

    System.out.println("====================================");

    linkedListLafore.displayList();

    System.out.println("====================================");

    linkedListLafore.insertFirst(22,2.99);
    linkedListLafore.insertFirst(44,4.99);
    linkedListLafore.insertFirst(66,6.99);
    linkedListLafore.insertFirst(88,8.99);
    linkedListLafore.insertFirst(99,8.99);
    linkedListLafore.insertFirst(100,8.99);

    Link k = linkedListLafore.find(new Link(66, 6.99));
    k.displayLink();

    System.out.println("====================================");

    linkedListLafore.delete(k);

    System.out.println("====================================");

    linkedListLafore.displayList();
  }

}


/**
 * Этот класс предназначен для хранения данных.
 * */
class Link {

  public int iData;
  public double dData;
  public Link next; // ссылка на следующий элемент

  public Link(int id, double dd) {
    iData = id;
    dData = dd;
    // next автоматически присваивается null, впрочем, можно сделать это и явно next = null;
    // Это означает, что пока элемент не связан с другими в связном списке, то ссылка ни на что и не указывает.
  }

  public void displayLink() {
    System.out.println("{" + iData + ", " + dData + "}");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Link link = (Link) o;

    if (iData != link.iData) return false;
    return Double.compare(link.dData, dData) == 0;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = iData;
    temp = Double.doubleToLongBits(dData);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
}