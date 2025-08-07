package mall.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.hibernate.SessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 애플리케이션 scope 수준에서 관리될 빈즈들에 대한 설정 파일
 */
@Configuration
@ComponentScan(basePackages = {
	    "mall.repository",
	    "mall.service",
	    "mall.domain",
	    "mall.util"
	})
@EnableTransactionManagement(proxyTargetClass = true) 
public class RootConfig {

	/**
	 * 사용할 데이터베이스를 선택
	 * 
	 * @return
	 * @throws NamingException
	 */
	@Bean
	public DataSource dataSource() throws NamingException {
		JndiTemplate jndiTemplate = new JndiTemplate();
		return jndiTemplate.lookup("java:comp/env/jndi/mysql", DataSource.class);
	}

	// 데이터 소스를 결정했다면 적절한 트랜잭션 매니저를 등록해야 함
	// JDBC 또는 MyBatis 사용 시, DataSourceTransactionManager 객체
	// Hibernate 사용 시, HibernateTransactionManager 객체
	// 트랜잭션을 직접 처리하지 않아도 알아서 처리하고, 어떤 기술을 사용하더라도 코드가 일관되어 변함이 없음

	/**
	 * ================================= 
	 * MyBatis 관련 코드
	 *  [바닐라 mybatis] [mybatis spring] 
	 *  SqlSessionFactory => SqlSessionFactoryBean 
	 *  SqlSession => SqlSessionTemplate 
	 *  =================================
	 */

	/**
	 * 트랜잭션 매니저 선택
	 * 
	 * @param sqlSessionFactory
	 * @return
	 */

	@Bean(name = "myBatisTransactionManager")
	public PlatformTransactionManager platformTransactionManager(SqlSessionFactory sqlSessionFactory) {
		return new DataSourceTransactionManager(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource());
	}

	/**
	 * Mybatis의 SqlSession이 모여있는 SqlSessionFactory을 빈으로 등록
	 * 
	 * @throws Exception
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setConfigLocation(new ClassPathResource("mall/config/mybatis/mybatis-config.xml")); // mybatis 설정파일의 위치
		// sqlSessionFactoryBean에게 사용할 데이터베이스를 알려줌
		factoryBean.setDataSource(dataSource);
		

		return factoryBean.getObject();
	}

	/**
	 * DAO가 사용할 SqlSessionTempalte 등록
	 * 
	 * SqlSessionFactoryBean으로부터 쿼리문을 수행하는 객체는 SqlSession이지만, mybatis spring에서는 이를
	 * 랩핑한 객체인 SqlSessionTemplate 객체를 사용
	 * 
	 * @throws Exception
	 */
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sessionFactory) throws Exception {
		return new SqlSessionTemplate(sessionFactory);
	}

	/**
	 * ================================= 
	 * Hibernate 관련 코드
	 * =================================
	 * 
	 * @throws NamingException
	 */

	@Bean
	public LocalSessionFactoryBean sessionFactoryBean() throws NamingException {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		
		// 어떤 데이터베이스를 사용할지
		factoryBean.setDataSource(dataSource());
		factoryBean.setConfigLocation(new ClassPathResource("mall/config/hibernate/hibernate.cfg.xml"));
		factoryBean.setPackagesToScan("mall.domain");  // 여기에 엔티티가 있는 패키지 
		
		return factoryBean;
	}

	/**
	 * 트랜잭션 매니저 등록
	 * 
	 * @param sessionFactory
	 * @return
	 */
	@Primary // 여러개의 트랜잭션 매니저 중 최우선순위로 등록
	@Bean(name = "hibernateTransactionManager")
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}

}
