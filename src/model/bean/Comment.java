package model.bean;

import java.sql.Timestamp;

public class Comment {
	int id;
	int id_song;
	String username;
	String comment;
	Timestamp date_create;
	boolean active;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDate_create() {
		return date_create;
	}

	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}

	public int getId_song() {
		return id_song;
	}

	public void setId_song(int id_song) {
		this.id_song = id_song;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Comment(int id, int id_song, String username, String comment, Timestamp date_create, boolean active) {
		super();
		this.id = id;
		this.id_song = id_song;
		this.username = username;
		this.comment = comment;
		this.date_create = date_create;
		this.active = active;
	}

	public Comment() {
		super();
	}

}
