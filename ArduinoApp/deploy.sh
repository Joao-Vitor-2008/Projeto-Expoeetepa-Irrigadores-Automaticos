#!/bin/zsh
set -e  # Encerra o script se qualquer comando falhar

# Configurações
USER_NAME=$(whoami)
WAR_NAME="ArduinoApp.war"

# Diretórios por usuário
case "$USER_NAME" in
  "joao-vitor")
    BASE_DIR="/home/joao-vitor/git/Projeto-Expoeetepa-Irrigadores-Automaticos"
    ;;
  "admin")
    BASE_DIR="/home/admin/git/Projeto-Expoeetepa-Irrigadores-Automaticos"
    ;;
  *)
    echo "Usuário não reconhecido: $USER_NAME"
    exit 1
    ;;
esac

DOCKER_TOMCAT9="$BASE_DIR/docker-data/arduino-app"
PROJETO_DIR="$BASE_DIR/ArduinoApp"

echo "Olá $USER_NAME!"
cd "$PROJETO_DIR"

# Compilar o projeto
echo "Compilando o projeto..."
if ! mvn -q clean package; then
  echo "Falha na compilação!"
  exit 1
fi

# Copiar o .war para o Tomcat
echo "Copiando WAR..."
sudo cp "target/$WAR_NAME" "$DOCKER_TOMCAT9/" || {
  echo "Falha ao copiar o WAR!"
  exit 1
}
echo "WAR copiado com sucesso."

# Reiniciar serviços
echo "Reiniciando serviços..."
sudo systemctl restart tomcat9 || echo "Erro ao reiniciar o Tomcat"
sudo systemctl restart mariadb || echo "Erro ao reiniciar o MariaDB"

sleep 3

# Testar endpoints
echo "Testando API..."

curl -s -X POST http://10.0.0.101:8081/ArduinoApp/estacao \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Skymetric",
    "temperaturaAr": 32.5,
    "umidadeAr": 78.9,
    "pressaoAr": 1012
  }'
echo ""

curl -s -X POST http://10.0.0.101:8081/ArduinoApp/irrigador \
  -H "Content-Type: application/json" \
  -d '{
    "plantio": "cebolinha",
    "nome_estacao": "Skymetric",
    "umidadeSolo": 40.5,
    "acaoAtual": "desligado",
    "tempoRestante": 0,
    "cicloDias": 3,
    "limiarUmidade": 50
  }'
echo ""

echo "Deploy concluído com sucesso!"
