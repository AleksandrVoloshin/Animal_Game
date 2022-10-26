package animal;

/**
 * Интерфейс Game определяющий основные методы игры.
 */
public interface Game {

    void startGame();

    AnimalTree init();

    void play(AnimalTree current);

    boolean goOn(String data);
}