package strategies;

import parties.Action;
import cover.Pair;
import parties.Party;
import parties.Candidate;
import voting.ElectoralDistrict;

import java.util.List;

public class GreedyStrategy extends Strategy {

    @Override
    public Pair<Action, ElectoralDistrict> getBestAction(List<Action> actions, List<ElectoralDistrict> districts, Party party) {
        Pair<Action, ElectoralDistrict> best = null;
        int max_value = Integer.MIN_VALUE;
        for (ElectoralDistrict district : districts) {
            List<Candidate> candidates = district.getCandidates(party);
            for (Action action : actions) {
                int sum = 0;
                for (Candidate candidate : candidates) {
                    sum += candidate.actionChangeCost(action);
                }
                if (sum > max_value && action.cost(district.getPopulation()) <= party.getBudget()) {
                    best = new Pair<>(action, district);
                    max_value = sum;
                }
            }
        }
        return best;
    }

}
