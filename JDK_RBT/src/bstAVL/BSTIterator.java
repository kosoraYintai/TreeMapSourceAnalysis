package bstAVL;

import java.util.ArrayList;
import java.util.Iterator;

import warmUp.TreeNode;

public class BSTIterator {
private Iterator<Integer> itr;
public BSTIterator(TreeNode root) {
    ArrayList<Integer> list=new ArrayList<Integer>();
    inOrder(root, list);
    itr=list.iterator();
}
private void inOrder(TreeNode p,ArrayList<Integer> list){
	if(p!=null){
		inOrder(p.left, list);
		list.add(p.val);
		inOrder(p.right, list);
	}
}
/** @return whether we have a next smallest number */
public boolean hasNext() {
    return itr.hasNext();
}

/** @return the next smallest number */
public int next() {
    return itr.next();
}
}
