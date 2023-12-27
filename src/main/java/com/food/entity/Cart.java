package com.food.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private double price;
	private String type;
	private int quantity;

	@CreationTimestamp
	private LocalDateTime createdTime ;

//	private long createdTime =new Date().getTime();

	@UpdateTimestamp
	private LocalDateTime modifiedTime;
	
//	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	private ItemEnum item;

}
