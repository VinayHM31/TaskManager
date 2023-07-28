package com.uttara.taskmanager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PersonalTaskManager {
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			Scanner sc1 = new Scanner(System.in);
			System.out.println(" ");
			System.err.println(" *****************************************");
			System.err.println(" *            TASK MANAGER               *");
			System.err.println(" *****************************************");
			Logger.getIntenceOf().log(" Task Manager Starting");
			String cname;
			int i = 0;
			FileChecking file = new FileChecking();
			while (i < 5) {
				System.out.println("  1 Create Category");
				System.out.println("  2 Load Category");
				System.out.println("  3 Search");
				System.out.println("  4 List");
				System.out.println("  5 Exit");
				System.out.println(" -> Enter your Option");
				System.out.println(" ");
				while (!sc.hasNextInt()) {
					System.out.println(" That's not a number!");
					sc.nextInt();
				}
				i = sc.nextInt();
				switch (i) {
//Main case Create Category :
				case 1:
					System.out.println(" ");
					System.out.println(" Enter Category Name ");
					cname = sc1.nextLine();
					while (!CategoryNameValidation.categorynamevalidation(cname)) {
						System.out.println(" Name contains: [A-Z][a-z], One World ");
						cname = sc1.nextLine();
					}
					if (file.categoryExits(cname)) {
						System.out
								.println(" You Entered Category Name is Already Exists Please Enter Another Category");
					} else {
						System.out.println(" ");
						doTask(cname);
					}
					break;
//Main switch Load Category : -->
				case 2:
					file.listFile();
					System.out.println(" Enter Categoty Name for Load your Category");
					cname = sc1.next();
					while (!file.categoryExits(cname)) {
						System.out.println(" You Entered Category Name is Incorrect! Please Enter Valid Category Name");
						cname = sc1.next();
					}
					if (file.categoryExits(cname)) {
						doTask(cname);
					} else
						System.out.println(" You Entered Category not found...!");
					break;
//Main switch Search Category : -->
				case 3:
					try {
						file.listFile();
						System.out.println(" Enter Above Listed Files Name to Search");
						cname = sc1.nextLine();
						while (!file.categoryExits(cname)) {
							System.out.println(
									" You Entered Category Name is Incorrect! Please Enter Valid Category Name");
							cname = sc1.nextLine();
						}
						System.out.println(" Enter Text to check weather Given Text is Present or Not");
						String stringToSearch = sc1.nextLine();
						file.search(cname, stringToSearch);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
//Main switch List Category  : -->
				case 4:
					try {
						file.listFile();
						System.out.println(" ");
					} catch (Exception e) {
						e.printStackTrace();
					}
//Main Switch Exit : -->
				case 5:
					System.out.println(" Exited");
					break;
				default:
					System.out.println(" Please select the above option");
					break;
				}
			}
			Logger.getIntenceOf().log(" TaskManager Ending...");
			sc.close();
			sc1.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public static void doTask(String category) {
		String cname, newtask, description, duedate, tags, stringToSearch, newDescription, newTags;
		int priority;
		int i = 0, p = 0;
		FileChecking file = new FileChecking();
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		while (p != 6) {
			System.out.println(" ");
			System.out.println("  1 Add a Task");
			System.out.println("  2 Edit a Task");
			System.out.println("  3 Remove a Task");
			System.out.println("  4 List the Task");
			System.out.println("  5 Search");
			System.out.println("  6 Go Back");
			System.out.println(" -> Enter your option");
			System.out.println(" ");
			while (!sc.hasNextInt()) {
				System.out.println(" WARNNING : Please Enter Numbers only!");
				sc.nextInt();
			}
			p = sc.nextInt();
			switch (p) {
//Add Task
			case 1:
				try {
					System.out.println(" Enter a TaskName");
					newtask = sc1.nextLine();
					while (!CategoryNameValidation.categorynamevalidation(newtask)) {
						System.err.println(
								" Name contains: [A-Z][a-z], One World, No Numbers and Special Character Allowed");
						newtask = sc1.nextLine();
					}
					System.out.println(" Enter a Description");
					description = sc1.nextLine();
					while (!CategoryNameValidation.categorynamevalidation(description)) {
						System.err.println(
								" Name contains: [A-Z][a-z], One World, No Numbers and Special Character Allowed");
						description = sc1.nextLine();
					}
					System.out.println(" Enter a Tags");
					tags = sc1.nextLine();
					while (!CategoryNameValidation.categorynamevalidation(tags)) {
						System.err.println(
								" Name contains: [A-Z][a-z], One World, No Numbers and Special Character Allowed");
						tags = sc1.nextLine();
					}
					System.out.println(" Enter Date [Format : dd/MM/yyyy]");
					duedate = sc1.nextLine();
					System.out.println(" Enter priority");
					priority = sc.nextInt();
					while (!CategoryNameValidation.intPriorityValidation(priority)) {
						System.err.println(" Priority contains: [1-10] and Text, Special Character are Not Allowed");
						priority = sc.nextInt();
					}
					Date dt = sd.parse(duedate);
					TaskBean tb = new TaskBean(newtask, description, dt, tags, priority);
					String result = file.createNewTask(tb, category);
					if (result.equals("Completed")) {
						System.out.println(" ------------------------------------------");
						System.out.println(" | Task " + newtask + " Added successfully          |");
						System.out.println(" ------------------------------------------");
					} else
						System.out.println(" Failed to add task " + result);
					break;
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
//Edit Task-----------------------------------------------------------------------------------------------------------------------------------
			case 2:
				try {
					// file.listFile();
					try {
						int ie = 0;
						while (ie != 4) {
							if (file.categoryExits(category)) {
								System.out.println("  1 Change Description");
								System.out.println("  2 Change Status");
								System.out.println("  3 Change Priority");
								System.out.println("  4 Exit");
								System.out.println(" -> Enter your option for Edit your Task");
								System.out.println(" ");
								while (!sc.hasNextInt()) {
									System.out.println(" WARNNING : Please Enter Numbers only");
									sc.nextInt();
								}
								ie = sc.nextInt();
								switch (ie) {
								case 1:
									System.out.println(" Enter Task Name");
									newtask = sc1.next();
									if (file.taskExits(category, newtask)) {
										System.out.println(" Enter New Description");
										newDescription = sc1.next();
										file.update(category, newtask, newDescription);
									} else {
										System.out.println(
												" Task Not Found in the category..! Please Enter Valid Category Name");
										System.out.println(" ");
									}
									break;

								case 2:
									System.out.println(" Enter Task Name");
									newtask = sc1.nextLine();
									if (file.taskExits(category, newtask)) {
										System.out.println(" Enter New Tags");
										newTags = sc1.nextLine();
										file.updateTags(category, newtask, newTags);
									} else {
										System.out.println(
												" Task Not Found in the category..! Please Enter Valid Category Name");
										System.out.println(" ");
									}
									break;

								case 3:
									System.out.println(" Enter Task Name");
									newtask = sc1.nextLine();
									if (file.taskExits(category, newtask)) {
										System.out.println(" Enter New priority");
										priority = sc.nextInt();
										file.updatePriority(category, newtask, priority);
									} else {
										System.out.println(
												" Task Not Found in the category..! Please Enter Valid Category Name");
										System.out.println(" ");
									}
									break;

								case 4:
									System.out.println(" Back to Main Menu");
									break;
								default:
									System.out.println(" Invalid Choice");
									break;
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
//Remove Task--------------------------------------------------------------------------------------------------------------------------------
			case 3:
				try {
					System.out.println(" Enter Category Name to Remove Task");
					cname = sc1.nextLine();
					while (!CategoryNameValidation.categorynamevalidation(category)) {
						System.out.println(
								" Name contains: [A-Z][a-z], One World, No Numbers and Special Character Allowed");
						cname = sc1.nextLine();
					}
					System.out.println(" Enter TaskName to Remove");
					newtask = sc1.nextLine();
					if (file.taskExits(cname, newtask)) {
						file.rermoveTasks(cname, newtask);
						System.out.println("" + newtask + " task removed successfully.....!");
					} else {
						System.out.println(" Task Not Found in the category..! Please Enter Valid Category Name");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

//List Tasks-------------------------------------------------------------------------------------------------------------------------------------
			case 4:
				try {
					int i41 = 0;
					System.out.println(" Please Select Below Options to perform Sort");
					while (i41 != 3) {
						System.out.println("  1 Alpha Sort");
						System.out.println("  2 Priority Sort");
						// System.out.println("3 DueDate Sort");
						System.out.println("  3 Exit");
						i41 = sc.nextInt();

						switch (i41) {
						case 1:
							for (String string : file.sortAplhabitacilly(category)) {
								System.out.println(string);
							}
							break;

						case 2:
							for (TaskBean bean : file.sortPriorityWise(category)) {
								System.out.println(bean);
							}
							break;
//						case 3:
//							for (TaskBean bean : file.sortDueDate(category)) {
//								System.out.println(bean);
//							}
//							break;
						case 3:
							System.out.println(" Back to main menu");
							break;

						default:
							System.out.println(" Invalid Choice");
							break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
//Search Tasks-----------------------------------------------------------------------------------------------------------------------------------
			case 5:
				try {
					System.out.println("  Enter Above Listed Files Name to Search");
					cname = sc1.nextLine();
					while (!file.categoryExits(cname)) {
						System.out.println(" ");
						System.out.println(" You Entered Category Name is Incorrect! Please Enter Valid Category Name");
						cname = sc1.nextLine();
					}
					System.out.println(" Enter Text to check weather Given Text is Present or Not");
					stringToSearch = sc1.nextLine();
					file.search(cname, stringToSearch);
					System.out.println(" ");
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
//Go Back to main switch-------------------------------------------------------------------------------------------------------------------------
			case 6:
				System.out.println("  Back to Main Menu");
				break;
			default:
				System.out.println("  Invalid");
				break;
			}
		}
	}
}