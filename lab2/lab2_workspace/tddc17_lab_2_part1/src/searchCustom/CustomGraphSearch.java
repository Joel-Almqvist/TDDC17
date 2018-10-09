package searchCustom;

import java.util.ArrayList;
import java.util.HashSet;

import searchShared.NodeQueue;
import searchShared.Problem;
import searchShared.SearchObject;
import searchShared.SearchNode;

import world.GridPos;

public class CustomGraphSearch implements SearchObject {
    
    //changed from SearchNode, as we did not want to override the search function of SearchNode and we want to be able to compare new object with old ones. 
    private HashSet<String> exploredPos;
    private NodeQueue frontier;
    protected ArrayList<SearchNode> path;
    private boolean insertFront;

    /**
     * The constructor tells graph search whether it should insert nodes to
     * front or back of the frontier
     */
    public CustomGraphSearch(boolean bInsertFront) {
        insertFront = bInsertFront;
    }

    /**
     * Implements "graph search", which is the foundation of many search
     * algorithms.
     */
    public ArrayList<SearchNode> search(Problem p) {
        // The frontier is a queue of expanded SearchNodes not processed yet
        frontier = new NodeQueue();
        /// The explored set is a set of nodes that have been processed 
        exploredPos = new HashSet<String>();
        // The start state is given
        GridPos startState = (GridPos) p.getInitialState();
        // Initialize the frontier with the start state  
        frontier.addNodeToFront(new SearchNode(startState));

        // Path will be empty until we find the goal.
        path = new ArrayList<SearchNode>();
        frontier.peekAtFront().setParent(null);
        // If our goal is our current position
        if(p.getGoalState().equals(p.getInitialState())){
            path.add(new SearchNode(p.getInitialState()));
            return path;        
        }
        while (!frontier.isEmpty() && path.isEmpty()) { //If the entire world model is explored with no dust found, and no path to goal has been found, continue the search
            SearchNode current = frontier.peekAtFront();
            frontier.removeFirst();
            ArrayList<GridPos> reachable = p.getReachableStatesFrom(current.getState());
            for (GridPos newPos : reachable) {
                if (!exploredPos.contains("" + (newPos.getX() * 1000 + newPos.getY()))) { //Avoids revisiting of nodes.
                    exploredPos.add("" + (newPos.getX() * 1000 + newPos.getY()));
                    SearchNode newNode = new SearchNode(newPos);
                    newNode.setParent(current);
                    newNode.setDepth(current.getDepth() + 1);
                    if (newPos.equals(p.getGoalState())) {
                        path = newNode.getPathFromRoot();
                    }
                    if (insertFront) {
                        frontier.addNodeToFront(newNode);
                    } else {
                        frontier.addNodeToBack(newNode);
                    }
                }
            }
        }
        System.out.println("frontier empty");

        return path;
    }

    /*
	 * Functions below are just getters used externally by the program 
     */
    public ArrayList<SearchNode> getPath() {
        return path;
    }

    public ArrayList<SearchNode> getFrontierNodes() {
        return new ArrayList<SearchNode>(frontier.toList());
    }

    public ArrayList<SearchNode> getExploredNodes() {
        ArrayList<SearchNode> result = new ArrayList<SearchNode>();
        for (String pos : exploredPos) {
            int X = Integer.parseInt(pos);
            int Y = X % 1000;
            X /= 1000;
            
            result.add(new SearchNode(new GridPos(X, Y)));
        }
        return result;
    }

    public ArrayList<SearchNode> getAllExpandedNodes() {
        ArrayList<SearchNode> allNodes = new ArrayList<SearchNode>();
        allNodes.addAll(getFrontierNodes());
        allNodes.addAll(getExploredNodes());
        return allNodes;
    }
}

class NodeHolder{
    public SearchNode node;
    
}
