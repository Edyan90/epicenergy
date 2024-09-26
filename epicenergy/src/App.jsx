import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import Fatture from "./components/Fatture";
import Clients from "./components/Clients";

const App = () => {
  return (
    <Router>
      <Navbar />
      <div className="container mt-5">
        <Routes>
          <Route path="/fatture" element={<Fatture />} />
          <Route path="/clients" element={<Clients />} />
          <Route path="/" element={<h1>Benvenuto!</h1>} />
        </Routes>
      </div>
      <Footer />
    </Router>
  );
};

export default App;
