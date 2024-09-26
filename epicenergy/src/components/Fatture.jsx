import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

const Fatture = () => {
  return (
    <div className="container mt-5">
      <h1 className="text-center">Gestione Fatture</h1>

      {/* Form per aggiungere una nuova fattura */}
      <div className="card mt-4">
        <div className="card-header">
          <h2>Aggiungi Fattura</h2>
        </div>
        <div className="card-body">
          <form id="fatturaForm">
            <div className="form-row">
              <div className="form-group col-md-6">
                <label htmlFor="dataFattura">Data Fattura</label>
                <input
                  type="date"
                  className="form-control"
                  id="dataFattura"
                  placeholder="Inserisci Data Fattura"
                />
              </div>
              <div className="form-group col-md-6">
                <label htmlFor="numeroFattura">Numero Fattura</label>
                <input
                  type="number"
                  className="form-control"
                  id="numeroFattura"
                  placeholder="Inserisci Numero Fattura"
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
                  placeholder="Inserisci Importo"
                />
              </div>
              <div className="form-group col-md-6">
                <label htmlFor="statoFattura">Stato Fattura</label>
                <select className="form-control" id="statoFattura">
                  <option>SELEZIONA STATO</option>
                  <option>PAGATA</option>
                  <option>NON PAGATA</option>
                  <option>IN ATTESA</option>
                  <option>ANNULLATA</option>
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
                  placeholder="Inserisci Cliente ID o Nome Cliente"
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
          <form className="form-inline mt-3">
            <input
              className="form-control mr-sm-2"
              type="search"
              placeholder="Cerca per numero fattura"
              aria-label="Search"
            />
            <button className="btn btn-outline-success my-2 my-sm-0" type="submit">
              Cerca
            </button>
          </form>
        </div>
        <div className="card-body">
          <table className="table table-striped">
            <thead>
              <tr>
                <th scope="col">Numero Fattura</th>
                <th scope="col">Data</th>
                <th scope="col">Importo</th>
                <th scope="col">Stato</th>
                <th scope="col">Cliente</th>
                <th scope="col">Azione</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>001</td>
                <td>2024-01-10</td>
                <td>1500.00 €</td>
                <td>PAGATA</td>
                <td>Mario Rossi</td>
                <td>
                  <button className="btn btn-warning btn-sm">Modifica</button>
                  <button className="btn btn-danger btn-sm">Elimina</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default Fatture;
