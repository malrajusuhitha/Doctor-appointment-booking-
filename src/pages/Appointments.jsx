import { useState, useEffect } from 'react';
import axios from 'axios';
import toast from 'react-hot-toast';

export default function Appointments() {
  const [appointments, setAppointments] = useState([]);

  useEffect(() => {
    fetchAppointments();
  }, []);

  const fetchAppointments = async () => {
    try {
      const token = localStorage.getItem('token');
      const userEmail = localStorage.getItem('userEmail');
      const response = await axios.get('http://localhost:8080/api/appointments', {
        headers: {
          userEmail,
          token
        }
      });
      setAppointments(response.data);
    } catch (error) {
      toast.error('Failed to fetch appointments');
    }
  };

  const handleCancelAppointment = async (appointmentId) => {
    try {
      const token = localStorage.getItem('token');
      const userEmail = localStorage.getItem('userEmail');
      await axios.delete(`http://localhost:8080/api/appointments/cancel`, {
        headers: {
          userEmail,
          token
        },
        params: {
          appointmentId
        }
      });
      toast.success('Appointment cancelled successfully');
      fetchAppointments();
    } catch (error) {
      toast.error('Failed to cancel appointment');
    }
  };

  return (
    <div className="space-y-6">
      <h2 className="text-2xl font-bold">My Appointments</h2>
      <div className="space-y-4">
        {appointments.map((appointment) => (
          <div key={appointment.appointmentId} className="border rounded-lg p-4 shadow-sm">
            <div className="flex justify-between items-center">
              <div>
                <h3 className="text-lg font-semibold">Dr. {appointment.doctor.doctorName}</h3>
                <p className="text-gray-600">{new Date(appointment.time).toLocaleString()}</p>
              </div>
              <button
                onClick={() => handleCancelAppointment(appointment.appointmentId)}
                className="bg-red-600 text-white px-4 py-2 rounded-md hover:bg-red-700"
              >
                Cancel
              </button>
            </div>
          </div>
        ))}
        {appointments.length === 0 && (
          <p className="text-gray-600 text-center">No appointments found</p>
        )}
      </div>
    </div>
  );
}