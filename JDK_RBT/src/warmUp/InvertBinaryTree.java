package warmUp;

import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {
	 public TreeNode invertTree(TreeNode root) {
	        if(root!=null){
	        	TreeNode t=root.left;
	        	root.left=root.right;
	        	root.right=t;
	        	invertTree(root.left);
	        	invertTree(root.right);
	        	return root;
	        }else{
	        	return null;
	        }
	    }
	 public TreeNode invertTree_bfs(TreeNode root) {
		 if(root==null){
			 return null;
		 }else{
			 Queue<TreeNode> queue=new LinkedList<TreeNode>();
			 queue.offer(root);
			 while(!queue.isEmpty()){
				 TreeNode p=queue.poll();
				 TreeNode t=p.left;
		         p.left=p.right;
		         p.right=t;
		         if(p.left!=null){
		        	 queue.offer(p.left);
		         }
		         if(p.right!=null){
		        	 queue.offer(p.right);
		         }
		         
			 }
			 return root;
		 }
	 }
}
