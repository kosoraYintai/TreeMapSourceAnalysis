package rbt;

import java.util.Random;
import java.util.TreeMap;

import org.junit.Test;

import bstAVL.AVLMap;


public class TestAVLVSRBT {

	private Random random=new Random();
	private static int Max=(1<<21)+99999;
	private static AVLMap<Integer, Integer> map1=new AVLMap<Integer, Integer>();
	private static TreeMap<Integer, Integer> map2=new TreeMap<Integer, Integer>();
	@Test
	public void _1_TestAVLInsert(){
		for(int i=0;i<Max;i++){
			map1.put(random.nextInt(Max), random.nextInt(Max));
		}
	}
	@Test
	public void _2_TestRBTInsert() throws Throwable{
		for(int i=0;i<Max;i++){
			map2.put(random.nextInt(Max), random.nextInt(Max));
		}
	}
	@Test
	public void _3_TestAVLSearch(){
		for(int i=0;i<Max<<1;i++){
		    map1.containsKey(random.nextInt(Max));
	    }
	}
	@Test
	public void _4_TestRBTSearch(){
		for(int i=0;i<Max<<1;i++){
		    map2.containsKey(random.nextInt(Max));
	    }
	}
	@Test
	public void _5_TestAVLRemove(){
		for(int i=0;i<Max>>>1;i++){
			map1.remove(random.nextInt(Max));
		}
	}
	@Test
	public void _6_TestRBTRemove(){
		for(int i=0;i<Max>>>1;i++){
			map2.remove(random.nextInt(Max));
		}
	}
}
