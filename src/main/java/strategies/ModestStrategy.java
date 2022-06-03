package strategies;

import parties.Action;
import cover.Pair;
import parties.Party;
import voting.ElectoralDistrict;

import java.util.List;

public class ModestStrategy extends Strategy {
    @Override
    public Pair<Action, ElectoralDistrict> getBestAction(List<Action> actions, List<ElectoralDistrict> districts,
                                                         Party party) {
        Pair<Action, ElectoralDistrict> best = null;
        int min_cost = Integer.MAX_VALUE;
        for (Action action : actions) {
            for (ElectoralDistrict district : districts) {
                int population = district.getPopulation();
                int cost = action.cost(population);
                if (cost < min_cost && cost <= party.getBudget()) {
                    min_cost = action.cost(population);
                    best = new Pair<>(action, district);
                }
            }
        }
        return best;
    }
}
