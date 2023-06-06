
var urlForm = document.getElementById("urlForm");
var out = document.getElementById("out");

async function tap() {

    url = urlForm.value;

    let response = await fetch('/generate', { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify({ 'url': url })});

    if(response.ok) {
        let responseText = await response.text();
        out.textContent = '/' + responseText;
    } else {
        out.textContent = 'Url is not valid';
    }
}