document.addEventListener("DOMContentLoaded", function () {
    const urlApi = "http://localhost:8080/pao";
    const divBotoesPao = document.getElementById("botoesPao");
    const divMensagem = document.getElementById("mensagem");

    async function carregarPaes() {
        try {
            const response = await fetch(urlApi);
            if (!response.ok) {
                throw new Error("Erro ao carregar os pães.");
            }

            const paes = await response.json();
            adicionarBotoesPaes(paes);
        } catch (error) {
            console.error(error);
            exibirMensagem("Erro ao carregar os pães.", "alert-danger");
        }
    }

    function adicionarBotoesPaes(paes) {
        const colunas = Array.from({ length: 3 }, () => document.createElement("div"));
        colunas.forEach((coluna) => coluna.classList.add("col"));

        paes.forEach((pao, index) => {
            const button = document.createElement("button");
            button.className = "btn btn-primary mr-2 mb-2";
            button.textContent = pao.tipoPao;
            button.style.backgroundColor = gerarCorAleatoria();
            button.addEventListener("click", () => cadastrarFornada(pao.id));

            const colunaAtual = index % 3;
            colunas[colunaAtual].appendChild(button);
        });

        colunas.forEach((coluna) => divBotoesPao.appendChild(coluna));
    }

    function gerarCorAleatoria() {
        const cores = ["#FF5733", "#33FF57", "#5733FF", "#33AFFF", "#FF33AF", "#AF33FF"];
        return cores[Math.floor(Math.random() * cores.length)];
    }

    async function cadastrarFornada(paoId) {
        try {
            const response = await fetch("http://localhost:8080/fornada", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    pao: {
                        id: paoId,
                    },
                }),
            });

            if (!response.ok) {
                throw new Error("Erro ao cadastrar a fornada.");
            }

            exibirMensagem("Fornada criada com sucesso!", "alert-success");
        } catch (error) {
            console.error(error);
            exibirMensagem("Erro ao cadastrar a fornada.", "alert-danger");
        }
    }

    function exibirMensagem(mensagem, classe) {
        const alertDiv = document.createElement("div");
        alertDiv.className = `alert ${classe}`;
        alertDiv.textContent = mensagem;

        divMensagem.innerHTML = ""; // Limpar mensagens anteriores
        divMensagem.appendChild(alertDiv);

        // Remover mensagem após 3 segundos
        setTimeout(() => {
            divMensagem.innerHTML = "";
        }, 3000);
    }

    carregarPaes();
});
