package com.step.name.config;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static java.lang.System.getProperties;

@WebListener
public class ContextInitializer implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger(ContextInitializer.class);

    private static final String PATH = "/dataBase/dataBase.properties";

    private static final Properties PROPERTIES = getProperties();


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try (Connection connection = ConnectCreator.getInit()) {
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    private static Properties getProperties() {
        Properties temp = new Properties();
        try (InputStream inp = ContextInitializer.class.getResourceAsStream(PATH)) {
            if (inp != null) {
                temp.load(inp);
            }
        } catch (IOException e) {
            LOGGER.error("IOException at PropertiesLoader at getProperties()" + e);
        }
        return temp;
    }

    public static String getStringProperties(String str) {
        return PROPERTIES.getProperty(str);
    }


}