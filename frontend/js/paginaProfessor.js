document.addEventListener('DOMContentLoaded', function() {
    const saldoValor = document.getElementById('saldoValor');
    const consultarSaldoButton = document.getElementById('consultarSaldo');
    const formTransferirMoedas = document.getElementById('formTransferirMoedas');

    // Função para consultar saldo
    consultarSaldoButton.addEventListener('click', function() {
        fetch('/professor/saldo') // URL do seu back-end para consultar saldo
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao consultar saldo');
                }
                return response.json();
            })
            .then(data => {
                saldoValor.textContent = data.saldo; // Atualiza o saldo na página
            })
            .catch(error => {
                console.error('Erro:', error);
                alert('Erro ao consultar saldo. Tente novamente.'); // Mensagem de erro
            });
    });

    // Função para transferir moedas
    formTransferirMoedas.addEventListener('submit', function(event) {
        event.preventDefault(); // Impede o envio padrão do formulário

        const transferenciaData = {
            aluno: document.getElementById('aluno').value,
            quantidade: parseInt(document.getElementById('quantidade').value)
        };

        fetch('/professor/transferir', { // URL do seu back-end para transferir moedas
            method: 'POST',
            headers: {
                'Content-Type': 'application/json' // Define o tipo de conteúdo
            },
            body: JSON.stringify(transferenciaData), // Converte o objeto para JSON
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro na transferência');
            }
            return response.json();
        })
        .then(data => {
            alert('Transferência realizada com sucesso!'); // Mensagem de sucesso
            formTransferirMoedas.reset(); // Limpa o formulário após o envio
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao transferir moedas. Tente novamente.'); // Mensagem de erro
        });
    });
});
