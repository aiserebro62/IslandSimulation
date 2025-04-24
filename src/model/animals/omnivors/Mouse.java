package model.animals.omnivors;

import model.Cell;
import model.Island;
import model.animals.Omnivore;
import model.animals.herbivors.Caterpillar;


public class Mouse extends Omnivore {
    public Mouse() {
        this.weight = 0.05;
        this.maxPerCell = 500;
        this.movementRange = 1;
    }

    @Override
    public void move(Island island, int x, int y) {
        island.moveAnimal(this, x, y, movementRange);
    }

    @Override
    public void eat(Cell cell) {
        double foodNeeded = 0.01;
        foodNeeded  -= cell.consumePrey(Caterpillar.class, 1, 0.01);
        if (foodNeeded > 0) foodNeeded-= cell.consumeGrass(0.01) ? 0.01 : 0.0;
        isHungry = foodNeeded > 0;
        hungerTicks = isHungry ? hungerTicks + 1 : 0;
    }

    @Override
    public void reproduce(Cell cell) {
        cell.reproduce(this.getClass(), maxPerCell);
    }
}




