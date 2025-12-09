#!/bin/zsh

# Configurações
USER_NAME=$(whoami)
DOCKER_TOMCAT9="/home/joao-vitor/git/Projeto-Expoeetepa-Irrigadores-Automaticos/docker-data/web-app"
WAR_NAME="WebApp.war"

case "$USER_NAME" in
  "joao-vitor")
DOCKER_TOMCAT9="/home/joao-vitor/git/Projeto-Expoeetepa-Irrigadores-Automaticos/docker-data/arduino-app/"
    ;;
  "admin")
DOCKER_TOMCAT9="/home/admin/git/Projeto-Expoeetepa-Irrigadores-Automaticos/docker-data/arduino-app/"
    ;;
esac


# Entrar no diretório do projeto
 case "$USER_NAME" in
  "joao-vitor")
    PROJETO_DIR="/home/joao-vitor/git/Projeto-Expoeetepa-Irrigadores-Automaticos/WebApp/"
    echo "Olá "$USER_NAME"!"
    cd $PROJETO_DIR
    ;;
  "admin")
    PROJETO_DIR="/home/admin/git/Projeto-Expoeetepa-Irrigadores-Automaticos/WebApp"
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

# Copiar o .war para o Docker
  sudo cp "target/$WAR_NAME" "$DOCKER_TOMCAT9/" || {
  echo "Falha ao copiar o WAR!"
  exit 1
}
echo "War copiado com sucesso"

sudo systemctl restart tomcat9 || echo "Erro ao recarregar o tomcat"

sudo systemctl restart mariadb || echo "Erro ao reiniciar o MySQL"

sleep 3

echo ""
echo "Deploy concluído com sucesso!"
