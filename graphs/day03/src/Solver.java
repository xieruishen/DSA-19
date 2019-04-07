/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

public class Solver {

    public int minMoves = -1;
    private State solutionState;
    private boolean solved = false;

    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State implements Comparable<State>{
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves; // equal to g-cost in A*
        public int cost; // equal to f-cost in A*
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            // TODO
            cost = moves + board.manhattan();
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }

        @Override
        public  int compareTo(State s){
            return cost - s.cost;
        }

        @Override
        public int hashCode(){
            return board.hashCode();
        }
    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        // TODO: Your code here
        State current = state;
        while(current != null){
            current = state.prev;
        }
        return current;
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
        // TODO: Your code here
        solutionState = new State(initial,0,null);
        if(isSolvable()){
            AStarSolver();
        }
    }

    public void AStarSolver(){
        HashMap<State,Integer> closed = new HashMap<>();
        PriorityQueue<State> open = new PriorityQueue<>();
        open.add(solutionState);
        while(!open.isEmpty()){
            State current = open.poll();
            if(current.board.isGoal()){
                solved = true;
                solutionState = current;
                minMoves = current.moves;
                break;
            }
            Iterable<Board> neighbours = current.board.neighbors();
            for(Board successor:neighbours){
                State state = new State(successor,current.moves+1,current);
                boolean ignore = false;
                if(closed.containsKey(state)){
                    if(closed.get(state) > state.cost) {
                        closed.put(state, state.cost);
                        open.add(state);
                    }
                    else{
                        ignore = true;
                    }
                }
                for(State i: open){
                    if(state.equals(i) && state.cost >= i.cost){
                        ignore = true;
                    }
                }
                if(!ignore){
                    open.add(state);
                }
            }
            closed.put(current,current.cost);

        }

    }

    /*
     * Is the input board a solvable state
     * Research how to check this without exploring all states
     */
    public boolean isSolvable() {
        // TODO: Your code here
        return solutionState.board.solvable();
    }


    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
        // TODO: Your code here
        if(isSolvable()){
            LinkedList<Board> solutions = new LinkedList<>();
            State current = solutionState;
            while(current!=null){
                solutions.add(current.board);
                current = current.prev;
            }
            return solutions;
        }
        return null;
    }

    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.board.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space
     */
    public static void main(String[] args) {
        int[][] initState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board initial = new Board(initState);

        Solver solver = new Solver(initial);
    }


}
