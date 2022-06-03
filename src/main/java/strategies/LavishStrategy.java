package strategies;

import parties.Action;
import cover.Pair;
import parties.Party;
import voting.ElectoralDistrict;

import java.util.List;

public class LavishStrategy extends Strategy {

    @Override
    public Pair<Action, ElectoralDistrict> getBestAction(List<Action> actions, List<ElectoralDistrict> districts,
                                                         Party party) {
        Pair<Action, ElectoralDistrict> best = null;
        int max_cost = -1;
        for (Action action : actions) {
            for (ElectoralDistrict district : districts) {
                int population = district.getPopulation();
                int cost = action.cost(population);
                if (cost > max_cost && cost <= party.getBudget()) {
                    max_cost = action.cost(population);
                    best = new Pair<>(action, district);
                }
            }
        }
        return best;
    }
}
