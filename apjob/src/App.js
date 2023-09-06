import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./layouts/Header";
import Footer from "./layouts/Footer";
import Jobs from "./layouts/Sidebar/Pages/jobs";
import CV from "./layouts/Sidebar/Pages/cv";
import Comp from "./layouts/Sidebar/Pages/company";
import Sidebar from "./layouts/Sidebar/sidebarIndex";
import './App.css';
import Login from "./components/Login";
import Signup from "./components/Signup";
import ShowBar from "./layouts/showSidebar";

const App = () => {
  return (
    <BrowserRouter>  
      <ShowBar>    
        <Header/>
      </ShowBar>

      <div className="d-flex">
        <ShowBar>
          <div className="col-auto">
            <Sidebar/>
          </div>
        </ShowBar>
        <div>
          <Routes>

            {/* <Route path="/" element={<Home />}/> */}
            <Route path="/jobs" element={<Jobs/>}></Route>
            <Route path="/cv" element={<CV/>}></Route>
            <Route path="/company" element={<Comp/>}></Route>
            <Route path="/login" element={<Login/>}></Route>
            <Route path="/signup" element={<Signup/>}></Route>

          </Routes>
        </div>
      </div>

      <ShowBar>
        <Footer/>
      </ShowBar>
    </BrowserRouter>
    
  )
}

export default App;
