package tddc17;

import aima.core.environment.liuvacuum.*;
import aima.core.agent.Action;
import aima.core.agent.AgentProgram;
import aima.core.agent.Percept;
import aima.core.agent.impl.*;
import java.util.ArrayList;

import java.util.Random;

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
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                world[i][j] = UNKNOWN;
            }
        }
        world[1][1] = HOME;
        agent_last_action = ACTION_NONE;
    }
    // Based on the last action and the received percept updates the x & y agent position

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
                if (world[j][i] == UNKNOWN) {
                    System.out.print(" ? ");
                }
                if (world[j][i] == WALL) {
                    System.out.print(" # ");
                }
                if (world[j][i] == CLEAR) {
                    System.out.print(" . ");
                }
                if (world[j][i] == DIRT) {
                    System.out.print(" D ");
                }
                if (world[j][i] == HOME) {
                    System.out.print(" H ");
                }
            }
            System.out.println("");
        }
    }
}

class MyAgentProgram implements AgentProgram {

    private int initnialRandomActions = 10;
    private Random random_generator = new Random();

    // Here you can define your variables!
    public int iterationCounter = 10;
    public MyAgentState state = new MyAgentState();
    // TODO add my variables
    public int room[][] = new int[39][39];
    public int x = 19, y = 19;
    boolean uninitialized = true;

