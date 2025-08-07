package mall.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="subcategory")
public class SubCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subcategory_id;
	
	private String sub_name;
	
	@ManyToOne	
	@JoinColumn(name="topcategory_id")
	@JsonBackReference
	private TopCategory topCategory;
}
