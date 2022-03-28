import Navbar from "./components/Navbar";
import '././components/assets/reset.css';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import MainContainer from "./components/main";
import Listing_Contact from "./components/pages/listing_contact";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={ <MainContainer /> } />
        <Route path="/listaContatos" element={ <Listing_Contact /> } />
      </Routes>
    </BrowserRouter>
    
  );
}

export default App;
