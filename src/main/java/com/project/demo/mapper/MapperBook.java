package com.project.demo.mapper;

import org.springframework.stereotype.Component;

import com.project.demo.domain.dto.BookDto;
import com.project.demo.entity.Book;

//@Mapper(componentModel = "spring")
@Component
public class MapperBook {

	//public static final MapperBook INSTANCE = new MapperBook();
	
	  /**
	MapperBook INSTANCE = Mappers.getMapper(MapperBook.class);
	
	  @Mappings({
	  
	  @Mapping(source = "isbn", target = "isbnCode"),
	  
	  @Mapping(source = "dischargeDate", target = "dischargeDate", dateFormat =
	  "yyyy-MM-dd") }) BookDto toDto(Book book);
	  
	  @Mappings({
	  
	  @Mapping(source = "isbnCode", target = "isbn"),
	  
	  @Mapping(source = "dischargeDate", target = "dischargeDate", dateFormat =
	  "yyyy-MM-dd") }) Book toEntity(BookDto bookDto);
	 **/
	
	
	  public BookDto toDto(Book bookEntity) { 
		  if(bookEntity == null) {
			  return null; 
			  }
	  
	  return BookDto.builder() 
			  .id(bookEntity.getId())
			  .title(bookEntity.getTitle()) 
			  .author(bookEntity.getAuthor())
			  .publicationYear(bookEntity.getPublicationYear())
			  .price(bookEntity.getPrice()) 
			  .dischargeDate( bookEntity.getDischargeDate()
			  != null ? bookEntity.getDischargeDate().toString() : null) 
			  .isbnCode(bookEntity.getIsbn())
			  .build(); 
	  }
	  
	  
	  public Book toEntity(BookDto bookDto) {
	  
	  if(bookDto == null) { 
		  return null; 
		  }
	  
	  return Book.builder() 
			  .id(bookDto.getId()) 
			  .title(bookDto.getTitle())
			  .author(bookDto.getAuthor()) 
			  .publicationYear(bookDto.getPublicationYear())
			  .price(bookDto.getPrice()) 
			  .dischargeDate(bookDto.getDischargeDate() != null
			  ? java.time.LocalDate.parse(bookDto.getDischargeDate()) : null)
			  .isbn(bookDto.getIsbnCode()) 
			  .build(); 
	  }
}
