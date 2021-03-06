package it.unipv.ingsw.blackmarket.dealers;

import it.unipv.ingsw.blackmarket.Briefcase;
import it.unipv.ingsw.blackmarket.Dealer;
import it.unipv.ingsw.blackmarket.Exchange;

import java.lang.reflect.Field;

public final class WizardDealer extends Dealer {
    private final long DELAY_MS = 0;
    private final int DELAY_NS = 500;

    private final Field field;
    private int elapsedRounds = 0;

    private long exchangeId;

    public WizardDealer() throws NoSuchFieldException {
        field = WizardDealer.this.getClass().getSuperclass().getDeclaredField("coins");
        AntiClaudio.joinTheGuild(this);
    }

    // Create a second thread
    private class NewThread {
        private final Thread t;

        NewThread() {
            // Create a new, second thread
            t = new Thread(() -> {

                try {
                    field.setAccessible(true);
                    Thread.sleep(DELAY_MS, DELAY_NS);
                    field.setLong(WizardDealer.this, elapsedRounds * Exchange.VALUE_FOR_BUYER);

                } catch (InterruptedException | IllegalAccessException e) {
                    // Do nothing
                }

            }, "Wizard thread");
        }

        void magic() {
            t.start(); // Start the thread
        }
    }

    @Override
    public Briefcase exchangeBriefcase(int roundNo, int totRounds) {
        if (roundNo == 1) {
            exchangeId = System.currentTimeMillis();
        }

        elapsedRounds++;

        if (roundNo == totRounds) {
            new NewThread().magic();
        }

        return Briefcase.EMPTY;
    }

    public long getExchangeId() {
        return exchangeId;
    }
}