function sendJSON(){
    let deposit = document.querySelector('#sum');
    fetch( 'http://localhost:8000/balance',
        {
            method: "POST",
            headers: { 'Content-Type': 'application/json;charset=utf-8' },
            body: JSON.stringify({deposit: deposit.value})
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
        })
        .catch(e => console.log(e));
}
