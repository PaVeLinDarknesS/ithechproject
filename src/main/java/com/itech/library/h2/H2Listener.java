package com.itech.library.h2;

import org.h2.tools.Server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class H2Listener implements ServletContextListener {

    private Server server;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            server = Server.createWebServer();
            server.start();
        } catch (SQLException ex) {
            ex.printStackTrace();
            server.stop();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        server.stop();
    }
}
