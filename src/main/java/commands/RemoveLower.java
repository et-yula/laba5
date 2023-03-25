package commands;

import managers.CollectionManager;
import models.Ask;
import models.LabWork;
import utility.Console;
import java.util.ArrayList;

/**
 *  удаляет из коллекции все элементы, меньшие, чем заданный
 */
public class RemoveLower extends Command {
    private final Console console;
    private final CollectionManager collectionManager;
    public RemoveLower(Console console, CollectionManager collectionManager){
        super("remove_lower {element}", " удалить из коллекции все элементы, меньшие, чем заданный");
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
        console.println("* Введите лабораторную работу для сравнения:");
        LabWork receivedLabWork = Ask.askLabWork(console, collectionManager);
        if (receivedLabWork != null && receivedLabWork.validate()) {
            int i = 0;
            var needRemove = new ArrayList<LabWork>();
            for (var labWork : collectionManager.getCollection()) {
                if (labWork.getMinimalPoint() < receivedLabWork.getMinimalPoint()){
                    needRemove.add(labWork);
                    i++;
                }
            }
            for (var labWork:needRemove)
                collectionManager.getCollection().remove(labWork);
            console.println("Удалено "+i+" лабораторных работ");
            return true;
        } else {
            console.printError("Поля лабораторной работы не валидны! Лабораторная работа не сравнима!");
            return false;
        }
    }
}
