package com.ShopHoa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "total_price", insertable = false)
    private float totalPrice;

    @Column(name = "f_and_l_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "note")
    private String note;

    @Column(name = "order_date", insertable = false)
    private Date orderDate;

    @ElementCollection
    @CollectionTable(name = "order_detail", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyJoinColumn(name = "flower_id")
    @Column(name = "quantity")
    private Map<Flower, Integer> flowerQuantities;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;




    // constructors, getters and setters


}
