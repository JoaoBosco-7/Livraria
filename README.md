## Sistema de Gerenciamento de Biblioteca

O Sistema de Gerenciamento de Biblioteca é uma aplicação desenvolvida para facilitar a gestão e organização de uma biblioteca ou livraria. Seu principal objetivo é proporcionar uma experiência intuitiva e eficiente para usuários, permitindo o controle completo sobre os livros disponíveis, clientes, aluguéis e devoluções. 

# **Utilidade do Sistema:** 
O sistema oferece uma série de funcionalidades essenciais para a administração de uma biblioteca ou livraria, incluindo: 

**Cadastro de Livros e Clientes:** Permite o cadastro e gerenciamento detalhado de livros, incluindo título, autor, gênero e volume, bem como o cadastro de clientes, com informações como nome, conta e senha. 

**Controle de Empréstimos:** Facilita o processo de empréstimo de livros, permitindo que os clientes solicitem e retirem livros disponíveis.  

**Gerenciamento de Usuários:** Permite o gerenciamento de perfis de usuários, incluindo clientes e administradores, garantindo a segurança e o controle de acesso às funcionalidades do sistema. 

**Motivação e Perfil dos Usuários:** A motivação para o desenvolvimento deste sistema surgiu da necessidade de facilitar a gestão de bibliotecas e livrarias, especialmente em ambientes educacionais. E principalmente, a tentativa de entender como esse sistema funcionaria na prática. 

## Diagrama de Caso de Uso
![image](https://github.com/JoaoBosco-7/Livraria/assets/71224952/8e335a4d-dcc6-4b08-92f6-46d56f3af14b)

## Diagrama de Classes 
![image](https://github.com/JoaoBosco-7/Livraria/assets/71224952/388a3a05-f524-475d-bc4b-689e8c868ad3)

O **Diagrama de Classes** descreve um sistema onde um gerente administra uma biblioteca, que contém livros disponíveis para aluguel. Os clientes podem realizar transações de aluguel com a biblioteca, associando-se a um ou mais livros disponíveis. A existência de uma conta (Acount) fornece informações básicas tanto para clientes quanto para gerentes. A classe ExternalApiSimulator está associada ao Manager e tem uma relação de dependência com o LisinerCallBack, indicando uma relação de uso entre essas classes para simulação de interações externas e recebimento de chamadas de retorno.

- Acount é herdado por Cliente e Manager: Isso sugere que tanto Cliente quanto Manager têm características comuns de uma conta, como identificação e informações de contato.
- Manager e Cliente têm uma relação de associação: Isso indica que um gerente pode estar associado a vários clientes ou vice-versa, talvez para fins de gerenciamento ou interação.
- Manager e Rent têm uma relação de associação: Isso sugere que um gerente pode estar associado a várias transações de aluguel, possivelmente para monitoramento ou gerenciamento.
- Manager e Library têm uma relação de associação: Isso indica que um gerente está associado a uma biblioteca específica para realizar tarefas de gerenciamento.
Rent e Book têm uma relação de associação: Isso sugere que uma transação de aluguel está associada a um ou mais livros que estão sendo alugados.
- Book e Library têm uma relação de composição: Isso implica que os livros são componentes da biblioteca e que a existência da biblioteca é essencial para a existência dos livros.
- Cliente e Library têm uma relação de associação: Isso indica que um cliente pode estar associado a uma biblioteca para acessar e interagir com os livros disponíveis.

## Interagindo com o programa

Ao iniciar o programa, você será recebido com uma interface de linha de comando (CLI). Aqui estão algumas instruções para interagir com o programa:

- Para fazer login como cliente, digite "cliente" e pressione Enter. Você será solicitado a inserir seu AcountId e senha. (cliente1; senha1)
- Para fazer login como gerente, digite "gerente" e pressione Enter. Novamente, você precisará inserir seu AcountId e senha. (gerente1; senha1)
- Após fazer login com sucesso, você terá acesso a diferentes opções, dependendo do seu papel (cliente ou gerente). Por exemplo, como cliente, você pode buscar livros, alugar livros, devolver livros, etc. Como gerente, você pode adicionar novos clientes, buscar clientes, adicionar novos livros à biblioteca, etc.
- Use os números correspondentes às opções fornecidas para selecionar a ação desejada.
- Para sair do programa, você pode digitar "sair" a qualquer momento.

**Outras considerações:**

Certifique-se de inserir as informações solicitadas corretamente para evitar erros de autenticação.
A interação com o programa é em tempo real, então você pode executar várias ações consecutivas durante uma única execução do programa.
Apenas o gerente pode adicionar uma nova conta cliente, ou remover a conta de um cliente, bem como a adição e remoção de livros na livraria.
