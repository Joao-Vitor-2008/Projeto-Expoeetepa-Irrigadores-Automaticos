package br.com.eetepa.Estacao;

import java.sql.Timestamp;
import java.util.UUID;

public class Estacao {

  private String id;
  private String nome;
  private double temperaturaAr;
  private double umidadeAr;
  private int pressaoAr;
  private int indice_uv;
  private Timestamp data_hora = new java.sql.Timestamp(System.currentTimeMillis());

  // Construtor para gerar o id com UUID
  public Estacao() {
    id = UUID.randomUUID().toString().replace("-", "");
  }

  public String getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public double getTemperaturaAr() {
    return temperaturaAr;
  }

  public void setTemperaturaAr(double temperaturaAr) {
    this.temperaturaAr = temperaturaAr;
  }

  public double getUmidadeAr() {
    return umidadeAr;
  }

  public void setUmidadeAr(double umidadeAr) {
    this.umidadeAr = umidadeAr;
  }

  public int getPressaoAr() {
    return pressaoAr;
  }

  public void setPressaoAr(int pressaoAr) {
    this.pressaoAr = pressaoAr;
  }

  public int getIndice_uv() {
    return indice_uv;
  }

  public void setIndice_uv(int indice_uv) {
    this.indice_uv = indice_uv;
  }

  public Timestamp getData_hora() {
    return data_hora;
  }

  public void setData_hora(Timestamp data_hora) {
    this.data_hora = data_hora;
  }
}
