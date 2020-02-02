package spring.boot.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleApplication {

	/**
	 * 1.pom.xml的依赖架构：build project dependency parent
	 *  //spring.factories -->springboot | autoConfiguration | actuator
	 * 2.new SpringApplication(primarySources):
	 * SpringApplication:
	 * 		private List<ApplicationContextInitializer<?>> initializers;
	 * 		private List<ApplicationListener<?>> listeners;
	 *
	 * 3.SpringApplicationRunListeners listeners = getRunListeners(args);
	 * 		org.springframework.boot.context.event.EventPublishingRunListener->SimpleApplicationEventMulticaster(ApplicationListener)
	 *
	 * listeners.starting(); //无操作
	 *
	 * //6中propertySource (c p p c )4点
	 * ConfigurableEnvironment environment = prepareEnvironment(listeners, applicationArguments);
	 *
	 * listeners.environmentPrepared(environment); -->RunListener->initialMulticaster(多播器)->-->pushEvent-->Listener
	 * 	 	//PropertySource + PropertySourceLoader (propertiesPropertyLoader | YamlPropertyLoader)
	 *		RandomValuePropertySource
	 *		ConfigFilePropertySource --> application中的文件映射成propertySource
	 *
	 *===========================================================================
	 * springboot存储变量的 --> ConfigurableEnvironment
	 * StubPropertySource [name='servletConfigInitParams']
	 * StubPropertySource [name='servletContextInitParams']
	 * MapPropertySource [name='systemProperties']
	 * SystemEnvironmentPropertySource [name='systemEnvironment']
	 * RandomValuePropertySource [name='random']
	 * ConfigurationPropertySources [name='applicationConfigurationProperties'] //解析成功后就添加到
	 *===========================================================================
	 *
	 * 4.context = createApplicationContext();
	 * 5.prepareContext(context, environment, listeners, applicationArguments, printedBanner);
	 * 		 context.setEnvironment(environment); //将Ioc容器和环境进行绑定
	 * 		 applyInitializers(context); //将SpringApplication中的init-->添加到context中，并启动
	 * 		 //这里beanFactoryMap中就存在7个BeanDefinition
	 * 		load(context, sources.toArray(new Object[0]));
	 * =========================================================================
	 * SpringApplicationJsonEnvironmentPostProcessor
	 * CloudFoundryVcapEnvironmentPostProcessor
	 * ConfigFileApplicationListener
	 * =========================================================================
	 *
	 * BeanDefinitionRegistry (BeanDefinitionLoader->BeanDefinitionHolder->BeanDefinition)
	 * 			|
	 * DefaultListableBeanFactory
	 *
	 * 6.refreshContext(context);
	 * 7.callRunners(context, applicationArguments);
	 * 8.listeners.running(context);//ApplicationReadyEvent-->destory()
	 *
	 * ===================================================================
	 * @EnableMvc
	 * WebMvcAutoConfiguration
	 *
	 * ===================================================================
	 * AutoConfiguration:
	 * @EnableAutoConfiguration
	 *  	org.springframework.boot.autoconfigure.AutoConfigurationImportSelector#selectImports(org.springframework.core.type.AnnotationMetadata)
	 *		//返回值就是导入的configuration ，这里是按照类的标准进行调入的
	 *
	 *====================================================================
	 * 内嵌tomcat:
	 * 	ServletWebServerFactoryAutoConfiguration
	 * 	TomcatServletWebServerFactory
	 * org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory#getWebServer(org.springframework.boot.web.servlet.ServletContextInitializer...)
	 *====================================================================
	 * @Import
	 * invokeBeanFactoryProcessor
	 * ConfigurationClassPostProcessor
	 *
	 * 总结：3,3,4,4,3
	 */

	public static void main(String[] args) {
		//方式一
		SpringApplication.run(ExampleApplication.class, args);
		//方式二
		//SpringApplication springApplication = new SpringApplication(ExampleApplication.class);
		//ConfigurableApplicationContext context = springApplication.run(args);
		//方式三
		//ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(ExampleApplication.class).run(args);
	}
}
