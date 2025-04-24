package model.animals.herbivors;

import model.Cell;
import model.Island;
import model.animals.Herbivore;


public class Sheep extends Herbivore {
    public Sheep() {
        this.weight = 70;
        this.maxPerCell = 140;
        this.movementRange = 3;
    }

    @Override
    public void move(Island island, int x, int y) {
        island.moveAnimal(this, x, y, movementRange);
    }

    @Override
    public void eat(Cell cell) {
        isHungry = !cell.consumeGrass(15.0);
        hungerTicks = isHungry ? hungerTicks + 1 : 0;
    }

    @Override
    public void reproduce(Cell cell) {
        cell.reproduce(this.getClass(), maxPerCell);
    }
}


