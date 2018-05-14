/**
 * Поиск парных скобок.
 * Программа проверяет соответвие скобок "()[]{}", причем открываюющие скобки, близкие к концу строки, закрываются
 * раньше, чем те, что ближе к началу. То есть
 * c[d] - верно
 * a{b[c]d}e - верно
 * a{b[c}c(]) - неверно
 * Программа поиска парных скобок последовательно читает символы строки и заносит обнаруженные октрывающие скобки в стек.
 * Обнаружив во входных данных закрывающую скобку, она извлекает верхний элемент из стека и проверяет его на сотвествие
 * закрывающей скобке. Если это разные типы скобок, например ( и }, то ошибка. Также ошибка происходит, если в стеке нет
 * октрывающей скобки, парной по отношению к закрывающей или для какой то скобки в конце не нашлось пары (в стеке остались
 * элементы). То есть, каждая открывающая скобка помещается в стэк, каждая закрывающая - сопоставляется с открывающей,
 * извлеченной из вершины стека. В стек заносятся только скобки - все остальное игнорируется. Это работает, так как
 * стек работает по принципу LIFO.
 */


package ch_4.stack_and_queue.pairs_of_brackets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BracketChecker {

  private String input;

  public BracketChecker(String input) {
    this.input = input;
  }

  public void check() {
    Stack<Character> stack = new Stack<Character>();

    for (int i = 0; i < input.length(); i++) {
      char ch = input.charAt(i);
      switch (ch) {
        case '{':
        case '[':
        case '(':
          stack.push(ch);
          break;
        case '}':
        case ']':
        case ')':
        if (!stack.empty()) {
          char chStack = stack.pop();
          if ((chStack == '(' && ch != ')')||
              (chStack == '[' && ch != ']')||
              (chStack == '{' && ch != '}')) {
            System.out.println("Error: " + ch + " at " + i);
          }
        } else {
          System.out.println("Stack is empty");
        }
        break;
        default:
          break;
      }
    }
    if (!stack.empty()) {
      System.out.println("Error: остались октрывающие скобки");
      for (Character c : stack) {
        System.out.print(c);
      }
    } else {
      System.out.println("Congratulations!");
    }
  }

  public static void main(String[] args) throws IOException {
    String input;
    while (true) {
      System.out.println("Enter string: ");
      System.out.flush();
      input = getString();
      if ("".equals(input)) {
        break;
      }

      BracketChecker checker = new BracketChecker(input);
      checker.check();
    }
  }

  public static String getString() throws IOException {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    String s = br.readLine();
    return s;
  }

}
