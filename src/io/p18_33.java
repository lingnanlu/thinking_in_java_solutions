package io;

import java.util.prefs.Preferences;

public class p18_33 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Preferences pref = Preferences.userNodeForPackage(p18_33.class);
		int value = pref.getInt("base directory", 0);
		System.out.println(value);
	}

}
