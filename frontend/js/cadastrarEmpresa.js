document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('formCadastroEmpresa');

    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Impede o envio padrão do formulário

        const empresaData = {
            nome: document.getElementById('nome').value,
            email: document.getElementById('email').value,
            cnpj: document.getElementById('cnpj').value
        };

        fetch('/cadastroempresa', { // URL do seu back-end
            method: 'POST',
            headers: {
                'Content-Type': 'application/json' // Define o tipo de conteúdo
            },
            body: JSON.stringify(empresaData), // Converte o objeto para JSON
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao cadastrar a empresa');
            }
            return response.json();
        })
        .then(data => {
            alert('Empresa cadastrada com sucesso!'); // Mensagem de sucesso
            form.reset(); // Limpa o formulário após o envio
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao cadastrar a empresa. Tente novamente.'); // Mensagem de erro
        });
    });
});
