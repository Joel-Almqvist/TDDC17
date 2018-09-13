package tddc17;

import aima.core.environment.liuvacuum.*;
import aima.core.agent.Action;
import aima.core.agent.AgentProgram;
import aima.core.agent.Percept;
import aima.core.agent.State;
import aima.core.agent.impl.*;

import java.util.ArrayList;
import java.util.Random;

import com.sun.media.jfxmedia.events.PlayerEvent;

class Node {
	public int x;
	public int y;
	public Node parent;

	Node(int x, int y, Node parent) {
		this.x = x;
		this.y = y;
		this.parent = parent;
	}

	public double distanceTo(Node goal) {
		return Math.sqrt(Math.pow(x - goal.x, 2) + Math.pow(y - goal.y, 2));
	}

	public Node copy(int x, int y) {
		return new Node(this.x + x, this.y + y, this);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Node && ((Node) o).x == this.x
				&& ((Node) o).y == this.y) {
			return true;
		}
		return false;
	}

}

class MyAgentState {
	public int[][] world = new int[30][30];
	public int initialized = 0;
	final int UNKNOWN = 0;
	final int WALL = 1;
	final int CLEAR = 2;
	final int DIRT = 3;
	final int HOME = 4;
	final int ACTION_NONE = 0;
	final int ACTION_MOVE_FORWARD = 1;
	final int ACTION_TURN_RIGHT = 2;
	final int ACTION_TURN_LEFT = 3;
	final int ACTION_SUCK = 4;

	public int agent_x_position = 1;
	public int agent_y_position = 1;
	public int agent_last_action = ACTION_NONE;

	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	public int agent_direction = EAST;

	MyAgentState() {
		for (int i = 0; i < world.length; i++)
			for (int j = 0; j < world[i].length; j++)
				world[i][j] = UNKNOWN;
		world[1][1] = HOME;
		agent_last_action = ACTION_NONE;
	}

	public Node findNearestUnexploredPos() {
		ArrayList<Node> unexploredPos = new ArrayList<Node>();
		for (int c = 0; c < agent_x_position; c++) {
			for (int r = 0; r < agent_y_position; r++) {
				if (world[r][c] == UNKNOWN) {
					unexploredPos.add(new Node(c, r, null));
				}
			}
		}
		Node closestPos = null;
		int minDist = Integer.MAX_VALUE;
		Node playerPos = new Node(agent_x_position, agent_y_position, null);
		for (Node pos : unexploredPos) {
			if (pos.distanceTo(playerPos) < minDist) {
				closestPos = pos;
			}
		}
		return closestPos;
	}

	public ArrayList<Node> generatePathToPos(Node goalPos) {
		ArrayList<Node> exploredSet = new ArrayList<Node>();
		ArrayList<Node> exploringQueue = new ArrayList<Node>();
		exploringQueue.add(goalPos);
		Node finishNode;

		while (true) {
			Node exploringNode = exploringQueue.get(0);
			exploringQueue.remove(0);

			if (exploringNode.x == agent_x_position
					&& exploringNode.y == agent_y_position) {
				finishNode = exploringNode;
				// TODO return found path
				break;
			}
			if (world[0].length > exploringNode.y + 1
					&& world[exploringNode.y + 1][exploringNode.x] == CLEAR) {
				Node neighbor = exploringNode.copy(0, 1);
				if (!exploredSet.contains(neighbor)) {
					exploringQueue.add(neighbor);
				}
			}
			if (world[exploringNode.y - 1][exploringNode.x] == CLEAR) {
				Node neighbor = exploringNode.copy(0, -1);
				if (!exploredSet.contains(neighbor)) {
					exploringQueue.add(neighbor);
				}
			}
			if (world[exploringNode.y][exploringNode.x + 1] == CLEAR) {
				Node neighbor = exploringNode.copy(1, 0);
				if (!exploredSet.contains(neighbor)) {
					exploringQueue.add(neighbor);
				}

			}
			if (world[exploringNode.y][exploringNode.x - 1] == CLEAR) {
				Node neighbor = exploringNode.copy(-1, 0);
				if (!exploredSet.contains(neighbor)) {
					exploringQueue.add(neighbor);
				}

			}
		}

		ArrayList<Node> path = new ArrayList<Node>();
		path.add(finishNode);

		while (true) {
			if (finishNode.parent != null) {
				path.add(finishNode.parent);
				finishNode = finishNode.parent;
			} else {
				break;
			}
		}

		return path;
	}

