package jdbc;

import java.io.IOException;
import java.io.StringReader;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInitListener implements ServletContextListener {

   @Override
   public void contextInitialized(ServletContextEvent sce) {
      String pool_Config = 
            sce.getServletContext().getInitParameter("pool_Config");
      Properties prop = new Properties();
      try {
         prop.load(new StringReader(pool_Config));
      } catch (IOException e) {
         throw new RuntimeException("config load fail", e);
      }
      //JDBC 드라이버 로드, 커넥션 풀 초기화
      loadJDBCDriver(prop);
      initConnectionPool(prop);
   }

   private void loadJDBCDriver(Properties prop) {
      String driver_Class = prop.getProperty("jdbc_Driver");
      try {
         Class.forName(driver_Class);
      } catch (ClassNotFoundException ex) {
         throw new RuntimeException("fail to load JDBC Driver", ex);
      }
   }

   private void initConnectionPool(Properties prop) {
      try {
         String jdbc_Url = prop.getProperty("jdbc_Url");
         String username = prop.getProperty("db_User");
         String pw = prop.getProperty("db_Pass");

         ConnectionFactory conn_Factory = 
               new DriverManagerConnectionFactory(jdbc_Url, username, pw);

         PoolableConnectionFactory poolable_Conn_Factory = 
               new PoolableConnectionFactory(conn_Factory, null);
         String validation_Query = prop.getProperty("validation_Query");
         if (validation_Query != null && !validation_Query.isEmpty()) {
            poolable_Conn_Factory.setValidationQuery(validation_Query);
         }
         GenericObjectPoolConfig pool_Config = new GenericObjectPoolConfig();
         pool_Config.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
         pool_Config.setTestWhileIdle(true);
         int minIdle = getIntProperty(prop, "min_Idle", 5);
         pool_Config.setMinIdle(minIdle);
         int maxTotal = getIntProperty(prop, "max_Total", 50);
         pool_Config.setMaxTotal(maxTotal);

         GenericObjectPool<PoolableConnection> connection_Pool = 
               new GenericObjectPool<>(poolable_Conn_Factory, pool_Config);
         poolable_Conn_Factory.setPool(connection_Pool);
         
         //제공된 풀 이름(inventory)으로 커넥션 풀 등록
         Class.forName("org.apache.commons.dbcp2.PoolingDriver");
         PoolingDriver driver = (PoolingDriver)
            DriverManager.getDriver("jdbc:apache:commons:dbcp:");
         String pool_Name = prop.getProperty("pool_Name");
         driver.registerPool(pool_Name, connection_Pool);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   private int getIntProperty(Properties prop, String prop_Name, int default_Value) {
      String value = prop.getProperty(prop_Name);
      if (value == null) return default_Value;
      return Integer.parseInt(value);
   }

   @Override
   public void contextDestroyed(ServletContextEvent sce) {
   }

}
