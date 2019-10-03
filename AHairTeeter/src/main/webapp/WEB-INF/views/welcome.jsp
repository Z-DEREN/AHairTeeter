<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<h1>Spring Boot Web JSP Example</h1>
	<h2>${message}</h2>
	<h2>${hello}</h2>

</body>
</html>

<!-- <?xml version="1.0" encoding="UTF-8"?> 
<web-app xmlns="http://java.sun.com/xml/ns/javaee" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
                      http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" 
         version="2.5"> 
    <context-param> 
        <param-name>contextConfigLocation</param-name> 
        <param-value>com.robbie.SpringBootTraditionalApplication</param-value> 
    </context-param> 
    <listener> 
        <listener-class>org.springframework.boot.legacy.context.web.SpringBootContextLoaderListener</listener-class> 
    </listener> 
    <servlet> 
        <servlet-name>appServlet</servlet-name> 
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class> 
        <init-param> 
            <param-name>contextAttribute</param-name> 
            <param-value>org.springframework.web.context.WebApplicationContext.ROOT</param-value> 
        </init-param> 
        <load-on-startup>1</load-on-startup> 
    </servlet> 
    <servlet-mapping> 
        <servlet-name>appServlet</servlet-name> 
        <url-pattern>/</url-pattern> 
    </servlet-mapping> 
</web-app>  -->
