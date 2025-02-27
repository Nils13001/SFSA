import { Link, Outlet } from "react-router-dom";
import { FolderUp, FileText } from "lucide-react";

const Dashboard = () => {
  return (
    <div className="flex h-screen">
      {/* Sidebar */}
      <aside className="w-64 bg-gray-900 text-white flex flex-col p-6">
        <h2 className="text-2xl font-semibold mb-4">Dashboard</h2>
        <nav>
          <Link className="flex items-center space-x-2 p-3 rounded-lg hover:bg-gray-700" to="/dashboard/upload">
            <FolderUp size={20} /> <span>Upload Files</span>
          </Link>
          <Link className="flex items-center space-x-2 p-3 rounded-lg hover:bg-gray-700" to="/dashboard/list">
            <FileText size={20} /> <span>View Files</span>
          </Link>
        </nav>
      </aside>

      {/* Content */}
      <main className="flex-grow p-6 bg-gray-100 dark:bg-gray-800">
        <Outlet />
      </main>
    </div>
  );
};

export default Dashboard;
