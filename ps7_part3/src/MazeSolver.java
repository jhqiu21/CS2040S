import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.PriorityQueue;
import java.util.Comparator;



public class MazeSolver implements IMazeSolver {
	private static final int TRUE_WALL = Integer.MAX_VALUE;
	private static final int EMPTY_SPACE = 0;
	private static final List<Function<Room, Integer>> WALL_FUNCTIONS = Arrays.asList(
			Room::getNorthWall,
			Room::getEastWall,
			Room::getWestWall,
			Room::getSouthWall
	);
	private static final int[][] DELTAS = new int[][] {
			{ -1, 0 }, // North
			{ 0, 1 }, // East
			{ 0, -1 }, // West
			{ 1, 0 } // South
	};

	private Maze maze;
	private int[][] fearMatrix;
	private boolean[][] visit;
	private PriorityQueue<Node> node;

	private class Node implements Comparator<Node>{
		private int x;
		private int y;
		private int fearLevel;

		// comparator of Node pass to priority queue
		public Node() {}
		public Node(int x, int y, int fearLevel) {
			this.x = x;
			this.y = y;
			this.fearLevel = fearLevel;
		}

		/*
		public void setFearLevel(int fearLevel) {
			this.fearLevel = fearLevel;
		}
		 */

		@Override
		public int compare(Node n1, Node n2) {
			if (n1.fearLevel < n2.fearLevel) {
				return -1;
			}

			if (n1.fearLevel == n2.fearLevel) {
				return 0;
			}

			return 1;
		}
	}
	public MazeSolver() {
		// TODO: Initialize variables.
		// default constructor
	}

	@Override
	public void initialize(Maze maze) {
		// TODO: Initialize the solver.
		this.maze = maze;
		int capacity = maze.getRows() * maze.getColumns();
		this.node = new PriorityQueue<>(capacity, new Node());
		this.visit = new boolean[maze.getRows()][maze.getColumns()];
		this.fearMatrix = new int[maze.getRows()][maze.getColumns()];
	}

	@Override
	public Integer pathSearch(int startRow, int startCol, int endRow, int endCol) throws Exception {
		// TODO: Find minimum fear level.
		if (this.maze == null) {
			throw new Exception("Oh no! You cannot call me without initializing the maze!");
		}

		if (startRow < 0 || startCol < 0 || startRow >= maze.getRows() || startCol >= maze.getColumns() ||
				endRow < 0 || endCol < 0 || endRow >= maze.getRows() || endCol >= maze.getColumns()) {
			throw new IllegalArgumentException("Invalid start/end coordinate");
		}

		// set all visited flag to false
		// before we begin our search
		for (int i = 0; i < this.maze.getRows(); ++i) {
			for (int j = 0; j < this.maze.getColumns(); ++j) {
				this.visit[i][j] = false;
				this.fearMatrix[i][j] = TRUE_WALL;
			}
		}

		Node start = new Node(startRow, startCol, 0);
		this.fearMatrix[startRow][startCol] = 0;
		this.node.add(start);

		while (!this.node.isEmpty()) {
			Node curr = this.node.poll();
			if (!visit[curr.x][curr.y]) {
				visit[curr.x][curr.y] = true;

				for (int dir = 0; dir < 4; dir++) {
					int nextX = curr.x + DELTAS[dir][0];
					int nextY = curr.y + DELTAS[dir][1];
					boolean invalid = nextX < 0 || nextX >= this.maze.getRows() || nextY < 0 || nextY >= this.maze.getColumns();
					if (invalid) {
						continue;
					}

					Function<Room, Integer> funWall = WALL_FUNCTIONS.get(dir);
					int wallFare = funWall.apply(this.maze.getRoom(curr.x, curr.y));
					// shift the farelevel when the wall is a empty space
					if (wallFare == EMPTY_SPACE) {
						wallFare = 1;
					}
					// if next is not visit and not true wall, move on
					if (!this.visit[nextX][nextY] && wallFare != TRUE_WALL) {
						int nextFareLevel = this.fearMatrix[curr.x][curr.y] + wallFare;
						// this.fearMatrix[nextX][nextY] = nextFareLevel;
						// return the minimum possible fear level
						this.fearMatrix[nextX][nextY] = Math.min(this.fearMatrix[nextX][nextY], nextFareLevel);
						Node next = new Node(nextX, nextY, this.fearMatrix[nextX][nextY]);
						// next.setFearLevel(this.fearMatrix[nextX][nextY]);
						this.node.add(next);
					}
				}
			}
		}

		if (this.fearMatrix[endRow][endCol] == TRUE_WALL) {
			return null;
		}

		return this.fearMatrix[endRow][endCol];
	}

	@Override
	public Integer bonusSearch(int startRow, int startCol, int endRow, int endCol) throws Exception {
		// TODO: Find minimum fear level given new rules.
		return null;
	}

	@Override
	public Integer bonusSearch(int startRow, int startCol, int endRow, int endCol, int sRow, int sCol) throws Exception {
		// TODO: Find minimum fear level given new rules and special room.
		return null;
	}

	public static void main(String[] args) {
		try {
			Maze maze = Maze.readMaze("haunted-maze-sample.txt");
			IMazeSolver solver = new MazeSolver();
			solver.initialize(maze);

			System.out.println(solver.pathSearch(0, 0, 0, 5));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
