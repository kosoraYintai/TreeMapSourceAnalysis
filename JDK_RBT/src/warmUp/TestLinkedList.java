package warmUp;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Test;

public class TestLinkedList {
@Test
public void testQuery(){
	LinkedList<Integer> list=new LinkedList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11));
	System.out.println(list.get(3));
	System.out.println(list.get(9));
}
}
