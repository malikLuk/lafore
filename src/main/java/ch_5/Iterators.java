/**
 * Итераторы.
 * Допустим, тербуется перебрать элементы списка, выполняя некие операции с определенными элементами - скажем, увеличить
 * зарплату всех работников, которые получают минимальный оклад, а остальные не трогать. Или, скажем, из списка клиентов
 * интернет-магазина удалить записи всех покупателей, которые ничего не заказывали в последние полгода.
 * С массивами подобные операции выполняются просто, потому что для отслеживания текущей позиции можно воспользоваться
 * индексом. Однако, в связном списке элементы не имеют индексов. Каждый раз выполнять поиск элемента - невыгодно, так
 * как поиск всегда будет идти сначала и будет много лишнего перебора элементов списка. Намного эффективнее было бы
 * переходить от элемента к элементу, проверять, удовлетворяет ли элемент некоторому критерию, и, если проверка дает
 * положительный результат - выполнять с ним соответствующую операцию.
 * Ссылка на элемент списка.
 * Фактически, пользователю класса требуется ссылка, которая может указывать на произвольный элемент списка. По ссылке
 * пользователь может проверить или изменить элемент списка. Допустим, мы создали такую ссылку, где она должна размещаться?
 * Один из возможных вариантов - включить соответствующее поле непосредственно в список(какой-нибудь current или нечто
 * подобное). Пользователь обращается к элементу по ссылке current, а затем увеличивает current для перехода к следующему
 * элементу. Однако, у такого подхода имеется серьезный минус: пользователю может потребоваться несколько таких ссылок,
 * по аналогии с одновременным использованием нескольких индексов при работе с массивом. Невозможно заранее предсказать,
 * сколько таких ссылок потребуется, поэтому самым логичным решением будет предоставить пользователю возможность создать
 * столько ссылок, сколько ему необходимо. В рамках ООП - это будет внедрение каждой такой ссылки в объект класса. и это
 * не может быть класс списка, так как объект списка существует в одном экземпляре, поэтому обычно ссылка реализуется в
 * отдельном классе.
 * Объекты, содержащие ссылки на элементы структур данных и используемые для перебора элементов этих структур, обычно
 * называют ИТЕРАТОРАМИ. Определение класса итератора выглядит примерно так:
 * <p>
 * class ListIterator {
 * private LinkIter current;
 * }
 * <p>
 * где поле current содержит ссылку, указывающую на элемент, на который в настоящее время смотрит итератор. Чтобы использовать
 * итератор, пользователь сначала создает список, а затем объект-итератор, ассоциированный с этим списком. Но лучше будет
 * поручить создание итератора списку, потому что список может передать итератору полезную информацию, например, ссылку
 * на first. По этой причине в класс списка включается метод getIterator(), который возврщает пользователю объект-итератор
 * для данного списка. Пример:
 * <p>
 * psvm() {
 * LinkList theList = new LinkList();               // создали список
 * ListIterator iter1 = theList.getIterator();      // создали итератор
 * LinkIter aLink = iter1.getCurrent();                 // обратились к текущему элементу, на который указывает итератор
 * iter1.next();                                    // переместили итератор к следующему элементу
 * }
 * <p>
 * Итератор ВСЕГДА указывает на некоторый элемент списка. Он связан со списком, но не относится ни к классу списка, ни к
 * классу элемента списка.
 * Концептуальные методы итераторов:
 * <p>
 * reset() - перемещение итератора в начало списка
 * next() - перемещение итератора к следующему элементу
 * getCurrent() - получение элемента, на который указывает итератор
 * atEnd() - true, если итератор находится в конце списка
 * insertAfter() - вставка нового элемента после итератора
 * insertBefore() - вставка нового элемента перед итератором
 * deleteCurrent() - удаление элемента, на который указывает итератор
 * <p>
 * Тем не менее, бывает непросто решить, какие операции должны выполнятся иетратором, а какие самим списком.
 */


