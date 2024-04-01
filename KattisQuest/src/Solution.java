import java.util.TreeSet;
public class Solution {
    TreeSet<Quest> questTreeSet = new TreeSet<Quest>();
    private static long MAX_VALUE = Long.MAX_VALUE;

    public class Quest implements Comparable<Quest>{
        private final long energy;
        private final long reward;
        public Quest(long energy, long reward) {
            this.energy = energy;
            this.reward = reward;
        }

        @Override
        public int compareTo(Quest quest) {
            if (energy != quest.energy) {
                return (int) (energy - quest.energy);
            } else {
                return (int) (reward - quest.reward);
            }
        }
    }

    public Solution() {
    }

    void add(long energy, long value) {
        questTreeSet.add(new Quest(energy, value));
    }

    long query(long remainingEnergy) {
        long total = 0;
        while (!questTreeSet.isEmpty() && remainingEnergy > 0) {
            Quest max = new Quest(remainingEnergy, MAX_VALUE);
            Quest remove = questTreeSet.floor(max);
            if (remove != null) {
                total += remove.reward;
                remainingEnergy -= remove.energy;
                questTreeSet.remove(remove);
            } else {
                break;
            }
        }
        return total;
    }
}
