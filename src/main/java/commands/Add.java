package commands;

import managers.CollectionManager;
import models.Ask;
import models.LabWork;
import utility.Console;

/**
 * добавляет новый элемент в коллекцию
 */
public class Add extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Add(Console console, CollectionManager collectionManager) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            console.println("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
            return false;
        }
        console.println("* Создание новой лабораторной работы:");
        LabWork d = Ask.askLabWork(console, collectionManager);

        if (d != null && d.validate()) {
            collectionManager.add(d);
            console.println("Лабораторная работа успешно добавлена!");
            return true;
        }else {
            console.printError("Поля лабораторной работы не валидны! Лабораторная работа не создана!");
            return false;
        }
    }
}
