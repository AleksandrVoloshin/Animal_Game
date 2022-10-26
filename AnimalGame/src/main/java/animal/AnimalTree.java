package animal;

/**
 * Класс представляющий описание алгоритма дерева решений. Каждый узел дерева содержит фрагмент данных
 * и ссылки на левый и правый дочерние элементы.
 */
public class AnimalTree {

    private String question; //Каждый узел имеет одну ссылку на объект, хранящийся в переменной экземпляра
    private AnimalTree left; //Переменная экземпляра left является ссылкой на левый дочерний элемент
    private AnimalTree right; //Переменная экземпляра right является ссылкой на правый дочерний элемент

    public AnimalTree(String data, AnimalTree left, AnimalTree right) {
        this.question = data;
        this.left = left;
        this.right = right;
    }
    public AnimalTree(String data) {
        this.question = data;
    }

    /**
     * Метод для определения того, является ли узел конечным.
     */
    public boolean isLeaf() {
        return (left == null) && (right == null);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public AnimalTree getLeft() {
        return left;
    }

    public void setLeft(AnimalTree left) {
        this.left = left;
    }

    public AnimalTree getRight() {
        return right;
    }

    public void setRight(AnimalTree right) {
        this.right = right;
    }
}