package com.zqdy.service.snatch;

public class DoubanActor {

	private String name; 
	
	private String doubanId;
	
	private String imagePath;
	
	public DoubanActor(){
		 
	}
	
	public DoubanActor(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDoubanId() {
		return doubanId;
	}

	public void setDoubanId(String doubanId) {
		this.doubanId = doubanId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
