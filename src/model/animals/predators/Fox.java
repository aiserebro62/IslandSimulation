package model.animals.predators;

import model.Cell;
import model.Island;
import model.animals.Predator;
import model.animals.herbivors.Rabbit;
import model.animals.omnivors.Duck;
import model.animals.omnivors.Mouse;


public class Fox extends Predator {
    public Fox() {
        this.weight = 8;
        this.maxPerCell = 30;
        this.movementRange = 3;
    }

    @Override
    public void move(Island island, int x, int y) {
        island.moveAnimal(this, x, y, movementRange);
    }

    @Override
    public void eat(Cell cell) {
        double foodNeeded = 2.0;
        foodNeeded -= cell.consumePrey(Rabbit.class, 2, 2.0);
        if (foodNeeded > 0) foodNeeded -= cell.consumePrey(Mouse.class, 40, 0.05);
        if (foodNeeded > 0) foodNeeded -= cell.consumePrey(Duck.class, 2, 1.0);
        isHungry = foodNeeded > 0;
        hungerTicks = isHungry ? hungerTicks + 1 : 0;
    }

    @Override
    public void reproduce(Cell cell) {
        cell.reproduce(this.getClass(), maxPerCell);
    }
}





