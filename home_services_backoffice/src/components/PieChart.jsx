import { ResponsivePie } from "@nivo/pie";
import { mockPieData as data } from "../data/mockData";
import { useTheme } from "@mui/material";
import { tokens } from "../theme";

const MyPie = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);

  const total = data.reduce((sum, d) => sum + d.value, 0);

  return (
    <ResponsivePie
      data={data}
      margin={{ top: 40, right: 80, bottom: 80, left: 80 }}
      innerRadius={0.5}
      padAngle={0.6}
      cornerRadius={2}
      activeOuterRadiusOffset={8}
      arcLinkLabelsSkipAngle={10}
      arcLinkLabelsTextColor={colors.grey[100]}
      arcLinkLabelsThickness={2}
      arcLinkLabelsColor={{ from: "color" }}
      arcLabelsSkipAngle={10}
      arcLabelsTextColor={{ from: "color", modifiers: [["darker", 2]] }}
      tooltip={({ datum }) => (
        <div
          style={{
            background: theme.palette.mode === "dark" ? "#1f2a40" : "#fff",
            color: theme.palette.mode === "dark" ? "#fff" : "#333",
            padding: "8px",
            borderRadius: "4px",
            boxShadow: "0 0 5px rgba(0,0,0,0.2)",
          }}
        >
          <strong>{datum.id}</strong>: {datum.value} (
          {((datum.value / total) * 100).toFixed(1)}%)
        </div>
      )}
      theme={{
        axis: {
          domain: { line: { stroke: colors.grey[100] } },
          ticks: {
            line: { stroke: colors.grey[100], strokeWidth: 1 },
            text: { fill: colors.grey[100] },
          },
          legend: { text: { fill: colors.grey[100] } },
        },
        legends: { text: { fill: colors.grey[100] } },
        tooltip: {
          container: {
            background: theme.palette.mode === "dark" ? "#1f2a40" : "#fff",
            color: theme.palette.mode === "dark" ? "#fff" : "#333",
            fontSize: 12,
          },
        },
      }}
    />
  );
};

export default MyPie;
