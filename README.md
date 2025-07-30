# dio-api-hexagonal-com-testes-archunit

## **Visão Geral**

A **customer-api** é uma aplicação backend robusta e escalável, desenvolvida com o Spring Boot, que gerencia informações de clientes. Ela exemplifica a aplicação da **Arquitetura Hexagonal (Ports and Adapters)**, um padrão de design que visa criar sistemas altamente desacoplados, testáveis e fáceis de manter.

## **Arquitetura Hexagonal: Uma Breve Introdução**

A Arquitetura Hexagonal, também conhecida como Arquitetura de Portas e Adaptadores, foi proposta por Alistair Cockburn para resolver o problema de acoplamento excessivo entre a lógica de negócio principal de uma aplicação e as tecnologias externas (bancos de dados, serviços web, interfaces de usuário, etc.).

Seus princípios fundamentais são:

* **Separação de Preocupações**: A lógica de negócio (o "Core" ou "Domínio" da aplicação) é isolada de detalhes técnicos e de infraestrutura.
* **Testabilidade**: O Core da aplicação pode ser testado independentemente de qualquer tecnologia externa, através das suas "Portas".
* **Flexibilidade**: Permite que as tecnologias externas (bancos de dados, frameworks web, sistemas de mensageria) sejam facilmente substituídas ou alteradas sem impactar a lógica de negócio central.

Como funciona:

* **Core (Application)**: Contém o Domínio e os Casos de Uso. É a parte mais importante da aplicação, onde reside a lógica de negócio. Não tem conhecimento de tecnologias externas.
* **Portas (Ports)**: São interfaces que definem os contratos de comunicação entre o Core e o mundo externo.
    * **Input Ports (Portas de Entrada)**: Definem o que a aplicação pode fazer. São implementadas pelos Casos de Uso e consumidas pelos Adaptadores de Entrada.
    * **Output Ports (Portas de Saída)**: Definem o que a aplicação precisa do mundo externo. São implementadas pelos Adaptadores de Saída e usadas pelos Casos de Uso.
* **Adaptadores (Adapters)**: São implementações concretas das Portas, conectando a aplicação a tecnologias específicas.
    * **Input Adapters (Adaptadores de Entrada)**: Convertem chamadas de tecnologias externas (ex: requisições HTTP, mensagens Kafka) para chamadas aos Input Ports.
    * **Output Adapters (Adaptadores de Saída)**: Convertem chamadas dos Output Ports para chamadas a tecnologias externas (ex: chamadas a bancos de dados, clientes REST, produtores Kafka).

## **A customer-api na Arquitetura Hexagonal**

A estrutura de pacotes da customer-api reflete diretamente os conceitos da Arquitetura Hexagonal:

* **application/core/domain**: Este é o **Core/Domínio** da aplicação. Contém as entidades de negócio puras (como Customer, Address), que encapsulam as regras de negócio mais importantes e não possuem dependências de frameworks externos ou infraestrutura.
* **application/ports**: Define os contratos de comunicação para o Core.
    * **in**: Contém as InputPorts, que são interfaces que o Core expõe para que as ações da aplicação possam ser iniciadas. Por exemplo, FindCustomerByIdInputPort.
    * **out**: Contém as OutputPorts, que são interfaces que o Core precisa que o mundo externo implemente para que ele possa realizar suas operações. Por exemplo, FindCustomerByIdOutputPort, InsertCustomerOutputPort.
* **application/core/usecase**: Contém a implementação da lógica de negócio para cada funcionalidade. As classes aqui implementam as InputPorts e utilizam as OutputPorts. Por exemplo, FindCustomerByIdUseCase, InsertCustomerUseCase.
* **adapters**: Implementam as Ports, conectando o Core às tecnologias externas.
    * **in**: Os **Adaptadores de Entrada**. Incluem controller (controladores REST para requisições HTTP), consumer (consumidores Kafka para processar mensagens), e seus respectivos DTOs (request, response) e mappers.
    * **out**: Os **Adaptadores de Saída**. Incluem repository (integração com MongoDB via Spring Data JPA), client (clientes Feign para consumo de APIs externas), e as classes adaptadoras concretas que implementam as OutputPorts (e.g., FindAddressByPostalAdapter, InsertCustomerAdapter).
* **config**: Contém as classes de configuração da aplicação, onde as dependências entre as portas e os adaptadores são injetadas.

Essa estrutura garante que a customer-api mantenha seu domínio livre de detalhes de infraestrutura, facilitando a manutenção e a evolução do sistema.

## **Diagrama da Arquitetura**

A visualização a seguir ilustra as principais interações e componentes da customer-api com os sistemas externos:

Snippet de código

