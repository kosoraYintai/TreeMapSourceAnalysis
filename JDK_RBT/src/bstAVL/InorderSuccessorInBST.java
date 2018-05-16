package bstAVL;

import warmUp.TreeNode;

public class InorderSuccessorInBST {
	  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
	        if(p==null){
	        	return null;
	        }
	        if(getLastEntry(root)==p){
	        	return null;
	        }
	        if(p.right!=null){
	        	return getFirstEntry(p.right);
	        }
	        TreeNode parent=root;
	        TreeNode temp=root;
	        while(parent!=null){
	        	if(parent==p){
	        		break;
	        	}else if(p.val<parent.val){
	        		temp=parent;
	        		parent=parent.left;
	        	}else{
	        		parent=parent.right;
	        	}
	        }
	        return temp;
	    }
	  private TreeNode getLastEntry(TreeNode p){
		  while(p.right!=null){
			  p=p.right;
		  }
		  return p;
	  }
	  private TreeNode getFirstEntry(TreeNode p){
		  while(p.left!=null){
			  p=p.left;
		  }
		  return p;
	  }
}
