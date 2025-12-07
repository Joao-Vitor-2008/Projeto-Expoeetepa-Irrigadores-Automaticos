package br.com.eetepa.Estacao;

import br.com.eetepa.ConexaoBanco.EstacaoDAO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/estacao")
public class EstacaoServlet extends HttpServlet {
  private EstacaoManager manager = new EstacaoManager();
  private Gson gson = new Gson();
  private EstacaoDAO estacaoDAO = new EstacaoDAO();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("application/json");
    resp.getWriter().write("{\"status\":\"use POST para enviar dados\"}");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    BufferedReader reader = req.getReader();

    StringBuilder sb = new StringBuilder();
    String line;

    while ((line = reader.readLine()) != null) {
      sb.append(line);
    }

    Estacao estacao = gson.fromJson(sb.toString(), Estacao.class);

    resp.setContentType("application/json");
    resp.getWriter().write(sb.toString());

    try {
      estacaoDAO.inserirDadosEstacao(estacao);
    } catch (SQLException e) {
      System.err.println(e.getErrorCode());
    }

    manager.updateEstacao(estacao);
  }
}
