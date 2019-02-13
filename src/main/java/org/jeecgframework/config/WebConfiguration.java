package org.jeecgframework.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.jeecgframework.core.aop.GZipFilter;
import org.jeecgframework.web.system.listener.InitListener;
import org.jeecgframework.web.system.listener.OnlineListener;
import org.jeecgframework.web.system.servlet.ImgServerServlet;
import org.jeecgframework.web.system.servlet.RandCodeImageServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.IntrospectorCleanupListener;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.EventListener;

@Configuration
public class WebConfiguration {

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean
    public ServletRegistrationBean<Servlet> customDispatcherServlet() {
        ServletRegistrationBean<Servlet> servletRegistrationBean = new ServletRegistrationBean<>(dispatcherServlet());
        servletRegistrationBean.setName("springMVC");
        servletRegistrationBean.addInitParameter("contextConfigLocation", "classpath*:spring-mvc.xml");
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.addUrlMappings("*.do", "/rest/*");
        return servletRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean<Servlet> druidStatViewServlet() {
        ServletRegistrationBean<Servlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(),"/webpage/system/druid/*");
        servletRegistrationBean.setName("druidStatView");
        return servletRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean<Servlet> randCodeImageServlet() {
        ServletRegistrationBean<Servlet> servletRegistrationBean = new ServletRegistrationBean<>(new RandCodeImageServlet(),"/randCodeImage");
        servletRegistrationBean.setName("RandCodeImage");
        return servletRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean<Servlet> imgServerServlet() {
        ServletRegistrationBean<Servlet> servletRegistrationBean = new ServletRegistrationBean<>(new ImgServerServlet(),"/img/server/*", "/img-online/server/*");
        servletRegistrationBean.setName("imgServerServlet");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> openSessionInViewFilter() {
        FilterRegistrationBean<Filter> openSessionInViewFilter = new FilterRegistrationBean<>();
        openSessionInViewFilter.setFilter(new OpenSessionInViewFilter());
        openSessionInViewFilter.setName("openSessionInView");
        openSessionInViewFilter.addUrlPatterns("*.do", "/rest/*");
        openSessionInViewFilter.addInitParameter("singleSession", "true");
        openSessionInViewFilter.setOrder(1);
        return openSessionInViewFilter;
    }

    @Bean
    public FilterRegistrationBean<Filter> ecsideExportFilter() {
        FilterRegistrationBean<Filter> ecsideExportFilter = new FilterRegistrationBean<>();
        ecsideExportFilter.setFilter(new GZipFilter());
        ecsideExportFilter.setName("ecsideExport");
        ecsideExportFilter.setOrder(2);
        return ecsideExportFilter;
    }

    @Bean
    public FilterRegistrationBean<Filter> druidWebStatFilter() {
        FilterRegistrationBean<Filter> druidWebStatFilter = new FilterRegistrationBean<>();
        druidWebStatFilter.setFilter(new WebStatFilter());
        druidWebStatFilter.setName("druidWebStatFilter");
        druidWebStatFilter.addInitParameter("exclusions", "/css/*,/context/*,/plug-in/*,/plug-in-ui/*,*.js,*.css,*.gif ,*.jpg,*.png,*.ico,*/druid*,/attached/*,/upload/*,/webpage/*,/swagger/*,*.jsp");
        druidWebStatFilter.setOrder(3);
        return druidWebStatFilter;
    }

    @Bean
    public ServletListenerRegistrationBean<EventListener> introspectorCleanupListener() {
        return new ServletListenerRegistrationBean<>(new IntrospectorCleanupListener());
    }

    @Bean
    public ServletListenerRegistrationBean<EventListener> requestContextListener() {
        return new ServletListenerRegistrationBean<>(new RequestContextListener());
    }

    @Bean
    public ServletListenerRegistrationBean<EventListener> initListener() {
        return new ServletListenerRegistrationBean<>(new InitListener());
    }

    @Bean
    public ServletListenerRegistrationBean<EventListener> onlineListener() {
        return new ServletListenerRegistrationBean<>(new OnlineListener());
    }

}
