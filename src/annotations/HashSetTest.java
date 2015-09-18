package annotations;

import io.lingnanlu.github.OSExecute;

import java.util.*;

import net.mindview.atunit.Test;
public class HashSetTest extends HashSet<String>{

	@Test
	void initialization(){
		assert this.isEmpty();
	}
	
	@Test
	void _contains(){
		this.add("one");
		assert this.contains("one");
	}
	
	@Test
	void _remove(){
		this.add("one");
		this.remove("one");
		assert this.isEmpty();
	}
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		OSExecute.command("java net.mindview.atunit.AtUnit "
				+ "annotations/HashSetTest");
	}

}
