package com.uttara.taskmanager;

import java.io.BufferedReader;  
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class FileChecking {
	public boolean categoryExits(String cname) {
		return new File(cname + ".NewFile").exists();
	}
	
	public boolean taskExits(String cname, String newtask) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(cname + ".NewFile"));
			String line = null;
			while((line = br.readLine()) != null){
				if(line.startsWith(newtask)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return false;
	}

	public String createNewTask(TaskBean tb, String cname) {
		CategoryNameValidation.categorynamevalidation(cname);
		BufferedWriter b = null;
		try {
			b = new BufferedWriter(new FileWriter(cname + ".NewFile", true));
			Date d = new Date();
			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
			String pd = sd.format(d);
			b.write(tb.getNewtask() + " | " + tb.getDescription() + " | " + tb.getTags() + " | " + pd + " | "
					+ tb.getPriority() + " | " + d.getTime());
			b.newLine();
			Logger.getIntenceOf().log(tb.getNewtask()+" is created...");
			return "Completed";
		} catch (IOException e) {
			e.printStackTrace();
			return "someting went wrong" + e.getMessage();
		} finally {
			if (b != null)
				try {
					b.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	// list files
	public void listFile() throws IOException {
		File dir = new File("B:\\eclipse-java-2022-12-R-win32-x86_64\\eclipse\\PersonalTaskManager");
		String contents[] = dir.list();
		System.out.println("");
		System.out.println(" Listing All Files");
		for (String content : contents) {
			if (content.endsWith("NewFile"))
				System.out.println("  > " + content.substring(0,content.indexOf(".NewFile")));
		}
		System.out.println("");
	}

	// for Searching
	public List<TaskBean> search(String cname, String str) {
		CategoryNameValidation.categorynamevalidation(cname);
		int count = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(cname + ".NewFile"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String[] words = null;
		String s;
		try {
			while ((s = br.readLine()) != null) {
				words = s.split(":");
				for (String word : words) {
					if (word.contains(str)) {
						count++;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (count != 0) {
			System.out.println(" Given String is present in " + cname + ".NewFile");
			System.out.println(" Number of Words occur for Given Text is " + count);
			System.out.println("");
		} else
			System.out.println(" Given Text is not present in " + cname + ".NewFile");
		System.out.println(" ");
		return null;
	}

	// Update description
	public String update(String cname, String newtask, String newDescription) {
		BufferedReader br1 = null;
		BufferedWriter b12 = null;
		List<String> list1;
		String line;
		String a[] = null;
		try {
			list1 = new ArrayList<>();
			br1 = new BufferedReader(new FileReader(cname + ".NewFile"));
			while ((line = br1.readLine()) != null) {
				if (line.startsWith(newtask)) {
					a = line.split(" \\| ");
				} else {
					list1.add(line);
				}
			}
			if (a.length > 0)
				a[1] = newDescription;
			b12 = new BufferedWriter(new FileWriter(cname + ".NewFile"));
			for (String string : list1) {
				b12.write(string);
				b12.newLine();
			}
			b12.write(String.join(" | ", a));
			b12.newLine();
			Logger.getIntenceOf().log(newtask+ " Task updated Successfully");
			System.out.println(" ");
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
			System.out.println(" swalpa nodo" + e.getMessage());
		} finally {
			if (br1 != null && b12 != null) {
				try {
					br1.close();
					b12.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "Description Updated Successfully";
	}

	// update status(tags)
	public String updateTags(String cname, String newtask, String tags) {
		BufferedReader br1 = null;
		BufferedWriter b12 = null;
		List<String> list1;
		String line;
		String a[] = null;
		try {
			list1 = new ArrayList<>();
			br1 = new BufferedReader(new FileReader(cname + ".NewFile"));
			while ((line = br1.readLine()) != null) {
				if (line.startsWith(newtask)) {
					a = line.split(" \\| ");
				} else {
					list1.add(line);
				}
			}
			if (a.length > 0)
				a[2] = tags;
			b12 = new BufferedWriter(new FileWriter(cname + ".NewFile"));
			for (String string : list1) {
				b12.write(string);
				b12.newLine();
			}
			b12.write(String.join(" | ", a));
			b12.newLine();
			Logger.getIntenceOf().log(newtask+ " Task updated");
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
			System.out.println("swalpa nodo" + e.getMessage());
		} finally {
			if (br1 != null && b12 != null) {
				try {
					br1.close();
					b12.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "Tags Updated Successfully";
	}

	// update Priority
	public String updatePriority(String cname, String newtask, int priority) {
		BufferedReader br1 = null;
		BufferedWriter b12 = null;
		List<String> list1;
		String line;
		String a[] = null;
		try {
			list1 = new ArrayList<>();
			br1 = new BufferedReader(new FileReader(cname + ".NewFile"));
			while ((line = br1.readLine()) != null) {
				if (line.startsWith(newtask)) {
					a = line.split(" \\| ");
				} else {
					list1.add(line);
				}
			}
			if (a.length > 0)
				a[4] = Integer.toString(priority);
			b12 = new BufferedWriter(new FileWriter(cname + ".NewFile"));
			for (String string : list1) {
				b12.write(string);
				b12.newLine();
			}
			b12.write(String.join(" | ", a));
			b12.newLine();
			Logger.getIntenceOf().log(newtask+ " Task updated");
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
			System.out.println("swalpa nodo" + e.getMessage());
		} finally {
			if (br1 != null && b12 != null) {
				try {
					br1.close();
					b12.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "Priority Updated Successfully";
	}

	// List Tasks
	public List<TaskBean> getTasks(String cname) {
		CategoryNameValidation.categorynamevalidation(cname);
		BufferedReader br1 = null;
		List<TaskBean> list = new ArrayList<>();
		String line;
		try {
			TaskBean task;
			br1 = new BufferedReader(new FileReader(cname + ".NewFile"));
			SimpleDateFormat s1 = new SimpleDateFormat("dd/MM/yyyy");
			while ((line = br1.readLine()) != null) {
				String[] sa = line.split(" \\| ");
				task = new TaskBean(sa[0], sa[1], s1.parse(sa[3]), sa[2], Integer.parseInt(sa[4]));
				list.add(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("swalpa nodo" + e.getMessage());
		} finally {
			if (br1 != null) {
				try {
					br1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	// remove Tasks
	public String rermoveTasks(String cname, String taskname) {
		CategoryNameValidation.categorynamevalidation(cname);
		BufferedReader br1 = null;
		BufferedWriter b12 = null;
		List<String> list1;
		String line;
		try {
			list1 = new ArrayList<>();
			br1 = new BufferedReader(new FileReader(cname + ".NewFile"));
			while ((line = br1.readLine()) != null) {
				if (line.startsWith(taskname)) {
					continue;
				} else {
					list1.add(line);
				}
			}
			b12 = new BufferedWriter(new FileWriter(cname + ".NewFile"));
			for (String string : list1) {
				b12.write(string);
				b12.newLine();
			}
			Logger.getIntenceOf().log(taskname+ " Task deleted");
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
			System.out.println("swalpa nodo" + e.getMessage());
		} finally {
			if (br1 != null && b12 != null) {
				try {
					br1.close();
					b12.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "Removed Successfully";
	}

	// sort Alphabitacilly
	public List<String> sortAplhabitacilly(String cname) {
		CategoryNameValidation.categorynamevalidation(cname);
		List<String> list = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(cname + ".NewFile"));
			String line;

			while ((line = br.readLine()) != null) {
				list.add(line);
			}
			Collections.sort(list, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return o1.length() - o2.length();
				}
			});
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public List<TaskBean> loadTask(String cname) {
		CategoryNameValidation.categorynamevalidation(cname);
		BufferedReader br1 = null;
		List<TaskBean> list1 = null;
		String line;
		try {
			list1 = new ArrayList<>();
			br1 = new BufferedReader(new FileReader(cname + ".NewFile"));
			while ((line = br1.readLine()) != null) {
				String[] a = line.split(" \\| ");
				list1.add(new TaskBean(a[0], a[1], new SimpleDateFormat("dd/MM/yyyy").parse(a[3]), a[2],
						Integer.parseInt(a[4])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br1 != null) {
				try {
					br1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list1;
	}

	// sort prioritywise
	public List<TaskBean> sortPriorityWise(String cname) {
		CategoryNameValidation.categorynamevalidation(cname);
		List<TaskBean> t = loadTask(cname);
		Collections.sort(t, new Comparator<TaskBean>() {

			@Override
			public int compare(TaskBean o1, TaskBean o2) {
				return o1.getPriority() - o2.getPriority();
			}
		});
		return t;
	}

	public List<TaskBean> sortDueDate(String cname) {
		CategoryNameValidation.categorynamevalidation(cname);
		List<TaskBean> t = loadTask(cname);
		Collections.sort(t, new Comparator<TaskBean>() {

			@Override
			public int compare(TaskBean o1, TaskBean o2) {
				return o1.getDuedate().compareTo(o2.getDuedate());
			}
		});
		return t;
	}

}