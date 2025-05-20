package homework.musinsa.infrastructure.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brand")
@SQLRestriction("is_deleted <> true")
public class BrandEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Setter
	private String name;

	@Column(nullable = false)
	private boolean isDeleted;

	public BrandEntity(String name) {
		this.name = name;
		this.isDeleted = false;
	}

	public void disable() {
		this.isDeleted = true;
	}

}