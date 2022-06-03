package cover;

import voting.Elections;
import electoralSystems.HareNiemeyerMethod;
import electoralSystems.DHondtMethod;
import electoralSystems.SainteLagueMethod;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        File file = new File(args[0]);
        Elections elections = Parser.parseElectionsData(file);
        if (elections == null) {
            return;
        }
        elections.proceedCampaign();
        elections.proceedElections(new DHondtMethod());
        elections.proceedElections(new SainteLagueMethod());
        elections.proceedElections(new HareNiemeyerMethod());
    }

}
