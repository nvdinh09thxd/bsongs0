package models;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Song {
	private int id;
	private String name;
	private String description;
	private String detail;
	private Timestamp createAt;
	private String picture;
	private int count;
	private Category cat;

	public Song(String name, String description, String detail, String picture, Category cat) {
		super();
		this.name = name;
		this.description = description;
		this.detail = detail;
		this.picture = picture;
		this.cat = cat;
	}

}
