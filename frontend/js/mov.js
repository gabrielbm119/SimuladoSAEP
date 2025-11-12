const apiUrl = 'http://localhost:8084/movimentacao';

async function carregarMovimentacoes() {
    try {
        const response = await fetch(apiUrl);
        if (!response.ok) throw new Error(`Erro ao buscar movimentações (${response.status})`);
        const movimentacoes = await response.json();

        const lista = document.getElementById('listaMovimentacoes');
        lista.innerHTML = ''; // limpa antes de preencher

        if (movimentacoes.length === 0) {
            lista.innerHTML = '<p>Nenhuma movimentação encontrada.</p>';
            return;
        }

        movimentacoes.forEach(mov => {
            const card = document.createElement('div');
            card.classList.add('card-movimentacao', mov.tipoMovimentacao);

            const dataFormatada = new Date(mov.dataHoraMovimentacao).toLocaleString('pt-BR');

            card.innerHTML = `
            <div class="card-header">
              <span class="tipo">${mov.tipoMovimentacao}</span>
              <span class="data">${dataFormatada}</span>
            </div>
            <div class="card-body">
              <p><strong>ID Movimentação:</strong> ${mov.idMovimentacao}</p>
              <p><strong>ID Produto:</strong> ${mov.produtoModel.idProduto}</p>
              <p><strong>Produto:</strong> ${mov.produtoModel.nomeProduto}</p>
            </div>
          `;

            lista.appendChild(card);
        });
    } catch (error) {
        console.error(error);
        document.getElementById('listaMovimentacoes').innerHTML =
            `<p style="color:red;">Erro ao carregar movimentações: ${error.message}</p>`;
    }
}

// Chama automaticamente ao abrir a página
carregarMovimentacoes();