import React from "react";
import ServiceCard from "./Cards/ServiceCard";
import { Button } from "flowbite-react";
import { Link } from "react-router-dom";

const OurServiceSection = () => {
  return (
    <div className="mt-20 md:ml-30 md:mr-30 flex md:flex-row flex-col justify-around items-center">
      <div className="flex flex-col gap-2 w-3/4 md:justify-center md:items-start items-center">
        <h3 className="text-primary font-semibold">Our Services</h3>
        <div className="mb-2 flex flex-col md:items-start items-center ">
          <h2 className="md:text-wrap text-nowrap text-black text-3xl font-semibold mb-3 md:text-left text-center">
            What Services <br /> We Provide ?
          </h2>
          <div className="w-24 h-1 bg-secondary rounded-3xl" />
        </div>
        <div>
          <Link to={"services"}>
          <Button className="bg-secondary rounded-4xl hover:bg-secondary cursor-pointer text-nowrap text-md transition-all duration-300 hover:scale-105 md:flex gap-1 hover:gap-2 hidden">
            Explore More <span className="text-2xl">&#8594;</span>
          </Button>
          </Link>
        </div>
      </div>
      <div className="flex flex-col md:flex-row gap-8">
        {[...Array(3)].map((key) => (
          <ServiceCard key={key} />
        ))}
      </div>
      <div className="mt-5 md:hidden">
          <Link to={"services"}>
            <Button className="bg-secondary rounded-4xl hover:bg-secondary cursor-pointer text-nowrap transition-all duration-300 hover:scale-105 gap-1 hover:gap-2 ">
              Explore More <span className="text-2xl">&#8594;</span>
            </Button>
          </Link>
        </div>
    </div>
  );
};

export default OurServiceSection;
