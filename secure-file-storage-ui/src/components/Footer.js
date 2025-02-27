const Footer = () => {
    return (
      <footer className="bg-gray-900 text-white text-center p-6 mt-8">
        <div className="max-w-6xl mx-auto">
          <p className="text-lg font-semibold">Secure File Storage</p>
          <p className="text-sm opacity-80">Enterprise Cloud Security & File Management</p>
          <div className="mt-3 flex justify-center space-x-4">
            <a href="/about" className="hover:underline">About</a>
            <a href="/contact" className="hover:underline">Contact</a>
            <a href="/dashboard" className="hover:underline">Dashboard</a>
          </div>
          <p className="mt-4 text-xs opacity-70">© {new Date().getFullYear()} Secure File Storage. All Rights Reserved.</p>
        </div>
      </footer>
    );
  };
  
  export default Footer;
  