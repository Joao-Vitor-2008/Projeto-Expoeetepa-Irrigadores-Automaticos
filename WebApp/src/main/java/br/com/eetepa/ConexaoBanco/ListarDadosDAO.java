package br.com.eetepa.ConexaoBanco;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListarDadosDAO {

  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

  public Map<String, Object> listarDados() throws SQLException {

    List<Map<String, Object>> estacoes = new ArrayList<>();
    List<Map<String, Object>> irrigadores = new ArrayList<>();

    try (Connection conn = ConexaoMysql.getConnection()) {

      carregarEstacoes(conn, estacoes);
      carregarIrrigadores(conn, irrigadores);

    } catch (SQLException e) {
      throw new SQLException("Erro ao conectar/listar dados: " + e.getMessage());
    }

    Map<String, Object> resposta = new HashMap<>();
    resposta.put("estacoes", estacoes);
    resposta.put("irrigadores", irrigadores);
    return resposta;
  }

  private void carregarEstacoes(Connection conn, List<Map<String, Object>> estacoes) throws SQLException {

    final String sql = "SELECT * FROM estacoes ORDER BY data_hora DESC LIMIT 10;";

    try (PreparedStatement sttm = conn.prepareStatement(sql);
        ResultSet rs = sttm.executeQuery()) {

      while (rs.next()) {

        Map<String, Object> item = new HashMap<>();

        item.put("id", rs.getString("id"));
        item.put("nome", rs.getString("nome"));

        Timestamp ts = rs.getTimestamp("data_hora");
        String dataFormatada = (ts == null)
            ? null
            : ts.toLocalDateTime().format(formatter);

        item.put("data_hora", dataFormatada);
        estacoes.add(item);
      }

    } catch (Exception e) {
      throw new SQLException("Erro ao carregar estacoes: " + e.getMessage());
    }
  }

  private void carregarIrrigadores(Connection conn, List<Map<String, Object>> irrigadores) throws SQLException {

    final String sql = "SELECT * FROM irrigadores ORDER BY id DESC LIMIT 10;";

    try (PreparedStatement sttm = conn.prepareStatement(sql);
        ResultSet rs = sttm.executeQuery()) {

      while (rs.next()) {

        Map<String, Object> item = new HashMap<>();
        item.put("id", rs.getInt("id"));
        item.put("plantio", rs.getString("plantio"));
        item.put("acao_atual", rs.getString("acao_atual"));
        item.put("id_estacao", rs.getString("id_estacao"));

        irrigadores.add(item);
      }

    } catch (Exception e) {
      throw new SQLException("Erro ao carregar irrigadores: " + e.getMessage());
    }
  }
}
