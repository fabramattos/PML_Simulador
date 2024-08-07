*Mude o idioma:* [![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](https://github.com/fabramattos/PML_Simulador/blob/master/README_pt-BR.md)<br>
# Stock Market Back-Testing Tool V5.3.0

Custom back-testing tool to run simulations as if trading in the stock market.
Each simulation/strategy can be customized acording to the MANUAL, provided along with the compiled version:
Entry points, gain, loss, risk management, max position, indicators, etc.
 - Data input -> Import from Excel File
 - Results Output -> Save to a new Excel File

| :placard: Vitrine.Dev | [Minha Vitrine](https://cursos.alura.com.br/vitrinedev/fabramattos) |
| -------------  | --- |
| :sparkles: Nome        | **Stock Market Back-Testing Tool**
| :label: Tecnologias | Java, Java Swing, Netbeans, Apache POI
| :rocket: URL         | [Latest Version](https://github.com/fabramattos/PML-Simulador/releases)

<!-- Inserir imagem com a #vitrinedev ao final do link -->
![interface](https://github.com/fabramattos/PML-Simulador/assets/45768087/ca53dda4-c2f0-4d66-abd1-57cb132464ba#vitrinedev)

## Details

Project made with:
 - NetBeans IDE 12.4
 - Java SDK 16
 - Java Swing (for user interface)
 - Apache POI Lib (for excel files)
 
 ## Configuration
 ### Importing Data Into the Simulator:
 The Excel file containing the candle data series information MUST have **6 collums** in the following order:

 - **1st row, titles:**  
   - | Date | opening | maximum | minimun | closing | indicator
   - The last collum's title (here named as "indicator") will have it's name imported into the code
- **Following rows:**
  - data only

 *Following the .jar file, you will find two excel files ready to be imported into the simulator.*

 
### About each strategy and interface options:
- **For detailed info about each strategy, risk management, simulator interface and further details: READ THE PROVIDED MANUAL!**
