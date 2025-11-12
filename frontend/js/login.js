document.getElementById('loginBtn').addEventListener('click', function() {
   const username = document.getElementById('username').value;
   const password = document.getElementById('password').value; 
    console.log(username);
    console.log(password);

   if (username === 'admin' && password === 'admin'){
        window.location.href = 'index.html';
   } else {
        alert('Credenciais inválidas. Tente novamente.');
   }
});