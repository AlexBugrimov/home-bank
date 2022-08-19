import * as React from 'react'
import {Route, Routes} from "react-router-dom";

const Home = React.lazy(() => import("./pages/Home"));
const SignIn = React.lazy(() => import("./pages/SignIn"));
const Error = React.lazy(() => import("./pages/Error"));

const Loading = () => <p>Loading ...</p>;

export const App = () => {
  return (
      <React.Suspense fallback={<Loading />}>
      <Routes>
          <Route path='/' element={<Home/>}/>
          <Route path='/sign-in' element={<SignIn/>}/>
          <Route path='*' element={<Error/>}/>
      </Routes>
      </React.Suspense>
  );
}