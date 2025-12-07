package br.com.eetepa.ConexaoBanco;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ListarDadosDAO {

  ZoneId zonaBr = ZoneId.of("America/Santarem");

  public Map<String, Object> listarDados() throws SQLException {

    List<Map<String, Object>> estacoes = new ArrayList<>();
    List<Map<String, Object>> irrigadores = new ArrayList<>();

    String sqlEstacoes = "SELECT * FROM estacoes;";
    String sqlIrrigadores = "SELECT * FROM irrigadores;";

    // Estacoes
    try (
        Connection conn = new ConexaoMysql().getConnection();
        PreparedStatement sttm = conn.prepareStatement(sqlEstacoes);
        ResultSet result = sttm.executeQuery()) {

      while (result.next()) {
        Map<String, Object> item = new HashMap<>();
        item.put("id", result.getString("id"));
        item.put("nome", result.getString("nome"));
        Timestamp ts = result.getTimestamp("data_hora");
        String data_hora = ts == null ? null
            : ts.toInstant().atZone(zonaBr).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        item.put("data_hora", data_hora);
        estacoes.add(item);
      }

    } catch (Exception e) {
      throw new SQLException("Erro ao buscar estacoes: " + e.getMessage());
    }

    // ------ IRRIGADORES ------
    try (
        Connection conn = new ConexaoMysql().getConnection();
        PreparedStatement sttm = conn.prepareStatement(sqlIrrigadores);
        ResultSet result = sttm.executeQuery()) {

      while (result.next()) {
        Map<String, Object> item = new HashMap<>();
        item.put("id", result.getInt("id"));
        item.put("plantio", result.getString("plantio"));
        item.put("id_estacao", result.getString("id_estacao"));
        irrigadores.add(item);
      }

    } catch (Exception e) {
      throw new SQLException("Erro ao buscar irrigadores: " + e.getMessage());
    }

    // JSON FINAL COM OS DOIS
    Map<String, Object> resposta = new HashMap<>();
    resposta.put("estacoes", estacoes);
    resposta.put("irrigadores", irrigadores);

    return resposta;
  }
}
