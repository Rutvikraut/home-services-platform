import { Box, IconButton, useTheme } from "@mui/material";
import { useContext } from "react";
import { ColorModeContext, tokens } from "../../theme";
import Badge from "@mui/material/Badge";
import { useNavigate } from "react-router-dom";
import LightModeOutlinedIcon from "@mui/icons-material/LightModeOutlined";
import DarkModeOutlinedIcon from "@mui/icons-material/DarkModeOutlined";
import NotificationsOutlinedIcon from "@mui/icons-material/NotificationsOutlined";
import SettingsOutlinedIcon from "@mui/icons-material/SettingsOutlined";
import PersonOutlinedIcon from "@mui/icons-material/PersonOutlined";

const Topbar = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const colorMode = useContext(ColorModeContext);
const navigate = useNavigate();
  const isDarkMode = theme.palette.mode === "dark";

  return (
    <>
      <Box
        sx={{
          backgroundColor: isDarkMode ? colors.primary[400] : "#ffffffcc",
          border: isDarkMode
            ? "1.5px solid rgba(255, 255, 255, 0.15)"
            : "1.5px solid rgba(0, 0, 0, 0.15)",
          borderRadius: "10px",
          boxShadow: isDarkMode
            ? "0px 2px 6px rgba(0,0,0,0.3)"
            : "0px 2px 6px rgba(0,0,0,0.05)",
          px: 2,
          py: 1,
          mx: 2,
          mt: 2,
        }}
      >
        <Box display="flex" justifyContent="flex-end">
          <Box display="flex" alignItems="center" gap={1}>
            {/* Theme Toggle Icon */}
            <IconButton onClick={colorMode.toggleColorMode}>
              {isDarkMode ? (
                <DarkModeOutlinedIcon sx={{ color: colors.grey[100] }} />
              ) : (
                <LightModeOutlinedIcon sx={{ color: colors.grey[100] }} />
              )}
            </IconButton>

            {/* Notifications with Badge */}
            <IconButton>
              <Badge badgeContent={4} color="warning">
                <NotificationsOutlinedIcon sx={{ color: colors.grey[100] }} />
              </Badge>
            </IconButton>

            {/* Settings */}
            <IconButton>
              <SettingsOutlinedIcon sx={{ color: colors.grey[100] }} />
            </IconButton>

            {/* Profile */}
            <IconButton onClick={() => navigate("/Profile")}>
              <PersonOutlinedIcon sx={{ color: colors.grey[100] }} />
            </IconButton>
          </Box>
        </Box>
      </Box>
    </>
  );
};

export default Topbar;
