//URL da API
const apiUrl = 'http://localhost:8084/produto';

document.getElementById('getDataBtn').addEventListener('click', function () {

    document.getElementById('produtoForm').reset();
    const idProduto = document.getElementById('idProduto').value;

    if (idProduto.length != 0) {
        document.querySelector('.container3').style.display = 'none';

        fetch(`${apiUrl}/${idProduto}`) //Adiciona o ID do produto na URL
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Produto não encontrado, favor inserir um id válido.`);
                }
                return response.json();
            })
            .then(data => {
                document.getElementById('result').value = JSON.stringify(data, null, 2);
                // Preenchendo os campos com os dados do produto de forma separada
                document.getElementById(`idProdutoDisplay`).value = data.idProduto;
                document.getElementById(`nomeProduto`).value = data.nomeProduto;
                document.getElementById(`nomeProdutoOriginal`).value = data.nomeProduto;
                document.getElementById(`quantidadeProduto`).value = data.quantidadeProduto;
                document.getElementById(`quantidadeProdutoOriginal`).value = data.quantidadeProduto;
                document.getElementById(`qtdMinimaProduto`).value = data.qtdMinimaProduto;
                document.getElementById(`qtdMinimaProdutoOriginal`).value = data.qtdMinimaProduto;
                document.getElementById(`qtdMaximaProduto`).value = data.qtdMaximaProduto;
                document.getElementById(`qtdMaximaProdutoOriginal`).value = data.qtdMaximaProduto;
            })
            .catch(error => {
                document.getElementById('result').value = `Erro: ${error.message}`;
            })
    }
    else if (idProduto.length == 0) {
        fetch(`${apiUrl}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Não existe nenhum produto disponível no momento ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                document.getElementById('result').value = JSON.stringify(data, null, 2);

                // Mostrar container3
                const container3 = document.querySelector('.container3');
                container3.style.display = 'block';

                // Limpar lista antes de preencher
                const lista = document.getElementById('produtosLista');
                lista.innerHTML = '';

                // Criar um card para cada produto
                data.forEach(produto => {
                    const card = document.createElement('div');
                    card.classList.add('card-produto');
                    card.innerHTML = `
                    <h4>${produto.nomeProduto}</h4>
                    <p><strong>Quantidade:</strong> ${produto.quantidadeProduto}</p>
                    <p><strong>Estoque mínimo:</strong> ${produto.qtdMinimaProduto}</p>
                    <p><strong>Estoque máximo:</strong> ${produto.qtdMaximaProduto}</p>
                `;
                    lista.appendChild(card);
                });
            })
            .catch(error => {
                document.getElementById('result').value = `Erro: ${error.message}`;
            });
    }
});

// Função para enviar dados (POST)
document.getElementById('sendDataBtn').addEventListener('click', function () {
    const nomeProduto = document.getElementById('nomeProduto').value;
    const quantidadeProduto = document.getElementById('quantidadeProduto').value;
    const qtdMinimaProduto = document.getElementById('qtdMinimaProduto').value;
    const qtdMaximaProduto = document.getElementById('qtdMaximaProduto').value;

    if (nomeProduto && quantidadeProduto) {
        const payload = {
            nomeProduto: nomeProduto,
            quantidadeProduto: parseInt(quantidadeProduto, 10),
            qtdMinimaProduto: parseInt(qtdMinimaProduto, 10),
            qtdMaximaProduto: parseInt(qtdMaximaProduto, 10)
        };

        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro ao enviar dados: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                alert('Dados enviados com sucesso!');
                console.log('Resposta da API:', data);
            })
            .catch(error => {
                alert(`erro: ${error.message}`);
            });
    } else {
        alert('Por favor, preencha todos os campos.');
    }
});

