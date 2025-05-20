package homework.musinsa.infrastructure.entity;

import homework.musinsa.domain.enums.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@SQLRestriction("is_deleted <> true")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private BrandEntity brand;

    @Column(nullable = false)
    private boolean isDeleted;

    public ProductEntity(int price, Category category, BrandEntity brand) {
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.isDeleted = false;
    }

    public void disable() {
        this.isDeleted = true;
    }

}