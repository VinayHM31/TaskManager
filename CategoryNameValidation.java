package com.uttara.taskmanager;
public class CategoryNameValidation {
	public static boolean categorynamevalidation(String s) {
		try {
			if (s == null)
				return false;

			if (s.trim().equals(""))
				return false;

			if (s.split(" ").length > 1)
				return false;

			if (Character.isDigit(s.charAt(0)))
				return false;

			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (!(Character.isDigit(c) || Character.isLetter((c))))
					return false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	public static boolean intPriorityValidation(int priority) {
		try {
			if (!(priority >= 0 && priority <= 10))
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}