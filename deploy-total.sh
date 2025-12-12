#!/bin/zsh
set -e

# Executar deploys individuais
./WebApp/deploy.sh || echo "Erro ao executar o deploy do WebApp"
./ArduinoApp/deploy.sh || echo "Erro ao executar o deploy do ArduinoApp"

USER_NAME=$(whoami)

# Selecionar diretório do docker conforme o usuário
case "$USER_NAME" in
  "joao-vitor")
    DOCKER_DIR="/home/joao-vitor/git/Projeto-Expoeetepa-Irrigadores-Automaticos/docker-data"
    ;;
  "admin")
    DOCKER_DIR="/home/admin/git/Projeto-Expoeetepa-Irrigadores-Automaticos/docker-data"
    ;;
  *)
    echo "Usuário não reconhecido: $USER_NAME"
    exit 1
    ;;
esac

cd "$DOCKER_DIR"

# Reiniciar containers
echo "Reiniciando containers..."
sudo docker compose down
sleep 2
sudo docker compose up -d --build

echo "Ambiente Docker reiniciado com sucesso!"
