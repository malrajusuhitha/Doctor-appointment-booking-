import { useState, useEffect } from 'react';
import axios from 'axios';
import toast from 'react-hot-toast';

export default function DoctorList() {
  const [doctors, setDoctors] = useState([]);
  const [selectedDoctor, setSelectedDoctor] = useState(null);
  const [appointmentDate, setAppointmentDate] = useState('');
  const [appointmentTime, setAppointmentTime] = useState('');

  useEffect(() => {
    fetchDoctors();
  }, []);

  const fetchDoctors = async () => {
    try {
      const token = localStorage.getItem('token');
      const userEmail = localStorage.getItem('userEmail');
      const response = await axios.get('http://localhost:8080/api/patient/doctors', {
        params: {
          userEmail,
          token
        }
      });
      setDoctors(response.data);
    } catch (error) {
      toast.error('Failed to fetch doctors');
    }
  };

  const handleBookAppointment = async () => {
    if (!selectedDoctor || !appointmentDate || !appointmentTime) {
      toast.error('Please select all appointment details');
      return;
    }

    try {
      const dateTime = new Date(appointmentDate + 'T' + appointmentTime);
      const appointment = {
        doctor: selectedDoctor,
        time: dateTime.toISOString(),
        patient: {
          patientEmail: localStorage.getItem('userEmail')
        }
      };

      await axios.post('http://localhost:8080/api/appointments/book', appointment);
      toast.success('Appointment booked successfully!');
      setSelectedDoctor(null);
      setAppointmentDate('');
      setAppointmentTime('');
    } catch (error) {
      toast.error(error.response?.data || 'Failed to book appointment');
    }
  };

  return (
    <div className="space-y-6">
      <h2 className="text-2xl font-bold">Available Doctors</h2>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {doctors.map((doctor) => (
          <div key={doctor.doctorId} className="border rounded-lg p-4 shadow-sm">
            <h3 className="text-lg font-semibold">{doctor.doctorName}</h3>
            <p className="text-gray-600">{doctor.specialization}</p>
            <button
              onClick={() => setSelectedDoctor(doctor)}
              className="mt-2 bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700"
            >
              Book Appointment
            </button>
          </div>
        ))}
      </div>

      {selectedDoctor && (
        <div className="mt-6 border rounded-lg p-4">
          <h3 className="text-lg font-semibold mb-4">Book Appointment with {selectedDoctor.doctorName}</h3>
          <div className="space-y-4">
            <div>
              <label className="block text-sm font-medium text-gray-700">Date</label>
              <input
                type="date"
                className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                value={appointmentDate}
                onChange={(e) => setAppointmentDate(e.target.value)}
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700">Time</label>
              <input
                type="time"
                className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                value={appointmentTime}
                onChange={(e) => setAppointmentTime(e.target.value)}
              />
            </div>
            <button
              onClick={handleBookAppointment}
              className="w-full bg-green-600 text-white py-2 px-4 rounded-md hover:bg-green-700"
            >
              Confirm Booking
            </button>
          </div>
        </div>
      )}
    </div>
  );
}