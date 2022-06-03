package voting;

import electoralSystems.ElectoralSystem;
import parties.Candidate;
import parties.Party;
import voters.Voter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElectoralDistrict {

    private Map<String, Integer> seatCount;
    private Map<String, Integer> voteCount;
    private int index;
    private List<Party> parties;
    private List<Voter> voters;
    private List<Candidate> candidates;
    private int population;

    public ElectoralDistrict(int index, List<Party> parties) {
        this.index = index;
        this.parties = parties;
        this.voters = new ArrayList<>();
        this.candidates = new ArrayList<>();
        seatCount = emptyPartyMap();
        voteCount = emptyPartyMap();
    }

    public void addPopulation(int n) {
        population += n;
    }

    private Map<String, Integer> emptyPartyMap() {
        Map<String, Integer> result = new HashMap<>();
        for (Party party : parties) {
            result.put(party.getName(), 0);
        }
        return result;
    }

    public int getSeatCount(String partyName) {
        return seatCount.get(partyName);
    }

    public void resetSeats() {
        seatCount = emptyPartyMap();
    }

    public void addSeats(String partyName, int n) {
        seatCount.put(partyName, seatCount.get(partyName) + n);
    }

    public int getVoteCount(String partyName) {
        return voteCount.get(partyName);
    }

    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
    }

    public void addVoter(Voter voter) {
        voters.add(voter);
    }

    public int getPopulation() {
        return population;
    }

    public List<Candidate> getCandidates(Party party) {
        List<Candidate> result = new ArrayList<>();
        for (Candidate candidate : candidates) {
            if (party != null && candidate.getParty() != party) {
                continue;
            }
            result.add(candidate);
        }
        return result;
    }

    public void addVote(Candidate candidate) {
        candidate.addVote();
        String partyName = candidate.getParty().getName();
        voteCount.put(partyName, voteCount.get(partyName) + 1);
    }

    public List<Voter> getVoters() {
        return voters;
    }

    public void proceedVoting() {
        for (Voter voter : voters) {
            voter.vote();
        }
    }

    public void allocateSeats(ElectoralSystem method) {
        method.allocateSeats(this, parties);
    }

    public List<Party> getParties() {
        return parties;
    }

    public void printElectionsData() {
        StringBuilder sb = new StringBuilder();
        sb.append(index);
        sb.append('\n');
        for (Voter voter : voters) {
            sb.append(voter.toString());
            sb.append(' ');
            sb.append(voter.getCandidate().toString());
            sb.append('\n');
        }
        for (Candidate candidate : candidates) {
            sb.append(candidate.toString());
            sb.append(' ');
            sb.append(candidate.getParty().getName());
            sb.append(' ');
            sb.append(candidate.getIndex());
            sb.append(' ');
            sb.append(candidate.getVoteCount());
            sb.append('\n');
        }
        for (Party party : parties) {
            sb.append(party.getName());
            sb.append(' ');
            sb.append(seatCount.get(party.getName()));
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

}
