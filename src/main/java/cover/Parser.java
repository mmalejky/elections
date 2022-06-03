package cover;

import parties.*;
import strategies.*;
import voters.*;
import voting.Elections;
import voting.ElectoralDistrict;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parser {

    private static Strategy newStrategy(String s) {
        switch (s) {
            case "R":
                return new LavishStrategy();
            case "S":
                return new ModestStrategy();
            case "W":
                return new MyStrategy();
            case "Z":
                return new GreedyStrategy();
            default:
                return null;
        }
    }

    public static Elections parseElectionsData(File file) {

        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        // Basic info
        Scanner basicInfoData = new Scanner(scanner.nextLine());
        int districtCount, partyCount, campaignCount, attributesCount;
        districtCount = Integer.parseInt(basicInfoData.next());
        partyCount = Integer.parseInt(basicInfoData.next());
        campaignCount = Integer.parseInt(basicInfoData.next());
        attributesCount = Integer.parseInt(basicInfoData.next());
        basicInfoData.close();

        // Merging
        int[] index = new int[districtCount + 1];
        for (int i = 1; i <= districtCount; ++i) {
            index[i] = i;
        }

        List<Integer> districtIndexes = new ArrayList<>();
        for (int i = 1; i <= districtCount; ++i) {
            districtIndexes.add(i);
        }
        Scanner mergingData = new Scanner(scanner.nextLine());
        int mergeCount = Integer.parseInt(mergingData.next());
        for (int i = 0; i < mergeCount; ++i) {
            String word = mergingData.next();
            String[] words = word.substring(1, word.length() - 1).split(",");
            int a = Integer.parseInt(words[0]);
            int b = Integer.parseInt(words[1]);
            index[b] = a;
            districtIndexes.remove(Integer.valueOf(b));
        }
        mergingData.close();

        // Parties
        Map<String, Party> parties = new HashMap<>();
        Scanner partyNames = new Scanner(scanner.nextLine());
        Scanner partyBudgets = new Scanner(scanner.nextLine());
        Scanner partyStrategies = new Scanner(scanner.nextLine());
        for (int i = 0; i < partyCount; ++i) {
            String name = partyNames.next();
            parties.put(name, new Party(name, newStrategy(partyStrategies.next()),
                    Integer.parseInt(partyBudgets.next())));
        }
        List<Party> partyList = new ArrayList<>(parties.values());
        partyNames.close();
        partyBudgets.close();
        partyStrategies.close();

        // Districts
        List<ElectoralDistrict> districts = new ArrayList<>();
        for (int i = 0; i <= districtCount; ++i) {
            if (districtIndexes.contains(i)) {
                ElectoralDistrict district = new ElectoralDistrict(i, partyList);
                districts.add(district);
            } else {
                districts.add(null);
            }
        }
        Scanner districtPopulationData = new Scanner(scanner.nextLine());
        int[] districtPopulation = new int[districtCount + 1];
        for (int i = 1; i <= districtCount; ++i) {
            districtPopulation[i] = Integer.parseInt(districtPopulationData.next());
        }
        districtPopulationData.close();
        for (int i = 1; i <= districtCount; ++i) {
            districts.get(index[i]).addPopulation(districtPopulation[i]);
        }


        // Candidates
        for (int i = 1; i <= districtCount; ++i) {
            for (Party party : partyList) {
                for (int j = 1; j <= districtPopulation[i] / 10; ++j) {
                    Scanner candidateData = new Scanner(scanner.nextLine());
                    String name = candidateData.next();
                    String surname = candidateData.next();
                    candidateData.next(); // Pass district number
                    candidateData.next(); // Pass party name
                    candidateData.next(); // Pass list index
                    List<Integer> attributes = new ArrayList<>();
                    for (int k = 0; k < attributesCount; ++k) {
                        attributes.add(Integer.parseInt(candidateData.next()));
                    }
                    Candidate candidate = new Candidate(name, surname, party, attributes, j);
                    party.addCandidate(candidate);
                    districts.get(index[i]).addCandidate(candidate);
                    candidateData.close();
                }
            }
        }

        // Voters
        for (int i = 1; i <= districtCount; ++i) {
            for (int j = 1; j <= districtPopulation[i]; ++j) {
                Scanner voterData = new Scanner(scanner.nextLine());
                String name = voterData.next();
                String surname = voterData.next();
                voterData.next(); // Pass district number
                int type = Integer.parseInt(voterData.next());
                Voter voter;
                switch (type) {
                    case 1:
                        voter = new FixedPartyVoter(name, surname, districts.get(index[i]),
                                parties.get(voterData.next()));
                        break;
                    case 2:
                        voter = new FixedCandidateVoter(name, surname, districts.get(index[i]),
                                parties.get(voterData.next()).getCandidate(Integer.parseInt(voterData.next())));
                        break;
                    case 3:
                        voter = new MinimizingVoter(name, surname, districts.get(index[i]),
                                Integer.parseInt(voterData.next()));
                        break;
                    case 4:
                        voter = new MaximizingVoter(name, surname, districts.get(index[i]),
                                Integer.parseInt(voterData.next()));
                        break;
                    case 5:
                        List<Integer> attributeWeights = new ArrayList<>();
                        for (int k = 0; k < attributesCount; ++k) {
                            attributeWeights.add(Integer.parseInt(voterData.next()));
                        }
                        voter = new ComprehensiveVoter(name, surname, districts.get(index[i]), attributeWeights);
                        break;
                    case 6:
                        voter = new MinimizingFixedPartyVoter(name, surname, districts.get(index[i]),
                                Integer.parseInt(voterData.next()), parties.get(voterData.next()));
                        break;
                    case 7:
                        voter = new MaximizingFixedPartyVoter(name, surname, districts.get(index[i]),
                                Integer.parseInt(voterData.next()), parties.get(voterData.next()));
                        break;
                    case 8:
                        attributeWeights = new ArrayList<>();
                        for (int k = 0; k < attributesCount; ++k) {
                            attributeWeights.add(Integer.parseInt(voterData.next()));
                        }
                        voter = new ComprehensiveFixedPartyVoter(name, surname, districts.get(index[i]),
                                attributeWeights, parties.get(voterData.next()));
                        break;
                    default:
                        voter = null;
                }
                districts.get(index[i]).addVoter(voter);
                voterData.close();
            }
        }

        // Actions
        List<Action> actions = new ArrayList<>();
        for (int i = 0; i < campaignCount; ++i) {
            Scanner actionData = new Scanner(scanner.nextLine());
            List<Integer> changes = new ArrayList<>();
            for (int j = 0; j < attributesCount; ++j) {
                changes.add(Integer.parseInt(actionData.next()));
            }
            actions.add(new Action(changes));
            actionData.close();
        }
        for (Party party : partyList) {
            party.setActions(actions);
        }


        List<ElectoralDistrict> resultDistricts = new ArrayList<>();
        for (ElectoralDistrict district : districts) {
            if (district != null) {
                resultDistricts.add(district);
            }
        }

        for (Party party : partyList) {
            party.setDistricts(resultDistricts);
        }

        scanner.close();
        return new Elections(resultDistricts, partyList);
    }

}
