
interface ButtonProps {
    text: string
}
const Button = ({text}: ButtonProps) => {
  return <div className='w-full pt-2'>
      <button
        className='flex justify-center uppercase bg-cyan-600 text-white px-6 py-2 border
        rounded-md hover:bg-cyan-700 hover:text-white hover:border-cyan-600 hover:text-cyan-600 w-full'
      >{text}</button>
  </div>
}

export default Button