
#!/bin/zsh
set -e

# Configurações
USER_NAME=$(whoami)
WAR_NAME="WebApp.war"

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
PROJETO_DIR="$BASE_DIR/WebApp"

echo "Olá $USER_NAME!"
cd "$PROJETO_DIR"

# Compilar o projeto
echo "Compilando o projeto..."
if ! mvn -q clean package; then
  echo "Falha na compilação!"
  exit 1
fi

# Copiar o .war
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

echo ""
echo "Deploy concluído com sucesso!"

