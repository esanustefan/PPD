package org.example;

public class Banda {
    private final Integer capacitate;
    private Integer nrObiecte;
    private final Object capatStangaLock, capatDreaptaLock, obiecteLock;

    public Banda(Integer capacitate) {
        this.capacitate = capacitate;
        this.nrObiecte = 0;
        this.capatStangaLock = new Object();
        this.capatDreaptaLock = new Object();
        this.obiecteLock = new Object();
    }

    public int puneObiecte() throws InterruptedException {
        synchronized (capatStangaLock) {
            while(nrObiecte + 4 > capacitate) {
                this.wait();
            }
            synchronized (obiecteLock) {
                nrObiecte += 4;
            }
            return nrObiecte;
        }
    }

    public int iaObiecte() {
        synchronized (capatDreaptaLock) {
            synchronized (obiecteLock) {
                nrObiecte -= 3;
            }
            this.notifyAll();
            return nrObiecte;
        }
    }
}
