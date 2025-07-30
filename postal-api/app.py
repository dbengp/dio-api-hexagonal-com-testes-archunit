from flask import Flask, jsonify, abort

app = Flask(__name__)

# MOCKED_ADDRESS_DATA é uma LISTA de dicionários, contendo vários endereços
# Estes serão os dados que sua outra API vai "consultar" individualmente.
MOCKED_ADDRESS_DATA = [
    {
        "street": "Rua da Praia",
        "city": "Porto Alegre",
        "state": "RS",
        "country": "Brasil",
        "postal": "90000000"
    },
    {
        "street": "Travessa dos Pinheiros",
        "city": "Curitiba",
        "state": "PR",
        "country": "Brasil",
        "postal": "80000000"
    },
    {
        "street": "Rua das Flores",
        "city": "São Paulo",
        "state": "SP",
        "country": "Brasil",
        "postal": "01000000"
    },
    {
        "street": "Avenida Brasil",
        "city": "Rio de Janeiro",
        "state": "RJ",
        "country": "Brasil",
        "postal": "20000000"
    },
    {
        "street": "Rua da Mata",
        "city": "Porto Alegre",
        "state": "RS",
        "country": "Brasil",
        "postal": "98000000"
    },
    {
        "street": "Travessa dos Los Angeles",
        "city": "Curitiba",
        "state": "PR",
        "country": "Brasil",
        "postal": "82000000"
    },
    {
        "street": "Praça Sete",
        "city": "Belo Horizonte",
        "state": "MG",
        "country": "Brasil",
        "postal": "30000000"
    },
    {
        "street": "Rua Treze de Maio",
        "city": "Campinas",
        "state": "SP",
        "country": "Brasil",
        "postal": "13000000"
    },
    {
        "street": "Avenida da Boa Viagem",
        "city": "Recife",
        "state": "PE",
        "country": "Brasil",
        "postal": "50000000"
    },
    {
        "street": "Rua da Aurora",
        "city": "Manaus",
        "state": "AM",
        "country": "Brasil",
        "postal": "69000000"
    },
    {
        "street": "Rua dos Andradas",
        "city": "Florianópolis",
        "state": "SC",
        "country": "Brasil",
        "postal": "88000000"
    },
    {
        "street": "Avenida Afonso Pena",
        "city": "Campo Grande",
        "state": "MS",
        "country": "Brasil",
        "postal": "79000000"
    },
    {
        "street": "Rua da Consolação",
        "city": "São Paulo",
        "state": "SP",
        "country": "Brasil",
        "postal": "01300000"
    },
    {
        "street": "Rua Chile",
        "city": "Salvador",
        "state": "BA",
        "country": "Brasil",
        "postal": "40000000"
    },
    {
        "street": "Avenida Paulista",
        "city": "São Paulo",
        "state": "SP",
        "country": "Brasil",
        "postal": "01310000"
    }
]

@app.route('/addresses/<postal>', methods=['GET'])
def get_address_by_postal(postal):
    for address in MOCKED_ADDRESS_DATA:
        if address["postal"] == postal:
            return jsonify(address)       
    abort(404, description=f"Endereço para o CEP {postal} não encontrado.")

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=8082)