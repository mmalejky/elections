package electoralSystems;

import parties.Party;
import voting.ElectoralDistrict;
import voting.ElectoralQuotient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HareNiemeyerMethod extends ElectoralSystem {

    @Override
    public String getName() {
        return "Metoda Hare’a-Niemeyera";
    }

    @Override
    public void allocateSeats(ElectoralDistrict district, List<Party> parties) {
        int availableSeats = district.getPopulation() / 10;
        int totalSeatCount = 0;
        List<ElectoralQuotient> quotients = new ArrayList<>();
        for (Party party : parties) {
            double temp = district.getVoteCount(party.getName()) / 10.0;
            district.addSeats(party.getName(), (int) temp);
            totalSeatCount += (int) temp;
            quotients.add(new ElectoralQuotient(temp - (int) temp, party));
        }

        Collections.sort(quotients);
        Collections.reverse(quotients);
        for (int i = 0; i < availableSeats - totalSeatCount; ++i) {
            district.addSeats(quotients.get(i).getParty().getName(), 1);
        }
    }
}
