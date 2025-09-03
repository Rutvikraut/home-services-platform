import React, { useEffect, useState } from "react";
import profileImg from "../../../public/images/profile.png"; // adjust path if needed
import { Button } from "flowbite-react";
import axios from "axios";
import { useAuth } from "../../providers/AuthContext";
import { UPDATE_USER } from "../../api/config";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { getUserById } from "../../api/User";

const PersonalInfo = () => {
  const { user, login } = useAuth();
  const [formData, setFormData] = useState(null);
  const [editable, setEditable] = useState(false);
  const [isUpdating, setIsUpdating] = useState(false);

  const handleEditButton = () => {
    setEditable((prev) => !prev);
  };

  const handleUpdateButton = async () => {
    if (!user?.id) {
      return;
    }

    setIsUpdating(true);
    try {
      const { firstName, lastName, email, phone } = formData;
      const userPayload = { firstName, lastName, email, phone };
    
      const response = await axios.put(UPDATE_USER(user.id), userPayload, {
        headers: {
          Authorization: `Bearer ${user.token}`,
        },
      });

      login({ ...user, ...response.data });
      setFormData(response.data);
      setEditable(false);

      toast.success("Profile updated successfully!");
    } catch (error) {
      console.error("Update failed", error);
      toast.error("Failed to update profile. Please try again.");
    } finally {
      setIsUpdating(false);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  useEffect(() => {
      const fetchUser = async () => {
        if (!user?.id) return;
        try {
          const userData = await getUserById(user.id,user.token);
          setFormData(userData);
        } catch (err) {
          console.error("Failed to fetch user:", err);
          toast.error("Failed to fetched profile")
        }
      };
  
      fetchUser();
    }, [user.id,user?.token]);
  if (!formData) return <p>Loading profile...</p>;

  return (
    <div className="bg-white p-4 sm:p-6 rounded shadow-md w-full max-w-3xl mx-auto">
      <h2 className="text-lg font-semibold mb-6 text-center sm:text-left">
        Personal Information
      </h2>

      <div className="flex flex-col sm:flex-row items-center sm:items-start sm:space-x-6 gap-y-4">
        <div className="flex-shrink-0">
          <img
            src={profileImg}
            alt="Profile"
            className="w-24 h-24 sm:w-28 sm:h-28 rounded-full object-cover mx-auto sm:mx-0"
          />
        </div>

        <div className="grid grid-cols-1 sm:grid-cols-2 gap-4 w-full">
          <input
            type="text"
            value={formData.firstName || ""}
            readOnly={!editable}
            name="firstName"
            placeholder="First Name"
            onChange={(e) => handleChange(e)}
            className="p-2 border border-gray-200 rounded shadow-sm bg-gray-100 w-full"
          />
          <input
            type="text"
            value={formData.lastName || ""}
            readOnly={!editable}
            name="lastName"
            placeholder="Last Name"
            onChange={(e) => handleChange(e)}
            className="p-2 border border-gray-200 rounded shadow-sm bg-gray-100 w-full"
          />
          <input
            type="email"
            value={formData.email || ""}
            readOnly={!editable}
            name="email"
            placeholder="Email"
            onChange={(e) => handleChange(e)}
            className="p-2 border border-gray-200 rounded shadow-sm bg-gray-100 w-full"
          />
          <input
            type="tel"
            value={formData.phone || ""}
            readOnly={!editable}
            name="phone"
            placeholder="Phone"
            onChange={(e) => handleChange(e)}
            className="p-2 border border-gray-200 rounded shadow-sm bg-gray-100 w-full"
          />
        </div>
      </div>

      <div className="mt-6 flex flex-col sm:flex-row justify-end gap-3">
        <div className="mt-6 flex justify-end mr-2">
          <Button
            className={`${
              editable
                ? "bg-red-500 hover:bg-red-600"
                : "bg-blue-500 hover:bg-blue-600"
            } text-white px-6 py-2 rounded-4xl  transition cursor-pointer`}
            onClick={handleEditButton}
          >
            {editable ? "Cancel" : "Edit"}
          </Button>
        </div>

        {editable && (
          <div className="mt-6 flex justify-end">
            <button
              disabled={isUpdating}
              className={`${
                isUpdating
                  ? "bg-gray-400 cursor-not-allowed"
                  : "bg-secondary hover:bg-yellow-600"
              } text-white px-6 py-2 rounded-4xl transition cursor-pointer`}
              onClick={handleUpdateButton}
            >
              {isUpdating ? "Updating..." : "Update"}
            </button>
          </div>
        )}
      </div>
    </div>
  );
};

export default PersonalInfo;
