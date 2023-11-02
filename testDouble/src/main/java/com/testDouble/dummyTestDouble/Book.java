package com.testDouble.dummyTestDouble;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
	
	private String bookId;
	private String title;
	private int price;
	private LocalDate publishDate;

}
