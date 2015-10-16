package com.rao.playground.utils.spring;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by shuaibrao on 16/10/2015.
 */
public class AbstractSpringAwareServlet extends HttpServlet {

    private static final long serialVersionUID = 7227294143265669973L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // Use this if Spring intiliased throung web.xml etc
//        AutowireCapableBeanFactory applicationContext = ((ApplicationContext) getServletContext().getAttribute("applicationContext")).getAutowireCapableBeanFactory();
////        The Following Line does the Magic
//        applicationContext.autowireBean(this);

        // The Following does the magic, Autowiring the spring beans into the Annotated classes initied through Spring

        // Use this to wire in beans from some other app context
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();

        if (applicationContext != null) {
            AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
            bpp.setBeanFactory(applicationContext.getAutowireCapableBeanFactory());
            bpp.processInjection(this);
        } else {
            throw new ServletException("Spring Application Context is Null, How do you expect me to Autowire Spring Beans into the Servlet");
        }
    }
}
