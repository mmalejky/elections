package strategies;

import parties.Action;
import cover.Pair;
import parties.Party;
import voting.ElectoralDistrict;

import java.util.List;

public abstract class Strategy {

    public abstract Pair<Action, ElectoralDistrict> getBestAction(List<Action> actions,
                                                                  List<ElectoralDistrict> districts, Party party);

}
