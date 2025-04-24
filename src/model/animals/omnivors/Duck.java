package model.animals.omnivors;

import model.Cell;
import model.Island;
import model.animals.Omnivore;
import model.animals.herbivors.Caterpillar;


public class Duck extends Omnivore {
    public Duck() {
        this.weight = 1.0;
        this.maxPerCell = 200;
        this.movementRange = 4;
    }

    @Override
    public void move(Island island, int x, int y) {
        island.moveAnimal(this, x, y, movementRange);
    }

    @Override
    public void eat(Cell cell) {
        double foodNeeded = 0.15;
        foodNeeded -= cell.consumePrey(Caterpillar.class, 15, 0.01);
        if (foodNeeded > 0) foodNeeded -= cell.consumeGrass(0.15) ? 0.15 : 0.0;

        isHungry = foodNeeded > 0;
        hungerTicks = isHungry ? hungerTicks + 1 : 0;
    }

    @Override
    public void reproduce(Cell cell) {
        cell.reproduce(this.getClass(), maxPerCell);
    }
}