package ch_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class InterIterApp {

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws  IOException {
        String s = getString();
        return Integer.parseInt(s);
    }

    public static void main(String[] args) throws IOException {
        LinkList theList = new LinkList();
        ListIterator iter1 = theList.getIterator();
        long value;

        iter1.insertAfter(20);
        iter1.insertAfter(40);
        iter1.insertAfter(100);
        iter1.insertAfter(60);
        iter1.insertAfter(80);

        while (true) {
            System.out.println("Enter first letter of show, reset, ");
            System.out.println("next, get, before, after, delete: ");
            System.out.flush();
            int choice = getChar();

            switch (choice) {
                case 's':
                    if(!theList.isEmpty()) {
                        theList.displayList();
                    } else {
                        System.out.println("List is empty");
                    }
                    break;
                case 'r':
                    iter1.reset();
                    break;
                case 'n':
                    if (!theList.isEmpty() && !iter1.atEnd()) {
                        iter1.nextLink();
                        System.out.println("Current elem: " + iter1.getCurrent().dData);
                    } else {
                        System.out.println("Can't go to next");
                    }
                    break;
                case 'g':
                    if (!theList.isEmpty()) {
                        value = iter1.getCurrent().dData;
                        System.out.println("Returnde: " + value);
                    } else {
                        System.out.println("List is empty");
                    }
                    break;
                case 'b':
                    System.out.println("Enter value to insert: ");
                    System.out.flush();
                    value = getInt();
                    iter1.insertBefore(value);
                    break;
                case 'a':
                    System.out.println("Enter value to insert: ");
                    System.out.flush();
                    value = getInt();
                    iter1.insertAfter(value);
                    break;
                case 'd':
                    if (!theList.isEmpty()) {
                        value = iter1.deleteCurrent();
                        System.out.println("Deleted: " + value);
                    } else {
                        System.out.println("List is empty");
                    }
                    break;
                    default:
                        System.out.println("Invalid entry");
            }
        }
    }
}


/**
 * Полноценный Итератор
 * */
class ListIterator {

    /**
     * Поля next, prev и, возможно, какие-то другие
     * должны быть перенесены в итератор. Также у каждого
     * объекта итератора должен быть список, с которым он связан,
     * который он и итерирует
     * */
    private LinkIter current;  // текущий элемент списка
    private LinkIter previous; // предыдущий элемент спика
    private LinkList ourList;  // ссылка на сам список

    public ListIterator(LinkList list) {
        ourList = list;
        reset();        // При инициализации итератора сбрасываем указатель текущего элемента на первый элемент списка
    }

    public void reset() {               // возврат к first
        current = ourList.getFirst();
        previous = null;
    }

    public boolean atEnd() {         // ялвяется ли текущий элемент последним в списке
        return current.next == null;
    }

    public void nextLink() {            // переход к следующему элементу
        previous = current;             // текущий элемент становится предыдущим
        current = current.next;         // а следующий текущим
    }

    public LinkIter getCurrent() {
        return current;
    }

    public void insertAfter(long dd) {      // вставка после ТЕКУЩЕГО элемента
        LinkIter newLink = new LinkIter(dd);
        if (ourList.isEmpty()) {            // если список пустой
            ourList.setFirst(newLink);      // просто вставляем наш элемент на позвицию first
            current = newLink;
        } else {                            // если список не пустой
            newLink.next = current.next;    // то мы вставляем наш элемент между
            current.next = newLink;         // current и current.next
            nextLink();                     // и осуществляем переход на этот элемент
        }

    }

    public void insertBefore(long dd) {         // вставка перед ТЕКУЩИМ элементом
        LinkIter newLink = new LinkIter(dd);
        if (previous == null) {                 // если мы в начале списка, или список вообще пустой (не важно)
            newLink.next = ourList.getFirst();  // просто вставляем наш элемент на позвицию first
            ourList.setFirst(newLink);
            reset();                            // и сбрасываем указатель current на первй элемент (на добавленный)
        } else {
            newLink.next = previous.next;       // если не в начале списка, то вставляем элемент между
            previous.next = newLink;            // previous и previous.next(оно же current)
            current = newLink;

        }
    }

    public long deleteCurrent() {               // удаление текущего элемента
        long value = current.dData;
        if (previous == null) {                 // если это начало списка (просто начало списка, он не пуст, потому что)
            ourList.setFirst(current.next);     // current ссылкается на какой-то элемент. В этом случае первым (first)
            reset();                            // элементом делает current.next и сбрасываем указатель current
        } else {
            previous.next = current.next;       // если не начало - то присваиваем next предыдущего элемента next следующего
            if (atEnd()) {                      // если мы оказались в конце, то сброс
                reset();
            } else {                            // если это еще не конец списка - то двигаем current к следующего элменту
                current = current.next;
            }
        }
        return value;
    }
}

/**
 * Элемент списка
 * */
class LinkIter {
    public long dData; // данные
    public LinkIter next; // следующий элемент списка

    public LinkIter(long dd) {
        dData = dd;
    }

    public void displayLink() {
        System.out.println(dData + " ");
    }
}

/**
 * Сама структура данных (список)
 * */
class LinkList {
    private LinkIter first; // ссылка на первый элемент

    public LinkList() {
        first = null; // присваиваем явно для наглядности
    }

    public LinkIter getFirst() {
        return first;
    }

    public void setFirst(LinkIter first) {
        this.first = first;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public ListIterator getIterator() { // получение итератора
        return new ListIterator(this);  // в конструктор итератора передаем сам список
    }

    public void displayList() {
        LinkIter current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println("=======");
    }
}




