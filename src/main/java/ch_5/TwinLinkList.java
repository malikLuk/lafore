/**
 *
 *                                                          Двусвязные списки.
 * Не путать с ДВУСТОРОННИМ СПИСКОМ. Потенциальным недостатком обычных связных списков является сложность перемещения в
 * обратном направлении. Команда вида current = current.next позволяется передвигаться по списку вперед, но соотвествующего
 * способа перехода к предыдущему элементу не дает. Иногда это крайне неэффективно. Поэтому должен быть способ
 * двигаться по списку в обратном направлении. Двусвязный список предоставляет такую возможность, то есть позволяет
 * перемещаться по списку как в прямом, так и в обратном направлении. Суть в том, что каждый элемент списка хранит две
 * ссылки, вместо одной - на следующий и предыдущий элементы. Недостаток такого подхода в том, что при каждой вставке и
 * удалении нам придется менять четыре ссылки, вместо двух. И, конечно, каждый элемент занимает занимает больше места в
 * памяти из-за дополнительной ссылки.
 * Двусвязный список не обязан быть двусторонним(то есть ссылка на последний элемент не обязана храниться в объекте
 * списка), но это удобно. Двусвязный список может использоваться как база для постоения дека(двусторонней очереди). В
 * деке операции вставки и удаления могут выолняться с обоих концов и двусвязный список предоставляет такую возможность.
 *
 * */

package ch_5;

public class TwinLinkList {

    private LinkTwin first;
    private LinkTwin last;

    public TwinLinkList() {
        this.first = null;
        this.last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Вставка в начало. Если список пустой, то в last и first вставляем новый элемент.
     * Если не пустой, то в previous первого элемента записываем newLink, и самой ссылке
     * first присваиваем newLink. last не трогаем.
     * */
    public void insertFirst(long dData) {
        LinkTwin newLink = new LinkTwin(dData);
        if (isEmpty()) {
            last = newLink;
        } else {
            first.previous = newLink;
        }
        newLink.next = first;
        first = newLink;
    }

    /**
     * Вставка в конец списка. То же самое, если список пустой, то last и first будут одним и
     * тем же новым элементом. Если не пустой, то в last.next пишем новый элемент, новому элементу
     * в previous пишем last, а после и в сам last пишем новый элемент.
     * */
    public void insertLast(long dData) {
        LinkTwin newLink = new LinkTwin(dData);
        if (isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
            newLink.previous = last;
        }
        last = newLink;
    }

    /**
     * Удаление первого элемента. Обнуляем first.next.previous и смещаем указатель first на first.next
     * так как теперь first.next - это первый элемент и у него не может быть previous.
     * */
    public LinkTwin deleteFirst() {
        LinkTwin temp = first;
        if (first.next == null) {
            last = null;
        } else {
            first.next.previous = null;
        }
        first = first.next;
        return temp;
    }

    /**
     * Удаление последнего элемента. Обнуляем last.previous.next и смещаем указатель last на last.previous.
     * */
    public LinkTwin deleteLast() {
        LinkTwin temp = last;
        if (first.next == null) {
            first = null;
        } else {
            last.previous.next = null;
        }
        last = last.previous;
        return temp;
    }

    /**
     * Вставка dd в позицию после key
     * */
    public boolean insertAfter(long key, long dd) {
        LinkTwin current = first;
        while (current.dData != key) {   // ищем элемент соответствующий key
            current = current.next;
            if (current == null)
                return false;           // не нашли элемента
        }
        LinkTwin newLink = new LinkTwin(dd);

        if (current == last) {              // если элемент, соответствующий ключу - это последний элемент
            newLink.next = null;            // то переписываем last и теперьу нас новый последний элемент
            last = newLink;
        }
        else {
            newLink.next = current.next;    // если элмент соответствующий ключу где-то посередине
            current.next.previous = newLink;// то просто перетираем соответствующие ссылки
        }
        newLink.previous = current;
        current.next = newLink;
        return true;
    }

    /**
     * Удалить по ключу
     * */
    public LinkTwin deleteKey(long key) {
        LinkTwin current = first;
        while (current.dData != key) {
            current = current.next;
            if (current == null) {
                return null;
            }
        }
        if (current == first) {
            first = current.next;
        } else {
            current.previous.next = current.next;
        }
        if (current == last) {
            last = current.previous;
        } else {
            current.next.previous = current.previous;
        }
        return current;
    }

    public void displayForward() {
        LinkTwin current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println();
    }

    public void displayBackward() {
        LinkTwin current = last;
        while (current != null) {
            current.displayLink();
            current = current.previous;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TwinLinkList theList = new TwinLinkList();
        theList.insertFirst(22);      // insert at front
        theList.insertFirst(44);
        theList.insertFirst(66);

        theList.insertLast(11);       // insert at rear
        theList.insertLast(33);
        theList.insertLast(55);

        theList.displayForward();     // display list forward
        theList.displayBackward();    // display list backward

        theList.deleteFirst();        // delete first item
        theList.deleteLast();         // delete last item
        theList.deleteKey(11);        // delete item with key 11

        theList.displayForward();     // display list forward

        theList.insertAfter(22, 77);  // insert 77 after 22
        theList.insertAfter(33, 88);  // insert 88 after 33

        theList.displayForward();
    }

}

class LinkTwin {

    public long dData;
    public LinkTwin next;
    public LinkTwin previous;

    public LinkTwin(long dData) {
        this.dData = dData;
    }

    public void displayLink() {
        System.out.println("dData: " + dData);
    }

}
