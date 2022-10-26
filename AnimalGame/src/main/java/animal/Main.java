package animal;

/**
 * Класс запускающий игру.
 */
public class Main {

    /**
     * Основной метод печатает инструкции и многократно воспроизводит игру в угадывание животных.
     * По мере прохождения игры дерево принятия решений растет за счет изучения новых животных.
     */
    public static void main(String[] args) {
        GameImpl ag = new GameImpl();
        ag.startGame();

        AnimalTree animalTreeRoot = ag.init();
        do {
            ag.play(animalTreeRoot);
        } while (ag.goOn("Хотите сыграть еще раз?"));

        System.out.println("Спасибо за игру!");
    }
}