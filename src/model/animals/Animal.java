package model.animals;

import model.Cell;
import model.Island;


public abstract class Animal {
    protected double weight;
    protected int maxPerCell;
    protected int movementRange;
    protected boolean isHungry = true;// Флаг голода (true - животное голодное)
    protected int hungerTicks = 0; // Счетчик тактов голодания (сколько тактов животное не ело)

    public abstract void move(Island island, int x, int y);

    public abstract void eat(Cell cell);

    public abstract void reproduce(Cell cell);

    public boolean isDead() {
        // Умирает, если не ело 2 такта подряд
        return hungerTicks >=2;
    }
}






