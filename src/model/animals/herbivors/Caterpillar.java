package model.animals.herbivors;

import model.Cell;
import model.Island;
import model.animals.Herbivore;

public class Caterpillar extends Herbivore {
    public Caterpillar() {
        this.weight = 0.01;
        this.maxPerCell = 1000;
        this.movementRange = 0;
    }

    @Override
    public void move(Island island, int x, int y) {
        island.moveAnimal(this, x, y, movementRange);
    }

    @Override
    public void eat(Cell cell) {
        isHungry = !cell.consumeGrass(0.01);
        hungerTicks = isHungry ? hungerTicks + 1 : 0;
    }

    @Override
    public void reproduce(Cell cell) {

        cell.reproduce(this.getClass(), maxPerCell);
    }
}




