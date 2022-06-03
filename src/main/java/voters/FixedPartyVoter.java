package voters;

import parties.Party;
import voting.Elections;
import voting.ElectoralDistrict;


public class FixedPartyVoter extends Voter {

    private Party party;

    public FixedPartyVoter(String name, String surname, ElectoralDistrict district, Party party) {
        super(name, surname, district);
        this.party = party;
    }

    @Override
    public void chooseCandidate() {
        candidate = Elections.randomCandidate(district.getCandidates(party));
    }

}
