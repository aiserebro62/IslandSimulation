package model;


public class Plant {
    private double grassAmount = 200.0;

    public synchronized boolean consume(double amount) {
        if (grassAmount >= amount) {
            grassAmount -= amount;
            return true;
        }
        return false;
    }

    public synchronized void grow() {
        grassAmount += 10.0; // Рост каждый такт
        if (grassAmount > 200.0) grassAmount = 200.0;
    }
}


