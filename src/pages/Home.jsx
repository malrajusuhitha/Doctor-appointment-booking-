import { Link } from 'react-router-dom';

export default function Home() {
  return (
    <div className="max-w-4xl mx-auto text-center">
      <h1 className="text-4xl font-bold mb-6">Welcome to Doctor's Appointment System</h1>
      <p className="text-xl text-gray-600 mb-8">
        Book appointments with qualified doctors easily and manage your healthcare journey.
      </p>
      <div className="space-x-4">
        <Link
          to="/register"
          className="inline-block bg-blue-600 text-white px-6 py-3 rounded-md hover:bg-blue-700"
        >
          Get Started
        </Link>
        <Link
          to="/login"
          className="inline-block bg-gray-600 text-white px-6 py-3 rounded-md hover:bg-gray-700"
        >
          Sign In
        </Link>
      </div>
    </div>
  );
}