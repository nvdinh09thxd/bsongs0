package models;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
	int id;
	int id_song;
	String username;
	String comment;
	Timestamp date_create;
	boolean status;
}
