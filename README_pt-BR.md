*Change language:* [![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/fabramattos/PML_Simulador)<br>
# Back-Testing para mercado financeiro

Ferramenta customizada para testar e simular estratégias de investimentos e trades para mercado financeiro.
Cada simulaçao/estratégia pode ser customizada de acordo com o informado no MANUAL, fornecido junto com a versão compilada:
 - Pontos de entrada, ganho, perda, gerenciamento de risco, exposição máxima, indicadores, etc.

![interface](https://github.com/fabramattos/PML-Simulador/assets/45768087/ca53dda4-c2f0-4d66-abd1-57cb132464ba#vitrinedev)

## Stack
 - NetBeans IDE 12.4
 - Java SDK 16
 - Java Swing (para interface de usuário)
 - Apache POI Lib (para tratamnento de arquivos .xlsx)

## Como usar o programa:
- Dados de entrada -> Importar .xlsx (arquivo Excel)
- Resultados -> Software gera um novo .xlsx

### Como importar os dados para o simulador:
O arquivo Excel que contem a série de dados dos candles do mercado DEVE seguir o formato:
- **6 colunas** , na seguinte ordem:
  - 1a linha, CABEÇALHO: 
    - | Data | abertura | máxima | mínima | fechamento | nome do indicador 
  - Demais linhas:
    - apenas dados

 *Juntamento com o arquivo .jar, você encontrará dois arquivos .xlsx (Excel) com dados do mercado financeiro prontos para serem importados no simulador.*<br>
 *Use os .xlsx como modelo para adaptar/utilizar seus proprios dados*

### Sobre cada estratégia e suas interfaces:
- Para detalhes sobre cada estratégia, gerenciamento de risco, interface geral e mais detalhes: **LEIA O MANUAL FORNECIDO**.
- Com a estratégia basica de compra/venda de acordo com indicadores, basta realizar os calculos e informar os resultados na coluna do indicador que provavelmente a ferramenta atenderá seu uso.
