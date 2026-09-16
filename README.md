# Simulador de Filas em Tandem

Simulação e Métodos Analíticos, M6
Integrantes: Leonardo Soares da Silva, Lucas Cid Duarte, Erick Marcondes de Mattos, Bernardo Lykawka;

## Requisitos
Java 17 ou superior (testado com Java 21).

## Como executar
```
cd src
javac *.java
java Main ../tandem.properties
```

## Arquivo de configuração
Os parâmetros ficam em `tandem.properties`, no formato `chave=valor`:

- `primeiraChegada`: tempo de chegada do primeiro cliente
- `semente` e `aleatorios`: semente do gerador e quantidade de números aleatórios usados
- `filaN.servidores` e `filaN.capacidade`
- `filaN.chegada` e `filaN.atendimento`: intervalos no formato `min,max`
- `roteamento.filaX.filaY`: probabilidade de um cliente ir da fila X para a fila Y

## Validação
Os resultados foram comparados com o simulador disponibilizado no Moodle (`simulator.jar`), usando o modelo equivalente em `tandem.yml`.