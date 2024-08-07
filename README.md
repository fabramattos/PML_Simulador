*Mude o idioma:* [![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](https://github.com/fabramattos/PML_Simulador/blob/master/README_pt-BR.md)<br>
# Back-Testing for Financial Market

A customized tool to test and simulate investment and trading strategies for the financial market.
Each simulation/strategy can be customized as specified in the MANUAL provided with the compiled version:
- Entry points, gain, loss, risk management, maximum exposure, indicators, etc.

![interface](https://github.com/fabramattos/PML-Simulador/assets/45768087/ca53dda4-c2f0-4d66-abd1-57cb132464ba#vitrinedev)

## Stack:
- NetBeans IDE 12.4
- Java SDK 16
- Java Swing (for user interface)
- Apache POI Lib (for handling .xlsx files)

## How to use the program:
- Input Data -> Import .xlsx (Excel file)
- Results -> Software generates a new .xlsx

### How to import data into the simulator:
The Excel file containing the series of market candle data MUST follow the format:
- 6 columns, in the following order:
  - 1st line, HEADER:
    - | Date | open | high | low | close | indicator name
  - Remaining lines:
    - data only
  
*Along with the .jar file, you will find two .xlsx (Excel) files with financial market data ready to be imported into the simulator.* <br>
*Use the .xlsx files as a template to adapt/use your own data.*

### About each strategy and its interfaces:
- For details on each strategy, risk management, general interface, and more details: READ THE PROVIDED MANUAL.
- With the basic buy/sell strategy according to indicators, simply perform the calculations and provide the results in the indicator column, and the tool will likely meet your needs.
