package br.com.eetepa.ConexaoBanco;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import br.com.eetepa.Estacao.Estacao;

public class EstacaoDAO {

  public void inserirDadosEstacao(Estacao estacao) throws SQLException {
    String sql = ("INSERT INTO estacoes (id, data_hora, nome, temperaturaAr, umidadeAr, pressaoAr) VALUES (?,?,?,?,?,?);");

    try (
        Connection conn = ConexaoMysql.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);) {

      stmt.setString(1, estacao.getId());
      stmt.setTimestamp(2, estacao.getData_hora());
      stmt.setString(3, estacao.getNome());
      stmt.setDouble(4, estacao.getTemperaturaAr());
      stmt.setDouble(5, estacao.getUmidadeAr());
      stmt.setInt(6, estacao.getPressaoAr());

      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao inserir dados da estacao: " + estacao.getNome(), e);
    }
  }
}
