import { useState } from "react";

const ContactPage = () => {
  const [formData, setFormData] = useState({ name: "", email: "", message: "" });
  const [status, setStatus] = useState("");

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setStatus("✅ Message sent successfully!");
  };

  return (
    <div className="max-w-lg mx-auto p-6 bg-white shadow-lg rounded-lg mt-10">
      <h2 className="text-3xl font-bold mb-4">Contact Us</h2>
      <p className="mb-4">Have questions? Fill out the form below.</p>
      <form onSubmit={handleSubmit}>
        <input type="text" name="name" placeholder="Your Name" required className="w-full p-3 border rounded-lg mb-4" onChange={handleChange} />
        <input type="email" name="email" placeholder="Your Email" required className="w-full p-3 border rounded-lg mb-4" onChange={handleChange} />
        <textarea name="message" placeholder="Your Message" required className="w-full p-3 border rounded-lg mb-4" onChange={handleChange}></textarea>
        <button type="submit" className="bg-blue-600 text-white px-6 py-2 rounded-lg shadow-md hover:bg-blue-700 transition-all">Send</button>
      </form>
      {status && <p className="mt-4 text-green-600">{status}</p>}
    </div>
  );
};

export default ContactPage;