	// Takes one action towards the goal node
	public Action stepTowardsPos(Node goal){
		Node agentPos = new Node(agent_x_position, agent_y_position, null);
		System.out.println("start x = "+agentPos.x+" , y = "+agentPos.y);
		System.out.println("goal x = "+goal.x+" , y = "+goal.y);
		System.out.println(agent_direction);
		if(goal.distanceTo(agentPos) > 1){
			return NoOpAction.NO_OP;
		}
		
		// The goal is below us
		if(agentPos.y < goal.y){
			System.out.println("BELOW");
			switch(agent_direction) {
			case MyAgentState.NORTH:
				agent_last_action = ACTION_TURN_RIGHT;
				updateDirectionOLD();
				return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
			case MyAgentState.EAST:
				agent_last_action = ACTION_TURN_RIGHT;
				updateDirectionOLD();
				return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
			case MyAgentState.WEST:
				agent_last_action = ACTION_TURN_LEFT;
				updateDirectionOLD();
				return LIUVacuumEnvironment.ACTION_TURN_LEFT;
			default:
				agent_last_action = ACTION_MOVE_FORWARD;
				updateDirectionOLD();
				return LIUVacuumEnvironment.ACTION_MOVE_FORWARD;
			}
		}
		// The goal is above us
		else if(agentPos.y > goal.y){
			switch(agent_direction) {
			case MyAgentState.SOUTH:
				agent_last_action = ACTION_TURN_RIGHT;
				updateDirectionOLD();
				return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
			case MyAgentState.WEST:
				agent_last_action = ACTION_TURN_RIGHT;
				updateDirectionOLD();
				return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
			case MyAgentState.EAST:
				agent_last_action = ACTION_TURN_LEFT;
				updateDirectionOLD();
				return LIUVacuumEnvironment.ACTION_TURN_LEFT;
			default:
				agent_last_action = ACTION_MOVE_FORWARD;
				return LIUVacuumEnvironment.ACTION_MOVE_FORWARD;
			}
		}
		// The goal is to our left
		if(agentPos.x - goal.x > 0){
			switch(agent_direction) {
			case MyAgentState.NORTH:
				agent_last_action = ACTION_TURN_LEFT;
				updateDirectionOLD();
				return LIUVacuumEnvironment.ACTION_TURN_LEFT;
			case MyAgentState.EAST:
				agent_last_action = ACTION_TURN_RIGHT;
				updateDirectionOLD();
				return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
			case MyAgentState.SOUTH:
				agent_last_action = ACTION_TURN_RIGHT;
				updateDirectionOLD();
				return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
			default:
				agent_last_action = ACTION_MOVE_FORWARD;
				return LIUVacuumEnvironment.ACTION_MOVE_FORWARD;
			}
		}
		
		// The goal is to our right
		if(agentPos.x - goal.x < 0){
			switch(agent_direction) {
			case MyAgentState.NORTH:
				agent_last_action = ACTION_TURN_RIGHT;
				updateDirectionOLD();
				return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
			case MyAgentState.SOUTH:
				agent_last_action = ACTION_TURN_LEFT;
				updateDirectionOLD();
				return LIUVacuumEnvironment.ACTION_TURN_LEFT;
			case MyAgentState.EAST:
				agent_last_action = ACTION_TURN_RIGHT;
				updateDirectionOLD();
				return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
			default:
				agent_last_action = ACTION_MOVE_FORWARD;
				return LIUVacuumEnvironment.ACTION_MOVE_FORWARD;
			}
		}
		return NoOpAction.NO_OP;
	}

	public void updateDirectionOLD() {
		System.out.println(agent_last_action);
		if (agent_last_action == ACTION_TURN_LEFT) {
			agent_direction--;
			if(agent_direction == -1) agent_direction = 3;
		}
		if (agent_last_action == ACTION_TURN_RIGHT) {
			agent_direction++;
			if(agent_direction == 4) agent_direction = 0;
		}
	}

	// Based on the last action and the received percept updates the x & y agent
	// position
	public void updatePosition(DynamicPercept p) {
		Boolean bump = (Boolean) p.getAttribute("bump");

		if (agent_last_action == ACTION_MOVE_FORWARD && !bump) {
			switch (agent_direction) {
			case MyAgentState.NORTH:
				agent_y_position--;
				break;
			case MyAgentState.EAST:
				agent_x_position++;
				break;
			case MyAgentState.SOUTH:
				agent_y_position++;
				break;
			case MyAgentState.WEST:
				agent_x_position--;
				break;
			}
		}
	}

	public void updateWorld(int x_position, int y_position, int info) {
		world[x_position][y_position] = info;
	}

	public void printWorldDebug() {
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				if (world[j][i] == UNKNOWN)
					System.out.print(" ? ");
				if (world[j][i] == WALL)
					System.out.print(" # ");
				if (world[j][i] == CLEAR)
					System.out.print(" . ");
				if (world[j][i] == DIRT)
					System.out.print(" D ");
				if (world[j][i] == HOME)
					System.out.print(" H ");
			}
			System.out.println("");
		}
	}
}