// Função para atualizar dados (PUT)
document.getElementById('updateDataBtn').addEventListener('click', function () {
    const idProduto = document = document.getElementById('idProdutoDisplay').value;
    const nomeProduto = document = document.getElementById('nomeProduto').value;
    const quantidadeProduto = document = document.getElementById('quantidadeProduto').value;
    const qtdMinimaProduto = document.getElementById('qtdMinimaProduto').value;
    const qtdMaximaProduto = document.getElementById('qtdMaximaProduto').value;

    if (idProduto && nomeProduto && quantidadeProduto) {
        const payload = {
            nomeProduto: nomeProduto,
            quantidadeProduto: parseInt(quantidadeProduto, 10),
            qtdMinimaProduto: parseInt(qtdMinimaProduto, 10),
            qtdMaximaProduto: parseInt(qtdMaximaProduto, 10)
        };

        fetch(`${apiUrl}/${idProduto}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro ao atualizar produto: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                alert('Produto atualizado com sucesso!');
                console.log('Resposta da API:', data);
                window.location.reload();
            })
            .catch(error => {
                alert(`Erro: ${error.message}`);
            });
    } else {
        alert('Por favor, preencha todos os campos antes de atualizar.')
    }
});

// Função para atualizar dados (PUT)
document.getElementById('addDataBtn').addEventListener('click', function () {
    const idProduto = document = document.getElementById('idProdutoDisplay').value;
    const nomeProdutoOriginal = document.getElementById('nomeProdutoOriginal').value;
    const quantidadeProdutoOriginal = document.getElementById('quantidadeProdutoOriginal').value;
    const quantidadeEntrasai = document.getElementById('quantidadeEntrasai').value;
    const estoqueAtualizado = parseInt(quantidadeProdutoOriginal, 10) + parseInt(quantidadeEntrasai, 10)
    const qtdMaximaProdutoOriginal = document.getElementById('qtdMaximaProdutoOriginal').value;
    const qtdMinimaProdutoOriginal = document.getElementById('qtdMinimaProdutoOriginal').value;

    if (idProduto && quantidadeProdutoOriginal && quantidadeEntrasai) {
        const payload = {
            nomeProduto: nomeProdutoOriginal,
            quantidadeProduto: estoqueAtualizado,
            qtdMinimaProduto: parseInt(qtdMinimaProdutoOriginal, 10),
            qtdMaximaProduto: parseInt(qtdMaximaProdutoOriginal, 10)
        };

        fetch(`${apiUrl}/${idProduto}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro ao atualizar produto: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                alert('Estoque adicionado com sucesso!');
                console.log('Resposta da API:', data);
                if (estoqueAtualizado > parseInt(qtdMaximaProdutoOriginal, 10)) {
                    alert('Atenção: O estoque atual ultrapassou o estoque máximo definido!');
                }
                window.location.reload();
            })
            .catch(error => {
                alert(`Erro: ${error.message}`);
            });
    } else {
        alert('Por favor, preencha todos os campos antes de atualizar.')
    }
});

// Função para atualizar dados (PUT)
document.getElementById('removeDataBtn').addEventListener('click', function () {
    const idProduto = document = document.getElementById('idProdutoDisplay').value;
    const nomeProdutoOriginal = document.getElementById('nomeProdutoOriginal').value;
    const quantidadeProdutoOriginal = document.getElementById('quantidadeProdutoOriginal').value;
    const quantidadeEntrasai = document.getElementById('quantidadeEntrasai').value;
    const estoqueAtualizado = parseInt(quantidadeProdutoOriginal, 10) - parseInt(quantidadeEntrasai, 10)
    const qtdMaximaProdutoOriginal = document.getElementById('qtdMaximaProdutoOriginal').value;
    const qtdMinimaProdutoOriginal = document.getElementById('qtdMinimaProdutoOriginal').value;

    if (idProduto && quantidadeProdutoOriginal && quantidadeEntrasai) {
        const payload = {
            nomeProduto: nomeProdutoOriginal,
            quantidadeProduto: estoqueAtualizado,
            qtdMinimaProduto: parseInt(qtdMinimaProdutoOriginal, 10),
            qtdMaximaProduto: parseInt(qtdMaximaProdutoOriginal, 10)
        };

        fetch(`${apiUrl}/${idProduto}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro ao atualizar produto: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                alert('Estoque removido com sucesso!');
                console.log('Resposta da API:', data);
                if (estoqueAtualizado < parseInt(qtdMinimaProdutoOriginal, 10)) {
                    alert('Atenção: O estoque atual ultrapassou o estoque minímo definido!');
                }
                window.location.reload();
            })
            .catch(error => {
                alert(`Erro: ${error.message}`);
            });
    } else {
        alert('Por favor, preencha todos os campos antes de atualizar.')
    }
});

// Função para deletar dados (DELETE)
document.getElementById('deleteDataBtn').addEventListener('click', function () {
    const produtoId = document.getElementById('idProdutoDisplay').value;

    if (produtoId) {
        if (confirm(`Tem certeza que deseja excluir o produto com ID ${produtoId}?`)) {
            fetch(`${apiUrl}/${produtoId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`Erro ao deletar produto: ${response.status}`);
                    }

                    alert('Produto deletado com sucesso!');
                })
                .catch(error => {
                    alert(`Erro: ${error.message}`);
                });
        }
    } else {
        alert('Por favor, insira um ID de produto válido para deletar.')
    }
})