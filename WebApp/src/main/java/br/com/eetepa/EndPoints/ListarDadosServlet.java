package br.com.eetepa.EndPoints;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import java.io.IOException;

import br.com.eetepa.ConexaoBanco.ListarDadosDAO;

@WebServlet("/ListarDados")
public class ListarDadosServlet extends HttpServlet {

  ListarDadosDAO listarDadosDAO = new ListarDadosDAO();
  Gson gson = new Gson();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json;charset=UTF-8");

    try {
      String json = gson.toJson(listarDadosDAO.listarDados());
      response.getWriter().write(json);

    } catch (Exception e) {
      response.setStatus(500);
      response.getWriter().write("{\"erro\": \"" + e.getMessage() + "\"}");
    }
  }
}
