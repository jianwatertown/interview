package tree;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * {}
 * {a,#,b,c}
 *
 *
 */
public class BinaryTreeSerialization {
    public String serialize(TreeNode root) {

        if(root==null) {return "{}";}

        StringBuffer sb = new StringBuffer();
        Queue<TreeNode> q = new LinkedList<>();
        sb.append("{");
        sb.append(root.val);

        q.add(root);
        // process before putting into the queue
        while(!q.isEmpty()){
            TreeNode node = q.poll();

            // left
            if(node.left==null){
                sb.append(",#");
            }
            else{
                sb.append(",");
                sb.append(node.left.val);
                q.add(node.left);
            }

            // right
            if(node.right==null){
                sb.append(",#");
            }
            else{
                sb.append(",");
                sb.append(node.right.val);
                q.add(node.right);
            }
        }
        sb.append("}");
        return sb.toString();
    }



    public TreeNode deserialize(String data) {

        if(data.equals("{}")) { return null;}

        Map<Integer,TreeNode> indexToNodeMap = new HashMap<>();
        String[] strNodes = data.substring(1,data.length()-1).split(",",0);

        TreeNode rootNode = new TreeNode(Integer.parseInt(strNodes[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(rootNode);

        for(int childrenIndex = 1; childrenIndex<strNodes.length;childrenIndex+=2){
            TreeNode currentRoot = q.poll();
            TreeNode left = createIfNotExistInMap(childrenIndex,strNodes,indexToNodeMap);
            currentRoot.left = left;
            if(left!=null) {q.add(left);}
            TreeNode right = createIfNotExistInMap(childrenIndex+1,strNodes,indexToNodeMap);
            currentRoot.right = right;
            if(right!=null) {q.add(right);}
        }
        return rootNode;
    }

    public TreeNode createIfNotExistInMap(int index, String[] strNodes, Map<Integer,TreeNode> map){
        String data = strNodes[index];
        if(data.equals("#")) return null;
        if(map.containsKey(index)) {return map.get(index);}
        TreeNode nNode = new TreeNode(Integer.parseInt(data));
        map.put(index,nNode);
        return nNode;
    }

    public static void main(String[] args){
        BinaryTreeSerialization tester = new BinaryTreeSerialization();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        one.right = two;
    //    System.out.println(tester.serialize(one));
        TreeNode oneAfter = tester.deserialize("{1,2,3,#,#,4,5}");
        System.out.println(tester.serialize(oneAfter));
    }
}
