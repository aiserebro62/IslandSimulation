package utils;


import model.Island;
import model.animals.herbivors.*;
import model.animals.omnivors.*;
import model.animals.predators.*;

import java.util.Random;

public class Simulation {
    // Создаем остров
    private final Island island = new Island();

    // Метод запуска симуляции
    public void start() {
        // Инициализация случайного распределения животных
        Random rand = new Random();
        // Создаем 100 групп животных, случайно размещенных на острове
        for (int i = 0; i < 100; i++) {
            // Генерируем случайные координаты клетки
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            // Добавляем в клетку различных животных (хищников и травоядных)
            island.getCell(x, y).getAnimals().add(new Wolf());
            island.getCell(x, y).getAnimals().add(new Bear());
            island.getCell(x, y).getAnimals().add(new Boa());
            island.getCell(x, y).getAnimals().add(new Eagle());
            island.getCell(x, y).getAnimals().add(new Fox());
            island.getCell(x, y).getAnimals().add(new Rabbit());
            island.getCell(x, y).getAnimals().add(new Mouse());
            island.getCell(x, y).getAnimals().add(new Sheep());
            island.getCell(x, y).getAnimals().add(new Goat());
            island.getCell(x, y).getAnimals().add(new Horse());
            island.getCell(x, y).getAnimals().add(new Deer());
            island.getCell(x, y).getAnimals().add(new Boar());
            island.getCell(x, y).getAnimals().add(new Buffalo());
            island.getCell(x, y).getAnimals().add(new Duck());
            island.getCell(x, y).getAnimals().add(new Caterpillar());
        }
        // Счетчик тактов симуляции
        int tick = 0;
        // Основной цикл симуляции (пока не закончится)
        while (!island.isSimulationOver()) {
            island.processTick();
            island.printStats(++tick);
            // Пауза между тактами (3 секунда)
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {
                // Игнорирование прерывание
            }
        }
        System.out.println("Simulation ended.");
    }
}




