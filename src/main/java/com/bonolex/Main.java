package com.bonolex;

import com.bonolex.onlineshop.jdbc.dao.ProductDAOImpl;
import com.bonolex.onlineshop.jdbc.dao.UserDAO;
import com.bonolex.onlineshop.jdbc.dao.UserDAOImpl;
import com.bonolex.onlineshop.service.DefaultProductServiceImpl;
import com.bonolex.onlineshop.service.DefaultSecurityService;
import com.bonolex.onlineshop.service.DefaultUserServiceImpl;
import com.bonolex.onlineshop.web.auth.AuthFilter;
import com.bonolex.onlineshop.web.servlets.login.LoginServlet;
import com.bonolex.onlineshop.web.servlets.login.LogoutServlet;
import com.bonolex.onlineshop.web.servlets.product.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.postgresql.ds.PGSimpleDataSource;

import javax.servlet.DispatcherType;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.util.EnumSet;
import java.util.Properties;

/**
 * Created by OBondar on 10.02.2019.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(fileInputStream);
        }
        //Configure datasource from properties
        DataSource dataSource = createDataSource(properties);

        //DAO
        UserDAO userDAO = new UserDAOImpl(dataSource);
        ProductDAOImpl productDAO = new ProductDAOImpl(dataSource);

        //Services
        DefaultUserServiceImpl defaultUserService = new DefaultUserServiceImpl();
        defaultUserService.setUserDAO(userDAO);
        DefaultProductServiceImpl defaultProductService = new DefaultProductServiceImpl();
        defaultProductService.setProductDAO(productDAO);
        DefaultSecurityService defaultSecurityService = new DefaultSecurityService(defaultUserService);

        //Servlets
        ViewProductServlet viewProductServlet = new ViewProductServlet(defaultProductService);
        AddProductServlet addProductServlet = new AddProductServlet(defaultProductService);
        EditProductServlet editProductServlet = new EditProductServlet(defaultProductService);
        DeleteProductServlet deleteProductServlet = new DeleteProductServlet(defaultProductService);

        LoginServlet loginServlet = new LoginServlet(defaultSecurityService);
        LogoutServlet logoutServlet = new LogoutServlet(defaultSecurityService);
        StaticSourcesServlet staticSourcesServlet=new StaticSourcesServlet();

        //Mappings
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(loginServlet), "/login");
        context.addServlet(new ServletHolder(viewProductServlet), "/products");
        context.addServlet(new ServletHolder(addProductServlet), "/product/add");
        context.addServlet(new ServletHolder(editProductServlet), "/product/edit/*");
        context.addServlet(new ServletHolder(deleteProductServlet), "/product/delete/*");
        context.addServlet(new ServletHolder(logoutServlet), "/logout");
        context.addServlet(new ServletHolder(staticSourcesServlet), "/styles");


        context.addFilter(new FilterHolder(new AuthFilter(defaultSecurityService)), "/product/*", EnumSet.of(DispatcherType.REQUEST));

        Server server = new Server(3000);
        server.setHandler(context);

        server.start();
    }

    private static DataSource createDataSource(Properties properties) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser(properties.getProperty("jdbc.username"));
        dataSource.setPassword(properties.getProperty("jdbc.password"));
        dataSource.setUrl(properties.getProperty("jdbc.url"));
        return dataSource;
    }
}
