package mall.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Member {
	    private int memberId;

	    private SnsProvider snsProvider;

	    private String id;

	    private String password;

	    private String name;

	    private String email;

	    private Timestamp regdate;
	    
	    private String salt; // 암호화 솔트

}
