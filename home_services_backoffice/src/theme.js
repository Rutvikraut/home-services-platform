import { createContext, useState, useMemo } from "react";
import { createTheme } from "@mui/material/styles";

//color design token
export const tokens = (mode) =>
  mode === "dark"
    ? {
        grey: {
          100: "#e0e0e0",
          200: "#e0e0e0",
          300: "#a3a3a3",
          400: "#858585",
          500: "#666666",
          600: "#525252",
          700: "#FFFFFF",
          800: "#292929",
          900: "#141414",
        },

        primary: {
          100: "#d0d1d5",
          200: "#a1a4ab",
          300: "#727681",
          400: "#1f2A40",
          500: "#141b2d",
          600: "#101624",
          700: "#0c101b",
          800: "#1f2A40",
          900: "#040509",
        },

        orangeYellowAccent: {
          100: "#fff2db",
          200: "#ffe0b7",
          300: "#ffcd94",
          400: "#ffbb70",
          500: "#f9a316", 
          600: "#c27f12",
          700: "#FFFFFF",
          800: "#533c09",
          900: "#1d1b04",
        },

        redAccent: {
          100: "#f8dcdb",
          200: "#f1b9b7",
          300: "#e99592",
          400: "#e2726e",
          500: "#db4f4a",
          600: "#af3f3b",
          700: "#832f2c",
          800: "#58201e",
          900: "#2c100f",
        },

        blueAccent: {
          100: "#e1e2fe",
          200: "#c3c6fd",
          300: "#a4a9fc",
          400: "#868dfb",
          500: "#6870fa",
          600: "#535ac8",
          700: "#3e4396",
          800: "#2a2d64",
          900: "#151632",
        },
      }
    : {
        grey: {
          100: "#141414",
          200: "#e0e0e0",
          300: "#e0e0e0",
          400: "#888888",
          500: "#666666",
          600: "#858585",
          700: "#FFFFFF",
          800: "#c2c2c2",
          900: "#e0e0e0",
        },

        primary: {
          100: "#040509",
          200: "#080b12",
          300: "#0c101b",
          400: "#001837",
          500: "#001837",
          600: "#434957",
          700: "#727681",
          800: "#263349",
          900: "#d0d1d5",
        },

        orangeYellowAccent: {
          100: "#1d1b04",
          200: "#533c09",
          300: "#8a5e0d",
          400: "#c27f12",
          500: "#e68a00", 
          600: "#ffbb70",
          700: "#ffcd94",
          800: "#ffe0b7",
          900: "#fff2db",
        },

        redAccent: {
          100: "#2c100f",
          200: "#58201e",
          300: "#832f2c",
          400: "#af3f3b",
          500: "#db4f4a",
          600: "#e2726e",
          700: "#e99592",
          800: "#f1b9b7",
          900: "#f8dcdb",
        },

        blueAccent: {
          100: "#151632",
          200: "#2a2d64",
          300: "#3e4396",
          400: "#535ac8",
          500: "#6870fa",
          600: "#868dfb",
          700: "#a4a9fc",
          800: "#c3c6fd",
          900: "#e1e2fe",
        },
      };

// mui theme settings
export const themeSettings = (mode) => {
  const colors = tokens(mode);
  return {
    palette: {
      mode: mode,
      ...(mode === "dark"
        ? {
            primary: {
              main: colors.primary[500],
            },
            secondary: {
              main: colors.orangeYellowAccent[500],
            },
            neutral: {
              dark: colors.grey[700],
              main: colors.grey[500],
              light: colors.grey[100],
            },
            background: {
              default: colors.primary[500],
            },
          }
        : {
            primary: {
              main: "#f5f5f5",
            },
            secondary: {
              main: colors.orangeYellowAccent[500],
            },
            neutral: {
              dark: colors.grey[700],
              main: colors.grey[500],
              light: colors.grey[100],
            },
            background: {
              default: "#fcfcfc",
            },
          }),
    },
    typography: {
      fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
      fontSize: 12,
      h1: {
        fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
        fontSize: 40,
      },
      h2: {
        fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
        fontSize: 32,
      },
      h3: {
        fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
        fontSize: 24,
      },
      h4: {
        fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
        fontSize: 20,
      },
      h5: {
        fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
        fontSize: 16,
      },
      h6: {
        fontFamily: ["Source Sans Pro", "sans-serif"].join(","),
        fontSize: 14,
      },
    },
  };
};

// context for color mode
export const ColorModeContext = createContext({
  toggleColorMode: () => {},
});

export const useMode = () => {
  const [mode, setMode] = useState("dark");

  const colorMode = useMemo(
    () => ({
      toggleColorMode: () =>
        setMode((prev) => (prev === "light" ? "dark" : "light")),
    }),
    []
  );

  const theme = useMemo(() => createTheme(themeSettings(mode)), [mode]);

  return [theme, colorMode];
};
