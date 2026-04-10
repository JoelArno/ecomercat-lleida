// HU-03 – Carret de compra
// Feature branch: feature/carret-compra

function Carret({ items = [] }) {
  const total = items.reduce((acc, item) => acc + item.preu * item.quantitat, 0);

  return (
    <div className="carret">
      <h2>El teu carret</h2>
      {items.length === 0 ? (
        <p>El carret és buit</p>
      ) : (
        <>
          {items.map((item) => (
            <div key={item.id} className="carret-item">
              <span>{item.nom}</span>
              <span>{item.quantitat} x {item.preu} €</span>
            </div>
          ))}
          <p className="total"><strong>Total: {total.toFixed(2)} €</strong></p>
        </>
      )}
    </div>
  );
}

export default Carret;
