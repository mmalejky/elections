package voters;

import parties.Action;
import parties.Candidate;
import voting.ElectoralDistrict;

public abstract class Voter {

    protected String name;
    protected String surname;
    protected ElectoralDistrict district;
    protected Candidate candidate;
    private boolean voted;

    public abstract void chooseCandidate();

    public void vote() {
        if (!voted) {
            chooseCandidate();
            district.addVote(candidate);
            voted = true;
        }
    }

    public void influence(Action action) {
        ;
    }

    public Voter(String name, String surname, ElectoralDistrict district) {
        this.voted = false;
        this.name = name;
        this.surname = surname;
        this.district = district;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    @Override
    public String toString() {
        return name + ' ' + surname;
    }
}
