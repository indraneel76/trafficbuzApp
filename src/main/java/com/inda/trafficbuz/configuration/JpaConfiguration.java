package com.inda.trafficbuz.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages="com.inda.trafficbuz.repository")
public class JpaConfiguration {

	 private static final String CLOUD_SQL_CONNECTION_NAME = System.getenv(
		      "CLOUD_SQL_CONNECTION_NAME");
		  private static final String DB_USER = System.getenv("DB_USER");
		  private static final String DB_PASS = System.getenv("DB_PASS");
		  private static final String DB_NAME = System.getenv("DB_NAME");
	
	 
		@Bean
		@Primary
	    public DataSource dataSource() {
		 
		// [START cloud_sql_mysql_servlet_create]
		    // The configuration object specifies behaviors for the connection pool.
		    HikariConfig config = new HikariConfig();

		    // Configure which instance and what database user to connect with.
		    config.setJdbcUrl(String.format("jdbc:mysql:///%s", DB_NAME));
		    config.setUsername(DB_USER); // e.g. "root", "postgres"
		    config.setPassword(DB_PASS); // e.g. "my-password"

		    // For Java users, the Cloud SQL JDBC Socket Factory can provide authenticated connections.
		    // See https://github.com/GoogleCloudPlatform/cloud-sql-jdbc-socket-factory for details.
		    config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.mysql.SocketFactory");
		    config.addDataSourceProperty("cloudSqlInstance", CLOUD_SQL_CONNECTION_NAME);
		    config.addDataSourceProperty("useSSL", "false");

		    // ... Specify additional connection properties here.
		    // [START_EXCLUDE]

		    // [START cloud_sql_mysql_servlet_limit]
		    // maximumPoolSize limits the total number of concurrent connections this pool will keep. Ideal
		    // values for this setting are highly variable on app design, infrastructure, and database.
		    config.setMaximumPoolSize(5);
		    // minimumIdle is the minimum number of idle connections Hikari maintains in the pool.
		    // Additional connections will be established to meet this value unless the pool is full.
		    config.setMinimumIdle(5);
		    // [END cloud_sql_mysql_servlet_limit]

		    // [START cloud_sql_mysql_servlet_timeout]
		    // setConnectionTimeout is the maximum number of milliseconds to wait for a connection checkout.
		    // Any attempt to retrieve a connection from this pool that exceeds the set limit will throw an
		    // SQLException.
		    config.setConnectionTimeout(10000); // 10 seconds
		    // idleTimeout is the maximum amount of time a connection can sit in the pool. Connections that
		    // sit idle for this many milliseconds are retried if minimumIdle is exceeded.
		    config.setIdleTimeout(600000); // 10 minutes
		    // [END cloud_sql_mysql_servlet_timeout]

		    // [START cloud_sql_mysql_servlet_backoff]
		    // Hikari automatically delays between failed connection attempts, eventually reaching a
		    // maximum delay of `connectionTimeout / 2` between attempts.
		    // [END cloud_sql_mysql_servlet_backoff]

		    // [START cloud_sql_mysql_servlet_lifetime]
		    // maxLifetime is the maximum possible lifetime of a connection in the pool. Connections that
		    // live longer than this many milliseconds will be closed and reestablished between uses. This
		    // value should be several minutes shorter than the database's timeout value to avoid unexpected
		    // terminations.
		    config.setMaxLifetime(1800000); // 30 minutes
		    // [END cloud_sql_mysql_servlet_lifetime]

		    // [END_EXCLUDE]

		    // Initialize the connection pool using the configuration object.
		    DataSource pool = new HikariDataSource(config);
		    // [END cloud_sql_mysql_servlet_create]
		    return pool;

	 }
		
		@Bean
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean()
		{
				
			LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
			emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);
			emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
			emf.setPackagesToScan("com.inda.trafficbuz.entity");
			emf.setDataSource(dataSource());
			Properties additionalProps =  new Properties();
		//	additionalProps.put("hibernate.hbm2ddl.auto", "create");
			additionalProps.put("hibernate.show_sql","true");
			emf.setJpaProperties(additionalProps);
			
			return emf ;
			
		}
		
		@Bean
		public JpaTransactionManager transactionManager()
		{
			return new JpaTransactionManager();
		}


		
	
}
