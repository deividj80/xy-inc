# xy-inc

Serviço que auxilia as pessoas na localização do ponto de interesse (POIs)

## Intruções para Teste

Criar banco de dados, executa o script de criação do banco.(create-db.sql)

Modifica as configurações xy-inc/src/main/resources/application.properties para fazer conexão com banco de dados.

Foi feito um arquivo de log. (repository-gps.log)

##Executa os comandos
mvn clean install

mvn package

mvn spring-boot:run ou java -jar target/repository-gps.jar

##Para testar os serviços use um sistema que faça chamadas post.

Criar POIs - Serviço POST:

url: http://localhost:8080/poi/create

Body: name_poi: Nome-Poi, x: Número,y:Número

  
Listar todos POIs - Serviço POST:

url: http://localhost:8080/poi/list

    
Listar Proximidades dos POIs - Serviço POST:

url: http://localhost:8080/poi/proximity

Body: x:Número, y: Número,prox:Número
  
