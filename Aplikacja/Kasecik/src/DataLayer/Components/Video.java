package DataLayer.Components;

public class Video {
	private int id;
	private String name;

	private String type;
	private int duration;
	private int amount;

	
	public Video(String line) {
		String [] strArr = line.split(";");
		this.id = Integer.parseInt(strArr[0]);
		this.name = strArr[1];
		this.type = strArr[2];
		this.id = Integer.parseInt(strArr[4]);
		this.id = Integer.parseInt(strArr[5]);
	}
	
	
	public Video(int id, String name, String type, int duration, int amount) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.duration = duration;
		this.amount = amount;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String toString() {
		return id+";"+name+";"+type+";"+duration+";"+amount;
	}
	
	public boolean equals(Video video) {
		if(video == null) return false;
		if(this.id == video.id) return true;
		return false;
	}
}
