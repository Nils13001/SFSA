import { Link } from "react-router-dom";

const HomePage = () => {
  return (
    <div className="h-screen flex flex-col justify-center items-center bg-gradient-to-br from-blue-600 to-indigo-800 text-white">
      <h1 className="text-6xl font-bold mb-6 tracking-tight">Enterprise Secure File Storage</h1>
      <p className="text-lg opacity-90 mb-6 max-w-2xl text-center">
  Protect your files with <strong>hybrid encryption</strong> and <strong>cloud security</strong>.  
  AES + RSA Encryption for <strong>top-tier safety</strong>.</p>

      <Link to="/dashboard" className="bg-white text-indigo-800 px-8 py-3 rounded-lg shadow-lg text-lg font-semibold hover:bg-gray-100 transition-all">
        Get Started
      </Link>
    </div>
  );
};

export default HomePage;
