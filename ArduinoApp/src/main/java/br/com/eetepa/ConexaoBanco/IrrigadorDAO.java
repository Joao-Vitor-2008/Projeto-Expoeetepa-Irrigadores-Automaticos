package br.com.eetepa.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.com.eetepa.Irrigador.Irrigador;
import br.com.eetepa.Irrigador.IrrigadorManager;
import br.com.eetepa.Estacao.EstacaoManager;

public class IrrigadorDAO {

  private IrrigadorManager irrigadorManager = new IrrigadorManager();
  private EstacaoManager manager = new EstacaoManager();

  public void inserirDadosIrrigador(Irrigador irrigador) throws SQLException {
    String sql = "INSERT INTO irrigadores (id_estacao, plantio, umidade_solo, acao_atual, tempo_restante, ciclo_dias, limiar_umidade, nome_estacao) VALUES (?,?,?,?,?,?,?,?);";

    try (Connection conn = new ConexaoMysql().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);) {

      stmt.setString(1, manager.getEstacao(irrigador.getNome_estacao()).getId());
      stmt.setString(2, irrigador.getPlantio());
      stmt.setDouble(3, irrigador.getUmidadeSolo());
      stmt.setString(4,
          irrigadorManager.compararComando(irrigadorManager.getComando(irrigador)) ? "ligado" : "desligado");
      stmt.setInt(5, irrigador.getTempoRestante());
      stmt.setInt(6, irrigador.getCicloDias());
      stmt.setDouble(7, irrigador.getLimiarUmidade());
      stmt.setString(8, irrigador.getNome_estacao());

      stmt.executeUpdate();

    } catch (SQLException e) {
      throw new RuntimeException("Erro ao inserir os dados do irrigador: " + irrigador.getPlantio(), e);
    }
  }
}
