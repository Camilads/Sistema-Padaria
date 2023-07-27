document.addEventListener("DOMContentLoaded", function () {
    const urlApi = "http://localhost:8080/fornada";
    const paoIdsExibidos = []; 

    async function buscarUltimasFornadas() {
        try {
            const response = await fetch(urlApi);
            if (!response.ok) {
                throw new Error("Erro ao buscar as últimas fornadas.");
            }

            const fornadas = await response.json();
            mostrarUltimasFornadas(fornadas);
        } catch (error) {
            throw error;
        }
    }

    function formatarTempo(tempo) {
        const segundos = Math.floor((tempo / 1000) % 60);
        const minutos = Math.floor((tempo / 1000 / 60) % 60);
        const horas = Math.floor((tempo / 1000 / 60 / 60) % 24);
        const dias = Math.floor(tempo / 1000 / 60 / 60 / 24);

        let mensagem = "";
        if (dias > 0) {
            mensagem += `${dias} dia${dias === 1 ? '' : 's'}, `;
        }
        if (horas > 0) {
            mensagem += `${horas} hora${horas === 1 ? '' : 's'}, `;
        }
        if (minutos > 0) {
            mensagem += `${minutos} minuto${minutos === 1 ? '' : 's'}`;
        }

        return mensagem;
    }

    function mostrarUltimasFornadas(fornadas) {
        const ultimasFornadasDiv = document.getElementById("ultimasFornadas");
        //ultimasFornadasDiv.innerHTML = "";

        fornadas.forEach((fornada) => {
            const pao = fornada.pao;
            const paoId = pao.id;
            const tempoPreparo = pao.tempoPreparo * 60 * 1000; 
            const horaFornada = new Date(fornada.horaInicio).getTime();
            const horaAtual = new Date().getTime();
            const tempoDecorrido = horaAtual - horaFornada;

            if(!paoIdsExibidos.includes(paoId)){

            const card = document.createElement("div");
            card.classList.add("col-md-4");
            card.innerHTML = `
                <div class="card mt-3">
                    <div class="card-body">
                        <h5 class="card-title">${pao.tipoPao}</h5>
                        <p class="card-text">${tempoDecorrido >= tempoPreparo ? 'Fornada pronta há ' + 
                        formatarTempo(tempoDecorrido - tempoPreparo) : 'Tempo restante: ' + 
                        formatarTempo(tempoPreparo - tempoDecorrido)}</p>
                    </div>
                </div>
            `;

            ultimasFornadasDiv.appendChild(card);
            paoIdsExibidos.push(paoId);

            }else{

                const paoCards = document.querySelectorAll(".card-title");
                paoCards.forEach((paoCard) => {
                    if(paoCard.textContent === pao.tipoPao){
                        const cardText = paoCard.nextElementSibling;
                        cardText.textContent = tempoDecorrido >= tempoPreparo
                            ? "Fornada pronta há " + formatarTempo(tempoDecorrido - tempoPreparo)
                            : "Tempo restante: " + formatarTempo(tempoPreparo - tempoDecorrido);
                    } 
            });
        }
    });

}

    buscarUltimasFornadas();
});
