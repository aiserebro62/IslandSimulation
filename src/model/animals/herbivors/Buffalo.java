package model.animals.herbivors;

import model.Cell;
import model.Island;
import model.animals.Herbivore;

public class Buffalo extends Herbivore {
    public Buffalo() {
        this.weight = 700;
        this.maxPerCell = 10;
        this.movementRange = 4;
    }

    @Override
    public void move(Island island, int x, int y) {
        island.moveAnimal(this, x, y, movementRange);
    }

    @Override
    public void eat(Cell cell) {
        isHungry = !cell.consumeGrass(100.0);
        hungerTicks = isHungry ? hungerTicks + 1 : 0;
    }

    @Override
    public void reproduce(Cell cell) {
        cell.reproduce(this.getClass(), maxPerCell);
    }
}


