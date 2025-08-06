export default function ProfileSection({ partnerProfile }) {
  if (!partnerProfile) {
    return <div>Loading profile...</div>; // or return null if you want to show nothing
  }

  return (
    <div className="bg-white rounded-xl shadow-md p-6">
      <h2 className="text-xl font-semibold mb-4">Partner Profile</h2>

      <p><strong>Name:</strong> {partnerProfile.firstName} {partnerProfile.lastName}</p>
      <p><strong>Email:</strong> {partnerProfile.email}</p>
      <p><strong>Phone:</strong> {partnerProfile.phoneNumber}</p>
      <p><strong>Experience:</strong> {partnerProfile.experience} years</p>
      <p><strong>Rating:</strong> {partnerProfile.rating}</p>
      <p><strong>Category:</strong> {partnerProfile.category?.name}</p>

      <div className="mt-4">
        <h3 className="text-lg font-semibold mb-2">Address</h3>
        <p><strong>Address:</strong> {partnerProfile.myAddress?.address}</p>
        <p><strong>City:</strong> {partnerProfile.myAddress?.city}</p>
        <p><strong>State:</strong> {partnerProfile.myAddress?.state}</p>
        <p><strong>Pincode:</strong> {partnerProfile.myAddress?.pincode}</p>
        <p><strong>Country:</strong> {partnerProfile.myAddress?.country}</p>
      </div>
    </div>
  );
}
