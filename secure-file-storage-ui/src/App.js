import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import HomePage from "./pages/HomePage";
import AboutPage from "./pages/AboutPage";
import ContactPage from "./pages/ContactPage";
import Dashboard from "./pages/Dashboard";
import UploadPage from "./pages/UploadPage";
import ListPage from "./pages/ListPage";

export default function App() {
  return (
    <Router>
      <Navbar />
      <div className="flex flex-col min-h-screen">
        <div className="flex-grow">
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/about" element={<AboutPage />} />
            <Route path="/contact" element={<ContactPage />} />
            <Route path="/dashboard" element={<Dashboard />}>
              <Route path="upload" element={<UploadPage />} />
              <Route path="list" element={<ListPage />} />
            </Route>
          </Routes>
        </div>
        <Footer />
      </div>
    </Router>
  );
}