    // moves the Agent to a random start position
    // uses percepts to update the Agent position - only the position, other percepts are ignored
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
            System.out.println("Processing percepts after the last execution of moveToRandomStartPosition()");
            state.agent_last_action = state.ACTION_SUCK;
            return LIUVacuumEnvironment.ACTION_SUCK;
        }

        // START HERE - code below should be modified!
        System.out.println("x=" + state.agent_x_position);
        System.out.println("y=" + state.agent_y_position);
        System.out.println("dir=" + state.agent_direction);
        //TODO: add code here

        DynamicPercept p = (DynamicPercept) percept;
        Boolean bump = (Boolean) p.getAttribute("bump");
        Boolean dirt = (Boolean) p.getAttribute("dirt");
        Boolean home = (Boolean) p.getAttribute("home");
        System.out.println("percept: " + p);

        if (uninitialized) {
            uninitialized = false;
            for (int i = 0; i < 39; i++) {
                for (int j = 0; j < 39; j++) {
                    room[i][j] = 0;
                }

            }
        }

        // State update based on the percept value and the last action
        state.updatePosition((DynamicPercept) percept);
        if (bump) {
            switch (state.agent_direction) {
                case MyAgentState.NORTH:
                    state.updateWorld(state.agent_x_position, state.agent_y_position - 1, state.WALL);
                    room[x][y - 1] = 2;
                    break;
                case MyAgentState.EAST:
                    state.updateWorld(state.agent_x_position + 1, state.agent_y_position, state.WALL);
                    room[x + 1][y] = 2;
                    break;
                case MyAgentState.SOUTH:
                    state.updateWorld(state.agent_x_position, state.agent_y_position + 1, state.WALL);
                    room[x][y + 1] = 2;
                    break;
                case MyAgentState.WEST:
                    state.updateWorld(state.agent_x_position - 1, state.agent_y_position, state.WALL);
                    room[x - 1][y] = 2;
                    break;
            }
        }
        if (dirt) {
            state.updateWorld(state.agent_x_position, state.agent_y_position, state.DIRT);
        } else {
            state.updateWorld(state.agent_x_position, state.agent_y_position, state.CLEAR);
        }

        state.printWorldDebug();

        // Next action selection based on the percept value
        if (dirt) {
            System.out.println("DIRT -> choosing SUCK action!");
            state.agent_last_action = state.ACTION_SUCK;
            return LIUVacuumEnvironment.ACTION_SUCK;
        } else {

            if (!bump && state.agent_last_action == state.ACTION_MOVE_FORWARD) {
                switch (state.agent_direction) {
                    case MyAgentState.NORTH:
                        y--;
                        break;
                    case MyAgentState.EAST:
                        x++;
                        break;
                    case MyAgentState.SOUTH:
                        y++;
                        break;
                    case MyAgentState.WEST:
                        x--;
                        break;
                }
            }

            int goalDir = bfs(room, x, y);

            System.out.println("BFS suggests dir " + goalDir + ", state is " + state.agent_direction);

            if (goalDir == -1) {
                state.agent_last_action = state.ACTION_NONE;
                return NoOpAction.NO_OP;
            }
            if (state.agent_direction == goalDir) {
                state.agent_last_action = state.ACTION_MOVE_FORWARD;
                return LIUVacuumEnvironment.ACTION_MOVE_FORWARD;
            } else if ((state.agent_direction + 1) % 4 == goalDir) {
                state.agent_last_action = state.ACTION_TURN_RIGHT;
                state.agent_direction = goalDir;
                return LIUVacuumEnvironment.ACTION_TURN_RIGHT;
            } else {
                state.agent_last_action = state.ACTION_TURN_LEFT;
                state.agent_direction = (state.agent_direction + 4 - 1) % 4;
                return LIUVacuumEnvironment.ACTION_TURN_LEFT;
            }

        }
    }

    int bfs(int[][] room, int x, int y) {
        //room states: -1 = empty, 0 = unexplored, 1 = visited, 2 = wall, 3 = home(unvisited), 4 = home(visited)

        for (int i = 0; i < 39; i++) { //clean up since last bfs
            for (int j = 0; j < 39; j++) {
                switch (room[i][j]) {
                    case 1:
                        room[i][j] = -1;
                        break;
                    case 2:
                        room[i][j] = 3;
                        break;
                    default:
                }
            }
        }
        ArrayList<SquarePos> searchList = new ArrayList();
        searchList.add(new SquarePos(x, y, -1));
        room[x][y] = 1;
        int dirToHome = -1;
        ArrayList<SquarePos> nextList = new ArrayList();
        System.out.println("current internal position: " + x + ", " + y);
        while (!searchList.isEmpty()) {
            for (SquarePos squarePos : searchList) {
                if (squarePos.x - 1 >= 0) { //west
                    System.out.println("checking west");
                    if (room[x - 1][y] == 0) {
                        return squarePos.dir == -1 ? MyAgentState.WEST : squarePos.dir;
                    }
                    if (room[x - 1][y] == 3) {
                        dirToHome = squarePos.dir == -1 ? MyAgentState.WEST : squarePos.dir;
                    }
                    if ((room[x - 1][y] == 2) || (room[x - 1][y] == 1) || (room[x - 1][y] == 4)) {
                        //System.out.println("dismissing  x = " + (x - 1) + ", y = " + y + " as it is " + room[x - 1][y]);
                    } else {
                        nextList.add(new SquarePos(x - 1, y, squarePos.dir == -1 ? MyAgentState.WEST : squarePos.dir));
                        if (room[x - 1][y] == 3) {
                            room[x - 1][y] = 4;
                        }
                        if (room[x - 1][y] == -1) {
                            room[x - 1][y] = 1;
                        }
                    }
                }

                if (squarePos.x + 1 < 39) { //east
                    System.out.println("checking east");
                    if (room[x + 1][y] == 0) {
                        return squarePos.dir == -1 ? MyAgentState.EAST : squarePos.dir;
                    }
                    if (room[x + 1][y] == 3) {
                        dirToHome = squarePos.dir == -1 ? MyAgentState.EAST : squarePos.dir;
                    }
                    if ((room[x + 1][y] == 2) || (room[x + 1][y] == 1) || (room[x + 1][y] == 4)) {
                        //System.out.println("dismissing  x = " + (x + 1) + ", y = " + y + " as it is " + room[x + 1][y]);
                    } else {
                        nextList.add(new SquarePos(x + 1, y, squarePos.dir == -1 ? MyAgentState.EAST : squarePos.dir));
                        if (room[x - 1][y] == 3) {
                            room[x - 1][y] = 4;
                        }
                        if (room[x - 1][y] == -1) {
                            room[x - 1][y] = 1;
                        }
                    }
                }

                if (squarePos.y - 1 >= 0) { //north
                    System.out.println("checking north");
                    if (room[x][y - 1] == 0) {
                        return squarePos.dir == -1 ? MyAgentState.NORTH : squarePos.dir;
                    }
                    if (room[x][y - 1] == 3) {
                        dirToHome = squarePos.dir == -1 ? MyAgentState.NORTH : squarePos.dir;
                    }
                    if ((room[x][y - 1] == 2) || (room[x][y - 1] == 1) || (room[x - 1][y] == 4)) {
                        //System.out.println("dismissing  x = " + x + ", y = " + (y - 1) + " as it is " + room[x][y - 1]);
                    } else {
                        nextList.add(new SquarePos(x, y - 1, squarePos.dir == -1 ? MyAgentState.NORTH : squarePos.dir));
                        if (room[x - 1][y] == 3) {
                            room[x - 1][y] = 4;
                        }
                        if (room[x - 1][y] == -1) {
                            room[x - 1][y] = 1;
                        }
                    }
                }

                if (squarePos.y + 1 < 39) { //south
                    System.out.println("checking south");
                    if (room[x][y + 1] == 0) {
                        return squarePos.dir == -1 ? MyAgentState.SOUTH : squarePos.dir;
                    }
                    if (room[x][y + 1] == 3) {
                        dirToHome = squarePos.dir == -1 ? MyAgentState.SOUTH : squarePos.dir;
                    }
                    if ((room[x][y + 1] == 2) || (room[x][y + 1] == 1) || (room[x + 1][y] == 4)) {
                        //System.out.println("dismissing  x = " + x + ", y = " + (y + 1) + " as it is " + room[x][y + 1]);
                    } else {
                        nextList.add(new SquarePos(x, y + 1, squarePos.dir == -1 ? MyAgentState.SOUTH : squarePos.dir));
                        if (room[x - 1][y] == 3) {
                            room[x - 1][y] = 4;
                        }
                        if (room[x - 1][y] == -1) {
                            room[x - 1][y] = 1;
                        }
                    }
                }

                //all directions explored
                System.out.println("all direxctions explored");
            }
            searchList = nextList; //update with next layer of search
            nextList = new ArrayList();
        }

        System.out.println("returning dirToHome");
        return dirToHome; //if this is reached, all positions are explored, and this yields a path home, or -1 if allready home.

    }
}

public class MyVacuumAgent extends AbstractAgent {

    public MyVacuumAgent() {
        super(new MyAgentProgram());
    }
}

class SquarePos {

    int x, y, dir; //dir 0 up, 1 right, 2 down, 3 left

    public SquarePos(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

}
