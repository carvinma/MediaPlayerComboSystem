package com.tcd.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;


import com.tcd.common.cache.CacheUtils;
import com.tcd.common.utils.spring.SpringContextHolder;

/**
 * 系统启动监听器
 * @author 郑卫东
 *
 */
public class StartupListener extends ContextLoaderListener implements
		ServletContextListener { 

	private static final Log log = LogFactory.getLog(StartupListener.class);

	public void contextInitialized(ServletContextEvent event) {
		log.info("initializing context..."); 

		ServletContext context = event.getServletContext();

		ApplicationContext ctx = (ApplicationContext) context
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

		//ApplicationContextUtil.setApplicationContext(ctx);
		
		new SpringContextHolder().setApplicationContext(ctx);
		
		CacheUtils.init();
		

	}

}
