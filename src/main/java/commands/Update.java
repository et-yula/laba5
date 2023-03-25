package commands;

import managers.CollectionManager;
import models.Ask;
import utility.Console;

/**
 * обновляет значение элемента коллекции, id которого равен заданному
 */
public class Update extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Update(Console console, CollectionManager collectionManager) {
        super("update <id> {element}", "обновить значение элемента коллекции, id которого равен заданному");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String[] arguments) {
        if (arguments[1].isEmpty()) {
            console.println("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
            return false;
        }
        int id = -1;
        try {
            id = Integer.parseInt(arguments[1].trim());
        } catch (NumberFormatException e) {
            console.println("ID не распознан");
            return false;
        }
        if (collectionManager.byId(id) == null) {
            console.println("Несуществующий ID");
            return false;
        }
        console.println("* Создание новой лабораторной работы:");
        var receivedLabWork = Ask.askLabWork(console, collectionManager);
        if (receivedLabWork != null && receivedLabWork.validate()) {
            collectionManager.getCollection().remove(collectionManager.byId(id));
            receivedLabWork.setId(id);
            collectionManager.add(receivedLabWork);
            console.println("Лабораторная работа успешно изменёна!");
            return true;
        } else {
            console.println("Поля лабораторной работы не валидны! Лабораторная работа не создана!");
            return false;
        }
    }
}