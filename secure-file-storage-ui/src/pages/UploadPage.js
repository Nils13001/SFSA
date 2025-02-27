// import React, { useState } from "react";
// import axios from "axios";

// const UploadPage = () => {
//   const [filePath, setFilePath] = useState("");
//   const [action, setAction] = useState("upload");
//   const [newFileName, setNewFileName] = useState("");

//   const handleUpload = async () => {
//     if (!filePath) {
//       alert("❌ Please enter a valid file path.");
//       return;
//     }

//     // **Normalize path (convert to lowercase)**
//     const normalizedFilePath = filePath.trim().toLowerCase();
//     setFilePath(normalizedFilePath);

//     try {
//       const formData = new FormData();
//       formData.append("file", normalizedFilePath);
//       formData.append("action", action);
//       if (action === "encrypt") {
//         formData.append("newFileName", newFileName);
//       }

//       const response = await axios.post("http://localhost:8080/s3/upload", formData);

//       if (response.status === 200) {
//         const responseData = response.data.toLowerCase(); // Ensure case insensitivity

//         if (responseData.includes("file exists")) {
//           alert("⚠️ File with the same name already exists at the destination.");
//         } else if (responseData.includes("wrong path")) {
//           alert("❌ Error: The provided file path is incorrect.");
//         } else if (responseData.includes("successfully")) {
//           alert("✅ File uploaded successfully!");
//         } else {
//           alert(`❌ Unexpected Response: ${response.data}`);
//         }
//       }
//     } catch (error) {
//       if (error.response) {
//         alert(`❌ Server Error: ${error.response.status} - ${error.response.data}`);
//       } else if (error.request) {
//         alert("❌ No response received from server.");
//       } else {
//         alert(`❌ Request Error: ${error.message}`);
//       }
//     }
//   };

//   return (
//     <div className="upload-container">
//       <h2 className="text-xl font-bold mb-4">Upload File</h2>
//       <input
//         type="text"
//         placeholder="Enter file path"
//         value={filePath}
//         onChange={(e) => setFilePath(e.target.value)}
//         className="border p-2 w-full mb-3"
//       />
//       <select
//         value={action}
//         onChange={(e) => setAction(e.target.value)}
//         className="border p-2 w-full mb-3"
//       >
//         <option value="upload">Upload</option>
//         <option value="encrypt">Encrypt & Upload</option>
//       </select>
//       {action === "encrypt" && (
//         <input
//           type="text"
//           placeholder="Enter new file name"
//           value={newFileName}
//           onChange={(e) => setNewFileName(e.target.value)}
//           className="border p-2 w-full mb-3"
//         />
//       )}
//       <button
//         onClick={handleUpload}
//         className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
//       >
//         Upload
//       </button>
//     </div>
//   );
// };

// export default UploadPage;

import React, { useState } from "react";
import axios from "axios";

const UploadPage = () => {
  const [filePath, setFilePath] = useState("");
  const [action, setAction] = useState("upload");
  const [newFileName, setNewFileName] = useState("");
  const [sendEmail, setSendEmail] = useState(false);
  const [email, setEmail] = useState("");

  const handleUpload = async () => {
    if (!filePath) {
      alert("❌ Please enter a valid file path.");
      return;
    }
  
    const formData = new FormData();
    formData.append("file", filePath.trim());
    formData.append("action", action);
    formData.append("sendEmail", sendEmail);
    if (sendEmail) formData.append("email", email);
    if (action === "encrypt") formData.append("newFileName", newFileName);
  
    try {
      const response = await axios.post("http://localhost:8080/s3/upload", formData);
  
      const responseData = response.data;
  
      if (responseData.includes("Error")) {
        alert(`❌ Upload failed: ${responseData}`);
      } else if (responseData.includes("File already exists")) {
        alert(`⚠️ Warning: ${responseData}`);
      } else if (responseData.includes("File uploaded successfully")) {
        alert(`✅ Success: ${responseData}`);
      } else {
        alert(`ℹ️ Unexpected Response: ${responseData}`);
      }
    } catch (error) {
      alert(`❌ Upload failed: ${error.response?.data || error.message}`);
    }
  };

  

  return (
    <div className="upload-container">
      <h2 className="text-xl font-bold mb-4">Upload File</h2>
      <input type="text" placeholder="Enter file path" value={filePath} onChange={(e) => setFilePath(e.target.value)} className="border p-2 w-full mb-3"/>
      <select value={action} onChange={(e) => setAction(e.target.value)} className="border p-2 w-full mb-3">
        <option value="upload">Upload</option>
        <option value="encrypt">Encrypt & Upload</option>
      </select>
      {action === "encrypt" && (
        <input type="text" placeholder="Enter new file name" value={newFileName} onChange={(e) => setNewFileName(e.target.value)} className="border p-2 w-full mb-3"/>
      )}
      <label className="flex items-center mb-3">
        <input type="checkbox" checked={sendEmail} onChange={(e) => setSendEmail(e.target.checked)} className="mr-2"/>
        Send Gmail Intimation?
      </label>
      {sendEmail && (
        <input type="email" placeholder="Enter email" value={email} onChange={(e) => setEmail(e.target.value)} className="border p-2 w-full mb-3"/>
      )}
      <button onClick={handleUpload} className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">
        Upload
      </button>
    </div>
  );
};

export default UploadPage;
