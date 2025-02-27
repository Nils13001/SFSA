// import { useState, useEffect } from "react";
// import axios from "axios";

// const ListPage = () => {
//   const [files, setFiles] = useState([]);

//   useEffect(() => {
//     axios.get("http://localhost:8080/s3/list-files")
//       .then(response => setFiles(response.data))
//       .catch(() => setFiles([]));
//   }, []);

//   return (
//     <div className="bg-white p-6 rounded-lg shadow-lg max-w-3xl mx-auto">
//       <h2 className="text-2xl font-bold mb-4">Stored Files</h2>
//       <ul className="list-disc pl-6">
//         {files.length > 0 ? files.map((file, idx) => (
//           <li key={idx} className="text-lg">{file}</li>
//         )) : <p>No files found.</p>}
//       </ul>
//     </div>
//   );
// };

// export default ListPage;

import { useState, useEffect } from "react";
import axios from "axios";

const ListPage = () => {
  const [files, setFiles] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/s3/list-files")
      .then(response => setFiles(response.data))
      .catch(() => setFiles([]));
  }, []);

  const handleDownload = async (fileName) => {
    try {
      const response = await axios.get(`http://localhost:8080/s3/download-url?fileName=${fileName}`);
      const url = response.data;

      // Create a temporary anchor element to trigger the download
      const a = document.createElement("a");
      a.href = url;
      a.download = fileName;
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);
    } catch (error) {
      alert("Download failed! Check console for details.");
      console.error("Error downloading file:", error);
    }
  };

  return (
    <div className="bg-white p-6 rounded-lg shadow-lg max-w-4xl mx-auto">
      <h2 className="text-2xl font-bold mb-4 text-center">Stored Files</h2>
      {files.length > 0 ? (
        <div className="overflow-x-auto">
          <table className="min-w-full bg-white border border-gray-300 rounded-lg">
            <thead className="bg-gray-200">
              <tr>
                <th className="px-6 py-3 text-left text-sm font-semibold text-gray-700">File Name</th>
                <th className="px-6 py-3 text-center text-sm font-semibold text-gray-700">Action</th>
              </tr>
            </thead>
            <tbody>
              {files.map((file, idx) => (
                <tr key={idx} className="border-t border-gray-300">
                  <td className="px-6 py-3 text-gray-800">{file}</td>
                  <td className="px-6 py-3 text-center">
                    <button
                      className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition duration-200"
                      onClick={() => handleDownload(file)}
                    >
                      Download
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      ) : (
        <p className="text-center text-gray-600">No files found.</p>
      )}
    </div>
  );
};

export default ListPage;