package voters;

import parties.Candidate;
import voting.ElectoralDistrict;

public class FixedCandidateVoter extends Voter {

    public FixedCandidateVoter(String name, String surname, ElectoralDistrict district, Candidate candidate) {
        super(name, surname, district);
        this.candidate = candidate;
    }

    @Override
    public void chooseCandidate() {
        //DO NOTHING
    }
}
