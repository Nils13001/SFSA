const AboutPage = () => {
    return (
      <div className="max-w-4xl mx-auto p-6 bg-white shadow-lg rounded-lg mt-10">
        <h2 className="text-3xl font-bold mb-4">About Secure File Storage</h2>
        <p className="text-lg mb-4">
  Secure File Storage is an enterprise-grade cloud storage solution that uses <strong>Hybrid Cryptography (AES + RSA)</strong> to ensure that your files remain private and secure.
</p>

        <h3 className="text-2xl font-semibold mt-6">Features</h3>
        <ul className="list-disc ml-6 mt-2 text-lg">
          <li>256-bit AES Encryption for file security</li>
          <li>RSA Encryption for secure key management</li>
          <li>Cloud-based storage with AWS S3</li>
          <li>User authentication & access control</li>
        </ul>
        <p className="mt-6">Our goal is to provide <strong>high-level security</strong> for enterprises, startups, and individuals.</p>
      </div>
    );
  };
  
  export default AboutPage;
  