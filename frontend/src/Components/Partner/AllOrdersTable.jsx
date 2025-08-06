import React from "react";
import { updateOrderStatus } from "../../api/Partner"; 

const AllOrdersTable = ({ orders, setOrders, partnerId }) => {
  const handleMarkCompleted = async (orderId) => {
    try {
      await updateOrderStatus(partnerId, orderId); 
      setOrders((prevOrders) =>
        prevOrders.map((order) =>
          order.id === orderId ? { ...order, orderStatus: "COMPLETED" } : order
        )
      );
    } catch (error) {
      console.error("Failed to update order status", error);
      alert("Something went wrong while updating the order.");
    }
  };

  return (
    <div className="bg-white rounded-xl shadow-md p-6">
      <h2 className="text-xl font-semibold mb-4">All Orders</h2>
      <table className="w-full text-left border-collapse">
        <thead>
          <tr className="border-b">
            <th className="p-2">Order ID</th>
            <th className="p-2">Service</th>
            <th className="p-2">Date</th>
            <th className="p-2">Time</th>
            <th className="p-2">Status</th>
            <th className="p-2">Amount</th>
            <th className="p-2">Action</th>
          </tr>
        </thead>
        <tbody>
          {orders.map((order) => (
            <tr key={order.id} className="border-b hover:bg-gray-50">
              <td className="p-2">#{order.id}</td>
              <td className="p-2">{order.service}</td>
              <td className="p-2">{order.serviceDate}</td>
              <td className="p-2">{order.serviceTime}</td>
              <td
                className={`p-2 font-semibold ${
                  order.orderStatus === "COMPLETED"
                    ? "text-green-600"
                    : "text-yellow-600"
                }`}
              >
                {order.orderStatus}
              </td>
              <td className="p-2">₹{order.totalCost}</td>
              <td className="p-2">
                {order.orderStatus !== "COMPLETED" && (
                  <button
                    onClick={() => handleMarkCompleted(order.id)}
                    className="bg-secondary text-white px-3 py-1 rounded hover:bg-secondary-hover"
                  >
                    Mark Completed
                  </button>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AllOrdersTable;
