import { useState } from "react";
import { Form, Button } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

const Registrati = () => {
  const [username, setUsername] = useState("");
  const [mail, setMail] = useState("");
  const [password, setPassword] = useState("");
  const [nome, setNome] = useState("");
  const [cognome, setCognome] = useState("");
  const [ruoli, setRuoli] = useState("");
  /* const [avatar, setAvatar] = useState(null); */

  const handleSubmit = async (e) => {
    e.preventDefault();

    const userData = {
      username,
      mail,
      password,
      nome,
      cognome,
      ruoli,
      /* avatar, */
    };

    try {
      const response = await fetch("http://localhost:3001/auth/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(userData),
      });

      if (response.ok) {
        const data = await response.json();
        alert("Registrazione avvenuta con successo!");
      } else {
        alert("Errore durante la registrazione");
      }
    } catch (error) {
      console.error("Errore:", error);
      alert("Errore durante la registrazione!");
    }
  };

  return (
    <div className="container mt-5">
      <h1 className="text-center">Registrazione</h1>
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="formUsername">
          <Form.Label>Username</Form.Label>
          <Form.Control
            type="text"
            placeholder="Inserisci username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </Form.Group>

        <Form.Group controlId="formEmail">
          <Form.Label>Email</Form.Label>
          <Form.Control
            type="email"
            placeholder="Inserisci email"
            value={mail}
            onChange={(e) => setMail(e.target.value)}
          />
        </Form.Group>

        <Form.Group controlId="formPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </Form.Group>

        <Form.Group controlId="formName">
          <Form.Label>Nome</Form.Label>
          <Form.Control
            type="text"
            placeholder="Inserisci nome"
            value={nome}
            onChange={(e) => setNome(e.target.value)}
          />
        </Form.Group>

        <Form.Group controlId="formSurname">
          <Form.Label>Cognome</Form.Label>
          <Form.Control
            type="text"
            placeholder="Inserisci cognome"
            value={cognome}
            onChange={(e) => setCognome(e.target.value)}
          />
        </Form.Group>

        <Form.Group controlId="formRuolo">
          <Form.Label>Ruolo</Form.Label>
          <Form.Control
            type="text"
            placeholder="Inserisci ruolo"
            value={ruoli}
            onChange={(e) => setRuoli(e.target.value)}
          />
        </Form.Group>

        {/*  <Form.Group controlId="formAvatar">
          <Form.Label>Avatar</Form.Label>
          <Form.Control type="file" accept="image/*" onChange={(e) => setAvatar(e.target.files[0])} />
        </Form.Group> */}

        <Button variant="primary" type="submit" className="mt-3">
          Registrati
        </Button>
      </Form>
    </div>
  );
};

export default Registrati;
