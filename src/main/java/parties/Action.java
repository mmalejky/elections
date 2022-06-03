package parties;

import java.util.List;

public class Action implements Comparable<Action> {

    private List<Integer> changes;

    public Action(List<Integer> changes) {
        this.changes = changes;
    }

    public int cost(int population) {
        int sum = 0;
        for (int value : changes) {
            sum += Math.abs(value);
        }
        return sum * population;
    }

    public List<Integer> getChanges() {
        return changes;
    }

    @Override
    public int compareTo(Action o) {
        return this.cost(1) - o.cost(1);
    }

}
