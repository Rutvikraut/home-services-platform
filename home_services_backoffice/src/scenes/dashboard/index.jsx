import { Box, Grid, Card, CardContent, Typography } from "@mui/material";
import Header from "../../components/Header";
import { mockPieData } from "../../data/mockData";
import MyPie from "../../components/PieChart";
import { tokens } from "../../theme";
import ScreenSearchDesktopOutlinedIcon from "@mui/icons-material/ScreenSearchDesktopOutlined";
import CurrencyRupeeOutlinedIcon from "@mui/icons-material/CurrencyRupeeOutlined";
import PeopleAltOutlinedIcon from "@mui/icons-material/PeopleAltOutlined";
import PendingActionsOutlinedIcon from "@mui/icons-material/PendingActionsOutlined";

// Shared card styles
const cardStyles = {
  height: "90%",
  display: "flex",
  flexDirection: "column",
  justifyContent: "center",
  backgroundColor: "transparent",
  boxShadow: "0px 6px 20px rgba(0, 0, 0, 0.12)",
  border: "1.5px solid rgba(0, 0, 0, 0.15)",
  borderRadius: "12px",
};

// Reusable Stat Card Component
const StatCard = ({ label, value, icon, iconBg, gap = 10 }) => (
  <Card sx={cardStyles}>
    <CardContent>
      <Typography>{label}</Typography>
      <Typography
        sx={{ color: "text.secondary" }}
        gutterBottom
        variant="h5"
        component="div"
        display="flex"
        alignItems="center"
        gap={gap}
      >
        {value}
        <Box
          sx={{
            backgroundColor: iconBg,
            borderRadius: "50%",
            padding: "8px",
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
            color: "#fff",
          }}
        >
          {icon}
        </Box>
      </Typography>
    </CardContent>
  </Card>
);

const statCards = [
  {
    label: "Visits",
    value: "65,122",
    icon: <ScreenSearchDesktopOutlinedIcon />,
    iconBg: "#1976d2",
  },
  {
    label: "Total Revenue",
    value: "Rs 45,000",
    icon: <CurrencyRupeeOutlinedIcon />,
    iconBg: "#1976d2",
    gap: 7,
  },
  {
    label: "Partners",
    value: "2,378",
    icon: <PeopleAltOutlinedIcon />,
    iconBg: "#1976d2",
    gap: 12,
  },
  {
    label: "Pending Requests",
    value: "112",
    icon: <PendingActionsOutlinedIcon />,
    iconBg: "#f57c00",
    gap: 14,
  },
];

const Dashboard = () => {
  return (
    <Box m="20px">
      {/* Header */}
      <Box
        display="flex"
        justifyContent="space-between"
        alignItems="center"
        mb={4}
      >
        <Header title="DASHBOARD" subtitle="Welcome to admin dashboard" />
      </Box>

      <Grid container spacing={4}>
        {statCards.map((card, index) => (
          <Grid item xs={12} md={4} key={index}>
            <StatCard {...card} />
          </Grid>
        ))}
      </Grid>

      {/* Chart Section */}
      {/* Chart + Insights Section */}
      <Box sx={{ display: "flex", gap: 4, mt: 4 }}>
        {/* Pie Chart */}
        <Box sx={{ flex: 2 }}>
          <Card
            sx={{
              backgroundColor: "transparent",
              border: "1.5px solid rgba(0, 0, 0, 0.15)",
              borderRadius: "12px",
            }}
          >
            <CardContent>
              <Typography variant="h5" mb={2}>
                Service Distribution
              </Typography>
              <Box sx={{ width: "100%", height: 430 }}>
                <MyPie />
              </Box>
            </CardContent>
          </Card>
        </Box>

        {/* Insight Card */}
        <Box sx={{ flex: 1 }}>
          <Card
            sx={{
              backgroundColor: "transparent",
              border: "1.5px solid rgba(0, 0, 0, 0.15)",
              borderRadius: "12px",
              boxShadow: "0px 6px 20px rgba(0, 0, 0, 0.12)",
              height: "100%",
              display: "flex",
              flexDirection: "column",
              justifyContent: "flex-start",
            }}
          >
            <Typography
              variant="h5"
              m={2}
              mb={5}
              sx={{
                fontWeight: 600,
              }}
            >
              💡 Top 5 Services (Revenue)
            </Typography>

            {[...mockPieData]
              .sort((a, b) => b.value - a.value)
              .slice(0,5)
              .map((item, index) => (
                <Box
                  key={item.id}
                  sx={{
                    width: "300px", 
                    mx: "auto", 
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "center",
                    backgroundColor: "#ffffffcc",
                    p: 2,
                    mb: 4,
                    borderRadius: "10px",
                    boxShadow: "0px 2px 6px rgba(0,0,0,0.05)",
                  }}
                >
                  <Typography
                    variant="body2"
                    sx={{ fontWeight: 500, color: "#333" }}
                  >
                    {index + 1}. {item.label}
                  </Typography>
                  <Typography
                    variant="body1"
                    sx={{ fontWeight: "bold", color: "#222" }}
                  >
                    ₹{item.value.toLocaleString()}
                  </Typography>
                </Box>
              ))}
          </Card>
        </Box>
      </Box>
    </Box>
  );
};

export default Dashboard;
