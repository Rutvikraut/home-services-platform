import { ColorModeContext } from "./theme";
import { useMode } from "./theme";
import { CssBaseline, ThemeProvider } from "@mui/material";
import { Route, Routes, useLocation, useNavigate } from "react-router-dom";
import Dashboard from "./scenes/dashboard";
import Partners from "./scenes/Partners/Verified";
import UnPartners from "./scenes/Partners/Unverified";
import Logout from "./scenes/Logout";
import Profile from "./scenes/Profile";
import Login from "./Auth/Login";
import Layout from "./Layout/Layout";
import { useEffect, useState } from "react";
import OrderList from "./scenes/Orders/OrderList";

function App() {
  const [theme, colorMode] = useMode();
  const location = useLocation();
  const navigate = useNavigate();
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const isProtectedRoute = location.pathname !== "/";
    if (!isLoggedIn && isProtectedRoute) {
      navigate("/");
    }
  }, [isLoggedIn, location.pathname, navigate]);

  return (
    <ColorModeContext.Provider value={colorMode}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <Routes>
          {/* Login page */}
          <Route
            path="/"
            element={
              <Login
                onLogin={() => {
                  setIsLoggedIn(true);
                  navigate("/dashboard");
                }}
              />
            }
          />

          {/* Protected routes wrapped with Layout */}
          {isLoggedIn && (
            <Route element={<Layout />}>
              <Route path="/dashboard" element={<Dashboard />} />
              <Route path="/partners/Verified" element={<Partners />} />
              <Route path="/partners/Unverified" element={<UnPartners />} />
              <Route path="/orders" element={<OrderList />} />
              <Route path="/profile" element={<Profile />} />
              <Route
                path="/logout"
                element={
                  <Logout
                    onLogout={() => {
                      setIsLoggedIn(false);
                      navigate("/");
                    }}
                  />
                }
              />
            </Route>
          )}
        </Routes>
      </ThemeProvider>
    </ColorModeContext.Provider>
  );
}

export default App;
