package com.ShopHoa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "total_price")
    private float totalPrice;

    @Column(name = "f_and_l_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "note")
    private String note;

    @Column(name = "order_date", insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP )
    private Date orderDate;

    @ElementCollection
    @CollectionTable(name = "order_detail", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyJoinColumn(name = "flower_id")
    @Column(name = "quantity")
    private Map<Flower, Integer> flowerQuantities;




    // constructors, getters and setters


}
