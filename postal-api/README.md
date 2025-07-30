# **API Mockada de Consulta de Endereços por CEP**
Certifique-se de que sua pasta de projeto é "/postal-api", ou outro nome que você escolher, desde que tenha os seguintes arquivos:

* **app.py**: O código-fonte da aplicação Flask.  
* **requirements.txt**: Lista as dependências Python (Flask e Gunicorn).  
* **Dockerfile**: A receita para construir a imagem Docker da aplicação.

---

## **🚀 Como Rodar a Aplicação**

Para colocar esta API mockada para funcionar, você tem três opções principais. **Abra seu terminal/Prompt de Comando/PowerShell e navegue até o diretório raiz do projeto antes de seguir os passos de qualquer opção.**

1. **Diretamente no Linux (Ubuntu/Debian)**  
2. **Diretamente no Windows**  
3. **Via Docker (recomendado para qualquer SO)**

---

## **💻 Opção 1: Rodar no Linux (Ubuntu/Debian)**

### **Pré-requisitos**

* **Python 3 e pip3**: Já devem estar instalados. Se não, use sudo apt update && sudo apt install python3 python3-pip.  
* **Pacote python3-venv**: Essencial para criar ambientes virtuais.  
  Bash  
  sudo apt install python3-venv \# Ou python3.12-venv, dependendo da sua versão do Python

### **Passos**

1. **Crie e ative o ambiente virtual:**  
   Bash  
   python3 \-m venv venv  
   source venv/bin/activate

   Você verá (venv) no início do seu prompt, indicando que o ambiente está ativo.  
2. **Instale as dependências:**  
   Bash  
   pip install \-r requirements.txt

3. **Inicie a aplicação com Gunicorn:**  
   Bash  
   gunicorn \--bind 0.0.0.0:8082 app:app

   A aplicação estará acessível em http://localhost:8082.  
4. Para parar a aplicação:  
   Pressione Ctrl \+ C no terminal onde o Gunicorn está rodando.  
5. **Para sair do ambiente virtual:**  
   Bash  
   deactivate

---

## **🪟 Opção 2: Rodar no Windows**

### **Pré-requisitos**

* **Python 3 e pip**: Baixe o instalador oficial do [Python](https://www.python.org/downloads/windows/) e **certifique-se de marcar "Add Python to PATH"** durante a instalação.

### **Passos**

1. **Crie e ative o ambiente virtual:**  
   DOS  
   python \-m venv venv

   * **Para Prompt de Comando (CMD):**  
     DOS  
     venv\\Scripts\\activate

   * **Para PowerShell:**  
     PowerShell  
     .\\venv\\Scripts\\Activate.ps1

Você verá (venv) no início do seu prompt.

2. **Instale as dependências:**  
   DOS  
   pip install \-r requirements.txt

   * **Nota**: Gunicorn é primariamente para ambientes Unix. Se você tiver problemas, considere usar [Waitress](https://pypi.org/project/waitress/).  
     * Para usar Waitress, altere requirements.txt para Flask e waitress.  
     * No app.py, adicione no if \_\_name\_\_ \== '\_\_main\_\_'::  
       Python  
       from waitress import serve  
       serve(app, host='0.0.0.0', port=8082)

     * E execute a aplicação com python app.py.  
3. **Inicie a aplicação com Gunicorn (ou Waitress):**  
   * **Com Gunicorn:**  
     DOS  
     gunicorn \--bind 0.0.0.0:8082 app:app

   * **Com Waitress (se você o configurou):**  
     DOS  
     python app.py

A aplicação estará acessível em http://localhost:8082.

4. Para parar a aplicação:  
   Pressione Ctrl \+ C no terminal onde a aplicação está rodando.  
5. **Para sair do ambiente virtual:**  
   DOS  
   deactivate

---

## **🐳 Opção 3: Rodar com Docker (Recomendado)**

Esta é a forma mais consistente, garantindo que a aplicação rode da mesma maneira em qualquer sistema operacional que tenha Docker.

### **Pré-requisitos**

* **Docker Desktop (Windows/macOS)** ou **Docker Engine (Linux)** instalado. Consulte a [documentação oficial do Docker](https://docs.docker.com/get-docker/) para instalação.

### **Passos**

1. Construa a imagem Docker:  
   Este comando cria uma imagem Docker baseada no Dockerfile e a nomeia postal-mock-api.  
   Bash  
   docker build \-t postal-mock-api .

   Isso pode levar alguns minutos na primeira vez.  
2. Rode a aplicação em um contêiner Docker:  
   Este comando inicia um contêiner a partir da imagem que você acabou de construir, mapeando a porta 8082 do seu computador para a porta 8082 dentro do contêiner.  
   Bash  
   docker run \-p 8082:8082 \--name postal-mock-container postal-mock-api

   A aplicação estará acessível em http://localhost:8082.  
3. Para parar o contêiner:  
   Pressione Ctrl \+ C no terminal onde o contêiner está rodando.

---

## **✅ Testando a API Mockada**

Uma vez que a aplicação esteja rodando (em qualquer uma das opções), você pode testá-la usando seu navegador ou uma ferramenta como Postman:

* Consultar um CEP existente (ex: São Paulo):  
  GET http://localhost:8082/addresses/01000000  
* Consultar outro CEP existente (ex: Rio de Janeiro):  
  GET http://localhost:8082/addresses/20000000  
* Consultar um CEP que NÃO existe nos dados mockados (para testar o 404):  
  GET http://localhost:8082/addresses/99999999