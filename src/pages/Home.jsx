import { CalendarIcon, UserGroupIcon, ClockIcon } from '@heroicons/react/24/outline';
import Hero from '../components/Hero';
import FeatureCard from '../components/FeatureCard';

export default function Home() {
  const features = [
    {
      icon: CalendarIcon,
      title: 'Easy Scheduling',
      description: 'Book appointments with just a few clicks, anytime and anywhere.'
    },
    {
      icon: UserGroupIcon,
      title: 'Expert Doctors',
      description: 'Connect with qualified and experienced healthcare professionals.'
    },
    {
      icon: ClockIcon,
      title: '24/7 Access',
      description: 'Manage your appointments and medical history around the clock.'
    }
  ];

  return (
    <div className="min-h-screen bg-gray-50">
      <Hero />
      
      <div className="py-16 px-4 sm:px-6 lg:px-8">
        <div className="text-center mb-12">
          <h2 className="text-3xl font-bold text-gray-900">Why Choose Us</h2>
          <p className="mt-4 text-lg text-gray-600">Experience healthcare management like never before</p>
        </div>
        
        <div className="max-w-7xl mx-auto">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            {features.map((feature, index) => (
              <FeatureCard key={index} {...feature} />
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}