graph TD  
subgraph "Cliente API"  
User\[Usuário / Aplicação Externa\] \-- Requisições HTTP \--\> API\_Gateway(API Gateway / Load Balancer)  
end

    subgraph "Sistema Customer-API"  
        direction LR

        subgraph "Adaptadores de Entrada (Inbound Adapters)"  
            CAPI\_REST(REST Controllers) \--- P\_IN(Input Ports)  
            CAPI\_Kafka\_Consumer(Kafka Consumer: Customer Cpf Validation) \--- P\_IN  
        end

        subgraph "Core da Aplicação (Application)"  
            direction LR  
            P\_IN \-- Invoca \--\> UC\[Use Cases\]  
            UC \-- Usa \--\> P\_OUT(Output Ports)  
        end

        subgraph "Adaptadores de Saída (Outbound Adapters)"  
            P\_OUT \--- CAPI\_Mongo(MongoDB Repository)  
            P\_OUT \--- CAPI\_Kafka\_Producer(Kafka Producer: CPF Validation Queue)  
            P\_OUT \--- CAPI\_Feign(Feign Client: External Address API)  
        end  
    end

    API\_Gateway \-- Encaminha \--\> CAPI\_REST  
    Kafka\_Broker(Kafka Broker) \-- Envia Mensagens \--\> CAPI\_Kafka\_Consumer  
    CAPI\_Kafka\_Producer \-- Envia Mensagens \--\> Kafka\_Broker  
    CAPI\_Mongo \-- Armazena/Consulta \--\> MongoDB(Servidor MongoDB)  
    CAPI\_Feign \-- Requisições HTTP \--\> External\_Address\_API(API Externa: Consulta de Endereços)

    style CAPI\_REST fill:\#f9f,stroke:\#333,stroke-width:2px  
    style CAPI\_Kafka\_Consumer fill:\#f9f,stroke:\#333,stroke-width:2px  
    style CAPI\_Mongo fill:\#9f9,stroke:\#333,stroke-width:2px  
    style CAPI\_Kafka\_Producer fill:\#9f9,stroke:\#333,stroke-width:2px  
    style CAPI\_Feign fill:\#9f9,stroke:\#333,stroke-width:2px  
    style P\_IN fill:\#cff,stroke:\#333,stroke-width:2px  
    style P\_OUT fill:\#cff,stroke:\#333,stroke-width:2px  
    style UC fill:\#fc9,stroke:\#333,stroke-width:2px  
    style MongoDB fill:\#ccc,stroke:\#333,stroke-width:2px  
    style Kafka\_Broker fill:\#ccc,stroke:\#333,stroke-width:2px  
    style External\_Address\_API fill:\#ccc,stroke:\#333,stroke-width:2px

## **Tecnologias e Interações**

A customer-api utiliza diversas tecnologias para compor seu ecossistema:

* **Spring Boot**: Framework principal para o desenvolvimento da aplicação.
* **MongoDB**: Utilizado como banco de dados para persistência das informações de clientes. A interação é realizada através de adaptadores na camada adapters.out.repository.
* **Apache Kafka**: A aplicação interage com Kafka de duas formas:
    * **Consumo**: Através de adaptadores em adapters.in.consumer, a customer-api consome mensagens de tópicos Kafka (e.g., CustomerValidateCpfConsumer).
    * **Produção**: Através de adaptadores em adapters.out (e.g., QueueCpfForValidationAdapter), a aplicação pode produzir mensagens para tópicos Kafka.
* **Feign Client**: Para consumir serviços externos, a customer-api emprega o Feign Client. O exemplo FindAddressByPostalClient em adapters.out.client demonstra a integração com uma API externa para buscar endereços por CEP.

## **Manutenção da Arquitetura com ArchUnit**

Para garantir que a arquitetura hexagonal seja mantida consistentemente ao longo do desenvolvimento e evolução da customer-api, utilizamos o **ArchUnit**.

O ArchUnit é uma biblioteca poderosa de testes de arquitetura que permite escrever testes automatizados para verificar regras arquiteturais. Na customer-api, o ArchUnit é empregado para:

* **Validação de Camadas**: Garante que as dependências entre as camadas da arquitetura hexagonal (Domain, Ports, UseCases, Adapters) sigam as regras estabelecidas (e.g., que o Domain não dependa de Adapters, que UseCases só dependam de Ports e Domain).
* **Convenções de Nomenclatura**: Impõe padrões de nomes para classes em pacotes específicos (e.g., classes em usecase devem terminar com "UseCase", interfaces de ports.in devem terminar com "InputPort", adaptadores de saída na raiz de adapters.out devem terminar com "Adapter").
* **Restrições de Anotação**: Proíbe o uso de anotações de persistência em classes do domínio, garantindo que o core permaneça puro.

Esses testes de arquitetura são executados juntamente com os testes de unidade e integração, atuando como um "guardião" da arquitetura e prevenindo o acúmulo de débito técnico arquitetural.