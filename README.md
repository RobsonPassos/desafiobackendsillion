# desafiobackendsillion
Criando projeto desafio back end sillion.

Requisitos:
Este é um projeto de avaliação que busca identificar a compatibilidade da codificação com a equipe. Tente codificar o mais limpo e claro possível, levando em consideração a reutilização, neste teste iremos avaliar os pontos adicionais, testes unitários e boas práticas.

Meta

Crie um projeto Maven Java com a seguinte funcionalidade:
1.	Insira um URL válido no console
2.	Insira no console uma frase composta por N palavras
3.	Faça uma solicitação para o referido URL
4.	Mostrar o número de vezes que a frase digitada é repetida no console
5.	Mostrar no console o número de vezes que cada palavra da frase é repetida entrou.
6.	[OPCIONAL] Dockerize o aplicativo criando uma imagem docker com o código criado.
7.	[OPCIONAL] Execute o referido contêiner em qualquer cluster k8s, ou seja, kind, minicube, gcp, aws etc…


Exemplos:

URL de entrada: https://es.wikipedia.org/wiki/Pir%C3%A1mides_de_Egipto
Frase a procurar: bloques de piedra

Resultados:

“bloques de piedra” ⇒ repete 2 vezes
“bloques” ⇒ repete 7 vezes
“de” ⇒ é repetido 215 vezes
“piedra” ⇒ repete 4 vezes

_______________________________________________________________________________________

Importe o projeto maven para o Intellij.

1-Para criar o imagem para o docker abra o terminal do intellij vá para o diretório onde se encontra o arquivo "Dockerfile" e execute o seguinte comando:

docker build -t desafiobackendsillion:1.0-SNAPSHOT-jar-with-dependencies .

2-Para executar o container no terminal do intellij execute o seguinte comando:

docker run -it desafiobackendsillion:1.0-SNAPSHOT-jar-with-dependencies

Será validado uma URL válida.

Obs.: tem testes unitários cobrindo toda a classe Main.
