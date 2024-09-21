async function callSoapService(email, password) {
    const soapRequest = `
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:con="http://controllers.jaxws.com/">
   <soapenv:Header/>
   <soapenv:Body>
      <con:LOGIN>
         <EMAIL>${email}</EMAIL>
         <PASSWORD>${password}</PASSWORD>
      </con:LOGIN>
   </soapenv:Body>
</soapenv:Envelope>
    `;

    try {
        const response = await fetch('http://DESKTOP-5QHEAJA:8080/jaxws-1.0-SNAPSHOT/AuthController?wsdl', {
            method: 'POST',
            headers: {
                'Content-Type': 'text/xml; charset=utf-8',
                'SOAPAction': 'LOGIN'
            },
            body: soapRequest
        });

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const responseText = await response.text();
        console.log(responseText);  // Ver la respuesta SOAP como texto
    } catch (error) {
        console.error('Error:', error);
    }
}
function parseSoapResponse(responseText) {
    const parser = new DOMParser();
    const xmlDoc = parser.parseFromString(responseText, "text/xml");

    // Aquí extraes los datos del XML
    const result = xmlDoc.getElementsByTagName("return")[0].textContent; // Ajusta esto según tu respuesta SOAP
    console.log("Resultado:", result);
}


document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('loginForm');

    form.addEventListener('submit', async (event) => {
        event.preventDefault(); // Evita el envío del formulario

        const email = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        // Llama a la función que consume el servicio SOAP
        await callSoapService(email, password);
    });
});
//callSoapService('example@example.com', '12345');
