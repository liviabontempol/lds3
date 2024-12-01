<script>
document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('formCadastroAluno');

    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Impede o envio padrão do formulário

        // Validação simples
        const nome = document.getElementById('nome').value;
        const email = document.getElementById('email').value;
        const cpf = document.getElementById('cpf').value;
        const rg = document.getElementById('rg').value;
        const endereco = document.getElementById('endereco').value;
        const instituicao = document.getElementById('instituicao').value;
        const curso = document.getElementById('curso').value;
        const senha = document.getElementById('senha').value;

        if (!nome || !email || !cpf || !rg || !endereco || !instituicao || !curso || !senha) {
            alert('Por favor, preencha todos os campos.');
            return;
        }

        const alunoData = {
            nome: nome,
            email: email,
            cpf: cpf,
            rg: rg,
            endereco: endereco,
            instituicao: instituicao,
            curso: curso,
            senha: senha
        };

        fetch('https://seu-backend.com/api/cadastro', { // URL do seu back-end
            method: 'POST',
            headers: {
                'Content-Type': 'application/json' // Define o tipo de conteúdo
            },
            body: JSON.stringify(alunoData), // Converte o objeto para JSON
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro na requisição');
            }
            return response.json();
        })
        .then(data => {
            alert('Cadastro realizado com sucesso!'); // Mensagem de sucesso
            form.reset(); // Limpa o formulário após o envio
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao cadastrar. Tente novamente.'); // Mensagem de erro
        });
    });
});
</script>