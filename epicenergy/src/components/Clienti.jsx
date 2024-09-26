import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

const Clients = () => {
  return (
    <div className="container mt-5">
      <h1 className="text-center">Gestione Clienti</h1>

      {/* Form per aggiungere un nuovo cliente */}
      <div className="card mt-4">
        <div className="card-header">
          <h2>Aggiungi Cliente</h2>
        </div>
        <div className="card-body">
          <form id="clienteForm">
            <div className="form-row">
              <div className="form-group col-md-6">
                <label htmlFor="ragioneSociale">Ragione Sociale</label>
                <input
                  type="text"
                  className="form-control"
                  id="ragioneSociale"
                  placeholder="Inserisci Ragione Sociale"
                />
              </div>
              <div className="form-group col-md-6">
                <label htmlFor="partitaIva">Partita IVA</label>
                <input
                  type="text"
                  className="form-control"
                  id="partitaIva"
                  placeholder="Inserisci Partita IVA"
                />
              </div>
            </div>
            <div className="form-row">
              <div className="form-group col-md-6">
                <label htmlFor="email">Email</label>
                <input
                  type="email"
                  className="form-control"
                  id="email"
                  placeholder="Inserisci Email"
                />
              </div>
              <div className="form-group col-md-6">
                <label htmlFor="telefono">Telefono</label>
                <input
                  type="text"
                  className="form-control"
                  id="telefono"
                  placeholder="Inserisci Telefono"
                />
              </div>
            </div>
            <div className="form-row">
              <div className="form-group col-md-6">
                <label htmlFor="nomeContatto">Nome Contatto</label>
                <input
                  type="text"
                  className="form-control"
                  id="nomeContatto"
                  placeholder="Inserisci Nome Contatto"
                />
              </div>
              <div className="form-group col-md-6">
                <label htmlFor="cognomeContatto">Cognome Contatto</label>
                <input
                  type="text"
                  className="form-control"
                  id="cognomeContatto"
                  placeholder="Inserisci Cognome Contatto"
                />
              </div>
            </div>
            <div className="form-row">
              <div className="form-group col-md-6">
                <label htmlFor="fatturatoAnnuale">Fatturato Annuale</label>
                <input
                  type="number"
                  className="form-control"
                  id="fatturatoAnnuale"
                  placeholder="Inserisci Fatturato Annuale"
                />
              </div>
              <div className="form-group col-md-6">
                <label htmlFor="pec">PEC</label>
                <input
                  type="text"
                  className="form-control"
                  id="pec"
                  placeholder="Inserisci PEC"
                />
              </div>
            </div>
            <button type="submit" className="btn btn-primary">
              Aggiungi Cliente
            </button>
          </form>
        </div>
      </div>

      {/* Tabella per visualizzare l'elenco dei clienti */}
      <div className="card mt-5">
        <div className="card-header">
          <h2>Elenco Clienti</h2>
          <form className="form-inline mt-3">
            <input
              className="form-control mr-sm-2"
              type="search"
              placeholder="Cerca per nome"
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
                <th scope="col">Ragione Sociale</th>
                <th scope="col">Partita IVA</th>
                <th scope="col">Email</th>
                <th scope="col">Telefono</th>
                <th scope="col">Fatturato Annuale</th>
                <th scope="col">PEC</th>
                <th scope="col">Nome Contatto</th>
                <th scope="col">Cognome Contatto</th>
                <th scope="col">Azione</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Rossi S.p.A.</td>
                <td>12345678901</td>
                <td>info@rossispa.it</td>
                <td>+39 012 3456789</td>
                <td>500,000 €</td>
                <td>pec@rossispa.it</td>
                <td>Mario</td>
                <td>Rossi</td>
                <td>
                  <button className="btn btn-warning btn-sm">Modifica</button>
                  <button className="btn btn-danger btn-sm">Elimina</button>
                </td>
              </tr>
              {/* Aggiungi ulteriori righe come necessario */}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default Clients;
