package DataLayer.Components;

public class Video {
	private int id;
	private String name;
	
	private enum Type{
		AKCJI,HORROR,KOMEDIA
	}
	
	private Type type;
	private int duration;
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	private int amount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		if(this.id == video.id) return true;
		return false;
	}
}
