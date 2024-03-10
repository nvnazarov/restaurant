package kpo.restorant.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Check;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dishes")
@Data
@Check(constraints = "amount >= 0 and price >= 0 and time >= 0")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "time", nullable = false)
    private Integer time;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @OneToMany(mappedBy = "dish")
    private Set<Order> orders;
}
