package org.zerock.myapp.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties
public class BoardDTO {
	private Long id;
	private String title;
	private String writer;
	private String content;
	private Integer cnt;
	
	private Integer currPage;
	private Integer pageSize;
} // end class
