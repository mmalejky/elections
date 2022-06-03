package voters;

import parties.Action;
import parties.Candidate;
import parties.Party;
import voting.ElectoralDistrict;

import java.util.List;

public class ComprehensiveFixedPartyVoter extends Voter {

    private List<Integer> attributeWeights;
    private Party party;

    public ComprehensiveFixedPartyVoter(String name, String surname, ElectoralDistrict district,
                                        List<Integer> attributeWeights, Party party) {
        super(name, surname, district);
        this.attributeWeights = attributeWeights;
        this.party = party;
    }

    @Override
    public void influence(Action action) {
        List<Integer> changes = action.getChanges();
        for (int i = 0; i < attributeWeights.size(); ++i) {
            attributeWeights.set(i, Math.max(-100, Math.min(100, attributeWeights.get(i) + changes.get(i))));
        }
    }

    @Override
    public void chooseCandidate() {
        List<Candidate> options = district.getCandidates(party);
        int max = -10001;
        Candidate best = options.get(0);
        for (Candidate candidate : options) {
            int temp = 0;
            for (int i = 1; i <= attributeWeights.size(); ++i) {
                temp += candidate.getAttribute(i) * attributeWeights.get(i - 1);
            }
            if (temp > max) {
                best = candidate;
                max = temp;
            }
        }
        candidate = best;
    }
}
