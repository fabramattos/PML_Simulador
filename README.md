# Simulador_v5.3.0: 
## Stock Market Back-Testing Tool

Project made with:
 - NetBeans IDE 12.4
 - Java SDK 20
 - Java Swing (for user interface)
 - Apache POI Lib (for excel files)
 
 ## Importing Data Into the Simulator:
 The Excel file containing the candle information should have the following format:
### Collums:
6 collums in the following order:
 - Date
 - opening
 - maximum
 - minimun
 - closing
 - indicator
### 1st row
 - Titles only. The last collum title, relating to the 'indicator' will be imported into the code
 ### every other row:
 - Candle data.

## How to run the application
```sh
mvn clean package -DskipTests
java -jar target/Simulador-5.3.0.jar
```
