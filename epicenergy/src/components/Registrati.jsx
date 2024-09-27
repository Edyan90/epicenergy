import { Form, Button } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

const Registrati = () => {
  return (
    <div className="container mt-5">
      <h1 className="text-center">Registrazione</h1>
      <Form>
        <Form.Group controlId="formUsername">
          <Form.Label>Username</Form.Label>
          <Form.Control type="text" placeholder="Inserisci username" />
        </Form.Group>

        <Form.Group controlId="formEmail">
          <Form.Label>Email</Form.Label>
          <Form.Control type="email" placeholder="Inserisci email" />
        </Form.Group>

        <Form.Group controlId="formPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control type="password" placeholder="Password" />
        </Form.Group>

        <Form.Group controlId="formName">
          <Form.Label>Nome</Form.Label>
          <Form.Control type="text" placeholder="Inserisci nome" />
        </Form.Group>

        <Form.Group controlId="formSurname">
          <Form.Label>Cognome</Form.Label>
          <Form.Control type="text" placeholder="Inserisci cognome" />
        </Form.Group>

        <Form.Group controlId="formAvatar">
          <Form.Label>Avatar</Form.Label>
          <Form.Control type="file" accept="image/*" />
        </Form.Group>

        <Button variant="primary" type="submit" className="mt-3">
          Registrati
        </Button>
      </Form>
    </div>
  );
};

export default Registrati;
