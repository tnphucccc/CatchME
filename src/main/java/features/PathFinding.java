package features;

import variables.Constant;

import java.util.ArrayList;

public class PathFinding {
    public ArrayList<Node> pathList = new ArrayList<>();
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = true;
    int step = 0;

    public PathFinding() {
        instantiateNodes();
    }

    public void instantiateNodes() {
        // create node
        node = new Node[Constant.MAX_SCREEN_COL][Constant.MAX_SCREEN_ROW];

        for (int col = 0; col < Constant.MAX_SCREEN_COL; col++) {
            for (int row = 0; row < Constant.MAX_SCREEN_ROW; row++) {
                node[col][row] = new Node(col, row);
            }
        }
    }

}
