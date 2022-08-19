const Home = () => {
    return (
        <div className="flex flex-col border-2 border-pink-400 m-4 p-3 rounded-md shadow-lg w-[400px]">
            <h1 className="text-3xl font-thin">Sign In</h1>
            <div className="flex p-4 data-center">
                <div className="w-1/2">Username</div>
                <div className="w-1/2"><input type="text" className="outline-none bg-slate-200 p-1"/></div>
            </div>

            <div className="flex p-4 data-center">
                <div className="w-1/2">Password</div>
                <div className="w-1/2"><input type="password" className="outline-none bg-slate-200 p-1"/></div>
            </div>
            <button className="mt-5 border border-pink-500 p-1 rounded-full w-1/2 self-center">Login</button>
        </div>
    )
}

export default Home