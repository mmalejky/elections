package voting;

import parties.Party;

public class ElectoralQuotient implements Comparable<ElectoralQuotient> {

    private Double quotient;
    private Party party;

    public ElectoralQuotient(double quotient, Party party) {
        this.quotient = quotient;
        this.party = party;
    }

    @Override
    public int compareTo(ElectoralQuotient o) {
        return quotient.compareTo(o.getQuotient());
    }

    public Double getQuotient() {
        return quotient;
    }

    public Party getParty() {
        return party;
    }

}
