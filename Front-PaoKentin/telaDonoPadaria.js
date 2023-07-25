document.addEventListener("DOMContentLoaded", function () {
    const urlApi = "http://localhost:8080/pao";
    const tabelaPaesCorpo = document.getElementById("tabelaPaesCorpo");

    async function carregarPaesCadastrados() {
        try {
            const response = await fetch(urlApi);
            if (!response.ok) {
                throw new Error("Erro ao carregar os pães cadastrados.");
            }

            const paesCadastrados = await response.json();
            atualizarTabelaPaes(paesCadastrados);
        } catch (error) {
            console.error(error);
            alert(error.message || "Erro ao carregar os pães cadastrados.");
        }
    }

    function atualizarTabelaPaes(paes) {
        if (!tabelaPaesCorpo) return;

        tabelaPaesCorpo.innerHTML = "";

        paes.forEach((pao) => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${pao.tipoPao}</td>
                <td>${pao.tempoPreparo}</td>
                <td>${pao.descricao}</td>
            `;
            tabelaPaesCorpo.appendChild(row);
        });
    }



    carregarPaesCadastrados();
});
