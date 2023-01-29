package org.example;

public class Consumator extends Thread{
    private static final int noIterations = 100;
    private final int threadId;
    private final Banda banda;
    private final ListaTranzactii listaTranzactii;

    public Consumator(int threadId, Banda banda, ListaTranzactii listaTranzactii) {
        this.threadId = threadId;
        this.banda = banda;
        this.listaTranzactii = listaTranzactii;
    }
    @Override
    public void run() {
        for(int i = 0; i < 100; i++) {
            int nrObiecte = banda.iaObiecte();
            Nod nod = new Nod(threadId, "preia", nrObiecte);
            listaTranzactii.addTranzactie(nod);
            try {
                sleep(8);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        listaTranzactii.setFinishedRobots();
    }
}
