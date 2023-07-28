package com.uttara.taskmanager;

import java.util.Date;
import java.util.Objects;

public class TaskBean implements Comparable<TaskBean> {

	private String newtask;
	private String description;
	private String tags;
	private Date duedate;
	private int priority;

	public TaskBean() {
	}

	public TaskBean(String newtask, String description, Date duedate, String tags, int priority) {
		super();
		this.newtask = newtask;
		this.description = description;
		this.duedate = duedate;
		this.tags = tags;
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "TaskBean [newtask=" + newtask + ", description=" + description + ", duedate=" + duedate + ", tags="
				+ tags + ", priority=" + priority + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, duedate, newtask, priority, tags);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		TaskBean other = (TaskBean) obj;
		return Objects.equals(description, other.description) && Objects.equals(duedate, other.duedate)
				&& Objects.equals(newtask, other.newtask) && priority == other.priority
				&& Objects.equals(tags, other.tags);
	}

	public String getNewtask() {
		return newtask;
	}

	public void setNewtask(String newtask) {
		this.newtask = newtask;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public int compareTo(TaskBean o) {
		return newtask.compareTo(o.newtask);
	}
}