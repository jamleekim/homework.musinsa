package homework.musinsa.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("브랜드 API")
                    .description("김재민 백엔드 과제 프로젝트 API 명세서")
                    .version("1.0.0")
                );
    }

}
