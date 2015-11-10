## Sample AMQ

Projeto exemplo, utilizando <i> Gradle</i>, para utilizar como base na criação de <b>PRODUCER e CONSUMER</b> do Apache ActiveMQ Apollo.

O projeto contém 3 classes:
* ConfigurationAmq.java 
  - classe que contém configurações e constantes necessária para conectar com o Apollo.

* Producer.java
	- classe que contem código que envia menssagens para o Apollo.

* Consumer.java
	- classe que contem código que recebe menssagens do Apollo.

#### Para rodar o projeto deve-se ter baixado e instalado o gradle e Apache ActiveMQ Apollo.

* https://activemq.apache.org/apollo/download.html

* OBS: pode ser utilizado também o Apollo dentro do Docker (exemplo de uso no link abaixo)
	- https://github.com/pires/docker-apollomq 

Iniciando o Apollo :
 - para criar uma instancia do apollo ir na pasta <b>/apache-apollo-1.7.1/bin/</b>
 - executar o comando : <b> apollo create mybroker </b>
 - nessa mesma pasta será criado a pasta <b> mybroker </b>
 - dentro da pasta <b> mybroker/bin </b> executar o comando : <b> apollo-broker run </b>
 - Apollo deve estar disponível na url : <b> http://localhost:61680 </b>
 - Usuario: admin 
 - Password: password

Após gradle instalado, baixar o código e por linha de comando executar: 

* <b>gradle clean build</b> 

Após ter sido executado o build pode ser executado o metodo <b>main</b> dentro da classe Producer  e main dentro da Classe Consumer.
