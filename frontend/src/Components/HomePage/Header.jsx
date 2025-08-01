import {
  createTheme,
  Navbar,
  NavbarBrand,
  NavbarCollapse,
  NavbarLink,
  NavbarToggle,
  ThemeProvider,
} from "flowbite-react";
import { SharedButton } from "../Shared/SharedButton";
import { Link, NavLink } from "react-router-dom";
import { useAuthModal } from "../../hooks/useAuthModal";

const customTheme = createTheme({
  navbar: {
    link: {
      active: {
        on: "text-primary border-b-2 border-primary",
        off: "text-secondary",
      },
    },
  },
  toggle: {
    base: "inline-flex items-center rounded-lg p-2 text-sm text-primary md:hidden",
    icon: "h-6 w-6 shrink-0 text-primary",
    title: "sr-only",
  },
});
const Header = () => {
  const { setModalType } = useAuthModal();
  const navLinkFilter = ["Home", "Services", "About-Us", "Contact-Us"];

  return (
    <Navbar
      theme={customTheme}
      className="fixed w-full z-50 transition-transform duration-300 rounded-b-md shadow-2xl "
    >
      <NavbarBrand className="text-2xl font-semibold text-center">
        Home<span className="text-secondary">Mate</span>
      </NavbarBrand>
      <NavbarToggle />
      <NavbarCollapse>
        <div className="mt-4 flex flex-col md:mt-0 md:flex-row md:space-x-8 md:text-sm md:font-medium items-center">
          {navLinkFilter.map((navLink) => (
            <NavLink
              key={navLink}
              to={navLink == "Home" ? "/" : `/${navLink.toLowerCase()}`}
              className={({ isActive }) =>
                `${
                  isActive ? "text-primary " : "text-tertiary"
                } hover:text-primary`
              }
            >
              {navLink}
            </NavLink>
          ))}
        </div>
        <div className="mt-3 md:mt-0 gap-2 flex items-center flex-col md:flex-row">
          <div className="flex flex-col gap-1">
            <Link to={'/partner'} className="text-primary hover:text-secondary-hover">Login As A Partner</Link>
            <div className="w-1/2 h-0.5 bg-secondary"/>
          </div>
          <SharedButton setModalType={setModalType} label="Login" />
          <SharedButton setModalType={setModalType} label="Register" />
        </div>
      </NavbarCollapse>
    </Navbar>
  );
};

export default Header;
