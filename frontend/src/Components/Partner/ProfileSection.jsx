import { FaUser, FaEnvelope, FaPhone, FaBriefcase, FaTags, FaMapMarkerAlt } from "react-icons/fa";

export default function ProfileSection({ partnerProfile }) {
  if (!partnerProfile) {
    return (
      <div className="flex items-center justify-center h-40 bg-gray-50 rounded-xl shadow-md">
        <p className="text-gray-500 animate-pulse">Loading profile...</p>
      </div>
    );
  }

  return (
    <div className="bg-white rounded-2xl shadow-lg p-8">
      {/* Header */}
      <div className="flex items-center gap-4 border-b pb-4 mb-6">
        <img
          src="/images/profile.png"
          alt="Partner"
          className="w-20 h-20 rounded-full border-4 border-secondary shadow-md"
        />
        <div>
          <h2 className="text-2xl font-bold text-primary">
            {partnerProfile.firstName} {partnerProfile.lastName}
          </h2>
        </div>
      </div>

      {/* Info Grid */}
      <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
        <div className="flex items-center gap-3 bg-gray-50 p-4 rounded-xl shadow-sm">
          <FaEnvelope className="text-secondary text-xl" />
          <p className="text-gray-700">
            <strong>Email:</strong> {partnerProfile.email}
          </p>
        </div>

        <div className="flex items-center gap-3 bg-gray-50 p-4 rounded-xl shadow-sm">
          <FaPhone className="text-green-600 text-xl" />
          <p className="text-gray-700">
            <strong>Phone:</strong> {partnerProfile.phoneNumber}
          </p>
        </div>

        <div className="flex items-center gap-3 bg-gray-50 p-4 rounded-xl shadow-sm">
          <FaBriefcase className="text-blue-600 text-xl" />
          <p className="text-gray-700">
            <strong>Experience:</strong> {partnerProfile.experience} years
          </p>
        </div>

        <div className="flex items-center gap-3 bg-gray-50 p-4 rounded-xl shadow-sm">
          <FaTags className="text-purple-600 text-xl" />
          <p className="text-gray-700">
            <strong>Category:</strong> {partnerProfile.category?.name}
          </p>
        </div>
      </div>

      {/* Address Section */}
      <div className="mt-8">
        <h3 className="text-lg font-semibold mb-3 flex items-center gap-2 text-primary">
          <FaMapMarkerAlt /> Address
        </h3>
        <div className="bg-gray-50 p-5 rounded-xl shadow-sm text-gray-700">
          <p>{partnerProfile.myAddress?.address}</p>
          <p>
            {partnerProfile.myAddress?.city}, {partnerProfile.myAddress?.state} -{" "}
            {partnerProfile.myAddress?.pincode}
          </p>
          <p>{partnerProfile.myAddress?.country}</p>
        </div>
      </div>
    </div>
  );
}
