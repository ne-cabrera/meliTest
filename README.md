# Test mercadolibre
#### La API esta desplegada en http://meli-test.sa-east-1.elasticbeanstalk.com/stats

Para ejecutar la api localmente clone el repositorio y ejecute ```./gradlew build```, esto generara un jar el cual puede ejecutar 
con ```java -jar build/libs/meli-test-0.0.1.SNAPSHOT.jar```.
Es necesario contar con las siguientes dependencias:
* Java 11
* MySQL
* Gradle 5.6.4

Además, es necesario definir las siguientes variables de entorno:
* RDS_DB_NAME - nombre de la base de datos
* MYSQL_USER - usuario de la base de datos
* MYSQL_PASSWORD - contrasena de la base de datos
