
export enum InputType {
    PASSWORD = 'password',
    TEXT = 'text'
}

interface InputProps {
    type: InputType
    name: string
    placeholder?: string
}

const Input = ({type, name, placeholder}: InputProps) => (
    <div className='input-type'>
        <input
            type={type}
            name={name}
            placeholder={placeholder}
            className='border w-full px-6 py-2 focus:outline-none rounded-md bg-gray-50'
        />
    </div>
)

export default Input