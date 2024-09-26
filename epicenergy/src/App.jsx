import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Fatture from './components/Fatture';

  return (
    <Router>
      <Navbar />
      <div className="container mt-5">
        <Routes>
          <Route path="/fatture" element={<Fatture />} />
          <Route path="/clienti" element={<Clienti />} />
          <Route path="/login" element={<Login />} />
          <Route path="/registrati" element={<Registrati />} />
          <Route path="/" element={<h1>Benvenuto!</h1>} />
        </Routes>
      </div>
      <Footer />
    </Router>
  );


export default App;