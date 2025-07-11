package myframework.staff.model.domain;

import lombok.Data;

@Data
public class Bio {
	private int bio_id;
	private String blood;
	private int height;
	private int weight;
	private Staff staff;
}
