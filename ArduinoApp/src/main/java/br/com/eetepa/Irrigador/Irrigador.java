package br.com.eetepa.Irrigador;

public class Irrigador {
  private String plantio;
  private String nome_estacao;
  private double umidadeSolo;
  private String acaoAtual;
  private int tempoRestante;
  private int cicloDias;
  private double limiarUmidade;
  private String comando;

  public String getPlantio() {
    return plantio;
  }

  public void setPlantio(String plantio) {
    this.plantio = plantio;
  }

  public String getNome_estacao() {
    return nome_estacao;
  }

  public void setNome_estacao(String nome_estacao) {
    this.nome_estacao = nome_estacao;
  }

  public double getUmidadeSolo() {
    return umidadeSolo;
  }

  public void setUmidadeSolo(double umidadeSolo) {
    this.umidadeSolo = umidadeSolo;
  }

  public String getAcaoAtual() {
    return acaoAtual;
  }

  public void setAcaoAtual(String acaoAtual) {
    this.acaoAtual = acaoAtual;
  }

  public int getTempoRestante() {
    return tempoRestante;
  }

  public void setTempoRestante(int tempoRestante) {
    this.tempoRestante = tempoRestante;
  }

  public int getCicloDias() {
    return cicloDias;
  }

  public void setCicloDias(int cicloDias) {
    this.cicloDias = cicloDias;
  }

  public double getLimiarUmidade() {
    return limiarUmidade;
  }

  public void setLimiarUmidade(double limiarUmidade) {
    this.limiarUmidade = limiarUmidade;
  }

  public String getComando() {
    return comando;
  }

  public void setComando(String comando) {
    this.comando = comando;
  }
}
