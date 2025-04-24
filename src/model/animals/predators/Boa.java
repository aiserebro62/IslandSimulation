package model.animals.predators;


import model.Cell;
import model.Island;
import model.animals.herbivors.*;
import model.animals.Predator;
import model.animals.omnivors.*;


public class Boa extends Predator {
    public Boa() {
        this.weight = 15;
        this.maxPerCell = 30;
        this.movementRange = 1;
    }

    @Override
    public void move(Island island, int x, int y) {
        island.moveAnimal(this, x, y, movementRange);
    }

    @Override
    public void eat(Cell cell) {
        double foodNeeded = 3.0;
        foodNeeded -= cell.consumePrey(Rabbit.class, 1, 2.0);
        if (foodNeeded > 0) foodNeeded -= cell.consumePrey(Mouse.class, 60, 0.05);
        if (foodNeeded > 0) foodNeeded -= cell.consumePrey(Duck.class, 3, 1.0);
        if (foodNeeded > 0) foodNeeded -= cell.consumePrey(Fox.class, 1, 8.0);
        isHungry = foodNeeded > 0;
        hungerTicks = isHungry ? hungerTicks + 1 : 0;
    }

    @Override
    public void reproduce(Cell cell) {
        cell.reproduce(this.getClass(), maxPerCell);
    }
}



