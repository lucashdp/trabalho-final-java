# Trabalho final javaee
Trabalho final de Arquitetura de Software na Plataforma Java EE - O desafio propõe desenvolver uma API com alguns endpoints que viabilizem a construção de um mercado de ações.

# Tecnologias
- Backend: Java Spring MVC/Rest
- Banco de Dados: MySQL
- Fila de Comunicação Assíncrona: RabbitMQ

## Deploy
**[Esta API foi publicada em https://trabalho-final-java.herokuapp.com](https://trabalho-final-java.herokuapp.com)**

## Documentação
**[A documentação foi gerada pelo swagger e disponível em https://trabalho-final-java.herokuapp.com/swagger-ui.html](https://trabalho-final-java.herokuapp.com/swagger-ui.html)**

## Requisitos do Mercado de Ações
O sistema deverá tratar da compra de ações para pessoas físicas.

- Uma Empresa possui um número limitado de ações para serem vendidas;

<code>Este número pode ser inserido através do POST da empresa, no parâmetro "stock_number"</code>
- As Empresas podem emitir novas ações porém não podemos diminuir o número de ações atuais;

<code>Este número pode ser alterado através do PUT da empresa, no parâmetro "stock_number", a própria API bloqueia e não edita caso diminua</code>
- Cada ação pode pertencer a somente um Comprador;

<code>O mapeamento da ação só permite um único proprietário</code>
- Uma Ação deve possuir a informação de quando foi comprada e de qual seu valor inicial e atual, juntamente das informações do seu Comprador;

<code>Informações disponíveis respectivamente em "purchaseDate", "initial_stock_price", price" e "owner" no mapeamento da ação (https://trabalho-final-java.herokuapp.com/api/v1/stock)</code>
- Um Comprador pode possuir várias Ações;

<code>Cada ação tem um "owner", ao qual não é único então é possível um usuário ter várias ações</code>
- O sistema precisa tratar de forma assíncrona a compra e venda das Ações;

<code>Através de uma fila com o RabbitMQ são processadas as compras.
O teste pode ser executado no POST do shopping-controller, passando o id do novo comprador (buyer_id), preço (price) e id da ação (stock_id)</code>
- Durante uma compra ou venda, seu Comprador antigo e o novo precisam receber um email com a informação adequada sobre a operação;

<code>Para testar essa funcionalidade é necessário executar o projeto local, alterando no arquivo src/main/java/com/javaee/stockmarket/listeners/ShoppingListener.java o "fromEmail" e "password" nas linhas 61 e 62. Além disso será necessário configurar o src/main/resources/config.properties de acordo com o SMTP do provedor de email. Após configurar, basta repetir o processo de compra, caso os usuários anterior e novo tenham um email válido.</code>