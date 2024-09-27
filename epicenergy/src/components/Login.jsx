import { Form, Button } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

const Login = () => {
  return (
    <div className="container mt-5">
    <div className="row justify-content-center">
      <div className="col-md-6">
        <h1 className="text-center">Login</h1>
        <Form>
          <Form.Group controlId="formEmail">
            <Form.Label>Email</Form.Label>
            <Form.Control type="email" placeholder="Inserisci email" />
          </Form.Group>

          <Form.Group controlId="formPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control type="password" placeholder="Password" />
          </Form.Group>

          <Button variant="primary" type="submit" className="mt-3">
            Accedi
          </Button>
        </Form>
      </div>
    </div>
  </div>
);
};

export default Login;
