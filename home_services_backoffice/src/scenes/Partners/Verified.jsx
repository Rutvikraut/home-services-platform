import React, { useEffect, useState } from "react";
import { Box, useTheme, Button } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";
import { tokens } from "../../theme";
import Header from "../../components/Header";
import axios from "axios";
import { getVerifiedPartners } from "../../api/Partner";

const Partners = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const [partners, setPartners] = useState([]);

   useEffect(() => {
    const fetchPartners = async () => {
      try {
        const data = await getVerifiedPartners();
        setPartners(data);
      } catch (error) {
        console.error("Failed to fetch verified partners", error);
      }
    };

    fetchPartners();
  }, []);

  const columns = [
    // { field: "id", headerName: "ID" },
    {
      field: "firstName",
      headerName: "firstName",
      flex: 1,
      cellClassName: "name-column--cell",
    },
    {
      field: "lastName",
      headerName: "lastName",
      flex: 1,
      cellClassName: "name-column--cell",
    },
    {
      field: "phoneNumber",
      headerName: "Phone Number",
      flex: 1,
    },
    {
      field: "email",
      headerName: "Email",
      flex: 1,
    },
    {
      field: "actions",
      headerName: "Actions",
      flex: 1,
      sortable: false,
      disableColumnMenu: true,
      renderCell: () => (
        <Box>
          <Button variant="contained" color="error">
            RESTRICT ACCESS
          </Button>
        </Box>
      ),
    },
  ];

  return (
    <Box m="20px">
      <Header title="PARTNERS" subtitle="Managing the Partners and List of Partners" />
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
          "& .MuiTablePagination-root, & .MuiTablePagination-selectLabel, & .MuiTablePagination-input, & .MuiTablePagination-actions": {
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
        <DataGrid rows={partners} columns={columns} pageSize={100} />
      </Box>
    </Box>
  );
};

export default Partners;
