import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class MazeSolver implements IMazeSolver {
	private static final int NORTH = 0, SOUTH = 1, EAST = 2, WEST = 3;
	private static int[][] DELTAS = new int[][] {
		{ -1, 0 }, // North
		{ 1, 0 }, // South
		{ 0, 1 }, // East
		{ 0, -1 } // West
	};

	private Maze maze;
	private int steps = 0;
	private boolean solveStatus = false;
	private boolean[][] visit;
	private Queue<Node> node;
	private ArrayList<Integer> reachable;

	private class Node {
		private int x;
		private int y;
		private Node parent;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
			this.parent = null;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}
	}

	public MazeSolver() {
		// TODO: Initialize variables.
		this.node = new LinkedList<>();
		this.reachable = new ArrayList<>();
	}

	@Override
	public void initialize(Maze maze) {
		// TODO: Initialize the solver.
		this.maze = maze;
		this.visit = new boolean[maze.getRows()][maze.getColumns()];
	}

	private boolean canGo(int row, int col, int dir) {
		// Copy from MazeSolverNaive
		if (row + DELTAS[dir][0] < 0 || row + DELTAS[dir][0] >= maze.getRows()) return false;
		if (col + DELTAS[dir][1] < 0 || col + DELTAS[dir][1] >= maze.getColumns()) return false;

		switch (dir) {
			case NORTH:
				return !maze.getRoom(row, col).hasNorthWall();
			case SOUTH:
				return !maze.getRoom(row, col).hasSouthWall();
			case EAST:
				return !maze.getRoom(row, col).hasEastWall();
			case WEST:
				return !maze.getRoom(row, col).hasWestWall();
		}
		return false;
	}

	@Override
	public Integer pathSearch(int startRow, int startCol, int endRow, int endCol) throws Exception {
		// TODO: Find shortest path.
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
				this.maze.getRoom(i, j).onPath = false;
			}
		}

		// initialize everything before we begin a new search
		this.solveStatus = false;
		this.reachable = new ArrayList<>();
		this.node = new LinkedList<>();
		this.steps = 0;

		// set the start node and add the start node to the queue
		this.visit[startRow][startCol] = true;
		Node start = new Node(startRow, startCol);
		this.node.add(start);
		this.maze.getRoom(startRow,startCol).onPath = true;


		Node target = null;
		// maintain a count to track on the steps we use
		int count = 0;

		// start search using bfs
		while (!node.isEmpty()) {
			int len = this.node.size();
			// store the numOfReachable
			this.reachable.add(len);
			// Search for every adjcent node
			for (int i = 0; i < len; i++) {
				Node curr = node.poll();
				if (curr.x == endRow && curr.y == endCol) {
					this.solveStatus = true;
					this.steps = count;
					target = curr;
				}
				// find the next node
				for (int dir = 0; dir < 4; dir++) {
					if (canGo(curr.x, curr.y, dir)) {
						// calculate the location of next node
						int nextX = curr.x + DELTAS[dir][0];
						int nextY = curr.y + DELTAS[dir][1];
						if (!visit[nextX][nextY]) {
							visit[nextX][nextY] = true;
							Node next = new Node(nextX, nextY);
							next.setParent(curr);
							node.add(next);
						}
					}
				}
			}
			count++;
		}

		if (!this.solveStatus) {
			return null;
		}

		// update onPath status of the node on the path
		for (; target != null; target = target.parent) {
			this.maze.getRoom(target.x, target.y).onPath = true;
		}
		return steps;
	}

	@Override
	public Integer numReachable(int k) throws Exception {
		// TODO: Find number of reachable rooms.
		if (k < 0 || k > this.reachable.size() - 1) {
			return 0;
		}
		return this.reachable.get(k);
	}

	public static void main(String[] args) {
		// Do remember to remove any references to ImprovedMazePrinter before submitting
		// your code!
		try {
			Maze maze = Maze.readMaze("maze-sample.txt");
			IMazeSolver solver = new MazeSolver();
			solver.initialize(maze);

			System.out.println(solver.pathSearch(0, 0, 4, 1));
			MazePrinter.printMaze(maze);

			for (int i = 0; i <= 9; ++i) {
				System.out.println("Steps " + i + " Rooms: " + solver.numReachable(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
