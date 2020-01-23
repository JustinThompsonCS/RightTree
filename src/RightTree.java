import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class RightTree {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(new File("righttree.dat"));
        int cases = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < cases; i++) {

            String line = scanner.nextLine();
            //System.out.println("input: " + line );
            Node root = new Node(1);
            Scanner lineReader = new Scanner(line);
            lineReader.useDelimiter("");
            lineReader.nextInt();
            LinkedList<Node> queue = new LinkedList<>();
            Node currentNode = root;
            queue.addLast(currentNode);
            while(lineReader.hasNextInt()){
                if(queue.peekFirst() == null){
                    queue.removeFirst();
                    lineReader.nextInt();
                    lineReader.nextInt();
                    queue.addLast(null);
                    queue.addLast(null);
                }
                else {
                    int currentInt = lineReader.nextInt();
                    currentNode = queue.removeFirst();
                    if(currentInt == 1){
                        currentNode.left = new Node(1);

                    }
                    else{
                        currentNode.left = null;
                    }
                    queue.addLast(currentNode.left);
                    if(lineReader.hasNextInt()){
                        currentInt = lineReader.nextInt();
                        if(currentInt == 1){
                            currentNode.right = new Node(1);
                        }
                        else{
                            currentNode.right = null;
                        }
                        queue.addLast(currentNode.right);
                    }
                    else{
                        currentNode.right = null;
                    }
                }

            }

            boolean rightTree = true;
            LinkedList<Node> nodeQueue = new LinkedList<>();
            nodeQueue.addLast(root);
            while (!nodeQueue.isEmpty()){
                currentNode = nodeQueue.removeFirst();
                if(currentNode.hasLeft()){
                    nodeQueue.addLast(currentNode.left);
                }
                if(currentNode.hasRight()){
                    nodeQueue.addLast(currentNode.right);
                }

                if(currentNode.hasLeft() || currentNode.hasRight()){
                    LinkedList<Node> nodeQueueTwo = new LinkedList<>();
                    LinkedList<Integer> values = new LinkedList<>();
                    values.addLast(0);
                    nodeQueueTwo.addLast(currentNode);
                    //System.out.println(currentNode);
                    int left = 0;
                    int right = 0;
                    while(!nodeQueueTwo.isEmpty()){
                        Node currentNodeTwo = nodeQueueTwo.removeFirst();
                        int currentValue = values.removeFirst();
                        if(currentValue == 0){
                            if(currentNodeTwo.hasRight()){
                                right++;
                                values.addLast(1);
                                nodeQueueTwo.addLast(currentNodeTwo.right);
                            }
                            if(currentNodeTwo.hasLeft()){
                                left++;
                                values.addLast(-1);
                                nodeQueueTwo.addLast(currentNodeTwo.left);
                            }
                        }
                        else{
                            if(currentValue == 1){
                                right++;
                                if(currentNodeTwo.hasRight()){
                                    values.addLast(1);
                                    nodeQueueTwo.addLast(currentNodeTwo.right);
                                }
                                if (currentNodeTwo.hasLeft()){
                                    values.addLast(1);
                                    nodeQueueTwo.addLast(currentNodeTwo.left);
                                }
                            }
                            if(currentValue == -1){
                                left++;
                                if (currentNodeTwo.hasLeft()){
                                    values.addLast(-1);
                                    nodeQueueTwo.addLast(currentNodeTwo.left);
                                }
                                if(currentNodeTwo.hasRight()){
                                    values.addLast(-1);
                                    nodeQueueTwo.addLast(currentNodeTwo.right);
                                }

                            }
                        }

                    }
                    rightTree = rightTree && right >= left;
                }


            }

            if(rightTree){
                System.out.println("Tree " + (i+1) + " is a right-tree.");
            }else {
                System.out.println("Tree " + (i+1) + " is not a right-tree.");
            }

        }

    }





    private static class Node
    {
        private Integer value;
        public Node left;
        public Node right;

        public Node(Integer value){
            this.value = value;
            left = null;
            right = null;
        }

        public boolean hasLeft(){
            return left != null;
        }
        public boolean hasRight(){
            return right != null;
        }

        @Override
        public String toString() {
            if (this == null) {
                return "";
            }
            String s = "";
            ArrayList<ArrayList<Node>> lists = new ArrayList<>();
            lists.add(new ArrayList<Node>());
            lists.get(0).add(this);
            int index = 0;
            boolean breakout = false;
            while (!breakout) {

                ArrayList<Node> current = lists.get(index);
                ArrayList<Node> nodeList = new ArrayList<>();
                breakout = true;
                for (int i = 0; i < current.size(); i++){
                    Node currentNode = current.get(i);
                    if(currentNode == null){
                        s += "n ";
                        continue;
                    }

                    if(currentNode.left != null || currentNode.right != null){
                        breakout = false;
                    }

                    nodeList.add(currentNode.left);
                    nodeList.add(currentNode.right);
                    s += currentNode.value + " ";
                }
                index ++;
                s += "\n";
                lists.add(nodeList);
            }
            return s;
        }


    }

}