package voters;

import parties.Candidate;
import voting.Elections;
import voting.ElectoralDistrict;

import java.util.ArrayList;
import java.util.List;

public class MaximizingVoter extends Voter {

    private int attributeIndex;

    public MaximizingVoter(String name, String surname, ElectoralDistrict district, int attributeIndex) {
        super(name, surname, district);
        this.attributeIndex = attributeIndex;
    }

    @Override
    public void chooseCandidate() {
        List<Candidate> options = district.getCandidates(null);
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
