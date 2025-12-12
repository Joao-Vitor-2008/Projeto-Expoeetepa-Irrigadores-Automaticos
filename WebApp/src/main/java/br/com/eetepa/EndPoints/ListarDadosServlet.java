package br.com.eetepa.EndPoints;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;

import br.com.eetepa.ConexaoBanco.ListarDadosDAO;

@WebServlet("/ListarDados")
public class ListarDadosServlet extends HttpServlet {

  ListarDadosDAO listarDadosDAO = new ListarDadosDAO();
  Gson gson = new Gson();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json;charset=UTF-8");

    try (PrintWriter outWriter = response.getWriter()) {
      String json = gson.toJson(listarDadosDAO.listarDados());
      outWriter.write(json);

    } catch (Exception e) {
      response.setStatus(500);

      String jsonErro = gson.toJson(e.getMessage());
      response.getWriter().write(jsonErro);
    }
  }
}
