package voting;

import electoralSystems.ElectoralSystem;
import parties.Candidate;
import parties.Party;

import java.util.List;
import java.util.Random;

public class Elections {

    private List<Party> parties;
    private List<ElectoralDistrict> electoralDistricts;

    public Elections(List<ElectoralDistrict> electoralDistricts, List<Party> parties) {
        this.electoralDistricts = electoralDistricts;
        this.parties = parties;
    }

    public static Candidate randomCandidate(List<Candidate> candidates) {
        Random random = new Random();
        return candidates.get(random.nextInt(candidates.size()));
    }

    private void proceedVoting() {
        for (ElectoralDistrict district : electoralDistricts)
            district.proceedVoting();
    }

    private void allocateSeats(ElectoralSystem method) {
        for (ElectoralDistrict district : electoralDistricts)
            district.allocateSeats(method);
    }

    private void resetSeats() {
        for (ElectoralDistrict district : electoralDistricts) {
            district.resetSeats();
        }
    }

    private void getPartiesTotalSeats() {
        for (Party party : parties) {
            int sum = 0;
            for (ElectoralDistrict district : electoralDistricts) {
                sum += district.getSeatCount(party.getName());
            }
            System.out.println(party.getName() + " " + sum);
        }
    }

    private void printDistricts() {
        for (ElectoralDistrict district : electoralDistricts) {
            district.printElectionsData();
        }
    }

    public void proceedCampaign() {
        for (Party party : parties) {
            party.proceedCampaign();
        }
    }

    public void proceedElections(ElectoralSystem method) {
        System.out.println(method.getName());
        proceedVoting();
        allocateSeats(method);
        printDistricts();
        getPartiesTotalSeats();
        resetSeats();
    }

}
