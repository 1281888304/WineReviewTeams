package Wines.model;

public class Variety {
	protected String type;
	protected String wineTitle;

	public Variety(String type, String wineTitle) {
		this.type = type;
		this.wineTitle = wineTitle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWineTitle() {
		return wineTitle;
	}

	public void setWineTitle(String wineTitle) {
		this.wineTitle = wineTitle;
	}
	
	
}
