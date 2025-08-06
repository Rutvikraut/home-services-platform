import { useState } from "react";
import { ProSidebar, Menu, MenuItem, SubMenu } from "react-pro-sidebar";
import "react-pro-sidebar/dist/css/styles.css";
import { Box, IconButton, Typography, useTheme, Tooltip } from "@mui/material";
import { Link } from "react-router-dom";
import { tokens } from "../../theme";

import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import PeopleOutlinedIcon from "@mui/icons-material/PeopleOutlined";
import PersonOutlinedIcon from "@mui/icons-material/PersonOutlined";
import VerifiedOutlinedIcon from "@mui/icons-material/VerifiedOutlined";
import NewReleasesOutlinedIcon from "@mui/icons-material/NewReleasesOutlined";
import MenuOutlinedIcon from "@mui/icons-material/MenuOutlined";
import LogoutOutlinedIcon from "@mui/icons-material/LogoutOutlined";

const Item = ({
  title,
  to,
  icon,
  selected,
  setSelected,
  customClass,
  isCollapsed,
}) => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);

  const content = (
    <MenuItem
      active={selected === title}
      className={customClass}
      style={{ color: colors.grey[100] }}
      onClick={() => setSelected(title)}
      icon={icon}
    >
      <Typography>{title}</Typography>
      <Link to={to} />
    </MenuItem>
  );

  return isCollapsed ? (
    <Tooltip title={title} placement="right">
      <Box>{content}</Box>
    </Tooltip>
  ) : (
    content
  );
};

const Sidebar = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const [isCollapsed, setIsCollapsed] = useState(false);
  const [selected, setSelected] = useState("Dashboard");

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        "& .pro-sidebar": {
          height: "100%",
          color: `${colors.grey[700]} !important`,
        },
        "& .pro-sidebar-inner": {
          height: "100%",
          background: `${colors.primary[400]} !important`,
        },
        "& .pro-menu": {
          height: "100%",
        },
        "& .pro-menu-item": {
          height: "auto",
          color: `${colors.grey[700]} !important`,
        },
        "& .pro-icon-wrapper": {
          backgroundColor: "transparent !important",
        },
        "& .pro-icon": {
          color: `${colors.grey[700]} !important`,
        },
        "& .pro-inner-item:hover .pro-icon": {
          color: `${colors.orangeYellowAccent[500]} !important`,
        },

        "& .pro-inner-item": {
          padding: "5px 35px 5px 20px !important",
        },
        "& .pro-inner-item:hover": {
          backgroundColor: `${colors.primary[600]} !important`,
          borderRadius: "8px",
          color: `${colors.orangeYellowAccent[500]} !important`,
        },
        "& .pro-menu-item.active": {
          color: `${colors.orangeYellowAccent[500]} !important`,
        },
        "& .logout-menu .pro-inner-item": {
          color: "red !important",
          "& svg": {
            color: "red !important",
          },
        },
      }}
    >
      <ProSidebar collapsed={isCollapsed}>
        <Menu iconShape="square">
          {/* LOGO AND TOGGLE ICON */}
          <MenuItem
            onClick={() => setIsCollapsed(!isCollapsed)}
            icon={isCollapsed ? <MenuOutlinedIcon /> : undefined}
            style={{
              margin: "10px 0 20px 0",
              color: colors.grey[700],
            }}
          >
            {!isCollapsed && (
              <Box
                display="flex"
                justifyContent="space-around"
                alignItems="center"
                ml="15px"
              >
                <Typography variant="h3" color="white">
                  Admins
                </Typography>
                <IconButton onClick={() => setIsCollapsed(!isCollapsed)}>
                   <MenuOutlinedIcon sx={{ color: "#fff" }} />
                </IconButton>
              </Box>
            )}
          </MenuItem>

          {!isCollapsed && (
            <Box mb="25px">
              <Box display={"flex"} justifyContent="center" alignItems="center">
                <img
                  alt="profile-user"
                  width="100px"
                  height="100px"
                  src={`../../assets/images.jpg`}
                  style={{ cursor: "pointer", borderRadius: "50%" }}
                />
              </Box>
              <Box textAlign={"center"}>
                <Typography
                  variant="h4"
                  color="white"
                  fontWeight="bold"
                  sx={{ m: "10px 0 0 0 " }}
                >
                  Mahboob Alam
                </Typography>
                <Typography
                  variant="h6"
                  sx={{ color: colors.orangeYellowAccent[500] }}
                >
                  Full stack dev
                </Typography>
              </Box>
            </Box>
          )}

          {/* MENU ITEMS */}
          <Box paddingLeft={isCollapsed ? undefined : "10%"}>
            <Item
              title="Dashboard"
              to="/dashboard"
              icon={<HomeOutlinedIcon />}
              selected={selected}
              setSelected={setSelected}
              isCollapsed={isCollapsed}
            />
            <SubMenu
              title="Manage Partners"
              icon={<PeopleOutlinedIcon />}
              style={{ color: colors.grey[100] }}
            >
              <Item
                title="Verified Partners"
                to="/partners/Verified"
                icon={<VerifiedOutlinedIcon />}
                selected={selected}
                setSelected={setSelected}
                isCollapsed={isCollapsed}
              />
              <Item
                title="Unverified Partners"
                to="/partners/Unverified"
                icon={<NewReleasesOutlinedIcon />}
                selected={selected}
                setSelected={setSelected}
                isCollapsed={isCollapsed}
              />
            </SubMenu>
           
            <Item
              title="Logout"
              to="/logout"
              icon={<LogoutOutlinedIcon />}
              selected={selected}
              setSelected={setSelected}
              customClass="logout-menu"
              isCollapsed={isCollapsed}
            />
          </Box>
        </Menu>
      </ProSidebar>
    </Box>
  );
};

export default Sidebar;
