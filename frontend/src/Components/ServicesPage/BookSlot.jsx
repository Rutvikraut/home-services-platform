import React, { useState } from "react";
import DatePicker from "../../utils/DatePicker";
import { Button, Label } from "flowbite-react";
import { Link } from "react-router-dom";

const BookSlot = () => {
  const [selectedTime, setSelectedTime] = useState("10:00");

  const handleTimeChange = (e) => {
    setSelectedTime(e.target.value);
  };
  return (
    <div className="flex flex-col items-center mb-5 sticky top-20 self-start">
      {/* <h1 className="text-2xl font-medium">Book Slot</h1> */}
      <div className="w-full border border-gray-200 rounded-md flex flex-col p-4 gap-4">
        <div className="w-full flex justify-center">
          <DatePicker />
        </div>
        <div className="text-center flex-1 flex flex-col gap-3 justify-between items-center ">
          <div className="flex flex-col gap-2">
          <Label htmlFor="time" className="text-sm font-medium">
            Select Time
          </Label>
          <input
            id="time"
            type="time"
            className="w-full border border-gray-300 rounded px-3 py-2"
            value={selectedTime}
            onChange={handleTimeChange}
          />
          </div>
          <div className="flex justify-end">
            <Link to={"add-address"}>
              <Button className="bg-secondary hover:bg-secondary-hover cursor-pointer">
                Continue
              </Button>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default BookSlot;
