// HU-01 – Catàleg de productes
// Feature branch: feature/cataleg-productes

function Cataleg() {
  const productes = [
    { id: 1, nom: "Pomes ecològiques", preu: 2.50, categoria: "fruita" },
    { id: 2, nom: "Tomàquets km0", preu: 1.80, categoria: "verdura" },
    { id: 3, nom: "Iogurt ecològic", preu: 1.20, categoria: "lactics" },
  ];

  return (
    <div className="cataleg">
      <h1>Catàleg de productes</h1>
      <div className="productes-grid">
        {productes.map((p) => (
          <div key={p.id} className="producte-card">
            <h3>{p.nom}</h3>
            <p>{p.preu} €</p>
            <span className="categoria">{p.categoria}</span>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Cataleg;
