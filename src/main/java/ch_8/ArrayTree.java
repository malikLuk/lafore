/**
 *
 *                                              Представление дерева в виде массива.
 * Наши примеры основаны на представлении ребер дерева ссылками leftChild и rightChild, хранимыми в каждом узле. Однако,
 * наряду с таким представлением существует и совершенно другое представление дерева в виде массива. В таком представлении
 * узлы хранятся в массиве и не связываются ссылками. Позиция узла в массиве соответствует его положению в дереве. Элемент
 * с индексом 0 представляет корень дерева, элемент с индексом 1 - его левого потомка, и так далее, с перебором слева
 * направо на каждом уровне дерева (рисунок tree_20). Каждая позиция в дереве, независимо от того, существует в ней узел
 * или нет, соответствует определенной ячейке массива. Включение узла в некоторую позицию дерева означает вставку узла в
 * сответствующую ячейку массива. Ячейки, представляющие пустые позиции дерева, заполняются null-ами.
 * В такой схеме родитель и потомки узла вычисляются по простым формулам на основании индекса узла в массиве:
 * 1. Индекс левого потомка 2*index + 1
 * 2. Индекс правого потомка 2*index + 2
 * 3. Индекс родителя (index - 1)/2, где "/" - целочисленное деление без остатка
 * Хранение дерева в виде массива, конечно, фигня полная. Незаполненные и удаленные узлы оставляют пустоты в массиве, что
 * есть неэффективное расходование памяти. Еще хуже - если удаление узла требует перемещения поддеревьев. В этом случае
 * нужно перетаскивать каждый узел поддерева в новую ячейку массива. Но если удаление запрещено, то представление дерева
 * в виде массива имеет место быть. На странице 384 полный код программы.
 *
 * */

package ch_8;

public class ArrayTree {
}
