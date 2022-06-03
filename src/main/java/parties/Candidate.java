package parties;

import java.util.List;

public class Candidate {

    private String name;
    private String surname;
    private Party party;
    private List<Integer> attributes;
    private int voteCount;
    private int index;

    public Candidate(String name, String surname, Party party, List<Integer> attributes, int index) {
        this.name = name;
        this.surname = surname;
        this.party = party;
        this.attributes = attributes;
        this.index = index;
        voteCount = 0;
    }

    public int actionChangeCost(Action action) {
        int n = attributes.size();
        List<Integer> changes = action.getChanges();
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            int actualValue = attributes.get(i);
            int change = changes.get(i);
            sum += actualValue * change;
        }
        return sum;
    }

    public Party getParty() {
        return party;
    }

    public int getAttribute(int index) {
        return attributes.get(index - 1);
    }

    public void addVote() {
        ++voteCount;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return name + ' ' + surname;
    }
}
