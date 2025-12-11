package br.com.eetepa.Irrigador;

import java.util.Map;

import br.com.eetepa.ClassesAuxiliares.Store;
import br.com.eetepa.Estacao.EstacaoManager;

public class IrrigadorManager {

  private Store store = Store.getStoreInstance();
  private EstacaoManager manager = new EstacaoManager();

  public String getComando(Irrigador irrigador) {
    if (irrigador.getUmidadeSolo() < irrigador.getLimiarUmidade()
        && manager.getEstacao(irrigador.getNome_estacao()).getTemperaturaAr() > 30) {
      irrigador.setAcaoAtual("ligado");
      return "ligar";
    } else if (irrigador.getUmidadeSolo() < irrigador.getLimiarUmidade()
        && manager.getEstacao(irrigador.getNome_estacao()).getTemperaturaAr() > 28) {
      irrigador.setAcaoAtual("ligado");
      return "ligar";
    } else {
      return "desligar";
    }
  }

  public boolean compararComando(String comando) {
    return comando.equals("ligar");
  }

  public void updateIrrigador(Irrigador irrigador) {
    store.getIrrigadores().put(irrigador.getPlantio(), irrigador);
  }

  public Irrigador getIrrigador(String plantio) {
    return store.getIrrigadores().get(plantio);
  }

  public Map<String, Irrigador> getTodos() {
    return store.getIrrigadores();
  }

  public boolean existeIrrigador(String id) {
    return store.getIrrigadores().containsKey(id);
  }

}
