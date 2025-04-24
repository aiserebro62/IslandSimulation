package model.animals.predators;

import model.Cell;
import model.Island;
import model.animals.herbivors.*;
import model.animals.Predator;
import model.animals.omnivors.Boar;
import model.animals.omnivors.Duck;
import model.animals.omnivors.Mouse;


public class Wolf extends Predator {
    public Wolf() {
        this.weight = 50;
        this.maxPerCell = 30;
        this.movementRange = 3;
    }

    @Override
    public void move(Island island, int x, int y) {
        island.moveAnimal(this, x, y, movementRange);
    }

    @Override
    public void eat(Cell cell) {
        double foodNeeded = 8.0;
        // Поедание кроликов, мышей, овец в приоритетном порядке
        foodNeeded -= cell.consumePrey(Rabbit.class, 4, 2.0);
        if (foodNeeded > 0) foodNeeded -= cell.consumePrey(Mouse.class, 160, 0.05);
        if (foodNeeded > 0) foodNeeded -= cell.consumePrey(Sheep.class, 1, 70.0);
        if (foodNeeded > 0) foodNeeded -= cell.consumePrey(Goat.class, 1, 60.0);
        if (foodNeeded > 0) foodNeeded -= cell.consumePrey(Buffalo.class, 1, 700.0);
        if (foodNeeded > 0) foodNeeded -= cell.consumePrey(Horse.class, 1, 400.0);
        if (foodNeeded > 0) foodNeeded -= cell.consumePrey(Deer.class, 1, 300.0);
        if (foodNeeded > 0) foodNeeded -= cell.consumePrey(Boar.class, 1, 400.0);
        if (foodNeeded > 0) foodNeeded -= cell.consumePrey(Duck.class, 8, 1.0);
        isHungry = foodNeeded > 0;
        hungerTicks = isHungry ? hungerTicks + 1 : 0;
    }

    @Override
    public void reproduce(Cell cell) {
        cell.reproduce(this.getClass(), maxPerCell);
    }
}
