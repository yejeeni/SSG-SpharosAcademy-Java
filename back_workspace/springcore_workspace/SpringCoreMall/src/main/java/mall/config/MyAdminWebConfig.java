package mall.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "mall" })
@Import({
    ViewResolverConfig.class,
    DataSourceConfig.class,
    MybatisConfig.class,
    HibernateConfig.class
})

/**
 * 관심사 분리(SoC. Separation of Concerns)
 */
public class MyAdminWebConfig {
    // 위 설정 클래스들을 모아서 전체 설정을 구성
}
