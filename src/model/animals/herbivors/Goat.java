package model.animals.herbivors;

import model.Cell;
import model.Island;
import model.animals.Herbivore;

public class Goat extends Herbivore {
    public Goat() {
        this.weight = 60;
        this.maxPerCell = 140;
        this.movementRange = 3;
    }

    @Override
    public void move(Island island, int x, int y) {
        island.moveAnimal(this, x, y, movementRange);
    }

    @Override
    public void eat(Cell cell) {
        isHungry = !cell.consumeGrass(10.0);
        hungerTicks = isHungry ? hungerTicks + 1 : 0;
    }

    @Override
    public void reproduce(Cell cell) {
        cell.reproduce(this.getClass(), maxPerCell);
    }
}


