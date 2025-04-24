package model.animals.omnivors;

import model.Cell;
import model.Island;
import model.animals.Omnivore;
import model.animals.herbivors.*;


public class Boar extends Omnivore {
    public Boar() {
        this.weight = 400;
        this.maxPerCell = 50;
        this.movementRange = 2;
    }

    @Override
    public void move(Island island, int x, int y) {
        island.moveAnimal(this, x, y, movementRange);
    }

    @Override
    public void eat(Cell cell) {
        double foodNeeded = 50.0;
        foodNeeded -= cell.consumePrey(Caterpillar.class, 5000, 0.01);
        if (foodNeeded > 0) foodNeeded -= cell.consumePrey(Mouse.class, 1000, 0.05);
        if (foodNeeded > 0) foodNeeded -= cell.consumeGrass(50.0) ? 50.0 : 0.0;
        isHungry = foodNeeded > 0;

    }

    @Override
    public void reproduce(Cell cell) {
        cell.reproduce(this.getClass(), maxPerCell);
    }
}


