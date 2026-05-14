<img width="109" height="75" alt="Captura de tela 2026-05-14 011622 (1)" src="https://github.com/user-attachments/assets/356ccb41-b1ea-456c-a3d5-5c224612c52a" />


# LetterShelf - O filme que você quer, antes da pipoca esfriar!

O **LetterShelf** é um motor de recomendação inteligente projetado para conectar você ao seu próximo filme favorito. Diferente de algoritmos genéricos, o LetterShelf prioriza o refinamento técnico, cruzando dados comportamentais e preferências estritas para entregar uma lista ranqueada de alta precisão.

## Componentes da Equipe 

* **CAIQUE RAMOS DA SILVA**
* **CARLOS EDUARDO MARTINS FORTUNATO**
* **VITOR JOAQUIM CALDEIRA SANTOS**

## Funcionalidades Principais

O sistema utiliza um motor de busca e filtragem que analisa sete camadas de dados do usuário:

* **Gêneros Favoritos:** Peso maior para os estilos que o usuário mais consome.
* **Duração Ideal:** Filtro inteligente para momentos de "filme rápido" ou "épicos de 3 horas".
* **Classificação Etária:** Ajuste de segurança e preferência de conteúdo.
* **Idiomas:** Priorização de obras em idiomas de domínio ou preferência do usuário.
* **Histórico de Visualização:** O sistema evita redundâncias e entende o arco de consumo.
* **Feedback Qualitativo:** As notas atribuídas pelo usuário moldam o peso de futuras recomendações.

---

## Arquitetura do Sistema 

O projeto segue princípios de engenharia de software com responsabilidades bem definidas:

* **RecomendadorService:** O orquestrador que recebe o perfil, busca no catálogo, filtra, pontua e ordena.
* **CatalogoFilmesAPI:** Interface (mockável) para consumo de dados externos como TMDB.
* **NotificadorPush:** Envia um alerta quando a recomendação do dia está pronta.
* **Modo "Surpreenda-me":** Método extra que seleciona um filme aleatório entre os que passaram no filtro.
  
---

## Print da Cobertura

* **Testes JUnit 5**:
<img width="533" height="704" alt="image" src="https://github.com/user-attachments/assets/a4c6642f-47ea-4d6b-8fcd-78e5366685ae" />

* **Testes Mockito**:
<img width="303" height="525" alt="image" src="https://github.com/user-attachments/assets/4637d689-9580-4063-aace-8ce559136066" />




---

## Tecnologias Utilizadas 

| Camada | Tecnologia |
| :--- | :--- |
| **Linguagem Utilizada** | Java |
| **Editor de Código** | Visual Studio Code |
| **Ferramenta de Teste** | JUnit 5 |
| **Ferramenta de Teste** | Mockito |

---

## Diagramas 

-Diagramas de Classe: https://app.diagrams.net/#G1hJNlfzrbVkx6FsPx1TuFx4drfvN4B0fw 

-Diagrama de Sequência: https://app.diagrams.net/#G1h5CUZOz5u98fL5GVVIsQ_m_ibIYjXEgH#%7B%22pageId%22%3A%220LJwvzljNp3Yjp7CEBai%22%7D

---

## Como Executar os Testes com Maven no VS Code 

(Exemplo de um cenário): 
Para validar a lógica de cálculo de popularidade e outros componentes do sistema, siga as instruções abaixo:

### 1. Estrutura do Teste 
Certifique-se de que o método de teste na classe correspondente segue o padrão **JUnit 5** (Exemplo):

```java
@Test
void devCalcularPopularidadeCorretamente() {
    // 1. Arrange: Dados de entrada
    int entrada = 100; 

    // 2. Act: Execução da lógica
    int resultado = service.calcularPopularidade(entrada);

    // 3. Assert: Verificação do resultado esperado
    // Se o resultado for o esperado, o Assertions confirma o sucesso do teste
    Assertions.assertEquals(10, resultado, "O cálculo de popularidade deve ser 10 para entrada 100");
}

```

---

### 2. Fluxo de Execução no Editor: 

* **Abra o VS Code e acesse a classe de teste.**

* **Clique no ícone de Testing (ícone de frasco de laboratório) na barra lateral esquerda.**

* **Localize o método que está sendo utilizado.**

* **Clique no botão de Seta (Run Test) que aparece acima da anotação @Test ou na aba de testes.**
  
---

### 3. Interpretação dos Resultados: 
O VS Code utiliza sinalizações visuais para indicar o status do Assertions:

* **Check Verde: O teste passou. A resposta obtida foi exatamente a que você esperava.**

* **X Vermelho: O teste falhou. O Assertions detectou que a resposta da função diverge do esperado, indicando necessidade de correção na lógica.**

* **Ícone de Carregamento: O Maven está processando a execução e os dados de entrada.**
  
