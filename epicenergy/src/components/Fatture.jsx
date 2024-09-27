import { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";

const Fatture = () => {
  const [fatture, setFatture] = useState([]);
  const [formData, setFormData] = useState({
    dataFattura: "",
    numeroFattura: "",
    importoFattura: "",
    statoFattura: "",
    clienteFattura: "",
  });
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch("http://localhost:3001/fatture")
      .then((response) => response.json())
      .then((data) => {
        console.log("Dati ricevuti dal backend:", data);
        setFatture(data);
      })
      .catch((error) => {
        console.error("Errore nel recupero delle fatture:", error);
        setError("Errore nel recupero delle fatture");
      });
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Form inviato");

    const newFattura = {
      data: formData.dataFattura,
      numeroFattura: formData.numeroFattura,
      importo: formData.importoFattura,
      stato: formData.statoFattura,
      clienteId: formData.clienteFattura,
    };

    console.log("Dati della nuova fattura:", newFattura);

    fetch("http://localhost:3001/fatture", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newFattura),
      mode: "cors",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Errore nella risposta dal server");
        }
        return response.json();
      })
      .then((data) => {
        console.log("Risposta dal server:", data);
        alert("Fattura aggiunta con successo");
        setFatture([...fatture, data]);
        setFormData({
          dataFattura: "",
          numeroFattura: "",
          importoFattura: "",
          statoFattura: "",
          clienteFattura: "",
        });
      })
      .catch((error) => {
        console.error("Errore nell'aggiunta della fattura:", error);
        setError("Errore nell'aggiunta della fattura");
      });
  };

  const handleInputChange = (e) => {
    const { id, value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [id]: value,
    }));
  };

  return (
    <div className="container mt-5">
      <h1 className="text-center">Gestione Fatture</h1>

      {/* Errore di invio */}
      {error && <div className="alert alert-danger">{error}</div>}

      {/* Form per aggiungere una nuova fattura */}
      <div className="card mt-4">
        <div className="card-header">
          <h2>Aggiungi Fattura</h2>
        </div>
        <div className="card-body">
          <form id="fatturaForm" onSubmit={handleSubmit}>
            <div className="form-row">
              <div className="form-group col-md-6">
                <label htmlFor="dataFattura">Data Fattura</label>
                <input
                  type="date"
                  className="form-control"
                  id="dataFattura"
                  value={formData.dataFattura}
                  onChange={handleInputChange}
                  placeholder="Inserisci Data Fattura"
                  required
                />
              </div>
              <div className="form-group col-md-6">
                <label htmlFor="numeroFattura">Numero Fattura</label>
                <input
                  type="number"
                  className="form-control"
                  id="numeroFattura"
                  value={formData.numeroFattura}
                  onChange={handleInputChange}
                  placeholder="Inserisci Numero Fattura"
                  required
                />
              </div>
            </div>
            <div className="form-row">
              <div className="form-group col-md-6">
                <label htmlFor="importoFattura">Importo</label>
                <input
                  type="number"
                  step="0.01"
                  className="form-control"
                  id="importoFattura"
                  value={formData.importoFattura}
                  onChange={handleInputChange}
                  placeholder="Inserisci Importo"
                  required
                />
              </div>
              <div className="form-group col-md-6">
                <label htmlFor="statoFattura">Stato Fattura</label>
                <select
                  className="form-control"
                  id="statoFattura"
                  value={formData.statoFattura}
                  onChange={handleInputChange}
                  required
                >
                  <option value="">SELEZIONA STATO</option>
                  <option value="PAGATA">PAGATA</option>
                  <option value="NON PAGATA">NON PAGATA</option>
                  <option value="IN ATTESA">IN ATTESA</option>
                  <option value="ANNULLATA">ANNULLATA</option>
                </select>
              </div>
            </div>
            <div className="form-row">
              <div className="form-group col-md-12">
                <label htmlFor="clienteFattura">Cliente Associato</label>
                <input
                  type="text"
                  className="form-control"
                  id="clienteFattura"
                  value={formData.clienteFattura}
                  onChange={handleInputChange}
                  placeholder="Inserisci Cliente ID o Nome Cliente"
                  required
                />
              </div>
            </div>
            <button type="submit" className="btn btn-primary">
              Aggiungi Fattura
            </button>
          </form>
        </div>
      </div>

      {/* Tabella per visualizzare l'elenco delle fatture */}
      <div className="card mt-5">
        <div className="card-header">
          <h2>Elenco Fatture</h2>
        </div>
        <div className="card-body">
          <table className="table table-striped">
            <thead>
              <tr>
                <th>Numero Fattura</th>
                <th>Data</th>
                <th>Importo</th>
                <th>Stato</th>
                <th>Cliente</th>
                <th>Azione</th>
              </tr>
            </thead>
            <tbody>
              {fatture.map((fattura) => (
                <tr key={fattura.id}>
                  <td>{fattura.numeroFattura}</td>
                  <td>{fattura.data}</td>
                  <td>{fattura.importo} €</td>
                  <td>{fattura.stato}</td>
                  <td>{fattura.clienteId}</td>
                  <td>
                    <button className="btn btn-warning btn-sm">Modifica</button>
                    <button className="btn btn-danger btn-sm">Elimina</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default Fatture;
