package br.com.eetepa.Irrigador;

import br.com.eetepa.ConexaoBanco.IrrigadorDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.BufferedReader;
import java.sql.SQLException;

@WebServlet("/irrigador")
public class IrrigadorServlet extends HttpServlet {
  private IrrigadorDAO irrigadorDAO = new IrrigadorDAO();
  private IrrigadorManager manager = new IrrigadorManager();
  Gson gson = new Gson();

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

    Irrigador irrigador = gson.fromJson(sb.toString(), Irrigador.class);
    manager.updateIrrigador(irrigador);

    try {
      irrigadorDAO.inserirDadosIrrigador(irrigador);
    } catch (SQLException e) {
      System.err.println(e.getErrorCode());
    }

    resp.setContentType("application/json");
    resp.getWriter().write("\n\nStatus : " + manager.getComando(irrigador));
  }
}
