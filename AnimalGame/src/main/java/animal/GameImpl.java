package animal;

import java.util.Scanner;

import static java.lang.String.format;

/**
 * Класс GameImpl, представляет собой небольшую игру в угадывание животных и иллюстрируюет работу и использование
 * алгоритма дерева решений.
 */

public class GameImpl implements Game {

    private final Scanner sc;

    public GameImpl() {
        sc = new Scanner(System.in);
    }

    /**
     * Метод - инструкция для игры в угадывание животных.
     */
    @Override
    public void startGame() {
        print("Загадайте животное!");
        print("Отвечайте на заданные вопросы - Да/Нет, а я попытатюсь понять какое животное Вы загадали.");
    }

    /**
     * Метод строит начало алгоритма дерева решений с двумя животными (Кот и Кит).
     * @return - ссылка на корень дерева.
     */
    @Override
    public AnimalTree init() {
        final String cat = "Кот";
        final String whale = "Кит";
        final String rootQuestion = "Ваше животное, живет на суше?";

        return new AnimalTree(rootQuestion, new AnimalTree(cat), new AnimalTree(whale));
    }

    /**
     * Метод запускающий 1 "круг" игры угадай животное. Либо угадывается загаданное животное,
     * либо добавляется новое животное и факт о нем.
     * @param current - ссылка на корневой узел алгоритма дерева решений для запуска игры.
     */
    @Override
    public void play(AnimalTree current) {
        while (!current.isLeaf()) {
            if (goOn(current.getQuestion())) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        print(format("Я предполагаю, что это %s. ", current.getQuestion()));
        if (!goOn("Я угадал?")) {
            learn(current);
        } else {
            print("Я так и знал!");
        }
    }

    /**
     * Метод проверяет введенную информацию в консоль на соответствие указанных параметров
     * @param data - ссылка на введенные данные.
     * @return - ссылка на введенный параметр.
     */
    @Override
    public boolean goOn(String data) {
        print(format("%s [Да или Нет]: ", data));
        String answer = sc.nextLine().toUpperCase();
        while (!answer.startsWith("Д") && !answer.startsWith("Н")) {
            print("Неверный ответ. Пожалуйста, введите Да или Нет: ");
            answer = sc.nextLine().toUpperCase();
        }
        return answer.startsWith("Д");
    }

    /**
     * Метод который запрашивает у пользователя информацию о новом животном и факт о нем.
     * @param current - ссылка на лист алгоритма дерева решений. Лист содержит ошибочный факт о животном
     * (в случае если игра не знает что за животное загадано), который был озвучен.
     * После получения новой инфомации от пользователя, дерево расширяется.
     */
    private void learn(AnimalTree current) {
        String guessAnimal = current.getQuestion(); //Животное, о котором была сделана догадка
        print("Я не знаю ответа. Что это за животное?");
        String correctAnimal = sc.nextLine(); //Животное, которое загадал пользователь
        while (correctAnimal == null || correctAnimal.equals("")) {
            print("Некорректное значение, повторите ввод");
            correctAnimal = sc.nextLine();
        }

        //Вставка факта о новом животном
        print(format("Пожалуйста, напишите чем отличается %s от %s.", correctAnimal, guessAnimal));
        String newAnimal = sc.nextLine(); //Факт о животном, позволяющий его отличить от других животных
        while (newAnimal == null || newAnimal.equals("")) {
            print("Некорректное значение, повторите ввод");
            newAnimal = sc.nextLine();
        }

        //Факт о новом животом добавлен в текущий узел и добавляется два дочерних узла
        current.setQuestion(format("Животное, которое Вы загадали, %s?", newAnimal));
        if (goOn(format("%s %s?", correctAnimal, newAnimal))) {
            current.setLeft(new AnimalTree(correctAnimal));
            current.setRight(new AnimalTree(guessAnimal));
        } else {
            current.setLeft(new AnimalTree(guessAnimal));
            current.setRight(new AnimalTree(correctAnimal));
        }
    }

    /**
     * Метод оптимизирующий вывод информации
     */
    private void print(String data) {
        System.out.println(data);
    }
}