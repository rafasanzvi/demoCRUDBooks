package com.project.demo.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDto {

	private Long id;
	
	private String title;
	
	private String author;
	
	private Integer publicationYear;
	
	private Double price;
	
	private String dischargeDate;
	
	private String isbnCode;
}
