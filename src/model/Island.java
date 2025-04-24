package model;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import model.animals.Animal;
import model.animals.Herbivore;
import model.animals.Omnivore;
import model.animals.Predator;
import model.animals.herbivors.*;
import model.animals.omnivors.*;
import model.animals.predators.*;

public class Island {
    // Размеры острова
    private final int width = 10;
    private final int height = 10;
    private final Cell[][] cells = new Cell[width][height];

    // Конструктор острова - инициализирует все клетки
    public Island() {
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                cells[i][j] = new Cell();
    }

    public Cell getCell(int x, int y) {

        return cells[x][y];
    }

    // Метод для перемещения животного в пределах его диапазона движения
    public void moveAnimal(Animal animal, int x, int y, int range) {
        // Вычисляем новые координаты с учетом диапазона движения
        int newX = Math.min(Math.max(0, x + (int) (Math.random() * (2 * range + 1)) - range), width - 1);
        int newY = Math.min(Math.max(0, y + (int) (Math.random() * (2 * range + 1)) - range), height - 1);
        // Перемещаем животное в новую клетку
        cells[newX][newY].getAnimals().add(animal);
        // Удаляем из старой клетки
        cells[x][y].getAnimals().remove(animal);
    }

    // Обработка одного такта симуляции
    public void processTick() {
        // Создаем пул из 8 потоков для параллельной обработки
        ExecutorService executor = Executors.newFixedThreadPool(8);
        // Обрабатываем каждую строку острова в отдельном потоке
        for (int i = 0; i < width; i++) {
            int finalI = i;
            executor.submit(() -> {
                for (int j = 0; j < height; j++) {
                    Cell cell = cells[finalI][j];
                    List<Animal> toRemove = new ArrayList<>();
                    // Фаза движения: все животные двигаются
                    for (Animal animal : new ArrayList<>(cell.getAnimals())) {
                        animal.move(this, finalI, j);
                    }
                    // Фаза питания: животные едят и могут умереть
                    for (Animal animal : new ArrayList<>(cell.getAnimals())) {
                        animal.eat(cell);
                        if (animal.isDead()) toRemove.add(animal);
                    }
                    cell.getAnimals().removeAll(toRemove);
                    // Фаза размножения
                    for (Animal animal : new ArrayList<>(cell.getAnimals())) {
                        animal.reproduce(cell);
                    }
                    // Растения растут в клетке
                    cell.growPlants();
                }
            });
        }
        // Завершаем работу пула потоков
        executor.shutdown();
        try {
            // Ожидаем завершения всех задач (максимум 1 минута)
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

        }
    }

    // Проверка условий окончания симуляции
    public boolean isSimulationOver() {
        boolean hasPredator = false, hasHerbivore = false, hasOmnivore = false;
        // Проверяем наличие разных типов животных на острове
        for (Cell[] row : cells)
            for (Cell cell : row)
                for (Animal animal : cell.getAnimals()) {
                    if (animal instanceof Predator) hasPredator = true;

                    if (animal instanceof Herbivore) hasHerbivore = true;
                    if (animal instanceof Omnivore) hasOmnivore = true;
                }
        // Симуляция заканчивается, если нет либо хищников, либо травоядных
        return !(hasPredator && hasHerbivore);

    }
    // Вывод статистики по количеству животных каждого вида
    public void printStats(int tick) {
        System.out.println();
        System.out.println("== Tick " + tick + " ==");
        // Счетчики для каждого вида животных
        int wolves = 0, bears = 0, boaes = 0, eagles = 0, rabbits = 0, mouses = 0, sheeps = 0, goats = 0,
                horses = 0, deers = 0, boars = 0, buffaloes = 0, foxes = 0, ducks = 0, caterpillars = 0;
        // Подсчет количества каждого вида животных
        for (Cell[] row : cells)
            for (Cell cell : row)
                for (Animal animal : cell.getAnimals()) {
                    if (animal instanceof Wolf) wolves++;
                    if (animal instanceof Bear) bears++;
                    if (animal instanceof Boa) boaes++;
                    if (animal instanceof Eagle) eagles++;
                    if (animal instanceof Fox) foxes++;
                    if (animal instanceof Sheep) sheeps++;
                    if (animal instanceof Goat) goats++;
                    if (animal instanceof Horse) horses++;
                    if (animal instanceof Deer) deers++;
                    if (animal instanceof Boar) boars++;
                    if (animal instanceof Buffalo) buffaloes++;
                    if (animal instanceof Mouse) mouses++;
                    if (animal instanceof Rabbit) rabbits++;
                    if (animal instanceof Duck) ducks++;
                    if (animal instanceof Caterpillar) caterpillars++;
                }
        // Вывод статистики с эмодзи для наглядности
        System.out.println("\uD83D\uDC3A" + "Wolf: " + wolves + "\n" + "\uD83D\uDC3B" + "Bear: " + bears + "\n"
                + "\uD83D\uDC0D" + "Boa: " + boaes + "\n" + "\uD83E\uDD85" + "Eagle: " + eagles + "\n"
                + "\uD83E\uDD8A" + "Fox: " + foxes + "\n" + "\uD83D\uDC07" + "Rabbits: " + rabbits + "\n"
                + "\uD83D\uDC11" + "Sheep: " + sheeps + "\n" + "\uD83D\uDC10" + "Goat: " + goats + "\n"
                + "\uD83D\uDC0E" + "Horse: " + horses + "\n" + "\uD83E\uDD8C" + "Deer: " + deers + "\n"
                + "\uD83D\uDC17" + "Boar: " + boars + "\n" + "\uD83D\uDC03" + "Buffalo: " + buffaloes + "\n" + "\uD83D\uDC01" + "Mouse: "
                + mouses + "\n" + "\uD83E\uDD86" + "Duck: " + ducks + "\n" + "\uD83D\uDC1B" + "Caterpillar: " + caterpillars);


    }
}




