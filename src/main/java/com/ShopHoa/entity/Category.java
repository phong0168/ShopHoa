package com.ShopHoa.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "category")
public class Category {

    public List<Flower> getFlowers() {
        return flowers;
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Category() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFlowers(List<Flower> flowers) {
        this.flowers = flowers;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH })
    private List<Flower> flowers;


}
