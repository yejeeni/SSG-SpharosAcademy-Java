package com.ssg.hiberasync.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * [ORM - Java의 Object와 Relation(RDBMS) 자체, 즉 테이블과 java 객체와의 매핑]
 * JPA는 Java 자체 api에서 지원하는 인터페이스로, java 표준
 * hibernate는 사설로 JPA를 구현한 구현체로, 표준은 아님
 */

@Data
@Entity
@Table(name="food_type")
public class FoodType {
	@Id
	private int food_type_id;
	private String title;
}
