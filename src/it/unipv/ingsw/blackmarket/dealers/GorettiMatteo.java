//Goretti Matteo
// matricola: 438952

package it.unipv.ingsw.blackmarket.dealers;

import it.unipv.ingsw.blackmarket.Briefcase;
import it.unipv.ingsw.blackmarket.Dealer;
import it.unipv.ingsw.blackmarket.Exchange;

import java.util.ArrayList;
import java.util.Random;

public class GorettiMatteo extends Dealer{

    private Briefcase briefcase;
    private double trust;

    public GorettiMatteo() {
        this.briefcase = Briefcase.EMPTY;
        this.trust = 0.5;
    }

    @Override
    public Briefcase exchangeBriefcase(int roundNo, int totRounds) {

        if (roundNo == 1) {
            trust = 0.5;
            return Briefcase.FULL;
        }else if(roundNo == totRounds){
            return Briefcase.EMPTY;
        }
        else {
            return briefcase;
        }
    }

    @Override
    public void exchangeResult(Exchange exchange, int roundNo, int totRounds) {
        Briefcase bag = exchange.secondBriefcase();

        if(bag == Briefcase.EMPTY){
            trust -= 0.17;
        }else{
            trust += 0.1;
        }
        if(trust>=0.5) briefcase = Briefcase.FULL;
        else briefcase = Briefcase.EMPTY;

    }
}