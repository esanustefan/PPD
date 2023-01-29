package org.example;

import java.util.Objects;

public class Banda {
    private final Integer capacitate;
    private Integer nrObiecte;
    private Integer nrFinishedProducers;
    private final Integer nrProducers, nrConsumers;
    private Integer nrFinishedConsumers; // to know when to stop if the band is empty

    public Banda(Integer capacitate, Integer nrProducers, Integer nrConsumers) {
        this.capacitate = capacitate;
        this.nrObiecte = 0;
        this.nrProducers = nrProducers;
        this.nrConsumers = nrConsumers;
        this.nrFinishedProducers = 0;
        this.nrFinishedConsumers = 0;
    }

    public synchronized int puneObiecte() throws InterruptedException {
        while(nrObiecte + 4 > capacitate) {
            if(Objects.equals(nrFinishedConsumers, nrConsumers)) {
                break;
            }
            this.wait();
        }
        nrObiecte += 4;
        this.notifyAll();
        return nrObiecte;
    }

    public synchronized int iaObiecte() throws InterruptedException {
        while (nrObiecte -3 < 0) {
            if(Objects.equals(nrFinishedProducers, nrProducers)) {
                break;
            }
            this.wait();
        }
        nrObiecte -= 3;
        this.notifyAll();
        return nrObiecte;
    }

    public synchronized void setFinishedProducers() {
        this.nrFinishedProducers++;
    }

    public synchronized void setFinishedConsumers() {
        this.nrFinishedConsumers++;
    }
}
