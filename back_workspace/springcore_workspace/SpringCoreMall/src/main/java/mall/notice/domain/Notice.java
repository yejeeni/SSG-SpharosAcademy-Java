package mall.notice.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity // 하이버네이트가 사용할 대상
@Table(name="notice") // 연동 테이블
public class Notice {
	@Id
	private int notice_id;
	private String title;
	private String writer;
	private String content;
	private int hit;
	@Column(name = "regDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate;
}
