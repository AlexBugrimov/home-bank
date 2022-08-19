import Input, {InputType} from "../components/Input";
import Button from "../components/Button";

const SignIn = () => {
    return (
        <section>
            <div className='h-screen w-full  flex items-center justify-center'>
                <div className='bg-white border-2 border-gray-400 rounded-md max-w-[430px] w-full p-8 drop-shadow-lg'>
                    <header className='text-3xl font-semibold text-center'>Sign In</header>
                    <form className='grid lg:grid-cols-1 gap-4 pt-8'>
                        <Input type={InputType.TEXT} name='login' placeholder='Login'/>
                        <Input type={InputType.PASSWORD} name='password' placeholder='Password'/>
                        <Button text='Sign In'/>
                        <div className='w-full text-sm text-gray-500'>
                            <span>Already have an account?&nbsp;
                                <a href='#'
                                   className='hover:underline text-cyan-600'
                                >SignUp</a></span>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    )
}

export default SignIn