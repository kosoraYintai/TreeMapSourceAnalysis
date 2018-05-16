package bstAVL;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;

public class AVLMap<K, V> implements Iterable<AVLEntry<K, V>>{
	private int size;
	private AVLEntry<K, V> root;
	private Comparator<K> comp;
	private LinkedList<AVLEntry<K, V>> stack=new LinkedList<AVLEntry<K,V>>();
	@SuppressWarnings("unchecked")
	private int compare(K a, K b) {
		if (comp != null) {
			return comp.compare(a, b);
		} else {
			Comparable<K> c = (Comparable<K>) a;
			return c.compareTo(b);
		}
	}
	public AVLMap(Comparator<K> comp) {
		super();
		this.comp = comp;
	}
	public AVLMap() {
		super();
	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0 ? true : false;
	}
	
	public V put(K key,V value){
		if(root==null){
			root=new AVLEntry<K,V>(key, value);
			stack.push(root);
			size++;
		}else{
			AVLEntry<K,V> p=root;
			while(p!=null){
				stack.push(p);
				int compareResult=compare(key, p.key);
				if(compareResult==0){
					p.setValue(value);
					break;
				}else if(compareResult<0){
					if(p.left==null){
						p.left=new AVLEntry<K,V>(key, value);
						size++;
						stack.push(p.left);
						break;
					}else{
						p=p.left;
					}
				}else{
					if(p.right==null){
						p.right=new AVLEntry<K,V>(key, value);
						size++;
						stack.push(p.right);
						break;
					}else{
						p=p.right;
					}
				}
			}
		}
		fixAfterInsertion(key);
		return value;
	}
	@Override
	public Iterator<AVLEntry<K, V>> iterator() {
		return new AVLIterator<K, V>(root);
	}
	
