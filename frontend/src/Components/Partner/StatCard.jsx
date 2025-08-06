import React from "react";

const StatCard = ({ label, value }) => (
  <div className="bg-white rounded-xl shadow-md p-6 flex flex-col items-center text-center">
    <span className="text-2xl font-semibold">{value}</span>
    <span className="text-gray-600">{label}</span>
  </div>
);

export default StatCard;