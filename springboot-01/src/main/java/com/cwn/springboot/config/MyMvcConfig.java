package com.cwn.springboot.config;



import com.cwn.springboot.component.LoginHandLerInterceptor;
import com.cwn.springboot.component.MyLocaleResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;


@SuppressWarnings("ALL")
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    //设置访问绝对路径下的静态资源
    @Value("${file.staticAccessPath}")
    private String staticAccessPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;



   @Bean //注册在容器中
    public WebMvcConfigurerAdapter WebMvcConfigurerAdapter (){
        WebMvcConfigurerAdapter adapter =new WebMvcConfigurerAdapter(){


            @Override
            public void addViewControllers(ViewControllerRegistry registry){
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/index.html").setViewName("index");

//                registry.addViewController( "/" ).setViewName( "forward:/index.html" );
//                registry.setOrder( Ordered.HIGHEST_PRECEDENCE );

                registry.addViewController("/").setViewName("about");
                registry.addViewController("/about.html").setViewName("about");

                registry.addViewController("/").setViewName("single");
                registry.addViewController("/single.html").setViewName("single");

                registry.addViewController("/").setViewName("contact");
                registry.addViewController("/contact.html").setViewName("contact");

                registry.addViewController("/").setViewName("portfolio");
                registry.addViewController("/portfolio.html").setViewName("portfolio");

                registry.addViewController("/").setViewName("savepersoninfo");
                registry.addViewController("/accountmain.html").setViewName("savepersoninfo");

                registry.addViewController("/").setViewName("changeheadimg");
                registry.addViewController("/changeheadimg.html").setViewName("changeheadimg");

                registry.addViewController("/").setViewName("changehead");
                registry.addViewController("/changehead.html").setViewName("changehead");

                registry.addViewController("/").setViewName("photoshow");
                registry.addViewController("/photoshow.html").setViewName("photoshow");

                registry.addViewController("/").setViewName("logout");
                registry.addViewController("/login.html").setViewName("logout");

                registry.addViewController("/login").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");


                registry.addViewController("/changehead").setViewName("changehead");
                registry.addViewController("/changehead.html").setViewName("changehead");

                registry.addViewController("/accountmain").setViewName("accountmain");
                registry.addViewController("/accountmain.html").setViewName("accountmain");

                registry.addViewController("/file").setViewName("file");
                registry.addViewController("/file.html").setViewName("file");

                registry.addViewController("/filemanage").setViewName("filemanage");
                registry.addViewController("/filemanage.html").setViewName("filemanage");

                registry.addViewController("/deldocumentfile").setViewName("deldocumentfile");
                registry.addViewController("/deldocumentfile.html").setViewName("deldocumentfile");

                registry.addViewController("/updatedocumentfile").setViewName("updatedocumentfile");
                registry.addViewController("/updatedocumentfile.html").setViewName("updatedocumentfile");

                registry.addViewController("/gallery").setViewName("gallery");
                registry.addViewController("/gallery.html").setViewName("gallery");

                registry.addViewController("/gallerymanage").setViewName("gallerymanage");
                registry.addViewController("/gallerymanage.html").setViewName("gallerymanage");

                registry.addViewController("/uploadimg").setViewName("uploadimg");
                registry.addViewController("/uploadimg.html").setViewName("uploadimg");

                registry.addViewController("/adminback/adminindex").setViewName("/adminback/adminindex");
                registry.addViewController("/adminback/adminindex.html").setViewName("/adminback/adminindex");

                registry.addViewController("/getUserInfo").setViewName("getUserInfo");

                registry.addViewController("/sendMsg").setViewName("sendMsg");


            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
               // super.addInterceptors(registry);
                InterceptorRegistration interceptorRegistration = registry.addInterceptor(new LoginHandLerInterceptor()).addPathPatterns("/accountmain");

//                InterceptorRegistration interceptorRegistration = registry.addInterceptor(new LoginHandLerInterceptor()).addPathPatterns("/**")
//                        .excludePathPatterns("/","");

            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler(staticAccessPath).addResourceLocations("file:" + uploadFolder);
            }

        };
        return adapter;
    }

    @Bean
    public LocaleResolver localeResolver(){
       return new MyLocaleResolver();
    }

}