	private AVLEntry<K, V> getEntry(K key){
		AVLEntry<K, V> p=root;
		while(p!=null){
			int compareResult=compare(key, p.key);
			if(compareResult==0){
				return p;
			}else if(compareResult<0){
				p=p.left;
			}else{
				p=p.right;
			}
		}
		return null;
	}
	public boolean containsKey(K key){
		AVLEntry<K, V> p=getEntry(key);
		return p!=null;
	}
	public V get(K key){
		AVLEntry<K, V> p=getEntry(key);
		return p!=null?p.getValue():null;
	}
	public boolean containsValue(V value){
		Iterator<AVLEntry<K, V>> itr=this.iterator();
		while(itr.hasNext()){
			if(itr.next().getValue().equals(value)){
				return true;
			}
		}
		return false;
	}
	public AVLEntry<K, V> getFirstEntry(AVLEntry<K, V> p){
		if(p==null){
			return null;
		}
		while(p.left!=null){
			p=p.left;
		}
		return p;
	}
	public AVLEntry<K, V> getLastEntry(AVLEntry<K, V> p){
		if(p==null){
			return null;
		}
		while(p.right!=null){
			p=p.right;
		}
		return p;
	}
	private AVLEntry<K, V> deleteEntry(AVLEntry<K, V> p,K key){
		if(p==null){
			return null;
		}else{
			int compareResult=compare(key, p.key);
			if(compareResult==0){
				if(p.left==null&&p.right==null){
					p=null;
				}else if(p.left!=null&&p.right==null){
					p=p.left;
				}else if(p.left==null&&p.right!=null){
					p=p.right;
				}else{
					if((size&1)==0){
						AVLEntry<K, V> rightMin=getFirstEntry(p.right);
						p.key=rightMin.key;
						p.value=rightMin.value;
						AVLEntry<K, V> newRight=deleteEntry(p.right, p.key);
						p.right=newRight;
					}else{
						AVLEntry<K, V> leftMax=getLastEntry(p.left);
						p.key=leftMax.key;
						p.value=leftMax.value;
						AVLEntry<K, V> newLeft=deleteEntry(p.left, p.key);
						p.left=newLeft;
					}
				}
			}else if(compareResult<0){
				AVLEntry<K, V> newLeft=deleteEntry(p.left, key);
				p.left=newLeft;
			}else{
				AVLEntry<K, V> newRight=deleteEntry(p.right, key);
				p.right=newRight;
			}
			p=fixAfterDeletion(p);
			return p;
		}
	}
	public int getHeight(AVLEntry<K, V> p){
		return p==null?0:p.height;
	}
	private AVLEntry<K, V> rotateRight(AVLEntry<K, V> p){
		AVLEntry<K, V> left=p.left;
		p.left=left.right;
		left.right=p;
		p.height=Math.max(getHeight(p.left), getHeight(p.right))+1;
		left.height=Math.max(getHeight(left.left), p.height)+1;
		return left;
	}
	private AVLEntry<K, V> rotateLeft(AVLEntry<K, V> p){
		AVLEntry<K, V> right=p.right;
		p.right=right.left;
		right.left=p;
		p.height=Math.max(getHeight(p.left), getHeight(p.right))+1;
		right.height=Math.max(p.height, getHeight(right.right))+1;
		return right;
	}
	private AVLEntry<K, V> firstLeftThenRight(AVLEntry<K, V> p){
		p.left=rotateLeft(p.left);
		p=rotateRight(p);
		return p;
	}
	private AVLEntry<K, V> firstRightThenLeft(AVLEntry<K, V> p){
		p.right=rotateRight(p.right);
		p=rotateLeft(p);
		return p;
	}
	private void fixAfterInsertion(K key){
		AVLEntry<K, V> p=root;
		while(!stack.isEmpty()){
			p=stack.pop();
			int newHeight=Math.max(getHeight(p.left), getHeight(p.right))+1;
			if(p.height>1&&newHeight==p.height){
				stack.clear();
				return;
			}
			p.height=newHeight;
			int d=getHeight(p.left)-getHeight(p.right);
			if(Math.abs(d)<=1){
				continue;
			}else{
				if(d==2){
					if(compare(key, p.left.key)<0){
						p=rotateRight(p);
					}else{
						p=firstLeftThenRight(p);
					}
				}else{
					if(compare(key, p.right.key)>0){
						p=rotateLeft(p);
					}else{
						p=firstRightThenLeft(p);
					}
				}
				if(!stack.isEmpty()){
					if(compare(key, stack.peek().key)<0){
						stack.peek().left=p;
					}else{
						stack.peek().right=p;
					}
				}
			}
		}
		root=p;
	}
	public void checkBalance(){
		postOrderCheckBalance(root);
	}
	private void postOrderCheckBalance(AVLEntry<K, V> p){
		if(p!=null){
			postOrderCheckBalance(p.left);
			postOrderCheckBalance(p.right);
			Assert.assertTrue(Math.abs(getHeight(p.left)-getHeight(p.right))<=1);
		}
	}
	public V remove(K key){
		AVLEntry<K, V> entry=getEntry(key);
		if(entry==null){
			return null;
		}
		V oldValue=entry.getValue();
		root=deleteEntry(root, key);
		size--;
		return oldValue;
	}
	public void levelOrder(){
		Queue<AVLEntry<K, V>> queue=new LinkedList<AVLEntry<K,V>>();
		queue.offer(root);
		int preCount=1;
		int pCount=0;
		while(!queue.isEmpty()){
			preCount--;
			AVLEntry<K, V> p=queue.poll();
			System.out.print(p+" ");
			if(p.left!=null){
				queue.offer(p.left);
				pCount++;
			}
			if(p.right!=null){
				queue.offer(p.right);
				pCount++;
			}
			if(preCount==0){
				preCount=pCount;
				pCount=0;
				System.out.println();
			}
		}
	}
	public AVLEntry<K, V> fixAfterDeletion(AVLEntry<K, V> p){
		if(p==null){
			return null;
		}else{
			p.height=Math.max(getHeight(p.left), getHeight(p.right))+1;
			int d=getHeight(p.left)-getHeight(p.right);
			if(d==2){
				if(getHeight(p.left.left)-getHeight(p.left.right)>=0){
					p=rotateRight(p);
				}else{
					p=firstLeftThenRight(p);
				}
			}else if(d==-2){
				if(getHeight(p.right.right)-getHeight(p.right.left)>=0){
					p=rotateLeft(p);
				}else{
					p=firstRightThenLeft(p);
				}
			}
			return p;
		}
	}
}
