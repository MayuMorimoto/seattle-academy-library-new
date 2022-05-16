package jp.co.seattle.library.dto;

import java.sql.Date;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * 貸出管理情報DTO
 *
 */
@Configuration
@Data
public class LendingManegesInfo {
	
	private int id;
	
	private int bookId;
	
	private Date lendingDate; 
	
	private Date returnDate; 
}
