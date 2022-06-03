package voters;

import parties.Candidate;
import parties.Party;
import voting.Elections;
import voting.ElectoralDistrict;

import java.util.ArrayList;
import java.util.List;

public class MaximizingFixedPartyVoter extends Voter {
    private int attributeIndex;
    private Party party;

    public MaximizingFixedPartyVoter(String name, String surname, ElectoralDistrict district, int attributeIndex, Party party) {
        super(name, surname, district);
        this.attributeIndex = attributeIndex;
        this.party = party;
    }

    @Override
    public void chooseCandidate() {
        List<Candidate> options = district.getCandidates(party);
        int max = -101;
        List<Candidate> podium = new ArrayList<>();
        for (Candidate candidate : options) {
            int temp = candidate.getAttribute(attributeIndex);
            if (temp > max) {
                podium = new ArrayList<>();
                podium.add(candidate);
                max = temp;
            } else if (temp == max) {
                podium.add(candidate);
            }
        }
        candidate = Elections.randomCandidate(podium);
    }

}
