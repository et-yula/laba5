package commands;

import managers.CollectionManager;
import models.LabWork;
import utility.Console;

/**
 * выводит любой объект из коллекции, значение поля minimalPoint которого является минимальным
 */
  public class MinByMinimalPoint extends Command {
    private final Console console;
    private final CollectionManager collectionManager;
    public MinByMinimalPoint(Console console, CollectionManager collectionManager){
        super("min_by_minimal_point", "вывести любой объект из коллекции, значение поля minimalPoint которого является минимальным");
        this.console = console;
        this.collectionManager = collectionManager;
    }
    /**
     * выполняет команду
     * @param arguments - аргументы команды
     * @return успешность выполнения команды
     */
    @Override
    public boolean execute(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            console.println("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
            return false;
        }
        LabWork labWorkWithMinimalPoint = null;
        for (var labWork: collectionManager.getCollection()){
            if (labWorkWithMinimalPoint == null || labWorkWithMinimalPoint.getMinimalPoint() > labWork.getMinimalPoint()) {
                labWorkWithMinimalPoint = labWork;
            }
        }
        if (labWorkWithMinimalPoint == null) {
            console.println("Коллекция пуста!");
            return false;
        }
        console.println(labWorkWithMinimalPoint);
        return true;
    }
}
