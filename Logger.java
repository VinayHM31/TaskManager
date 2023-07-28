package com.uttara.taskmanager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
	private static Logger instence = null;
	private String path = "B:\\eclipse-java-2022-12-R-win32-x86_64\\eclipse\\PersonalTaskManager\\src\\com\\uttara\\taskmanager\\log\\log.txt";

	private Logger() {
	}

	public static Logger getIntenceOf() {
		if (instence == null) {
			synchronized (Logger.class) {
				if (instence == null) {
					instence = new Logger();
				}
			}
		}
		return instence;
	}

	public void log(String message) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				BufferedWriter bw = null;
				try {
					System.out.println(" Logger : " + message);
					bw = new BufferedWriter(new FileWriter(path, true));
					bw.write(message + "\t |" + new Date());
					bw.newLine();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} finally {
					if (bw != null) {
						try {
							bw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}
}