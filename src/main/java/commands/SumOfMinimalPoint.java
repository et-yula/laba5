package commands;

import managers.CollectionManager;
import models.LabWork;
import utility.Console;

/**
 *  выводит сумму значений поля minimalPoint для всех элементов коллекции
 */
public class SumOfMinimalPoint extends Command {
    private final Console console;
    private final CollectionManager collectionManager;
    public SumOfMinimalPoint(Console console, CollectionManager collectionManager){
        super("sum_of_minimal_point", "вывести сумму значений поля minimalPoint для всех элементов коллекции");
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
        Long sumOfMinimalPoint = 0L;
        for (var labWork: collectionManager.getCollection()){
            sumOfMinimalPoint = sumOfMinimalPoint + labWork.getMinimalPoint();
        }
        if (sumOfMinimalPoint == 0L) {
            console.println("Коллекция пуста!");
            return false;
        }
        console.println(sumOfMinimalPoint);
        return true;
    }
}
