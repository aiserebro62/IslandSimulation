package model;


import model.animals.Animal;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cell {
    // потокобезопасный список животных в клетке (использует CopyOnWriteArrayList)
    private List<Animal> animals = new CopyOnWriteArrayList<>();
    private Plant plant = new Plant();

    public List<Animal> getAnimals() {

        return animals;//Возвращает общий вес съеденной добычи
    }

    public synchronized boolean consumeGrass(double amount) {
        return plant.consume(amount);
    }

    public double consumePrey(Class<? extends Animal> preyType, int maxCount, double preyWeight) {
        double consumed = 0.0;
        int count = 0;
        for (Animal animal : animals) {
            if (preyType.isInstance(animal) && count < maxCount) {
                animals.remove(animal);
                consumed += preyWeight;
                count++;
            }
        }
        return consumed;
    }
   // Создает новое животное, если есть хотя бы 2 особи одного вида, с помощью рефлексии
    public void reproduce(Class<? extends Animal> type, int maxPerCell) {
        long count = animals.stream().filter(a -> a.getClass() == type).count();
        if (count >= 2 && animals.size() < maxPerCell) {
            try {
                animals.add(type.getDeclaredConstructor().newInstance());
            } catch (Exception ignored) {
            }
        }
    }

    public void growPlants() {
        plant.grow();
    }


}


