//: annotations/AtUnitExample1.java
package annotations;

import io.lingnanlu.github.atunit.*;

import io.lingnanlu.github.*;

public class AtUnitExample1 {
	

	public String methodOne() {
		return "This is methodOne";
	}

	public int methodTwo() {
		System.out.println("This is methodTwo");
		return 2;
	}

	@TestNote(note = "methodOneTest Note")
	@Test
	boolean methodOneTest() {
		return methodOne().equals("This is methodOne");
	}

	@TestNote(note = "m2 Note")
	@Test
	boolean m2() {
		return methodTwo() == 2;
	}

	@Test
	private boolean m3() {
		return true;
	}

	// Shows output for failure:
	@Test
	boolean failureTest() {
		return false;
	}

	@Test
	boolean anotherDisappointment() {
		return false;
	}

	public static void main(String[] args) throws Exception {
		OSExecute.command("java io.lingnanlu.github.atunit.AtUnit annotations/AtUnitExample1");
	//	OSExecute.command("java net.mindview.atunit.AtUnit annotations/AtUnitExample1");

	}
} /*
 * Output: annotations.AtUnitExample1 . methodOneTest . m2 This is methodTwo
 * 
 * . m3 . failureTest (failed) . anotherDisappointment (failed) (5 tests)
 * 
 * >>> 2 FAILURES <<< annotations.AtUnitExample1: failureTest
 * annotations.AtUnitExample1: anotherDisappointment
 */// :~
