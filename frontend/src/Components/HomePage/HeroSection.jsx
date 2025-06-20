import { Button } from 'flowbite-react'
import React from 'react'

const HeroSection = () => {
  return (
    <div className='md:h-screen mt-0 bg-primary mb-4 flex md:flex-row flex-col justify-center md:gap-22'>
        <div className='flex gap-5 flex-col md:justify-center'>
            <h2 className='text-white md:text-left text-center text-4xl md:text-6xl font-bold text-wrap mt-24 md:mt-4'>Lorem ipsum dolor <br /> sit amet</h2>
            <p className='text-white md:text-left text-center text-xl md:text-2xl font-medium text-wrap'>Lorem ipsum dolor sit amet</p>
            <div className='flex justify-center md:justify-start'>
                <Button className='bg-secondary rounded-4xl hover:bg-secondary cursor-pointer text-nowrap text-lg mb-4 transition-all duration-300 hover:scale-105 gap-1 hover:gap-2'>
                   Get Started <span className="text-2xl">&#8594;</span>
                </Button>
            </div>
        </div>
        <div className='md:h-100 flex'>
            <img className='h-84 md:h-154 md:mt-23.5' src="/images/background.png" alt="" />
        </div>
    </div>
  )
}

export default HeroSection