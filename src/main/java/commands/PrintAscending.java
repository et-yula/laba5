package commands;

import managers.CollectionManager;
import models.LabWork;
import utility.Console;

import java.util.*;

/**
 * выводит элементы коллекции в порядке возрастания
 */
public class PrintAscending extends Command {
    private final Console console;
    private final CollectionManager collectionManager;
    public PrintAscending(Console console, CollectionManager collectionManager){
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
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
        List<LabWork> allLabWorks = new ArrayList<>(collectionManager.getCollection());
        if (allLabWorks.isEmpty()) {
            console.println("Коллекция пуста!");
            return false;
        }
        allLabWorks.sort((o1, o2) -> {
            if (o1.getMinimalPoint() > o2.getMinimalPoint()) {
                return 1;
            }
            if (o1.getMinimalPoint() < o2.getMinimalPoint()) {
                return -1;
            }
            return 0;
        });
        console.println("Элементы коллекции в порядке возрастания:");
        for (var labWork:allLabWorks){
            console.println(labWork);
        }
        return true;
    }
}
