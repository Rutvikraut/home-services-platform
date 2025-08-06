import React, { useEffect, useState } from "react";
import { Box, Button, useTheme } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";
import { tokens } from "../../theme";
import Header from "../../components/Header";
import { getAllOrders, assignOrderToPartner } from "../../api/Order";

const OrderList = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const data = await getAllOrders();
        setOrders(data);
      } catch (error) {
        console.error("Failed to fetch orders", error);
      }
    };

    fetchOrders();
  }, []);

  const handleView = (order) => {
    alert(`Viewing order ID: ${order.id}`);
  };

const handleAssign = async (orderId) => {
  const partnerId = prompt("Enter Partner ID to assign:");
  if (!partnerId) return;

  try {
    await assignOrderToPartner(orderId,partnerId);
    alert("Order assigned successfully.");
    const updatedOrders = await getAllOrders();
    setOrders(updatedOrders);
  } catch (err) {
    console.error("Assignment failed:", err);
    alert("Failed to assign order.");
  }
};


  const columns = [
    { field: "id", headerName: "Order ID", flex: 1 },
    { field: "service", headerName: "Service", flex: 1 },
    { field: "serviceDate", headerName: "Service Date", flex: 1 },
    { field: "serviceTime", headerName: "Service Time", flex: 1 },
    { field: "orderStatus", headerName: "Status", flex: 1 },
    {
      field: "actions",
      headerName: "Actions",
      flex: 1,
      sortable: false,
      disableColumnMenu: true,
      renderCell: (params) => (
        <Box display="flex" gap={1}>
          <Button
            variant="contained"
            color="info"
            onClick={() => handleView(params.row)}
          >
            View
          </Button>
          <Button
            variant="contained"
            color="success"
            onClick={() => handleAssign(params.row.id)}
          >
            Assign
          </Button>
        </Box>
      ),
    },
  ];

  return (
    <Box m="20px">
      <Header title="ORDERS" subtitle="List of all customer orders" />
      <Box
        m="40px 0 0 0"
        height="75vh"
        sx={{
          "& .MuiDataGrid-root": {
            border: "none",
            color: "#fff",
          },
          "& .MuiDataGrid-cell": {
            borderBottom: "1px solid black",
          },
          "& .MuiDataGrid-columnHeaders": {
            backgroundColor: `${colors.blueAccent[700]} !important`,
            borderBottom: "1px solid black",
          },
          "& .MuiDataGrid-columnHeader": {
            backgroundColor: `${colors.orangeYellowAccent[500]} !important`,
            color: "#000",
          },
          "& .MuiDataGrid-virtualScroller": {
            backgroundColor: colors.primary[800],
          },
          "& .MuiDataGrid-footerContainer": {
            borderTop: "1px solid black",
            backgroundColor: colors.orangeYellowAccent[500],
            color: "#000",
            fontWeight: "bold",
          },
          "& .MuiTablePagination-root, & .MuiTablePagination-selectLabel, & .MuiTablePagination-input, & .MuiTablePagination-actions":
            {
              color: "#000",
            },
          "& .MuiDataGrid-row:hover": {
            backgroundColor: `${colors.primary[500]} !important`,
            color: "#fff",
          },
          "& .MuiSvgIcon-root": {
            color: "#000 !important",
          },
        }}
      >
        <DataGrid rows={orders} columns={columns} pageSize={100} />
      </Box>
    </Box>
  );
};

export default OrderList;
