package bstAVL;

import java.util.Random;
import java.util.TreeMap;

import org.junit.Test;

public class TestBSTVSTreeMap {
public static final int MAX=65535;
private Random random=new Random();
@Test
public void testBSTRandom(){
	AVLMap<Integer, String> map=new AVLMap<Integer, String>();
	for(int i=0;i<MAX;i++){
		map.put(random.nextInt(MAX), random.nextInt(MAX)+"");
	}
	map.checkBalance();
	for(int i=0;i<MAX;i++){
		map.get(random.nextInt(MAX));
	}
}
@Test
public void testTreeMapRandom(){
	TreeMap<Integer, String> map=new TreeMap<Integer, String>();
	for(int i=0;i<MAX;i++){
		map.put(random.nextInt(MAX), random.nextInt(MAX)+"");
	}
	for(int i=0;i<MAX;i++){
		map.get(random.nextInt(MAX));
	}
}
@Test
public void testBSTIncrement(){
	AVLMap<Integer, String> map=new AVLMap<Integer, String>();
	for(int i=0;i<MAX;i++){
		map.put(i, random.nextInt(MAX)+"");
	}
	map.checkBalance();
	for(int i=0;i<MAX;i++){
		map.get(random.nextInt(MAX));
	}
}
@Test
public void testTreeMapIncrement(){
	TreeMap<Integer, String> map=new TreeMap<Integer, String>();
	for(int i=0;i<MAX;i++){
		map.put(i, random.nextInt(MAX)+"");
	}
	for(int i=0;i<MAX;i++){
		map.get(random.nextInt(MAX));
	}

}
}
