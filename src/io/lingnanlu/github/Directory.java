package io.lingnanlu.github;
import java.io.*;
import java.util.regex.Pattern;
import java.util.*;
public final class Directory {
	public static File[] local(File dir, final String regex){
		return dir.listFiles(new FilenameFilter(){
			private Pattern pattern = Pattern.compile(regex);
			@Override
			public boolean accept(File dir, String name) {
				// TODO 自动生成的方法存根
				//System.out.println(name);
				//System.out.println(new File(name).getName());
				//System.out.println();
				return pattern.matcher(name).matches();
			}
			
		});
		
	}
	
	public static File[] local(String path, final String regex){
		return local(new File(path), regex);
	}
	
	
	
	// A two-tuple for returning a pair of objects:
	public static class TreeInfo implements Iterable<File> {
		public List<File> files = new ArrayList<File>();
		public List<File> dirs = new ArrayList<File>();

		// The default iterable element is the file list:
		public Iterator<File> iterator() {
			return files.iterator();
		}

		void addAll(TreeInfo other) {
			files.addAll(other.files);
			dirs.addAll(other.dirs);
		}

		public String toString() {
			return "dirs: " + PPrint.pformat(dirs) + "\n\nfiles: "
					+ PPrint.pformat(files);
		}
	}

	public static TreeInfo walk(String start, String regex) { // Begin recursion
		return recurseDirs(new File(start), regex);
	}

	public static TreeInfo walk(File start, String regex) { // Overloaded
		return recurseDirs(start, regex);
	}

	public static TreeInfo walk(File start) { // Everything
		return recurseDirs(start, ".*");
	}

	public static TreeInfo walk(String start) {
		return recurseDirs(new File(start), ".*");
	}

	static TreeInfo recurseDirs(File startDir, String regex) {
		TreeInfo result = new TreeInfo();
		for (File item : startDir.listFiles()) {
			if (item.isDirectory()) {
				result.dirs.add(item);
				result.addAll(recurseDirs(item, regex));
			} else // Regular file
			if (item.getName().matches(regex))
				result.files.add(item);
		}
		return result;
	}
	
	
	public static void main(String[] args){
		
	}
}
