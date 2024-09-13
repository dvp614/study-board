package org.zerock.myapp.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.generator.EventType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.zerock.myapp.listener.CommonEntityLifecycleListener;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data

@EntityListeners(
		{
			CommonEntityLifecycleListener.class, 
			AuditingEntityListener.class
		})


@Entity(name = "Board")
@Table(name = "board")
public class Board implements Serializable{
	@Serial private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id")
	private Long id;
	
	@Basic(optional = false)
	private String title;
	
	@Basic(optional = false)
	private String writer;
	
	@Basic
	private String content;
	
//	@Basic(optional = false)
	@Column(columnDefinition = "INTEGER default 0", insertable = false, nullable = false)
	private Integer cnt;
	
//	@CreatedDate
	@CurrentTimestamp(event = EventType.INSERT)

    @Basic(optional = false)
    private Date createDate;

    //    @UpdateTimestamp  // 최초등록시에도 타임스탬프가 생성됨
    @CurrentTimestamp(event = EventType.UPDATE) // BEST
    @Basic
    private LocalDateTime updateDate;
} // end class
