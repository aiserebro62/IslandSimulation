package model.animals.predators;

import model.Cell;
import model.Island;
import model.animals.Predator;
import model.animals.herbivors.Rabbit;
import model.animals.omnivors.*;


public class Eagle extends Predator {
    public Eagle() {
        this.weight = 6;
        this.maxPerCell = 20;
        this.movementRange = 3;
    }

    @Override
    public void move(Island island, int x, int y) {
        island.moveAnimal(this, x, y, movementRange);
    }

    @Override
    public void eat(Cell cell) {
        double foodNeeded = 1.0;
        foodNeeded -= cell.consumePrey(Rabbit.class, 1, 2.0);
        if (foodNeeded > 0) foodNeeded -= cell.consumePrey(Mouse.class, 20, 0.05);
        if (foodNeeded > 0) foodNeeded -= cell.consumePrey(Duck.class, 1, 1.0);
        if (foodNeeded > 0) foodNeeded -= cell.consumePrey(Fox.class, 1, 8.0);
        isHungry = foodNeeded > 0;
        hungerTicks = isHungry ? hungerTicks + 1 : 0;
    }

    @Override
    public void reproduce(Cell cell) {
        cell.reproduce(this.getClass(), maxPerCell);
    }
}


