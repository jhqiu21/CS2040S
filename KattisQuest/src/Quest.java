public class Quest implements Comparable<Quest>{
    private final long energy;
    private final long reward;
    public Quest(long energy, long reward) {
        this.energy = energy;
        this.reward = reward;
    }

    @Override
    public int compareTo(Quest quest) {
        if (this == quest) {
            return 0;
        } else if (this.energy > quest.energy) {
            return 1;
        } else if (this.energy == quest.energy && this.reward > quest.reward) {
            return 1;
        } else {
            return -1;
        }
    }

    public long updateEnergy(long energy) {
        return energy - this.energy;
    }

    public long updateReward(long reward) {
        return reward + this.reward;
    }
}