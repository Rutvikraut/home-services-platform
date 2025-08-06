import { Typography, Box, useTheme } from '@mui/material';
import { tokens } from '../theme';

const Header = ({ title, subtitle }) => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);

  return (
    <Box mb="30px">
      <Typography
        variant="h2"
        color={colors.grey[100]}
        fontWeight="bold"
        sx={{ mb: "5px" }}
      >
        {title}
      </Typography>
      <Typography
        variant="h5"
        color={colors.orangeYellowAccent[500]}
        sx={{ mb: 1 }}
      >
        {subtitle}
      </Typography>

      {/* Horizontal Line */}
      <Box
        sx={{
          height: "4px",
          backgroundColor: colors.orangeYellowAccent[500],
          borderRadius: "5px",
          width: "80px", // You can adjust width or make it full width: "100%"
        }}
      />
    </Box>
  );
};

export default Header;