class MyAgentProgram implements AgentProgram {

	private int initnialRandomActions = 10;
	private Random random_generator = new Random();

	// Here you can define your variables!
	public int iterationCounter = 50;
	public MyAgentState state = new MyAgentState();

	// moves the Agent to a random start position
	// uses percepts to update the Agent position - only the position, other
	// percepts are ignored
	// returns a random action
	private Action moveToRandomStartPosition(DynamicPercept percept) {
		int action = random_generator.nextInt(6);
		initnialRandomActions--;
		state.updatePosition(percept);
		if (action == 0) {
			state.agent_direction = ((state.agent_direction - 1) % 4);
			if (state.agent_direction < 0)
				state.agent_direction += 4;
			state.agent_last_action = state.ACTION_TURN_LEFT;
			return LIUVacuumEnvironment.ACTION_TURN_LEFT;
		} else if (action == 1) {
			state.agent_direction = ((state.agent_direction + 1) % 4);
			state.agent_last_action = state.ACTION_TURN_RIGHT;
			return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
		}
		state.agent_last_action = state.ACTION_MOVE_FORWARD;
		return LIUVacuumEnvironment.ACTION_MOVE_FORWARD;
	}

	@Override
	public Action execute(Percept percept) {

		// DO NOT REMOVE this if condition!!!
		if (initnialRandomActions > 0) {
			return moveToRandomStartPosition((DynamicPercept) percept);
		} else if (initnialRandomActions == 0) {
			// process percept for the last step of the initial random actions
			initnialRandomActions--;
			state.updatePosition((DynamicPercept) percept);
			System.out
					.println("Processing percepts after the last execution of moveToRandomStartPosition()");
			state.agent_last_action = state.ACTION_SUCK;
			return LIUVacuumEnvironment.ACTION_SUCK;
		}

		// This example agent program will update the internal agent state while
		// only moving forward.
		// START HERE - code below should be modified!

		System.out.println("x=" + state.agent_x_position);
		System.out.println("y=" + state.agent_y_position);
		System.out.println("dir=" + state.agent_direction);

		iterationCounter--;

		if (iterationCounter == 0)
			return NoOpAction.NO_OP;

		DynamicPercept p = (DynamicPercept) percept;
		Boolean bump = (Boolean) p.getAttribute("bump");
		Boolean dirt = (Boolean) p.getAttribute("dirt");
		Boolean home = (Boolean) p.getAttribute("home");
		System.out.println("percept: " + p);

		// State update based on the percept value and the last action
		state.updatePosition((DynamicPercept) percept);
		
		
		System.out.println("*****"+state.agent_direction);

		System.out.println("*****"+state.agent_direction);
		
		if (bump) {
			switch (state.agent_direction) {
			case MyAgentState.NORTH:
				state.updateWorld(state.agent_x_position,
						state.agent_y_position - 1, state.WALL);
				break;
			case MyAgentState.EAST:
				state.updateWorld(state.agent_x_position + 1,
						state.agent_y_position, state.WALL);
				break;
			case MyAgentState.SOUTH:
				state.updateWorld(state.agent_x_position,
						state.agent_y_position + 1, state.WALL);
				break;
			case MyAgentState.WEST:
				state.updateWorld(state.agent_x_position - 1,
						state.agent_y_position, state.WALL);
				break;
			}
		}
		if (dirt)
			state.updateWorld(state.agent_x_position, state.agent_y_position,
					state.DIRT);
		else
			state.updateWorld(state.agent_x_position, state.agent_y_position,
					state.CLEAR);

		state.printWorldDebug();

		// Next action selection based on the percept value
		if (dirt) {
			System.out.println("DIRT -> choosing SUCK action!");
			state.agent_last_action = state.ACTION_SUCK;
			return LIUVacuumEnvironment.ACTION_SUCK;
		} else {
			if(true){
				return state.stepTowardsPos(new Node(state.agent_x_position,state.agent_y_position+1, null));
				
			}
			if (bump) {
				state.agent_last_action = state.ACTION_TURN_LEFT;
				//state.agent_direction--;
				//if(state.agent_direction == -1) state.agent_direction = 3;
				state.updateDirectionOLD();
				return LIUVacuumEnvironment.ACTION_TURN_LEFT;
				// state.agent_last_action=state.ACTION_NONE;
				// return NoOpAction.NO_OP;
			} else {
				state.agent_last_action = state.ACTION_MOVE_FORWARD;
				return LIUVacuumEnvironment.ACTION_MOVE_FORWARD;
			}
		}
	}
}

public class MyVacuumAgent extends AbstractAgent {
	public MyVacuumAgent() {
		super(new MyAgentProgram());
	}
}
