sedex = "";
pac = ""

function calculaFrete(cep_origem, cep_destino, pesoEntrega, valorEntrega) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			var resposta = JSON.parse(xhttp.responseText);
			pac = resposta.pac;
			sedex = resposta.sedex;
		}
	}
	xhttp.open("GET", "http://developers.agenciaideias.com.br/correios/frete/json/"+cep_origem+"/"+cep_destino+"/"+pesoEntrega+"/"+valorEntrega, true);
	xhttp.send();
}
