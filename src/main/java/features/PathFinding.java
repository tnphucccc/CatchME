package features;

import core.TileManager;
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

    public void resetNodes() {
        //reset node
        for (int col = 0; col < Constant.MAX_SCREEN_COL; col++) {
            for (int row = 0; row < Constant.MAX_SCREEN_ROW; row++) {
                node[col][row].open = false;
                node[col][row].checked = false;
                node[col][row].solid = false;
            }
        }
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void getCost(Node node) {
        //G cost
        int xDis = Math.abs(node.col - startNode.col);
        int yDis = Math.abs(node.row - startNode.row);
        node.gCost = xDis + yDis;
        //H cost
        xDis = Math.abs(node.col - goalNode.col);
        yDis = Math.abs(node.row - goalNode.row);
        node.hCost = xDis + yDis;
        //F cost
        node.fCost = node.gCost + node.hCost;
    }

    public void setNodes(int startCol, int startRow, int goalCol, int goalRow) {
        resetNodes();
        //set the start and goal node
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        for (int col = 0; col < Constant.MAX_SCREEN_COL; col++) {
            for (int row = 0; row < Constant.MAX_SCREEN_ROW; row++) {
                int tileNum = TileManager.map[col][row];
                if (TileManager.tiles[tileNum].collision) {
                    node[col][row].solid = true;
                }
                getCost(node[col][row]);
            }
        }
    }


}
