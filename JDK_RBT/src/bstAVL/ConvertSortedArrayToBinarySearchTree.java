package bstAVL;

import java.util.HashMap;
import java.util.LinkedList;

import warmUp.TreeNode;
class LeetCodeAVL {

	private int size;
	public TreeNode root;
	private LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0 ? true : false;
	}

	public void put(int key) {
		if (root == null) {
			root = new TreeNode(key);
			stack.push(root);
			size++;
		} else {
			TreeNode p = root;
			while (p != null) {
				stack.push(p);
				int compareResult = key - p.val;
				if (compareResult == 0) {
					break;
				} else if (compareResult < 0) {
					if (p.left == null) {
						p.left = new TreeNode(key);
						size++;
						stack.push(p.left);
						break;
					} else {
						p = p.left;
					}
				} else {
					if (p.right == null) {
						p.right = new TreeNode(key);
						size++;
						stack.push(p.right);
						break;
					} else {
						p = p.right;
					}
				}
			}
		}
		fixAfterInsertion(key);
	}
	private HashMap<TreeNode, Integer> heightMap = new HashMap<TreeNode, Integer>();
	public int getHeight(TreeNode p) {
		return heightMap.containsKey(p) ? heightMap.get(p) : 0;
	}
	private TreeNode rotateRight(TreeNode p) {
		TreeNode left = p.left;
		p.left = left.right;
		left.right = p;
		heightMap.put(p, Math.max(getHeight(p.left), getHeight(p.right)) + 1);
		heightMap.put(left,
				Math.max(getHeight(left.left), heightMap.get(p)) + 1);
		return left;
	}
	private TreeNode rotateLeft(TreeNode p) {
		TreeNode right = p.right;
		p.right = right.left;
		right.left = p;
		heightMap.put(p, Math.max(getHeight(p.left), getHeight(p.right)) + 1);
		heightMap
				.put(right, Math.max(getHeight(p), getHeight(right.right)) + 1);
		return right;
	}
	private TreeNode firstLeftThenRight(TreeNode p) {
		p.left = rotateLeft(p.left);
		p = rotateRight(p);
		return p;
	}
	private TreeNode firstRightThenLeft(TreeNode p) {
		p.right = rotateRight(p.right);
		p = rotateLeft(p);
		return p;
	}
	private void fixAfterInsertion(int key) {
		TreeNode p = root;
		while (!stack.isEmpty()) {
			p = stack.pop();
			int newHeight = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
			if (heightMap.containsKey(p) && getHeight(p) > 1
					&& newHeight == getHeight(p)) {
				stack.clear();
				return;
			}
			heightMap.put(p, newHeight);
			int d = getHeight(p.left) - getHeight(p.right);
			if (Math.abs(d) <= 1) {
				continue;
			} else {
				if (d == 2) {
					if (key - p.left.val < 0) {
						p = rotateRight(p);
					} else {
						p = firstLeftThenRight(p);
					}
				} else {
					if (key - p.right.val > 0) {
						p = rotateLeft(p);
					} else {
						p = firstRightThenLeft(p);
					}
				}
				if (!stack.isEmpty()) {
					if (key - stack.peek().val < 0) {
						stack.peek().left = p;
					} else {
						stack.peek().right = p;
					}
				}
			}
		}
		root = p;
	}
}
public class ConvertSortedArrayToBinarySearchTree {
	public TreeNode sortedArrayToBST_UsingAVL(int[] nums) {
		if (nums == null || nums.length == 0) {
			return null;
		}
		LeetCodeAVL avl = new LeetCodeAVL();
		for (int key : nums) {
			avl.put(key);
		}
		return avl.root;
	}
	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums == null || nums.length == 0) {
			return null;
		}
		return buildFromSorted(0, nums.length-1, nums);
	}
	private  TreeNode buildFromSorted(int lo, int hi,int[] nums) {
		if (hi < lo)
			return null;

		int mid = (lo + hi) / 2;

		TreeNode left = null;
		if (lo < mid)
			left = buildFromSorted(lo, mid - 1, nums);
		TreeNode middle = new TreeNode(nums[mid]);
		if (left != null) {
			middle.left = left;
		}
		if (mid < hi) {
			TreeNode right = buildFromSorted(mid + 1, hi,nums);
			middle.right = right;
		}
		return middle;
	}
}
