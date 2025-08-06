import React from "react";
import { createBrowserRouter } from "react-router-dom";
import RootLayout from "../Layout/RootLayout";
import Home from "../pages/Home";
import Services from "../pages/Services";
import AboutUs from "../pages/AboutUs";
import ContactUs from "../pages/ContactUs";
import CheckoutPage from "../pages/ServicePages/CheckoutPage/CheckoutPage";
import UserProfileLayout from "../pages/User/UserProfileLayout";
import PersonalInfo from '../Components/User/PersonalInfo';
import Orders from '../Components/User/Orders';
import ChangePassword from '../Components/User/ChangePassword';
import PartnerLogin from '../pages/Partner/PartnerLogin'
import PartnerRegister from '../pages/Partner/PartnerRegister'
import PartnerDashboard from "../Components/Partner/PartnerDashBoard";

import MyAddresses from '../Components/User/Addresses'


import PartnerHome from "../pages/Partner/PartnerHome";
import ServiceListings from "../Components/ServicesPage/ServiceListings";
import OrderSuccess from "../Components/ServicesPage/Pages/OrderSuccess/OrderSuccess";


const Routes = createBrowserRouter([
  {
    path: "/",
    element: <RootLayout />,
    children: [
      {
        path: "/",
        element: <Home />,
      },
      {
        path: "/services",
        element: <Services />,
        children: [
          {
            index: true,
            element: <ServiceListings />,
          },
          {
            path: ":categoryId",
            element: <ServiceListings />,
          },
        ],
      },
      {
        path: "/services/checkout",
        element: <CheckoutPage />,
      },
      {
        path: "/services/order-success",
        element: <OrderSuccess />,
      },
      {
        path: "/about-us",
        element: <AboutUs />,
      },
      {
        path: "/contact-us",
        element: <ContactUs />,
      },
      {
        path: "/user-profile",
        element: <UserProfileLayout />,
        children: [
          {
            index: true,
            element: <PersonalInfo />,
          },
          {
            path: "myorders",
            element: <Orders />,
          },
          {
            path: "myaddresses",
            element: <MyAddresses />,
          },
          {
            path: "change-password",
            element: <ChangePassword />,
          },

        ],
      },
      {
        path: "/partner",
        element: <PartnerHome />,
      },
      {
    path: "/partner/register",
    element: <PartnerRegister />
  },
  {
    path: "/partner/dashboard",
    element: <PartnerDashboard />
  },
    ],
  },
  // {
  //   path: "dashboard",
  //   element: <PartnerDashBoard />
  // },
  // {
  //   path: "login",
  //   element: <PartnerLogin />
  // },
  // {
  //   path: "register",
  //   element: <PartnerRegister />
  // }
]);

export default Routes;
