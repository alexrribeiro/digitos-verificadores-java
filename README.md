# 🛂 Algoritmos de Validação

## 🗒️ Breve descrição:
Neste projeto se encontram códigos para validação através de dígito verificador, com a execução de cálculos para esse fim. Ao inserir um número de RG ou de agência/conta com o dígito, o algoritmo verificará se o número informado é válido ou não.

## 🛠️ Ferramentas utilizadas:
* ☕ JDK 20 - Java
* 💻 Intellij IDEA Community

## 🚥 Status atual e futuras ideias:
* 🟡 Em andamento
* 🟩 Funcionando: Validação de RG, Contas Bancárias: Itaú, Banco do Brasil e Bradesco.
* 🟨 Futuramente: Validação de contas bancárias de outros bancos e outros documentos.

## 🔢 Cálculos de validação:
* 👥 RG: Da direita para a esquerda, multiplicamos os números por 2, 3, 4 e assim por diante. Ao final somamos os resultados das multiplicações, multiplicamos por 100 e dividimos por 11. O resto da divisão é o dígito verificador. Se o DV encontrado for 10, substituímos por X.
* :bank: Conta Itaú: As contas do Itaú usam o módulo 10. Juntamos o número da agência e da conta (que são validados no mesmo cálculo) e, da direita para a esquerda, multiplicamos as posições ímpares por 2 e as pares por 1. Caso o resultado de uma das multiplicações seja maior ou igual a 10, somamos os seus dígitos (exemplo: 14: 1 + 4 = 5). Somamos todos os resultados e dividimos por 10. O dígito verificador será o resultado da subtração de 10 pelo módulo encontrado.
* :bank: Conta Banco do Brasil: Da direita para a esquerda, multiplicamos os números por 2, 3, 4 e assim por diante. Ao final, somamos os resultados das multiplicações e dividimos por 11. O resultado da subtração de 11 pelo número encontrado será o dígito verificador. Se o resultado for 10, o dígito é X, se for 11 o dígito é 0 (zero).
* :bank: Conta Bradesco: é semelhante ao do Banco do Brasil, mas em contas com mais de 6 números, voltamos a multiplicar por 2 após a posição de peso 7. Outra diferença é que no lugar de X, usamos P para contas (agências usam 0).

## 👷‍♂️ Autor do projeto:
[Alex Ramos Ribeiro](https://alexrribeiro.github.io/curriculum)
