
var urlForm = document.getElementById("urlForm");
var out = document.getElementById("out");

function tap() {

    url = urlForm.value;

    fetch('/data', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ 'url': url })
        })
            .then(response => response.text())
                .then(responseText => out.textContent = 'domain.ru/' + responseText); //TODO доделать норм вывод если bad request
}