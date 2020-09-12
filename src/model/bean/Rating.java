package model.bean;

import java.sql.Timestamp;

public class Rating {
	int id;
	int id_song;
	float rating;
	int count;
	Timestamp date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_song() {
		return id_song;
	}

	public void setId_song(int id_song) {
		this.id_song = id_song;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Rating(int id, int id_song, float rating, int count, Timestamp date) {
		super();
		this.id = id;
		this.id_song = id_song;
		this.rating = rating;
		this.count = count;
		this.date = date;
	}

	public Rating() {
		super();
	}

}
