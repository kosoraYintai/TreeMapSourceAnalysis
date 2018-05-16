package rbt;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class ReflectUtilForTreeMap {
	public static Class<?> entryClass;
	public static Field leftField;
	public static Field rightField;
	public static Class<?> treeMapClass;
	public static Field rootField;
	public static Field colorFiled;
	static{
		try {
			entryClass=Class.forName("java.util.TreeMap$Entry");
			leftField=entryClass.getDeclaredField("left");
			leftField.setAccessible(true);
			rightField=entryClass.getDeclaredField("right");
			rightField.setAccessible(true);
			treeMapClass=TreeMap.class;
			rootField=treeMapClass.getDeclaredField("root");
			rootField.setAccessible(true);
			colorFiled=entryClass.getDeclaredField("color");
			colorFiled.setAccessible(true);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	private static <K,V> void printTreeNode(Map.Entry<K, V> p) throws Exception{
		boolean color=(Boolean) colorFiled.getBoolean(p);
    	String colorStr="";
    	if(color){
    		colorStr="BLACK";
    	}else{
    		colorStr="RED";
    	}
    	System.out.print(p.getKey()+"-"+colorStr+"-"+p.getValue()+"  ");
	}
	/**
	 * 层序输出JDK的红黑树
	 */
	@SuppressWarnings("unchecked")
	public static <K,V> void levelOrderPrintTree(TreeMap<K,V> map) throws Exception{
		Map.Entry<K, V> root=(Map.Entry<K, V>) rootField.get(map);
        if(root==null){
        	return;
        }
        Queue<Map.Entry<K, V>> queue=new LinkedList<Map.Entry<K, V>>();
        queue.offer(root);
        int preCount=1;
        int pCount=0;
        while(!queue.isEmpty()){
        	Map.Entry<K, V> p=queue.poll();
        	preCount--;
        	printTreeNode(p);
        	if((Map.Entry<K, V>)leftField.get(p)!=null){
        		queue.offer((Map.Entry<K, V>)leftField.get(p));
        		pCount++;
        	}
        	if((Map.Entry<K, V>)rightField.get(p)!=null){
        		queue.offer((Map.Entry<K, V>)rightField.get(p));
        		pCount++;
        	}
        	if(preCount==0){
        		preCount=pCount;
        		pCount=0;
        		System.out.println();
        	}
        }
        System.out.println("------------------------");
	}
}
