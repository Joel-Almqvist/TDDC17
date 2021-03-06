package tddc17;

import aima.core.environment.liuvacuum.*;
import aima.core.agent.Action;
import aima.core.agent.AgentProgram;
import aima.core.agent.Percept;
import aima.core.agent.State;
import aima.core.agent.impl.*;

import java.util.ArrayList;
import java.util.Collections;
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
	
	// CUSTOM FIELDS
	// The current path the agent is following
	public ArrayList<Node> path = new ArrayList<Node>();
	// The current node the agent is moving towards
	public Node currentDestination = null;
	// The agents home position
	public Node home = null;

	MyAgentState() {
		for (int i = 0; i < world.length; i++)
			for (int j = 0; j < world[i].length; j++)
				world[i][j] = UNKNOWN;
		world[1][1] = HOME;
		agent_last_action = ACTION_NONE;
	}

	/** Generates a path of nodes from the agent to the closest unexplored node. If
	 * no unexplored node is found a path towards the home position is returned. 
	 * 
	 * @return A list of nodes where the first element is the goal node which
	 * is the closest unexplored node. The nodes generate a path from the agent's
	 * position to the goal node. 
	 */
	public ArrayList<Node> generatePath() {
		ArrayList<Node> exploredSet = new ArrayList<Node>();
		ArrayList<Node> exploringQueue = new ArrayList<Node>();
		Node startNode = new Node(agent_x_position,agent_y_position, null);
		Node finishNode = null;
		boolean worldExplored = true;
		exploringQueue.add(startNode);
		while (!exploringQueue.isEmpty()) {
			Node exploringNode = exploringQueue.get(0);
			exploringQueue.remove(0);
			if (world[exploringNode.x][exploringNode.y] == UNKNOWN && !exploringNode.equals(startNode)) {
				finishNode = exploringNode;
				worldExplored = false;
				break;
			}
			// Neighbor below
			if (world.length > exploringNode.y + 1
					&& world[exploringNode.x][exploringNode.y + 1] != WALL) {
				Node neighbor = exploringNode.copy(0, 1);
				if (!exploredSet.contains(neighbor)) {
					exploringQueue.add(neighbor);
					exploredSet.add(neighbor);
				}
			}
			// Neighbor above
			if (exploringNode.y > 0 && world[exploringNode.x][exploringNode.y - 1] != WALL) {
				Node neighbor = exploringNode.copy(0, -1);
				if (!exploredSet.contains(neighbor)) {
					exploringQueue.add(neighbor);
					exploredSet.add(neighbor);
				}
			}
			// Neighbor to the right
			if (exploringNode.x -1 < world[0].length && 
					world[exploringNode.x + 1][exploringNode.y] != WALL) {
				Node neighbor = exploringNode.copy(1, 0);
				if (!exploredSet.contains(neighbor)) {
					exploringQueue.add(neighbor);
					exploredSet.add(neighbor);
				}

			}
			// Neighbor to the left
			if (exploringNode.x > 0 && 
					world[exploringNode.x - 1][exploringNode.y] != WALL) {
				Node neighbor = exploringNode.copy(-1, 0);
				if (!exploredSet.contains(neighbor)) {
					exploringQueue.add(neighbor);
					exploredSet.add(neighbor);
				}

			}
		}

		ArrayList<Node> path = new ArrayList<Node>();

		// No reachable unexplored nodes found
		if(worldExplored) {
			if(!startNode.equals(home)) {
				return generatePathHome();		
			}
			
			else {
				// Trying to step to our current position turns off the agent
				path.add(startNode);
				return path;
			}
		}

		// Save the found path as a list
		while (true) {
			path.add(finishNode);
			if (finishNode.parent != null) {
				finishNode = finishNode.parent;
			} else {
				break;
			}
		}
		
		// Remove our starting position from the path
		path.remove(path.size()-1);

		return path;
	}
	
	/** A BFS search similar to generatePath but instead searches from Home to the agent's current position.
	 * 
	 * @return A list of nodes the agent must traverse before reaching the home destination. The
	 * home node is included in the list.
	 */
	public ArrayList<Node> generatePathHome() {
		ArrayList<Node> exploredSet = new ArrayList<Node>();
		ArrayList<Node> exploringQueue = new ArrayList<Node>();
		Node startNode = home;
		Node finishNode = new Node(agent_x_position,agent_y_position, null);
		exploringQueue.add(startNode);
		while (!exploringQueue.isEmpty()) {
			Node exploringNode = exploringQueue.get(0);
			exploringQueue.remove(0);
			if (exploringNode.equals(finishNode)) {
				finishNode = exploringNode;
				break;
			}
			// Neighbor below
			if (world.length > exploringNode.y + 1
					&& world[exploringNode.x][exploringNode.y + 1] != WALL) {
				Node neighbor = exploringNode.copy(0, 1);
				if (!exploredSet.contains(neighbor)) {
					exploringQueue.add(neighbor);
					exploredSet.add(neighbor);
				}
			}
			// Neighbor above
			if (exploringNode.y > 0 && world[exploringNode.x][exploringNode.y - 1] != WALL) {
				Node neighbor = exploringNode.copy(0, -1);
				if (!exploredSet.contains(neighbor)) {
					exploringQueue.add(neighbor);
					exploredSet.add(neighbor);
				}
			}
			// Neighbor to the right
			if (exploringNode.x -1 < world[0].length && 
					world[exploringNode.x + 1][exploringNode.y] != WALL) {
				Node neighbor = exploringNode.copy(1, 0);
				if (!exploredSet.contains(neighbor)) {
					exploringQueue.add(neighbor);
					exploredSet.add(neighbor);
				}

			}
			// Neighbor to the left
			if (exploringNode.x > 0 && 
					world[exploringNode.x - 1][exploringNode.y] != WALL) {
				Node neighbor = exploringNode.copy(-1, 0);
				if (!exploredSet.contains(neighbor)) {
					exploringQueue.add(neighbor);
					exploredSet.add(neighbor);
				}

			}
		}

		ArrayList<Node> path = new ArrayList<Node>();
		// Add the found path in a list
		while (true) {
			path.add(finishNode);
			if (finishNode.parent != null) {
				finishNode = finishNode.parent;
			} else {
				break;
			}
		}
		
		// Remove our starting position from the path since we don't want the agent to
		// move to it
		path.remove(0);
		// Reverse the path so that its order is the same as the one created by generatePath()
		Collections.reverse(path);
		return path;
	}
	
	
	/** Returns an action which helps the agent reach the goal and updates the agent's last_action 
	 * and direction.
	 * 
	 * If the goal node is not a direct neighbor from the agent's current position the 
	 * function returns NO_OP
	 */
	public Action stepTowardsPos(Node goal){
		Node agentPos = new Node(agent_x_position, agent_y_position, null);
		if(goal.distanceTo(agentPos) > 1){
			return NoOpAction.NO_OP;
		}
		
		// The goal is below us
		if(agentPos.y < goal.y){
			switch(agent_direction) {
			case MyAgentState.NORTH:
				agent_last_action = ACTION_TURN_RIGHT;
				updateDirection();
				return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
			case MyAgentState.EAST:
				agent_last_action = ACTION_TURN_RIGHT;
				updateDirection();
				return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
			case MyAgentState.WEST:
				agent_last_action = ACTION_TURN_LEFT;
				updateDirection();
				return LIUVacuumEnvironment.ACTION_TURN_LEFT;
			default:
				agent_last_action = ACTION_MOVE_FORWARD;
				updateDirection();
				return LIUVacuumEnvironment.ACTION_MOVE_FORWARD;
			}
		}
		// The goal is above us
		else if(agentPos.y > goal.y){
			switch(agent_direction) {
			case MyAgentState.SOUTH:
				agent_last_action = ACTION_TURN_RIGHT;
				updateDirection();
				return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
			case MyAgentState.WEST:
				agent_last_action = ACTION_TURN_RIGHT;
				updateDirection();
				return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
			case MyAgentState.EAST:
				agent_last_action = ACTION_TURN_LEFT;
				updateDirection();
				return LIUVacuumEnvironment.ACTION_TURN_LEFT;
			default:
				agent_last_action = ACTION_MOVE_FORWARD;
				return LIUVacuumEnvironment.ACTION_MOVE_FORWARD;
			}
		}
		// The goal is to our left
		if(agentPos.x > goal.x){
			switch(agent_direction) {
			case MyAgentState.NORTH:
				agent_last_action = ACTION_TURN_LEFT;
				updateDirection();
				return LIUVacuumEnvironment.ACTION_TURN_LEFT;
			case MyAgentState.EAST:
				agent_last_action = ACTION_TURN_RIGHT;
				updateDirection();
				return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
			case MyAgentState.SOUTH:
				agent_last_action = ACTION_TURN_RIGHT;
				updateDirection();
				return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
			default:
				agent_last_action = ACTION_MOVE_FORWARD;
				return LIUVacuumEnvironment.ACTION_MOVE_FORWARD;
			}
		}
		
		// The goal is to our right
		if(agentPos.x < goal.x){
			switch(agent_direction) {
			case MyAgentState.NORTH:
				agent_last_action = ACTION_TURN_RIGHT;
				updateDirection();
				return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
			case MyAgentState.SOUTH:
				agent_last_action = ACTION_TURN_LEFT;
				updateDirection();
				return LIUVacuumEnvironment.ACTION_TURN_LEFT;
			case MyAgentState.WEST:
				agent_last_action = ACTION_TURN_RIGHT;
				updateDirection();
				return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
			default:
				agent_last_action = ACTION_MOVE_FORWARD;
				return LIUVacuumEnvironment.ACTION_MOVE_FORWARD;
			}
		}
		return NoOpAction.NO_OP;
	}

	public void updateDirection() {
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
            if (state.agent_direction < 0) {
                state.agent_direction += 4;
            }
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

        //iterationCounter--;
        if (iterationCounter == 0) {
            return NoOpAction.NO_OP;
        }

        DynamicPercept p = (DynamicPercept) percept;
        Boolean bump = (Boolean) p.getAttribute("bump");
        Boolean dirt = (Boolean) p.getAttribute("dirt");
        Boolean home = (Boolean) p.getAttribute("home");
        System.out.println("percept: " + p);

        // State update based on the percept value and the last action
        state.updatePosition((DynamicPercept) percept);

        System.out.println("*****" + state.agent_direction);

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
        if (dirt) {
            state.updateWorld(state.agent_x_position, state.agent_y_position,
                    state.DIRT);
        } else if (!home) {
            state.updateWorld(state.agent_x_position, state.agent_y_position,
                    state.CLEAR);
        }

        state.printWorldDebug();

        if (home) {
            state.home = new Node(state.agent_x_position, state.agent_y_position, null);
            state.updateWorld(state.agent_x_position, state.agent_y_position,
                    state.HOME);
        }

        // Next action selection based on the percept value
        if (dirt) {
            System.out.println("DIRT -> choosing SUCK action!");
            state.agent_last_action = state.ACTION_SUCK;
            return LIUVacuumEnvironment.ACTION_SUCK;
        } else {
            // Init
            if (state.currentDestination == null) {
                state.path = state.generatePath();
                state.currentDestination = state.path.get(state.path.size() - 1);
                state.path.remove(state.path.size() - 1);
                return state.stepTowardsPos(state.currentDestination);
            }

            // Have we reached our destination?
            if (state.currentDestination.x == state.agent_x_position
                    && state.currentDestination.y == state.agent_y_position) {

                // We have reached our destination and there is no path
                if (state.path.isEmpty()) {
                    state.path = state.generatePath();
                    state.currentDestination = state.path.get(state.path.size() - 1);
                    state.path.remove(state.path.size() - 1);
                } // Continue towards the next Node in the path
                else {
                    state.currentDestination = state.path.get(state.path.size() - 1);
                    state.path.remove(state.path.size() - 1);
                }
            }

            // If we realize that our goal is a wall, create a new path
            if (state.world[state.currentDestination.x][state.currentDestination.y] == state.WALL) {
                state.path = state.generatePath();
                state.currentDestination = state.path.get(state.path.size() - 1);
                state.path.remove(state.path.size() - 1);
            }
            // Continue towards our goal
            return state.stepTowardsPos(state.currentDestination);
        }
    }
}

public class MyVacuumAgent extends AbstractAgent {

    public MyVacuumAgent() {
        super(new MyAgentProgram());
    }
}
