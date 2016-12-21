package server;

import java.sql.Timestamp;

public class Doc{
	private String ID;
	private String creator;
	private Timestamp timestamp;
	private String description;
	private String filename;
	
	protected static String FileDatabasePath = "e:\\OPP\\ServerFileDatabase\\";
	
	public Doc(String ID, String creator, Timestamp timestamp, String description, String filename) {
		super();
		this.ID = ID;
		this.creator = creator;
		this.timestamp = timestamp;
		this.description = description;
		this.filename = filename;
	}

	public String getID() {
		return this.ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}