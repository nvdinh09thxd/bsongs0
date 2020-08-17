package model.bean;

import java.sql.Timestamp;

public class Rating {
	int id_song;
	float rating;
	int count;
	Timestamp date;

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
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
	
	public Rating(int id_song, float rating, int count, Timestamp date) {
		super();
		this.id_song = id_song;
		this.rating = rating;
		this.count = count;
		this.date = date;
	}

	public Rating() {
		super();
	}

}
