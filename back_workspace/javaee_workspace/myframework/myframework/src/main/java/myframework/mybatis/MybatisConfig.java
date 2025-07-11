package myframework.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * mybaties의 설정 파일인 xml을 읽어드리며, 필요한 객체를 만들어낸다.
 *하지만 xml은 프로그래밍 언어가 아니라, java를 이용하여 xml을 해석해야한다.
 *아래의 클래스는 설정 xml을 읽어들여, 필요한 객체를 얻기 위한 설정 객체이다.
 *Mybatis 사실 내부적으로 개발자 대신 JDBC를 제어하고 있다. 하지만 mybaits를사용할 경우개발자는
 *개발자는 더이상 jdbc를 직접 제어하지 않는다.
 이 쿼리를 대신 처리해주는 걸 SQLSession이라고 한다.
 */
public class MybatisConfig {
	private static MybatisConfig instance;
	private SqlSessionFactory sqlSessionFactory;
	private MybatisConfig() {
		String resource = "myframework/mybatis/mybatis-config.xml";
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory =
					  new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static MybatisConfig getInstance() {
		if(instance == null) {
			instance = new MybatisConfig();
		}
		
		return instance;
	}
	
	
	//SQLSesstion을 반환하는 메서드 정의
	//SQLSession은 쿼리문을 수행하는 객체(Connection, PreparedStatement, ResultSet이 숨겨져 있다)
	public SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
	
}
