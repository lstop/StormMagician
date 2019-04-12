package org.flowable.rocketmq;

public class TaskProperties {
	private String id;			//编号
	private String articleID;	//文章编号
	private String reachTime;	//到岗时间
	private String line;		//流水线
	private String taskID;		//任务序号
	private String postname;	//岗位名称
	public TaskProperties(){
		
	}
	public TaskProperties(String id, String articleID, String reachTime, String line, String taskID, String postname) {
		super();
		this.id = id;
		this.articleID = articleID;
		this.reachTime = reachTime;
		this.line = line;
		this.taskID = taskID;
		this.postname = postname;
	}
	public String getId() {
		return id;
	}
	public TaskProperties setId(String id) {
		this.id = id;
		return this;
	}
	public String getArticleID() {
		return articleID;
	}
	public TaskProperties setArticleID(String articleID) {
		this.articleID = articleID;
		return this;
	}
	public String getReachTime() {
		return reachTime;
	}
	public TaskProperties setReachTime(String reachTime) {
		this.reachTime = reachTime;
		return this;
	}
	public String getLine() {
		return line;
	}
	public TaskProperties setLine(String line) {
		this.line = line;
		return this;
	}
	public String getTaskID() {
		return taskID;
	}
	public TaskProperties setTaskID(String taskID) {
		this.taskID = taskID;
		return this;
	}
	public String getPostname() {
		return postname;
	}
	public TaskProperties setPostname(String postname) {
		this.postname = postname;
		return this;
	}
	@Override
	public String toString() {
		return "TaskProperties [id=" + id + ", articleID=" + articleID + ", reachTime=" + reachTime + ", line=" + line
				+ ", taskID=" + taskID + ", postname=" + postname + "]";
	}
	
	
}
