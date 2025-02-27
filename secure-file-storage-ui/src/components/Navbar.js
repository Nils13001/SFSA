import { useState } from "react";
import { Link } from "react-router-dom";
import { Sun, Moon } from "lucide-react";

const Navbar = () => {
  const [darkMode, setDarkMode] = useState(false);

  const toggleDarkMode = () => {
    setDarkMode(!darkMode);
    document.documentElement.classList.toggle("dark");
  };

  return (
    <nav className="bg-gray-900 dark:bg-gray-800 text-white p-4 shadow-lg">
      <div className="max-w-6xl mx-auto flex justify-between items-center">
        <h1 className="text-3xl font-semibold tracking-wide">Secure Storage</h1>
        <div>
          <Link className="px-4 hover:text-gray-300" to="/">Home</Link>
          <Link className="px-4 hover:text-gray-300" to="/about">About</Link>
          <Link className="px-4 hover:text-gray-300" to="/contact">Contact</Link>
          <Link className="px-4 hover:text-gray-300" to="/dashboard">Dashboard</Link>
        </div>
        <button onClick={toggleDarkMode} className="ml-4 p-2 rounded-full bg-gray-700 hover:bg-gray-600">
          {darkMode ? <Sun className="text-yellow-300" /> : <Moon className="text-white" />}
        </button>
      </div>
    </nav>
  );
};

export default Navbar;
