package com.example.studyclient.config;

import com.sms.AmazonawsSMS;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ConfigBean //boot -->spring   applicationContext.xml --- @Configuration配置   ConfigBean = applicationContext.xml
{ 
	@Bean
	@LoadBalanced//Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端       负载均衡的工具。
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}

//	@Bean
//	public IRule myRule()
//	{
		//return new RoundRobinRule();
		//return new RandomRule();//达到的目的，用我们重新选择的随机算法替代默认的轮询。
//		return new RetryRule();
//	}


	//发送短信配置bean
	@Bean
	public AmazonawsSMS initSMS(){
		return new AmazonawsSMS();
	}

}

//@Bean
//public UserServcie getUserServcie()
//{
//	return new UserServcieImpl();
//}
// applicationContext.xml == ConfigBean(@Configuration)
//<bean id="userServcie" class="com.atguigu.tmall.UserServiceImpl">


//	默认使用的是  MappingJackson2HttpMessageConverter
//	@Bean
//	@Primary
//	public HttpMessageConverters jacksonHttpMessageConverters() {
//		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//		converter.setSupportedMediaTypes(getSupportedMediaTypes());
//		converter.setDefaultCharset(Charset.forName("UTF-8"));
//		return new HttpMessageConverters(converter);
//	}
//
//	@Bean
//	public HttpMessageConverters fastJsonHttpMessageConverters() {
//		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//		FastJsonConfig fastJsonConfig = new FastJsonConfig();
//		fastJsonConfig.setSerializerFeatures(
//				SerializerFeature.PrettyFormat,
//				SerializerFeature.WriteNullStringAsEmpty,
//				SerializerFeature.WriteNullListAsEmpty,
//				// 循环引用
//				SerializerFeature.DisableCircularReferenceDetect);
//		converter.setFastJsonConfig(fastJsonConfig);
//		converter.setSupportedMediaTypes(getSupportedMediaTypes());
//		return new HttpMessageConverters(converter);
//	}
//
//	private List<MediaType> getSupportedMediaTypes() {
//		List<MediaType> mediaTypes = new ArrayList<>();
//		mediaTypes.add(MediaType.ALL);
//		return mediaTypes;
//	}