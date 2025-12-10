#!/bin/zsh
./WebApp/deploy.sh || echo "Erro Ao executar o deploy do WebApp"

./ArduinoApp/deploy.sh || echo "Erro Ao executar o deploy do ArduinoApp"

USER_NAME=$(whoami)

cd /home/joao-vitor/git/Projeto-Expoeetepa-Irrigadores-Automaticos/docker-data/

case "$USER_NAME" in
  "joao-vitor")
cd /home/joao-vitor/git/Projeto-Expoeetepa-Irrigadores-Automaticos/docker-data/
    ;;
  "admin")
cd /home/admin/git/Projeto-Expoeetepa-Irrigadores-Automaticos/docker-data/
    ;;
esac


sudo docker compose down

sleep 3

sudo docker compose up -d
