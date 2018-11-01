/**
 *
 *                                                  Обход дерева.
 * Обходом дерева называется посещение всех его узлов в определенном порядке. На практике, обход используется не так часто,
 * как поиск, вставка или удаление. Одна из причин в том, что алгоритмы обхода не отличаются быстротой. Существуют три
 * простых алгоритма обхода дерева: прямой, симметричный и обратный. Для деревьев двоичного поиска чаще всего применяется
 * алгоритм симметричного обхода.
 * Симметричный обход.
 * При симметричном обходе двоичного дерева все узлы перебираются в порядке возрастания ключей. Если вам потребуется создать
 * отсортированный список из данных двоичного дерева - то это одно из возможных решений. Простейший способ основан на
 * рекурсии. В аргумент рекурсивной функции передается узел. В исходном состоянии этим узлом является корень дерева. Метод
 * должен выполнить только три операции:
 *   1. вызвать самого себя для обхода левого поддерева
 *   2. посетить узел
 *   3. вызвать самого себя для обхода правого поддерева
 * Обход работает с любым двоичным деревом, не только с деревьями двоичного поиска. Алгоритм обхода не обращает внимания
 * на наличие ключей; его интересует только наличие у узла потомков.
 * Код симметричного обхода дерева представлен ниже. Метод inOrder() выполняет три вышеупомянутые операции. Посещение узла
 * сводится к отображению его содержимого. Как и любая рекурсивная функция, метод должен обладать базовым ограничением -
 * условием при котором он немедленно возвращает управление без рекурсивного вызова. В методе inOrder() это происходит при
 * передаче аргумента null.
 * Пример работы метода inOrder показан на рисунке tree_8. Более сложный пример на рисунке tree_9.
 *
 * */

package ch_8;

public class TreeTraversal {

    private void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            /**
            * Вывод между вызовами для левого и правого ребенка нужен
             * так как слева находятся меньшие значения, а справа большие текущего узла
            * */
            System.out.println(localRoot.iData + " ");
            inOrder(localRoot.rightChild);
        }
    }

}
