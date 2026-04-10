// HU-02 – Registre i login
// Feature branch: feature/registre-usuaris

function Login() {
  return (
    <div className="login-form">
      <h2>Inicia sessió</h2>
      <input type="email" placeholder="Email" />
      <input type="password" placeholder="Contrasenya" />
      <button type="submit">Entrar</button>
      <p>No tens compte? <a href="/registre">Registra't</a></p>
    </div>
  );
}

export default Login;
