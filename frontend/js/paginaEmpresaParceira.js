document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form');

    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Impede o envio padrão do formulário

        const formData = new FormData(form); // Coleta os dados do formulário

        fetch('/empresa/cadastrar-vantagem', { // URL do seu back-end
            method: 'POST',
            body: formData,
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro na requisição');
            }
            return response.json();
        })
        .then(data => {
            alert('Vantagem cadastrada com sucesso!'); // Mensagem de sucesso
            form.reset(); // Limpa o formulário após o envio
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao cadastrar a vantagem. Tente novamente.'); // Mensagem de erro
        });
    });
});
