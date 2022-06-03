package electoralSystems;

import parties.Party;
import voting.ElectoralDistrict;
import voting.ElectoralQuotient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SainteLagueMethod extends ElectoralSystem {

    @Override
    public String getName() {
        return "Metoda Sainte-Laguë";
    }

    @Override
    public void allocateSeats(ElectoralDistrict district, List<Party> parties) {
        int availableSeats = district.getPopulation() / 10;
        List<ElectoralQuotient> quotients = new ArrayList<>();
        for (Party party : parties) {
            for (double i = 1; i <= availableSeats; ++i) {
                quotients.add(new ElectoralQuotient(district.getVoteCount(party.getName()) / (2 * i - 1),
                              party));
            }
        }
        Collections.sort(quotients);
        Collections.reverse(quotients);
        for (int i = 0; i < availableSeats; ++i) {
            district.addSeats(quotients.get(i).getParty().getName(), 1);
        }
    }

}
