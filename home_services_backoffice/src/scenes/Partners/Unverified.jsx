import { Box, useTheme } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";
import { Button } from "@mui/material";
import * as React from "react";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";
import { tokens } from "../../theme";

import Header from "../../components/Header";
import { getUnverifiedPartners, verifyPartner } from "../../api/Partner";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

const UnPartners = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const [partners, setPartners] = React.useState([]);
  const [modalOpen, setModalOpen] = React.useState(false);
  const [selectedPartner, setSelectedPartner] = React.useState(null);

  React.useEffect(() => {
    const fetchPartners = async () => {
      try {
        const data = await getUnverifiedPartners();
        setPartners(data);
      } catch (error) {
        console.error("Failed to fetch unverified partners", error);
      }
    };
    fetchPartners();
  }, []);

  const handleOpenModal = (partner) => {
    setSelectedPartner(partner);
    setModalOpen(true);
  };

  const handleCloseModal = () => {
    setModalOpen(false);
    setSelectedPartner(null);
  };

  const handleVerify = async (partnerId) => {
    try {
      await verifyPartner(partnerId);
      setPartners((prev) => prev.filter((p) => p.id !== partnerId));
    } catch (error) {
      console.error("Error verifying partner", error);
    }
  };

  const columns = [
    // { field: "id", headerName: "ID" },
    {
      field: "firstName",
      headerName: "First Name",
      flex: 1,
      cellClassName: "name-column--cell",
    },
    {
      field: "lastName",
      headerName: "Last Name",
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
      renderCell: (params) => (
        <Box sx={{ display: "flex", m: "10px", gap: "10px" }}>
          <Button
            onClick={() => handleOpenModal(params.row)}
            variant="contained"
            color="info"
          >
            View
          </Button>
          <Button
            onClick={() => handleVerify(params.row.id)}
            variant="contained"
            color="success"
          >
            Verify
          </Button>
        </Box>
      ),
    },
  ];

  return (
    <Box m="20px">
      <Header
        title="PARTNERS"
        subtitle="Managing the Partners and List of Partners"
      />
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
        <DataGrid rows={partners} columns={columns} />
      </Box>

      {/* Modal for partner view */}
      <Modal
        open={modalOpen}
        onClose={handleCloseModal}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Typography id="modal-modal-title" variant="h6" component="h2">
            Partner Details
          </Typography>
          <Typography id="modal-modal-description" sx={{ mt: 2 }}>
            {selectedPartner
              ? `Name: ${selectedPartner.firstName} ${selectedPartner.lastName}
Phone: ${selectedPartner.phoneNumber}
Email: ${selectedPartner.email}`
              : "No partner selected"}
          </Typography>
        </Box>
      </Modal>
    </Box>
  );
};

export default UnPartners;
