import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Navbar from "./components/Navbar";
import Fatture from "./components/Fatture";
import Clients from "./components/Clients";
import Login from "./components/Login"; // Import del componente Login
import Registrati from "./components/Registrati";

const App = () => {
  return (
    <Router>
      <Navbar />
      <div className="container mt-5 ">
        <Routes>
          <Route path="/fatture" element={<Fatture />} />
          <Route path="/clients" element={<Clients />} />
          <Route path="/" element={<Login />} />
          <Route path="/auth/register" element={<Registrati />} />

        </Routes>
      </div>

    </Router>
  );
};

export default App;
