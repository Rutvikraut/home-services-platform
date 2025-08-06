import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import {
  FaAngleLeft,
  FaAngleRight,
  FaTachometerAlt,
  FaClipboardList,
  FaUser,
  FaSignOutAlt,
} from "react-icons/fa";
import { getPartnerById, getPartnerOrders } from "../../api/Partner";
import StatCard from "./StatCard";
import UpcomingOrdersTable from "./UpcomingOrdersTable";
import AllOrdersTable from "./AllOrdersTable";
import ProfileSection from "./ProfileSection";
import { usePartnerAuth } from "../../Providers/PartnerAuthContext";
import { toast } from "react-toastify";


export default function PartnerDashboard() {
  const navigate = useNavigate();
  const [showSidebar, setShowSidebar] = useState(true);
  const [activeTab, setActiveTab] = useState("dashboard");
  const [partnerProfile, setPartnerProfile] = useState(null);
  const [orders, setOrders] = useState([]);
  const { partner, logout: partnerLogout } = usePartnerAuth();
  const partnerId = 1;

  useEffect(() => {
    fetchPartnerData();
  }, []);

  const fetchPartnerData = async () => {
    try {
      const profile = await getPartnerById(partnerId);
      const orders = await getPartnerOrders(partnerId);
      setPartnerProfile(profile);
      setOrders(orders);
    } catch (error) {
      console.error("Failed to load partner data", error);
    }
  };
const handleLogout = () => {
  if (partner) {
    partnerLogout();         
    navigate("/partner");   
     toast.success("Logged out successfully"); 
  } else {
    navigate("/");     
     toast.success("Logged out successfully");      
  }

 
};


  const tabData = [
    { key: "dashboard", label: "Dashboard", icon: <FaTachometerAlt /> },
    { key: "orders", label: "Orders", icon: <FaClipboardList /> },
    { key: "profile", label: "Profile", icon: <FaUser /> },
  ];

  return (
    <div className="flex min-h-screen bg-gray-100 text-gray-900 ">
     
      <aside
        className={`bg-white shadow-lg flex flex-col py-4 px-2 transition-all duration-300 ${showSidebar ? "w-64" : "w-20"}`}
      >
        <div className="flex justify-between items-center px-2 mb-4">
          {showSidebar && <h2 className="text-xl font-bold text-primary">Partner</h2>}
          <button
            onClick={() => setShowSidebar(!showSidebar)}
            className="text-xl text-primary"
          >
            {showSidebar ? <FaAngleLeft /> : <FaAngleRight />}
          </button>
        </div>

        {showSidebar && (
          <div className="flex flex-col items-center mb-6">
            <img
              src="/images/profile.png"
              alt="Partner"
              className="w-20 h-20 rounded-full mb-2"
            />
            <p className="text-lg font-semibold text-gray-800">
              Hi, {partnerProfile?.firstName || "Partner"}!
            </p>
          </div>
        )}

        <nav className="flex flex-col gap-2">
          {tabData.map(({ key, label, icon }) => (
            <button
              key={key}
              className={`flex items-center gap-3 px-4 py-2 rounded transition ${activeTab === key
                ? "bg-secondary text-white"
                : "hover:bg-primary/10 text-primary"
                }`}
              onClick={() => setActiveTab(key)}
            >
              <span className="text-lg">{icon}</span>
              {showSidebar && <span className="capitalize">{label}</span>}
            </button>
          ))}
        </nav>
      </aside>

      {/* Main content */}
      <div className="flex flex-col flex-1">
        <header className="bg-white shadow-md px-6 py-4 flex justify-between items-center">
          <h1 className="text-xl font-bold text-primary">
            Home<span className="text-secondary">Mate</span>
          </h1>
          <button
            onClick={handleLogout}
            className="flex items-center gap-2 bg-secondary text-white px-4 py-2 hover:bg-secondary-hover transition cursor-pointer rounded-4xl"
          >
            <FaSignOutAlt />
            <span onClick={handleLogout} className="hidden sm:inline">Logout</span>
          </button>
        </header>

        <main className="flex-1 p-6">
          <h1 className="text-3xl font-bold mb-6 capitalize">{activeTab}</h1>

          {activeTab === "dashboard" && (
            <>
              <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6 mb-6">
                <StatCard label="Completed Visits" value={orders.filter(o => o.orderStatus === "COMPLETED").length} />
                <StatCard label="Pending Visits" value={orders.filter(o => o.orderStatus === "PENDING").length} />
                <StatCard label="Total Earnings" value={`₹${partnerProfile?.totalEarning || 0}`} />
              </div>
              <UpcomingOrdersTable orders={orders} />
            </>
          )}

          {activeTab === "orders" && (
            <AllOrdersTable
              orders={orders}
              setOrders={setOrders}
              partnerId={partnerId} 
            />
          )}

          {activeTab === "profile" && partnerProfile && <ProfileSection partnerProfile={partnerProfile} />}
        </main>
      </div>
    </div>
  );
}
