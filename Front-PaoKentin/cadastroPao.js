document.addEventListener("DOMContentLoaded", function () {
    const urlApi = "http://localhost:8080/pao";

    async function cadastrarPao(event) {
        event.preventDefault();

        const tipoPao = document.getElementById("tipoPao").value;
        const tempoPreparo = document.getElementById("tempoPreparo").value;
        const descricao = document.getElementById("descricao").value;

        const novoPao = {
            tipoPao,
            tempoPreparo,
            descricao,
        };

        try {
            const response = await fetch(urlApi, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(novoPao),
            });

            if (!response.ok) {
                const errorMessage = await response.text();
                exibirMensagemErro(errorMessage);
                return;
            }

            exibirMensagemSucesso("Pão cadastrado com sucesso!");
            window.location.href = "telaDonoPadaria.html"; 
        } catch (error) {
            console.error(error);
            exibirMensagemErro("Erro ao cadastrar o pão.");
        }
    }

    function exibirMensagemSucesso(mensagem) {
        const mensagemDiv = document.getElementById("mensagem");
        mensagemDiv.textContent = mensagem;
        mensagemDiv.style.color = "green"; 
    }

    function exibirMensagemErro(mensagem) {
        const mensagemDiv = document.getElementById("mensagem");
        mensagemDiv.textContent = mensagem;
        mensagemDiv.style.color = "red"; 
    }

    const formCadastroPao = document.getElementById("formCadastroPao");
    formCadastroPao.addEventListener("submit", cadastrarPao);
});
