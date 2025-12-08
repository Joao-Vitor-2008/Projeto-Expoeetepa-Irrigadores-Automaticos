#!/bin/zsh

# Configurações
USER_NAME=$(whoami)
DOCKER_TOMCAT9="/home/joao-vitor/git/Projeto-Expoeetepa-Irrigadores-Automaticos/docker-data/arduino-app/"
WAR_NAME="ArduinoApp.war"

# Entrar no diretório do projeto
 case "$USER_NAME" in
  "joao-vitor")
    PROJETO_DIR="/home/joao-vitor/git/Projeto-Expoeetepa-Irrigadores-Automaticos/ArduinoApp/"
    echo "Olá "$USER_NAME"!"
    cd $PROJETO_DIR
    ;;
  "admin")
    PROJETO_DIR="/home/admin/git/Projeto-Expoeetepa-Irrigadores-Automaticos/ArduinoApp"
    echo "Olá "$USER_NAME"!"
    cd $PROJETO_DIR
    ;;
  *)
    echo "Usuário não reconhecido: $USER_NAME"
    exit 1
    ;;
esac


# Compilar o projeto
mvn -q clean package || {
  echo "Falha na compilação!"
  exit 1
}

# Copiar o .war para o Tomcat
sudo cp "target/$WAR_NAME" "$DOCKER_TOMCAT9/" || {
  echo "Falha ao copiar o WAR!"
  exit 1
}
echo "War copiado com sucesso"

sudo systemctl restart tomcat9 || echo "Erro ao recarregar o tomcat"

sudo systemctl restart mariadb || echo "Erro ao reiniciar o MySQL"

sleep 3
# Testar o codigo automaticamente
curl -X POST http://localhost:8081/ArduinoApp/estacao \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Skymetric",
    "temperaturaAr": 32.5,
    "umidadeAr": 78.9,
    "pressaoAr": 1012,
    "indice_uv": 3
  }'

 curl -X POST http://localhost:8081/ArduinoApp/irrigador \
  -H "Content-Type: application/json" \
  -d '{
    "plantio": "cebolinha",
    "nome_estacao": "Skymetric",
    "umidadeSolo": 40.5,
    "acaoAtual": "desligado",
    "tempoRestante":0,
    "cicloDias": 3,
    "limiarUmidade": 50,
    "comando": "desligar"
  }'

echo ""
echo "Deploy concluído com sucesso!"
