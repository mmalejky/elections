package parties;

import cover.Pair;
import strategies.Strategy;
import voters.Voter;
import voting.ElectoralDistrict;

import java.util.ArrayList;
import java.util.List;

public class Party {

    private String name;
    private Strategy strategy;
    private int budget;
    private List<Action> actions;
    private List<ElectoralDistrict> districts;
    private List<Candidate> candidates;

    public Party(String name, Strategy strategy, int budget) {
        this.name = name;
        this.strategy = strategy;
        this.budget = budget;
        this.candidates = new ArrayList<>();
    }

    public int getBudget() {
        return budget;
    }

    public void setDistricts(List<ElectoralDistrict> districts) {
        this.districts = districts;
    }

    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
    }

    public void proceedCampaign() {
        boolean canMakeAction = true;
        while (canMakeAction) {
            Pair<Action, ElectoralDistrict> bestChoice = strategy.getBestAction(actions, districts, this);
            if (bestChoice == null) {
                canMakeAction = false;
            } else {
                budget -= bestChoice.getFirst().cost(bestChoice.getSecond().getPopulation());
                for (Voter voter : bestChoice.getSecond().getVoters()) {
                    voter.influence(bestChoice.getFirst());
                }
            }
        }
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public Candidate getCandidate(int index) {
        return candidates.get(index - 1);
    }

    public String getName() {
        return name;
    }

}
