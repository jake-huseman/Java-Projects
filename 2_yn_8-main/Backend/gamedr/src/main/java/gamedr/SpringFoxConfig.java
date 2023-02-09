package gamedr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Jake Huseman
 * @author David Dong
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfig
{
	private static final String BP = "REST API related to ";
	private static final String TAG_1 = "rejection-controller";
	private static final String TAG_2 = "mchron-controller";
	private static final String TAG_3 = "user-controller";
	private static final String TAG_4 = "report-controller";
	private static final String TAG_5 = "profile-controller";
	private static final String TAG_6 = "moderator-controller";
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .tags(
                		new Tag(TAG_1, BP + "RejectionController Entity"),
                		new Tag(TAG_2, BP + "chronicles for Matchmakers (Mchron Entity)"),
                		new Tag(TAG_3, BP + "User Entity"),
                		new Tag(TAG_4, BP + "Report Entity"),
                		new Tag(TAG_5, BP + "Profile Entity"),
                		new Tag(TAG_6, BP + "Moderator Entity")
                );
    }
}
