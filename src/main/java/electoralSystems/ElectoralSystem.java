package electoralSystems;

import parties.Party;
import voting.ElectoralDistrict;

import java.util.List;

public abstract class ElectoralSystem {

    public abstract String getName();

    public abstract void allocateSeats(ElectoralDistrict district, List<Party> parties);

}
