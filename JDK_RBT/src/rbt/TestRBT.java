package rbt;

import java.util.TreeMap;

import org.junit.Test;

public class TestRBT {
@Test
public void testPrint() throws Exception{
	TreeMap<Integer,Integer> map=new TreeMap<Integer,Integer>();
	map.put(2, 2);
	map.put(4, 4);
	map.put(1, 1);
	map.put(6, 6);
	map.put(5, 5);
	map.put(3, 3);
	ReflectUtilForTreeMap.levelOrderPrintTree(map);
}
@Test
public void testInsert() throws Exception{
	TreeMap<Integer, Integer> map=new TreeMap<Integer, Integer>();
	// 依次插入：12,1,9,2,0,11,7,19,4,15,18,5
	// root,blackHeight=1;
	map.put(12, 12);
	ReflectUtilForTreeMap.levelOrderPrintTree(map);
	// bpOver
	map.put(1, 1);
	ReflectUtilForTreeMap.levelOrderPrintTree(map);
	// leftCase2 --> leftCase3
	map.put(9, 9);
	ReflectUtilForTreeMap.levelOrderPrintTree(map);
	// leftCase1 --> rootOver,blackHeight=2;
	map.put(2, 2);
	ReflectUtilForTreeMap.levelOrderPrintTree(map);
	// bpOver
	map.put(0, 0);
	ReflectUtilForTreeMap.levelOrderPrintTree(map);
	// bpOver
	map.put(11, 11);
	ReflectUtilForTreeMap.levelOrderPrintTree(map);
	// rightCase1 --> bpOver
	map.put(7, 7);
	ReflectUtilForTreeMap.levelOrderPrintTree(map);
	// bpOver
	map.put(19, 19);
	ReflectUtilForTreeMap.levelOrderPrintTree(map);
	// rightCase2 --> rightCase3
	Integer key4=4;
	map.put(key4, key4);
	ReflectUtilForTreeMap.levelOrderPrintTree(map);
	// rightCase1 --> bpOver
	map.put(15, 15);
	ReflectUtilForTreeMap.levelOrderPrintTree(map);
	// leftCase2 --> leftCase3
	map.put(18, 18);
	ReflectUtilForTreeMap.levelOrderPrintTree(map);
	// rightCase1 --> leftCase1 --> rootOver,blackHeight=3
	// case1可以执行多次
	Integer key5=5;
	map.put(key5, key5);
	ReflectUtilForTreeMap.levelOrderPrintTree(map);
	// 插入余下的数字
	int[] others={14,13,10,16,6,3,8,17};
	for(int key:others){
		map.put(key, key);
	}
	ReflectUtilForTreeMap.levelOrderPrintTree(map);
}
@Test
public void testRemove(){
	TreeMap<Integer, Integer> map=new TreeMap<Integer, Integer>();
	int[] array={12,1,9,2,0,11,7,19,4,15,18,5,14,13,10,16,6,3,8,17};
	for(int key:array){
		map.put(key, key);
	}
	// successor --> fix(p) | rightCase4-2 | p=null
	Integer key12=12;
	map.remove(key12);
	
	// successor --> replacement --> p=null --> fix(replacement) | redOver
	Integer key1=1;
	map.remove(key1);
	
	// successor --> fix(p) | leftCase2-2 --> redOver | p=null
	Integer key9=9;
	map.remove(key9);
	
	// successor --> fix(p) | rightCase2-2 --> redOver | p=null
	Integer key2=2;
	map.remove(key2);
	
	// p=null
	Integer key0=0;
	map.remove(key0);
	
	// replacement --> p=null --> fix(replacement) | redOver
	Integer key11=11;
	map.remove(key11);
	
	// replacement --> p=null --> fix(replacement) | redOver
	Integer key7=7;
	map.remove(key7);
	
	// fix(p) | rightCase4-1 | p=null
	Integer key19=19;
	map.remove(key19);
	
	// successor --> fix(p) | leftCase2-2 --> redOver | p=null
	Integer key4=4;
	map.remove(key4);
	
	// fix(p) | leftCase3 --> leftCase4-2 | p=null
	System.out.println("fix(p) | leftCase3 --> leftCase4-2 | p=null");
	Integer key15=15;
	map.remove(key15);
	
	// fix(p) | rightCase2-2 --> redOver | p=null
	Integer key18=18;
	map.remove(key18);
	
	// successor --> replacement --> p=null --> fix(replacement) | redOver
	Integer key5=5;
	map.remove(key5);
	
	// successor --> p=null
	System.out.println("successor --> p=null");
	Integer key14=14;
	map.remove(key14);
	
	// fix(p) | leftCase2-1 --> rightCase2-1 --> rootOver | p=null,blackHeight=2
	System.out.println("fix(p) | leftCase2-1 --> rightCase2-1 --> rootOver | p=null,blackHeight=2");
	Integer key13=13;
	map.remove(key13);
	
	// successor --> replacement --> p=null --> fix(replacement) | redOver
	Integer key10=10;
	map.remove(key10);
	
	// successor --> fix(p) | rightCase1 --> rightCase2-2 --> redOver | p=null
	Integer key16=16;
	map.remove(key16);
	
	// successor --> p=null
	Integer key6=6;
	map.remove(key6);
	
	// fix(p) | leftCase2-1 --> rootOver | p=null,blackHeight=1
	Integer key3=3;
	map.remove(key3);
	
	// replacement --> p=null --> fix(replacement) | rootOver&redOver
	Integer key8=8;
	map.remove(key8);
	
	// root=null,blackHeight=0
	Integer key17=17;
	map.remove(key17);

}
}
