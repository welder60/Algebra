# Algebra
Aplicação para resolver fórmulas matemáticas simples
![image](https://github.com/user-attachments/assets/c5a8445d-108d-4cc8-b248-c632d5530eab)

---
Este projeto em Java permite:
- Criar expressões matemáticas programaticamente ou via texto.
- Avaliar valores numéricos.
- Renderizar expressões usando LaTeX.
- Manipular expressões graficamente.

Agora o sistema está mais completo, incluindo:
- Multiplicação (`Multiplicacao.java`)
- Avaliação de variáveis (`Valor.java`, `Variavel.java`)
- Interface gráfica (`JanelaPrincipal.java`) para visualizar e editar expressões.

## Como funciona

- As expressões são objetos que podem ser combinados: soma, multiplicação, potência, divisão.
- `Parser` transforma texto em árvore de objetos.
- `JanelaPrincipal` mostra a expressão graficamente e permite interações.
- `PainelLatex` desenha usando a biblioteca [JLaTeXMath](http://forge.scilab.org/index.php/p/jlatexmath/).

Ao abrir a aplicação:
- Uma expressão aleatória é gerada.
- Você pode clicar em partes da expressão para selecioná-las.
- Pode digitar uma nova expressão para substituir uma parte da atual.

## Pré-requisitos

- Java 8 ou superior.
- Biblioteca JLaTeXMath adicionada ao projeto.

## Como executar

1. No Eclipse, crie um projeto Java.
2. Importe todos os `.java` que você me mandou (mantenha os pacotes `expressao` e `swing`).
3. Adicione o jar da biblioteca JLaTeXMath (`jlatexmath.jar`) no Build Path.
4. Rode a classe `expressao.Main`.

## Estrutura

**Pacote `expressao`:**
- `Expressao`: classe abstrata de expressões matemáticas.
- `Valor`, `Multiplicacao`, `Potencia`, `Adicao`, `Divisao`, `Raiz`: operações matemáticas.
- `Variavel`: representa variáveis.
- `Parser`: interpreta texto.
- `GeradorExpressao`: cria expressões aleatórias para teste.
- `Main`: inicia a aplicação.

**Pacote `swing`:**
- `JanelaPrincipal`: janela principal do programa.
- `PainelLatex`: painel que desenha expressões em LaTeX.
- `PanelExpressao`: campo de entrada de texto para editar expressões.

## Exemplo de uso

```java
Expressao expressao = Parser.parse("(3+2)*(x^2)");
System.out.println(expressao.getValorDecimal(new Variavel('x', 2.0))); // calcula para x=2
System.out.println(expressao.getLatex()); // gera string LaTeX
```

Para iniciar a interface gráfica:

```java
public class Main {
    public static void main(String[] args) {
        new JanelaPrincipal();
    }
}
```

## Observações

- Derivadas ainda estão como `TODO`.
- O sistema avalia a equivalência entre expressões de forma numérica, não simbólica.
- Apenas expressões bem formadas são aceitas no Parser.

## Glossário

| Termo | Inglês |
|:------|:------|
| Expressão matemática | Mathematical expression |
| Potenciação | Exponentiation |
| Parser | Parser |
| Derivada | Derivative |
| Variável | Variable |
| Interface gráfica | Graphical User Interface (GUI) |
| Multiplicação | Multiplication |
