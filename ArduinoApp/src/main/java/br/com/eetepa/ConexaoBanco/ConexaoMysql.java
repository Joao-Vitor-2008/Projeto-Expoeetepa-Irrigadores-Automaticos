package br.com.eetepa.ConexaoBanco;

import java.sql.Connection;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConexaoMysql {
  private static final Dotenv dotenv = Dotenv.configure().filename("dados.env").load();

  private static String DRIVER = "com.mysql.cj.jdbc.Driver";
  private static String USER = dotenv.get("usuarioBD");
  private static String PASSWORD = dotenv.get("senhaBD");
  private static String URL = dotenv.get("url");

  private static final HikariDataSource ds;

  static {
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(URL);
    config.setUsername(USER);
    config.setPassword(PASSWORD);
    config.setDriverClassName(DRIVER);
    config.setMaximumPoolSize(20);
    config.setMinimumIdle(3);
    config.setIdleTimeout(30000);
    config.setMaxLifetime(1800000);
    config.setConnectionTimeout(8000);
    config.setConnectionTestQuery("SELECT 1");
    ds = new HikariDataSource(config);
  }

  private ConexaoMysql() {
  }

  public static Connection getConnection() throws SQLException {
    return ds.getConnection();
  }
}
