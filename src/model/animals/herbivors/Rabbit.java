package model.animals.herbivors;

import model.Cell;
import model.Island;
import model.animals.Herbivore;

public class Rabbit extends Herbivore {
    public Rabbit() {
        this.weight = 2;
        this.maxPerCell = 150;
        this.movementRange = 2;
    }

    @Override
    public void move(Island island, int x, int y) {
        island.moveAnimal(this, x, y, movementRange);
    }

    @Override
    public void eat(Cell cell) {
        isHungry = !cell.consumeGrass(0.45);
        hungerTicks = isHungry ? hungerTicks + 1 : 0;
    }

    @Override
    public void reproduce(Cell cell) {
        cell.reproduce(this.getClass(), maxPerCell);
    }
}




