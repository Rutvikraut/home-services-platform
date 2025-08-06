import React, { useEffect, useState } from "react";
import ServiceProfileCard from "./Card/ServiceProfileCard";
import { Button, Pagination } from "flowbite-react";
import { getServicesByCategoryId } from "../../api/CatalogService";
import { useParams } from "react-router-dom";
import PaginationComponent from "../Shared/PaginationComponent";

const ServiceListings = () => {
  const { categoryId } = useParams();
  const [services, setServices] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const providersPerPage = 5;

  useEffect(() => {
    const fetchServices = async () => {
      try {
        let res;
        if (categoryId === undefined) {
          res = await getServicesByCategoryId(1,user?.token);
        } else {
          res = await getServicesByCategoryId(categoryId,user?.token);
        }
        setServices(res || []);
      } catch (err) {
        console.error("Failed to fetch services:", err);
      }
    };
    fetchServices();
  }, [categoryId]);

  const totalPages = Math.ceil(services.length / providersPerPage);
  const indexOfLast = currentPage * providersPerPage;
  const indexOfFirst = indexOfLast - providersPerPage;
  const currentProviders = services.slice(indexOfFirst, indexOfLast);

  return (
    <div className="flex flex-col mb-5">
      {/* Service Cards */}
      <div className="flex flex-col items-center gap-6 px-4 overflow-y-auto no-scrollbar whitespace-nowrap mb-5">
        {currentProviders.map((service) => (
          <ServiceProfileCard key={service.id} service={service} />
        ))}
      </div>

      {/* Pagination Component */}
      <div className="flex justify-center mt-4">
        <PaginationComponent
          currentPage={currentPage}
          totalPages={totalPages}
          onPageChange={(page) => setCurrentPage(page)}
        />
      </div>
    </div>
  );
};

export default ServiceListings;
