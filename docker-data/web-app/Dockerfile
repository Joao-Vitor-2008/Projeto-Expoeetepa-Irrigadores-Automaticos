FROM tomcat:9.0

# Remove o ROOT padrão do Tomcat
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copia seu WebApp
COPY WebApp.war /usr/local/tomcat/webapps/WebApp.war

# Copia o ROOT que faz o redirect para WebApp/
COPY ROOT /usr/local/tomcat/webapps/ROOT

EXPOSE 8080
