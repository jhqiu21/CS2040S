import java.util.TreeSet;
public class Solution {
    // TODO: Include your data structures here
    TreeSet<Team> score = new TreeSet<>();
    Team[] teams;

    public class Team implements Comparable<Team>{
        private int id;
        private int score;
        private int penalty;

        public Team(int id, int score, int penalty) {
            this.id = id;
            this.score = score;
            this.penalty = penalty;
        }

        public void addScore() {
            this.score += 1;
        }

        public void addPenalty(long penalty) {
            this.penalty += penalty;
        }

        @Override
        public int compareTo(Team t2) {
            if (this == t2) {
                return 0;
            } else if (this.score > t2.score) {
                return -1;
            } else if (this.score == t2.score && this.penalty < t2.penalty) {
                return -1;
            } else if (this.score == t2.score && this.penalty == t2.penalty && this.id < t2.id) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public Solution(int numTeams) {
        // TODO: Construct/Initialise your data structures here
        this.teams = new Team[numTeams];
        for (int i = 0; i < numTeams; i += 1) {
            this.teams[i] = new Team(i + 1, 0, 0);
        }
    }

    public int update(int team, long newPenalty){
        // TODO: Implement your update function here
        Team curr = this.teams[team - 1];
        score.remove(curr);
        curr.addScore();
        curr.addPenalty(newPenalty);
        if (team == 1) {
            while (!score.isEmpty()) {
                Team max = score.last();
                if (curr.compareTo(max) < 0) {
                    score.remove(max);
                } else {
                    break;
                }
            }
        }

        if (this.teams[0].compareTo(curr) > 0) {
            score.add(curr);
        }

        return score.size() + 1;
    }
}
