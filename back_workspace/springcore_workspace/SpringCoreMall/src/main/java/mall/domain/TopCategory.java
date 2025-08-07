package mall.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="topcategory")
public class TopCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int topcategory_id;
	
	private String top_name;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "topCategory")
	@JsonManagedReference 
	private List<SubCategory> subCategories;
}
